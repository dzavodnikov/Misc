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
package org.zavodnikov.reflection;

import org.junit.Assert;
import org.junit.Test;
import org.zavodnikov.Person;

/**
 * Tests for {@link ObjectHelper}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class ObjectHelperTest {

    @Test
    public void testToString() {
        final Person person1 = new Person("Dmitry", "Zavodnikov", 29);
        System.out.println(ObjectHelper.toString(person1));

        final Person person2 = new Person();
        System.out.println(ObjectHelper.toString(person2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToStringException() {
        ObjectHelper.toString(null);
    }

    @Test
    public void testHashCode() {
        final Person person1 = new Person("Dmitry", "Zavodnikov", 29);
        System.out.println(ObjectHelper.hashCode(person1));

        final Person person2 = new Person();
        System.out.println(ObjectHelper.hashCode(person2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHashCodeException() {
        ObjectHelper.hashCode(null);
    }

    @Test
    public void testEquals() {
        final Person person1 = new Person("Dmitry", "Zavodnikov", 29);
        Assert.assertTrue(ObjectHelper.equals(person1, person1));
        Assert.assertFalse(ObjectHelper.equals(null, person1));
        Assert.assertFalse(ObjectHelper.equals(person1, null));
        Assert.assertTrue(ObjectHelper.equals(null, null));

        final String person2 = "Some String";
        Assert.assertFalse(ObjectHelper.equals(person1, person2));

        final Person person3 = new Person("Dmitry", "Zavodnikov", 29);
        Assert.assertTrue(ObjectHelper.equals(person1, person3));
        Assert.assertTrue(ObjectHelper.equals(person3, person1));

        final Person person4 = new Person("Dmitry", "Zavodnikov", 92);
        Assert.assertFalse(ObjectHelper.equals(person1, person4));
        Assert.assertFalse(ObjectHelper.equals(person4, person1));

        final Person person5 = new Person("Dmitry", null, 29);
        Assert.assertFalse(ObjectHelper.equals(person1, person5));
        Assert.assertFalse(ObjectHelper.equals(person5, person1));
    }
}
