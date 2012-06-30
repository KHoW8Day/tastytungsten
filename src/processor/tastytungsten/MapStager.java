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

import java . util . Collection ;
import java . util . Map ;

/**
 * Creates a map based on a collection of data.
 * Provided keyStager and valueStager
 * turn the data into keys and values.
 *
 * @param <K> key type
 * @param <V> value type
 * @param <P> data type
 **/
abstract class MapStager < K , V , P >
    implements
	Stager < Map < ? extends K , ? extends V > , Collection < ? extends P > > //
{
    /**
     * {@inheritDoc}.
     *
     * @param collection {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public
	final
	Map < ? extends K , ? extends V >
	stage
	( final Collection < ? extends P > collection )
	{
	    Stager < ? extends K , ? super P > keyStager = getKeyStager ( ) ;
	    Stager < ? extends V , ? super P > valueStager =
		getValueStager ( ) ;
	    Map < ? extends K , ? extends V > stagerMap =
		getStagerMap ( collection , keyStager , valueStager ) ;
	    return stagerMap ;
	}

    /**
     * For the keys.
     *
     * @return a stager
     **/
    @ UseParameter
	abstract Stager < ? extends K , ? super P > getKeyStager ( ) ;

    /**
     * For the values.
     *
     * @return a stager
     **/
    @ UseParameter
	abstract Stager < ? extends V , ? super P > getValueStager ( ) ;

    /**
     * Constructs a stager map.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param collection data
     * @param keyStager for the keys
     * @param valueStager for the values
     * @return a map
     **/
    @ UseConstructor ( StagerMap . class )
	abstract
	< K , V , P >
	Map < ? extends K , ? extends V >
	getStagerMap
	(
	 Collection < ? extends P > collection ,
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;
}
