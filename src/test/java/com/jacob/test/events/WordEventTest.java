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
package com.jacob.test.events;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComException;
import com.jacob.com.DispatchEvents;
import com.jacob.com.InvocationProxy;
import com.jacob.com.Variant;
import com.jacob.test.BaseTestCase;

/**
 * This test was lifted from a forum posting and shows how you can't listen to
 * Excel events (added post 1.9.1 Eclipse Settings.) That test was modified make
 * this a MSWord event listener to demonstrate that the InvocationProxy code
 * works with MS Word Events This also uses the 1.10 InvocationProxy to receive
 * the events.
 * <p>
 * May need to run with some command line options (including from inside
 * Eclipse). Look in the docs area at the Jacob usage document for command line
 * options.
 */
public class WordEventTest extends BaseTestCase {

	/**
	 * load up word, register for events and make stuff happen
	 * 
	 * @param args
	 */
	public void testCaptureWordEvents() {
		String pid = "Word.Application";
		String typeLibLocation = null;

		// Grab The Component.
		ActiveXComponent axc = new ActiveXComponent(pid);
		try {
			// Add a listener (doesn't matter what it is).
			DispatchEvents de;
			if (typeLibLocation == null) {
				de = new DispatchEvents(axc, new WordEventTest());
			} else {
				de = new DispatchEvents(axc, new WordEventTest(), pid,
						typeLibLocation);
			}
			if (de == null) {
				fail("No exception thrown but no dispatch returned for Word events");
			} else {
				// Yea!
				System.out.println("Successfully attached to " + pid);

			}
			// this is different from the ExcelEventTest because it uses
			// the jacob active X api instead of the Dispatch api
			System.out.println("version=" + axc.getPropertyAsString("Version"));
			axc.setProperty("Visible", true);
			ActiveXComponent documents = axc
					.getPropertyAsComponent("Documents");
			if (documents == null) {
				fail("unable to get documents");
			}
			axc.invoke("Quit", new Variant[] {});

		} catch (ComException cfe) {
			cfe.printStackTrace();
			fail("Failed to attach to " + pid + ": " + cfe.getMessage());

		}
		System.out
				.println("Someone needs to add some MSWord commands to this to "
						+ "make some on screen stuff happens so the tester "
						+ "thinks we tested something");
	}

	/**
	 * a class that receives messages from word
	 */
	public class WordEvents extends InvocationProxy {
		/**
		 * Constructor so we can create an instance that implements invoke()
		 */
		public WordEvents() {
		}

		/**
		 * override the invoke() method to log all the events without writing a
		 * bunch of code
		 */
		public Variant invoke(String methodName, Variant targetParameter[]) {
			System.out.println("Received event from Windows program"
					+ methodName);
			return null;
		}

	}
}