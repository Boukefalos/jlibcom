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
package com.jacob.test.errors;

import com.jacob.test.BaseTestCase;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComException;

/**
 * This test verifies patch SF 1794811 . It shows how unicode filenames throw
 * exceptions in 1.13M4 and earlier.
 * 
 * @author justme84
 * 
 */
public class UnicodeErrorTest extends BaseTestCase {

    /**
     * verifies that messages can now have unicode in them like when the file
     * names have unicode characters
     */
    public void testUnicodeCharactersInErrorMessage() {
        ActiveXComponent application = new ActiveXComponent("Word.Application");
        ActiveXComponent documents = application
                .getPropertyAsComponent("Documents");
        String fileName = "abc\u0411\u0412\u0413\u0414def";
        try {
            documents.invoke("Open", fileName);
            fail("Should have thrown an exception");
        } catch (ComException e) {
            assertTrue("Error message should contain file name with unicode "
                    + "characters in it. " + e.getMessage(), e.getMessage()
                    .indexOf(fileName) > 0);
        }
    }
}