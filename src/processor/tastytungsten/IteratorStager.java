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
import java . util . Collection ;
import java . util . HashMap ;
import java . util . List ;
import java . util . Map ;

/**
 * Converts a bunch of Ps into a bunch of Rs.
 *
 * @param <R> the return type (wrapped in a list)
 * @param <P> the data type (wrapped in a list)
 **/
abstract class IteratorStager < R , P >
    implements Stager < List < ? extends R > , Iterator < ? extends P > >
{
    /**
     * Converts a bunch of Ps into a bunch of Rs by applying
     * the specified stager.
     *
     * @param value the bunch of Ps
     * @return a bunch of Rs
     **/
    @ Override
	public final List < ? extends R > stage ( final Iterator < ? extends P > value ) //
	{
	    Map < Boolean , Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > > //
		map =
		getMap ( ) ;
	    Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > //
		iteratorAlphaStager =
		getIteratorAlphaStager ( map ) ;
	    Stager < ? extends R , ? super P > stager = getStager ( ) ;
	    Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > //
		iteratorBetaStager =
		getIteratorBetaStager ( stager , iteratorAlphaStager ) ;
	    Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > //
		iteratorGammaStager =
		getIteratorGammaStager ( ) ;
	    map . put ( true , iteratorBetaStager ) ;
	    map . put ( false , iteratorGammaStager ) ;
	    List < ? extends R > stage = iteratorAlphaStager . stage ( value ) ;
	     return stage ;
	}

    /**
     * Gets a stager that turns a P into an R.
     *
     * @return an item stager
     **/
    @ UseParameter
	abstract Stager < ? extends R , ? super P > getStager ( ) ;

    /**
     * Constructs a map.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a new K,V map
     **/
    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;

    /**
     * Gets the stager that will start the recursion.
     *
     * @param map the recursion map
     * @return a stager that will delegate to one of two
     *         other stagers
     **/
    @ UseConstructor ( IteratorAlphaStager . class )
	abstract
	Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > //
	getIteratorAlphaStager
	(
	 Map < ? extends Boolean , ? super Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > > //
	 map
	 ) ;

    /**
     * Gets the stager that will continue recursion.
     *
     * @param stager the stager that will do item conversion
     * @param iteratorAlphaStager for recursion
     * @return a stager that assumes
     *         there is at least one more item in the iterator.
     **/
    @ UseConstructor ( IteratorBetaStager . class )
	abstract
	Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > //
	getIteratorBetaStager
	(
	 Stager < ? extends R , ? super P > stager ,
	 Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > //
	 iteratorAlphaStager
	 ) ;

    /**
     * Gets the stager that will stop recursion.
     *
     * @return a stager that will return an empty list
     **/
    @ UseConstructor ( IteratorGammaStager . class )
	abstract
	Stager < ? extends List < ? extends R > , ? super Iterator < ? extends P > > //
	getIteratorGammaStager
	( ) ;
}
