/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.learning.mvc.juc;

import io.swagger.models.auth.In;

/**
 * desc
 *
 *
 * 1.如果用if，造成虚假唤醒，会输出，但不会出现同一个线程连续加的情况？讲道理是会的，但是确实没有模拟出来，为什么？
 * 减的条件很宽松，下次唤醒还是我，发现不是0就继续减
 * D	-10
 * D	-11
 * D	-12
 * D	-13
 * D	-14
 * D	-15
 * D	-16
 *
 * 会出现切换加
 B	1
 C	2
 A	3
 C	4
 A	5
 C	6
 A	7
 C	8
 A	9
 D	8

 *
 * 2.出现程序卡住的情况，既由线程次数不能执行完，能正常执行完一定是0
 *
 *
 *
 *
 * ===============================================
 * 上面的直观想法是正确的吗？
 * 执行一百万遍
 *
 *
 *
 * @author Gavin Zhang
 * @created 2021/3/5
 */
public class WaitAndNotify {

    private  int state = 0;

    public  synchronized void add () throws  InterruptedException{

        if(state != 0) {
            wait();
        }
        state++;
        System.out.println(Thread.currentThread().getName()+"\t"+state);
        notifyAll();

    }


    public  synchronized void min () throws  InterruptedException{

        if(state == 0) {
            wait();
        }
        state--;
        System.out.println(Thread.currentThread().getName()+"\t"+state);
        notifyAll();

    }

    public static void main(String[] args) throws Exception {
        WaitAndNotify waitAndNotify = new WaitAndNotify();
        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    waitAndNotify.add();
                } catch(Exception e) {
                }

            }
        },"A").start();


        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    waitAndNotify.min();
                } catch(Exception e) {
                }

            }
        },"B").start();


        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    waitAndNotify.add();
                } catch(Exception e) {
                }

            }
        },"C").start();


        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    waitAndNotify.min();
                } catch(Exception e) {
                }

            }
        },"D").start();


    }


}



