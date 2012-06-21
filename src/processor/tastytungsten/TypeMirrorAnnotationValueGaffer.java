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
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;

/**
 * Combines two visitors.
 *
 * @param <R> the return type of the combined visitor
 * @param <P> the data type of the combined visitor
 * @param <A> secondary data type
 * @param <B> secondary data type
 * @param <C> wrangler data type
 * @param <D> visitor data type
 **/
abstract class TypeMirrorAnnotationValueGaffer < R , P , A , B , C , D >
    extends AbstractAnnotationValueVisitor < R , P , A , B >
{
    /**
     * Combines a AnnotationValueVisitor that return a TypeMirror (wrangler)
     * with a TypeVisitor (visitor).
     *
     * @param value the specified annotation value
     * @param data user data
     * @return the visitor's result
     **/
    @ Override
	R defaultAction ( final AnnotationValue value , final B data )
	{
	    TypeVisitor < ? extends R , ? super D > visitor = getVisitor ( ) ;
	    AnnotationValueVisitor < ? extends TypeMirror , ? super C >
		wrangler =
		getWrangler ( ) ;
	    C c = getC ( data ) ;
	    TypeMirror type = wrangler . visit ( value , c ) ;
	    D d = getD ( data ) ;
	    R visit = visitor . visit ( type , d ) ;
	    return visit ;
	}

    /**
     * Gets the wrangler.
     *
     * @return the wrangler
     **/
    @ UseParameter
	abstract
	AnnotationValueVisitor < ? extends TypeMirror , ? super C >
	getWrangler
	( ) ;

    /**
     * Gets the visitor.
     *
     * @return the visitor
     **/
    @ UseParameter
	abstract TypeVisitor < ? extends R , ? super D > getVisitor ( ) ;

    /**
     * Gets a reverser or upcaster.
     *
     * @return an annotation value reverser
     **/
    @ Override
	@ UseConstructor ( AnnotationValueReverser . class )
	abstract
	< P >
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P >
	getAnnotationValueReverser
	( ) ;

    /**
     * Gets user data for the wrangler.
     *
     * @param data user data
     * @return user data for the wrangler
     **/
    @ UseNull
	abstract C getC ( B data ) ;

    /**
     * Gets user data for the visitor.
     *
     * @param data user data
     * @return user data for the visitor
     **/
    @ UseNull
	abstract D getD ( B data ) ;

    /**
     * Gets the data for the reverser.
     *
     * @param data user data
     * @return the data
     **/
    @ Override
	@ UseNull
	abstract A getA ( P data ) ;

    /**
     * Gets the data for the secondary visit.
     *
     * @param data user data
     * @return the data
     **/
    @ Override
	@ UseNull
	abstract B getB ( P data ) ;
}
