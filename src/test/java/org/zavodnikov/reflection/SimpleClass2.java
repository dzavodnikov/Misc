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
package org.zavodnikov.reflection;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class SimpleClass2 extends SimpleClass1 {

    public static boolean boolean2;

    protected static boolean boolean3;

    public int int2;

    private int int3;

    public SimpleClass2(final int int2, final int int3) {
        super();

        setInt2(int2);
        setInt3(int3);
    }

    public int getInt2() {
        return this.int2;
    }

    public void setInt2(final int int2) {
        this.int2 = int2;
    }

    public int getInt3() {
        return this.int3;
    }

    public void setInt3(final int int3) {
        this.int3 = int3;
    }

    /**
     * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
     */
    protected class InternalClass1 {

        public float float0;

        private float float1;

        protected InternalClass1(final float float0, final float float1) {
            setFloat0(float0);
            setFloat1(float1);
        }

        public float getFloat0() {
            return this.float0;
        }

        public void setFloat0(final float float0) {
            this.float0 = float0;
        }

        public float getFloat1() {
            return this.float1;
        }

        public void setFloat1(final float float1) {
            this.float1 = float1;
        }
    }

    /**
     * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
     */
    protected static class InternalClass2 {

        public float float0;

        private float float1;

        protected InternalClass2(final float float0, final float float1) {
            setFloat0(float0);
            setFloat1(float1);
        }

        public float getFloat0() {
            return this.float0;
        }

        public void setFloat0(final float float0) {
            this.float0 = float0;
        }

        public float getFloat1() {
            return this.float1;
        }

        public void setFloat1(final float float1) {
            this.float1 = float1;
        }
    }
}
