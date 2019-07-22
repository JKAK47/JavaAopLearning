package org.vincent.pool.db;

import java.sql.Connection;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author PengRong
 * @package org.vincent.pool.db
 * @ClassName ConnectionPoolImpl.java
 * @date 2019/7/20 - 21:00
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public class ConnectionPoolImpl implements ConnectionPool {
    private int initSize;
    private int maxSize;
    private int idleCount;
    private long waitTime;
    /**
     * 空闲连接
     */
    private BlockingQueue<Connection> idle;
    /**
     * 已被使用的连接
     */
    private BlockingQueue<Connection> busy;
    /* 记录当前活跃的连接数*/
    private AtomicInteger activeSize;

    @Override
    public void init(int initSize, int maxSize, int idleCount, long waitTime) {
        /* 核心连接数*/
        this.initSize = initSize;
        /* 最大连接数*/
        this.maxSize = maxSize;
        /* 允许空闲的最大连接数*/
        this.idleCount = idleCount;
        /* 获取连接的超时时间 */
        this.waitTime = waitTime;
        initConnection(this.initSize);
    }

    @Override
    public void initConnection(int initSize) {
        for (int i = 0; i < initSize; i++) {
            if (activeSize.get() < maxSize) {
                if (activeSize.incrementAndGet() <= maxSize) {
                    /* 初始化 连接*/
                    Connection connection = null; // Mysql连接工具类产生连接
                    idle.offer(connection);
                } else {
                    /*多加了 减去多加的值*/
                    activeSize.decrementAndGet();
                }
            }
        }
    }

    @Override
    public Connection get() throws InterruptedException {
        /* 先从 空闲分组获取，*/
        Connection connection =idle.poll( );
        if (Objects.nonNull(connection)){
            System.out.println("从空闲组获取");
            return  connection;
        }
        /*
        *  空闲组中没有连接， 且没有超时连接，创建一个连接返回*/
        if (activeSize.get() < maxSize) {
            if (activeSize.incrementAndGet() <= maxSize) {
                /* 初始化 连接*/
                  connection = null; // Mysql连接工具类产生连接
                idle.offer(connection);
                return connection;
            } else {
                /*多加了 减去多加的值*/
                activeSize.decrementAndGet();
            }
        }
        /*
        *  连接全忙， 等待空闲  等待  */
        connection = idle.poll(waitTime, TimeUnit.SECONDS);
        return connection;
    }

    @Override
    public void recycle(Connection connection) {
        if (connection == null){
            return;
        }
        boolean remove = busy.remove(connection);
        System.out.println(remove);
        if (remove){
            if (idleCount< idle.size()){

            }
        }
    }
}
