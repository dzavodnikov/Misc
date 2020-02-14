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
    public void test_d1() {
        final int D = 1;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        int sum = 0;
        for (int i = 0; i < N; ++i) {
            pointer.index(0, i);

            assertEquals(sum, pointer.index());

            ++sum;
        }
    }

    @Test
    public void test_d2() {
        final int D = 2;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        int sum = 0;
        for (int j = 0; j < N; ++j) {
            for (int i = 0; i < N; ++i) {
                pointer.index(0, i);
                pointer.index(1, j);

                assertEquals(sum, pointer.index());

                ++sum;
            }
        }
    }

    @Test
    public void test_d3() {
        final int D = 3;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        assertEquals(0, pointer.index());

        int sum = 0;
        for (int k = 0; k < N; ++k) {
            for (int j = 0; j < N; ++j) {
                for (int i = 0; i < N; ++i) {
                    pointer.index(0, i);
                    pointer.index(1, j);
                    pointer.index(2, k);

                    assertEquals(sum, pointer.index());

                    ++sum;
                }
            }
        }
    }

    @Test
    public void test_d4() {
        final int D = 4;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        int sum = 0;
        for (int l = 0; l < N; ++l) {
            for (int k = 0; k < N; ++k) {
                for (int j = 0; j < N; ++j) {
                    for (int i = 0; i < N; ++i) {
                        pointer.index(0, i);
                        pointer.index(1, j);
                        pointer.index(2, k);
                        pointer.index(3, l);

                        assertEquals(sum, pointer.index());

                        ++sum;
                    }
                }
            }
        }
    }

    @Test
    public void test_d5() {
        final int D = 5;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        int sum = 0;
        for (int m = 0; m < N; ++m) {
            for (int l = 0; l < N; ++l) {
                for (int k = 0; k < N; ++k) {
                    for (int j = 0; j < N; ++j) {
                        for (int i = 0; i < N; ++i) {
                            pointer.index(0, i);
                            pointer.index(1, j);
                            pointer.index(2, k);
                            pointer.index(3, l);
                            pointer.index(4, m);

                            assertEquals(sum, pointer.index());

                            ++sum;
                        }
                    }
                }
            }
        }
    }

    @Test
    public void test_d6() {
        final int D = 6;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        int sum = 0;
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < N; ++m) {
                for (int l = 0; l < N; ++l) {
                    for (int k = 0; k < N; ++k) {
                        for (int j = 0; j < N; ++j) {
                            for (int i = 0; i < N; ++i) {
                                pointer.index(0, i);
                                pointer.index(1, j);
                                pointer.index(2, k);
                                pointer.index(3, l);
                                pointer.index(4, m);
                                pointer.index(5, n);

                                assertEquals(sum, pointer.index());

                                ++sum;
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void test_d7() {
        final int D = 7;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        int sum = 0;
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < N; ++m) {
                for (int l = 0; l < N; ++l) {
                    for (int k = 0; k < N; ++k) {
                        for (int j = 0; j < N; ++j) {
                            for (int i = 0; i < N; ++i) {
                                pointer.index(0, i);
                                pointer.index(1, j);
                                pointer.index(2, k);
                                pointer.index(3, l);
                                pointer.index(4, m);
                                pointer.index(5, n);

                                assertEquals(sum, pointer.index());

                                ++sum;
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void test_d8() {
        final int D = 8;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        int sum = 0;
        for (int o = 0; o < N; ++o) {
            for (int n = 0; n < N; ++n) {
                for (int m = 0; m < N; ++m) {
                    for (int l = 0; l < N; ++l) {
                        for (int k = 0; k < N; ++k) {
                            for (int j = 0; j < N; ++j) {
                                for (int i = 0; i < N; ++i) {
                                    pointer.index(0, i);
                                    pointer.index(1, j);
                                    pointer.index(2, k);
                                    pointer.index(3, l);
                                    pointer.index(4, m);
                                    pointer.index(5, n);
                                    pointer.index(6, o);

                                    assertEquals(sum, pointer.index());

                                    ++sum;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void test_d9() {
        final int D = 9;
        final int N = 10;

        final int[] dimArr = new int[D];
        for (int i = 0; i < D; ++i) {
            dimArr[i] = N;
        }

        final INArrayPointer pointer = new FlatNArrayPointer(dimArr);
        assertEquals(D, pointer.dimensions());
        assertEquals((int) Math.pow(N, D), pointer.size());

        for (int i = 0; i < D; ++i) {
            assertEquals(N, pointer.dimension(i));
        }

        int sum = 0;
        for (int p = 0; p < N; ++p) {
            for (int o = 0; o < N; ++o) {
                for (int n = 0; n < N; ++n) {
                    for (int m = 0; m < N; ++m) {
                        for (int l = 0; l < N; ++l) {
                            for (int k = 0; k < N; ++k) {
                                for (int j = 0; j < N; ++j) {
                                    for (int i = 0; i < N; ++i) {
                                        pointer.index(0, i);
                                        pointer.index(1, j);
                                        pointer.index(2, k);
                                        pointer.index(3, l);
                                        pointer.index(4, m);
                                        pointer.index(5, n);
                                        pointer.index(6, o);
                                        pointer.index(7, p);

                                        assertEquals(sum, pointer.index());

                                        ++sum;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
