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

import java . util . Set ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . TypeElement ;

abstract class ElementsAnnotatedWithTransformer implements Transformer < Set < ? extends Element > , TypeElement >
{
    final Set < ? extends Element > transform ( TypeElement annotation , @ UseParameter RoundEnvironment roundEnvironment )
    {
	Set < ? extends Element > elementsAnnotatedWith = roundEnvironment . getElementsAnnotatedWith ( annotation ) ;
	return elementsAnnotatedWith ;
    }
}