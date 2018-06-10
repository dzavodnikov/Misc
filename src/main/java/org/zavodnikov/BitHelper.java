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
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class BitHelper {

    public static String getBinaryString(final int value, final boolean format) {
        final StringBuilder sb = new StringBuilder();

        final int maxIntegerValue = 1 << 31;
        for (int i = 0; i < 32; ++i) {
            int bitValue;
            if ((value & maxIntegerValue >>> i) != 0) {
                bitValue = 1;
            } else {
                bitValue = 0;
            }

            if (format) {
                sb.append("| ");
            }

            sb.append(bitValue);
        }
        if (format) {
            sb.append("|\n");
        }

        return sb.toString();
    }

    private static void addLine(final StringBuilder sb) {
        for (int i = 0; i < 32; ++i) {
            sb.append("+--");
        }
        sb.append("+\n");
    }

    private static void addHeader(final StringBuilder sb) {
        for (int i = 31; i >= 0; --i) {
            sb.append(String.format("|%2d", i));
        }
        sb.append("|\n");
    }

    public static String getFormattedBinaryString(final int value) {
        final StringBuilder sb = new StringBuilder();

        sb.append("Value: ");
        sb.append(value);
        sb.append("\n");

        BitHelper.addLine(sb);

        BitHelper.addHeader(sb);

        BitHelper.addLine(sb);

        sb.append(BitHelper.getBinaryString(value, true));

        BitHelper.addLine(sb);

        return sb.toString();
    }
}
