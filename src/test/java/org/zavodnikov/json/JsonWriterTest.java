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
package org.zavodnikov.json;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.zavodnikov.Person;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class JsonWriterTest {

    private final Person person = new Person("Dmitry", "Zavodnikov", 29);

    @Test
    public void testWriteUser() {
        final String personStr = JsonWriter.writeObject(this.person);
        Assert.assertNotNull(personStr);
        System.out.println(personStr);
    }

    @Test
    public void testWriteMap() {
        final Map<String, Object> map = new HashMap<>();
        map.put("firstName", this.person.getFirstName());
        map.put("lastName", this.person.getLastName());
        map.put("age", 29);

        final String mapStr = JsonWriter.writeAnyObject(map);
        Assert.assertNotNull(mapStr);
        System.out.println(mapStr);
    }

    @Test
    public void testWriteEquals() {
        final String personStr = JsonWriter.writeObject(this.person);

        final Map<String, Object> map = new HashMap<>();
        map.put("firstName", this.person.getFirstName());
        map.put("lastName", this.person.getLastName());
        map.put("age", 29);
        final String mapStr = JsonWriter.writeAnyObject(map);

        Assert.assertEquals(personStr, mapStr);
    }
}
