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

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class GenerateXML {

    private static Document generate() {
        try {
            final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            final Element rootElement = document.createElement("company");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

            document.appendChild(rootElement);

            final Element staff = document.createElement("user");

            final Attr attr = document.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);

            rootElement.appendChild(staff);

            final Element firstnameElement = document.createElement("firstname");
            firstnameElement.appendChild(document.createTextNode("Dmitry"));
            staff.appendChild(firstnameElement);

            final Element lastnameElement = document.createElement("lastname");
            lastnameElement.appendChild(document.createTextNode("Zavodnikov"));
            staff.appendChild(lastnameElement);

            final Element nicknameElement = document.createElement("username");
            nicknameElement.appendChild(document.createTextNode("dzavodnikov"));
            staff.appendChild(nicknameElement);

            return document;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertToString(final Document document) {
        try {
            final Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            final StringWriter writer = new StringWriter();
            final StreamResult result = new StreamResult(writer);

            transformer.transform(new DOMSource(document), result);

            return writer.getBuffer().toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(final String[] args) {
        final Document document = generate();
        final String xmlString = convertToString(document);
        System.out.println(xmlString);
    }
}
