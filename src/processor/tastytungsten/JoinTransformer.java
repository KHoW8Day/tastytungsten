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

abstract class JoinTransformer < R , A , P > implements Transformer < R , P >
{
    final R transform ( final P value , @ UseParameter final Transformer < ? extends A , ? super P > alpha , @ UseParameter final Transformer < ? extends R , ? super A > beta )
    {
	A a = alpha . transform ( value ) ;
	R transform = beta . transform ( a ) ;
	return transform ;
    }
}
