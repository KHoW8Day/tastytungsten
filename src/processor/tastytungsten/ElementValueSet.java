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

import java . util . AbstractSet ;
import java . util . Iterator ;
import java . util . Map ;
import java . util . Set ;
import javax . lang . model . element . Element ;

/**
 * Makes a string keyed set off an Element keyed set.
 *
 * @param <V> usually {@link javax.lang.model.element.AnnotationValue}
 **/
abstract class ElementValueSet < V >
    extends AbstractSet < Map . Entry < String , V > >
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public
	Iterator < Map . Entry < String , V > >
	iterator
	( )
	{
	    Set < ? extends Map . Entry < ? extends Element , ? extends V > >
		set =
		getSet ( ) ;
	    Iterator < ? extends Map . Entry < ? extends Element , ? extends V > > //
		iterator =
		set . iterator ( ) ;
	    Iterator < Map . Entry < String , V > > stage =
		getElementValueIterator ( iterator ) ;
	    return stage ;
	}

    /**
     * {@inheritDoc}.
     **/
    @ Override
	public int size ( )
	{
	    Set < ? > set = getSet ( ) ;
	    int size = set . size ( ) ;
	    return size ;
	}

    /**
     * Gets the element based set.
     *
     * @return the element based set
     **/
    @ UseParameter
	abstract
	Set < ? extends Map . Entry < ? extends Element , ? extends V > >
	getSet ( ) ;

    /**
     * Constructs a string based iterator based on the specified
     * element based iterator.
     *
     * @param <V> usually {@link javax.lang.model.element.AnnotationValue}
     * @param iterator the specified element based iterator
     * @return a string based iterator
     **/
    @ UseConstructor ( ElementValueIterator . class )
	abstract
	< V >
	Iterator < Map . Entry < String , V > >
	getElementValueIterator
	(
	 Iterator < ? extends Map . Entry < ? extends Element , ? extends V > >
	 iterator
	 ) ;
}
