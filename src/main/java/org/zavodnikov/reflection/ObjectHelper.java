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
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class ObjectHelper {

    private static List<Field> getFields(final Class<?> cl) {
        final List<Field> fields = ReflectionHelper.getFields(cl);

        // Clean fields.
        final Iterator<Field> fieldIterator = fields.iterator();
        while (fieldIterator.hasNext()) {
            final Field f = fieldIterator.next();

            if (Modifier.isStatic(f.getModifiers())) {
                fieldIterator.remove();
            }
        }

        return fields;
    }

    public static String toString(final Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }

        final StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s[", obj.getClass().getSimpleName()));

        final List<Field> fields = ObjectHelper.getFields(obj.getClass());

        for (int i = 0; i < fields.size(); ++i) {
            try {
                final Field f = fields.get(i);
                f.setAccessible(true);

                final Object fieldValue = f.get(obj);

                sb.append(String.format("%s=%s", f.getName(), fieldValue != null ? fieldValue.toString() : "null"));

                if (i < fields.size() - 1) {
                    sb.append(", ");
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sb.append("]");

        return sb.toString();
    }

    public static int hashCode(final Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }

        final int prime = 31;

        int result = 1;

        for (final Field f : ObjectHelper.getFields(obj.getClass())) {
            try {
                f.setAccessible(true);

                final Object fieldValue = f.get(obj);

                result = prime * result + (fieldValue != null ? fieldValue.hashCode() : 0);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static boolean equals(final Object obj1, final Object obj2) {
        if (obj1 == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        if (obj1 == obj2) {
            return true;
        }
        if (obj1.getClass() == obj2.getClass()) {
            for (final Field f : ObjectHelper.getFields(obj1.getClass())) {
                try {
                    f.setAccessible(true);

                    final Object value1 = f.get(obj1);
                    final Object value2 = f.get(obj2);

                    if (value1 != null && !value1.equals(value2)) {
                        return false;
                    }
                    if (value1 == null && value2 != null) {
                        return false;
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
