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

/**
 * Human-readable byte count. Based on <a href="https://stackoverflow.com/questions/3758606/">3758606</a>.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class HumanReadableByteCountHelper {

    public static String humanReadableByteCount(final long bytes, final boolean si) {
        // Check value.
        if (bytes < 0) {
            throw new IllegalArgumentException("File size can not be less than zero!");
        }

        //@formatter:off
        int         unit;
        String[]    postfix;
        if (si) {
            unit = 1000;
            postfix = new String[]{  "kB",  "MB",  "GB",  "TB",  "PB",  "EB" };
        } else {
            unit = 1024;
            postfix = new String[]{ "KiB", "MiB", "GiB", "TiB", "PiB", "EiB" };
        }
        //@formatter:on

        if (bytes < unit) {
            // Integer number.
            return String.format("%s B", bytes);
        } else {
            // Float-point number.
            final int exp = (int) (Math.log(bytes) / Math.log(unit));
            return String.format("%.1f %s", bytes / Math.pow(unit, exp), postfix[exp - 1]);
        }
    }
}
