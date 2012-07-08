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

import java . util . AbstractSet ;
import java . util . Iterator ;
import java . util . Set ;
import java . util . Collection ;

abstract class TransformerSet < R , P > extends AbstractSet < R >
{
    @ Override
	public final int size ( )
	{
	    final Collection < ? > collection = getCollection ( ) ;
	    int size = collection . size ( ) ;
	    return size ;
	}
    
    @ Override
	public final Iterator < R > iterator ( )
	{
	    final Collection < ? extends P > collection = getCollection ( ) ;
	    Iterator < ? extends P > iterator = collection . iterator ( ) ;
	    final Transformer < ? extends R , ? super P > transformer = getTransformer ( ) ;
	    Iterator < R > transformerIterator = getTransformerIterator ( iterator , transformer ) ;
	    return transformerIterator ;
	}

    @ UseParameter
	abstract Collection < ? extends P > getCollection ( ) ;

    @ UseParameter
	abstract Transformer < ? extends R , ? super P > getTransformer ( ) ;

    @ UseConstructor ( TransformerIterator . class )
	abstract < R , P > Iterator < R > getTransformerIterator ( Iterator < ? extends P > iterator , Transformer < ? extends R , ? super P > transformer ) ;
}
