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

/**
 * Most COM bridges use java.lang.Long as their Java data type for COM Currency
 * data. This is because COM currency is a 64 bit number where the last 4 digits
 * represent the milli-cents. We wanted to support 64 bit Long values for x64
 * platforms so that meant we wanted to map Java.LONG to COM.LONG even though it
 * only works for 64 bit platforms. The end result was we needed a new
 * representation for Money so we have this.
 * <p>
 * In the future, this should convert to and from BigDecimal or Double
 */
public class Currency {
	Long embeddedValue = null;

	/**
	 * constructor that takes a long already in COM representation
	 * 
	 * @param newValue
	 */
	public Currency(long newValue) {
		embeddedValue = new Long(newValue);
	}

	/**
	 * constructor that takes a String already in COM representation
	 * 
	 * @param newValue
	 */
	public Currency(String newValue) {
		embeddedValue = new Long(newValue);
	}

	/**
	 * 
	 * @return the currency as a primitive long
	 */
	public long longValue() {
		return embeddedValue.longValue();
	}

	/**
	 * getter to the inner storage so that cmpareTo can work
	 * 
	 * @return the embedded long value
	 */
	protected Long getLongValue() {
		return embeddedValue;
	}

	/**
	 * compares the values of two currencies
	 * 
	 * @param anotherCurrency
	 * @return the usual compareTo results
	 */
	public int compareTo(Currency anotherCurrency) {
		return embeddedValue.compareTo(anotherCurrency.getLongValue());
	}

	/**
	 * standard comparison
	 * 
	 * @param o
	 *            must be Currency or Long
	 * @return the usual compareTo results
	 */
	public int compareTo(Object o) {
		if (o instanceof Currency) {
			return compareTo((Currency) o);
		} else if (o instanceof Long) {
			return embeddedValue.compareTo((Long) o);
		} else
			throw new IllegalArgumentException(
					"Can only compare to Long and Currency not "
							+ o.getClass().getName());
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (compareTo(o) == 0) {
			return true;
		} else {
			return false;
		}
	}
}
