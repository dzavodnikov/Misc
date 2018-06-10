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
package org.zavodnikov.swagger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.RefProperty;

/**
 * Example of usage Swagger APi generator. See <a href="http://swagger.io/specification/">Specification</a> for more
 * details.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class Generator {

    public static void main(final String[] args) throws JsonProcessingException {
        final Swagger swagger = new Swagger();
        swagger.setHost("localhost");

        final Info info = new Info();
        info.setTitle("Swagger Sample App");
        info.setDescription("This is a sample server Petstore server.");
        info.setTermsOfService("http://swagger.io/terms/");

        final Contact contact = new Contact();
        contact.setName("API Support");
        contact.setUrl("http://www.swagger.io/support");
        contact.setEmail("support@swagger.io");
        info.setContact(contact);

        final License license = new License();
        license.setName("Apache 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        info.setLicense(license);

        info.setVersion("1.0.1");

        swagger.setInfo(info);

        final Path petsPath = new Path();

        final Operation petsPathOperation = new Operation();
        petsPathOperation.setDescription("Returns all pets from the system that the user has access to");
        petsPathOperation.produces("application/json");

        final Response petsPathGetResponce = new Response();
        petsPathGetResponce.setDescription("A list of pets.");

        final ArrayProperty arrayProperty = new ArrayProperty();

        arrayProperty.setItems(new RefProperty("#/definitions/pet"));
        petsPathGetResponce.schema(arrayProperty);

        petsPathOperation.addResponse("200", petsPathGetResponce);

        petsPath.setGet(petsPathOperation);

        swagger.path("/pets", petsPath);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_EMPTY);

        final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(swagger);
        System.out.println(json);
    }
}
