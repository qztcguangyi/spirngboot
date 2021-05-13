package com.learning.mvc.juc;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/** 
 * @Description:  k
 * @Param:  
 * @return:  
 * @Author: Gavin Zhang
 * @Date: 2021/1/11 
*/ 
public class TestJoin implements Runnable {


    public static void main(String[] sure) throws InterruptedException {
        Thread t = new Thread(new TestJoin());
        long start = System.currentTimeMillis();
        t.start();
        //线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B
        //其实join方法本质就是利用上面的线程实例作为对象锁的原理，当线程终止时，会调用线程自身的notifyAll()方法，通知所有等待在该线程对象上的线程的特征
        //为啥？a.wait()，不是a等待吗？其实是哪个线程调用wait()就是那个线程让出资源，你想啊SynWiatAndNotify里面调用了o.wait()，难道是让o对象wait吗
        t.join();//等待线程t 1000毫秒 等价于o.wait()
        System.out.println(System.currentTimeMillis()-start);//打印出时间间隔
        System.out.println("Main finished");//打印主线程结束
    }

    @Override
    public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    sleep(1000);//睡眠5秒，循环是为了方便输出信息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("睡眠" + i);
            }
            System.out.println("TestJoin finished");//t线程结束
        }
}