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

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.zavodnikov.lexer.Lexer;
import org.zavodnikov.lexer.Token;
import org.zavodnikov.lexer.TokenType;
import org.zavodnikov.lexer.TokenizationException;
import org.zavodnikov.lexer.examples.Calculator.CalculatorToken;

/**
 * Test for {@link Calculator}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class CalculatorTest {

    private static void equal(final Token<CalculatorToken> expectedToken, final Token<CalculatorToken> token) {
        Assert.assertEquals(expectedToken.getType(), token.getType());
        Assert.assertEquals(expectedToken.getValue(), token.getValue());
    }

    private static void equal(final List<Token<CalculatorToken>> expectedTokens,
            final List<Token<CalculatorToken>> tokens) {
        Assert.assertNotNull(expectedTokens);
        Assert.assertNotNull(tokens);
        Assert.assertEquals(expectedTokens.size(), tokens.size());
        for (int i = 0; i < expectedTokens.size(); ++i) {
            CalculatorTest.equal(expectedTokens.get(i), tokens.get(i));
        }
    }

    private static void equal(final List<Token<CalculatorToken>> tokens, final String input) {
        try {
            CalculatorTest.equal(tokens, Lexer.tokenization(CalculatorToken.class, input));
        } catch (final TokenizationException e) {
            Assert.fail(e.getMessage());
        }
    }

    private static void empty(final String input) {
        CalculatorTest.equal(new ArrayList<Token<CalculatorToken>>(), input);
    }

    private static <T extends Enum<T> & TokenType> void addTokenToList(final List<Token<T>> tokenList,
            final T tokenType, final String tokenData) {
        tokenList.add(new Token<>(tokenType, tokenData, 0, 1));
    }

    @Test
    public void testEmpty() {
        CalculatorTest.empty(null);
        CalculatorTest.empty("");
    }

    @Test
    public void testWhitespace() {
        //@formatter:off
        final String[] whitespaces = new String[]{
            " ",
            "\t",
            "\f",
            "\r",
            "\n"
        };
        //@formatter:on

        for (final String ws : whitespaces) {
            CalculatorTest.empty(ws);
        }

        for (final String ws : whitespaces) {
            final StringBuilder sb = new StringBuilder();
            sb.append(ws);
            sb.append(ws);
            final String ws2 = sb.toString();

            CalculatorTest.empty(ws2);
        }

        final StringBuilder sb = new StringBuilder();
        for (final String ws : whitespaces) {
            sb.append(ws);
        }
        final String allWs = sb.toString();

        CalculatorTest.empty(allWs);
    }

    @Test
    public void testNumber() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        for (int i = 0; i < 100; ++i) {
            final String intStr = Integer.toString(i);

            tokens.clear();
            CalculatorTest.addTokenToList(tokens, CalculatorToken.NUMBER, intStr);

            CalculatorTest.equal(tokens, intStr);
        }
    }

    @Test
    public void testOperator() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR, "+");
        CalculatorTest.equal(tokens, "+");

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR, "-");
        CalculatorTest.equal(tokens, "-");

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR, "*");
        CalculatorTest.equal(tokens, "*");

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR, "/");
        CalculatorTest.equal(tokens, "/");

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR, "=");
        CalculatorTest.equal(tokens, "=");
    }

    @Test
    public void testSeparator() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.SEPARATOR, ";");
        CalculatorTest.equal(tokens, ";");

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.SEPARATOR, ";");
        CalculatorTest.addTokenToList(tokens, CalculatorToken.SEPARATOR, ";");
        CalculatorTest.equal(tokens, ";;");
    }

    @Test
    public void testBracketLeft() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.BRACKET_LEFT, "(");
        CalculatorTest.equal(tokens, "(");

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.BRACKET_LEFT, "(");
        CalculatorTest.addTokenToList(tokens, CalculatorToken.BRACKET_LEFT, "(");
        CalculatorTest.equal(tokens, "((");
    }

    @Test
    public void testBracketRight() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.BRACKET_RIGHT, ")");
        CalculatorTest.equal(tokens, ")");

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.BRACKET_RIGHT, ")");
        CalculatorTest.addTokenToList(tokens, CalculatorToken.BRACKET_RIGHT, ")");
        CalculatorTest.equal(tokens, "))");
    }

    @Test
    public void testVar() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.VAR, "VAR");
        CalculatorTest.equal(tokens, "VAR");
        CalculatorTest.equal(tokens, "Var");
        CalculatorTest.equal(tokens, "var");
        CalculatorTest.equal(tokens, "vAr");
    }

    @Test
    public void testName() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NAME, "abc");
        CalculatorTest.equal(tokens, "ABC");
        CalculatorTest.equal(tokens, "Abc");
        CalculatorTest.equal(tokens, "abc");
        CalculatorTest.equal(tokens, "aBc");
    }

    @Test
    public void testUnrecognized() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        CalculatorTest.addTokenToList(tokens, CalculatorToken.UNRECOGNIZED, "!@#$%^&");
        CalculatorTest.equal(tokens, "!@#$%^&");
    }

    @Test
    public void testBracketIntergration1() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        //@formatter:off
        CalculatorTest.addTokenToList(tokens, CalculatorToken.VAR,         "VAR"   );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NAME,        "a"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR,    "="     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NUMBER,      "1"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR,    "+"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NUMBER,      "2"     );
        CalculatorTest.equal(tokens, "var A = 1 + 2");
        //@formatter:on
    }

    @Test
    public void testBracketIntergration2() {
        final List<Token<CalculatorToken>> tokens = new ArrayList<>();

        tokens.clear();
        //@formatter:off
        CalculatorTest.addTokenToList(tokens, CalculatorToken.VAR,             "VAR"   );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NAME,            "abc"   );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR,        "="     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NUMBER,          "2"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR,        "*"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.BRACKET_LEFT,    "("     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NUMBER,          "3"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR,        "+"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NUMBER,          "4"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.BRACKET_RIGHT,   ")"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR,        "-"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NUMBER,          "5"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.OPERATOR,        "/"     );
        CalculatorTest.addTokenToList(tokens, CalculatorToken.NUMBER,          "6"     );
        //@formatter:on
        CalculatorTest.equal(tokens, "Var Abc = 2 * (3 + 4) - 5/6");
    }
}
