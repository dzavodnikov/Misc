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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class RequestUtils {

    private static String inputStreamToString(final InputStream is) {
        final StringBuilder sb = new StringBuilder();
        try {
            final InputStreamReader isr = new InputStreamReader(is);
            final BufferedReader reader = new BufferedReader(isr);

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static String getRequest(final String url) {
        try {
            final URL siteURL = new URL(url);
            final HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();

            // Common configuration.
            connection.setConnectTimeout(10_000); // 10 seconds.
            connection.setInstanceFollowRedirects(true);

            // Request headers.
            connection.setRequestProperty("MyHeader", "MyHeaderValue");

            // Connect.
            connection.connect();

            // Response processing.
            final int responseCode = connection.getResponseCode();
            switch (responseCode) {
                case HttpURLConnection.HTTP_OK:
                    return RequestUtils.inputStreamToString(connection.getInputStream());
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    return RequestUtils.getRequest(connection.getHeaderField("Location"));
                default:
                    throw new RuntimeException(String.format("Wrong response code %s!", responseCode));
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String putRequest(final String url, final String data) {
        try {
            final URL siteURL = new URL(url);
            final HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();

            // Common configuration.
            connection.setConnectTimeout(10_000); // 10 seconds.
            connection.setInstanceFollowRedirects(true);

            // Request headers.
            connection.setRequestProperty("MyHeader", "MyHeaderValue");

            // Connect.
            connection.setDoOutput(true);

            // Put data.
            connection.setRequestMethod("PUT");
            final OutputStreamWriter osr = new OutputStreamWriter(connection.getOutputStream());
            osr.write(data);
            osr.close();

            // Response processing.
            final int responseCode = connection.getResponseCode();
            switch (responseCode) {
                case HttpURLConnection.HTTP_OK:
                    return RequestUtils.inputStreamToString(connection.getInputStream());
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    return RequestUtils.putRequest(connection.getHeaderField("Location"), data);
                default:
                    throw new RuntimeException(String.format("Wrong response code %s!", responseCode));
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
