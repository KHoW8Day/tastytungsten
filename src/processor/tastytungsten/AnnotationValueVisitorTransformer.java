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

abstract class AnnotationValueVisitorTransformer < R , A , P > implements Transformer < R , A >
{
    R transform ( final A value , @ UseParameter final AnnotationValueVisitor < ? extends R , ? super P > visitor , @ UseParameter final Transformer < ? extends AnnotationValue , ? super A > transformer , @ UseParameter final P data )
    {
	AnnotationValue annotationValue = transformer . transform ( value ) ;
        R visit = visitor . visit ( annotationValue , data ) ;
	return visit ;
    }
}
