/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2010-2020 Dmitry Zavodnikov
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

import org.junit.Test;

/**
 * Tests for {@link HumanReadableByteCountHelper}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class HumanReadableByteCountHelperTest {

    private static void print(final long value) {
        //@formatter:off
        System.out.println(HumanReadableByteCountHelper.humanReadableByteCount(value, false ));
        System.out.println(HumanReadableByteCountHelper.humanReadableByteCount(value, true  ));
        //@formatter:on
        System.out.println();
    }

    @Test
    public void testPrint() {
        //@formatter:off
        HumanReadableByteCountHelperTest.print(                  0L);
        HumanReadableByteCountHelperTest.print(                 10L);
        HumanReadableByteCountHelperTest.print(               1000L);
        HumanReadableByteCountHelperTest.print(               1024L);
        HumanReadableByteCountHelperTest.print(              15000L);
        HumanReadableByteCountHelperTest.print(            2000000L);
        HumanReadableByteCountHelperTest.print(         2500000000L);
        HumanReadableByteCountHelperTest.print(      3000000000000L);
        HumanReadableByteCountHelperTest.print(   3500000000000000L);
        HumanReadableByteCountHelperTest.print(4000000000000000000L);
        //@formatter:on
    }
}
