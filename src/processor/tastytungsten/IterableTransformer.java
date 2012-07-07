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

abstract class IterableTransformer < R , P > implements Transformer < Iterable < ? extends R > , Iterable < ? extends P > >
{
    public final Iterable < ? extends R > transform ( final Iterable < ? extends P > iterable , @ UseParameter final Transformer < ? extends R , ? super P > transformer )
	{
	    Iterable < ? extends R > transformerIterable = getTransformerIterable ( iterable , transformer ) ;
	    return transformerIterable ;
	}

    @ UseConstructor ( TransformerIterable . class )
	abstract < R , P > Iterable < ? extends R > getTransformerIterable ( Iterable < ? extends P > iterable , Transformer < ? extends R , ? super P > transformer ) ;
}
