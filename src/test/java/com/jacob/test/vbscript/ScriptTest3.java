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
package com.jacob.test.vbscript;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComException;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.DispatchEvents;
import com.jacob.com.Variant;
import com.jacob.test.BaseTestCase;

/**
 * Here we create the ScriptControl component in a separate MTA thread and then
 * call the Eval method from the main thread. The main thread must also be an
 * MTA thread. If you try to create it as an STA then you will not be able to
 * make calls into a component running in another thread.
 * <p>
 * May need to run with some command line options (including from inside
 * Eclipse). Look in the docs area at the Jacob usage document for command line
 * options.
 */
public class ScriptTest3 extends BaseTestCase {

    public static ActiveXComponent sC;

    public static DispatchEvents de = null;

    public static Dispatch sControl = null;

    public static boolean quit = false;

    public void testScript() {
        try {
            ComThread.InitMTA();
            ScriptTest3Inner script = new ScriptTest3Inner();
            script.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                // should we get this?
            }

            Variant result = Dispatch.call(sControl, "Eval",
                    getSampleVPScriptForEval());
            System.out.println("eval(" + getSampleVPScriptForEval() + ") = "
                    + result);
            System.out.println("setting quit");
            ScriptTest3.quit = true;
        } catch (ComException e) {
            e.printStackTrace();
            fail("Caught excpetion running script with MTA");
        } finally {
            System.out.println("main done");
            ComThread.Release();
        }
    }

    class ScriptTest3Inner extends Thread {
        public void run() {
            try {
                ComThread.InitMTA();
                System.out.println("OnInit");
                String lang = "VBScript";
                sC = new ActiveXComponent("ScriptControl");
                sControl = sC.getObject();
                Dispatch.put(sControl, "Language", lang);
                ScriptTestErrEvents te = new ScriptTestErrEvents();
                de = new DispatchEvents(sControl, te);
                System.out.println("sControl=" + sControl);
                while (!quit) {
                    sleep(100);
                }
                ComThread.Release();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("worker thread exits");
            }
        }

    }
}
