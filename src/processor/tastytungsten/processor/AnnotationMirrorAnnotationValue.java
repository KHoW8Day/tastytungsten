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

import tastytungsten . annotations . UseParameter ;

/**
 * Wraps an AnnotationMirror into an AnnotationValue.
 **/
abstract class AnnotationMirrorAnnotationValue implements AnnotationValue
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public String toString ( )
    {
	Object value = getValue ( ) ;
	String string = value . toString ( ) ;
	return string ;
    }

    /**
     * {@inheritDoc}.
     *
     * @param <R> {@inheritDoc}
     * @param <P> {@inheritDoc}
     * @param visitor {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    public
	< R , P >
	R
	accept
	( final AnnotationValueVisitor < R , P > visitor , final P data )
    {
	AnnotationMirror value = getValue ( ) ;
	R visit = visitor . visitAnnotation ( value , data ) ;
	return visit ;
    }

    /**
     * {@inheritDoc}.
     *
     * Gets the underlying value.
     *
     * @return the underlying value
     **/
    @ Override
	@ UseParameter
	    public abstract AnnotationMirror getValue ( ) ;
}
