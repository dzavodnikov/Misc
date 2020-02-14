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
package org.zavodnikov.lexer;

import java.util.regex.Pattern;

/**
 * Interface for {@link Token} types.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 * @version 5.0.0
 */
public interface TokenType {

    /**
     * @return <a href="https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html#cg">capturing group</a>
     *             pattern. Note: Use zero-width positive lookahead to sole problem with multiple prefix.
     */
    Pattern getTokenPattern();

    /**
     * @return <code>true</code> if this token should be removed from result list and <code>false</code> otherwise.
     */
    boolean isSkip();

    /**
     * Perform first value preprocessing.
     *
     * @param rawValue
     *            Original value from string.
     * @return updated value.
     */
    String preprocessing(String rawValue);
}
