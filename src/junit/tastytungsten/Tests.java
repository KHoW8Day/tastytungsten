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

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import static org . junit . Assert . assertEquals ;
import static org . junit . Assert . assertTrue ;
import org . mockito . stubbing . OngoingStubbing ;

import org . junit . Test ;

/**
 * The standard suite of tests.
 **/
public abstract class Tests
{
    /**
     * Constructs a Tests.
     **/
    public Tests ( )
    {
    }

    /**
     * This test should pass every time.
     * If there is not at least one passing test,
     * something is wrong.
     **/
    @ Test
	public final void easyPass ( )
    {
	assertTrue ( true ) ;
    }

    /**
     * If we want to observe that the testing framework is actually
     * working, add a Test annotation to this method.
     * It should fail every time.
     * If it does not fail, then it is not really running the tests.
     **/
    public final void easyFail ( )
    {
	assertTrue ( false ) ;
    }

    /**
     * Tests the StagerAnnotationValueVisitor.
     * {@link StagerAnnotationValueVisitor}
     *
     * There is no need to mock the visitor or stager.
     * We have them on hand.
     * They are being tested separately.
     * If there is an error with this test,
     * the other respective test should be updated accordingly.
     **/
    @ Test
    public final void testStagerAnnotationValueVisitor ( )
    {
	AnnotationValueVisitor < ? extends String , ? super Object >
	    stringAnnotationValueVisitor =
	    getStringAnnotationValueVisitor ( ) ;
	Stager < ? extends String , ? super String >
	    qualifiedNameStager =
	    getQualifiedNameStager ( ) ;
	AnnotationValueVisitor < ? extends String , ? super Object >
	    stagerAnnotationValueVisitor =
	    getStagerAnnotationValueVisitor
	    ( stringAnnotationValueVisitor , qualifiedNameStager ) ;
	String expected = getTestStringAnnotationValueVisitorA ( ) ;
	String observed = stagerAnnotationValueVisitor . visitString ( expected , null ) ;
	assertEquals ( expected , observed ) ;
    }

    /**
     * Tests the StringAnnotationValueVisitor.
     * {@link StringAnnotationValueVisitor}
     **/
    @ Test
	public final void testStringAnnotationValueVisitor ( )
    {
	AnnotationValueVisitor < ? extends String , ? super Object >
	    stringAnnotationValueVisitor =
	    getStringAnnotationValueVisitor ( ) ;
	String expected = getTestStringAnnotationValueVisitorA ( ) ;
	String observed1 =
	    stringAnnotationValueVisitor . visitString ( expected , null ) ;
	assertEquals ( expected , observed1 ) ;
	AnnotationValue annotationValue = mock ( AnnotationValue . class ) ;
	when ( annotationValue . accept ( stringAnnotationValueVisitor , null ) ) . thenReturn ( expected ) ; //
	String observed2 =
	    stringAnnotationValueVisitor . visit ( annotationValue , null ) ;
	assertEquals ( expected , observed2 ) ;
    }

    /**
     * Tests the QualifiedNameStager.
     * {@link QualifiedNameStager}.
     *
     * I am looking to see that it will not mangle a good qualified name; and
     * that it will strip whitespaces out of a bad qualified name.
     *
     * Emory likes to write qualified names in the form
     * org . junit . Test
     * but for creating source files it has to be in the form
     * org.junit.Test (no whitespaces).
     **/
    @ Test
	public final void testQualifiedStager ( )
    {
	String a =
	    getTestQualifiedNameStagerA ( ) ;
	String b =
	    getTestQualifiedNameStagerB ( ) ;
	testQualifiedNameStager ( a , b ) ;
	testQualifiedNameStager ( b , b ) ;
    }

    /**
     * Tests the QualifiedNameStager.
     * {@link QualifiedNameStager}.
     *
     * @param value the value to test
     * @param expected the expected result
     **/
    private
	void
	testQualifiedNameStager
	(
	 final String value ,
	 final String expected
	 )
    {
	Stager < ? extends String , ? super String > qualifiedNameStager =
	    getQualifiedNameStager ( ) ;
	Object observed =
	    qualifiedNameStager . stage
	    ( value ) ;
	assertEquals ( expected , observed ) ;
    }

    /**
     * A string constant for testing.
     *
     * @return a string
     **/
    @ UseStringConstant ( "Mary had a little lamb." )
	abstract
	String
	getTestStringAnnotationValueVisitorA
	( ) ;

    /**
     * Gets a constant for testing.
     *
     * @return a string unsuitable for use as a qualified name
     *         because it has whitespaces
     **/
    @ UseStringConstant ( "      org .          junit .      Test " )
	abstract
	String
	getTestQualifiedNameStagerA
	( ) ;

    /**
     * Gets a constant for testing.
     *
     * @return a string suitable for use as a qualified name
     **/
    @ UseStringConstant ( "org.junit.Test" )
	abstract
	String
	getTestQualifiedNameStagerB
	( ) ;

    /**
     * Mock a class.
     *
     * @param <T> the class to mock
     * @param clazz the class to mock
     * @return a mock object
     **/
    @ UseStaticMethod ( org . mockito . Mockito . class )
	abstract < T > T mock ( Class < ? extends T > clazz ) ;

    /**
     * Mock a method call.
     *
     * @param <T> the class of the return type of the method
     * @param methodCall the return type of the method
     * @return an OngoingStubbing for mocking
     **/
    @ UseStaticMethod ( org . mockito . Mockito . class )
	abstract < T > OngoingStubbing < T > when ( T methodCall ) ;

    /**
     * Creates an annotation value visitor based on the
     * specified visitor and stager.
     *
     * @param <R> return type of the annotation value visitor
     * @param <A> connection type
     * @param <P> data type
     * @param visitor the specified visitor
     * @param stager the specified stager
     * @return an annotation value visitor
     **/
    @ UseConstructor ( StagerAnnotationValueVisitor . class )
	abstract
	< R , A , P >
	AnnotationValueVisitor < ? extends R , ? super P >
					   getStagerAnnotationValueVisitor
					   (
					    AnnotationValueVisitor < ? extends A , ? super P > //
					    visitor ,
					    Stager < ? extends R , ? super A >
					    stager
					    ) ;

    /**
     * Get an object for testing.
     *
     * @return an annotation value visitor that will return
     *         the annotation's string value
     **/
    @ UseConstructor ( StringAnnotationValueVisitor . class )
	abstract
	AnnotationValueVisitor < ? extends String , ? super Object >
					   getStringAnnotationValueVisitor
					   ( ) ;

    /**
     * Get an object for testing.
     *
     * @return an implementation qualified name annotation value visitor
     **/
    @ UseConstructor
	( QualifiedNameStager . class )
	abstract
	Stager < ? extends String , ? super String >
	getQualifiedNameStager
	( ) ;
}
