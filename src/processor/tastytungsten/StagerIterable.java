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

import java . util . Iterator ;

/**
 * An iterable based on converting
 * from one type to another.
 *
 * @param <R> the return type
 * @param <P> the data type
 **/
abstract class StagerIterable < R , P > implements Iterable < R >
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public Iterator < R > iterator ( )
	{
	    Iterable < ? extends P > iterable = getIterable ( ) ;
	    Iterator < ? extends P > iterator = iterable . iterator ( ) ;
	    Stager < ? extends R , ? super P > stager = getStager ( ) ;
	    Iterator < R > stagerIterator =
		getStagerIterator ( iterator , stager ) ;
	    return stagerIterator ;
	}

    /**
     * The underlying data.
     *
     * @return the underlying data
     **/
    @ UseParameter
	abstract Iterable < ? extends P > getIterable ( ) ;

    /**
     * For conversion.
     *
     * @return a stager for conversion
     **/
    @ UseParameter
	abstract Stager < ? extends R , ? super P > getStager ( ) ;

    /**
     * Gets a StagerIterator.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param iterator the underlying data
     * @param stager for conversion
     * @return an iterator
     **/
    @ UseConstructor ( StagerIterator . class )
	abstract
	< R , P >
	Iterator < R > getStagerIterator
	(
	 Iterator < ? extends P > iterator ,
	 Stager < ? extends R , ? super P > stager
	 ) ;
}
