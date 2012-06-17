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

import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * Wrangles return type.
 *
 * @param <P> user data type
 **/
abstract class ReturnTypeElementWrangler < P >
    extends SimpleElementVisitor6 < TypeMirror , P >
{
    /**
     * Wrangles the return type of a method.
     *
     * @param element the method
     * @param data user data
     * @return the method's return type
     **/
    @ Override
	public
	TypeMirror
	visitExecutable
	(
	 final ExecutableElement element ,
	 final P data
	 )
	{
	    TypeMirror returnType = element . getReturnType ( ) ;
	    return returnType ;
	}
}
