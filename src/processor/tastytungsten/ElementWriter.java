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

import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

/**
 * Writes out a list associated with an element.
 *
 * @param <P> user data
 **/
abstract class ElementWriter < P > extends SimpleElementVisitor6 < Object , P >
{
    /**
     * Writes a list associated with the specified element.
     *
     * @param element the specified element
     * @param data user data
     * @return a writeup of the list
     **/
    @ Override
	protected
	Object
	defaultAction
	( final Element element , final P data )
	{
	    ElementVisitor < ? extends Iterable < ? > , ? super P > lister =
		getLister ( ) ;
	    Iterable < ? > list = lister . visit ( element , data ) ;
	    Object beforeList = getBeforeList ( ) ;
	    Object afterList = getAfterList ( ) ;
	    Object beforeFirst = getBeforeFirst ( ) ;
	    Object beforeItem = getBeforeItem ( ) ;
	    Object afterItem = getAfterItem ( ) ;
	    Object afterLast = getAfterLast ( ) ;
	    Callable < ? > listWriter =
		getListWriter
		(
		 list ,
		 beforeList ,
		 afterList ,
		 beforeFirst ,
		 beforeItem ,
		 afterItem ,
		 afterLast
		 ) ;
	    Object visit = listWriter . call ( ) ;
	    return visit ;
	}

    /**
     * Get a list writer.
     *
     * @param list the list to write
     * @param beforeList before the list
     * @param afterList after the list
     * @param beforeFirst before the first item
     * @param beforeItem before ordinary items
     * @param afterItem after ordinary items
     * @param afterLast after the last item
     * @return a list writer
     **/
    @ UseConstructor ( ListWriter . class )
	abstract Callable < ? > getListWriter
	(
	 Iterable < ? > list ,
	 Object beforeList ,
	 Object afterList ,
	 Object beforeFirst ,
	 Object beforeItem ,
	 Object afterItem ,
	 Object afterLast
	 ) ;

    /**
     * How to get the list.
     *
     * @return a lister
     **/
    @ UseParameter
	abstract
	ElementVisitor < ? extends Iterable < ? > , ? super P >
	getLister
	( ) ;

    /**
     * What to print before the list.
     *
     * @return before list
     **/
    @ UseParameter
	abstract Object getBeforeList ( ) ;

    /**
     * What to print after the list.
     *
     * @return after list
     **/
    @ UseParameter
	abstract Object getAfterList ( ) ;

    /**
     * What to print before the first item.
     *
     * @return before first
     **/
    @ UseParameter
	abstract Object getBeforeFirst ( ) ;

    /**
     * What to print before ordinary items.
     *
     * @return before item
     **/
    @ UseParameter
	abstract Object getBeforeItem ( ) ;

    /**
     * What to print after ordinary items.
     *
     * @return after item
     **/
    @ UseParameter
	abstract Object getAfterItem ( ) ;

    /**
     * What to print after the last item.
     *
     * @return after last
     **/
    @ UseParameter
	abstract Object getAfterLast ( ) ;
}
