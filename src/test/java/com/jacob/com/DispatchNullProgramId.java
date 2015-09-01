/**
 * This file is part of jlibcom.
 *
 * Copyright (C) 2014 Rik Veenboer <rik.veenboer@gmail.com>
 *
 * jlibcom is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jlibcom is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jlibcom. If not, see <http://www.gnu.org/licenses/>.
 *
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *     Copyright (c) 1999-2004 Sourceforge JACOB Project.
 *     All rights reserved. Originator: Dan Adler (http://danadler.com).
 *     Get more information about JACOB at http://sourceforge.net/projects/jacob-project
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.jacob.com;

import com.jacob.test.BaseTestCase;

/**
 * This test verifies that the Dispatch object protects itself when the
 * constructor is called with a null program id. Prior to this protection, the
 * VM might crash.m
 * <p>
 * May need to run with some command line options (including from inside
 * Eclipse). Look in the docs area at the Jacob usage document for command line
 * options.
 */
public class DispatchNullProgramId extends BaseTestCase {

    /**
     * Verify that dispatch constructors are protected from null program ids.
     */
    public void testNullProgramId() {
        try {
            String nullParam = null;
            new Dispatch(nullParam);
            fail("the dispatch failed to protect itself from null program ids");
        } catch (IllegalArgumentException iae) {
            System.out
                    .println("the dispatch protected itself from null program ids");
        }
        try {
            String nullParam = "";
            new Dispatch(nullParam);
            fail("the dispatch failed to protect itself from empty string program ids");
        } catch (IllegalArgumentException iae) {
            System.out
                    .println("the dispatch protected itself from empty string program ids");
        }
    }
}
