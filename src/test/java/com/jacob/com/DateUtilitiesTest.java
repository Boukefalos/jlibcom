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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

/**
 * test cases that should exercise the new date conversion code
 * <p>
 * This test does not require any command line options because it is only a
 * utility test
 */

public class DateUtilitiesTest extends TestCase {

    /**
     * verify date conversion to and from java
     */
    public void testDateUtilities() {
        Date now = new Date();
        double comTimeForNow = DateUtilities.convertDateToWindowsTime(now);
        Date retrievedNow = DateUtilities
                .convertWindowsTimeToDate(comTimeForNow);
        if (!now.equals(retrievedNow)) {
            fail("DateUtilities Date Test failed " + now + " != "
                    + retrievedNow);
        } else {
            System.out.println("DateUtilities Date Test passed");
        }

    }

    /**
     * Verify that the start of time is when we think it is.
     */
    public void testBeginningOfWindowsTime() {
        // this is a magic time in the windows world
        Date beginningOfWindowsTime = new GregorianCalendar(1899,
                Calendar.DECEMBER, 30).getTime();
        double comTimeForBeginningOfWindowsTime = DateUtilities
                .convertDateToWindowsTime(beginningOfWindowsTime);
        if (comTimeForBeginningOfWindowsTime > 0) {
            fail("Beginning of windows time test failed "
                    + comTimeForBeginningOfWindowsTime);
        } else {
            System.out.println("Beginning of windows time test passed");
        }

    }

}
