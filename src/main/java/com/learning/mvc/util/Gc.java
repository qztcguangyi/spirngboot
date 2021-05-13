/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.learning.mvc.util;
/**
 * JVM 测试
 *
 * @author Gavin Zhang
 * @created 2020/12/17
 */
public class Gc {

    public static void main(String[] args) throws Exception{
        M m = new M();
        for(;;) {
           //Reads the next byte of data from the input stream  10-->0x0A-->换行键 49-->0x31-->1
           //回车也算一个byte
           //因此如果输入 1桥下回车--》33
           //因此如果输入 111111桥下回车--》3333333
           int x = System.in.read();
           m.m();
        }



    }

}

class M {

    public void m() {
        System.out.print("3");
    }

}
