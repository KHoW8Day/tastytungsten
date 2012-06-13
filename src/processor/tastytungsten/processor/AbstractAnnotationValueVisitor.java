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

package tastytungsten . processor ;

import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . util . SimpleAnnotationValueVisitor6 ;

import tastytungsten . annotations . UseConstructor ;
import tastytungsten . annotations . UseNull ;

/**
 * An abstract annotation value visitor.
 *
 * Override this class when you want to have a default action.
 *
 * @param <R> the return type
 * @param <P> the user data type
 * @param <A> the data type for the reverser
 * @param <B> the secondary data type
 **/
abstract class AbstractAnnotationValueVisitor < R , P , A , B >
    extends SimpleAnnotationValueVisitor6 < R , P >
{
    @ Override
	public final
	R
	visitAnnotation
	( final AnnotationMirror value , final P data )
	{
	    // CHECKSTYLE:OFF
	    AnnotationValueVisitor < ? extends AnnotationValue , ? super A > annotationValueReverser =
		// CHECKSTYLE:ON
		getAnnotationValueReverser ( ) ;
	    A a = getA ( data ) ;
	    AnnotationValue annotationValue =
		annotationValueReverser . visitAnnotation ( value , a ) ;
	    B b = getB ( data ) ;
	    R visit = defaultAction ( annotationValue , b ) ;
	    return visit ;
	}

    /**
     * Subclass supplied default action.
     *
     * @param value the annotation value
     * @param b user data
     * @return subclass supplied default action
     **/
    @ UseNull
	abstract R defaultAction ( AnnotationValue value , B b ) ;

    /**
     * Gets a reverser or upcaster.
     *
     * @return an annotation value reverser
     **/
    @ UseConstructor ( AnnotationValueReverser . class )
	abstract
	AnnotationValueVisitor < ? extends AnnotationValue , A >
	getAnnotationValueReverser
	( ) ;

    /**
     * Gets the data for the reverser.
     *
     * @param data user data
     * @return the data
     **/
    @ UseNull
	abstract A getA ( P data ) ;

    /**
     * Gets the data for the secondary visit.
     *
     * @param data user data
     * @return the data
     **/
    @ UseNull
	abstract B getB ( P data ) ;
}
