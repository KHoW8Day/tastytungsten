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

import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . util . SimpleAnnotationValueVisitor6 ;

abstract class TransformerAnnotationValueVisitor < R , A , P > extends SimpleAnnotationValueVisitor6 < R , P >
{
    final R visitString ( final String value , final P data , @ UseParameter final AnnotationValueVisitor < ? extends A , ? super P > visitor , @ UseParameter final Transformer < ? extends R , ? super A > transformer )
	{
	    A a = visitor . visitString ( value , data ) ;
	    R visit = transformer . transform ( a ) ;
	    return visit ;
	}
}
