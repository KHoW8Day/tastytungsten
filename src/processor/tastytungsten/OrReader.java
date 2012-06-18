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
import java . util . Iterator ;
import java . util . Map ;

/**
 * Determines the logical or of a bunch or booleans.
 **/
abstract class OrReader implements Reader < Boolean , Boolean >
{
    /**
     * Determines if there is at least one true value.
     *
     * @param list the set of values
     * @return true iff there is at least one true value
     **/
    @ Override
	public
	Boolean
	read
	( final Iterator < ? extends Boolean > list )
	{
	    Map < Boolean , Reader < ? extends Boolean , ? super Boolean > >
		map =
		getMap ( ) ;
	    Reader < ? extends Boolean , ? super Boolean > trueVal =
		getInnerReader ( ) ;
	    map . put ( true , trueVal ) ;
	    Reader < ? extends Boolean , ? super Boolean > falseVal =
		getConstantReader ( false ) ;
	    map . put ( false , falseVal ) ;
	    boolean hasNext = list . hasNext ( ) ;
	    Reader < ? extends Boolean , ? super Boolean > reader =
		map . get ( hasNext ) ;
	    boolean read = reader . read ( list ) ;
	    return read ;
	}

    /**
     * Gets an inner reader.
     * If there is at least one more value (hasNext),
     * then proceed with the inner reader.
     *
     * @return an inner reader
     **/
    @ UseConstructor ( OrInnerReader . class )
	abstract Reader < Boolean , Boolean > getInnerReader ( ) ;

    /**
     * For stopping recursion.
     *
     * @param <R> return type
     * @param <P> user data type
     * @param read constant value
     * @return a constant reader
     **/
    @ UseConstructor ( ConstantReader . class )
	abstract
	< R , P >
	Reader < ? extends R , ? super P >
	getConstantReader
	( R read ) ;

    /**
     * Gets a new Map.
     *
     * @param <K> key type
     * @param <V> value type
     * @return a new map
     **/
    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;
}
