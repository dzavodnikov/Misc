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

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.zavodnikov.Person;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class JsonReaderTest {

    private final String jsonString = "{\"firstName\": \"Dmitry\", \"lastName\": \"Zavodnikov\"}";

    @Test
    public void testReadUser() {
        final Person person = JsonReader.readObject(this.jsonString, Person.class);
        Assert.assertNotNull(person);

        Assert.assertEquals("Dmitry", person.getFirstName());
        Assert.assertEquals("Zavodnikov", person.getLastName());
        System.out.println(person.toString());
    }

    @Test
    public void testReadMap() {
        final Map<String, Object> user = JsonReader.readAnyObject(this.jsonString);
        Assert.assertNotNull(user);
        Assert.assertEquals(2, user.size());

        Assert.assertEquals("Dmitry", user.get("firstName"));
        Assert.assertEquals("Zavodnikov", user.get("lastName"));
    }
}
