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
import java . util . List ;
import java . util . Map ;

/**
 * Converts one list to another.
 *
 * @param <R> the return type
 * @param <P> the data type
 **/
abstract class IteratorAlphaStager < R , P >
    implements Stager < List < ? extends R > , Iterator < ? extends P > >
{
    /**
     * Manages recursion.
     *
     * @param iterator the iterator to process
     * @return converted values
     **/
    @ Override
	public
	List < ? extends R >
	stage
	( final Iterator < ? extends P > iterator )
	{
	    Map < ? extends Boolean , ? extends Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > > //
		map =
		getMap ( ) ;
	    boolean hasNext = iterator . hasNext ( ) ;
	    Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > //
		stager =
		map . get ( hasNext ) ;
	    List < ? extends R > stage = stager . stage ( iterator ) ;
	    return stage ;
	}

    /**
     * For recursion.
     *
     * @return a map of stagers
     *         true = stager for recursion
     *         false = stager for stopping
     **/
    @ UseParameter
	abstract
	Map < ? extends Boolean , ? extends Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > > //
	getMap ( ) ;
}
