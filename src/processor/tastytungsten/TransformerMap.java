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

import java . util . AbstractMap ;
import java . util . Collection ;
import java . util . Map ;
import java . util . Set ;

abstract class TransformerMap < K , V , P > extends AbstractMap < K , V >
{
    final Set < Map . Entry < K , V > > entrySet ( @ UseParameter final Collection < ? extends P > collection , @ UseParameter final Transformer < ? extends K , ? super P > keyTransformer , @ UseParameter final Transformer < ? extends V , ? super P > valueTransformer )
	{
	    Set < Map . Entry < K , V > > entrySet = getTransformerMapEntrySet ( collection , keyTransformer , valueTransformer ) ;
	    return entrySet ;
	}

    @ UseConstructor ( TransformerMapEntrySet . class )
	abstract < K , V , P > Set < Map . Entry < K , V > > getTransformerMapEntrySet ( Collection < ? extends P > collection , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
}
