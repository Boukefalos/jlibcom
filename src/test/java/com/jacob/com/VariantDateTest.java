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
package com.jacob.com;

import java.util.Date;

import com.jacob.test.BaseTestCase;

/**
 * test cases that should exercise the new date conversion code
 * <p>
 * May need to run with some command line options (including from inside
 * Eclipse). Look in the docs area at the Jacob usage document for command line
 * options.
 */
public class VariantDateTest extends BaseTestCase {

	/**
	 * verify the conversion of Variants into java dates
	 */
	public void testVariantDate() {
		Date now = new Date();
		Variant holder = new Variant();
		holder.putDate(now);
		Date retrievedNow = holder.getJavaDate();
		if (!now.equals(retrievedNow)) {
			fail("Variant Date Test failed " + now + " != " + retrievedNow);
		} else {
			System.out.println("Variant Date Test passed");
		}

	}

	/**
	 * verify that the Variant constructor accepts Java dates and converts them
	 * correctly
	 */
	public void testVariantDateToJavaObject() {
		Date now = new Date();
		Variant holder = new Variant(now);
		for (int i = 0; i < 30000; i++) {
			Variant dateVariant = new Variant(now);
			Date retrievedNow = holder.getJavaDate();
			retrievedNow = dateVariant.getJavaDate();
			if (!now.equals(retrievedNow)) {
				fail("Variant Date Test (1) failed " + now + " != "
						+ retrievedNow);
			} else {
				// System.out.println("Variant Date Test (1) passed");
			}
			// verify auto typecasting works
			retrievedNow = (Date) dateVariant.toJavaObject();
			if (!now.equals(retrievedNow)) {
				fail("Variant Date Test (2) failed " + now + " != "
						+ retrievedNow);
			} else {
				// System.out.println("Variant Date Test (2) passed
				// "+retrievedNow);
			}

			Variant intVariant = new Variant(4);
			Object variantReturn = intVariant.toJavaObject();
			// degenerate test to make sure date isn't always returned
			if (variantReturn instanceof Date) {
				System.out.println("int variant returned date");
			}
		}
		System.out.print("Test finished.  All tests passed.");

	}

}
