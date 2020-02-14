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
package org.zavodnikov.lexer.examples;

import java.util.List;
import java.util.regex.Pattern;

import org.zavodnikov.lexer.Lexer;
import org.zavodnikov.lexer.Token;
import org.zavodnikov.lexer.TokenType;
import org.zavodnikov.lexer.TokenizationException;

/**
 * Simple example of calculator language.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class Calculator {

    public enum CalculatorToken implements TokenType {

        //@formatter:off
        WHITESPACE("\\s+", true), // Skip it.
        NUMBER("[0-9]+"),
        OPERATOR("\\+|\\-|\\*|\\/|\\="),
        SEPARATOR(";"),
        BRACKET_LEFT("\\("),
        BRACKET_RIGHT("\\)"),
        VAR("VAR", Pattern.CASE_INSENSITIVE) {
            @Override
            public String preprocessing(final String rawValue) {
                return rawValue.toUpperCase();
            }
        },
        NAME("[a-zA-Z_]+[a-zA-Z0-9_]*") {
            @Override
            public String preprocessing(final String rawValue) {
                return rawValue.toLowerCase();
            }
        },
        UNRECOGNIZED(".*"); // Ignore all unrecognized fragments.
        //@formatter:on

        private final Pattern pattern;

        private final boolean skip;

        private CalculatorToken(final String pattern, final int flags, final boolean skip) {
            this.pattern = Pattern.compile(pattern, flags);
            this.skip = skip;
        }

        private CalculatorToken(final String pattern, final int flags) {
            this(pattern, flags, false);
        }

        private CalculatorToken(final String pattern, final boolean skip) {
            this.pattern = Pattern.compile(pattern);
            this.skip = skip;
        }

        private CalculatorToken(final String pattern) {
            this(pattern, false);
        }

        @Override
        public Pattern getTokenPattern() {
            return this.pattern;
        }

        @Override
        public boolean isSkip() {
            return this.skip;
        }

        @Override
        public String preprocessing(final String rawValue) {
            return rawValue;
        }
    }

    public static void main(final String[] args) throws TokenizationException {
        final String input = "var A = 1 + 22 * (300 + 450);";

        final List<Token<CalculatorToken>> tokens = Lexer.tokenization(CalculatorToken.class, input);
        for (final Token<CalculatorToken> token : tokens) {
            System.out.println(token);
        }
    }
}
