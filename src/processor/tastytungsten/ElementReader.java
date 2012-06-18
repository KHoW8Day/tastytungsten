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

import java . util . Iterator ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;

/**
 * Connects an element visitor (lister) with a reader.
 *
 * The lister produces a list and the reader reduces it to one element.
 *
 * @param <R> the return type of both this and the reader
 * @param <P> the data type of this
 * @param <A> this wrapped in an Iterable is the return type of the lister
 * @param <B> the data type of the lister
 **/
abstract class ElementReader < R , P , A , B >
    extends AbstractElementVisitor < R , P >
{
    /**
     * Connects an element visitor (lister) with a reader.
     *
     * @param element {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	protected R defaultAction ( final Element element , final P data )
	{
	    Reader < ? extends R , ? super A > reader = getReader ( ) ;
	    ElementVisitor < ? extends Iterable < ? extends A > , ? super B >
		lister =
		getLister ( ) ;
	    B b = getB ( data ) ;
	    Iterable < ? extends A > list = lister . visit ( element , b ) ;
	    Iterator < ? extends A > iterator = list . iterator ( ) ;
	    R visit = reader . read ( iterator ) ;
	    return visit ;
	}

    /**
     * For fetching a list.
     *
     * @return a lister
     **/
    @ UseParameter
	abstract
	ElementVisitor < ? extends Iterable < ? extends A > , ? super B >
	getLister
	( ) ;

    /**
     * For reducing a list.
     *
     * @return a reader
     **/
    @ UseParameter
	abstract Reader < ? extends R , ? super A > getReader ( ) ;

    /**
     * Gets lister data.
     *
     * @param data user data
     * @return lister data
     **/
    @ UseNull
	abstract B getB ( P data ) ;
}
