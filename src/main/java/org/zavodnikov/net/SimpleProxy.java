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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class SimpleProxy extends Thread {

    private final String serverUrl;

    private final int    serverPort;

    private final Socket clientSocket;

    private SimpleProxy(final String serverUrl, final int serverPort, final Socket clientSocket) {
        this.serverUrl = serverUrl;
        this.serverPort = serverPort;

        this.clientSocket = clientSocket;
    }

    private void copy(final InputStream is, final OutputStream os) throws IOException {
        final byte[] request = new byte[1024];
        int buffer;
        while ((buffer = is.read(request)) != -1) {
            os.write(request, 0, buffer);
            os.flush();
        }
    }

    private void request(final Socket serverSocket) {
        try {
            try (final InputStream is = this.clientSocket.getInputStream()) {
                try (final OutputStream os = serverSocket.getOutputStream()) {
                    copy(is, os);
                }
            }
        } catch (IOException e) {
            // Ignore connection interrupt exception.
        }
    }

    private void responce(final Socket serverSocket) {
        try {
            try (final InputStream is = serverSocket.getInputStream()) {
                try (final OutputStream os = this.clientSocket.getOutputStream()) {
                    copy(is, os);
                }
            }
        } catch (IOException e) {
            // Ignore connection interrupt exception.
        }
    }

    @Override
    public void run() {
        try (final Socket serverSocket = new Socket(this.serverUrl, this.serverPort)) {
            final Thread requestThread = new Thread() {

                @Override
                public void run() {
                    request(serverSocket);
                }
            };
            requestThread.start();

            responce(serverSocket);

            requestThread.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void start(final String serverUrl, final int serverPort, final int localPort) throws IOException {
        try (final ServerSocket server = new ServerSocket(localPort)) {
            while (true) {
                final Socket clentSocket = server.accept();

                final SimpleProxy proxy = new SimpleProxy(serverUrl, serverPort, clentSocket);
                proxy.start();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Required 3 parameters <SERVER_URL> <SERVER_PORT> <LOCAL_PORT>");
            return;
        }
        final String serverUrl = args[0];
        final int serverPort = Integer.parseInt(args[1]);
        final int localPort = Integer.parseInt(args[2]);

        try {
            System.out.println(String.format("Proxy %s:%d to %s:%s", InetAddress.getLocalHost().getHostName(),
                    localPort, serverUrl, serverPort));

            start(serverUrl, serverPort, localPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
