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
package org.zavodnikov.filesystem;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Example of directory watcher.
 *
 * @author Igor Mikhailov
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class FileWather {

    public static void main(final String[] args) throws Exception {
        final Path dir = new File(System.getProperty("user.home")).toPath();
        final WatchService watcher = FileSystems.getDefault().newWatchService();
        final WatchKey key = dir.register(watcher, ENTRY_MODIFY, ENTRY_DELETE, ENTRY_CREATE);

        System.out.println(String.format("Start watching to \"%s\"", dir.toString()));
        final Thread thread = new Thread() {

            @Override
            public void run() {
                while (true) {
                    // Process events.
                    for (WatchEvent<?> event : key.pollEvents()) {
                        final Object context = event.context();
                        if (context instanceof Path) {
                            final Path path = (Path) context;

                            final Kind<?> kind = event.kind();
                            if (ENTRY_MODIFY == kind) {
                                System.out.println(String.format("Edited \"%s\"", path.toString()));
                            }
                            if (ENTRY_DELETE == kind) {
                                System.out.println(String.format("Removed \"%s\"", path.toString()));
                            }
                            if (ENTRY_CREATE == kind) {
                                System.out.println(String.format("Created \"%s\"", path.toString()));
                            }
                        }
                    }

                    // Reset the key.
                    if (!key.reset()) {
                        // Object no longer registered.
                    }
                }
            }
        };
        thread.start();

        thread.join();
    }
}
