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
import java . util . Map ;
import javax . lang . model . element . Element ;

/**
 * {@inheritDoc}.
 *
 * @param <V> value type usually
 * {@link javax.lang.model.element.AnnotationValue}
 **/
abstract class ElementValueIterator < V >
    implements Iterator < Map . Entry < String , V > >
{
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public boolean hasNext ( )
	{
	    Iterator < ? extends Map . Entry < ? extends Element , ? extends V > > //
		iterator =
		getIterator ( ) ;
	    boolean hasNext = iterator . hasNext ( ) ;
	    return hasNext ;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public Map . Entry < String , V > next ( )
	{
	    Iterator < ? extends Map . Entry < ? extends Element , ? extends V > > //
		iterator =
		getIterator ( ) ;
	    Map . Entry < ? extends Element , ? extends V > entry =
		iterator . next ( ) ;
	    Map . Entry < String , V > stage =
		getElementValueMapEntry ( entry ) ;
	    return stage ;
	}

    /**
     * Throws an exception.
     **/
    @ Override
	public abstract void remove ( ) ;

    /**
     * The element keyed iterator.
     *
     * @return the element keyed iterator
     **/
    @ UseParameter
	abstract
	Iterator < ? extends Map . Entry < ? extends Element , ? extends V > >
	getIterator
	( ) ;

    /**
     * Gets a string keyed map entry based on the specified
     * element keyed map entry.
     *
     * @param <V> usually {@link javax.lang.model.element.AnnotationValue}
     * @param entry the specified element keyed map entry
     * @return a string keyed map entry
     **/
    @ UseConstructor ( ElementValueMapEntry . class )
	abstract
	< V >
	Map . Entry < String , V >
	getElementValueMapEntry
	(
	 Map . Entry < ? extends Element , ? extends V > entry
	 ) ;
}
