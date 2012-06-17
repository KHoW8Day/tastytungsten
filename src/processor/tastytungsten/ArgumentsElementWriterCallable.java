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

/**
 * Makes an element visitor that will print arguments.
 *
 * @param <P> user data
 **/
abstract class ArgumentsElementWriterCallable < P >
    implements Callable < ElementVisitor < ? , ? super P > >
{
    @ Override
	public ElementVisitor < ? , ? super P > call ( )
	{
	    // CHECKSTYLE:OFF
	    ElementVisitor < ? extends Iterable < ? extends Element > , ? super P >
	    // CHECKSTYLE:ON
		typeParametersElementWrangler =
		getTypeParametersElementWrangler ( ) ;
	    ElementVisitor < ? , ? super P > simpleNameElementWrangler =
		getSimpleNameElementWrangler ( ) ;
	    Object blankConstant = getBlankConstant ( ) ;
	    Object openParenthesisConstant = getOpenParenthesisConstant ( ) ;
	    Object commaConstant = getCommaConstant ( ) ;
	    Object closeParenthesisConstant = getCloseParenthesisConstant ( ) ;
	    ElementVisitor < ? extends Iterable < ? > , ? super P >
		elementElementTrainer =
		getElementElementTrainer
		(
		 typeParametersElementWrangler ,
		 simpleNameElementWrangler
		 ) ;
	    ElementVisitor < ? , ? super P > elementWriter =
		getElementWriter
		(
		 elementElementTrainer ,
		 openParenthesisConstant ,
		 closeParenthesisConstant ,
		 blankConstant ,
		 blankConstant ,
		 commaConstant ,
		 blankConstant
		 ) ;
	    return elementWriter ;
	}

    /**
     * Gets an element writer.
     *
     * @param <P> user data type
     * @param lister prepares a list
     * @param beforeList what to print before the list
     * @param afterList what to print after the list
     * @param beforeFirst what to print before the first item
     * @param beforeItem what to print before usual items
     * @param afterItem what to print after usual items
     * @param afterLast what to print after the last item
     * @return an element writer
     **/
    @ UseConstructor ( ElementWriter . class )
	abstract
	< P >
	ElementVisitor < ? , ? super P >
	getElementWriter
	(
	 ElementVisitor < ? extends Iterable < ? > , ? super P > lister ,
	 Object beforeList ,
	 Object afterList ,
	 Object beforeFirst ,
	 Object beforeItem ,
	 Object afterItem ,
	 Object afterLast
	 ) ;

    /**
     * Gets an element element trainer.
     *
     * @param <R> the return type
     * @param <P> the user data type
     * @param <A> the lister type
     * @param <B> the visitor type
     * @param lister produces a list
     * @param visitor acts on each item in the list
     * @return an element element trainer.
     **/
    @ UseConstructor ( ElementElementTrainer . class )
	abstract
	< R , P , A , B >
	ElementVisitor < ? extends Iterable < ? extends R > , ? super P >
	getElementElementTrainer
	(
	 ElementVisitor < ? extends Iterable < ? extends Element > , ? super A >
	 lister ,
	 ElementVisitor < ? extends R , ? super B > visitor
	 ) ;

    /**
     * gets a type parameters wrangler.
     *
     * @param <P> user data
     * @return a type parameters wrangler
     **/
    @ UseConstructor ( TypeParametersElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? extends Iterable < ? extends Element > , ? super P >
	getTypeParametersElementWrangler
	( ) ;

    /**
     * Gets a simple name wrangler.
     *
     * @param <P> user data
     * @return a simple name wrangler
     **/
    @ UseConstructor ( SimpleNameElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? extends Object , ? super P >
	getSimpleNameElementWrangler
	( ) ;

    /**
     * Gets the blank constant.
     *
     * @return ""
     **/
    @ UseStringConstant ( "" )
	abstract Object getBlankConstant ( ) ;

    /**
     * Gets the open parenthesis.
     *
     * @return (
     **/
    @ UseStringConstant ( "(" )
	abstract Object getOpenParenthesisConstant ( ) ;

    /**
     * Gets the comma constant.
     *
     * @return ,
     **/
    @ UseStringConstant ( "," )
	abstract Object getCommaConstant ( ) ;

    /**
     * Gets the close parenthesis constant.
     *
     * @return )
     **/
    @ UseStringConstant ( ")" )
	abstract Object getCloseParenthesisConstant ( ) ;
}
