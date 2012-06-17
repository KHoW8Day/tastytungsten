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
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * A visitor that connects 2 visitors.
 *
 * @param <R> the return type of the visitor
 * @param <P> the data type of the visitor
 * @param <A> the wrangler data type
 * @param <B> the visitor data type
 **/
abstract class TypeElementGaffer < R , P , A , B >
    extends SimpleElementVisitor6 < R , P >
{
    /**
     * Connects 2 visitors - a wrangler and a visitor.
     *
     * @param element the element to visit
     * @param data user data
     * @return the result of the visitor
     **/
    @ Override
	protected R defaultAction ( final Element element , final P data )
	{
	    TypeVisitor < ? extends R , ? super B > visitor = getVisitor ( ) ;
	    ElementVisitor < ? extends TypeMirror , ? super A > wrangler =
		getWrangler ( ) ;
	    A a = getA ( data ) ;
	    TypeMirror type = wrangler . visit ( element , a ) ;
	    B b = getB ( data ) ;
	    R visit = visitor . visit ( type , b ) ;
	    return visit ;
	}

    /**
     * Get the wrangler.
     *
     * @return the wrangler
     **/
    @ UseParameter
	abstract
	ElementVisitor < ? extends TypeMirror , ? super A >
	getWrangler
	( ) ;

    /**
     * Get the visitor.
     *
     * @return the visitor
     **/
    @ UseParameter
	abstract TypeVisitor < ? extends R , ? super B > getVisitor ( ) ;

    /**
     * Get wrangler data.
     *
     * @param data user data
     * @return wrangler data
     **/
    @ UseNull
	abstract A getA ( P data ) ;

    /**
     * Get visitor data.
     *
     * @param data user data
     * @return visitor data
     **/
    @ UseNull
	abstract B getB ( P data ) ;
}
