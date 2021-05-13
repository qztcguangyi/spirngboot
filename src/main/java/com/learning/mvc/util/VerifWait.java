/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.learning.mvc.util;
/**
 * desc
 * wait()-->wait(0) 一直阻塞，等待其他线程notify
 * wait(1000) 倒是自动恢复(如果只有一个线程，不存在竞争)
 * join(xx)-->wait(xx)
 *
 * @author Gavin Zhang
 * @created 2021/1/23
 */
public class VerifWait {

    public static void main(String[] args) {

        Object o = new Object();
        synchronized (o) {
// 未释放锁资源，只是交出了CPU时间片

            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }


//永远阻塞
//            try {
//                o.wait();
//            } catch(InterruptedException e) {
//                e.printStackTrace();
//            }

//只是阻塞了10秒，交出锁资源，然后自动运行
            try {
                o.wait(1000*10);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("hello syn");

        }

        System.out.println("end");

    }

}
