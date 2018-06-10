/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2012-2018 Dmitry Zavodnikov
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
package org.zavodnikov.reflection;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ReflectionHelper}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class ReflectionHelperTest {

    @Test
    public void testCreateInsatnce() {
        final SimpleClass1 sc1 = ReflectionHelper.createInsatnce(SimpleClass1.class);
        Assert.assertNotNull(sc1);

        final SimpleClass1.InternalClass1 sc1_ic1 = ReflectionHelper.createInsatnce(SimpleClass1.InternalClass1.class);
        Assert.assertNotNull(sc1_ic1);

        final SimpleClass1.InternalClass2 sc1_ic2 = ReflectionHelper.createInsatnce(SimpleClass1.InternalClass2.class);
        Assert.assertNotNull(sc1_ic2);
    }

    @Test
    public void testCreateInsatnceException() {
        try {
            ReflectionHelper.createInsatnce(null);
            Assert.fail("No IllegalArgumentException!");
        } catch (final IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            ReflectionHelper.createInsatnce(SimpleClass2.class);
            Assert.fail("No RuntimeException!");
        } catch (final RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            ReflectionHelper.createInsatnce(SimpleClass2.InternalClass1.class);
            Assert.fail("No RuntimeException!");
        } catch (final RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            ReflectionHelper.createInsatnce(SimpleClass2.InternalClass2.class);
            Assert.fail("No RuntimeException!");
        } catch (final RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetFields() {
        final List<Field> sc1 = ReflectionHelper.getFields(SimpleClass1.class);
        Assert.assertNotNull(sc1);
        Assert.assertEquals(4, sc1.size());
        Assert.assertEquals("boolean0", sc1.get(0).getName());
        Assert.assertEquals("boolean1", sc1.get(1).getName());
        Assert.assertEquals("int0", sc1.get(2).getName());
        Assert.assertEquals("int1", sc1.get(3).getName());

        final List<Field> sc1_ic1 = ReflectionHelper.getFields(SimpleClass1.InternalClass1.class);
        Assert.assertNotNull(sc1_ic1);
        Assert.assertEquals(3, sc1_ic1.size());
        Assert.assertEquals("float0", sc1_ic1.get(0).getName());
        Assert.assertEquals("float1", sc1_ic1.get(1).getName());
        Assert.assertEquals("this$0", sc1_ic1.get(2).getName());

        final List<Field> sc1_ic2 = ReflectionHelper.getFields(SimpleClass1.InternalClass2.class);
        Assert.assertNotNull(sc1_ic2);
        Assert.assertEquals(2, sc1_ic2.size());
        Assert.assertEquals("float0", sc1_ic2.get(0).getName());
        Assert.assertEquals("float1", sc1_ic2.get(1).getName());
    }

    @Test
    public void testGetFieldsException() {
        try {
            ReflectionHelper.getFields(null);
            Assert.fail("No IllegalArgumentException!");
        } catch (final IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetFieldsInternalClasses() {
        final List<Field> classFields1 = ReflectionHelper.getFields(SimpleClass1.InternalClass1.class);
        Assert.assertNotNull(classFields1);
        Assert.assertEquals(3, classFields1.size());
        Assert.assertEquals("float0", classFields1.get(0).getName());
        Assert.assertEquals("float1", classFields1.get(1).getName());
        Assert.assertEquals("this$0", classFields1.get(2).getName());

        final List<Field> classFields2 = ReflectionHelper.getFields(SimpleClass2.InternalClass1.class);
        Assert.assertNotNull(classFields2);
        Assert.assertEquals(3, classFields2.size());
        Assert.assertEquals("float0", classFields2.get(0).getName());
        Assert.assertEquals("float1", classFields2.get(1).getName());
        Assert.assertEquals("this$0", classFields1.get(2).getName());
    }
}
