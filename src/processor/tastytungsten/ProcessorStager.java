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

import java . util . Collection ;
import java . util . Map ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . Element ;

/**
 * Processes an element.
 **/
abstract class ProcessorStager implements Stager < Object , Element >
{
    /**
     * Does processing.
     *
     * @param element the element to process
     * @return nothing
     **/
    @ Override
	public final Object stage ( final Element element )
	{
	    Stager < ? extends String , ? super AnnotationMirror > keyStager =
		getAnnotationMirrorKeyStager ( ) ;
	    Stager < ? extends AnnotationMirror , ? super AnnotationMirror >
		valueStager =
		getIdentityStager ( ) ;
	    Stager < ? extends Map < ? extends String , ? extends AnnotationMirror > , ? super Collection < ? extends AnnotationMirror > > //
		mapStager =
		getMapStager ( keyStager , valueStager ) ;
	    String valueConstant = getValueConstant ( ) ;
	    Stager < ? extends AnnotationMirror , ? super Map < ? extends String , ? extends AnnotationMirror > > mapItemStager = getMapItemStager ( valueConstant ) ;
	    Stager < ? extends AnnotationMirror , ? super Collection < ? extends AnnotationMirror > > singleJoinStager = getSingleJoinStager ( mapStager , mapItemStager ) ;
	    return null ;
	}

    /**
     * Constructs an AnnotationMirrorKeyStager.
     *
     * @return an AnnotationMirrorKeyStager
     **/
    @ UseConstructor ( AnnotationMirrorKeyStager . class )
	abstract
	Stager < ? extends String , ? super AnnotationMirror >
	getAnnotationMirrorKeyStager
	( ) ;

    /**
     * Constructs an IdentityStager.
     *
     * @param <R> the identity type
     * @return an identity stager
     **/
    @ UseConstructor ( IdentityStager . class )
	abstract
	< R >
	Stager < ? extends R , ? super R >
	getIdentityStager
	( ) ;

    /**
     * Constructs a MapStager.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param keyStager for keys
     * @param valueStager for values
     * @return a MapStager
     **/
    @ UseConstructor ( MapStager . class )
	abstract
	< K , V , P >
	Stager < ? extends Map < ? extends K , ? extends V > , ? super Collection < ? extends P > > //
	getMapStager
	(
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;

    /**
     *
     **/
    @ UseStringConstant ( "tastytungsten.Implementation" )
	abstract String getValueConstant ( ) ;

    /**
     * Constructs a MapItemStager.
     *
     * @param <K> key type
     * @param <V> value type
     * @param key the key
     * @return a mapItem stager
     **/
    abstract
	< K , V >
	Stager < ? extends V , ? super Map < ? extends K , ? extends V > >
								     getMapItemStager
								     ( K key ) ;

    /**
     * Creates a join stager for testing.
     *
     * @param <R> the return type
     * @param <A> the join type
     * @param <P> the data type
     * @param alpha the first stager to join
     * @param beta the second stager to join
     * @return a stager that links alpha with beta
     **/
    @ UseConstructor ( SingleJoinStager . class )
	abstract
	< R , A , P >
	Stager < ? extends R , ? super P >
			   getSingleJoinStager
			   (
			    Stager < ? extends A , ? super P > alpha ,
			    Stager < ? extends R , ? super A > beta
			    ) ;
}
