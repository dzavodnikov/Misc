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
package org.zavodnikov.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;

/**
 * Simple REST Service. See <a href="http://sparkjava.com/">Spark</a>.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class SimpleRest {

    private static Logger LOGGER = LoggerFactory.getLogger(SimpleRest.class);

    private static final int PORT = 9090;

    public static void main(final String[] args) {
        // Configure.
        Spark.port(SimpleRest.PORT);

        // Start listening.
        Spark.get("/hello", (req, res) -> {
            SimpleRest.LOGGER.info(String.format("Get request from %s", req.raw().getRemoteHost()));

            return "Hello, World!";
        });

        // Make a test request.
        System.out.println(RequestUtils.getRequest(String.format("http://localhost:%s/hello", SimpleRest.PORT)));

        // Stop listening.
        Spark.stop();
    }
}
