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

import javax . lang . model . util . SimpleAnnotationValueVisitor6 ;

/**
 * Casts an annotation value into a string.
 **/
abstract class StringAnnotationValueVisitor
    extends SimpleAnnotationValueVisitor6 < String , Object >
{
    /**
     * returns the string value.
     *
     * @param value the annotation value
     * @param data {@inheritDoc}
     * @return the string value
     **/
    @ Override
	public final String visitString ( final String value , final Object data )
	{
	    return value ;
	}
}
