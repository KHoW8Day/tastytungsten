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
import javax . lang . model . element . Name ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;

/**
 * Gets the return type of a method.
 *
 * @param <P> user data type
 **/
abstract class UseParameterTypeElementWranglerCallable < P >
    implements Callable < ElementVisitor < ? extends Name , ? super P > >
{
    /**
     * Gets the return type of a method.
     *
     * @return an element visitor that will get the return type of a method
     **/
    @ Override
	public ElementVisitor < ? extends Name , ? super P > call ( )
	{
	    ElementVisitor < ? extends TypeMirror , ? super P >
		typeElementWrangler =
		getTypeElementWrangler ( ) ;
	    TypeVisitor < ? extends Element , ? super P > elementTypeWrangler =
		getElementTypeWrangler ( ) ;
	    ElementVisitor < ? , ? super P > qualifiedNameElementWrangler =
		getQualifiedNameElementWrangler ( ) ;
	    TypeVisitor < ? , ? super P > elementTypeGaffer =
		getElementTypeGaffer
		( elementTypeWrangler , qualifiedNameElementWrangler ) ;
	    ElementVisitor < ? extends TypeMirror , ? super Object >
		returnTypeElementWrangler =
		getReturnTypeElementWrangler ( ) ;
	    return null ;
	}

    /**
     * Gets a type element wrangler.
     *
     * @param <P> user data type
     * @return a type element wrangler
     **/
    @ UseConstructor ( TypeElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? extends TypeMirror , ? super P >
	getTypeElementWrangler
	( ) ;

    /**
     * Gets an element type wrangler.
     *
     * @param <P> user data type
     * @return an element type wrangler
     **/
    @ UseConstructor ( ElementTypeWrangler . class )
	abstract
	< P >
	TypeVisitor < ? extends Element , ? super P >
	getElementTypeWrangler
	( ) ;

    /**
     * Gets a element type gaffer.
     *
     * @param <R> return type
     * @param <P> user data type
     * @param <A> wrangler data type
     * @param <B> visitor data type
     * @param wrangler the wrangler
     * @param visitor the visitor
     * @return an element type gaffer
     **/
    @ UseConstructor ( ElementTypeGaffer . class )
	abstract
	< R , P , A , B >
	TypeVisitor < ? extends R , ? super P >
	getElementTypeGaffer
	(
	 TypeVisitor < ? extends Element , ? super A > wrangler ,
	 ElementVisitor < ? extends R , ? super B > visitor
	 ) ;

    /**
     * Gets a TypeElementGaffer.
     *
     * @param <R> return type
     * @param <P> user data type
     * @param <A> wrangler data type
     * @param <B> visitor data type
     * @param wrangler the wrangler
     * @param visitor the visitor
     * @return a type element gaffer
     **/
    @ UseConstructor ( TypeElementGaffer . class )
	abstract
	< R , P , A , B >
	ElementVisitor < ? extends R , ? super P >
	getTypeElementGaffer
	(
	 ElementVisitor < ? extends TypeMirror , ? super A > wrangler ,
	 TypeVisitor < ? extends R , ? super B > visitor
	 ) ;

    /**
     * Gets a qualified name wrangler.
     *
     * @param <P> user data type
     * @return a qualified name wrangler
     **/
    @ UseConstructor ( QualifiedNameElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? , ? super P >
	getQualifiedNameElementWrangler
	( ) ;

    /**
     * Gets a return type wrangler.
     *
     * @param <P> user data type
     * @return a return type wrangler
     **/
    @ UseConstructor ( ReturnTypeElementWrangler . class )
	abstract
	< P >
	ElementVisitor < TypeMirror , ? super P >
	getReturnTypeElementWrangler
	( ) ;
}
