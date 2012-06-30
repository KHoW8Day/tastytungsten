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

/**
 * Converts a bunch of Ps to a bunch of Rs (lazily).
 *
 * @param <R> return type
 * @param <P> data type
 **/
abstract class IterableStager < R , P >
    implements Stager < Iterable < ? extends R > , Iterable < ? extends P > >
{
    /**
     * {@inheritDoc}.
     *
     * @param iterable {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public
	Iterable < ? extends R >
	stage
	( final Iterable < ? extends P > iterable )
	{
	    Stager < ? extends R , ? super P > stager = getStager ( ) ;
	    Iterable < ? extends R > stagerIterable =
		getStagerIterable ( iterable , stager ) ;
	    return stagerIterable ;
	}

    /**
     * For conversion.
     *
     * @return a stager to convert a P to an R.
     **/
    @ UseParameter
	abstract
	Stager < ? extends R , ? super P >
	getStager ( ) ;

    /**
     * Constructs an Iterable based on conversion.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param iterable source data
     * @param stager for conversion
     * @return an iterable based on conversion
     **/
    @ UseConstructor ( StagerIterable . class )
	abstract
	< R , P > Iterable < ? extends R >
	getStagerIterable
	(
	 Iterable < ? extends P > iterable ,
	 Stager < ? extends R , ? super P > stager
	 ) ;
}
