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
import java . util . HashMap ;
import java . util . HashSet ;
import java . util . Iterator ;
import java . util . Map ;
import java . util . Set ;
import javax . annotation . processing . ProcessingEnvironment ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . element . Name ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . type . DeclaredType ;
import org . junit . Assert ;
import org . mockito . stubbing . OngoingStubbing ;

import org . junit . Test ;

/**
 * The standard suite of tests.
 **/
@ SuppressWarnings ( "unchecked" )
    public abstract class Tests extends Bootstrap
{
    /**
     * Constructs a Tests.
     **/
    public Tests ( )
    {
    }

    @ Override
	void testAnnotationMirrorKeyStager ( AnnotationMirrorKeyStager annotationMirrorKeyStager )
    {
	AnnotationMirror annotationMirror = mock ( AnnotationMirror . class ) ;
	DeclaredType annotationType = mock ( DeclaredType . class ) ;
	Element annotationElement = mock ( Element . class ) ;
	when ( annotationType . asElement ( ) ) . thenReturn ( annotationElement ) ;
	when ( annotationMirror . getAnnotationType ( ) ) . thenReturn ( annotationType ) ;
	Name qualifiedName = mock ( Name . class ) ;
	when ( annotationMirrorKeyStager . getQualifiedNameElementVisitor ( ) . visit ( annotationElement ) ) . thenReturn ( qualifiedName ) ;
	String expected = qualifiedName . toString ( ) ;
	String observed = annotationMirrorKeyStager . stage ( annotationMirror ) ;
	assertEquals ( expected , observed ) ;
    }

    @ Override
	void testAnnotationValueVisitorStager ( AnnotationValueVisitorStager < Object , Object , Object > annotationValueVisitorStager )
    {
	Object input = mock ( Object . class ) ;
	AnnotationValue annotationValue = mock ( AnnotationValue . class ) ;
	when ( annotationValueVisitorStager . getStager ( ) . stage ( input ) ) . thenReturn ( annotationValue ) ;
	Object expected = mock ( Object . class ) ;
	when ( annotationValueVisitorStager . getVisitor ( ) . visit ( annotationValue , annotationValueVisitorStager . getData ( ) ) ) . thenReturn ( expected ) ;
	Object observed = annotationValueVisitorStager . stage ( input ) ;
	assertEquals ( expected , observed ) ;
    }

    /**
     * Asserts two things are equal.
     **/
    @ UseStaticMethod (  org . junit . Assert . class )
	abstract void assertEquals ( Object expected , Object observed ) ;

    @ UseStaticMethod (  org . junit . Assert . class )
	abstract void assertFalse ( boolean predicate ) ;

    @ UseStaticMethod (  org . junit . Assert . class )
	abstract void assertTrue ( boolean predicate ) ;

    @ UseStaticMethod ( org . mockito . Mockito . class )
	abstract < T > T mock ( Class < T > clazz ) ;

    /**
     * Mock a method call.
     *
     * @param <T> the class of the return type of the method
     * @param methodCall the return type of the method
     * @return an OngoingStubbing for mocking
     **/
    @ UseStaticMethod ( org . mockito . Mockito . class )
	abstract < T > OngoingStubbing < T > when ( T methodCall ) ;
}
