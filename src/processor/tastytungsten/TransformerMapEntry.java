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

/**
 * This map entry uses a keyTransformer and a valueTransformer to
 * get the key and value from an underlying data source.
 *
 * @param <K> key type
 * @param <V> value type
 * @param <P> underlying data type
 **/
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
	public final boolean equals ( final Object o )
	{
	    Class < ? > oClass = o . getClass ( ) ;
	    boolean isAssignableFrom = TransformerMapEntry . class . isAssignableFrom ( oClass ) ;
	    boolean equals = false ;
	    if ( isAssignableFrom )
		{
		    equals = equalsTransformerMapEntry ( o ) ;
		}
	    return equals ;
	}

    private boolean equalsTransformerMapEntry ( final Object o )
    {
	TransformerMapEntry < K , V , P > oo = TransformerMapEntry . class . cast ( o ) ;
	P entry1 = getEntry ( ) ;
	P entry2 = oo . getEntry ( ) ;
	boolean equals = entry1 . equals ( entry2 ) ;
	if ( equals )
	    {
		equals = equalsTransformerMapEntry ( oo , equals ) ;
	    }
	return equals ;
    }

    private boolean equalsTransformerMapEntry ( final TransformerMapEntry < K , V , P > o , final boolean equals1 )
    {
	assert equals1 ;
	Transformer < ? extends K , ? super P > keyTransformer1 = getKeyTransformer ( ) ;
	Transformer < ? extends K , ? super P > keyTransformer2 = o . getKeyTransformer ( ) ;
	boolean equals = keyTransformer1 . equals ( keyTransformer2 ) ;
	if ( equals )
	    {
		equals = equalsTransformerMapEntry ( o , equals1 , equals ) ;
	    }
	return equals ;
    }

    private boolean equalsTransformerMapEntry ( final TransformerMapEntry < K , V , P > o , final boolean equals1 , final boolean equals2 )
    {
	assert equals1 ;
	assert equals2 ;
	Transformer < ? extends V , ? super P > valueTransformer1 = getValueTransformer ( ) ;
	Transformer < ? extends V , ? super P > valueTransformer2 = o . getValueTransformer ( ) ;
	boolean equals = valueTransformer1 . equals ( valueTransformer2 ) ;
	return equals ;
    }

    @ Override
	public int hashCode ( )
	{
	    P entry = getEntry ( ) ;
	    int hashCode = entry . hashCode ( ) ;
	    return hashCode ;
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
