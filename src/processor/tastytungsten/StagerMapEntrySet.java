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
import java . util . Collection ;
import java . util . Map ;

/**
 * A set of map entries based on a collection of data.
 *
 * @param <K> key type
 * @param <V> value type
 * @param <P> data type
 **/
abstract class StagerMapEntrySet < K , V , P >
    extends AbstractSet < Map . Entry < K , V > >
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public final Iterator < Map . Entry < K , V > > iterator ( )
	{
	    Collection < ? extends P > collection = getCollection ( ) ;
	    Iterator < ? extends P > iterator = collection . iterator ( ) ;
	    Stager < ? extends K , ? super P > keyStager = getKeyStager ( ) ;
	    Stager < ? extends V , ? super P > valueStager =
		getValueStager ( ) ;
	    Iterator < Map . Entry < K , V > > stagerMapEntryIterator =
		getStagerMapEntryIterator
		( iterator , keyStager , valueStager ) ;
	    return stagerMapEntryIterator ;
	}

    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public final int size ( )
	{
	    Collection < ? > collection = getCollection ( ) ;
	    int size = collection . size ( ) ;
	    return size ;
	}

    /**
     * The underlying data.
     *
     * @return the underlying data
     **/
    @ UseParameter
	abstract Collection < ? extends P > getCollection ( ) ;

    /**
     * For the key.
     *
     * @return a stager
     **/
    @ UseParameter
	abstract Stager < ? extends K , ? super P > getKeyStager ( ) ;

    /**
     * For the value.
     *
     * @return a stager
     **/
    @ UseParameter
	abstract Stager < ? extends V , ? super P > getValueStager ( ) ;

    /**
     * Gets a StagerMapEntryIterator.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param <P> the data type
     * @param iterator the data
     * @param keyStager for the key
     * @param valueStager for the value
     * @return an iterator
     **/
    @ UseConstructor ( StagerMapEntryIterator . class )
	abstract
	< K , V , P >
	Iterator < Map . Entry < K , V > >
	getStagerMapEntryIterator
	(
	 Iterator < ? extends P > iterator ,
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;
}
