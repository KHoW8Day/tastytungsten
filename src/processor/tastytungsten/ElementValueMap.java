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

import java . util . AbstractMap ;
import java . util . Map ;
import java . util . Set ;
import javax . lang . model . element . Element ;

/**
 * A String keyed map based on an element keyed map.
 *
 * @param <V> usually {@link javax.lang.model.element.AnnotationValue}
 **/
abstract class ElementValueMap < V > extends AbstractMap < String , V >
{
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public Set < Map . Entry < String , V > > entrySet ( )
	{
	    Map < ? extends Element , ? extends V > map = getMap ( ) ;
	    Set < ? extends Map . Entry < ? extends Element , ? extends V > >
		entrySet =
		map . entrySet ( ) ;
	    Set < Map . Entry < String , V > > stage =
		getElementValueSet ( entrySet ) ;
	    return stage ;
	}

    /**
     * Gets the Element keyed map.
     *
     * @return the element keyed map
     **/
    @ UseParameter
	abstract Map < ? extends Element , ? extends V > getMap ( ) ;

    /**
     * Gets a set of string keyed map entries based on the specified
     * set of element keyed map entries.
     *
     * @param <V> usually {@link javax.lang.model.element.AnnotationValue}
     * @param set the specified element keyed map entries
     * @return a string keyed set of map entries
     **/
    @ UseConstructor ( ElementValueSet . class )
	abstract
	< V >
	Set < Map . Entry < String , V > >
	getElementValueSet
	(
	 Set < ? extends Map . Entry < ? extends Element , ? extends V > >
	 set
	 ) ;
}
