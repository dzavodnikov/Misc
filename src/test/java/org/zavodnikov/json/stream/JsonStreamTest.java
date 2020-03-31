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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public final class JsonStreamTest {

    //@formatter:off
    private final String JSON_OBJECT =
        "{" +
        "    'name': 'commons-math3'" +
        "}"
    ;
    //@formatter:on

    //@formatter:off
    private final String JSON_ARRAY =
        "[" +
        "    1," +
        "    2," +
        "    3" +
        "]"
    ;
    //@formatter:on

    //@formatter:off
    private final String JSON_OBJECT_USERS =
        "{" +
        "    'libraries': [" +
        "        {" +
        "            'name'     : 'commons-math3'," +
        "            'version'  : {" + 
        "                'major': 3," + 
        "                'minor': 6," + 
        "                'patch': 1" + 
        "            }" + 
        "        }," +
        "        {" +
        "            'name'     : 'javatuples'," +
        "            'version'  : {" + 
        "                'major': 1," + 
        "                'minor': 2" +
        "            }" + 
        "        }," +
        "        {" +
        "            'name'     : 'jopt-simple'," +
        "            'version'  : {" + 
        "                'major': 3," + 
        "                'minor': 0," + 
        "                'patch': 4" + 
        "            }" + 
        "        }" +
        "    ]" +
        "}"
    ;
    //@formatter:on

    @Test
    public void testSelectSimpleObject() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT);
        final List<JsonElement> result = json.select("name").list();
        assertNotNull(result);
        assertEquals(1, result.size());

        final JsonElement result1 = result.get(0);
        assertNotNull(result1);
        assertEquals("commons-math3", result1.getAsString());
    }

    @Test
    public void testSelectSimpleEmpty() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT);
        final List<JsonElement> result = json.select("").list();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectSimpleWrong1() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT);
        final List<JsonElement> result = json.select("name1").list();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectSimpleWrong2() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT);
        final List<JsonElement> result = json.select("name/a/b/c").list();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectArray() {
        final JsonStream json = new JsonStream(this.JSON_ARRAY);
        final List<JsonElement> result = json.select("[]").list();
        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals(1, result.get(0).getAsInt());
        assertEquals(2, result.get(1).getAsInt());
        assertEquals(3, result.get(2).getAsInt());
    }

    @Test
    public void testSelectArrayIndex() {
        final JsonStream json = new JsonStream(this.JSON_ARRAY);

        final List<JsonElement> result0 = json.select("[0]").list();
        assertNotNull(result0);
        assertEquals(1, result0.size());
        assertEquals(1, result0.get(0).getAsInt());

        final List<JsonElement> result1 = json.select("[1]").list();
        assertNotNull(result1);
        assertEquals(1, result1.size());
        assertEquals(2, result1.get(0).getAsInt());

        final List<JsonElement> result2 = json.select("[2]").list();
        assertNotNull(result2);
        assertEquals(1, result2.size());
        assertEquals(3, result2.get(0).getAsInt());
    }

    @Test
    public void testSelectArrayIndexBack() {
        final JsonStream json = new JsonStream(this.JSON_ARRAY);

        final List<JsonElement> result0 = json.select("[-1]").list();
        assertNotNull(result0);
        assertEquals(1, result0.size());
        assertEquals(3, result0.get(0).getAsInt());

        final List<JsonElement> result1 = json.select("[-2]").list();
        assertNotNull(result1);
        assertEquals(1, result1.size());
        assertEquals(2, result1.get(0).getAsInt());

        final List<JsonElement> result2 = json.select("[-3]").list();
        assertNotNull(result2);
        assertEquals(1, result2.size());
        assertEquals(1, result2.get(0).getAsInt());
    }

    @Test
    public void testSelectArrayEmpty() {
        final JsonStream json = new JsonStream(this.JSON_ARRAY);
        final List<JsonElement> result = json.select("").list();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectArrayWrong1() {
        final JsonStream json = new JsonStream(this.JSON_ARRAY);
        final List<JsonElement> result = json.select("name").list();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectArrayWrong2() {
        final JsonStream json = new JsonStream(this.JSON_ARRAY);
        final List<JsonElement> result = json.select("[123]").list();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectComplex() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        final List<JsonElement> result = json.select("libraries/[]/name").list();
        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals("commons-math3", result.get(0).getAsString());
        assertEquals("javatuples", result.get(1).getAsString());
        assertEquals("jopt-simple", result.get(2).getAsString());
    }

    @Test
    public void testSelectComplexIndex() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);

        final List<JsonElement> result0 = json.select("libraries/[0]/name").list();
        assertNotNull(result0);
        assertEquals(1, result0.size());
        assertEquals("commons-math3", result0.get(0).getAsString());

        final List<JsonElement> result1 = json.select("libraries/[1]/name").list();
        assertNotNull(result1);
        assertEquals(1, result1.size());
        assertEquals("javatuples", result1.get(0).getAsString());

        final List<JsonElement> result2 = json.select("libraries/[2]/name").list();
        assertNotNull(result2);
        assertEquals(1, result2.size());
        assertEquals("jopt-simple", result2.get(0).getAsString());
    }

    @Test
    public void testSelectComplexWrong1() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        final List<JsonElement> result = json.select("libraries/[]/name1").list();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectComplexWrong2() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        final List<JsonElement> result = json.select("libraries/[]/[]").list();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectFilter() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        //@formatter:off
        final List<JsonElement> result = json
            .select("libraries/[]")
            .filter(el -> el.getAsJsonObject().get("name").getAsString().equals("commons-math3"))
        .list();
        //@formatter:on
        assertNotNull(result);
        assertEquals(1, result.size());

        final JsonObject result1 = result.get(0).getAsJsonObject();
        assertEquals("commons-math3", result1.get("name").getAsString());
    }

    @Test
    public void testSelectFilterObject() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        //@formatter:off
        final List<JsonElement> result = json
            .select("libraries/[]")
            .filterJsonObject("name", "commons-math3")
        .list();
        //@formatter:on
        assertNotNull(result);
        assertEquals(1, result.size());

        final JsonObject result1 = result.get(0).getAsJsonObject();
        assertEquals("commons-math3", result1.get("name").getAsString());
    }

    @Test
    public void testSelectFilterObjectSelect() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        //@formatter:off
        final List<String> result = json
            .select("libraries/[]")
            .filterJsonObject("name", "commons-math3")
            .select("version/major")
        .listString();
        //@formatter:on
        assertNotNull(result);
        assertEquals(1, result.size());

        assertEquals("3", result.get(0));
    }

    @Test
    public void testSelectFilterObjectRegExp() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        //@formatter:off
        final List<String> result = json
            .select("libraries/[]")
            .filterJsonObject("name", Pattern.compile(".+\\-.+"))
            .select("name")
        .listString();
        //@formatter:on
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("commons-math3", result.get(0));
        assertEquals("jopt-simple", result.get(1));
    }

    @Test
    public void testSelectFilterObjectEmpty() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        //@formatter:off
        final List<JsonElement> result = json
            .select("libraries/[]")
            .filterJsonObject("name", "slf4j-simple")
        .list();
        //@formatter:on
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSelectFilterObjectWrong() {
        final JsonStream json = new JsonStream(this.JSON_OBJECT_USERS);
        //@formatter:off
        final List<JsonElement> result = json
            .select("libraries/[]")
            .filterJsonObject("name1", "commons-math3")
        .list();
        //@formatter:on
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
