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
package org.zavodnikov.cli;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Example of usage <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Robot.html">Robot</a>. More examples
 * available <a href="http://jexp.ru/index.php/Java_Tutorial/Development/Robot">here</a>.
 *
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class RobotExample {

    private static final int KEY_PRESS_DELAY = 500;

    private final Robot robot;

    public RobotExample() {
        try {
            this.robot = new Robot();
        } catch (final AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void delay(final int ms) {
        this.robot.delay(ms);
    }

    public void pressKey(final int keyCode) {
        delay(KEY_PRESS_DELAY);

        this.robot.keyPress(keyCode);
    }

    public void pressKey(final int... keyCodes) {
        System.out.println("Start printing");
        for (final int kc : keyCodes) {
            pressKey(kc);
        }
        System.out.println("End printing");
    }

    public static void main(final String... args) {
        final RobotExample robotExample = new RobotExample();

        System.out.println("Run text editor");
        robotExample.delay(5_000); // 5 seconds.

        //@formatter:off
        robotExample.pressKey(
                KeyEvent.VK_H,
                KeyEvent.VK_I,
                KeyEvent.VK_SPACE,
                KeyEvent.VK_D,
                KeyEvent.VK_I,
                KeyEvent.VK_M,
                KeyEvent.VK_A
            );
        //@formatter:on
    }
}
