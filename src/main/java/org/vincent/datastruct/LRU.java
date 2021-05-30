package org.vincent.datastruct;

import java.util.LinkedList;
import java.util.List;

/**
 * @author PengRong
 * @package org.vincent.datastruct
 * @ClassName LRU.java
 * @date 2021/5/30 - 0:07
 * @ProjectName JavaAopLearning
 * @Description: 数据结构，LRU 最近最少使用策略
 */
public class LRU<T> {
    List<T> lruCache = null;

    public static <T> LRU builderLru() {
        LRU<T> lru = new LRU<>();
        lru.lruCache = new LinkedList<>();
        return lru;
    }

    public  <E>  String setLruCache(E lruList){
        return "";
    }

    public  <E>  void setLruCache2(E lruList){

    }

}
