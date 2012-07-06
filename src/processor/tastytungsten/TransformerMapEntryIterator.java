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
import java . util . Map ;

abstract class TransformerMapEntryIterator < K , V , P > implements Iterator < Map . Entry < K , V > >
{
    @ Override
	public boolean hasNext ( )
	{
	    Iterator < ? > iterator = getIterator ( ) ;
	    boolean hasNext = iterator . hasNext ( ) ;
	    return hasNext ;
	}

    @ Override
	public Map . Entry < K , V > next ( )
	{
	    Iterator < ? extends P > iterator = getIterator ( ) ;
	    P p = iterator . next ( ) ;
	    Transformer < ? extends K , ? super P > keyTransformer = getKeyTransformer ( ) ;
	    Transformer < ? extends V , ? super P > valueTransformer = getValueTransformer ( ) ;
	    Map . Entry < K , V > next = getTransformerMapEntry ( p , keyTransformer , valueTransformer ) ;
	    return next ;
	}

    @ UseUnsupportedOperationException
	@ Override
	public abstract void remove ( ) ;

    @ UseParameter
	abstract Iterator < ? extends P > getIterator ( ) ;

    @ UseParameter
	abstract Transformer < ? extends K , ? super P > getKeyTransformer ( ) ;

    @ UseParameter
	abstract Transformer < ? extends V , ? super P > getValueTransformer ( ) ;

    @ UseConstructor ( TransformerMapEntry . class )
	abstract < K , V , P > Map . Entry < K , V > getTransformerMapEntry ( P entry , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
}
