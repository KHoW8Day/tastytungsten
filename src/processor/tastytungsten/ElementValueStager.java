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

/**
 * Useful for dealing with element keyed annotation value maps.
 *
 * @param <V> usually {@link javax.lang.model.element.AnnotationValue}
 **/
abstract class ElementValueStager < V >
    implements
	Stager < V , Map < ? extends Element , ? extends V > > //
{
    /**
     * Picks one of the provided annotation values.
     *
     * @param value an element keyed annotation value map.
     * @return one of the annotation values (based on a string key)
     **/
    @ Override
	public
	V
	stage
	( final Map < ? extends Element , ? extends V > value )
	{
	    Map < ? extends String , ? extends V > map =
		getMap ( value ) ;
	    Object targetObject = getTarget ( ) ;
	    String target = targetObject . toString ( ) ;
	    V stage = map . get ( target ) ;
	    return stage ;
	}

    /**
     * Gets the target.
     * The target is of type {@link Object}
     * but it will be converted to type
     * {@link String} using the
     * {@link Object#toString()} method.
     *
     * @return the target.
     **/
    @ UseParameter
	abstract Object getTarget ( ) ;

    /**
     * Converts the given Element keyed map to
     * a String keyed map.
     *
     * @param <V> usually {@link javax.lang.model.element.AnnotationValue}
     * @param map an element keyed map
     * @return a string keyed map
     **/
    @ UseConstructor ( ElementValueMap . class )
	abstract
	< V >
	Map < ? extends String , ? extends V >
	getMap
	( Map < ? extends Element , ? extends V > map ) ;
}
