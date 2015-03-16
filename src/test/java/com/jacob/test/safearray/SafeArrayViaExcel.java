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

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.SafeArray;
import com.jacob.com.Variant;
import com.jacob.test.BaseTestCase;

/**
 * This does simple tests with SafeArray using Excel as a source
 * <p>
 * May need to run with some command line options (including from inside
 * Eclipse). Look in the docs area at the Jacob usage document for command line
 * options.
 * <p>
 * This relies on BaseTestCase to provide the root path to the file under test
 */
public class SafeArrayViaExcel extends BaseTestCase {

	/**
	 * verify safe arrays work with standard applications, Excel in this case
	 */
	public void testSafeArrayViaExcel() {

		ActiveXComponent xl = new ActiveXComponent("Excel.Application");
		try {
			Dispatch cell;
			SafeArray sAProdText;
			Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
			System.out.println("have workbooks");
			Dispatch workbook = Dispatch.call(
					workbooks,
					"Open",
					getWindowsFilePathToPackageResource(
							"SafeArrayViaExcel.xls", this.getClass()))
					.toDispatch();
			System.out.println("Opened File - SafeArrayViaExcel.xls\n");
			Dispatch sheet = Dispatch.get(workbook, "ActiveSheet").toDispatch();
			cell = Dispatch.invoke(sheet, "Range", Dispatch.Get,
					new Object[] { "A1:D1000" }, new int[1]).toDispatch();
			System.out.println("have cell:" + cell);
			sAProdText = Dispatch.get(cell, "Value").toSafeArray();
			System.out.println("sa: dim=" + sAProdText.getNumDim());
			System.out.println("sa: start row=" + sAProdText.getLBound(1));
			System.out.println("sa: start col=" + sAProdText.getLBound(2));
			System.out.println("sa: end row=" + sAProdText.getUBound(1));
			System.out.println("sa: end col=" + sAProdText.getUBound(2));
			int i;
			int lineNumber = 1;
			int n = 0;
			for (lineNumber = 1; lineNumber < 1000; lineNumber++) {
				for (i = 1; i < 4; i++) {
					System.out.println((n++) + " " + lineNumber + " " + i + " "
							+ sAProdText.getString(lineNumber, i));
					/*
					 * if (sAProdText.getString(lineNumber,i).compareTo("aaaa") !=
					 * 0 ) { System.out.println("Invalid String in line " +
					 * lineNumber + " Cell " + i + " Value = " +
					 * sAProdText.getString(lineNumber,i)); stringFound = false; } }
					 * if (stringFound) { System.out.println("Valid Strings in
					 * line " + lineNumber); lineNumber++; }
					 */
				}
			}

			Dispatch.call(workbook, "Close");
			System.out.println("Closed File\n");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Caught Exception " + e);
		} finally {
			xl.invoke("Quit", new Variant[] {});
		}
	}
}
