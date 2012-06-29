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
import java . util . Collections ;
import java . util . Set ;
import javax . annotation . processing . AbstractProcessor ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . TypeElement ;

/**
 * The processor.
 **/
abstract class Processor extends AbstractProcessor
{
    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public Set < String > getSupportedAnnotationTypes ( )
    {
	Stager < ? extends String , ? super String >
	    qualifiedNameStager =
	    getQualifiedNameStager ( ) ;
	String supportedAnnotationType = getSupportedAnnotationType ( ) ;
	String qualifiedName =
	    qualifiedNameStager . stage
	    ( supportedAnnotationType ) ;
	Set < String > supportedAnnotationTypes =
	    singleton ( qualifiedName ) ;
	return supportedAnnotationTypes ;
    }

    /**
     * {@inheritDoc}.
     *
     * @param annotations {@inheritDoc}
     * @param roundEnvironment {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public
	boolean
	process
	(
	 final Set < ? extends TypeElement > annotations ,
	 final RoundEnvironment roundEnvironment
	 )
    {
	Stager < ? , ? super Iterable < ? extends Element > >
	    annotationsStager =
	    getAnnotationsStager ( roundEnvironment ) ;
	return true ;
    }

    /**
     * Gets an stager for doing the processing.
     *
     * @param roundEnvironment the round environment
     * @return a stager for actually doing the processing
     **/
    private
	Stager < ? , ? super Iterable < ? extends Element > >
						  getAnnotationsStager
						  (
						   final RoundEnvironment
						   roundEnvironment
						   )
    {
	Stager < ? , ? super Element > stager = null ;
	Stager < ? , ? super Iterator < ? extends Element > > iteratorStager = getIteratorStager ( stager ) ;
	return null ;
    }

    /*
    private Stager < String , Element > getQualifiedNameStager2 ( )
    {
	AnnotationValueVisitor < ? extends String , ? super Object >
	    stringAnnotationValueVisitor =
	    getStringAnnotationValueVisitor ( ) ;
      	Stager < ? extends String , ? super String > qualifiedNameStager =
	getQualifiedNameStager ( ) ;
	Stager < ? extends String , ? super Object >
	annotationValueVisitorStager =
	getAnnotationValueVisitorStager ( ) ;
	return null ;
    }
    */

    /**
     * Gets the single supported annotation type.
     *
     * @return the single supported annotation type
     **/
    @ UseStringConstant ( "tastytungsten . Implementation" )
	abstract String getSupportedAnnotationType ( ) ;

    /**
     * Creates a singleton.
     *
     * @param <T> the type
     * @param item the single item
     * @return a singleton containing the single item
     **/
    @ UseStaticMethod ( Collections . class )
	abstract < T > Set < T > singleton ( T item ) ;

    /*
    @ UseConstructor ( StringAnnotationValueVisitor . class )
	abstract
	AnnotationValueVisitor < ? extends String , ? super Object >
	getStringAnnotationValueVisitor
	( ) ;

    @ UseConstructor ( AnnotationValueVisitor . class )
	abstract
	< R , A , P >
	Stager < ? extends R , ? super P >
	getAnnotationValueVisitorStager
	( ) ;
    */

    /**
     * Gets a stager for iterative converting.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param stager for item conversion
     * @return an iterative converter
     **/
    @ UseConstructor ( IteratorStager . class )
	abstract
	< R , P >
	Stager < ? extends Iterable < ? extends R > , ? super Iterator < ? extends P > >
										   getIteratorStager
										   (
										    Stager < ? extends R , ? super P > stager
										    ) ;

    /**
     * For formatting the supported annotation type
     * qualified name.
     *
     * @return a stager for formatting
     **/
    @ UseConstructor ( QualifiedNameStager . class )
	abstract
	Stager < ? extends String , ? super String >
			   getQualifiedNameStager
			   ( ) ;

    /*
    @ UseConstructor ( SingleJoinStager . class )
    abstract
	< R , A , P >
	Stager
	getSingleJoinStager
	(
	 ) ;
    */
}
