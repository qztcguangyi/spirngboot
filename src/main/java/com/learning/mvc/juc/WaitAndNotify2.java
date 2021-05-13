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
 I----------------------:B	0
 W----------------------:B	0
 I----------------------:D	0
 W----------------------:D	0
 I----------------------:C	0
 O----------------------:C	1
 I----------------------:C	1
 W----------------------:C	1
 O----------------------:D	0
 I----------------------:D	0
 W----------------------:D	0
 O----------------------:B	-1
 I----------------------:B	-1
 O----------------------:B	-2
 I----------------------:B	-2
 O----------------------:B	-3
 I----------------------:B	-3
 O----------------------:B	-4
 I----------------------:B	-4
 O----------------------:B	-5
 I----------------------:B	-5
 O----------------------:B	-6
 I----------------------:B	-6
 O----------------------:B	-7
 I----------------------:B	-7
 O----------------------:B	-8
 I----------------------:B	-8
 O----------------------:B	-9
 I----------------------:B	-9
 O----------------------:B	-10
 O----------------------:D	-11
 I----------------------:D	-11
 O----------------------:D	-12
 I----------------------:D	-12
 O----------------------:D	-13
 I----------------------:D	-13
 O----------------------:D	-14
 I----------------------:D	-14
 O----------------------:D	-15
 I----------------------:D	-15
 O----------------------:D	-16
 I----------------------:D	-16
 O----------------------:D	-17
 I----------------------:D	-17
 O----------------------:D	-18
 I----------------------:D	-18
 O----------------------:D	-19
 O----------------------:C	-18
 I----------------------:C	-18
 W----------------------:C	-18
 I----------------------:A	-18
 W----------------------:A	-18

 https://www.zhihu.com/question/27654579 状态图
 1.为什么B和D都在等待之后C调动Wait后，D就出去了？这种情况怎么分析：
 WaitSet和BlockSet合并后选出一个
 I----------------------:B	0
 W----------------------:B	0
 I----------------------:D	0
 W----------------------:D	0
 I----------------------:C	0
 O----------------------:C	1
 I----------------------:C	1
 W----------------------:C	1
 O----------------------:D	0

 2.为什么会卡死？B和D已经全部执行完，A和C相互等，死锁
 O----------------------:C	-18
 I----------------------:C	-18
 W----------------------:C	-18
 I----------------------:A	-18
 W----------------------:A	-18

 *
 *
 *
 * @author Gavin Zhang
 * @created 2021/3/5
 */
public class WaitAndNotify2 {

    private  int state = 0;


    List<String> list = new ArrayList<>();
    List<String> info = new ArrayList<>();

    public  synchronized void add () throws  InterruptedException{
        System.out.println("I----------------------:"+Thread.currentThread().getName()+"\t"+state);
        if(state != 0) {
            System.out.println("W----------------------:"+Thread.currentThread().getName()+"\t"+state);
            wait();
        }
        state++;
        System.out.println("O----------------------:"+Thread.currentThread().getName()+"\t"+state);
        info.add(Thread.currentThread().getName()+"\t"+state);
        list.add(Thread.currentThread().getName());
        //System.out.println(Thread.currentThread().getName()+"\t"+state);
        //第一次会导致越界 且 list.size()-倒数第二个
//        if(list.size() != 1 && list.get(list.size()-2).equalsIgnoreCase(Thread.currentThread().getName())) {
//            for(String s : info) {
//                System.out.println(s);
//            }
//            System.out.println("exit add");
//            System.exit(0);
//        }


        notifyAll();


    }


    public  synchronized void min () throws  InterruptedException{

        System.out.println("I----------------------:"+Thread.currentThread().getName()+"\t"+state);
        if(state == 0) {
            System.out.println("W----------------------:"+Thread.currentThread().getName()+"\t"+state);
            wait();
        }
        state--;
        System.out.println("O----------------------:"+Thread.currentThread().getName()+"\t"+state);
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
        WaitAndNotify2 waitAndNotify = new WaitAndNotify2();
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



