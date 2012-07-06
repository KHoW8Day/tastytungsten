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

import java . util . AbstractSet ;
import java . util . Iterator ;
import java . util . Collection ;
import java . util . Map ;

abstract class TransformerMapEntrySet < K , V , P >
    extends AbstractSet < Map . Entry < K , V > >
{
    @ Override
	public final Iterator < Map . Entry < K , V > > iterator ( )
	{
	    Collection < ? extends P > collection = getCollection ( ) ;
	    Iterator < ? extends P > iterator = collection . iterator ( ) ;
	    Transformer < ? extends K , ? super P > keyTransformer = getKeyTransformer ( ) ;
	    Transformer < ? extends V , ? super P > valueTransformer = getValueTransformer ( ) ;
	    Iterator < Map . Entry < K , V > > transformrMapEntryIterator = getTransformerMapEntryIterator ( iterator , keyTransformer , valueTransformer ) ;
	    return transformrMapEntryIterator ;
	}

    @ Override
	public final int size ( )
	{
	    Collection < ? > collection = getCollection ( ) ;
	    int size = collection . size ( ) ;
	    return size ;
	}

    @ UseParameter
	abstract Collection < ? extends P > getCollection ( ) ;

    @ UseParameter
	abstract Transformer < ? extends K , ? super P > getKeyTransformer ( ) ;

    @ UseParameter
	abstract Transformer < ? extends V , ? super P > getValueTransformer ( ) ;

    @ UseConstructor ( TransformerMapEntryIterator . class )
	abstract < K , V , P > Iterator < Map . Entry < K , V > > getTransformerMapEntryIterator ( Iterator < ? extends P > iterator , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
}
