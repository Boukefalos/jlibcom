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
/**
 * 
 */
package com.jacob.com;

/**
 * A bunch of DispatchIds that were pulled out of the Dispatch class for version
 * 1.14.
 */
public class DispatchIdentifier {

    private DispatchIdentifier() {
        // This is utility class so there is no constructor.
    }

    public static final int DISPID_UNKNOWN = -1;
    public static final int DISPID_VALUE = 0;
    public static final int DISPID_PROPERTYPUT = -3;
    public static final int DISPID_NEWENUM = -4;
    public static final int DISPID_EVALUATE = -5;
    public static final int DISPID_CONSTRUCTOR = -6;
    public static final int DISPID_DESTRUCTOR = -7;
    public static final int DISPID_COLLECT = -8;
    public static final int DISPID_AUTOSIZE = -500;
    public static final int DISPID_BACKCOLOR = -501;
    public static final int DISPID_BACKSTYLE = -502;
    public static final int DISPID_BORDERCOLOR = -503;
    public static final int DISPID_BORDERSTYLE = -504;
    public static final int DISPID_BORDERWIDTH = -505;
    public static final int DISPID_DRAWMODE = -507;
    public static final int DISPID_DRAWSTYLE = -508;
    public static final int DISPID_DRAWWIDTH = -509;
    public static final int DISPID_FILLCOLOR = -510;
    public static final int DISPID_FILLSTYLE = -511;
    public static final int DISPID_FONT = -512;
    public static final int DISPID_FORECOLOR = -513;
    public static final int DISPID_ENABLED = -514;
    public static final int DISPID_HWND = -515;
    public static final int DISPID_TABSTOP = -516;
    public static final int DISPID_TEXT = -517;
    public static final int DISPID_CAPTION = -518;
    public static final int DISPID_BORDERVISIBLE = -519;
    public static final int DISPID_APPEARANCE = -520;
    public static final int DISPID_MOUSEPOINTER = -521;
    public static final int DISPID_MOUSEICON = -522;
    public static final int DISPID_PICTURE = -523;
    public static final int DISPID_VALID = -524;
    public static final int DISPID_READYSTATE = -525;
    public static final int DISPID_REFRESH = -550;
    public static final int DISPID_DOCLICK = -551;
    public static final int DISPID_ABOUTBOX = -552;
    public static final int DISPID_CLICK = -600;
    public static final int DISPID_DBLCLICK = -601;
    public static final int DISPID_KEYDOWN = -602;
    public static final int DISPID_KEYPRESS = -603;
    public static final int DISPID_KEYUP = -604;
    public static final int DISPID_MOUSEDOWN = -605;
    public static final int DISPID_MOUSEMOVE = -606;
    public static final int DISPID_MOUSEUP = -607;
    public static final int DISPID_ERROREVENT = -608;
    public static final int DISPID_READYSTATECHANGE = -609;
    public static final int DISPID_AMBIENT_BACKCOLOR = -701;
    public static final int DISPID_AMBIENT_DISPLAYNAME = -702;
    public static final int DISPID_AMBIENT_FONT = -703;
    public static final int DISPID_AMBIENT_FORECOLOR = -704;
    public static final int DISPID_AMBIENT_LOCALEID = -705;
    public static final int DISPID_AMBIENT_MESSAGEREFLECT = -706;
    public static final int DISPID_AMBIENT_SCALEUNITS = -707;
    public static final int DISPID_AMBIENT_TEXTALIGN = -708;
    public static final int DISPID_AMBIENT_USERMODE = -709;
    public static final int DISPID_AMBIENT_UIDEAD = -710;
    public static final int DISPID_AMBIENT_SHOWGRABHANDLES = -711;
    public static final int DISPID_AMBIENT_SHOWHATCHING = -712;
    public static final int DISPID_AMBIENT_DISPLAYASDEFAULT = -713;
    public static final int DISPID_AMBIENT_SUPPORTSMNEMONICS = -714;
    public static final int DISPID_AMBIENT_AUTOCLIP = -715;
    public static final int DISPID_AMBIENT_APPEARANCE = -716;
    public static final int DISPID_AMBIENT_CODEPAGE = -725;
    public static final int DISPID_AMBIENT_PALETTE = -726;
    public static final int DISPID_AMBIENT_CHARSET = -727;
    public static final int DISPID_AMBIENT_TRANSFERPRIORITY = -728;
}
