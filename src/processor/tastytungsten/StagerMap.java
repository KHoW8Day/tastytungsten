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

import java . util . AbstractMap ;
import java . util . Collection ;
import java . util . Map ;
import java . util . Set ;

/**
 * A map based on underlying data.
 * The keys will be based on a keyStager
 * and the values will be based on a valueStager.
 *
 * @param <K> key type
 * @param <V> value type
 * @param <P> data type
 **/
abstract class StagerMap < K , V , P > extends AbstractMap < K , V >
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public final Set < Map . Entry < K , V > > entrySet ( )
	{
	    Collection < ? extends P > collection = getCollection ( ) ;
	    Stager < ? extends K , ? super P > keyStager = getKeyStager ( ) ;
	    Stager < ? extends V , ? super P > valueStager =
		getValueStager ( ) ;
	    Set < Map . Entry < K , V > > entrySet =
		getStagerMapEntrySet ( collection , keyStager , valueStager ) ;
	    return entrySet ;
	}

    /**
     * The data.
     *
     * @return a collection of data
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
     * Constructs a StagerMapEntrySet.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param collection underlying data
     * @param keyStager for the key
     * @param valueStager for the value
     * @return an entry set
     **/
    @ UseConstructor ( StagerMapEntrySet . class )
	abstract
	< K , V , P >
	Set < Map . Entry < K , V > >
	getStagerMapEntrySet
	(
	 Collection < ? extends P > collection ,
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;
}
