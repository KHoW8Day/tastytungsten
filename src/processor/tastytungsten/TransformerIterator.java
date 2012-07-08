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

abstract class TransformerIterator < R , P > implements Iterator < R >
{
    @ Override
	public final boolean hasNext ( )
	{
	    Iterator < ? > iterator = getIterator ( ) ;
	    boolean hasNext = iterator . hasNext ( ) ;
	    return hasNext ;
	}
    
    @ Override
	public final R next ( )
	{
	    Transformer < ? extends R , ? super P > transformer = getTransformer ( ) ;
	    Iterator < ? extends P > iterator = getIterator ( ) ;
	    P p = iterator . next ( ) ;
	    R r = transformer . transform ( p ) ;
	    return r ;
	}

    @ Override
	@ UseUnsupportedOperationException
	public abstract void remove ( ) ;

    @ UseParameter
	abstract Iterator < ? extends P > getIterator ( ) ;

    @ UseParameter
	abstract Transformer < ? extends R , ? super P > getTransformer ( ) ;
}
