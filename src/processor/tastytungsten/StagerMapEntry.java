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
 * This map entry uses a keyStager and a valueStager to
 * get the key and value from an underlying data source.
 *
 * @param <K> key type
 * @param <V> value type
 * @param <P> underlying data type
 **/
abstract class StagerMapEntry < K , V , P >
    implements Map . Entry < K , V >
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public final K getKey ( )
	{
	    Stager < ? extends K , ? super P > keyStager = getKeyStager ( ) ;
	    P entry = getEntry ( ) ;
	    K key = keyStager . stage ( entry ) ;
	    return key ;
	}

    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public final V getValue ( )
	{
	    Stager < ? extends V , ? super P > valueStager =
		getValueStager ( ) ;
	    P entry = getEntry ( ) ;
	    V value = valueStager . stage ( entry ) ;
	    return value ;
	}

    /**
     * {@inheritDoc}.
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public final boolean equals ( final Object o )
	{
	    Class < ? > oClass = o . getClass ( ) ;
	    boolean isAssignableFrom =
		StagerMapEntry . class . isAssignableFrom ( oClass ) ;
	    boolean equals = false ;
	    if ( isAssignableFrom )
		{
		    equals = equalsStagerMapEntry ( o ) ;
		}
	    return equals ;
	}

    /**
     * Tests for equality by looking at the data.
     *
     * @param o the other object
     * @return true iff equal on data, keystager, valuestager
     **/
    private
	boolean
	equalsStagerMapEntry
	( final Object o )
    {
	StagerMapEntry < K , V , P > oo = StagerMapEntry . class . cast ( o ) ;
	P entry1 = getEntry ( ) ;
	P entry2 = oo . getEntry ( ) ;
	boolean equals = entry1 . equals ( entry2 ) ;
	if ( equals )
	    {
		equals = equalsStagerMapEntry ( oo , equals ) ;
	    }
	return equals ;
    }

    /**
     * Tests for equality by looking at the key stager.
     *
     * @param o the other object
     * @param equals1 (assumed to be true)
     * @return true iff equal on data, keystager, valuestager
     **/
    private
	boolean
	equalsStagerMapEntry
	(
	 final StagerMapEntry < K , V , P > o ,
	 final boolean equals1
	 )
    {
	assert equals1 ;
	Stager < ? extends K , ? super P > keyStager1 = getKeyStager ( ) ;
	Stager < ? extends K , ? super P > keyStager2 = o . getKeyStager ( ) ;
	boolean equals =
	    keyStager1 . equals ( keyStager2 ) ;
	if ( equals )
	    {
		equals = equalsStagerMapEntry ( o , equals1 , equals ) ;
	    }
	return equals ;
    }

    /**
     * Tests for equality by looking at the value stager.
     *
     * @param o the other object
     * @param equals1 (assumed to be true)
     * @param equals2 (assumed to be true)
     * @return true iff equal on data, keystager, valuestager
     **/
    private
	boolean
	equalsStagerMapEntry
	(
	 final StagerMapEntry < K , V , P > o ,
	 final boolean equals1 ,
	 final boolean equals2
	 )
    {
	assert equals1 ;
	assert equals2 ;
	Stager < ? extends V , ? super P > valueStager1 = getValueStager ( ) ;
	Stager < ? extends V , ? super P > valueStager2 =
	    o . getValueStager ( ) ;
	boolean equals = valueStager1 . equals ( valueStager2 ) ;
	return equals ;
    }

    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public int hashCode ( )
	{
	    P entry = getEntry ( ) ;
	    int hashCode = entry . hashCode ( ) ;
	    return hashCode ;
	}

    /**
     * {@inheritDoc}.
     *
     * Unsupported.
     *
     * @param {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public abstract V setValue ( V value ) ;

    /**
     * The underlying data.
     *
     * @return data
     **/
    @ UseParameter
	abstract P getEntry ( ) ;

    /**
     * For determing the key.
     *
     * @return a key stager
     **/
    @ UseParameter
	abstract Stager < ? extends K , ? super P > getKeyStager ( ) ;

    /**
     * For determining the value.
     *
     * @return a value stager
     **/
    @ UseParameter
	abstract Stager < ? extends V , ? super P > getValueStager ( ) ;
}
