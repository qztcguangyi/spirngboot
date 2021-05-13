/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.learning.mvc.test;
/**
 * desc
 *
 * @author Gavin Zhang
 * @created 2021/3/5
 */
public class Function {


    public static void main(String[] args) {
        FunctionInterface2.div();

        FunctionInterface3.div();

    }
}


@FunctionalInterface
interface FunctionInterface {

    public int add();

    public static void multi() {
        System.out.println("abc");
    }

    public static void div() {
        System.out.println("abc");
    }

    default void test() {
        System.out.println("1");
    }

    default void test2() {
        System.out.println("1");
    }
}


interface FunctionInterface2 {

    public int add();

    public static void multi() {
        System.out.println("abc");
    }

    public static void div() {
        System.out.println("abc");
    }

    default void test() {
        System.out.println("1");
    }

    default void test2() {
        System.out.println("1");
    }
}

interface FunctionInterface3 {

    public int add();

    public int stop();

    public static void multi() {
        System.out.println("abc");
    }

    public static void div() {
        System.out.println("abc");
    }

    default void test2() {
        System.out.println("1");
    }


    default void test3() {
        System.out.println("1");
    }
}
