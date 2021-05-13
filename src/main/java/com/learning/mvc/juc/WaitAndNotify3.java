/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.learning.mvc.juc;

import java.util.ArrayList;
import java.util.List;

/**
 * desc
 *
 *
 * 1.如果用if，造成虚假唤醒，会输出，但不会出现同一个线程连续加的情况？讲道理是会的，但是确实没有模拟出来，为什么？
 *
 *
 *
 * @author Gavin Zhang
 * @created 2021/3/5
 */
public class WaitAndNotify3 {

    private  int state = 0;


    List<String> list = new ArrayList<>();
    List<String> info = new ArrayList<>();

    public  synchronized void add () throws  InterruptedException{
        if(state != 0) {
            wait();
        }
        state++;
        info.add(Thread.currentThread().getName()+"\t"+state);
        list.add(Thread.currentThread().getName());
        //System.out.println(Thread.currentThread().getName()+"\t"+state);
        //第一次会导致越界 且 list.size()-倒数第二个
        if(list.size() != 1 && list.get(list.size()-2).equalsIgnoreCase(Thread.currentThread().getName())) {
            for(String s : info) {
                System.out.println(s);
            }
            System.out.println("exit add");
            System.exit(0);
        }


        notifyAll();


    }


    public  synchronized void min () throws  InterruptedException{

        if(state == 0) {
            wait();
        }
        state--;
        info.add(Thread.currentThread().getName()+"\t"+state);
        list.add(Thread.currentThread().getName());
        //System.out.println(Thread.currentThread().getName()+"\t"+state);
//        if(list.size() != 1 && list.get(list.size()-2).equalsIgnoreCase(Thread.currentThread().getName())) {
//            for(String s : info) {
//                System.out.println(s);
//            }
//            System.out.println("exit min");
//            System.exit(0);
//        }

        notifyAll();

    }

    public static void main(String[] args)  {
        WaitAndNotify3 waitAndNotify = new WaitAndNotify3();
        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    waitAndNotify.add();
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        },"A").start();


        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    waitAndNotify.min();
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        },"B").start();


        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    waitAndNotify.add();
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        },"C").start();


        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    waitAndNotify.min();
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        },"D").start();


    }


}



