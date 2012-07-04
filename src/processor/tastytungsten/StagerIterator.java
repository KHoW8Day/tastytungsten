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
 * Converts an iterator to another form.
 *
 * @param <R> the return type
 * @param <P> the data type
 **/
abstract class StagerIterator < R , P > implements Iterator < R >
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public final boolean hasNext ( )
	{
	    Iterator < ? > iterator = getIterator ( ) ;
	    boolean hasNext = iterator . hasNext ( ) ;
	    return hasNext ;
	}

    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public final R next ( )
	{
	    Stager < ? extends R , ? super P > stager = getStager ( ) ;
	    Iterator < ? extends P > iterator = getIterator ( ) ;
	    P p = iterator . next ( ) ;
	    R r = stager . stage ( p ) ;
	    return r ;
	}

    /**
     * Throws an unsupported operation exception.
     **/
    @ Override
	@ UseUnsupportedOperationException
	public abstract void remove ( ) ;

    /**
     * Gets the source iterator.
     *
     * @return the source iterator
     **/
    @ UseParameter
	abstract Iterator < ? extends P > getIterator ( ) ;

    /**
     * For conversion.
     *
     * @return a stager
     **/
    @ UseParameter
	abstract Stager < ? extends R , ? super P > getStager ( ) ;
}
