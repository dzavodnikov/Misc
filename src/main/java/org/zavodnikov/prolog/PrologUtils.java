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
package org.zavodnikov.prolog;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Var;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class PrologUtils {

    public static void solve(final Prolog engine, final String query) {
        try {
            solve(engine, engine.solve(query));
        } catch (final MalformedGoalException e) {
            throw new RuntimeException(e);
        }
    }

    public static void solve(final Prolog engine, final Term query) {
        solve(engine, engine.solve(query));
    }

    private static void solve(final Prolog engine, SolveInfo info) {
        try {
            // Print the first solution.
            printSolutionValue(info);

            // Print other solutions.
            while (engine.hasOpenAlternatives()) {
                info = engine.solveNext();
                printSolutionValue(info);
            }
        } catch (final NoMoreSolutionException e) {
            // Will not happens.
        }
    }

    public static void printSolutionValue(final SolveInfo info) {
        try {
            for (final Var var : info.getBindingVars()) {
                final Term term1 = info.getVarValue(var.getName());
                System.out.println(String.format("%s: %s", var.getName(), term1.toString()));
                System.out.println("================================");
            }
        } catch (final NoSolutionException e) {
            // Will not happens.
        }
    }
}
