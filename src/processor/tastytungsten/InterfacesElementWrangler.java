// Copyright © (C) 2012 Emory Hughes Merryman, III
//
// This file is part of tastytungsten.
//
// tastytungsten is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// tastytungsten is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//

package tastytungsten ;

import java . util . List ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * Wrangles interfaces.
 *
 * @param <P> user data type
 **/
abstract class InterfacesElementWrangler < P >
    extends SimpleElementVisitor6 < List < ? extends TypeMirror > , P >
{
    /**
     * wrangles the interfaces of an element.
     *
     * @param element the specified element
     * @param data user data
     * @return the element's interfaces
     **/
    @ Override
	public
	List < ? extends TypeMirror >
	visitType
	(
	 final TypeElement element ,
	 final P data
	 )
	{
	    List < ? extends TypeMirror > interfaces =
		element . getInterfaces ( ) ;
	    return interfaces ;
	}
}
