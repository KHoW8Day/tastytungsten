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
 * Makes an element visitor that will print type arguments.
 *
 * @param <P> user data type
 **/
abstract class TypeArgumentsElementWriterCallable < P >
    implements Callable < ElementVisitor < ? , ? super P > >
{
    /**
     * Returns an element visitor that will print out type arguments
     * of a specified element.
     *
     * @return an element visitor for printing type arguments
     **/
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
	    Object openAngleConstant = getOpenAngleConstant ( ) ;
	    Object commaConstant = getCommaConstant ( ) ;
	    Object closeAngleConstant = getCloseAngleConstant ( ) ;
	    ElementVisitor < ? extends Iterable < ? > , ? super P >
		elementElementTrainer =
		getElementElementTrainer
		( typeParametersElementWrangler , simpleNameElementWrangler ) ;
	    ElementVisitor < ? , ? super P > elementWriter =
		getElementWriter
		(
		 elementElementTrainer ,
		 blankConstant ,
		 blankConstant ,
		 openAngleConstant ,
		 blankConstant ,
		 commaConstant ,
		 closeAngleConstant
		 ) ;
	    return elementWriter ;
	}

    /**
     * Gets an element writer.
     *
     * @param <P> user data type
     * @param lister produces the list
     * @param beforeList text before the list
     * @param afterList text after the list
     * @param beforeFirst text before the first item
     * @param beforeItem text before normal items
     * @param afterItem text after normal items
     * @param afterLast text after the last item
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
     * @param <R> return type
     * @param <P> user data type
     * @param <A> wrangler data type
     * @param <B> visitor data type
     * @param lister produces a list
     * @param visitor visits each element of the list
     * @return an element element trainer
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
     * Gets the type parameters.
     *
     * @param <P> user data type
     * @return type parameters wrangler
     **/
    @ UseConstructor ( TypeParametersElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? extends Iterable < ? extends Element > , ? super P >
	getTypeParametersElementWrangler
	( ) ;

    /**
     * Gets the element's simple name.
     * For printing, we just want the simple name.
     *
     * @param <P> user data type
     * @return simple name wrangler
     **/
    @ UseConstructor ( SimpleNameElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? extends Object , ? super P >
	getSimpleNameElementWrangler ( ) ;

    /**
     * The blank constant.
     *
     * @return the blank constant
     **/
    @ UseStringConstant ( "" )
	abstract Object getBlankConstant ( ) ;

    /**
     * The open angle bracket.
     *
     * @return the open angle bracket
     **/
    @ UseStringConstant ( "<" )
	abstract Object getOpenAngleConstant ( ) ;

    /**
     * The comma constant.
     *
     * @return the comma
     **/
    @ UseStringConstant ( "," )
	abstract Object getCommaConstant ( ) ;

    /**
     * The closing angle bracket constant.
     *
     * @return the closing angle bracket
     **/
    @ UseStringConstant ( ">" )
	abstract Object getCloseAngleConstant ( ) ;
}
