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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Lexer class.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 * @version 5.0.0
 */
public class Lexer {

    /**
     * Split string to tokens.
     *
     * @param input
     *            String that will be split to tokens.
     * @return List of tokens.
     * @throws TokenizationException
     *             If string can not be split to tokens.
     */
    public static <T extends Enum<T> & TokenType> List<Token<T>> tokenization(final Class<T> tokenTypes,
            final String input) throws TokenizationException {
        if (tokenTypes == null) {
            throw new IllegalArgumentException("Parameter \"tokenTypes\" can not be null");
        }

        final List<Token<T>> tokens = new ArrayList<>();
        if (input != null && input.length() > 0) {
            int currentPosition = 0;
            while (currentPosition < input.length()) {
                final String currentInput = input.substring(currentPosition, input.length());

                // Search token closest to begin of string.
                //@formatter:off
                int tokenPosition   = currentInput.length();
                String tokenValue   = null;
                T tokenType         = null;
                //@formatter:on
                for (final T type : tokenTypes.getEnumConstants()) {
                    final Matcher matcher = type.getTokenPattern().matcher(currentInput);
                    if (matcher.find()) {
                        if (tokenPosition > matcher.start()) {
                            //@formatter:off
                            tokenPosition   = matcher.start();
                            tokenValue      = matcher.group();
                            tokenType       = type;
                            //@formatter:on
                        }

                        // Best token (nearest to current position) found.
                        if (tokenPosition == 0) {
                            break;
                        }
                    }
                }

                // Check if proper token type was not found.
                if (tokenPosition != 0) {
                    throw new TokenizationException(String.format("Can not recognize string fragment \"%s\"",
                            currentInput.substring(0, tokenPosition)));
                }

                // Add token to output.
                final int next = currentPosition + tokenValue.length();
                if (!tokenType.isSkip()) {
                    //@formatter:off
                    tokens.add(new Token<>(
                            tokenType,
                            tokenType.preprocessing(tokenValue),
                            currentPosition,
                            next
                        ));
                    //@formatter:on
                }
                currentPosition = next;
            }
        }

        return tokens;
    }
}
