/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2012-2018 Dmitry Zavodnikov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.zavodnikov.narray;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@link INArrayPointer}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class INArrayPointerTest {

    @Test
    public void test_d1_i1() {
        final INArrayPointer p = new FlatNArrayPointer(1);
        assertEquals(1, p.getDimensionsNum());
        assertEquals(1, p.getDimension(0));

        assertEquals(0, p.getPosition());

        p.setIndex(0, 0);
        assertEquals(0, p.getPosition());
    }

    @Test
    public void test_d1_i2() {
        final INArrayPointer p = new FlatNArrayPointer(2);
        assertEquals(1, p.getDimensionsNum());
        assertEquals(2, p.getDimension(0));

        assertEquals(0, p.getPosition());

        p.setIndex(0, 0);
        assertEquals(0, p.getPosition());

        p.setIndex(0, 1);
        assertEquals(1, p.getPosition());
    }

    @Test
    public void test_d1_i3() {
        final INArrayPointer p = new FlatNArrayPointer(3);
        assertEquals(1, p.getDimensionsNum());
        assertEquals(3, p.getDimension(0));

        assertEquals(0, p.getPosition());

        p.setIndex(0, 0);
        assertEquals(0, p.getPosition());

        p.setIndex(0, 1);
        assertEquals(1, p.getPosition());

        p.setIndex(0, 2);
        assertEquals(2, p.getPosition());
    }

    @Test
    public void test_d1_iN() {
        final int N = 100;

        final INArrayPointer p = new FlatNArrayPointer(N);
        assertEquals(1, p.getDimensionsNum());
        assertEquals(N, p.getDimension(0));

        assertEquals(0, p.getPosition());

        for (int i = 0; i < N; ++i) {
            p.setIndex(0, i);
            assertEquals(i, p.getPosition());
        }
    }
}
