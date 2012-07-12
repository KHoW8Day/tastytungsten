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

import java . util . Map ;

@ SuppressWarnings ( "unchecked" )
    abstract class TransformerMapEntry < K , V , P > implements Map . Entry < K , V >
{
    @ Override
	public final K getKey ( )
	{
	    Transformer < ? extends K , ? super P > keyTransformer = getKeyTransformer ( ) ;
	    P entry = getEntry ( ) ;
	    K key = keyTransformer . transform ( entry ) ;
	    return key ;
	}

    @ Override
	public final V getValue ( )
	{
	    Transformer < ? extends V , ? super P > valueTransformer = getValueTransformer ( ) ;
	    P entry = getEntry ( ) ;
	    V value = valueTransformer . transform ( entry ) ;
	    return value ;
	}

    @ Override
	@ UseUnsupportedOperationException
	public abstract V setValue ( V value ) ;

    @ UseParameter
	abstract P getEntry ( ) ;

    @ UseParameter
	abstract Transformer < ? extends K , ? super P > getKeyTransformer ( ) ;

    @ UseParameter
	abstract Transformer < ? extends V , ? super P > getValueTransformer ( ) ;
}
