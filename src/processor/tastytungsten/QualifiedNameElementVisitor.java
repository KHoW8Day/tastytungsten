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

import javax . lang . model . element . Name ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * For wrangling the qualified name of elements.
 **/
abstract class QualifiedNameElementVisitor
    extends SimpleElementVisitor6 < Name , Object >
{
    /**
     * Gets the qualified name of the specified element.
     *
     * @param element the specified element
     * @param data nothing
     * @return the qualified name of the specified element
     **/
    @ Override
	public
	final
	Name
	visitType
	(
	 final TypeElement element ,
	 final Object data
	 )
	{
	    Name qualifiedName = element . getQualifiedName ( ) ;
	    return qualifiedName ;
	}
}
