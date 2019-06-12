/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2012-2019 Dmitry Zavodnikov
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
package org.zavodnikov.math;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.junit.Test;

/**
 * Tests for {@link SimpleRegression}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class LinearRegression {
    
    public static int findValue(final SimpleRegression regression, final double value) {
        return (int)((value - regression.getIntercept()) / regression.getSlope());
    }

    @Test
    public void test1() {
        final SimpleRegression regression = new SimpleRegression();
        
        final int[] observations = new int[] { 1, 1, 1, 2, 2, 5, 2, 3, 1};
        for (int i = 0; i < observations.length; ++i) {
            regression.addData(i + 1, observations[i]);
        }
        
        final int predictTime = observations.length + 1;
        //final double predict = regression.getSlope() * predictTime + regression.getIntercept();
        System.out.println(regression.predict(predictTime));
        System.out.println(findValue(regression, 5));
    }
}
