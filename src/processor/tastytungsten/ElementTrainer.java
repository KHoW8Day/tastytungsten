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
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * This works by chaining two element visitors together.
 * One the wrangler, wrangles up a bunch of elements.
 * The other, the visitor, processes each element.
 *
 * @param <R> the return type
 * @param <P> the data type
 * @param <A> the wrangler data type
 * @param <B> the visitor data type
 **/
abstract class ElementTrainer < R , P > implements Reader < List < ? extends R > , P >
{
    @ Override
	public List < ? extends R > read ( Iterator < ? extends P > input )
	{
	    Map < Boolean , Reader < ? extends List < ? extends R > , ? extends P > map = getMap ( ) ;
	    map . put ( true , trueVal ) ;
	    map . put ( false , falseVal ) ;
	    boolean hasNext = input . hasNext ( ) ;
	    Reader < ? extends List < ? extends R > , ? extends P > reader = map . get ( hasNext ) ;
	    List < ? extends R > read = reader . read ( input ) ;
	    return read ;
	}
}
