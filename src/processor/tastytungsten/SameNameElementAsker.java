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

/**
 * For testing if a name is the same.
 *
 * @param <P> user data type
 **/
abstract class SameNameElementAsker < P >
    extends AbstractElementVisitor < Boolean , P >
{
    /**
     * Tests if the candidate has the same name as the
     * simple name of the specified element.
     *
     * @param element {@inheritDoc}
     * @param data {@inheritDoc}
     * @return true iff names match
     **/
    @ Override
	protected
	Boolean
	defaultAction
	( final Element element , final P data )
	{
	    Object simpleName = element . getSimpleName ( ) ;
	    String string1 = simpleName . toString ( ) ;
	    Object candidate = getCandidate ( ) ;
	    String string2 = candidate . toString ( ) ;
	    boolean isSame = string1 . equals ( string2 ) ;
	    return isSame ;
	}

    /**
     * Gets the candidate to test.
     *
     * @return the candidate
     **/
    @ UseParameter
	abstract Object getCandidate ( ) ;
}
