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
package org.zavodnikov.reflection;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class SimpleClass1 {

    public static boolean boolean0;

    protected static boolean boolean1;

    public int int0;

    private int int1;

    public int getInt0() {
        return this.int0;
    }

    public void setInt0(final int int0) {
        this.int0 = int0;
    }

    public int getInt1() {
        return this.int1;
    }

    public void setInt1(final int int1) {
        this.int1 = int1;
    }

    /**
     * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
     */
    protected class InternalClass1 {

        public float float0;

        private float float1;

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
