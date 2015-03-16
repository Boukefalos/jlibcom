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
package com.jacob.test.windowsmedia;

/**
 * partial test program from the sourceforge bug report 1453161
 * that says you get a  random "can't map name to dispid" when
 * getting the URL from the player
 * <p>
 * this doesn't actually play for some reason.  It always says the length is 0.
 * <p>
 * May need to run with some command line options (including from inside Eclipse).  
 * Look in the docs area at the Jacob usage document for command line options.
 * <p>
 */
import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.jacob.test.BaseTestCase;

public class WMPlayer extends BaseTestCase {

	/**
	 * This should demo the media player but it doesn't
	 */
	public void testOpenWMPlayer() {
		// this file exists in windows 7 installations
		File file = new File(
				"C:/Windows/winsxs/x86_microsoft-windows-videosamples_31bf3856ad364e35_6.1.7600.16385_none_f583837f77a63ec7");
		String filePath = file.getAbsolutePath();
		String microsoftTestURL = filePath;
		// use these instead if not on windows 7
		// "http://support.microsoft.com/support/mediaplayer/wmptest/samples/new/mediaexample.wma";
		// "http://support.microsoft.com/support/mediaplayer/wmptest/samples/new/mediaexample.wmv";
		ActiveXComponent wmp = null;
		// could use WMPlayer.OCX alias also
		wmp = new ActiveXComponent(
				"CLSID:{6BF52A52-394A-11D3-B153-00C04F79FAA6}");// ("WMPlayer.OCX");

		wmp.setProperty("URL", microsoftTestURL);
		assertEquals(wmp.getProperty("URL").toString(), microsoftTestURL);

		// alternative way to get the controls
		Dispatch controls = Dispatch.get(wmp, "controls").toDispatch();
		Dispatch.call(controls, "Play");
		// the sourceforge posting didn't post all the code so this is all we
		// have we need some other information on how to set the document
		// so that we have a url to open

		// pause to let it play a second or two
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted");
		}
		for (int i = 0; i < 1000; i++) {
			// Get media object
			Dispatch vMedObj = wmp.getProperty("currentMedia").toDispatch();
			// Get duration of media object
			Variant vdur = Dispatch.call(vMedObj, "duration");
			// why is this always 0?
			// System.out.println(microsoftTestURL + " length is "
			// + vdur.getDouble());
			// System.out.println("the wmp url is "
			// + wmp.getProperty("URL").toString());
		}

	}
}
