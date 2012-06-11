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

import java . util . ArrayList ;
import java . util . Collection ;
import java . util . Collections ;
import java . util . Set ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;

/**
 * A bootstrap implementation of the TastyTungstenProcessor.
 **/
public final class BootstrapTastyTungstenProcessor
    extends TastyTungstenProcessor
{
    @ Override
	< T > Set < T > singleton ( final T item )
	{
	    Set < T > singleton = Collections . singleton ( item ) ;
	    return singleton ;
	}

    @ Override
	< R , P , A , B >
	ElementVisitor < ? extends Iterable < ? extends R > , ? super P >
							getElementElementTrainer
							(
							 // CHECKSTYLE:OFF
							 final ElementVisitor < ? extends Iterable < ? extends Element > , ? super A > wrangler ,
							 final ElementVisitor < ? extends R , ? super B > visitor
							 // CHECKSTYLE:ON
							 )
    {
	ElementVisitor <
	    ? extends Iterable <
	    ? extends R > ,
	    ? super P > elementElementTrainer =
	    new ElementElementTrainer < R , P , A , B > ( )
	    {
		@ Override
		// CHECKSTYLE:OFF
		ElementVisitor < ? extends Iterable < ? extends Element > , ? super A >
		// CHECKSTYLE:ON
		getWrangler ( )
		{
		    return wrangler ;
		}

		@ Override
		ElementVisitor < ? extends R , ? super B > getVisitor ( )
		{
		    return visitor ;
		}

		@ Override
		    < T > Collection < T > getCollection ( )
		{
		    Collection < T > collection = new ArrayList < T > ( ) ;
		    return collection ;
		}

		@ Override
		    A getA ( P data )
		{
		    return null ;
		}

		@ Override
		    B getB ( P data )
		{
		    return null ;
		}
	    } ;
	return elementElementTrainer ;
    }

    @ Override
	< P >
	ElementVisitor < ? extends Iterable < ? extends Element > , ? super P >
							// CHECKSTYLE:OFF
							getElementsAnnotatedWithElementWrangler
							// CHECKSTYLE:ON
							(
							 // CHECKSTYLE:OFF
							 final RoundEnvironment roundEnvironment
							 // CHECKSTYLE:ON
							 )
    {
	ElementVisitor < ? extends Iterable < ? extends Element > , ? super P >
	    getElementsAnnotatedWithElementWrangler =
	    new ElementsAnnotatedWithElementWrangler < P > ( )
	{
	    @ Override
	    RoundEnvironment getRoundEnvironment ( )
	    {
		return roundEnvironment ;
	    }
	} ;
	return getElementsAnnotatedWithElementWrangler ;
    }
}
