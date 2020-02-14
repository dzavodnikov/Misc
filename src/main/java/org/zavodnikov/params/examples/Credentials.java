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
package org.zavodnikov.params.examples;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class Credentials {

    private final Username username;

    private final Password password;

    public Credentials(final Username username, final Password password) {
        this.username = username;
        this.password = password;
    }

    public Username username() {
        return this.username;
    }

    public Password password() {
        return this.password;
    }

    public static void main(final String[] args) {
        final Username username = new Username("user");
        System.out.println(username.val());

        final Password password = new Password("pass");
        System.out.println(password.val());
        //password.val(null);

        final Credentials cred = new Credentials(username, password);
        System.out.println(cred);
    }
}
