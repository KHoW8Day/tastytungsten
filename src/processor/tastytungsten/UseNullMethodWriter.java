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
 * Writes the string constant method implementation body.
 **/
abstract class UseNullMethodWriter implements Callable < StringBuilder >
{
    /**
     * Writes the string constant method implementation body.
     *
     * @return a method implementation with return null
     **/
    @ Override public StringBuilder call ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object returnConstant = getReturnConstant ( ) ;
	stringBuilder . append ( returnConstant ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Object nullConstant = getNullConstant ( ) ;
	stringBuilder . append ( nullConstant ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	stringBuilder . append ( semicolonConstant ) ;
	return stringBuilder ;
    }

    /**
     * Gets the return keyword.
     *
     * @return the return keyword
     **/
    @ UseStringConstant ( "return" )
	abstract Object getReturnConstant ( ) ;

    /**
     * Gets the space character.
     *
     * @return a space character
     **/
    @ UseStringConstant ( " " )
	abstract Object getSpaceConstant ( ) ;

    /**
     * Gets the space character.
     *
     * @return a space character
     **/
    @ UseStringConstant ( "null" )
	abstract Object getNullConstant ( ) ;

    /**
     * Gets the semicolon character.
     *
     * @return the semicolon character
     **/
    @ UseStringConstant ( ";" )
	abstract Object getSemicolonConstant ( ) ;

    /**
     * Return the StringBuilder for writing.
     *
     * @return the StringBuilder
     **/
    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;
}
