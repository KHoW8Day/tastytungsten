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
import java . util . Map ;
import java . util . Set ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . util . SimpleAnnotationValueVisitor6 ;
import javax . lang . model . util . Elements ;




/**
 * Wrangles a convenient to use map of annotation values.
 *
 * @param <P> user data
 **/
abstract class ElementValuesWithDefaultsAnnotationValueWrangler < P >
    extends
	// CHECKSTYLE:OFF
	SimpleAnnotationValueVisitor6 < Map < ? extends String , ? extends AnnotationValue > , P >
	// CHECKSTYLE:ON
{
    /**
     * Return a map with all the annotation values
     * of the specified annotation mirror.
     *
     * @param value the specified annotation mirror
     * @param data {@inheritDoc}
     * @return a map with all the annotation values
     **/
    @ Override
	public
	Map < ? extends String , ? extends AnnotationValue >
	visitAnnotation
	( final AnnotationMirror value , final P data )
	{
	    Elements elementUtils = getElementUtils ( ) ;
	    // CHECKSTYLE:OFF
	    Map < ? extends ExecutableElement , ? extends AnnotationValue > elementValues =
		// CHECKSTYLE:ON
		elementUtils . getElementValuesWithDefaults ( value ) ;
	    // CHECKSTYLE:OFF
	    Set < ? extends Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > > input =
		// CHECKSTYLE:ON
		elementValues . entrySet ( ) ;
	    Map < String , AnnotationValue > output = getMap ( ) ;
	    process ( input , output ) ;
	    return output ;
	}

    /**
     * Add the specified set of entries to the output.
     *
     * @param input the set of entries
     * @param output the output map
     **/
    private void process
	(
	 // CHECKSTYLE:OFF
	 final Set < ? extends Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > > input ,
	 // CHECKSTYLE:ON
	 final Map < ? super String , ? super AnnotationValue > output
	 )
    {
	// CHECKSTYLE:OFF
	for ( Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > i : input )
	 // CHECKSTYLE:ON
	    {
		process ( i , output ) ;
	    }
    }

    /**
     * Add the specified entry into the output.
     *
     * @param i the specified entry
     * @param output the output
     **/
    private void process
	(
	 // CHECKSTYLE:OFF
	 final Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > i ,
	 // CHECKSTYLE:ON
	 final Map < ? super String , ? super AnnotationValue > output )
    {
	ExecutableElement executableElement = i . getKey ( ) ;
	Object object = executableElement . getSimpleName ( ) ;
	String string = object . toString ( ) ;
	AnnotationValue annotationValue = i . getValue ( ) ;
	output . put ( string , annotationValue ) ;
    }

    /**
     * Get the element utils.
     *
     * @return element utils
     **/
    @ UseParameter
	abstract Elements getElementUtils ( ) ;

    /**
     * Construct a new map.
     *
     * @param <K> the key class for the map
     * @param <V> the value class for the map
     * @return a new map
     **/
    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;
}
