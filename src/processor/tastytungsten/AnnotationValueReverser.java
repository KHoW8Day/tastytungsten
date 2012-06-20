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

import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . util . SimpleAnnotationValueVisitor6 ;



/**
 * Reverses or upcasts annotation values.
 *
 * @param <P> the user data type
 **/
abstract class AnnotationValueReverser < P >
    extends SimpleAnnotationValueVisitor6 < AnnotationValue , P >
{
    /**
     * Gets an annotation value based on the specified string.
     *
     * @param value the specified annotation mirror
     * @param data {@inheritDoc}
     * @return an annotation value based on the specified string
     **/
    @ Override
	public
	AnnotationValue visitString
	( final String value , final P data )
	{
	    AnnotationValue visit = getAnnotationValue ( value ) ;
	    return visit ;
	}

    /**
     * Gets an annotation value based on the specified annotation mirror.
     *
     * @param value the specified annotation mirror
     * @param data {@inheritDoc}
     * @return an annotation value based on the specified annotation mirror
     **/
    @ Override
	public
	AnnotationValue visitAnnotation
	( final AnnotationMirror value , final P data )
	{
	    AnnotationValue visit = getAnnotationValue ( value ) ;
	    return visit ;
	}

    /**
     * Gets an annotation value based on the specified string.
     *
     * @param value the specified annotation mirror
     * @return an annotation value based on the specified string
     **/
   @ UseConstructor ( StringAnnotationValue . class )
	abstract AnnotationValue getAnnotationValue ( String value ) ;

    /**
     * Gets an annotation value based on the specified annotation mirror.
     *
     * @param value the specified annotation mirror
     * @return an annotation value based on the specified annotation mirror
     **/
   @ UseConstructor ( AnnotationMirrorAnnotationValue . class )
	abstract AnnotationValue getAnnotationValue ( AnnotationMirror value ) ;
}
