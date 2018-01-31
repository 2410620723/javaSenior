package com.java.hash;

/**
 * HashMap那些事
 *  一、搞清楚什么是hash，以及什么是hash表
 *
 *  1.hash 它是将任意长度的二进制值通过一个映射关系转换成一个固定长度的二进制值
 *      1）任意长度的二进制值
 *      2）映射关系（哈希算法 -- 相当于大学里面的学号的一个映射规则）
 *      3）固定的二进制值（哈希值 -- 相当于我们大学里面的学号）
 *      任意长度的二进制值  和  固定长度的二进制值  是一个一一对应的关系
 *      固定长度的二进制值就相当于一个任意长度二进制值的一个摘要、就相当于一个key
 *      真正有效的数据，就是这个学员的基本信息，一个任意长度的二进制值 value
 *      key -- value
 *      hash只是确定了一个key和value的唯一映射关系
 *
 *    2.hash表：最重要的特性：它存储效率很高，取数据的时间复杂度是O(1)
 *      hash  通过一个key一个输入，通过一个哈希函数，找到数组中与这个key唯一映射的value
 *      根据这个hash函数，找到数组中这个value的下标
 *      table aaa = [];
 *      int index = hash(key);
 *      int value = aaa[index];
 *
 *    3.hash函数
 *      key：找下标，有哪些方法可以找到下标
 *          1）取模算法：定义数 aaa 长度是16
 *          int index = key % m
 *          m的取值规则：m要取比数组长度小的最大质数，m = 15
 *          key = 1,value = 23; index = 1%15(1);
 *          key = 17,value = 22; index = 17%15(2)
 *          key = 16,value = 44; index = 16%15(1)
 *
 *     4.hash表处理冲突
 *      线性探测法：探测步长是1，当index为1的位置有值了，则去探测index为2的位置，若没有值，则放到index为2的位置，否则继续向后探测
 *      链表法：index的位置有值的时候，会将原来的值复制一份，然后将新的值放到那个位置，然后将新的值的下个地址指向复制的那个值。
 *
 *     总结：
 *      hash：记住学号的例子
 *      hash表：首先记住存数据的规则（数组与链表）。
 *
 *    二、HashMap
 *      讲HashMap的put方法
 *      讲HashMap的get方法
 *      讲HashMap的扩容机制
 *          HashMap的初始长度，负载因子
 *      自己实现一个HashMap
 *
 */
public class HashMapDemo {
    public static void main(String[] args) {
        MyMap<String, String> myMap = new MyHashMap<>();
        myMap.put("test", "test MyHashMap");
        System.out.println(myMap.get("test"));
    }
}
