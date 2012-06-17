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
import javax . lang . model . element . ElementVisitor ;

/**
 * Writes the string constant method implementation body.
 **/
abstract class UseParameterClassParameterWriter
    implements Callable < StringBuilder >
{
    /**
     * Writes the string constant method implementation body.
     *
     * @return the method implementation text
     **/
    @ Override
	public StringBuilder call ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object privateConstant = getPrivateConstant ( ) ;
	stringBuilder . append ( privateConstant ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Object finalConstant = getFinalConstant ( ) ;
	stringBuilder . append ( finalConstant ) ;
	stringBuilder . append ( spaceConstant ) ;
	Callable < ? extends ElementVisitor < ? , ? super Object > >
	    useParameterTypeElementWranglerCallable =
	    getUseParameterTypeElementWranglerCallable ( ) ;
	ElementVisitor < ? , ? super Object > useParameterTypeElementWrangler =
	    useParameterTypeElementWranglerCallable . call ( ) ;
	Element element = getElement ( ) ;
	Object useParameterTypeElement =
	    useParameterTypeElementWrangler . visit ( element , null ) ;
	stringBuilder . append ( useParameterTypeElement ) ;
	Callable < ? extends ElementVisitor < ? , ? super Object > >
	    typeArgumentsElementWriterCallable =
	    getTypeArgumentsElementWriterCallable ( ) ;
	ElementVisitor < ? , ? super Object > typeArgumentsElementWriter =
	    typeArgumentsElementWriterCallable . call ( ) ;
	Object typeArguments =
	    typeArgumentsElementWriter . visit ( element , null ) ;
	stringBuilder . append ( typeArguments ) ;
	stringBuilder . append ( spaceConstant ) ;
	Object uniqueName = getUniqueName ( ) ;
	stringBuilder . append ( uniqueName ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	stringBuilder . append ( semicolonConstant ) ;
	return stringBuilder ;
    }

    /**
     * The element to be implemented.
     *
     * @return the method to be implemented.
     **/
    @ UseParameter
	abstract Element getElement ( ) ;

    /**
     * A guaranteed unique name.
     *
     * @return a guaranteed unique name
     **/
    @ UseParameter
	abstract Object getUniqueName ( ) ;

    /**
     * For writing type arguments.
     *
     * @param <P> user data type
     * @return a callable for writing type arguments
     **/
    @ UseConstructor ( TypeArgumentsElementWriterCallable . class )
	abstract < P > Callable < ? extends ElementVisitor < ? , ? super P > >
	getTypeArgumentsElementWriterCallable ( ) ;

    /**
     * For getting the type.
     *
     * @param <P> user data type
     * @return a Callable that can determine the type.
     **/
    @ UseConstructor (  UseParameterTypeElementWranglerCallable . class )
	abstract < P > Callable < ? extends ElementVisitor < ? , ? super P > >
	getUseParameterTypeElementWranglerCallable ( ) ;

    /**
     * Gets the private keyword.
     *
     * @return private
     **/
    @ UseStringConstant ( "private" )
	abstract Object getPrivateConstant ( ) ;

    /**
     * Gets the final keyword.
     *
     * @return the final keyword
     **/
    @ UseStringConstant ( "final" )
	abstract Object getFinalConstant ( ) ;

    /**
     * Gets the space character.
     *
     * @return a space character
     **/
    @ UseStringConstant ( " " )
	abstract Object getSpaceConstant ( ) ;

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
