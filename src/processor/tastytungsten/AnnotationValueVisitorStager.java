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

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;

/**
 * Connects a stager and a visitor.
 *
 * @param <R> the return type
 * @param <A> the connection type
 * @param <P> user data type
 **/
abstract class AnnotationValueVisitorStager < R , A , P >
    implements Stager < R , A >
{
    /**
     * Joins a stager and a visitor to form a stager.
     *
     * @param value {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public R stage ( final A value )
	{
	    AnnotationValueVisitor < ? extends R , ? super P > visitor =
		getVisitor ( ) ;
	    Stager < ? extends AnnotationValue , ? super A > stager =
		getStager ( ) ;
	    AnnotationValue annotationValue = stager . stage ( value ) ;
	    P data = getData ( ) ;
	    R visit = visitor . visit ( annotationValue , data ) ;
	    return visit ;
	}

    /**
     * Gets the stager.
     *
     * @return the stager
     **/
    @ UseParameter
	abstract
	Stager < ? extends AnnotationValue , ? super A >
	getStager
	( ) ;

    /**
     * Gets the visitor.
     *
     * @return the visitor
     **/
    @ UseParameter
	abstract
	AnnotationValueVisitor < ? extends R , ? super P >
	getVisitor
	( ) ;

    /**
     * Gets the user data to be used on the visitor.
     *
     * @return user data
     **/
    @ UseParameter
	abstract P getData ( ) ;
}
