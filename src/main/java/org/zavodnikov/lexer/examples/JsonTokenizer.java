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
 * Simple example of <a href="http://www.json.org/">JSON</a> tokenizer.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class JsonTokenizer {

    public enum JSONToken implements TokenType {

        //@formatter:off
        WHITESPACE("\\s+", true), // Skip it.

        OBJECT_BRACKET_LEFT("\\{"),
        OBJECT_BRACKET_RIGHT("\\}"),

        ARRAY_BRACKET_LEFT("\\["),
        ARRAY_BRACKET_RIGHT("\\]"),

        PAIR_SEPARATOR(":"),
        MEMBER_SEPARATOR(","),

        TRUE("true"),
        FALSE("false"),
        NULL("null"),

        STRING("\"[^\"]*\"") {
            @Override
            public String preprocessing(final String rawValue) {
                return rawValue.substring(1, rawValue.length() - 1); // Remove quotes for string values.
            }
        },

        NUMBER("\\-?[0-9]+(\\.[0-9]+)?((e|E)(\\+|\\-)?[0-9]+)?"),

        UNRECOGNIZED(".*"); // Ignore all unrecognized fragments.
        //@formatter:on

        private final Pattern pattern;

        private final boolean skip;

        private JSONToken(final String pattern, final int flags, final boolean skip) {
            this.pattern = Pattern.compile(pattern, flags);
            this.skip = skip;
        }

        private JSONToken(final String pattern, final int flags) {
            this(pattern, flags, false);
        }

        private JSONToken(final String pattern, final boolean skip) {
            this.pattern = Pattern.compile(pattern);
            this.skip = skip;
        }

        private JSONToken(final String pattern) {
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
        final String input = "{\"key1\":\"value1\",\"key2\":1,\"key3\":1.0,\"key4\":0.5e+2,\"key5\":true}";

        final List<Token<JSONToken>> tokens = Lexer.tokenization(JSONToken.class, input);
        for (final Token<JSONToken> token : tokens) {
            System.out.println(token);
        }
    }
}
