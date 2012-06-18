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

/**
 * Reduces Ps to one R.
 *
 * @param <R> output type
 * @param <P> input type
 **/
interface Reader < R , P >
{
    /**
     * Reduce a  list of Ps to one R.
     *
     * @param list the Ps to reduce
     * @return one R value
     **/
    R read ( Iterator < ? extends P > list ) ;
}
