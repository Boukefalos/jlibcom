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
import com.jacob.com.ComException;
import com.jacob.com.Dispatch;
import com.jacob.com.SafeArray;
import com.jacob.com.Variant;
import com.jacob.test.BaseTestCase;

/**
 * Test class to verify dispatch with SafeArray
 */
public class SafeArrayDispatchTest extends BaseTestCase {
	public void testDispatchWithSafeArray() {
		try {
			String scriptCommand = "1+(2*4)-3";
			String lang = "VBScript";
			ActiveXComponent sControl = new ActiveXComponent("ScriptControl");
			Dispatch.put(sControl, "Language", lang);

			Variant result = Dispatch.call(sControl, "Eval", scriptCommand);
			assertTrue(result.toString().equals("6"));

			// wrap the script control in a variant
			Variant v = new Variant(sControl);

			// create a safe array of type dispatch
			SafeArray sa = new SafeArray(Variant.VariantDispatch, 1);

			// put the variant in the array
			sa.setVariant(0, v);

			// take it back out
			Variant v2 = sa.getVariant(0);
			Dispatch d = v2.toDispatch();

			// make sure you can call eval on it
			result = Dispatch.call(d, "Eval", scriptCommand);
			assertTrue(result.toString().equals("6"));
		} catch (ComException e) {
			e.printStackTrace();
			fail("script failure " + e);
		}
	}
}
