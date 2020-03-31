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
package org.zavodnikov.json.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class JsonStream {

    private final List<JsonElement> content;

    private static Gson createGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    public JsonStream() {
        this.content = new ArrayList<>();
    }

    public JsonStream(final Stream<JsonElement> content) {
        if (content == null) {
            this.content = new ArrayList<>();
        } else {
            this.content = content.filter(el -> el != null).collect(Collectors.toList());
        }
    }

    public JsonStream(final List<JsonElement> content) {
        this(content != null ? content.stream() : null);
    }

    public JsonStream(final JsonElement... elements) {
        this(elements != null ? Arrays.stream(elements) : null);
    }

    public JsonStream(final String jsonString) {
        this(createGson().fromJson(jsonString, JsonElement.class));
    }

    public Stream<JsonElement> stream() {
        return this.content.stream();
    }

    public int count() {
        return this.content.size();
    }

    public boolean isEmpty() {
        return count() == 0;
    }

    public boolean notEmpty() {
        return count() > 0;
    }

    public List<JsonElement> list() {
        return this.content;
    }

    public List<JsonObject> listJsonObject() {
        return this.content.stream().map(el -> el.getAsJsonObject()).collect(Collectors.toList());
    }

    public List<String> listString() {
        return this.content.stream().map(el -> el.getAsString()).collect(Collectors.toList());
    }

    public List<Integer> listInteger() {
        return this.content.stream().map(el -> el.getAsInt()).collect(Collectors.toList());
    }

    public List<Double> listDouble() {
        return this.content.stream().map(el -> el.getAsDouble()).collect(Collectors.toList());
    }

    public List<Boolean> listBoolean() {
        return this.content.stream().map(el -> el.getAsBoolean()).collect(Collectors.toList());
    }

    public void forEach(final Consumer<JsonElement> action) {
        this.content.forEach(action);
    }

    public JsonElement first() {
        return !this.content.isEmpty() ? this.content.get(0) : null;
    }

    public JsonObject firstJsonObject() {
        return !this.content.isEmpty() ? this.content.get(0).getAsJsonObject() : null;
    }

    public String firstString() {
        return !this.content.isEmpty() ? this.content.get(0).getAsString() : null;
    }

    public Integer firstInteger() {
        return !this.content.isEmpty() ? this.content.get(0).getAsInt() : null;
    }

    public Double firstDouble() {
        return !this.content.isEmpty() ? this.content.get(0).getAsDouble() : null;
    }

    public Boolean firstBoolean() {
        return !this.content.isEmpty() ? this.content.get(0).getAsBoolean() : null;
    }

    private void selectArray(final List<JsonElement> result, final JsonElement current, final Integer arrayIndex) {
        final JsonArray arr = JsonArray.class.cast(current);
        final int size = arr.size();

        if (arrayIndex == null) {
            for (int i = 0; i < size; ++i) {
                result.add(arr.get(i));
            }
        } else {
            if (arrayIndex >= 0) {
                if (arrayIndex < size) {
                    result.add(arr.get(arrayIndex));
                } else {
                    // Ignore.
                }
            } else {
                result.add(arr.get(size + arrayIndex));
            }
        }
    }

    private void selectObject(final List<JsonElement> result, final JsonElement current, final String fragment) {
        final JsonObject obj = JsonObject.class.cast(current);

        final JsonElement newEl = obj.get(fragment);
        if (newEl != null) {
            result.add(newEl);
        }
    }

    private List<JsonElement> selectFragment(final List<JsonElement> current, final String fragment,
            final boolean isArray, final Integer arrayIndex) {
        final List<JsonElement> result = new ArrayList<>();

        for (final JsonElement c : current) {
            if (isArray) {
                if (c.isJsonArray()) {
                    selectArray(result, c, arrayIndex);
                }
            } else {
                if (c.isJsonObject()) {
                    selectObject(result, c, fragment);
                }
            }
        }

        return result;
    }

    private List<JsonElement> selectElement(final JsonElement root, final String fragments) {
        List<JsonElement> current = Arrays.asList(root);
        for (final String f : fragments.split("/")) {
            final boolean isArray = f.startsWith("[") && f.endsWith("]");

            Integer arrayIndex = null;
            if (isArray && f.length() > 2) {
                arrayIndex = Integer.parseInt(f.substring(1, f.length() - 1));
            }

            current = selectFragment(current, f, isArray, arrayIndex);
        }
        return current;
    }

    public JsonStream select(final String fragments) {
        return new JsonStream(this.content.stream().flatMap(el -> selectElement(el, fragments).stream()).collect(
                Collectors.toList()));
    }

    public JsonStream filter(final Predicate<JsonElement> predicate) {
        return new JsonStream(stream().filter(predicate));
    }

    private JsonStream filterJsonObject(final String keyQuery, final Predicate<JsonElement> p) {
        //@formatter:off
        return filter(el -> new JsonStream(el)
            .select(keyQuery)
            .filter(subEl -> subEl.isJsonPrimitive())
            .filter(subEl -> p.test(subEl))
        .count() > 0);
        //@formatter:on
    }

    /**
     * Example: <code><pre>
     * final JsonStream libraries = new JsonStream("[
     *      {
     *          "name"      : "commons-math3",
     *          "version"   : "3.6.1"
     *      },
     *      {
     *          "name"      : "javatuples",
     *          "version"   : "1.2"
     *      }
     * ]");
     * final JsonStream mathLib = libraries.filterJsonObject("[]/name", "commons-math3");
     * assertEquals("[
     *      {
     *          "name"      : "commons-math3",
     *          "position"  : "3.6.1"
     *      }
     * ]", mathLib);
     * </code>
     * </pre>
     *
     * @param keyQuery
     *            Query that select a {@link String} value that will be compared with provided value.
     * @param value
     *            Expected value.
     * @return JsonStream of elements that will be filtered by containing value into the proper query.
     */
    public JsonStream filterJsonObject(final String keyQuery, final String value) {
        return filterJsonObject(keyQuery, el -> Objects.equals(el.getAsString(), value));
    }

    public JsonStream filterJsonObject(final String keyQuery, final Pattern pattern) {
        return filterJsonObject(keyQuery, el -> pattern.matcher(el.getAsString()).matches());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.content.stream().map(el -> createGson().toJson(el)).collect(Collectors.joining(", ")));
        sb.append("]");
        return sb.toString();
    }
}
