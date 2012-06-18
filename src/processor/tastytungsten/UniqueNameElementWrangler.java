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

import java . util . HashMap ;
import java . util . Map ;
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
	    StringBuilder stringBuilder = getStringBuilder ( ) ;
	    Object prefixConstant = getPrefixConstant ( ) ;
	    stringBuilder . append ( prefixConstant ) ;
	    Random random = getRandom ( ) ;
	    int nextInt = random . nextInt ( ) ;
	    int abs = abs ( nextInt ) ;
	    stringBuilder . append ( abs ) ;
	    // CHECKSTYLE:OFF
	    Map < Boolean , ElementVisitor < ? extends StringBuilder , ? super P > >
		// CHECKSTYLE:ON
		map =
		getMap ( ) ;
	    ElementVisitor < ? extends StringBuilder , ? super P > trueVal =
		getUniqueNameElementWrangler ( ) ;
	    ElementVisitor < ? extends StringBuilder , ? super P > falseVal =
		getElementVisitor ( stringBuilder ) ;
	    map . put ( true , trueVal ) ;
	    map . put ( false , falseVal ) ;
	    // CHECKSTYLE:OFF
	    Callable < ? extends ElementVisitor < ? extends Boolean , ? super P > >
		// CHECKSTYLE:ON
		duplicateNameElementAskerCallable =
		getDuplicateNameElementAskerCallable ( stringBuilder ) ;
	    ElementVisitor < ? extends Boolean , ? super P >
		duplicateNameElementAsker =
		duplicateNameElementAskerCallable . call ( ) ;
	    boolean duplicateName =
		duplicateNameElementAsker . visit ( element , data ) ;
	    ElementVisitor < ? extends StringBuilder , ? super P > val =
		map . get ( duplicateName ) ;
	    StringBuilder visit = val . visit ( element , data ) ;
	    return visit ;
	}

    /**
     * Gets a map.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a map
     **/
    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;

    /**
     * Gets a unique name wrangler.
     *
     * @param <P> user data type
     * @return an unique name wrangler
     **/
    @ UseConstructor ( UniqueNameElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? extends StringBuilder , ? super P >
	getUniqueNameElementWrangler
	( ) ;

    /**
     * Gets a constant valued element visitor.
     *
     * @param <R> the return type
     * @param <P> user data type
     * @param value the constant value
     * @return a constant valued element visitor
     **/
    @ UseConstructor ( AbstractElementVisitor . class )
	abstract
	< R , P >
	ElementVisitor < ? extends R , ? super P >
	getElementVisitor
	( R value ) ;

    /**
     * Makes a DuplicateNameElementAsker.
     *
     * How do we know generated names are unique?
     *
     * @param target the unique name to test
     * @return a unique name element asker.
     **/
    @ UseConstructor ( DuplicateNameElementAskerCallable . class )
	abstract
	Callable < ? extends ElementVisitor < ? extends Boolean , ? super P > >
	getDuplicateNameElementAskerCallable
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
    @ UseConstructor ( Random . class )
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
