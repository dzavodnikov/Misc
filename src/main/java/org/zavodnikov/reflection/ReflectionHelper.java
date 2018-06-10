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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Some useful <a href="https://docs.oracle.com/javase/tutorial/reflect/">Reflection</a> methods.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class ReflectionHelper {

    /**
     * Create class instance.
     *
     * @param cl
     *            Source class.
     * @return Object instance.
     */
    public static <T> T createInsatnce(final Class<T> cl) {
        if (cl == null) {
            throw new IllegalArgumentException("Class can not be null!");
        }

        try {
            final Class<?> outerClass = cl.getEnclosingClass();

            if (outerClass != null && !Modifier.isStatic(cl.getModifiers())) {
                final Constructor<T> ctor = cl.getDeclaredConstructor(outerClass);
                ctor.setAccessible(true);

                final Object obj = ReflectionHelper.createInsatnce(outerClass);

                return ctor.newInstance(obj);
            } else {
                final Constructor<T> ctor = cl.getDeclaredConstructor();
                ctor.setAccessible(true);

                return ctor.newInstance();
            }
        } catch (NoSuchMethodException | SecurityException e) {
            throw new IllegalArgumentException(
                    String.format("Class %s have no constructor without arguments!", cl.getCanonicalName()));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Collect fields from class and all super-classes.
     *
     * @param cl
     *            Source class.
     * @return {@link List} of class {@link Field} objects.
     */
    public static List<Field> getFields(final Class<?> cl) {
        if (cl == null) {
            throw new IllegalArgumentException("Class can not be null!");
        }

        final List<Field> result = new ArrayList<>();

        // Collect fields.
        Class<?> currentTypeClass = cl;
        while (currentTypeClass != Object.class) {
            for (final Field field : currentTypeClass.getDeclaredFields()) {
                result.add(field);
            }

            currentTypeClass = currentTypeClass.getSuperclass();
        }

        // Sort fields by names.
        Collections.sort(result, new Comparator<Field>() {

            @Override
            public int compare(final Field field1, final Field field2) {
                return field1.getName().compareTo(field2.getName());
            }
        });

        return result;
    }
}
