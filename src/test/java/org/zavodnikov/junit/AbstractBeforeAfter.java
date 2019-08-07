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

/**
 * @author dzavodnikov
 */
public abstract class AbstractBeforeAfter {

    @BeforeClass
    public static void before0() {
        System.out.println("before0");
    }

    @AfterClass
    public static void after0() {
        System.out.println("after0");
    }
}
