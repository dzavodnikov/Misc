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

/**
 * Token class.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 * @version 5.0.0
 */
public class Token<T extends Enum<T> & TokenType> {

    /**
     * Type of token.
     */
    private final T type;

    /**
     * Value of token.
     */
    private final String value;

    /**
     * Start token position.
     */
    private final int startPosition;

    /**
     * End token position.
     */
    private final int endPosition;

    public Token(final T type, final String value, final int startPosition, final int endPosition) {
        // Type.
        if (type == null) {
            throw new IllegalArgumentException("Parameter \"type\" can not be null");
        }
        this.type = type;

        // Value.
        if (value == null) {
            throw new IllegalArgumentException("Parameter \"value\" can not be null");
        }
        this.value = value;

        // Start position.
        if (startPosition < -1) {
            throw new IllegalArgumentException(String.format("Parameter \"startPosition\" (%s) can not be less than 0",
                    startPosition));
        }
        this.startPosition = startPosition;

        // End position.
        if (endPosition < -1) {
            throw new IllegalArgumentException(String.format("Parameter \"endPosition\" (%s) can not be less than 0",
                    endPosition));
        }
        if (endPosition <= startPosition) {
            throw new IllegalArgumentException(String.format(
                    "Parameter \"endPosition\" (%s) can not be less or equals than parameter \"startPosition\" (%s)!",
                    endPosition, startPosition));
        }
        this.endPosition = endPosition;
    }

    public T getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public int getStartPosition() {
        return this.startPosition;
    }

    public int getEndPosition() {
        return this.endPosition;
    }

    public boolean is(final T type) {
        return getType().equals(type);
    }

    @Override
    public String toString() {
        return String.format("[%s \"%s\": (%s, %s)]", this.type.name(), this.value, this.startPosition,
                this.endPosition);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof Token<?>) {
            final Token<?> token = (Token<?>) obj;
            //@formatter:off
            return  getType().equals(token.getType())
                    && getValue().equals(token.getValue())
                    && getStartPosition() == token.getStartPosition()
                    && getEndPosition() == token.getEndPosition();
            //@formatter:on
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + this.type.hashCode();
        result = 31 * result + this.value.hashCode();
        result = 31 * result + this.startPosition;
        result = 31 * result + this.endPosition;

        return result;
    }
}
