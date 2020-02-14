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
package org.zavodnikov.net;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@link UrlUtils}.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class UrlUtilsTest {

    @Test
    public void testEncodeDecodeNullOREmptyValue() {
        assertEquals("", UrlUtils.encode(null));
        assertEquals("", UrlUtils.decode(null));
        assertEquals("", UrlUtils.encode(""));
        assertEquals("", UrlUtils.decode(""));
    }

    private void testEncodeDecode(final String decoded, final String encoded) {
        assertEquals(decoded, UrlUtils.decode(encoded));
        assertEquals(encoded, UrlUtils.encode(decoded));

        assertEquals(decoded, UrlUtils.decode(UrlUtils.encode(decoded)));
        assertEquals(encoded, UrlUtils.encode(UrlUtils.decode(encoded)));
    }

    @Test
    public void testEncodeDecode1() {
        testEncodeDecode("Date = today", "Date+%3D+today");
    }

    @Test
    public void testEncodeDecode2() {
        testEncodeDecode("\"A\" B ± \"", "%22A%22+B+%C2%B1+%22");
    }

    @Test
    public void testEncodeDecode3() {
        final String decoded = "name=*voor* or name=dzavo* or name=hren* or name=mzib* or name=mrom or name=qzh*";
        final String encoded = "name%3D*voor*+or+name%3Ddzavo*+or+name%3Dhren*+or+name%3Dmzib*+or+name%3Dmrom+or+name%3Dqzh*";
        testEncodeDecode(decoded, encoded);
    }

    @Test
    public void testDecodePercent20() {
        final String decoded = "name=*voor* or name=dzavo* or name=hren* or name=mzib* or name=mrom or name=qzh*";
        final String encoded = "name%3D*voor*%20or%20name%3Ddzavo*%20or%20name%3Dhren*%20or%20name%3Dmzib*%20or%20name%3Dmrom%20or%20name%3Dqzh*";
        assertEquals(decoded, UrlUtils.decode(encoded));
    }

    @Test
    public void testDecodeNBSP1() {
        final String decoded = "name=*voor* or name=dzavo* or name=hren* or name=mzib* or name=mrom or name=qzh*";
        final String encoded = "name%3D*voor*%C2%A0or%C2%A0name%3Ddzavo*%C2%A0or%C2%A0name%3Dhren*%C2%A0or%C2%A0name%3Dmzib*%C2%A0or%C2%A0name%3Dmrom%C2%A0or%C2%A0name%3Dqzh*";
        assertEquals(decoded, UrlUtils.decode(encoded));
    }

    @Test
    public void testDecodeNBSP2() {
        final String decoded1 = "name=*voor*\u00A0or\u00A0name=dzavo*\u00A0or\u00A0name=hren*\u00A0or\u00A0name=mzib*\u00A0or\u00A0name=mrom\u00A0or\u00A0name=qzh*";
        final String decoded2 = "name=*voor* or name=dzavo* or name=hren* or name=mzib* or name=mrom or name=qzh*";
        assertEquals(decoded2, UrlUtils.decode(UrlUtils.encode(decoded1)));
    }
}
