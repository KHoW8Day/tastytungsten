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

import java . util . Collections ;
import java . util . Iterator ;
import java . util . List ;

/**
 * For stopping recursion.
 *
 * @param <R> the return type
 * @param <P> the data type
 **/
abstract class IteratorGammaStager < R , P >
    implements Stager < List < ? extends R > , Iterator < ? extends P > >
{
    /**
     * Always returns an empty list.
     *
     * This is suitable if the specified iterator is empty.
     *
     * (Stop recursion).
     *
     * @param iterator an (empty) list
     * @return an empty list
     **/
    @ Override
	public
	List < ? extends R >
	stage
	( final Iterator < ? extends P > iterator )
	{
	    List < ? extends R > stage = emptyList ( ) ;
	    return stage ;
	}

    /**
     * Gets an empty list.
     *
     * @param <T> the type of list
     * @return an empty list
     **/
    @ UseStaticMethod ( Collections . class )
	abstract < T > List < T > emptyList ( ) ;
}
