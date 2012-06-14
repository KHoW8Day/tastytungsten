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





/**
 * Produces a value based on a mapper and a target.
 *
 * @param <P> user data type
 * @param <A> the reverser data type
 * @param <B> the secondary data type
 **/
abstract class AnnotationValuePunter < P , A , B >
    extends AbstractAnnotationValueVisitor < AnnotationValue , P , A , B >
{
    /**
     * Uses the mapper to produe a map and
     * then find the element based on the target.
     *
     * @param value {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	final
	AnnotationValue
	defaultAction
	( final AnnotationValue value , final B data )
	{
	    // CHECKSTYLE:OFF
	    AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super B > mapper =
		// CHECKSTYLE:ON
		getMapper ( ) ;
	    Map < ? extends String , ? extends AnnotationValue >  map =
		mapper . visit ( value , data ) ;
	    Object target = getTarget ( ) ;
	    String string = target . toString ( ) ;
	    AnnotationValue visit = map . get ( string ) ;
	    return visit ;
	}

    /**
     * Returns the mapper.
     *
     * @return the mapper
     **/
    @ UseParameter
	abstract
	// CHECKSTYLE:OFF
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super B >
	// CHECKSTYLE:ON
	getMapper ( ) ;

    /**
     * Returns the target of this hunt.
     *
     * @return the target
     **/
    @ UseParameter
	abstract Object getTarget ( ) ;

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
     * Gets the data for the wrangler.
     *
     * @param data user data
     * @return the data for the wrangler
     **/
    @ UseNull
	abstract A getA ( P data ) ;

    /**
     * Gets the data for the wrangler.
     *
     * @param data user data
     * @return the data for the wrangler
     **/
    @ UseNull
	abstract B getB ( P data ) ;
}
