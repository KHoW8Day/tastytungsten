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

import java . util . Map ;

abstract class MapEntryTransformer < K , V , P > implements Transformer < Map . Entry < K , V > , P >
{
    final Map . Entry < K , V > transform ( final P data , @ UseParameter final Transformer < ? extends K , ? super P > keyTransformer , @ UseParameter final Transformer < ? extends V , ? super P > valueTransformer )
	{
	    Map . Entry < K , V > transformerMapEntry = getTransformerMapEntry ( data , keyTransformer , valueTransformer ) ;
	    return transformerMapEntry ;
	}

    @ UseConstructor ( TransformerMapEntry . class )
	abstract < K , V , P > Map . Entry < K , V > getTransformerMapEntry ( P data , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
}
