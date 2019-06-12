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

import java.util.Arrays;

import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.KendallsCorrelation;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.junit.Test;

/**
 * Tests for {@link Covariance}, {@link PearsonsCorrelation}, {@link SpearmansCorrelation}, 
 * {@link KendallsCorrelation}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class Correlation {
    
    private double compare(final int[] pattern, final int[] sample) {
        final double[] patternCast = Arrays.stream(pattern).asDoubleStream().toArray();
        final double[] sampleCast = Arrays.stream(sample).asDoubleStream().toArray();
        // Available correlation.
        return new Covariance().covariance(patternCast, sampleCast);
        //return new PearsonsCorrelation().correlation(patternCast, sampleCast);
        //return new SpearmansCorrelation().correlation(patternCast, sampleCast);
        //return new KendallsCorrelation().correlation(patternCast, sampleCast);
    }

    @Test
    public void test1() {
        final int[] pattern = new int[] {1, 10, 1};
        final int[] sample1 = new int[] {1, 10, 1};
        final int[] sample2 = new int[] {1, 9, 1};
        final int[] sample3 = new int[] {1, 6, 1};
        final int[] sample4 = new int[] {1, 3, 1};
        final int[] sample5 = new int[] {1, 1, 1};
        final int[] sample6 = new int[] {12, 13, 9};
        final int[] sample7 = new int[] {120, 132, 111};
        
        System.out.println(compare(pattern, sample1));
        System.out.println(compare(pattern, sample2));
        System.out.println(compare(pattern, sample3));
        System.out.println(compare(pattern, sample4));
        System.out.println(compare(pattern, sample5));
        System.out.println(compare(pattern, sample6));
        System.out.println(compare(pattern, sample7));
    }
}
