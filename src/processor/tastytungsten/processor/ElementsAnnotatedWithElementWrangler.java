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

package tastytungsten . processor ;

import java . util . Set ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . util . SimpleElementVisitor6 ;

import tastytungsten . annotations . UseParameter ;

/**
 * Wrangles elements annotated with a specified annotation.
 *
 * @param <P> {@inheritDoc}
 **/
abstract class ElementsAnnotatedWithElementWrangler < P >
    extends SimpleElementVisitor6 < Set < ? extends Element > , P >
{
    /**
     * Gets elements annotated with a specific annotation.
     *
     * @param element the specific annotation
     * @param data {@inheritDoc}
     * @return elements annotated with the specified annotation
     **/
    @ Override
	public final Set < ? extends Element > visitType
	( final TypeElement element , final P data )
	{
	    RoundEnvironment roundEnvironment = getRoundEnvironment ( ) ;
	    Set < ? extends Element > visit =
		roundEnvironment . getElementsAnnotatedWith ( element ) ;
	    return visit ;
	}

    /**
     * Gets the utility class that can return elements
     * annotated with a specific annotation.
     *
     * @return the utility class
     **/
    @ UseParameter
	abstract RoundEnvironment getRoundEnvironment ( ) ;
}
