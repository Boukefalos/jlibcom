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

import com.jacob.com.ComThread;
import com.jacob.com.SafeArray;
import com.jacob.com.Variant;
import com.jacob.test.BaseTestCase;

/**
 * <p>
 * May need to run with some command line options (including from inside
 * Eclipse). Look in the docs area at the Jacob usage document for command line
 * options.
 * <p>
 * SF 1085370 In my understatnding, an instance of SafeArray java class has a
 * value of a pointer to VARIANT structure that contains a pointer to a
 * SAFEARRAY strucuture.
 * 
 * On the other hand, we can create a Variant object from the SafeArray object
 * like this: SafeArray sa = ...; Variant val = new Variant(sa); the val object
 * has a pointer to another VARIANT structure that contains a pointer to the
 * same SAFEARRAY structure.
 * 
 * In this case, the val object has a pointer to another VARIANT that contains a
 * pointer to the same SAFEARRAY like this:
 * 
 * +-----------+ |SafeArray | +------------+ | m_pV--->VARIANT(a) |
 * +-----------+ | VT_ARRAY| +---------+ | parray---->SAFEARRAY| +------------+
 * +^--------+ | +-----------+ | |Variant | +------------+ | |
 * m_pVariant--->VARIANT(b) | | +-----------+ | VT_ARRAY| | | parray-----+
 * +------------+
 * 
 * When previous objects are rereased by ComThread.Release(), first the
 * VARIANT(a) is released by VariantClear() function, and second the VARIANT(b)
 * is released by VariantClear() function too. But the SAFEARRAY was already
 * released by the VARIANT(a).
 * 
 * So, in my enviroment (WinXP + J2SDK 1.4.1) the following java program is
 * sometimes crash with EXCEPTION_ACCESS_VIOLATION.
 * 
 * 
 * To solve this problem, it is nessesary to copy the SAFEARRAY like this:
 * 
 * +-----------+ |Variant | +------------+ | m_pVariant--->VARIANT(a) |
 * +-----------+ | VT_ARRAY| +---------+ | parray---->SAFEARRAY| +------------+
 * +|--------+ | +-----------+ | copySA() |SafeArray | +------------+ | |
 * m_pV--->VARIANT(b) | V +-----------+ | VT_ARRAY| +---------+ |
 * parray---->SAFEARRAY| +------------+ +---------+
 * 
 * <p>
 * May need to run with some command line options (including from inside
 * Eclipse). Look in the docs area at the Jacob usage document for command line
 * options.
 */

public class SafeArrayReleaseTest extends BaseTestCase {
	final static int MAX = 300;

	/**
	 * verifies the release works on SafeArray
	 */
	public void testSaveArrayRelease() {
		int count;
		System.out.println("Starting test for max = " + MAX);
		for (count = 1; count < MAX; count++) {
			int i = 0;
			try {
				ComThread.InitMTA();
				for (i = 0; i < count; i++) {
					SafeArray a1 = new SafeArray(Variant.VariantVariant, 2);
					a1.setVariant(0, new Variant("foo"));
					a1.setVariant(1, new Variant("bar"));
					Variant v = new Variant(a1);
					SafeArray a2 = v.toSafeArray(true);
					if (a2 == null) {
						System.out.println("got null back from toSafeArray()");
					}
				}
				ComThread.Release();
				System.gc();
				// System.out.print(".");
			} catch (Exception e) {
				fail("Test fails with i = " + i + " (max = " + MAX + ")");
			}
		}
		System.gc();
		System.out.println("\nTest ends with count = " + count + " (max = "
				+ MAX + ")");
	}
}