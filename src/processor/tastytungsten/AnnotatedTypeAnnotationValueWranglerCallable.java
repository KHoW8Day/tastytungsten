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

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . Elements ;

/**
 * Determines annotation type.
 *
 * @param <P> data type
 **/
abstract class AnnotatedTypeAnnotationValueWranglerCallable < P >
    implements Callable < AnnotationValueVisitor < ? , ? super P > >
{
    /**
     * Makes an AnnotationValueVisitor that will figure out the type that
     * an annotation is referring to.
     * {@see UseConstructor}
     * {@see UseStaticMethod}
     *
     * @return an annotation value visitor for figuring out types
     **/
    public AnnotationValueVisitor < ? , ? super P > call ( )
    {
	Elements elementUtils = getElementUtils ( ) ;
	// CHECKSTYLE:OFF
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P >
	    // CHECKSTYLE:ON
	    elementValuesWithDefaultsAnnotationValueWrangler =
	    getElementValuesWithDefaultsAnnotationValueWrangler
	    ( elementUtils ) ;
	Object target = getTarget ( ) ;
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P >
	    annotationValuePunter =
	    getAnnotationValuePunter
	    ( elementValuesWithDefaultsAnnotationValueWrangler , target ) ;
	AnnotationValueVisitor < ? extends TypeMirror , ? super P >
	    typeCaster =
	    getTypeMirrorAnnotationValueCaster ( ) ;
	AnnotationValueVisitor < ? extends TypeMirror , ? super P >
	    annotationValueAnnotationValueGaffer =
	    getAnnotationValueAnnotationValueGaffer
	    ( annotationValuePunter , typeCaster ) ;
	TypeVisitor < ? extends Element , ? super P >
	    elementTypeWrangler =
	    getElementTypeWrangler ( ) ;
	AnnotationValueVisitor < ? extends Element , ? super P >
	    typeAnnotationValueGaffer =
	    getTypeMirrorAnnotationValueGaffer
	    (
	     annotationValueAnnotationValueGaffer ,
	     elementTypeWrangler
	     ) ;
	ElementVisitor < ? , ? super P > qualifiedNameElementWrangler =
	    getQualifiedNameElementWrangler ( ) ;
	AnnotationValueVisitor < ? , ? super P >
	    elementAnnotationValueGaffer =
	    getElementAnnotationValueGaffer
	    (
	     typeAnnotationValueGaffer ,
	     qualifiedNameElementWrangler
	     ) ;
	return null ;
    }

    /**
     * For getting element values with defaults.
     *
     * @return element utils
     **/
    @ UseParameter
	abstract Elements getElementUtils ( ) ;

    /**
     * For combining an annotation value visitor (wrangler) and
     * an element visitor (visitor).
     *
     * @param <R> return type
     * @param <P> data type
     * @param <A> secondary data type
     * @param <B> secondary data type
     * @param <C> wrangler data type
     * @param <D> visitor data type
     * @param wrangler visitor that produces a type
     * @param visitor acts on the type produced by the visitor
     * @return an annotation value visitor that
     *         combines the wrangler and visitor
     **/
    @ UseConstructor ( ElementAnnotationValueGaffer . class )
	abstract
	< R , P , A , B , C , D >
	AnnotationValueVisitor < ? extends R , ? super P >
	getElementAnnotationValueGaffer
	(
	 AnnotationValueVisitor < ? extends Element , ? super C > wrangler ,
	 ElementVisitor < ? extends R , ? super D > visitor
	 ) ;

    /**
     * For combining an annotation value visitor (wrangler) and a
     * type visitor (visitor).
     *
     * @param <R> return type
     * @param <P> data type
     * @param <A> secondary data type
     * @param <B> secondary data type
     * @param <C> wrangler data type
     * @param <D> visitor data type
     * @param wrangler visitor that produces a type
     * @param visitor acts on the type produced by the visitor
     * @return an annotation value visitor that combines
     *         the wrangler and visitor
     **/
    @ UseConstructor ( TypeMirrorAnnotationValueGaffer . class )
	abstract
	< R , P , A , B , C , D >
	AnnotationValueVisitor < ? extends R , ? super P >
	getTypeMirrorAnnotationValueGaffer
	(
	 // CHECKSTYLE:OFF
	 AnnotationValueVisitor < ? extends TypeMirror , ? super C > wrangler , TypeVisitor < ? extends R , ? super D >
	 // CHECKSTYLE:ON
	 visitor
	 ) ;

    /**
     * For getting the qualified name of an element.
     *
     * @param <P> user data type
     * @return a qualified name element wrangler
     **/
    @ UseConstructor ( QualifiedNameElementWrangler . class )
	abstract
	< P >
	ElementVisitor < ? , ? super P >
	getQualifiedNameElementWrangler
	( ) ;

    /**
     * For getting the element of a type.
     *
     * @param <P> user data type
     * @return an element type wrangler
     **/
    @ UseConstructor ( ElementTypeWrangler . class )
	abstract
	< P >
	TypeVisitor < ? extends Element , ? super P >
	getElementTypeWrangler
	( ) ;

    /**
     * For combining two annotation value visitors.
     *
     * @param <R> return type
     * @param <P> data type
     * @param <A> secondary data type
     * @param <B> secondary data type
     * @param <C> wrangler data type
     * @param <D> visitor data type
     * @param wrangler visitor that produces an annotation value
     * @param visitor acts on the annotation value produced by the visitor
     * @return an anotation value visitor that
     *         combines 2 annotation value visitors
     **/
    @ UseConstructor ( AnnotationValueAnnotationValueGaffer . class )
	abstract
	< R , P , A , B , C , D >
	AnnotationValueVisitor < ? extends R , ? super P >
	getAnnotationValueAnnotationValueGaffer
	(
	 AnnotationValueVisitor < ? extends AnnotationValue , ? super C >
	 wrangler ,
	 AnnotationValueVisitor < ? extends R , ? super D > visitor
	 ) ;

    /**
     * For getting the right element value.
     *
     * @param <P> user data type
     * @param <A> secondary data type
     * @param <B> secondary data type
     * @param mapper produces an easy to use map format
     * @param target selects from the map
     * @return an annotation value that can navigate element values
     **/
    @ UseConstructor ( AnnotationValuePunter . class )
	abstract
	< P , A , B >
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P >
	getAnnotationValuePunter
	(
	 // CHECKSTYLE:OFF
	 AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super B >
	 // CHECKSTYLE:ON
	 mapper ,
	 Object target
	 ) ;

    /**
     * For getting element values of an annotation.
     *
     * @param <P> user data
     * @param elementUtils for getting the element values with defaults
     * @return an annotation value visitor
     **/
    @ UseConstructor
	( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract
	< P >
	// CHECKSTYLE:OFF
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P >
	// CHECKSTYLE:ON
	getElementValuesWithDefaultsAnnotationValueWrangler
	( Elements elementUtils ) ;

    /**
     * Gets a TypeMirrorAnnotationValueCaster.
     *
     * @param <P> user data
     * @return a typemirror anotation value caster
     **/
    @ UseConstructor ( TypeMirrorAnnotationValueCaster . class )
	abstract
	< P >
	AnnotationValueVisitor < ? extends TypeMirror , ? super P >
	getTypeMirrorAnnotationValueCaster
	( ) ;

    /**
     * Gets the name of the annoted value.
     *
     * @return value
     **/
    @ UseStringConstant ( "value" )
	abstract Object getTarget ( ) ;
}
