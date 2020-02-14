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
package org.zavodnikov.parser;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Stack Machine class.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 * @version 1.0.0
 */
public class StackMachine {

    public static <T> T execute(final List<Element<T>> input) {
        final Stack<T> stack = new Stack<>();

        for (final Element<T> el : input) {
            if (el instanceof Operand<?>) {
                final Operand<T> operand = (Operand<T>) el;
                stack.push(operand.execute());
                continue;
            }

            if (el instanceof Operation<?>) {
                final Operation<T> operation = (Operation<T>) el;
                try {
                    final List<T> operands = new ArrayList<>();
                    for (int i = 0; i < operation.length(); ++i) {
                        operands.add(stack.pop());
                    }
                    stack.push(operation.execute(operands));
                } catch (final EmptyStackException e) {
                    throw new IllegalArgumentException("Wrong input expression");
                }
                continue;
            }

            throw new IllegalArgumentException(String.format("Unknown element type \"%s\"", el.getClass()
                    .getSimpleName()));
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Wrong input expression");
        }

        return stack.pop();
    }
}
