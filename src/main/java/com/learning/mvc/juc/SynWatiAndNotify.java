/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.learning.mvc.juc;

import java.util.concurrent.CountDownLatch;

/**
 * desc 控制线程顺序 必须先打印数字，然后打印字母

  两个细节：
  1.for循环之后必须notify，否则肯定有一个线程卡死，导致程序无法正常结束
  2.如果只有syn，notify , wait 是无法完成目标的，(即时使用你以为的awit控制)，归根结底是因为无法保证哪个线程一定先执行


  一共有六种写法：很多都要配合countdownlatch
  1.syn-wait-notify

  2.LockSupport--park unpark --> 整理下线程状态图 https://www.jianshu.com/p/92cdf9a73f7d   https://segmentfault.com/a/1190000014436679(locksupport原理)

  3.ReetEntryLock-->Condition await single

  4.比较优雅 --> TransBlockQueue 【1】

  5.LinkedBlockQueue --> take put

  6.AutomInteger ---> while cas


 *
 * @author Gavin Zhang
 * @created 2021/1/9
 */
public class SynWatiAndNotify {





    public static void main(String[] args){

        char i[] = {'1','2','3','4','5','6','7'};

        char c[] = {'a','b','c','d','e','f','g'};

        Object o = new Object();

        CountDownLatch lanch  = new CountDownLatch(1);

        new Thread(() -> {
            synchronized(o) {
              for(char temp : i) {
                  System.out.print(temp);
                  lanch.countDown();
                  try {
                      o.notify();
                      o.wait();
                  } catch(Exception e) {

                  }
              }
              o.notify();
          }


        }).start();

        new Thread(() -> {
            try {
                lanch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(o) {
                for(char temp : c) {
                    System.out.print(temp);
                    try {
                      o.notify();
                      o.wait();
                    } catch(Exception e) {
                    }
                }
                o.notify();
            }

        }).start();
    }
}
