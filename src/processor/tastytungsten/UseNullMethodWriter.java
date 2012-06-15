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

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . Elements ;

/**
 * Writes the string constant method implementation body.
 **/
abstract class UseNullMethodWriter implements Runnable
{
    /**
     * Writes the string constant method implementation body.
     **/
    @ Override public void run ( )
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
    }

    /**
     * Return the StringBuilder for writing.
     *
     * @return the StringBuilder
     **/
    @ UseParameter
	abstract StringBuilder getStringBuilder ( ) ;

    /**
     * This is not used,
     * but provided
     * so that the constructor will have the right signature.
     *
     * @return the element (not used)
     **/
    @ UseParameter
	abstract Element getElement ( ) ;

    /**
     * Gets the annotation value.
     *
     * @return the annotation value
     **/
    @ UseParameter
	abstract AnnotationValue getAnnotationValue ( ) ;

    /**
     * Gets element utils.
     *
     * @return element utils
     **/
    @ UseParameter
	abstract Elements getElementUtils ( ) ;

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
}
