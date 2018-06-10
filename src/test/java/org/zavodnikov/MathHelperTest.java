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
package org.zavodnikov;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link MathHelper}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class MathHelperTest {

    @Test
    public void testRound() {
        Assert.assertEquals(0, MathHelper.round(0.0));

        Assert.assertEquals(0, MathHelper.round(0.1));
        Assert.assertEquals(0, MathHelper.round(0.2));
        Assert.assertEquals(0, MathHelper.round(0.3));
        Assert.assertEquals(0, MathHelper.round(0.4));
        Assert.assertEquals(1, MathHelper.round(0.5));
        Assert.assertEquals(1, MathHelper.round(0.6));
        Assert.assertEquals(1, MathHelper.round(0.7));
        Assert.assertEquals(1, MathHelper.round(0.8));
        Assert.assertEquals(1, MathHelper.round(0.9));

        Assert.assertEquals(1, MathHelper.round(1.0));
    }

    @Test
    public void testRoundUp() {
        Assert.assertEquals(0, MathHelper.roundUp(0.0));

        Assert.assertEquals(1, MathHelper.roundUp(0.1));
        Assert.assertEquals(1, MathHelper.roundUp(0.2));
        Assert.assertEquals(1, MathHelper.roundUp(0.3));
        Assert.assertEquals(1, MathHelper.roundUp(0.4));
        Assert.assertEquals(1, MathHelper.roundUp(0.5));
        Assert.assertEquals(1, MathHelper.roundUp(0.6));
        Assert.assertEquals(1, MathHelper.roundUp(0.7));
        Assert.assertEquals(1, MathHelper.roundUp(0.8));
        Assert.assertEquals(1, MathHelper.roundUp(0.9));

        Assert.assertEquals(1, MathHelper.roundUp(1.0));
    }

    @Test
    public void testRoundDown() {
        Assert.assertEquals(0, MathHelper.roundDown(0.0));

        Assert.assertEquals(0, MathHelper.roundDown(0.1));
        Assert.assertEquals(0, MathHelper.roundDown(0.2));
        Assert.assertEquals(0, MathHelper.roundDown(0.3));
        Assert.assertEquals(0, MathHelper.roundDown(0.4));
        Assert.assertEquals(0, MathHelper.roundDown(0.5));
        Assert.assertEquals(0, MathHelper.roundDown(0.6));
        Assert.assertEquals(0, MathHelper.roundDown(0.7));
        Assert.assertEquals(0, MathHelper.roundDown(0.8));
        Assert.assertEquals(0, MathHelper.roundDown(0.9));

        Assert.assertEquals(1, MathHelper.roundDown(1.0));
    }

    @Test
    public void testLog2() {
        Assert.assertEquals(1.0, MathHelper.log2(2.0), Double.MIN_VALUE);
        Assert.assertEquals(2.0, MathHelper.log2(4.0), Double.MIN_VALUE);
        Assert.assertEquals(3.0, MathHelper.log2(8.0), Double.MIN_VALUE);
    }

    @Test
    public void testExtendArraySize() {
        //@formatter:off
        Assert.assertEquals( 1, MathHelper.extendArraySize( 0));
        Assert.assertEquals( 1, MathHelper.extendArraySize( 1));
        Assert.assertEquals( 2, MathHelper.extendArraySize( 2));
        Assert.assertEquals( 4, MathHelper.extendArraySize( 3));
        Assert.assertEquals( 4, MathHelper.extendArraySize( 4));
        Assert.assertEquals( 8, MathHelper.extendArraySize( 5));
        Assert.assertEquals( 8, MathHelper.extendArraySize( 6));
        Assert.assertEquals( 8, MathHelper.extendArraySize( 7));
        Assert.assertEquals( 8, MathHelper.extendArraySize( 8));
        Assert.assertEquals(16, MathHelper.extendArraySize( 9));
        Assert.assertEquals(16, MathHelper.extendArraySize(10));
        Assert.assertEquals(16, MathHelper.extendArraySize(11));
        Assert.assertEquals(16, MathHelper.extendArraySize(12));
        Assert.assertEquals(16, MathHelper.extendArraySize(13));
        Assert.assertEquals(16, MathHelper.extendArraySize(14));
        Assert.assertEquals(16, MathHelper.extendArraySize(15));
        Assert.assertEquals(16, MathHelper.extendArraySize(16));
        //@formatter:on
    }

    @Test
    public void testReduceArraySize() {
        //@formatter:off
        Assert.assertEquals(16, MathHelper.reduceArraySize(16, 16));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16, 15));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16, 14));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16, 13));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16, 12));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16, 11));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16, 10));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16,  9));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16,  8));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16,  7));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16,  6));
        Assert.assertEquals(16, MathHelper.reduceArraySize(16,  5));
        Assert.assertEquals( 8, MathHelper.reduceArraySize(16,  4));
        Assert.assertEquals( 8, MathHelper.reduceArraySize(16,  3));
        Assert.assertEquals( 4, MathHelper.reduceArraySize(16,  2));
        Assert.assertEquals( 2, MathHelper.reduceArraySize(16,  1));
        Assert.assertEquals( 1, MathHelper.reduceArraySize(16,  0));
        //@formatter:on
    }

    @Test
    public void testCalculateArraySize() {
        //@formatter:off
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 0));
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 1));
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 2));
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 3));
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 4));
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 5));
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 6));
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 7));
        Assert.assertEquals( 8, MathHelper.calculateArraySize(0, 8));
        Assert.assertEquals(16, MathHelper.calculateArraySize(0, 9));
        //@formatter:on
    }
}
