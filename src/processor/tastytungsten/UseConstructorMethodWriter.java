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
abstract class UseConstructorMethodWriter implements Callable < StringBuilder >
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
	Object newConstant = getNewConstant ( ) ;
	stringBuilder . append ( newConstant ) ;
	stringBuilder . append ( spaceConstant ) ;

	Element element = getElement ( ) ;
	Callable < ? extends ElementVisitor < ? , ? super Object > >
	    typeArgumentsElementWriterCallable =
	    getTypeArgumentsElementWriterCallable ( ) ;
	ElementVisitor < ? , ? super Object > typeArgumentsElementWriter =
	    typeArgumentsElementWriterCallable . call ( ) ;
	Object typeArguments =
	    typeArgumentsElementWriter . visit ( element , null ) ;
	stringBuilder . append ( typeArguments ) ;
	Callable < ? extends ElementVisitor < ? , ? super Object > >
	    argumentsElementWriterCallable =
	    getTypeArgumentsElementWriterCallable ( ) ;
	ElementVisitor < ? , ? super Object > argumentsElementWriter =
	    argumentsElementWriterCallable . call ( ) ;
	Object arguments = argumentsElementWriter . visit ( element , null ) ;
	stringBuilder . append ( arguments ) ;
	return stringBuilder ;
    }

    /**
     * Return the StringBuilder for writing.
     *
     * @return the StringBuilder
     **/
    @ UseConstructor ( StringBuilder . class )
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
     * Returns the return constant.
     *
     * @return return
     **/
    @ UseStringConstant ( "return" )
	abstract Object getReturnConstant (  );

    /**
     * Returns the space constant.
     *
     * @return space
     **/
    @ UseStringConstant ( " " )
	abstract Object getSpaceConstant ( ) ;

    /**
     * Returns the new constant.
     *
     * @return new
     **/
    @ UseStringConstant ( "new" )
	abstract Object getNewConstant ( ) ;

    /**
     * Returns the semicolon constant.
     *
     * @return the semicolon
     **/
    @ UseStringConstant ( ";" )
	abstract Object getSemicolonConstant ( ) ;

    /**
     * Used to write type parameters.
     *
     * @param <P> the data type
     * @return a callable that will write parameters.
     **/
    @ UseConstructor ( TypeArgumentsElementWriterCallable . class )
	abstract
	< P >
	Callable < ? extends ElementVisitor < ? , ? super P > >
	getTypeArgumentsElementWriterCallable
	( ) ;

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
}
