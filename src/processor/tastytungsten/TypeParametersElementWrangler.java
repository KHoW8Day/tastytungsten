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

import java . util . List ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . element . TypeParameterElement ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * Gets type parameters.
 *
 * @param <P> user data type
 **/
abstract class TypeParametersElementWrangler < P >
    extends
	SimpleElementVisitor6 < List < ? extends TypeParameterElement > , P >
{
    /**
     * Gets the type parameters of a class.
     *
     * @param element the specified class
     * @param data user data
     * @return the class's type parameters
     **/
    @ Override
	public
	List < ? extends TypeParameterElement >
	visitType
	(
	 final TypeElement element ,
	 final P data
	 )
	{
	    List < ? extends TypeParameterElement > typeParameters =
		element . getTypeParameters ( ) ;
	    return typeParameters ;
	}

    /**
     * Gets the type parameters of a method.
     *
     * @param element the specified method
     * @param data user data
     * @return the method's type parameters
     **/
    @ Override
	public
	List < ? extends TypeParameterElement >
	visitExecutable
	(
	 final ExecutableElement element ,
	 final P data
	 )
	{
	    List < ? extends TypeParameterElement > typeParameters =
		element . getTypeParameters ( ) ;
	    return typeParameters ;
	}
}
