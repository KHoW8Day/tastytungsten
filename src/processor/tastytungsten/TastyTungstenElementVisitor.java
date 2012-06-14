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
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . SimpleElementVisitor6 ;
import javax . lang . model . util . Elements ;





/**
 * This is where the dependency injection happens.
 *
 * @param <R> return type
 * @param <P> data type
 * @param <A> data type for other
 **/
abstract class TastyTungstenElementVisitor < R , P , A >
    extends SimpleElementVisitor6 < R , P >
{
    /**
     * Creates a dependency injected subclass.
     *
     * @param element represents the abstract superclass of the new subclass
     * @param data ${inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	protected R defaultAction ( final Element element , final P data )
	{
	    Elements elementUtils = getElementUtils ( ) ;
	    // CHECKSTYLE:OFF
	    AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super A > elementValuesWithDefaultsAnnotationValueWrangler =
		// CHECKSTYLE:ON
		getElementValuesWithDefaultsAnnotationValueWrangler ( elementUtils ) ;
	    Object value = getValue ( ) ;
	    // CHECKSTYLE:OFF
	    AnnotationValueVisitor < ? extends AnnotationValue , ? super A > annotationValuePunter =
		// CHECKSTYLE:ON
		getAnnotationValuePunter
		( elementValuesWithDefaultsAnnotationValueWrangler , value ) ;
	    // CHECKSTYLE:OFF
	    AnnotationValueVisitor < ? extends String , ? super A > qualifiedNameAnnotationValueWrangler =
		// CHECKSTYLE:ON
		getQualifiedNameAnnotationValueWrangler ( ) ;
	    return null ;
	}

    /**
     * Gets the element utils.
     *
     * @return the element utils
     **/
    @ UseParameter
	abstract Elements getElementUtils ( ) ;

    /**
     * Gets the element values.
     *
     * @return a visitor that gets the element values
     **/
    @ UseConstructor
	( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract
	// CHECKSTYLE:OFF
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super A >
	// CHECKSTYLE:ON
	getElementValuesWithDefaultsAnnotationValueWrangler
	( Elements elementUtils ) ;

    /**
     * Gets a punter.
     *
     * @param <R> return type
     * @param <A> data type
     * @param <B> secondary data type
     * @param mapper provides a map
     * @param target selects from the map
     * @return a punter
     **/
    @ UseConstructor ( AnnotationValuePunter . class )
	abstract
	< P , A , B >
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P >
	getAnnotationValuePunter
	(
	 // CHECKSTYLE:OFF
	 AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super B > mapper ,
	  // CHECKSTYLE:ON
	 Object target
	 ) ;

    /**
     * gets the qualified name.
     *
     * @return a visitor that gets the qualified name of an annotation
     **/
    @ UseConstructor ( QualifiedNameAnnotationValueWrangler . class )
	abstract
	AnnotationValueVisitor < ? extends String , ? super A >
	getQualifiedNameAnnotationValueWrangler ( ) ;

    /**
     * Returns the value constant.
     *
     * @return "value"
     **/
    @ UseStringConstant ( "value" )
	abstract Object getValue ( ) ;
}
