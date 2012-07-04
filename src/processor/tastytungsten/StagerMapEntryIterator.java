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

/**
 * An iterator that produces map entries based
 * on underlying data.
 *
 * @param <K> the key type
 * @param <V> the value type
 * @param <P> the data type
 **/
abstract class StagerMapEntryIterator < K , V , P >
    implements Iterator < Map . Entry < K , V > >
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public boolean hasNext ( )
	{
	    Iterator < ? > iterator = getIterator ( ) ;
	    boolean hasNext = iterator . hasNext ( ) ;
	    return hasNext ;
	}

    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public Map . Entry < K , V > next ( )
	{
	    Iterator < ? extends P > iterator = getIterator ( ) ;
	    P p = iterator . next ( ) ;
	    Stager < ? extends K , ? super P > keyStager =
		getKeyStager ( ) ;
	    Stager < ? extends V , ? super P > valueStager =
		getValueStager ( ) ;
	    Map . Entry < K , V > next =
		getStagerMapEntry ( p , keyStager , valueStager ) ;
	    return next ;
	}

    /**
     * {@inheritDoc}.
     **/
    @ UseUnsupportedOperationException
	@ Override
	public abstract void remove ( ) ;

    /**
     * The data.
     *
     * @return an iterator of data
     **/
    @ UseParameter
	abstract Iterator < ? extends P > getIterator ( ) ;

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
     * Constructs a map entry.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param entry data
     * @param keyStager for the key
     * @param valueStager for the value
     * @return an entry
     **/
    @ UseConstructor ( StagerMapEntry . class )
	abstract
	< K , V , P >
	Map . Entry < K , V >
	getStagerMapEntry
	(
	 P entry ,
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;
}
