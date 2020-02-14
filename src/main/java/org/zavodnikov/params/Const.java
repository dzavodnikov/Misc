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
package org.zavodnikov.params;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.zavodnikov.params.restrict.FunctionRestriction;
import org.zavodnikov.params.restrict.Restriction;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public abstract class Const<T> {

    private final String name;

    protected T val;

    protected final Set<Restriction<T>> restrictions = new HashSet<>();

    public Const(final String name, final T val) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "";
        } else {
            this.name = name;
        }

        verify(val);
        this.val = val;
    }

    public String name() {
        return this.name;
    }

    public T val() {
        return this.val;
    }

    protected void restrict(final Restriction<T> restriction) {

        this.restrictions.add(restriction);
    }

    protected void restrict(final Function<T, String> restriction) {
        this.restrictions.add(new FunctionRestriction<>(restriction));
    }

    protected void verify(final T val) {
        final List<String> errors = new ArrayList<>();
        this.restrictions.forEach(r -> errors.add(r.verify(val)));
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.format("Parameter \"%s\" is not correct: %s", name(), errors
                    .stream().collect(Collectors.joining(", "))));
        }
    }
}
