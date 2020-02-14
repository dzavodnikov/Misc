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
package org.zavodnikov.datastruct;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class OneToOneHashMap<K, V> implements Map<K, V> {

    private final Map<K, V> main = new HashMap<>();

    private final Map<V, K> back = new HashMap<>();

    @Override
    public int size() {
        return this.main.size();
    }

    @Override
    public boolean isEmpty() {
        return this.main.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return this.main.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return this.back.containsKey(value);
    }

    @Override
    public V get(final Object key) {
        return this.main.get(key);
    }

    public K getValueKey(final V value) {
        return this.back.get(value);
    }

    @Override
    public V remove(final Object key) {
        final V value = this.main.remove(key);
        this.back.remove(value);

        assert this.main.size() == this.back.size();
        return value;
    }

    public K removeValue(final V value) {
        final K key = this.back.remove(value);
        this.main.remove(key);

        assert this.main.size() == this.back.size();
        return key;
    }

    @Override
    public V put(final K key, final V value) {
        remove(key);
        removeValue(value);

        this.main.put(key, value);
        this.back.put(value, key);

        assert this.main.size() == this.back.size();
        return value;
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {
        for (final K key : m.keySet()) {
            put(key, m.get(key));
        }
    }

    @Override
    public void clear() {
        this.main.clear();
        this.back.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.main.keySet();
    }

    public Set<V> valueSet() {
        return this.back.keySet();
    }

    @Override
    public Collection<V> values() {
        return valueSet();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.main.entrySet();
    }
}
