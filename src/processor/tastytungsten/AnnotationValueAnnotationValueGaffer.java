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
 * Combines two visitors into one - piping the results of one into the other.
 *
 * @param <R> {@inheritDoc}
 * @param <P> {@inheritDoc}
 * @param <A> {@inheritDoc}
 * @param <B> {@inheritDoc}
 * @param <C> the data type of the wrangler
 * @param <D> the data type of the visitor
 **/
abstract class AnnotationValueAnnotationValueGaffer < R , P , A , B , C , D >
    extends AbstractAnnotationValueVisitor < R , P , A , B >
{
    /**
     * Combines a wrangler and a visitor into one.
     *
     * @param value {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	final R defaultAction ( final AnnotationValue value , final B data )
	{
	    AnnotationValueVisitor < ? extends R , ? super D > visitor =
		getVisitor ( ) ;
	    AnnotationValueVisitor < ? extends AnnotationValue , ? super C > wrangler =
		getWrangler ( ) ;
	    C c = getC ( data ) ;
	    AnnotationValue v = wrangler . visit ( value , c ) ;
	    D d = getD ( data ) ;
	    R visit = visitor . visit ( v , d ) ;
	    return visit ;
	}

    /**
     * Gets the wrangler.
     *
     * @return wrangler
     **/
    @ UseParameter
	abstract
	AnnotationValueVisitor < ? extends AnnotationValue , ? super C >
	getWrangler
	( ) ;

    /**
     * Gets the visitor.
     *
     * @return the visitor
     **/
    @ UseParameter
	abstract
	AnnotationValueVisitor < ? extends R , ? super D >
	getVisitor
	( ) ;

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

    /**
     * Gets the data for the wrangler.
     *
     * @param data user data
     * @return the data for the wrangler
     **/
    @ UseNull
	abstract C getC ( B data ) ;

    /**
     * Gets the data for the visitor.
     *
     * @param data user data
     * @return the data for the visitor
     **/
    @ UseNull
	abstract D getD ( B data ) ;
}