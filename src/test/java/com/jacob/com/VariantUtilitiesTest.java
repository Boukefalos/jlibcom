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

import java.util.Arrays;
import java.util.Date;

import com.jacob.test.BaseTestCase;

/**
 * This class should test some of the converter capabilities
 * 
 */
public class VariantUtilitiesTest extends BaseTestCase {

	/**
	 * verifies our unpacking stuff
	 */
	public void testObjectsToVariants() {
		Object testArray[] = new Object[] { Integer.valueOf(1),
				Integer.valueOf(2) };
		Variant resultArray[] = VariantUtilities.objectsToVariants(testArray);
		assertEquals(2, resultArray.length);

		Variant resultArray2[] = VariantUtilities
				.objectsToVariants(resultArray);
		assertEquals(2, resultArray2.length);
		assertSame(resultArray[0], resultArray2[0]);
		assertSame(resultArray[1], resultArray2[1]);
	}

	/**
	 * test nested arrays
	 */
	public void testObjectsToVariantNestedArray() {
		Object testArray[] = new Object[] { Integer.valueOf(1),
				Integer.valueOf(2) };
		Object testArrayOuter[] = new Object[] { testArray };
		Variant resultArray[] = VariantUtilities
				.objectsToVariants(testArrayOuter);
		// should be a SafeArray
		assertEquals(1, resultArray.length);
	}

	/**
	 * verify that dispatch can convert from object to variant and that the
	 * variant holds the right value
	 */
	public void testConverters() {
		Date testDate = new Date();
		Variant fromDate = VariantUtilities.objectToVariant(testDate);
		Date returnedDate = fromDate.getJavaDate();
		// System.out.println("test date is "+testDate);
		// System.out.println("VariantDate is "+fromDate.getJavaDate());
		assertTrue("Could not call obj2variant(Date) and get it to work",
				testDate.equals(returnedDate));
		Currency someMoney = new Currency(12349876L);
		Variant fromMoney = VariantUtilities.objectToVariant(someMoney);
		Currency someMoneyConverted = fromMoney.getCurrency();
		assertTrue("Could not call obj2variant(Long) and get it to work",
				someMoney.equals(someMoneyConverted));
		System.out.println("currency returned was: " + someMoneyConverted);

	}

	public void testPrimitiveByteArray() {
		byte[] arr = new byte[] { 1, 2, 3 };

		Variant arrVar = VariantUtilities.objectToVariant(arr);
		assertNotNull(arrVar);
		SafeArray sa = arrVar.toSafeArray();
		assertNotNull(sa);

		assertEquals(Variant.VariantByte, sa.getvt());

		assertEquals(0, sa.getLBound());
		assertEquals(2, sa.getUBound());

		byte[] bytes = sa.toByteArray();
		assertTrue(Arrays.equals(bytes, arr));
	}

	public void testPrimitiveIntArray() {
		int[] arr = new int[] { 1000, 2000, 3 };

		Variant arrVar = VariantUtilities.objectToVariant(arr);
		assertNotNull(arrVar);
		SafeArray sa = arrVar.toSafeArray();
		assertNotNull(sa);

		assertEquals(Variant.VariantInt, sa.getvt());

		assertEquals(0, sa.getLBound());
		assertEquals(2, sa.getUBound());

		int[] ints = sa.toIntArray();
		assertTrue(Arrays.equals(ints, arr));
	}

	public void testPrimitiveDoubleArray() {
		double[] arr = new double[] { 1000, 2000, 3 };

		Variant arrVar = VariantUtilities.objectToVariant(arr);
		assertNotNull(arrVar);
		SafeArray sa = arrVar.toSafeArray();
		assertNotNull(sa);

		assertEquals(Variant.VariantDouble, sa.getvt());

		assertEquals(0, sa.getLBound());
		assertEquals(2, sa.getUBound());

		double[] doubles = sa.toDoubleArray();
		assertTrue(Arrays.equals(doubles, arr));
	}

	public void testPrimitiveLongArray() {
		long[] arr = new long[] { 0xcafebabecafebabeL, 42, 0xbabecafebabeL };

		Variant arrVar = VariantUtilities.objectToVariant(arr);
		assertNotNull(arrVar);
		SafeArray sa = arrVar.toSafeArray();
		assertNotNull(sa);

		assertEquals(Variant.VariantLongInt, sa.getvt());

		assertEquals(0, sa.getLBound());
		assertEquals(2, sa.getUBound());

		long[] longs = sa.toLongArray();
		assertTrue(Arrays.equals(longs, arr));
	}

}
