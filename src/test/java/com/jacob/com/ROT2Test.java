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
package com.jacob.com;

import com.jacob.test.BaseTestCase;

/**
 * This test class exists to test the WeakRefernce implementation .
 * 
 * It is not useful if there isn't one at this time
 * 
 * <p>
 * May need to run with some command line options (including from inside
 * Eclipse). Look in the docs area at the Jacob usage document for command line
 * options.
 */
public class ROT2Test extends BaseTestCase {

    /**
     * runs a multi-threaded test
     */
    public void testDoesNotBlowUp() {
        ROT2TestThread threads[] = new ROT2TestThread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ROT2TestThread("thread-" + i, 3000);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    /**
     * This will try and exercise the thread support in the ROT
     */

    public class ROT2TestThread extends Thread {
        private java.util.List<Variant> ThreadObjects;

        private int initialRunSize = 0;

        /**
         * @param arg0
         * @param iStartCount
         *            the initial number of threads
         */
        public ROT2TestThread(String arg0, int iStartCount) {
            super(arg0);
            initialRunSize = iStartCount;

        }

        /**
         * A semi-complex series of steps to put the ROT under stress. 1)
         * discard half the objects we've created 2) if size is greater than 1
         * but not a even number, add 1 new object 3) stop when size is 1.
         * 
         * @see java.lang.Runnable#run()
         */
        public void run() {
            // something that keeps object references around
            // so the gc can't collect them
            // we need to create these in the thread so they end up in the right
            // ROT table
            ThreadObjects = new java.util.ArrayList<Variant>(initialRunSize);
            for (int i = 0; i < initialRunSize; i++) {
                // create the object
                Variant aNewVariant = new Variant(getName() + "_" + i);

                // create a hard reference to it
                ThreadObjects.add(aNewVariant);
            }

            while (ThreadObjects.size() > 1) {
                String message = "";
                message = getName() + " Workingset=" + ThreadObjects.size()
                        + " ROT: ";
                message += "(before additions and gc "
                        + ROT.getThreadObjects(false).size() + ")";
                // if there is an odd number of objects greater than 2
                if (ThreadObjects.size() > 2 && ThreadObjects.size() % 2 != 0) {
                    // add a new object
                    Variant aNewVariant = new Variant(getName() + "_*"
                            + ThreadObjects.size());
                    ThreadObjects.add(aNewVariant);
                }
                // now iterate across all the objects in our list
                for (int i = ThreadObjects.size(); i > 0; i--) {
                    // removing every other one?
                    if (i % 2 == 0) {
                        // remove the reference so gc can get it
                        ThreadObjects.remove(i - 1);
                    }

                }

                try {
                    // simulate the system under load and run the GC
                    // should end up with weak references with no objects
                    // attached
                    Thread.sleep(9);
                } catch (InterruptedException e) {
                    // the VM doesn't want us to sleep anymore,
                    // so get back to work
                }
                message += " (before gc, after additions "
                        + ROT.getThreadObjects(false).size() + ")";
                System.gc();
                message += " (after System.gc "
                        + ROT.getThreadObjects(false).size() + ")";
                System.out.println(message);
            }
        }

        /**
         * Another test would be to override this to always return the same
         * name. That would really screw the ROT!
         * 
         * @see java.lang.Object#toString()
         */
        public String toString() {
            return super.toString();
        }
    }
}