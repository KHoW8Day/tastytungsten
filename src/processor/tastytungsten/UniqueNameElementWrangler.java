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

import java . util . Random ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * Generates unique names.
 *
 * @param <P> user data type
 **/
abstract class UniqueNameElementWrangler < P >
    extends SimpleElementVisitor6 < StringBuilder , P >
{
    /**
     * Generates a unique name for the specified element.
     *
     * @param element the specified element
     * @param data user data
     * @return a unique name for the element
     **/
    @ Override
	protected
	StringBuilder defaultAction
	( final Element element , final P data )
	{
	    boolean unique = false ;
	    Random random = getRandom ( ) ;
	    StringBuilder stringBuilder = getStringBuilder ( ) ;
	    Object prefixConstant = getPrefixConstant ( ) ;
	    stringBuilder . append ( prefixConstant ) ;
	    while ( ! unique )
		{
		    int next = random . nextInt ( ) ;
		    int abs = abs ( next ) ;
		    stringBuilder . append ( abs ) ;
		    ElementVisitor < ? extends Boolean , ? super P >
			uniqueNameElementAsker =
			getUniqueNameElementAsker ( stringBuilder ) ;
		    unique = uniqueNameElementAsker . visit ( element , data ) ;
		}
	    return stringBuilder ;
	}

    /**
     * Makes a UniqueNameElementAsker.
     *
     * How do we know generated names are unique?
     *
     * @param target the unique name to test
     * @return a unique name element asker.
     **/
    @ UseConstructor ( UniqueNameElementAsker . class )
	abstract
	ElementVisitor < ? extends Boolean , ? super P >
	getUniqueNameElementAsker
	( Object target ) ;

    /**
     * Constructs a StringBuilder.
     *
     * @return a StringBuilder
     **/
    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    /**
     * Gets the prefix for generated simple names.
     *
     * Since the generator relies on random numbers and
     * a java simple name can not begin with a number,
     * the prefix is for compliance.
     *
     * @return some legal prefix for java names
     **/
    @ UseStringConstant ( "_var_" )
	abstract Object getPrefixConstant ( ) ;

    /**
     * Creates a random.
     *
     * @return a random
     **/
    @ UseParameter
	abstract Random getRandom ( ) ;

    /**
     * Gets the absolute value of the specified number.
     *
     * @param x the specified number
     * @return the absolute value
     **/
    @ UseStaticMethod ( Math . class )
	abstract int abs ( int x ) ;
}
