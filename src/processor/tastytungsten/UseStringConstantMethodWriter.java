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
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . util . Elements ;

/**
 * Writes the string constant method implementation body.
 **/
abstract class UseStringConstantMethodWriter
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
	Object returnConstant = getReturnConstant ( ) ;
	stringBuilder . append ( returnConstant ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Elements elementUtils = getElementUtils ( ) ;
	Callable < ? extends AnnotationValueVisitor < ? , ? super Object > >
	    annotatedConstantExpressionAnnotationValueWranglerCallable =
	    getAnnotatedConstantExpressionAnnotationValueWranglerCallable
	    ( elementUtils ) ;
	AnnotationValueVisitor < ? , ? super Object >
	    annotatedConstantExpressionAnnotationValueWrangler =
	    // CHECKSTYLE:OFF
	    annotatedConstantExpressionAnnotationValueWranglerCallable . call ( ) ;
	// CHECKSTYLE:ON
	AnnotationValue annotationValue = getAnnotationValue ( ) ;
	Object value =
	    annotatedConstantExpressionAnnotationValueWrangler . visit
	    ( annotationValue , null ) ;
	stringBuilder . append ( value ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	return stringBuilder ;
    }

    /**
     * Gets an annotation value visitor
     * that will wrangle the constant expression.
     *
     * @param <P> the data type
     * @param elementUtils for producing the constant expression
     * @return an annotation value visitor for wrangling the constant expression
     **/
    @ UseConstructor
	( AnnotatedConstantExpressionAnnotationValueWranglerCallable . class )
	abstract
	< P >
	// CHECKSTYLE:OFF
	Callable < ? extends AnnotationValueVisitor < ? extends String , ? super P > >
	// CHECKSTYLE:ON
	getAnnotatedConstantExpressionAnnotationValueWranglerCallable
	( Elements elementUtils ) ;

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

    /**
     * Gets the annotation for the string constant.
     *
     * @return the annotation value holding the string constant
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
