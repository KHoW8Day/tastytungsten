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
 * For checking if a candidate name already exists in an element.
 *
 * @param <P> user data type
 **/
abstract class DuplicateNameElementAskerCallable < P >
    implements Callable < ElementVisitor < ? extends Boolean , ? super P > >
{
    /**
     * {@inheritDoc}.
     *
     * Creates an element visitor that will check a candidate name for matches.
     **/
    @ Override
	public ElementVisitor < ? extends Boolean , ? super P > call ( )
	{
	    Object candidate = getCandidate ( ) ;
	    // CHECKSTYLE:OFF
	    ElementVisitor < ? extends Iterable < ? extends Element > , ? super P >
		// CHECKSTYLE:ON
		enclosedElementsElementWrangler =
		getEnclosedElementsElementWrangler ( ) ;
	    ElementVisitor < ? extends Boolean , ? super P >
		sameNameElementAsker =
		getSameNameElementAsker ( candidate ) ;
	    // CHECKSTYLE:OFF
	    ElementVisitor < ? extends Iterable < ? extends Boolean > , ? super P >
		// CHECKSTYLE:ON
		elementElementTrainer =
		getElementElementTrainer
		(
		 enclosedElementsElementWrangler ,
		 sameNameElementAsker
		 ) ;
	    Reader < ? extends Boolean , ? super Boolean > orReader =
		getOrReader ( ) ;
	    ElementVisitor < ? extends Boolean , ? super P > elementReader =
		getElementReader ( elementElementTrainer , orReader ) ;
	    return elementReader ;
	}

    /**
     * Gets the candidate name to test.
     *
     * @return the candidate name
     **/
    @ UseParameter
	abstract Object getCandidate ( ) ;

    /**
     * For reducing things to one boolean.
     *
     * @param <R> return type
     * @param <P> user data type
     * @param <A> lister type
     * @param <B> visitor type
     * @param lister for producing a list
     * @param reader for reduction
     * @return an element reader
     **/
    @ UseConstructor ( ElementReader . class )
	abstract
	< R , P , A , B >
	ElementVisitor < ? extends R , ? super P >
	getElementReader
	(
	 ElementVisitor < ? extends Iterable < ? extends A > , ? super B >
	 lister ,
	 Reader < ? extends R , ? super A > reader
	 ) ;

    /**
     * For determing if there is one or more matches.
     *
     * @return an OrReader
     **/
    @ UseConstructor ( OrReader . class )
	abstract Reader getOrReader ( ) ;

    /**
     * For iterating over the enclosed elements.
     *
     * @param <R> return type
     * @param <P> user data type
     * @param <A> lister type
     * @param <B> visitor type
     * @param lister for producing a list
     * @param visitor for visiting the list
     * @return a trainer
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
     * For grabbing the enclosed elements.
     *
     * @param <P> user data
     * @return an enclosed elements element wrangler
     **/
    @ UseConstructor ( EnclosedElementsElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? extends Iterable < ? extends Element > , ? super P >
	getEnclosedElementsElementWrangler
	( ) ;

    /**
     * for determining whether two names are the same.
     *
     * @param <P> user data
     * @param candidate one of the two names
     * @return a same name asker
     **/
    @ UseConstructor ( SameNameElementAsker . class )
	abstract
	< P >
	ElementVisitor < ? extends Boolean , ? super P >
	getSameNameElementAsker
	( Object candidate ) ;
}
