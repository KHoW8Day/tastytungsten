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

import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * Connects 2 element visitors:
 * a wrangler (that returns an element)
 * and a visitor (that acts on the element returned by the wrangler).
 *
 * @param <R> the return type
 * @param <P> the data type
 * @param <A> the wrangler type
 * @param <B> the visitor type
 **/
abstract class ElementElementGaffer < R , P , A , B >
    extends SimpleElementVisitor6 < R , P >
{
    /**
     * Connects 2 element visitors.
     *
     * @param element {@inheritDoc}
     * @param data {@inheritDoc}
     * @return the result of the visitor
     **/
    @ Override
	protected
	R
	defaultAction
	( final Element element , final P data )
	{
	    ElementVisitor < ? extends R , ? super B > visitor =
		getVisitor ( ) ;
	    ElementVisitor < ? extends Element , ? super A > wrangler =
		getWrangler ( ) ;
	    A a = getA ( data ) ;
	    Element e = wrangler . visit ( element , a ) ;
	    B b = getB ( data ) ;
	    R visit = visitor . visit ( e , b ) ;
	    return visit ;
	}

    /**
     * Gets the wrangler.
     *
     * @return the wrangler
     **/
    @ UseParameter
	abstract
	ElementVisitor < ? extends Element , ? super A >
	getWrangler ( ) ;

    /**
     * Gets the visitor.
     *
     * @return the visitor
     **/
    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super B > getVisitor ( ) ;

    /**
     * Gets wrangler data.
     *
     * @param data user data
     * @return wrangler data
     **/
    @ UseNull
	abstract A getA ( P data ) ;

    /**
     * Gets visitor data.
     *
     * @param data user data
     * @return visitor data
     **/
    @ UseNull
	abstract B getB ( P data ) ;
}
