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
package org.zavodnikov.xml;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class ValidatorXSD {

    public static String validateXmlByXsd(final InputStream xmlContent, final InputStream xsdContent) {
        if (xmlContent == null) {
            throw new IllegalArgumentException("Content of XML file for theting can not be null!");
        }
        if (xsdContent == null) {
            throw new IllegalArgumentException("Content of XSD file for theting can not be null!");
        }

        try {
            final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            final Schema schema = factory.newSchema(new StreamSource(xsdContent));
            final Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlContent));
            return null;
        } catch (IOException | SAXException e) {
            return e.getMessage();
        }
    }

    public static String validateXmlByXsd(final String xmlContent, final String xsdContent) {
        if (xmlContent == null || xmlContent.isEmpty()) {
            throw new IllegalArgumentException("Content of XML file for checking can not be null or empty!");
        }
        if (xsdContent == null || xsdContent.isEmpty()) {
            throw new IllegalArgumentException("Content of XSD file for checking can not be null or empty!");
        }

        return validateXmlByXsd(new ByteArrayInputStream(xmlContent.getBytes()),
                new ByteArrayInputStream(xsdContent.getBytes()));
    }

    public static void main(final String[] args) throws IOException {
        final OptionParser parser = new OptionParser();
        final OptionSpec<String> xmlOpt = parser.accepts("xml", "XML file for validation").withRequiredArg()
                .ofType(String.class);
        final OptionSpec<String> xsdOpt = parser.accepts("xsd", "XSD file with definition").withRequiredArg()
                .ofType(String.class);

        final OptionSet options = parser.parse(args);
        if (options.has(xmlOpt) && options.has(xsdOpt)) {
            try (FileInputStream xmlFile = new FileInputStream(options.valueOf(xmlOpt))) {
                try (FileInputStream xsdFile = new FileInputStream(options.valueOf(xsdOpt))) {
                    final String validationResult = validateXmlByXsd(xmlFile, xsdFile);

                    if (validationResult == null) {
                        System.out.println("VALID");
                    } else {
                        System.err.println(validationResult);
                    }
                }
            }
        } else {
            parser.printHelpOn(System.out);
        }
    }
}
