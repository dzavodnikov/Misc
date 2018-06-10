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
package org.zavodnikov.spa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Spark;

/**
 * Records REST Service: generate and return records. <p> Use <a href="http://sparkjava.com/">Spark</a> and <a
 * href="http://knockoutjs.com/">Knockout</a>. </p>
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class RecordsRest {

    private static Logger LOGGER = LoggerFactory.getLogger(RecordsRest.class);

    private static final int PORT = 9090;

    private static final String HTML_DIR = "src/main/resources/html/";

    private static String readPage(final String pageName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(HTML_DIR + pageName + ".html")));
    }

    public static void main(final String[] args) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        final List<Record> records = new ArrayList<>();

        // Configure.
        Spark.port(RecordsRest.PORT);
        Spark.staticFileLocation("/"); // Set to "/src/main/resources".

        // Return HTML page.
        Spark.get("/page", (req, res) -> {
            RecordsRest.LOGGER.info(String.format("Get request from %s", req.raw().getRemoteHost()));

            res.type("text/html");
            return readPage("records");
        });

        // Return data.
        Spark.get("/records", (req, res) -> {
            RecordsRest.LOGGER.info(String.format("Get request from %s", req.raw().getRemoteHost()));

            records.add(Record.getRandomRecord());

            res.type("application/json");
            return mapper.writeValueAsString(records);
        });
    }

    /**
     * Simple record of some action.
     * 
     * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
     */
    public static class Record {

        private final Date date;

        private final String name;

        private final String event;

        public Record(final Date date, final String name, final String event) {
            this.date = date;
            this.name = name;
            this.event = event;
        }

        private static String getRandomName(final Random random) {
            //@formatter:off
            final String usernames[] = {
                "Gabriela", "Cheryl", "Seven", "Eugeny", "Alexander", "Dmitry", "Marcel", "Vivienne"
            };
            //@formatter:on
            return usernames[random.nextInt(usernames.length)];
        }

        private static String getRandomEvent(final Random random) {
          //@formatter:off
            final String events[] = {
                "LOGIN", "LOGOUT", "TEST", "REPORT", "DUMP", "UPLOAD"
            };
            //@formatter:on
            return events[random.nextInt(events.length)];
        }

        private static Date getCurrentTime() {
            return Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.ENGLISH).getTime();
        }

        public static Record getRandomRecord() {
            final Random random = new Random();
            return new Record(getCurrentTime(), getRandomName(random), getRandomEvent(random));
        }

        public Date getDate() {
            return this.date;
        }

        public String getName() {
            return this.name;
        }

        public String getEvent() {
            return this.event;
        }
    }
}
