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

import java . util . HashMap ;
import java . util . HashSet ;
import java . util . Iterator ;
import java . util . Map ;
import java . util . Set ;
import javax . annotation . processing . RoundEnvironment ;
import javax . annotation . processing . ProcessingEnvironment ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . Name ;
import javax . lang . model . element . TypeElement ;
import org . junit . Assert ;
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
    public final void easyFail ( )
    {
	assertFalse ( true ) ;
    }
     **/

    /**
     * Tests the element value map iterator.
     **/
    @ Test ( expected = UnsupportedOperationException . class )
	public final void testElementValueIterator ( )
    {
	Set < Map . Entry < Element , AnnotationValue > > set = getSet ( ) ;
	Iterator < Map . Entry < Element , AnnotationValue > > input =
	    set . iterator ( ) ;
	Iterator < Map . Entry < String , AnnotationValue > >
	    elementValueIterator =
	    getElementValueIterator ( input ) ;
	boolean hasNext1 = elementValueIterator . hasNext ( ) ;
	assertFalse ( hasNext1 ) ;
	elementValueIterator . remove ( ) ;
    }

    /**
     * Tests the element value map.
     **/
    @ Test
	public final void testElementValueMap ( )
    {
	Map < Element , AnnotationValue > input = getMap ( ) ;
	Element key = mock ( Element . class ) ;
	AnnotationValue value = mock ( AnnotationValue . class ) ;
	input . put ( key , value ) ;
	Map < String , AnnotationValue > elementValueMap =
	    getElementValueMap ( input ) ;
	Set < Map . Entry < String , AnnotationValue > > elementValueSet =
	    elementValueMap . entrySet ( ) ;
    }

    /**
     * Tests the element value map entry.
     **/
    @ Test ( expected = UnsupportedOperationException . class )
	public final void testElementValueMapEntry ( )
    {
	Map . Entry < Element , AnnotationValue > input =
	    mock ( Map . Entry . class ) ;
	Element key = mock ( Element . class ) ;
	Name simpleName = mock ( Name . class ) ;
	String a = getTestElementValueMapEntryA ( ) ;
	when ( simpleName . toString ( ) ) . thenReturn ( a ) ;
	when ( key . getSimpleName ( ) ) . thenReturn ( simpleName ) ;
	when ( input . getKey ( ) ) . thenReturn ( key ) ;
	AnnotationValue value = mock ( AnnotationValue . class ) ;
	when ( input . getValue ( ) ) . thenReturn ( value ) ;
	Map . Entry < String , AnnotationValue > elementValueMapEntry =
	    getElementValueMapEntry ( input ) ;
	String string = elementValueMapEntry . getKey ( ) ;
	assertEquals ( a , string ) ;
	AnnotationValue annotationValue = elementValueMapEntry . getValue ( ) ;
	assertEquals ( value , annotationValue ) ;
	boolean equals1 =
	    elementValueMapEntry . equals ( elementValueMapEntry ) ;
	assertTrue ( equals1 ) ;
	boolean equals2 = elementValueMapEntry . equals ( a ) ;
	assertFalse ( equals2 ) ;
	int hashCode1 = input . hashCode ( ) ;
	int hashCode2 = elementValueMapEntry . hashCode ( ) ;
	assertEquals ( hashCode1 , hashCode2 ) ;
	elementValueMapEntry . setValue ( annotationValue ) ;
    }

    /**
     * Tests an ElementValueSet.
     **/
    @ Test
	public final void testElementValueSet ( )
    {
	Set < Map . Entry < Element , AnnotationValue > > input = getSet ( ) ;
	Set < Map . Entry < String , AnnotationValue > > elementValueSet =
	    getElementValueSet ( input ) ;
	int size = elementValueSet . size ( ) ;
	assertEquals ( 0 , size ) ;
	Iterator < Map . Entry < String , AnnotationValue > >
	    elementValueIterator =
	    elementValueSet . iterator ( ) ;
    }

    /**
     * Tests a NullWriterFactory (for coverage).
     **/
    @ Test ( expected = UnsupportedOperationException . class )
	public final void testNullWriterFactory ( )
    {
	WriterFactory nullWriterFactory = getNullWriterFactory ( ) ;
	nullWriterFactory . make ( null , null , null , null ) ;
    }

    /**
     * Tests a processor.
     **/
    @ Test
	public final void testProcessor ( )
    {
	Processor processor = getProcessor ( ) ;
	ProcessingEnvironment processingEnvironment =
	    mock ( ProcessingEnvironment . class ) ;
	processor . init ( processingEnvironment ) ;
	Set < String > supportedAnnotationTypes =
	    processor . getSupportedAnnotationTypes ( ) ;
	int size = supportedAnnotationTypes . size ( ) ;
	assertEquals ( 1 , size ) ;
	String a = getTestProcessorA ( ) ;
	Iterator < String > iterator = supportedAnnotationTypes . iterator ( ) ;
	boolean hasNext1 = iterator . hasNext ( ) ;
	assertTrue ( hasNext1 ) ;
	String observed = iterator . next ( ) ;
	assertEquals ( a , observed ) ;
	boolean hasNext2 = iterator . hasNext ( ) ;
	assertFalse ( hasNext2 ) ;
	Set < TypeElement > annotations = getSet ( ) ;
	TypeElement annotation = mock ( TypeElement . class ) ;
	annotations . add ( annotation ) ;
	RoundEnvironment roundEnvironment = mock ( RoundEnvironment . class ) ;
	boolean process =
	    processor . process ( annotations , roundEnvironment ) ;
	assertTrue ( process ) ;
    }

    /**
     * Tests the iterator stager.
     **/
    @ Test
	public final void testIteratorStager ( )
    {
	Stager < ? , ? super Object > stager = mock ( Stager . class ) ;
	Object expected = mock ( Object . class ) ;
	when ( stager . stage ( expected ) ) . thenReturn ( expected ) ;
	Stager < ? extends Iterable < ? > , ? super Iterator < ? > > iteratorStager = getIteratorStager ( stager ) ;
	Set < Object > set = getSet ( ) ;
	set . add ( expected ) ;
	Iterator < ? > iterator = set . iterator ( ) ;
	Iterable < ? > iterable = iteratorStager . stage ( iterator ) ;
	Iterator < ? > test = iterable . iterator ( ) ;
	boolean hasNext1 = test . hasNext ( ) ;
	assertTrue ( hasNext1 ) ;
	Object observed = test . next ( ) ;
	assertEquals ( expected , observed ) ;
	boolean hasNext2 = test . hasNext ( ) ;
	assertFalse ( hasNext2 ) ;
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
	AnnotationValueVisitor < Object , Object > visitor =
	    mock ( AnnotationValueVisitor . class ) ;
	String a = getTestStagerAnnotationValueVisitorA ( ) ;
	Object expected = mock ( Object . class ) ;
	when ( visitor . visitString ( a , null ) ) . thenReturn ( expected ) ;
	Stager < Object , Object > stager = mock ( Stager . class ) ;
	when ( stager . stage ( expected ) ) . thenReturn ( expected ) ;
	AnnotationValueVisitor < Object , Object >
	    stagerAnnotationValueVisitor =
	    getStagerAnnotationValueVisitor
	    ( visitor , stager ) ;
	Object observed =
	    stagerAnnotationValueVisitor . visitString ( a , null ) ;
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
     * Tests the AnnotationValueVisitorStager.
     * {@link AnnotationValueVisitorStager}
     **/
    @ Test
	public final void testAnnotationValueVisitorStager ( )
    {
	Stager < AnnotationValue , String > stager = mock ( Stager . class ) ;
	String a = getTestAnnotationValueVisitorStagerA ( ) ;
	AnnotationValue b = mock ( AnnotationValue . class ) ;
	when ( stager . stage ( a ) ) . thenReturn ( b ) ;
	AnnotationValueVisitor < String , Object > visitor =
	    mock ( AnnotationValueVisitor . class ) ;
	when ( visitor . visit ( b , null ) ) . thenReturn ( a ) ;
	Stager < ? extends String , ? super String >
	    annotationValueVisitorStager =
	    getAnnotationValueVisitorStager ( stager , visitor , null ) ;
	String c = annotationValueVisitorStager . stage ( a ) ;
	assertEquals ( a , c ) ;
    }

    /**
     * Tests the ElementValueStager.
     * {@link ElementValueStager}
     **/
    @ Test
	public final void testElementValueStager ( )
    {
	String target = getTestElementValueStagerA ( ) ;
	Stager < ? extends AnnotationValue , ? super Map < ? extends Element , ? extends AnnotationValue > > //
	    elementValueStager =
	    getElementValueStager ( target ) ;
	Map < Element , AnnotationValue > map = getMap ( ) ;
	Element key = mock ( Element . class ) ;
	Name simpleName = mock ( Name . class ) ;
	when ( simpleName . toString ( ) ) . thenReturn ( target ) ;
	when ( key . getSimpleName ( ) ) . thenReturn ( simpleName ) ;
	AnnotationValue expected = mock ( AnnotationValue . class ) ;
	map . put ( key , expected ) ;
	AnnotationValue observed = elementValueStager . stage ( map ) ;
	assertEquals ( expected , observed ) ;
    }

    /**
     * Tests the PackageStatementStager.
     * {@link PackageStatementStager}
     **/
    @ Test
	public final void testPackageStagementStager ( )
    {
	Stager < ? extends String , ? super String > packageStatementStager =
	    getPackageStatementStager ( ) ;
	String a = getTestPackageStatementStagerA ( ) ;
	String ab = packageStatementStager . stage ( a ) ;
	String b = getTestPackageStatementStagerB ( ) ;
	assertEquals ( b , ab ) ;
	String c = getTestPackageStatementStagerC ( ) ;
	String cd = packageStatementStager . stage ( c ) ;
	String d = getTestPackageStatementStagerD ( ) ;
	assertEquals ( d , cd ) ;
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
     * Tests the SingleJoinStager.
     *
     * {@link SingleJoinStager}
     **/
    @ Test
	public final void testSingleJoinStager ( )
    {
	Stager < ? extends Object , ? super Object > alpha =
	    mock ( Stager . class ) ;
	Object expected = mock ( Object . class ) ;
	when ( alpha . stage ( expected ) ) . thenReturn ( expected ) ;
	Stager < ? extends Object , ? super Object > beta =
	    mock ( Stager . class ) ;
	when ( beta . stage ( expected ) ) . thenReturn ( expected ) ;
	Stager < ? extends Object , ? super Object > singleJoinStager =
	getSingleJoinStager ( alpha , beta ) ;
	Object observed = singleJoinStager . stage ( expected ) ;
	assertEquals ( expected , observed ) ;
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
     * The name of the supported annotation type.
     *
     * @return a constant for testing
     **/
    @ UseStringConstant ( "tastytungsten.Implementation" )
	abstract String getTestProcessorA ( ) ;

    /**
     * A string for testing.
     *
     * @return a testing constant
     **/
    @ UseStringConstant ( "value" )
	abstract String getTestElementValueMapEntryA ( ) ;

    /**
     * A string constant for testing.
     *
     * @return a string
     **/
    @ UseStringConstant ( "Mary had a little lamb." )
	abstract
	String
	getTestStagerAnnotationValueVisitorA
	( ) ;

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
     * A string constant for testing.
     *
     * @return a string
     **/
    @ UseStringConstant ( "apple" )
	abstract
	String
	getTestAnnotationValueVisitorStagerA
	( ) ;

    /**
     * A string constant for testing.
     *
     * @return a string
     **/
    @ UseStringConstant ( "silly" )
	abstract
	String
	getTestElementValueStagerA
	( ) ;

    /**
     * A string constant for testing.
     *
     * @return a qualified name with package part
     **/
    @ UseStringConstant ( "tastytungsten .     StringConstantClass" )
	abstract String getTestPackageStatementStagerA ( ) ;

    /**
     * A string constant for testing.
     *
     * @return the package statement for
     * {@link #getTestPackageStatementStagerA}
     **/
    @ UseStringConstant ( "package tastytungsten ;" )
	abstract String getTestPackageStatementStagerB ( ) ;

    /**
     * A string constant for testing.
     *
     * @return a qualified name without package part
     **/
    @ UseStringConstant ( "StringConstantClass      " )
	abstract String getTestPackageStatementStagerC ( ) ;

    /**
     * A string constant for testing.
     *
     * @return the package statement for
     * {@link #getTestPackageStatementStagerC}
     **/
    @ UseStringConstant ( "" )
	abstract String getTestPackageStatementStagerD ( ) ;

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
     * Assert that the predicate is true.
     *
     * @param predicate the predicate to assert
     **/
    @ UseStaticMethod ( Assert . class )
	abstract void assertTrue ( boolean predicate ) ;

    /**
     * Assert that the predicate is false.
     *
     * @param predicate the predicate to assert
     **/
    @ UseStaticMethod ( Assert . class )
	abstract void assertFalse ( boolean predicate ) ;

    /**
     * Assert that the expected = observed.
     *
     * @param expected the expected value
     * @param observed the observed value
     **/
    @ UseStaticMethod ( Assert . class )
	abstract void assertEquals ( Object expected , Object observed ) ;

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
     * Constructs a map.
     * No need for mocking.
     * It is easier to use a real map.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a map
     **/
    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;

    /**
     * Constructs a set.
     * No need for mocking.
     * It is easier to use a real set.
     *
     * @param <T> the type of set
     * @return a set
     **/
    @ UseConstructor ( HashSet . class )
	abstract < T > Set < T > getSet ( ) ;

    /**
     * Get an element value iterator for testing.
     *
     * @param <V> the parameter type of the iterator
     * @param input the underlying data
     * @return an element value iterator
     **/
    @ UseConstructor ( ElementValueIterator . class )
	abstract
	< V >
	Iterator < Map . Entry < String , V > >
	getElementValueIterator
	( Iterator < Map . Entry < Element , V > > input ) ;

    /**
     * Gets an element value map for testing.
     *
     * @param <V> the parameter type of the map
     * @param input the underlying data
     * @return an element value map
     **/
    @ UseConstructor ( ElementValueMap . class )
	abstract
	< V >
	Map < String , V >
	getElementValueMap
	( Map < ? extends Element , ? extends V > input ) ;

    /**
     * Get an element value map entry for testing.
     *
     * @param <V> the parameter type of the map
     * @param input the underlying data
     * @return an element value map entry
     **/
    @ UseConstructor ( ElementValueMapEntry . class )
	abstract
	< V >
	Map . Entry < String , V >
	getElementValueMapEntry
	( Map . Entry < Element , V > input ) ;

    /**
     * Get an element value set for testing.
     *
     * @param <V> the parameter type of the set
     * @param input the underlying data
     * @return an element value set
     **/
    @ UseConstructor ( ElementValueSet . class )
	abstract
	< V >
	Set < Map . Entry < String , V > >
	getElementValueSet
	( Set < Map . Entry < Element , V > > input ) ;

    /**
     * Get a NullWriterFactory for testing (for coverage).
     *
     * @return a null writer factory
     **/
    @ UseConstructor ( NullWriterFactory . class )
	abstract
	WriterFactory
	getNullWriterFactory
	( ) ;

    /**
     * Gets a processor for testing.
     *
     * @return a processor
     **/
    @ UseConstructor ( Processor . class )
	abstract Processor getProcessor ( ) ;

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
	AnnotationValueVisitor < R , P >
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
     * Creates a stager based on the specified
     * visitor and stager.
     *
     * @param <R> the return type
     * @param <A> the connection type
     * @param <P> the user data type
     * @param stager the specified stager
     * @param visitor the specified visitor
     * @param data user data
     * @return an annotation value visitor stager for testing
     **/
    @ UseConstructor ( AnnotationValueVisitorStager . class )
	abstract
	< R , A , P >
	Stager < ? extends R , ? super A >
			   getAnnotationValueVisitorStager
			   (
			    Stager < ? extends AnnotationValue , ? super A >
			    stager ,
			    AnnotationValueVisitor < ? extends R , ? super P > //
			    visitor ,
			    P data
			    ) ;

    /**
     * Get an object for testing.
     * This stager should be useful with respect to getting
     * element values off annotation mirrors.
     * An annotation mirror has a
     * {@link AnnotationMirror#getElementValues()} method.
     * (And element utils has a
     * {@link Elements#getElementValuesWithDefaults(AnnotationMirror)}
     * method.)
     * They both return annotations in the form
     * of a
     * {@link Map}
     * with key parameter
     * {@link ExecutableElement}.
     * This is no good because it is not easy for us to
     * create key values.
     *
     * @param <V> usually {@link AnnotationValue}
     * @param target indicates which one of the element values
     *        should be taken.
     *        basically this is the simple name in string form
     * @return an ElementValuesStager
     **/
    @ UseConstructor ( ElementValueStager . class )
	abstract
	< V >
	Stager < ? extends V , ? super Map < ? extends Element , ? extends V > > //
									   getElementValueStager //
									   ( //
									    Object target //
									     ) ; //

    /**
     * Gets an IteratorStager for testing.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param stager the stager
     * @return an iterator stager
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
     * Get an object for testing.
     *
     * @return a package statement stager
     **/
    @ UseConstructor ( PackageStatementStager . class )
	abstract
	Stager < ? extends String , ? super String >
			   getPackageStatementStager
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
