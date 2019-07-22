package org.vincent.pool.db;

import java.sql.Connection;

/**
 * @author PengRong
 * @package org.vincent.pool.db
 * @ClassName ConnectionPool.java
 * @date 2019/7/20 - 20:58
 * @ProjectName JavaAopLearning Db 线程池  管理类接口
 * @Description: TODO
 */
public interface ConnectionPool {
    void init(int initSize,int maxSize, int idleCount, long waitTime);
    void  initConnection(int initSize);
    Connection get() throws InterruptedException;
    /** 归还 */
    void recycle(Connection connection);
}
