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

import java . util . ArrayList ;
import java . util . Collection ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * This works by chaining two element visitors together.
 * One the wrangler, wrangles up a bunch of elements.
 * The other, the visitor, processes each element.
 *
 * @param <R> the return type
 * @param <P> the data type
 * @param <A> the wrangler data type
 * @param <B> the visitor data type
 **/
abstract class ElementElementTrainer < R , P , A , B >
    extends SimpleElementVisitor6 < Collection < ? extends R > , P >
{
    /**
     * {@inheritDoc}.
     *
     * Wrangle up a bunch of elements and
     * then apply the visitor to each element.
     * Return a collection of the results.
     *
     * @param element {@inheritDoc}
     * @param data {@inheritDoc}
     **/
    @ Override
	protected Collection < ? extends R > defaultAction
	( final Element element , final P data )
	{
	    ElementVisitor
		< ? extends Iterable < ? extends Element > , ? super A >
		wrangler =
		getWrangler ( ) ;
	    A a = getA ( data ) ;
	    Iterable < ? extends Element > input =
		wrangler . visit ( element , a ) ;
	    Collection < R > output = getCollection ( ) ;
	    process ( input , output , data ) ;
	    return output ;
	}

    /**
     * Process a bunch of elements into the output list.
     *
     * @param input the specified elements
     * @param output the output list
     * @param data user data
     **/
    private void process
	(
	 final Iterable < ? extends Element > input ,
	 final Collection < ? super R > output ,
	 final P data
	 )
    {
	for ( Element i : input )
	    {
		process ( i , output , data ) ;
	    }
    }

    /**
     * Process an element into the output list.
     *
     * @param i an element
     * @param output the output list
     * @param data user data
     **/
    private void process
	(
	 final Element i ,
	 final Collection < ? super R > output ,
	 final P data
	 )
    {
	ElementVisitor < ? extends R , ? super B > visitor = getVisitor ( ) ;
	B b = getB ( data ) ;
	R o = visitor . visit ( i , b ) ;
	output . add ( o ) ;
    }

    /**
     * Gets the wrangler,
     * an element visitor that will fetch a bunch of elements.
     *
     * @return a wrangler.
     **/
    @ UseParameter
	abstract
	ElementVisitor < ? extends Iterable < ? extends Element > , ? super A >
	getWrangler ( ) ;

    /**
     * Gets the visitor.
     *
     * @return a visitor suitable for processing individual elements
     **/
    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super B > getVisitor ( ) ;

    /**
     * Constructs a new Collection suitable for adding.
     *
     * @param <T> the type of the collection
     * @return a new collection
     **/
    @ UseConstructor ( ArrayList . class )
	abstract < T > Collection < T > getCollection ( ) ;

    /**
     * Gets the data for the wrangler.
     *
     * @param data the input data
     * @return data based to the wrangler
     **/
    @ UseNull
	abstract A getA ( P data ) ;

    /**
     * Gets the data for the visitor.
     *
     * @param data the input data
     * @return data passed to the visitor
     **/
    @ UseNull
	abstract B getB ( P data ) ;
}
