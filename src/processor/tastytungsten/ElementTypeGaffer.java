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
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . SimpleTypeVisitor6 ;

/**
 * Connects 2 visitors.
 *
 * @param <R> the return type
 * @param <P> user data type
 * @param <A> wrangler type
 * @param <B> visitor type
 **/
abstract class ElementTypeGaffer < R , P , A , B >
    extends SimpleTypeVisitor6 < R , P >
{
    /**
     * Connects 2 visitors:
     * a type visitor (wrangler) that returns an element;
     * and an element visitor (visitor)
     * that acts on the results of the wrangler.
     *
     * @param type {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	protected
	R
	defaultAction
	( final TypeMirror type , final P data )
	{
	    ElementVisitor < ? extends R , ? super B > visitor =
		getVisitor ( ) ;
	    TypeVisitor < ? extends Element , ? super A > wrangler =
		getWrangler ( ) ;
	    A a = getA ( data ) ;
	    Element element = wrangler . visit ( type , a ) ;
	    B b = getB ( data ) ;
	    R visit = visitor . visit ( element , b ) ;
	    return visit ;
	}

    /**
     * Gets the wrangler.
     *
     * @return the wrangler
     **/
    @ UseParameter
	abstract TypeVisitor < ? extends Element , ? super A > getWrangler ( ) ;

    /**
     * Gets the visitor.
     *
     * @return the visitor
     **/
    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super B > getVisitor ( ) ;

    /**
     * Gets the wrangler data.
     *
     * @param data user data
     * @return wrangler data
     **/
    @ UseNull
	abstract A getA ( P data ) ;

    /**
     * Gets the visitor data.
     *
     * @param data user data
     * @return the visitor data
     **/
    @ UseNull
	abstract B getB ( P data ) ;
}
