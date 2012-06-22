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

import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . util . SimpleAnnotationValueVisitor6 ;

/**
 * Connects an annotation value visitor with a stager
 * to get work done.
 *
 * @param <R> return type
 * @param <A> return type of the visitor and data type of the stager.
 *            this is where the connection happens.
 * @param <P> data type
 **/
abstract class
    StagerAnnotationValueVisitor < R , A , P >
    extends SimpleAnnotationValueVisitor6 < R , P >
{
    /**
     * {@inheritDoc}
     *
     * @param value {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public R visitString ( String value , P data )
	{
	    AnnotationValueVisitor < ? extends A , ? super P > visitor =
		getVisitor ( ) ;
	    A a = visitor . visitString ( value , data ) ;
	    Stager < ? extends R , ? super A > stager = getStager ( ) ;
	    R visit = stager . stage ( a ) ;
	    return visit ;
	}

    /**
     * Gets the visitor.
     *
     * @return the visitor
     **/
    @ UseParameter
	abstract
	AnnotationValueVisitor < ? extends A , ? super P >
	getVisitor
	( ) ;

    /**
     * Gets the stager.
     *
     * @return the stager
     **/
    @ UseParameter
	abstract Stager < ? extends R , ? super A > getStager ( ) ;
}
