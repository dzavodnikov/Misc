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
package org.zavodnikov.datastruct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link OneToOneHashMap}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class OneToOneHashMapTest {

    @Test
    public void testPutDifferentKeysAndValues() {
        final OneToOneHashMap<String, Integer> map = new OneToOneHashMap<>();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        assertEquals(3, map.size());
        assertFalse(map.isEmpty());

        assertTrue(map.containsKey("1"));
        assertTrue(map.containsValue(Integer.valueOf(1)));
        assertEquals(Integer.valueOf(1), map.get("1"));
        assertEquals("1", map.getValueKey(Integer.valueOf(1)));

        assertTrue(map.containsKey("2"));
        assertTrue(map.containsValue(Integer.valueOf(2)));
        assertEquals(Integer.valueOf(2), map.get("2"));
        assertEquals("2", map.getValueKey(Integer.valueOf(2)));

        assertTrue(map.containsKey("3"));
        assertTrue(map.containsValue(Integer.valueOf(3)));
        assertEquals(Integer.valueOf(3), map.get("3"));
        assertEquals("3", map.getValueKey(Integer.valueOf(3)));
    }

    @Test
    public void testPutSameKey() {
        final OneToOneHashMap<String, Integer> map = new OneToOneHashMap<>();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        map.put("1", 1);
        map.put("1", 2);
        map.put("1", 3);

        assertEquals(1, map.size());
        assertFalse(map.isEmpty());

        assertTrue(map.containsKey("1"));
        assertTrue(map.containsValue(Integer.valueOf(3)));
        assertEquals(Integer.valueOf(3), map.get("1"));
        assertEquals("1", map.getValueKey(Integer.valueOf(3)));
    }

    @Test
    public void testPutSameValue() {
        final OneToOneHashMap<String, Integer> map = new OneToOneHashMap<>();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        map.put("1", 1);
        map.put("2", 1);
        map.put("3", 1);

        assertEquals(1, map.size());
        assertFalse(map.isEmpty());

        assertTrue(map.containsKey("3"));
        assertTrue(map.containsValue(Integer.valueOf(1)));
        assertEquals(Integer.valueOf(1), map.get("3"));
        assertEquals("3", map.getValueKey(Integer.valueOf(1)));
    }

    @Test
    public void testRemove() {
        final OneToOneHashMap<String, Integer> map = new OneToOneHashMap<>();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        assertEquals(3, map.size());

        map.remove("1");
        assertEquals(2, map.size());

        map.removeValue(2);
        assertEquals(1, map.size());

        assertTrue(map.containsKey("3"));
        assertTrue(map.containsValue(Integer.valueOf(3)));
        assertEquals(Integer.valueOf(3), map.get("3"));
        assertEquals("3", map.getValueKey(Integer.valueOf(3)));
    }
}
