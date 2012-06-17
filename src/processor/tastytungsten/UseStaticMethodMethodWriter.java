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
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . Elements ;

/**
 * {@inheritDoc}.
 *
 * Writes a method implementation based on calling a constructor.
 **/
abstract class UseStaticMethodMethodWriter implements Callable < StringBuilder >
{
    /**
     * {@inheritDoc}.
     *
     * Writes a method implementation (body) based on calling a constructor.
     **/
    @ Override
	public StringBuilder call ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object returnConstant = getReturnConstant ( ) ;
	stringBuilder . append ( returnConstant ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Element element = getElement ( ) ;
	Object simpleName = element . getSimpleName ( ) ;
	stringBuilder . append ( simpleName ) ;
	Callable < ? extends ElementVisitor < ? , ? super Object > >
	    argumentsElementWriterCallable =
	    getArgumentsElementWriterCallable ( ) ;
	ElementVisitor < ? , ? super Object > argumentsElementWriter =
	    argumentsElementWriterCallable . call ( ) ;
	Object arguments = argumentsElementWriter . visit ( element , null ) ;
	stringBuilder . append ( arguments ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	stringBuilder . append ( semicolonConstant ) ;
	return stringBuilder ;
    }

    /**
     * Returns the return constant.
     *
     * @return return
     **/
    @ UseStringConstant ( "return" )
	abstract Object getReturnConstant (  );

    /**
     * Returns the semicolon constant.
     *
     * @return the semicolon
     **/
    @ UseStringConstant ( ";" )
	abstract Object getSemicolonConstant ( ) ;

    /**
     * Returns the space constant.
     *
     * @return space
     **/
    @ UseStringConstant ( " " )
	abstract Object getSpaceConstant ( ) ;

    /**
     * Used to write type parameters.
     *
     * @param <P> the data type
     * @return a callable that will write parameters.
     **/
    @ UseConstructor ( ArgumentsElementWriterCallable . class )
	abstract
	< P >
	Callable < ? extends ElementVisitor < ? , ? super P > >
	getArgumentsElementWriterCallable
	( ) ;

    /**
     * Return the StringBuilder for writing.
     *
     * @return the StringBuilder
     **/
    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    /**
     * This is used so for type parameters and parameters
     * and for the name of the static method.
     *
     * @return the element (not used)
     **/
    @ UseParameter
	abstract Element getElement ( ) ;

    /**
     * Gets the annotation value.
     * This is used for name of the class holding the static method.
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
}
