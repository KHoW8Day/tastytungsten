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
	    Reader < ? , ? super Object > printReader = getPrintReader
		(
		 openParenthesisConstant ,
		 closeParenthesisConstant ,
		 blankConstant ,
		 blankConstant ,
		 commaConstant ,
		 blankConstant
		 ) ;
	    ElementVisitor < ? , ? super P > elementReader =
		getElementReader
		(
		 elementElementTrainer ,
		 printReader
		 ) ;
	    return elementReader ;
	}

    /**
     * Gets a print reader.
     *
     * @param <P> user data type
     * @param beforeList text before the list
     * @param afterList text after the list
     * @param beforeFirst text before the first item
     * @param beforeItem text before normal items
     * @param afterItem text after normal items
     * @param afterLast text after the last item
     * @return an element writer
     **/
    @ UseConstructor ( PrintReader . class ) // ArgumentsElementWriterCallable
	abstract < P > Reader < ? , ? super P > getPrintReader
	(
	 Object beforeList ,
	 Object afterList ,
	 Object beforeFirst ,
	 Object beforeItem ,
	 Object afterItem ,
	 Object afterLast
	 ) ;

    /**
     * Gets the open parenthesis.
     *
     * @return (
     **/
    @ UseStringConstant ( "(" )
	abstract Object getOpenParenthesisConstant ( ) ;

    /**
     * For writing.
     *
     * @param <R> the return type
     * @param <P> user data type
     * @param <A> secondary data type
     * @param <B> lister data type
     * @param lister for producing the list
     * @param reader for reducing the list
     * @return an element visitor for writing.
     **/
    @ UseConstructor ( ElementReader . class )
	abstract < R , P , A , B > ElementVisitor < ? extends R , ? super P >
	getElementReader
	(
	 ElementVisitor < ? extends Iterable < ? extends A > , ? super B >
	 lister ,
	 Reader < ? extends R , ? super A > reader
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
