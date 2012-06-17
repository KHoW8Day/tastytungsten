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

import java . util . Map ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * A caching element visitor.
 *
 * @param <R> the return type
 * @param <P> the user data type
 **/
abstract class ElementCacher < R , P > extends SimpleElementVisitor6 < R , P >
{
    /**
     * A caching element visitor.
     *
     * If it does not have a cache entry, it creates one
     * and then remembers it.
     *
     * @param element {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	protected
	R
	defaultAction
	( final Element element , final P data )
	{
	    Map < Element , R > map = getMap ( ) ;
	    boolean containsKey = map . containsKey ( element ) ;
	    R visit = null ;
	    if ( containsKey )
		{
		    visit = map . get ( element ) ;
		}
	    else
		{
		    ElementVisitor < ? extends R , ? super P > visitor =
			getVisitor ( ) ;
		    visit = visitor . visit ( element , data ) ;
		    map . put ( element , visit ) ;
		}
	    return visit ;
	}

    /**
     * Creates a cache map.
     *
     * @return a map.
     **/
    @ UseParameter
	abstract Map < Element , R > getMap ( ) ;

    /**
     * Used if there is not a cached value.
     *
     * @return an element visitor to get non cached values.
     **/
    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super P > getVisitor ( ) ;
}
