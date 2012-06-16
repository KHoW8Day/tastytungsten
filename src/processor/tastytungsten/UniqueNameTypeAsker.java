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

import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . type . DeclaredType ;
import javax . lang . model . type . NoType ;
import javax . lang . model . util . SimpleTypeVisitor6 ;

/**
 * A unique name asker.
 *
 * @param <P> user data type
 **/
abstract class UniqueNameTypeAsker < P >
    extends SimpleTypeVisitor6 < Boolean , P >
{
    /**
     * Test the specified name candidate to see it is unique.
     *
     * @param type the type to test it againtst
     * @param data user data
     * @return true if the candidate name is unique
     **/
    @ Override
	public Boolean visitDeclared ( final DeclaredType type , final P data )
	{
	    Object target = getTarget ( ) ;
	    ElementVisitor < ? extends Boolean , ? super P >
		uniqueNameElementAsker =
		getUniqueNameElementAsker ( target ) ;
	    Element element = type . asElement ( ) ;
	    Boolean visit = uniqueNameElementAsker . visit ( element , data ) ;
	    return visit ;
	}

    /**
     * Congrats.  You should have arrived here either via
     * Object or an interface with no super interface.
     * There is nothing more to test.
     *
     * @param type the specified type
     * @param data user data
     * @return true always
     **/
    @ Override
	public Boolean visitNoType ( final NoType type , final P data )
	{
	    return true ;
	}

    /**
     * Gets the target name to test for uniqueness.
     *
     * @return the target name to test
     **/
    @ UseParameter
	abstract Object getTarget ( ) ;

    /**
     * Get a unique name element asker.
     *
     * @param <P> the user data type
     * @param target the name to test for uniqueness
     * @return a unique name element asker
     **/
    @ UseConstructor ( UniqueNameElementAsker . class )
	abstract
	< P >
	ElementVisitor < ? extends Boolean , ? super P >
	getUniqueNameElementAsker
	( Object target ) ;
}
