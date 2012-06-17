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
import javax . lang . model . element . Element ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * Used to determine if a name candidate can be added to a class
 * without conflict.
 *
 * @param <P> user data type
 **/
abstract class UniqueNameElementAsker < P >
    extends SimpleElementVisitor6 < Boolean , P >
{
    /**
     * Checks to see if a proposed candidate name
     * matches any of the simple names
     * of the enclosed elements of the specified element.
     *
     * @param element the specified element
     * @param data user data
     * @return true iff there is no match
     **/
    @ Override
	protected
	Boolean
	defaultAction
	( final Element element , final P data )
	{
	    Object target = getTarget ( ) ;
	    List < ? extends Element > enclosedElements =
		element . getEnclosedElements ( ) ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    Object simpleName = enclosedElement . getSimpleName ( ) ;
		    String string1 = simpleName . toString ( ) ;
		    String string2 = target . toString ( ) ;
		    boolean match = string1 . equals ( string2 ) ;
		    if ( match )
			{
			    return false ;
			}
		}
	    return true ;
	}

    /**
     * Gets the candidate name to check.
     *
     * @return the candidate
     **/
    @ UseParameter
	abstract Object getTarget ( ) ;
}
