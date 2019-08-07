/*
 *
 * Copyright (C) 2003-2019 Rocket Software BV
 *
 * BeforeAfter.java
 * Created on 23 Jul 2019
 *
 */
package org.zavodnikov.junit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dzavodnikov
 */
public class BeforeAfter extends AbstractBeforeAfter {

    @BeforeClass
    public static void before1() {
        System.out.println("before1");
    }

    @BeforeClass
    public static void before2() {
        System.out.println("before2");
    }

    @AfterClass
    public static void after1() {
        System.out.println("after1");
    }

    @AfterClass
    public static void after2() {
        System.out.println("after2");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }
}
