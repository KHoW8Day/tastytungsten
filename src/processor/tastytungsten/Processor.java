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

import java . util . Collections ;
import java . util . Set ;
import javax . annotation . processing . AbstractProcessor ;
import javax . annotation . processing . RoundEnvironment ;
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
    public
	boolean
	process
	(
	 final Set < ? extends TypeElement > annotations ,
	 final RoundEnvironment roundEnvironment
	 )
    {
	return true ;
    }

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
}
