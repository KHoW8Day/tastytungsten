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
	    // CHECKSTYLE:OFF
	    Reader < ? extends Map < ? extends String , ? extends AnnotationValue > , Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > >
		// CHECKSTYLE:ON
		elementValuesWithDefaultsReader =
		getElementValuesWithDefaultsReader ( ) ;
	    Elements elementUtils = getElementUtils ( ) ;
	    // CHECKSTYLE:OFF
	    Map < ? extends ExecutableElement , ? extends AnnotationValue > elementValues =
		// CHECKSTYLE:ON
		elementUtils . getElementValuesWithDefaults ( value ) ;
	    // CHECKSTYLE:OFF
	    Set < ? extends Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > > input =
		// CHECKSTYLE:ON
		elementValues . entrySet ( ) ;
	    // CHECKSTYLE:OFF
	    Iterator < ? extends Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > >
		// CHECKSTYLE:ON
		iterator =
		input . iterator ( ) ;
	    Map < ? extends String , ? extends AnnotationValue >
		output =
		elementValuesWithDefaultsReader . read ( iterator ) ;
	    return output ;
	}

    /**
     * Gets a reader for reduction.
     *
     * @return a reduction reader
     **/
    @ UseConstructor ( ElementValuesWithDefaultsReader . class )
	abstract
	// CHECKSTYLE:OFF
	Reader < ? extends Map < ? extends String , ? extends AnnotationValue > , Map . Entry < ? extends ExecutableElement , ? extends AnnotationValue > >
	// CHECKSTYLE:ON
	getElementValuesWithDefaultsReader
	( ) ;

    /**
     * Get the element utils.
     *
     * @return element utils
     **/
    @ UseParameter
	abstract Elements getElementUtils ( ) ;
}
