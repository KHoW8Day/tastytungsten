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
 * Checks (assuming there is a next element)
 * the or of the specified iterator.
 **/
abstract class OrInnerReader implements Reader < Boolean , Boolean >
{
    /**
     * {@inheritDoc}.
     *
     * @param list {@inheritDoc}
     * return {@inheritDoc}
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
		getConstantReader ( true ) ;
	    map . put ( true , trueVal ) ;
	    Reader < ? extends Boolean , ? super Boolean > falseVal =
		getOrReader ( ) ;
	    map . put ( false , falseVal ) ;
	    boolean next = list . next ( ) ;
	    Reader < ? extends Boolean , ? super Boolean > reader =
		map . get ( next ) ;
	    boolean read = reader . read ( list ) ;
	    return read ;
	}

    /**
     * If need be recurse.
     *
     * @return an OrReader for finishing
     **/
    @ UseConstructor ( OrReader . class )
	abstract Reader < Boolean , Boolean > getOrReader ( ) ;

    /**
     * For stopping recursion.
     *
     * @param <R> reduction type
     * @param <P> input type
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
     * Constructs a new map.
     *
     * @param <K> key type
     * @param <V> value type
     * @return a new map
     **/
    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;
}
