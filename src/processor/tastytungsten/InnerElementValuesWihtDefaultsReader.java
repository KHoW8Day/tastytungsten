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
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . ExecutableElement ;

/**
 * For simplifiying element values with defaults.
 **/
abstract class InnerElementValuesWithDefaultsReader
    implements
	// CHECKSTYLE:OFF
	Reader < Map < String , AnnotationValue > , Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > >
													  // CHECKSTYLE:ON
{
    /**
     * Reduces a set of ExecutableElement, AnnotationValue
     * to a map of String, AnnotationValue.
     *
     * @param iterator {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public
	Map < String , AnnotationValue >
	read
	(
	 final
	 // CHECKSTYLE:OFF
	 Iterator < ? extends Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > >
	 // CHECKSTYLE:ON
	 iterator
	 )
	{
	    Map < String , AnnotationValue > map1 = getMap ( ) ;
	    Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > entry =   iterator . next ( ) ;
	    ExecutableElement key = entry . getKey ( ) ;
	    Object simpleName = key . getSimpleName ( ) ;
	    String string = simpleName . toString ( ) ;
	    AnnotationValue annotationValue = entry . getValue ( ) ;
	    map1 . put ( string , annotationValue ) ;
	    Reader < Map < String , AnnotationValue > , ? super Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > >
		// CHECKSTYLE:ON
		elementValuesWithDefaultsReader =
		getElementValuesWithDefaultsReader ( ) ;
	    Map < String , AnnotationValue > map2 = elementValuesWithDefaultsReader . read ( iterator ) ;
	    map1 . putAll ( map2 ) ;
	    return map1 ;
	}

    /**
     * Gets an inner reader for recursion.
     *
     * @return an inner reader
     **/
    @ UseConstructor ( ElementValuesWithDefaultsReader . class )
	abstract
	// CHECKSTYLE:OFF
	Reader < Map < String , AnnotationValue > , ? super Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > >
	// CHECKSTYLE:ON
	getElementValuesWithDefaultsReader
	( ) ;

    /**
     * Gets a constant reader.
     *
     * @param <R> output type
     * @param <P> input type
     * @param constantValue for returning
     * @return a constant reader
     **/
    @ UseConstructor ( ConstantReader . class )
	abstract
	< R , P >
	Reader < R , ? super P >
	getConstantReader
	( R constantValue ) ;

    /**
     * Gets a map.
     *
     * @param <K> key type
     * @param <V> value type
     * @return a new map
     **/
    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;
}
