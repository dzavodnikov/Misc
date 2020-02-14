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

import org.javatuples.Triplet;
import org.junit.Assert;
import org.junit.Test;

import joptsimple.OptionException;

/**
 * Tests for {@link JOptSimple}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class JOptSimpleTest {

    @Test
    public void testHelp() {
        try {
            JOptSimple.cli();
        } catch (final OptionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testHelp2() {
        JOptSimple.cli("-h");
        JOptSimple.cli("--help");
    }

    private void testRead(final File file, final Triplet<File, Boolean, Boolean> params) {
        Assert.assertEquals(file.getAbsolutePath(), params.getValue0().getAbsolutePath());
        Assert.assertTrue(params.getValue1());
        Assert.assertFalse(params.getValue2());
    }

    @Test
    public void testRead() {
        final File file = new File("/home/user/file1.txt");

        testRead(file, JOptSimple.cli("-i", file.getAbsolutePath(), "-r"));
        testRead(file, JOptSimple.cli("--input", file.getAbsolutePath(), "--read"));
    }

    private void testWrite(final File file, final Triplet<File, Boolean, Boolean> params) {
        Assert.assertEquals(file.getAbsolutePath(), params.getValue0().getAbsolutePath());
        Assert.assertFalse(params.getValue1());
        Assert.assertTrue(params.getValue2());
    }

    @Test
    public void testWrite() throws IOException {
        final File file = new File("/home/user/file1.txt");

        testWrite(file, JOptSimple.cli("-i", file.getAbsolutePath(), "-w"));
        testWrite(file, JOptSimple.cli("--input", file.getAbsolutePath(), "--write"));
    }

    @Test(expected = OptionException.class)
    public void testEmpty() {
        testRead(null, JOptSimple.cli("-r"));
    }
}
