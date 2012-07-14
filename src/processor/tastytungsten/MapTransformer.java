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

import java . util . Collection ;
import java . util . Map ;

abstract class MapTransformer < K , V , P > implements Transformer < Map < ? extends K , ? extends V > , Collection < ? extends P > >
{
    final Map < ? extends K , ? extends V > transform ( Collection < ? extends P > data , @ UseParameter Transformer < ? extends K , ? super P > keyTransformer , @ UseParameter Transformer < ? extends V , ? super P > valueTransformer )
	{
	    Map < ? extends K , ? extends V > transformMap = getTransformerMap ( data , keyTransformer , valueTransformer ) ;
	    return transformMap ;
	}

    @ UseConstructor ( TransformerMap . class )
	abstract < K , V , P > Map < ? extends K , ? extends V > getTransformerMap ( Collection < ? extends P > data , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
}
