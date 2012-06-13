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

import java . util . Collections ;
import java . util . Set ;
import javax . annotation . processing . AbstractProcessor ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . TypeElement ;
import tastytungsten . annotations . Implementation ;

import tastytungsten . annotations . UseConstructor ;
import tastytungsten . annotations . UseStaticMethod ;

/**
 * This processor will inject dependencies by subclass injection.
 **/
@ Implementation ( "tastytungsten . processor . StandardProcessor" )
    abstract class Processor extends AbstractProcessor
    {
	/**
	 * {@inheritDoc}.
	 *
	 * @return {@inheritDoc}
	 **/
	@ Override
	    public final Set < String > getSupportedAnnotationTypes ( )
	{
	    Set < String > supportedAnnotationTypes =
		singleton ( "tastytungsten.annotations.Implementation" ) ;
	    return supportedAnnotationTypes ;
	}

	/**
	 * {@inheritDoc}.
	 *
	 * @param annotations annotations to process
	 * @param roundEnvironment used for finding elements to process
	 * @return {@inheritDoc}
	 **/
	@ Override
	    public final boolean process
	    (
	     final Set < ? extends TypeElement > annotations ,
	     final RoundEnvironment roundEnvironment
	     )
	{
	    return true ;
	}

	/**
	 * Gets a singleton based on the specified input.
	 *
	 * @param <T> the type of singleton
	 * @param value the contents of the singleton set
	 * @return a singleton set
	 **/
	@ UseStaticMethod ( Collections . class )
	    abstract < T > Set < T > singleton ( T value ) ;

	/**
	 * Trains an element visitor.
	 *
	 * The output of the specified wrangler (a list of elements)
	 * is fed to the input of another visitor.
	 *
	 * @param <R> the output type
	 * @param <P> the data type
	 * @param <A> the data type of the wrangler
	 * @param <B> the data type of the visitor
	 * @param wrangler the specified wrangler
	 * @param visitor the specified visitor
	 * @return a visitor that will feed the output of the wrangler
	 *         to the input of the visitor
	 **/
	@ UseConstructor ( ElementElementTrainer . class )
	    abstract
	    < R , P , A , B >
	    ElementVisitor < ? extends Iterable < ? extends R > , ? super P >
							    // CHECKSTYLE:OFF
							    getElementElementTrainer
							    // CHECKSTYLE:ON
							    (
							     // CHECKSTYLE:OFF
							     ElementVisitor < ? extends Iterable < ? extends Element > , ? super A > wrangler ,
							     ElementVisitor < ? extends R , ? super B > visitor
							     // CHECKSTYLE:ON
							     ) ;

	/**
	 * Gets a visitor this will return elements
	 * annotated with a particular annotation.
	 *
	 * @param <P> the data type
	 * @param roundEnvironment used to get the elements
	 *        annotated with a particular annotation
	 * @return a visitor
	 **/
	@ UseConstructor ( ElementsAnnotatedWithElementWrangler . class )
	    abstract < P >
	    ElementVisitor
	// CHECKSTYLE:OFF
	    < ? extends Iterable < ? extends Element > , ? super P >
					     getElementsAnnotatedWithElementWrangler
					     ( RoundEnvironment roundEnvironment ) ;
	// CHECKSTYLE:ON
    }
