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
 * 	Copyright (c) 1999-2004 Sourceforge JACOB Project.
 * 	All rights reserved. Originator: Dan Adler (http://danadler.com).
 * 	Get more information about JACOB at http://sourceforge.net/projects/jacob-project
 *
 * 	This library is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU Lesser General Public
 * 	License as published by the Free Software Foundation; either
 * 	version 2.1 of the License, or (at your option) any later version.
 *
 * 	This library is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * 	Lesser General Public License for more details.
 *
 * 	You should have received a copy of the GNU Lesser General Public
 * 	License along with this library; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.jacob.test.safearray;

import com.jacob.com.SafeArray;
import com.jacob.test.BaseTestCase;

/**
 * Test case provided #41 Fix for SafeArray(String) constructor
 * 
 * In the current release of Jacob, SafeArray.java contains a constructor which
 * takes a string as a single argument. The documentation claims that this
 * method converts a string to a VT_UI1 array. Using this method as written
 * always causes a ComFailException, because it attempts to create a SafeArray
 * from Java chars, which are 16-bit unsigned integers (which would be VT_UI2).
 */
public class SafeArrayStringConstructorTest extends BaseTestCase {
	public void testStringConstructor() {
		// The line below will throw ComFailException using jacob 1.17-M2
		// without the patch.
		SafeArray safeArrayFromString = new SafeArray("This is a string.");
		String convertBack = safeArrayFromString.asString();
		assertEquals("This is a string.", convertBack);
	}

}
