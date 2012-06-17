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

import javax . lang . model . element . Element ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * This is necessary because the constructors in SimpleElementVisitor6
 * are protected.
 *
 * @param <R> {@inheritDoc}
 * @param <P> {@inheritDoc}
 **/
abstract class AbstractElementVisitor < R , P >
    extends SimpleElementVisitor6 < R , P >
{
    /**
     * {@inheritDoc}.
     *
     * @param element {@inheritDoc}
     * @param data {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	@ UseParameter
	protected abstract R defaultAction ( Element element , P data ) ;
}
