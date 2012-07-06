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

abstract class QualifiedNameTransformer implements Transformer < String , String >
{
    final String transform ( final String value , @ UseStringConstant ( "[ \\t\\r\\n\\v\\f]" ) final String whitespace , @ UseStringConstant ( "" ) final String blank )
	{
	    String visit = value . replaceAll ( whitespace , blank ) ;
	    return visit ;
	}
}
