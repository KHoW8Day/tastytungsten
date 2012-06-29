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
 * Connects 2 stagers.
 *
 * @param <R> the return type
 * @param <A> the join type
 * @param <P> the data type
 **/
abstract class SingleJoinStager < R , A , P >
    implements Stager < R , P >
{
    /**
     * Connects the first stager with the second stager.
     *
     * @param value {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public final R stage ( final P value )
    {
	Stager < ? extends R , ? super A > beta = getBeta ( ) ;
	Stager < ? extends A , ? super P > alpha = getAlpha ( ) ;
	A a = alpha . stage ( value ) ;
	R stage = beta . stage ( a ) ;
	return stage ;
    }

    /**
     * Gets the first stager.
     *
     * @return the first stager
     **/
    @ UseParameter
	abstract Stager < ? extends A , ? super P > getAlpha ( ) ;

    /**
     * Gets the second stager.
     *
     * @return the second stager
     **/
    @ UseParameter
	abstract Stager < ? extends R , ? super A > getBeta ( ) ;
}
