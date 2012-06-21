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
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;

/**
 * Connects two visitors.
 *
 * @param <R> the return type
 * @param <P> user data type
 * @param <A> secondary data type
 * @param <B> secondary data type
 * @param <C> wrangler type
 * @param <D> visitor type
 **/
abstract class ElementAnnotationValueGaffer < R , P , A , B , C , D >
    extends AbstractAnnotationValueVisitor < R , P , A , B >
{
    /**
     * Connects two visitors:
     * an AnnotationValueVisitor (wrangler) that return an Element; and
     * an ElementVisitor (visitor) that acts on the result of the wrangler.
     *
     * @param value {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	R
	defaultAction
	( final AnnotationValue value , final B data )
	{
	    ElementVisitor < ? extends R , ? super D > visitor =
		getVisitor ( ) ;
	    AnnotationValueVisitor < ? extends Element , ? super C >
		wrangler =
		getWrangler ( ) ;
	    C c = getC ( data ) ;
	    Element element = wrangler . visit ( value , c ) ;
	    D d = getD ( data ) ;
	    R visit = visitor . visit ( element , d ) ;
	    return visit ;
	}

    /**
     * gets the wrangler.
     *
     * @return the wrangler
     **/
    @ UseParameter
	abstract
	AnnotationValueVisitor < ? extends Element , ? super C >
	getWrangler
	( ) ;

    /**
     * Gets the visitor.
     *
     * @return the visitor
     **/
    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super D > getVisitor ( ) ;

    /**
     * Get the wrangler data.
     *
     * @param data secondary data
     * @return visitor data
     **/
    @ UseNull
	abstract C getC ( B data ) ;

    /**
     * Get the visitor data.
     *
     * @param data secondary data
     * @return visitor data
     **/
    @ UseNull
	abstract D getD ( B data ) ;

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
