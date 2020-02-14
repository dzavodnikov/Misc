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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.javatuples.Triplet;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import joptsimple.OptionSpecBuilder;

/**
 * You will not see support in JOpt Simple for option "groups".
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class JOptSimple {

    public static Triplet<File, Boolean, Boolean> cli(final String... args) {
        try {
            /*
             * CLI.
             */
            final OptionParser parser = new OptionParser();
            parser.recognizeAlternativeLongOptions(true);
            parser.posixlyCorrect(true);
            // Option 'Help'.
            parser.acceptsAll(Arrays.asList("h", "help")).forHelp();
            // Option 'Input'.
            final OptionSpec<File> input = parser.acceptsAll(Arrays.asList("i", "input"), "Input file.")
                    .withRequiredArg().ofType(File.class).required();
            // Option 'Read'.
            final OptionSpecBuilder read = parser.acceptsAll(Arrays.asList("r", "read"), "Read file.");
            // Option 'Write'.
            final OptionSpecBuilder write = parser.acceptsAll(Arrays.asList("w", "write"), "Write file.");
            /*
             * Get values.
             */
            final OptionSet options = parser.parse(args);
            if (options.has("help")) {
                parser.printHelpOn(System.out);
            }
            return new Triplet<>(options.valueOf(input), options.has(read), options.has(write));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(final String[] args) throws IOException {
        cli(args);
    }
}
