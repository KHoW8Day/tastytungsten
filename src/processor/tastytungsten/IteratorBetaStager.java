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

import java . util . ArrayList ;
import java . util . Collection ;
import java . util . Iterator ;
import java . util . List ;

/**
 * Convert one element and recurse.
 *
 * @param <R> return type
 * @param <P> data type
 **/
abstract class IteratorBetaStager < R , P >
    implements Stager < List < ? extends R > , Iterator < ? extends P > >
{
    /**
     * Assuming that there is at least one element left in the iterator,
     * convert it and recurse.
     *
     * @param iterator an iterator with at least one element left
     * @return a converted list
     **/
    @ Override
	public
	List < ? extends R >
	stage
	( final Iterator < ? extends P > iterator )
	{
	    List < R > list = getList ( ) ;
	    Stager < ? extends R , ? super P > stager = getStager ( ) ;
	    P p = iterator . next ( ) ;
	    R r = stager . stage ( p ) ;
	    list . add ( r ) ;
	    Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > //
		iteratorAlphaStager =
		getIteratorAlphaStager ( ) ;
	    Collection < ? extends R > stage =
		iteratorAlphaStager . stage ( iterator ) ;
	    list . addAll ( stage ) ;
	    return list ;
	}

    /**
     * For item conversion.
     *
     * @return a stager that will convert an item
     **/
    @ UseParameter
	abstract Stager < ? extends R , ? super P > getStager ( ) ;

    /**
     * For recursion.
     *
     * @return a stager that will recurse
     **/
    @ UseParameter
	abstract
	Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > //
	getIteratorAlphaStager
	( ) ;

    /**
     * Constructs an ArrayList.
     *
     * @param <T> the list type
     * @return a new List.
     **/
    @ UseConstructor ( ArrayList . class )
	abstract < T > List < T > getList ( ) ;
}
