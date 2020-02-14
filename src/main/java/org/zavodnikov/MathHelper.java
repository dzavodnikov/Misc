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

/**
 * Some useful mathematical functions.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class MathHelper {

    public final static double EXTEND_SCALE = 2.0;

    public final static double REDUCE_SCALE = 1.0 / (MathHelper.EXTEND_SCALE * MathHelper.EXTEND_SCALE);

    public final static int MIN_ARRAY_SIZE = 8;

    /**
     * Round value to nearest integer.
     * <p>
     * For example:
     * <ul>
     * <li><code>0.0 --> 0</code></li>
     * <li><code>0.1 -->
     * 0</code></li>
     * <li><code>0.5 --> 1</code></li>
     * <li><code>0.9 --> 1</code></li>
     * <li><code>1.0 --> 1</code></li>
     * </ul>
     * </p>
     */
    public static int round(final double value) {
        return (int) Math.round(value);
    }

    /**
     * Round value to greater integer.
     * <p>
     * For example:
     * <ul>
     * <li><code>0.0 --> 0</code></li>
     * <li><code>0.1 -->
     * 1</code></li>
     * <li><code>0.5 --> 1</code></li>
     * <li><code>0.9 --> 1</code></li>
     * <li><code>1.0 --> 1</code></li>
     * </ul>
     * </p>
     */
    public static int roundUp(final double value) {
        return (int) Math.ceil(value);
    }

    /**
     * Round value to less integer.
     * <p>
     * For example:
     * <ul>
     * <li><code>0.0 --> 0</code></li>
     * <li><code>0.1 -->
     * 0</code></li>
     * <li><code>0.5 --> 0</code></li>
     * <li><code>0.9 --> 0</code></li>
     * <li><code>1.0 --> 1</code></li>
     * </ul>
     * </p>
     */
    public static int roundDown(final double value) {
        return (int) Math.floor(value);
    }

    /**
     * Calculate <code>log<sub>2</sub>(x)</code>.
     */
    public static double log2(final double value) {
        return Math.log(value) / Math.log(2.0);
    }

    public static int extendArraySize(final int size) {
        if (size == 0) {
            return 1;
        } else {
            return MathHelper.roundUp(Math.pow(MathHelper.EXTEND_SCALE, MathHelper.roundUp(MathHelper.log2(size))));
        }
    }

    public static int reduceArraySize(final int oldSize, final int newSize) {
        if (newSize <= MathHelper.REDUCE_SCALE * oldSize) {
            return MathHelper.extendArraySize(MathHelper.roundUp(MathHelper.EXTEND_SCALE * newSize));
        } else {
            return oldSize;
        }
    }

    public static int calculateArraySize(final int oldSize, final int newSize) {
        int newArraySize;

        if (oldSize < newSize) {
            newArraySize = MathHelper.extendArraySize(newSize);
        } else {
            newArraySize = MathHelper.reduceArraySize(oldSize, newSize);
        }

        if (newArraySize < MathHelper.MIN_ARRAY_SIZE) {
            return MathHelper.MIN_ARRAY_SIZE;
        } else {
            return newArraySize;
        }
    }
}
