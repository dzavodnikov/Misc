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
package org.zavodnikov.swing;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class SimpleUI extends JFrame {

    public SimpleUI() {

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);

        final JButton btnOpen = new JButton("Open Directory");
        btnOpen.addActionListener(arg0 -> {
        });
        panel.add(btnOpen);

        final JTextPane txtpnNamePattern = new JTextPane();
        txtpnNamePattern.setText("Name  pattern");
        panel.add(txtpnNamePattern);

        this.textField = new JTextField();
        panel.add(this.textField);
        this.textField.setColumns(10);

        final JButton btnNewButton = new JButton("Rename");
        btnNewButton.addActionListener(e -> {
        });
        panel.add(btnNewButton);

        final JTree tree = new JTree();
        getContentPane().add(tree, BorderLayout.WEST);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(250, 100);
    }

    private static final long serialVersionUID = -8439351020137387634L;

    private final JTextField textField;

    public static void main(final String[] args) {
        final JFrame myWindow = new SimpleUI();
        myWindow.setVisible(true);
    }
}
