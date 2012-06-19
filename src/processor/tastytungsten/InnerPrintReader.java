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

import java . util . Iterator ;
import java . util . HashMap ;
import java . util . Map ;

/**
 * For writing lists.
 *
 * @param <P> the type of things to read
 **/
abstract class InnerPrintReader < P > implements Reader < StringBuilder , P >
{
    /**
     * Writes a list.
     *
     * @param iterator the input
     * @return a written list
     **/
    @ Override
	public
	StringBuilder
	read
	( final Iterator < ? extends P > iterator )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object beforeList = getBeforeList ( ) ;
	stringBuilder . append ( beforeList ) ;
	Object beforeFirst = getBeforeFirst ( ) ;
	stringBuilder . append ( beforeFirst ) ;
	Object beforeItem = getBeforeItem ( ) ;
	stringBuilder . append ( beforeItem ) ;
	P item = iterator . next ( ) ;
	stringBuilder . append ( item ) ;
	Object afterItem = getAfterItem ( ) ;
	stringBuilder . append ( afterItem ) ;
	Map < Boolean , Reader < ? , ? super P > > map = getMap ( ) ;
	Object blankConstant = getBlankConstant ( ) ;
	Reader < ? , ? super P > trueVal = getPrintReader
	    (
	     blankConstant ,
	     blankConstant ,
	     beforeItem ,
	     beforeItem ,
	     afterItem ,
	     blankConstant
	     ) ;
	map . put ( true , trueVal ) ;
	Object afterList = getAfterList ( ) ;
	Object afterLast = getAfterLast ( ) ;
	Reader < ? , ? super P > falseVal = getPrintReader
	    (
	     blankConstant ,
	     afterList ,
	     blankConstant ,
	     blankConstant ,
	     afterLast ,
	     afterItem
	     ) ;
	map . put ( false , falseVal ) ;
	boolean hasNext = iterator . hasNext ( ) ;
	Reader < ?  , ? super P > val = map . get ( hasNext ) ;
	val . read ( iterator ) ;
	return stringBuilder ;
    }

    /**
     * Gets a new map.
     *
     * @param <K> key type
     * @param <V> value type
     * @return a new map
     **/
    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;

    /**
     * For recursion.
     *
     * @param <P> user data type
     * @param beforeList text before the list
     * @param afterList text after the list
     * @param beforeFirst text before the first item
     * @param beforeItem text before normal items
     * @param afterItem text after normal items
     * @param afterLast text after the last item
     * @return a reader
     **/
    @ UseConstructor ( PrintReader . class )
	abstract
	< P >
	Reader < ? , ? super P >
	getPrintReader
	(
	 Object beforeList ,
	 Object afterList ,
	 Object beforeFirst ,
	 Object beforeItem ,
	 Object afterItem ,
	 Object afterLast
	 ) ;

    /**
     * For recursion.
     *
     * @return ""
     **/
    @ UseStringConstant ( "" )
	abstract Object getBlankConstant ( ) ;

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
     * what to print before the first item.
     *
     * @return before first
     **/
    @ UseParameter
	abstract Object getBeforeFirst ( ) ;

    /**
     * Get a string builder.
     *
     * @return a new string builder
     **/
    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    /**
     * What to print before an ordinary item.
     *
     * @return before item
     **/
    @ UseParameter
	abstract Object getBeforeItem ( ) ;

    /**
     * What to print after an ordinary item.
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
