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
package org.zavodnikov.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class UrlUtils {

    /**
     * Analog of <code>encodeURIComponent(value).replace(new RegExp('%20', 'g'), '+').replace(new RegExp('%C2%A0', 'g'),
     * '+')</code> form JavaScript.
     * <p>
     * See <a href="https://stackoverflow.com/questions/607176/">Java equivalent to
     * JavaScript's encodeURIComponent that produces identical output</a> and <a
     * href="https://stackoverflow.com/questions/1634271/">URL encoding the space character: + or %20?</a> for more
     * details.
     * </p>
     */
    public static String encode(final String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }

        try {
            //@formatter:off
            return URLEncoder.encode(value, "UTF-8" )
                    .replaceAll("\u00A0",   "+"     )   // https://en.wikipedia.org/wiki/Non-breaking_space
                    .replaceAll("\\%21",    "!"     )
                    .replaceAll("\\%27",    "'"     )
                    .replaceAll("\\%28",    "("     )
                    .replaceAll("\\%29",    ")"     )
                    .replaceAll("\\%7E",    "~"     )
                    ;
            //@formatter:on
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Analog of <code>decodeURIComponent(value).replace(new RegExp('\\+', 'g'), ' ')</code> form JavaScript.
     * <p>
     * See <a
     * href="https://stackoverflow.com/questions/607176/">Java equivalent to JavaScript's encodeURIComponent that
     * produces identical output</a> and <a href="https://stackoverflow.com/questions/1634271/">URL encoding the space
     * character: + or %20?</a> for more details.
     * </p>
     */
    public static String decode(final String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }

        try {
            //@formatter:off
            return URLDecoder.decode(value, "UTF-8" )
                    .replaceAll("\u00A0",   " "     )   // https://en.wikipedia.org/wiki/Non-breaking_space
                    ;
            //@formatter:on
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
