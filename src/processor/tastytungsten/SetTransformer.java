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
import java . util . Set ;

abstract class SetTransformer < R , P > implements Transformer < Set < ? extends R > , Collection < ? extends P > >
{
    final Set < ? extends R > transform ( Collection < ? extends P > collection , @ UseParameter Transformer < ? extends R , ? super P > transformer )
    {
	Set < ? extends R > transformerSet = getTransformerSet ( collection , transformer ) ;
	return transformerSet ;
    }

    @ UseConstructor ( TransformerSet . class )
	abstract < R , P > Set < ? extends R > getTransformerSet ( Collection < ? extends P > collection , Transformer < ? extends R , ? super P > transformer ) ;
}
