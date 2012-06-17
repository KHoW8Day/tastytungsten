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
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . Elements ;

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
	TypeVisitor < ? extends Element , ? super Object > elementTypeWrangler = getElementTypeWrangler ( ) ;
	ElementVisitor < ? , ? super Object > qualifiedNameElementWrangler = getQualifiedNameElementWrangler ( ) ;
	TypeVisitor < ? , ? super Object > elementTypeGaffer = getElementTypeGaffer ( elementTypeWrangler , qualifiedNameElementWrangler ) ;

	ElementVisitor < ? extends TypeMirror , ? super Object > returnTypeElementWrangler = getReturnTypeElementWrangler ( ) ;
	
	Element element = getElement ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Object simpleName = element . getSimpleName ( ) ;
	stringBuilder . append ( simpleName ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	stringBuilder . append ( semicolonConstant ) ;
	return stringBuilder ;
    }

    @ UseParameter
	abstract Element getElement ( ) ;

    @ UseConstructor ( ElementTypeWrangler . class )
	abstract < P > TypeVisitor < ? extends Element , ? super P > getElementTypeWrangler ( ) ;

    @ UseConstructor ( ElementTypeGaffer . class )
	abstract < R , P , A , B > TypeVisitor < ? extends R , ? super P > getElementTypeGaffer ( TypeVisitor < ? extends Element , ? super A > wrangler , ElementVisitor < ? extends R , ? super B > visitor ) ;

    @ UseConstructor ( TypeElementGaffer . class )
	abstract < R , P , A , B > ElementVisitor < ? extends R , ? super P > getTypeElementGaffer ( ElementVisitor < ? extends TypeMirror , ? super A > wrangler , TypeVisitor < ? extends R , ? super B > visitor ) ;

    @ UseConstructor ( QualifiedNameElementWrangler . class )
	abstract < P > ElementVisitor < ? , ? super P > getQualifiedNameElementWrangler ( ) ;

    @ UseConstructor ( ReturnTypeElementWrangler . class )
	abstract < P > ElementVisitor < TypeMirror , ? super P > getReturnTypeElementWrangler ( ) ;

    @ UseStringConstant ( "private" )
	abstract Object getPrivateConstant ( ) ;

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
