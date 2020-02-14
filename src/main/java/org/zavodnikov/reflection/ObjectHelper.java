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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Method that makes writing Object methods simpler.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class ObjectHelper {

    private static final String NULL = "<null>";

    private static final String NL = System.lineSeparator();

    private static final String SHIFT = "    ";

    private static String fieldToString(final Field field, final Object value) {
        if (value == null) {
            return NULL;
        }

        final Class<? extends Field> fieldClass = field.getClass();

        // Boolean
        if (fieldClass.equals(boolean.class)) {
            return Boolean.toString(boolean.class.cast(value));
        }
        if (fieldClass.equals(Boolean.class)) {
            return Boolean.class.cast(value).toString();
        }

        // Byte
        if (fieldClass.equals(byte.class)) {
            return Byte.toString(byte.class.cast(value));
        }
        if (fieldClass.equals(Byte.class)) {
            return Byte.class.cast(value).toString();
        }

        // Character
        if (fieldClass.equals(char.class)) {
            return Character.toString(char.class.cast(value));
        }
        if (fieldClass.equals(Character.class)) {
            return Character.class.cast(value).toString();
        }

        // Short
        if (fieldClass.equals(short.class)) {
            return Short.toString(short.class.cast(value));
        }
        if (fieldClass.equals(Short.class)) {
            return Short.class.cast(value).toString();
        }

        // Integer
        if (fieldClass.equals(int.class)) {
            return Integer.toString(int.class.cast(value));
        }
        if (fieldClass.equals(Integer.class)) {
            return Integer.class.cast(value).toString();
        }

        // Float
        if (fieldClass.equals(float.class)) {
            return Float.toString(float.class.cast(value));
        }
        if (fieldClass.equals(Float.class)) {
            return Float.class.cast(value).toString();
        }

        // Long
        if (fieldClass.equals(long.class)) {
            return Long.toString(long.class.cast(value));
        }
        if (fieldClass.equals(Long.class)) {
            return Long.class.cast(value).toString();
        }

        // Double
        if (fieldClass.equals(double.class)) {
            return Double.toString(double.class.cast(value));
        }
        if (fieldClass.equals(Double.class)) {
            return Double.class.cast(value).toString();
        }

        // String
        if (fieldClass.equals(String.class)) {
            return String.class.cast(value);
        }

        return value.toString();
    }

    private static <R> R processField(final Field field, final Object object,
            final BiFunction<Field, Object, R> processor) {
        R result = null;

        final boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        try {
            final Object value = field.get(object);
            result = processor.apply(field, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (!accessible) {
            field.setAccessible(accessible);
        }

        return result;
    }

    private static Object processField(final Field field, final Object object) {
        return processField(field, object, (f, obj) -> obj);
    }

    public static String toString(final Object object) {
        if (object == null) {
            return NULL;
        }

        // Header.
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s[%s]{", object.getClass().getSimpleName(), Integer.toString(object.hashCode())));
        sb.append(NL);

        // Collect all fields.
        final List<Field> fields = new ArrayList<>();
        Class<?> cl = object.getClass();
        while (cl != Object.class) {
            fields.addAll(Arrays.asList(cl.getDeclaredFields()));
            cl = cl.getSuperclass();
        }

        // For each filed.
        for (int i = 0; i < fields.size(); ++i) {
            final Field field = fields.get(i);

            final String result = processField(field, object, ObjectHelper::fieldToString);

            sb.append(SHIFT);
            sb.append(String.format("%s=%s", field.getName(), result));

            // Add separator.
            if (i < fields.size() - 1) {
                sb.append(",");
            }
            sb.append(NL);
        }

        sb.append("}");

        return sb.toString();
    }

    public static boolean equals(final Object object1, final Object object2) {
        if (object1 == null) {
            return object2 == null;
        }
        if (object2 == null) {
            return object1 == null;
        }

        if (object1 == object2) {
            return true;
        }

        if (!object1.getClass().equals(object2.getClass())) {
            return false;
        }

        for (final Field field : object1.getClass().getDeclaredFields()) {
            final Object value1 = processField(field, object1);
            final Object value2 = processField(field, object2);

            if (value1 == null && value2 != null) {
                return false;
            }
            if (!value1.equals(value2)) {
                return false;
            }
        }
        return true;
    }

    public static int hashCode(final Object object) {
        if (object == null) {
            return 0;
        }

        int result = 1;
        for (final Field field : object.getClass().getDeclaredFields()) {
            final Object value = processField(field, object);

            result = 31 * result + (value == null ? 0 : value.hashCode());
        }
        return result;
    }
}
