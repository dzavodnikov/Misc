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
 * Tests for {@link BitHelper}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class BitHelperTest {

    @Test
    public void testGetBinaryString() {
        Assert.assertEquals("00000000000000000000000000000000", BitHelper.getBinaryString(0, false));
        Assert.assertEquals("00000000000000000000000000000001", BitHelper.getBinaryString(1, false));
        Assert.assertEquals("00000000000000000000000000000010", BitHelper.getBinaryString(2, false));
        Assert.assertEquals("00000000000000000000000000000011", BitHelper.getBinaryString(3, false));
        Assert.assertEquals("00000000000000000000000000000100", BitHelper.getBinaryString(4, false));
    }

    @Test
    public void testGetFormattedBinaryStringPrint() {
        System.out.println(BitHelper.getFormattedBinaryString(0));
        System.out.println(BitHelper.getFormattedBinaryString(1));
        System.out.println(BitHelper.getFormattedBinaryString(2));
        System.out.println(BitHelper.getFormattedBinaryString(3));
        System.out.println(BitHelper.getFormattedBinaryString(4));
    }
}
