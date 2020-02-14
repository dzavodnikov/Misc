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
package org.zavodnikov.cli;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;
import net.sf.expectit.Result;
import net.sf.expectit.matcher.Matchers;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class Pipe {

    private static final String EXEC_PATH = "/home/dima/Programming/Python/Pipe/main";

    private static final String EXEC_PROMPT = "Enter string> ";

    private static void execute(final String... inputs) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(EXEC_PATH);

            //@formatter:off
            try(Expect expect = new ExpectBuilder()
                    .withInputs(process.getInputStream())
                    .withOutput(process.getOutputStream())
                    .withTimeout(1, TimeUnit.SECONDS)
                    .withExceptionOnFailure()
                    .build()) {
                //@formatter:on
                for (final String input : inputs) {
                    final Result res = expect.expect(Matchers.contains(EXEC_PROMPT));
                    System.out.print(res.getInput());

                    expect.sendLine(input);
                    System.out.println(input);
                }
                final Result res = expect.expect(Matchers.contains(EXEC_PROMPT));
                System.out.print(res.getInput());
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    public static void main(final String[] args) throws IOException {
        execute("one", "two", "three", "four", "five");
    }
}
