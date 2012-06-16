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

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . Elements ;

/**
 * Writes the string constant method implementation body.
 **/
abstract class UseStringConstantMethodWriter implements Callable < StringBuilder >
{
    /**
     * Writes the string constant method implementation body.
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
	// CHECKSTYLE:OFF
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super Object >
	    // CHECKSTYLE:ON
	    elementValuesWithDefaultsAnnotationValueWrangler =
	    getElementValuesWithDefaultsAnnotationValueWrangler
	    ( elementUtils ) ;
	Object target = getTarget ( ) ;
	AnnotationValueVisitor < ? extends AnnotationValue , ? super Object >
	    punter =
	    getAnnotationValuePunter
	    ( elementValuesWithDefaultsAnnotationValueWrangler , target ) ;
	AnnotationValueVisitor < ? extends String , ? super Object >
	    stringAnnotationValueCaster =
	    getStringAnnotationValueCaster ( ) ;
	AnnotationValueVisitor < ? extends String , ? extends Object > gaffer =
	    getAnnotationValueAnnotationValueGaffer
	    ( punter , stringAnnotationValueCaster ) ;
	AnnotationValue annotationValue = getAnnotationValue ( ) ;
	String value = gaffer . visit ( annotationValue , null ) ;
	String constantExpression =
	    elementUtils . getConstantExpression ( value ) ;
	stringBuilder . append ( constantExpression ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	stringBuilder . append ( semicolonConstant ) ;
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
     * gets a element values with defaults wrangler.
     *
     * @param <P> the data type
     * @param elementUtils element utils
     * @return an element values with defaults wrangler
     **/
    @ UseConstructor
	( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract
	< P >
    // CHECKSTYLE:OFF
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P >
									      getElementValuesWithDefaultsAnnotationValueWrangler
									      ( final Elements elementUtils ) ;
    // CHECKSTYLE:ON

    /**
     * Gets a visitor to convert a string annotation to a string.
     *
     * @param <P> the data type
     * @return a string caster
     **/
    @ UseConstructor ( StringAnnotationValueCaster . class )
	abstract
	< P >
	AnnotationValueVisitor < ? extends String , ? super P >
					   getStringAnnotationValueCaster
					   ( ) ;

    /**
     * Gets an annotation value punter.
     *
     * @param <P> user data type
     * @param <A> other data type
     * @param <B> mapper data type
     * @param mapper the specified mapper
     * @param target the specified target
     * @return a punter
     **/
    @ UseConstructor ( AnnotationValuePunter . class )
	abstract
	< P , A , B >
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P >
					   getAnnotationValuePunter
					   (
					    // CHECKSTYLE:OFF
					    AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super B > mapper ,
					    // CHECKSTYLE:ON
					    Object target
					    ) ;

    /**
     * Gets a punter.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param <A> the wrangler's data type
     * @param <B> the visitor's data type
     * @param <C> other data type
     * @param <D> other data type
     * @param wrangler wrangles an annotation value
     * @param visitor visits an annotation
     * @return a punter
     **/
    @ UseConstructor ( AnnotationValueAnnotationValueGaffer . class )
	abstract
	< R , P , A , B , C , D >
	AnnotationValueVisitor < ? extends R , ? super P >
					   // CHECKSTYLE:OFF
					   getAnnotationValueAnnotationValueGaffer
					   // CHECKSTYLE:ON
					   (
					    // CHECKSTYLE:OFF
					    AnnotationValueVisitor < ? extends AnnotationValue , ? super C > wrangler ,
					    AnnotationValueVisitor < ? extends R , ? super D > visitor
					    // CHECKSTYLE:ON
					    ) ;

    /**
     * Gets the target.
     *
     * @return the target
     **/
    @ UseStringConstant ( "value" )
	abstract Object getTarget ( ) ;

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
}
