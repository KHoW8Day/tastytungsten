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

/**
 * For writing lists.
 *
 * @param <P> the type of things to read
 **/
abstract class PrintReader < P > implements Reader < StringBuilder , P >
{
    /**
     * Writes a list.
     *
     * @return a written list
     **/
    @ Override
	public StringBuilder read ( Iterator < ? extends P > iterator )
    {
	boolean isFirst = true ;
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object beforeList = getBeforeList ( ) ;
	stringBuilder . append ( beforeList ) ;
	for
	    (
	     Object item = iterator . next ( ) ;
	     iterator . hasNext ( ) ;
	     item = iterator . next ( )
	     )
	    {
		boolean isLast = iterator . hasNext ( ) ;
		Object beforeItem = null ;
		if ( isFirst )
		    {
			beforeItem = getBeforeFirst ( ) ;
		    }
		else
		    {
			beforeItem = getBeforeItem ( ) ;
		    }
		stringBuilder . append ( beforeItem ) ;
		stringBuilder . append ( item ) ;
		Object afterItem = null ;
		if ( isLast )
		    {
			afterItem = getAfterLast ( ) ;
		    }
		else
		    {
			afterItem = getAfterItem ( ) ;
		    }
		stringBuilder . append ( afterItem ) ;
		isFirst = true ;
	    }
	Object afterList = getAfterList ( ) ;
	stringBuilder . append ( afterList ) ;
	return stringBuilder ;
    }

    private
	< P >
	Reader < ? , ? super P >
	getPrintReader
	(
	 Object afterList ,
	 Object beforeFirst ,
	 Object beforeItem ,
	 Object afterItem ,
	 Object afterLast
	 )
	{
	    Object beforeList = getBlankConstant ( ) ;
	    Reader < ? , ? super P >
		printReader =
		getPrintReader
		(
		 beforeList ,
		 afterList ,
		 beforeFirst ,
		 beforeItem ,
		 afterItem ,
		 afterLast
		 ) ;
	    return printReader ;
	}

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
