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
    public abstract class Tests
{
    /**
     * Constructs a Tests.
     **/
    public Tests ( )
    {
    }

    /**
     * Tests a MapItemStager.
     **/
    @ Test
	public final void testMapItemStager ( )
    {
	Object expected = mock ( Object . class ) ;
	Stager < ? extends Object , ? extends Map < ? extends Object , ? extends Object > >
	    mapItemStager =
	    getMapItemStager ( expected ) ;
	Map < ? extends Object , ? extends Object > map = mock ( Map . class ) ;
	when ( map . get ( expected ) ) . thenReturn ( expected ) ;
	Object observed = mapItemStager . stage ( map ) ;
	assertEquals ( expected , observed ) ;
    }

    /**
     * Constructs a MapItemStager for testing.
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
     * Tests a QualifiedNameElementVisitor.
     **/
    @ Test
	public final void testQualifiedNameElementVisitor ( )
    {
	ElementVisitor < ? extends Name , ? super Object >
	    qualifiedNameElementVisitor =
	    getQualifiedNameElementVisitor ( ) ;
	TypeElement element = mock ( TypeElement . class ) ;
	Name expected = mock ( Name . class ) ;
	when ( element . getQualifiedName ( ) ) . thenReturn ( expected ) ;
	Name observed =
	    qualifiedNameElementVisitor . visitType ( element , null ) ;
	assertEquals ( expected , observed ) ;
    }

    /**
     * Gets a QualifiedNameElementVisitor.
     *
     * @return a QualifiedNameElementVisitor
     **/
    @ UseConstructor ( QualifiedNameElementVisitor . class )
	abstract
	ElementVisitor < ? extends Name , ? super Object >
				   getQualifiedNameElementVisitor
				   ( ) ;

    /**
     *
     **/
    @ Test
	public final void testAnnotationMirrorKeyStager ( )
    {
	Stager < ? extends String , ? super AnnotationMirror >
	    annotationMirrorKeyStager =
	    getAnnotationMirrorKeyStager ( ) ;
	AnnotationMirror annotationMirror = mock ( AnnotationMirror . class ) ;
	DeclaredType annotationType = mock ( DeclaredType . class ) ;
	TypeElement annotationElement = mock ( TypeElement . class ) ;
	Name qualifiedName = mock ( Name . class ) ;
	String expected = qualifiedName . toString ( ) ;
	when ( qualifiedName . toString ( ) ) . thenReturn ( expected ) ;
	ElementVisitor < ? extends Name , ? super Object > elementVisitor =
	    any ( ) ;
	Object data = any ( ) ;
	when ( annotationElement . accept ( elementVisitor , data ) ) . thenReturn ( qualifiedName ) ; //
	when ( annotationType . asElement ( ) ) . thenReturn ( annotationElement ) ; //
	when ( annotationMirror . getAnnotationType ( ) ) . thenReturn ( annotationType ) ; //
	String observed =
	    annotationMirrorKeyStager . stage ( annotationMirror ) ;
	assertEquals ( expected , observed ) ;
    }

    /**
     * Gets a stager that will get the fully qualified name
     * of an annotation type.
     *
     * @return an AnnotationMirrorKeyStager
     **/
    @ UseConstructor ( AnnotationMirrorKeyStager . class )
	abstract
	Stager < ? extends String , ? super AnnotationMirror >
			   getAnnotationMirrorKeyStager
			   ( ) ;

    /**
     * Test an IdentityStager.
     **/
    @ Test
	public final void testIdentityStager ( )
    {
	Stager < ? extends Object , ? super Object > identityStager =
	    getIdentityStager ( ) ;
	Object expected = mock ( Object . class ) ;
	Object observed = identityStager . stage ( expected ) ;
	assertEquals ( expected , observed ) ;
    }

    /**
     * Gets an identity stager.
     *
     * @param <R> the type of the identity
     * @return a stager that will convert something to itself
     **/
    @ UseConstructor ( IdentityStager . class )
	abstract
	< R >
	Stager < ? extends R , ? super R >
			   getIdentityStager
			   ( ) ;

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
     * Tests a StagerIterable.
     **/
    @ Test
	public final void testStagerIterable ( )
    {
	Iterable < ? > iterable = mock ( Iterable . class ) ;
	Stager < ? , ? super Object > stager = mock ( Stager . class ) ;
	Iterable < ? > stagerIterable =
	    getStagerIterable ( iterable , stager ) ;
	Iterator < ? > iterator = stagerIterable . iterator ( ) ;
    }

    /**
     * Tests a StagerIterator.
     **/
    @ Test ( expected = UnsupportedOperationException . class )
	public final void testStagerIterator ( )
    {
	Iterator < ? > iterator = mock ( Iterator . class ) ;
	Object expected = mock ( Object . class ) ;
	when ( iterator . next ( ) ) . thenReturn ( expected ) ;
	Stager < ? , ? super Object > stager = mock ( Stager . class ) ;
	when ( stager . stage ( expected ) ) . thenReturn ( expected ) ;
	Iterator < ? > stagerIterator =
	    getStagerIterator ( iterator , stager ) ;
	boolean hasNext1 = iterator . hasNext ( ) ;
	boolean hasNext2 = stagerIterator . hasNext ( ) ;
	assertEquals ( hasNext1 , hasNext2 ) ;
	Object observed = stagerIterator . next ( ) ;
	assertEquals ( expected , observed ) ;
	stagerIterator . remove ( ) ;
    }

    /**
     * Tests a StagerMapEntry.
     **/
    @ Test ( expected = UnsupportedOperationException . class )
	public final void testStagerMapEntry ( )
    {
	Object expectedItem = mock ( Object . class ) ;
	Stager < ? extends Object , ? super Object > keyStager =
	    mock ( Stager . class ) ;
	Object expectedKey = mock ( Object . class ) ;
	when ( keyStager . stage ( expectedItem ) ) . thenReturn ( expectedKey ) ; //
	Stager < ? extends Object , ? super Object > valueStager =
	    mock ( Stager . class ) ;
	Object expectedValue = mock ( Object . class ) ;
	when ( valueStager . stage ( expectedItem ) ) . thenReturn ( expectedValue ) ; //
	Map . Entry < Object , Object > stagerMapEntry =
	    getStagerMapEntry ( expectedItem , keyStager , valueStager ) ;
	Object observedKey = stagerMapEntry . getKey ( ) ;
	assertEquals ( expectedKey , observedKey ) ;
	Object observedValue = stagerMapEntry . getValue ( ) ;
	assertEquals ( expectedValue , observedValue ) ;
	boolean equals1 = stagerMapEntry . equals ( stagerMapEntry ) ;
	assertTrue ( equals1 ) ;
	boolean equals2 = stagerMapEntry . equals ( expectedItem ) ;
	assertFalse ( equals2 ) ;
	Object alternativeExpectedItem = mock ( Object . class ) ;
	Stager < ? extends Object , ? super Object > alternativeKeyStager =
	    mock ( Stager . class ) ;
	Stager < ? extends Object , ? super Object > alternativeValueStager =
	    mock ( Stager . class ) ;
	testStagerMapEntryEquality
	    (
	     stagerMapEntry ,
	     expectedItem ,
	     keyStager ,
	     alternativeValueStager
	     ) ;
	testStagerMapEntryEquality
	    (
	     stagerMapEntry ,
	     expectedItem ,
	     alternativeKeyStager ,
	     valueStager
	     ) ;
	testStagerMapEntryEquality
	    (
	     stagerMapEntry ,
	     expectedItem ,
	     alternativeKeyStager ,
	     alternativeValueStager
	     ) ;
	testStagerMapEntryEquality
	    (
	     stagerMapEntry ,
	     alternativeExpectedItem ,
	     keyStager ,
	     valueStager
	     ) ;
	testStagerMapEntryEquality
	    (
	     stagerMapEntry ,
	     alternativeExpectedItem ,
	     keyStager ,
	     alternativeValueStager
	     ) ;
	testStagerMapEntryEquality
	    (
	     stagerMapEntry ,
	     alternativeExpectedItem ,
	     alternativeKeyStager ,
	     valueStager
	     ) ;
	testStagerMapEntryEquality
	    (
	     stagerMapEntry ,
	     alternativeExpectedItem ,
	     alternativeKeyStager ,
	     alternativeValueStager
	     ) ;
	int hashCode1 = expectedItem . hashCode ( ) ;
	int hashCode2 = stagerMapEntry . hashCode ( ) ;
	assertEquals ( hashCode1 , hashCode2 ) ;
	stagerMapEntry . setValue ( expectedValue ) ;
    }

    /**
     * Creates a different stager map entry and tests it for equality.
     *
     * @param stagerMapEntry the first stager map entry
     * @param item for creating the second stager map entry
     * @param keyStager for creating the second stager map entry
     * @param valueStager for creating the second stager map entry
     **/
    private
	void
	testStagerMapEntryEquality
	(
	 final Map . Entry < Object , Object > stagerMapEntry ,
	 final Object item ,
	 final Stager < ? extends Object , ? super Object > keyStager ,
	 final Stager < ? extends Object , ? super Object > valueStager
	 )
    {
	Map . Entry < Object , Object > o =
	    getStagerMapEntry ( item , keyStager , valueStager ) ;
	boolean equals = stagerMapEntry . equals ( o ) ;
	assertFalse ( equals ) ;
    }

    /**
     * Tests a StagerMapEntryIterator.
     **/
    @ Test ( expected = UnsupportedOperationException . class )
	public final void testStagerMapEntryIterator ( )
    {
	Iterator < ? extends Object > iterator = mock ( Iterator . class ) ;
	Stager < Object , Object > keyStager = mock ( Stager . class ) ;
	Stager < Object , Object > valueStager = keyStager ;
	Iterator < Map . Entry < Object , Object > >
	    stagerMapEntryIterator =
	    getStagerMapEntryIterator ( iterator , keyStager , valueStager ) ;
	boolean hasNext1 = iterator . hasNext ( ) ;
	boolean hasNext2 = stagerMapEntryIterator . hasNext ( ) ;
	assertEquals ( hasNext1 , hasNext2 ) ;
	stagerMapEntryIterator . next ( ) ;
	stagerMapEntryIterator . remove ( ) ;
    }

    /**
     * Tests a Stager Map Entry.
     **/
    @ Test
	public final void testStagerMapEntrySet ( )
    {
	Collection < Object > collection = mock ( Collection . class ) ;
	Stager < Object , Object > keyStager = mock ( Stager . class ) ;
	Stager < Object , Object > valueStager = keyStager ;
	Set < Map . Entry < Object , Object > > stagerMapEntrySet =
	    getStagerMapEntrySet ( collection , keyStager , valueStager ) ;
	int size1 = collection . size ( ) ;
	int size2 = stagerMapEntrySet . size ( ) ;
	assertEquals ( size1 , size2 ) ;
	Object iterator = stagerMapEntrySet . iterator ( ) ;
    }

    /**
     * Tests a StagerMap.
     **/
    @ Test
	public final void testStagerMap ( )
    {
	Collection < Object > collection = mock ( Collection . class ) ;
	Stager < Object , Object > keyStager = mock ( Stager . class ) ;
	Stager < Object , Object > valueStager = keyStager ;
	Map < Object , Object > stagerMap =
	    getStagerMap ( collection , keyStager , valueStager ) ;
	Object entrySet = stagerMap . entrySet ( ) ;
    }

    /**
     *
     **/
    @ Test
	public final void testMapStager ( )
    {
	Stager < Object , Object > keyStager = mock ( Stager . class ) ;
	Stager < Object , Object > valueStager = keyStager ;
	Stager < Map < ? extends Object , ? extends Object > , Collection < ? extends Object > > //
	    mapStager =
	    getMapStager ( keyStager , valueStager ) ;
	Collection < Object > collection = mock ( Collection . class ) ;
	Object map = mapStager . stage ( collection ) ;
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
	MockRoundEnvironment roundEnvironment =
	    mock ( MockRoundEnvironment . class ) ;
	when ( roundEnvironment . getElementsAnnotatedWith ( annotation ) ) .
	    thenReturn ( null ) ;
	boolean process =
	    processor . process ( annotations , roundEnvironment ) ;
	assertTrue ( process ) ;
    }

    /**
     * Tests the iterable stager.
     **/
    @ Test
	public final void testIterableStager ( )
    {
	Stager < ? , ? super Object > stager = mock ( Stager . class ) ;
	Object expected = mock ( Object . class ) ;
	when ( stager . stage ( expected ) ) . thenReturn ( expected ) ;
	Stager < ? extends Iterable < ? > , ? super Iterable < ? > >
	    iterableStager =
	    getIterableStager ( stager ) ;
	Set < Object > set = getSet ( ) ;
	set . add ( expected ) ;
	Iterable < ? > iterable = iterableStager . stage ( set ) ;
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
     * Test the processor stager.
     * This is the one that does all the work.
     **/
    @ Test
	public final void testProcessorStager ( )
    {
	Stager < ? , ? super Element > processorStager =
	    getProcessorStager ( ) ;
	Element element = mock ( Element . class ) ;
	Object object = processorStager . stage ( element ) ;
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
     * Any object or null.
     *
     * @param <T> the type of the object
     * @return an object that will match any object or null
     **/
    @ UseStaticMethod ( org . mockito . Matchers . class )
	abstract < T > T any ( ) ;

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
     * Get a StagerIterable for testing.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param iterable the input data
     * @param stager for conversion
     * @return a StagerIterable
     **/
    @ UseConstructor ( StagerIterable . class )
	abstract
	< R , P >
	Iterable < ? extends R >
			     getStagerIterable
			     (
			      Iterable < ? extends P > iterable ,
			      Stager < ? extends R , ? super P > stager
			      ) ;

    /**
     * Get a StagerIterator for testing.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param iterator the input iterator
     * @param stager for conversion
     * @return a StagerIterator
     **/
    @ UseConstructor ( StagerIterator . class )
	abstract
	< R , P >
	Iterator < ? extends R >
			     getStagerIterator
			     (
			      Iterator < ? extends P > iterator ,
			      Stager < ? extends R , ? super P > stager
			      ) ;

    /**
     * Gets a stager map entry for testing.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param item the underlying data
     * @param keyStager for producing the key
     * @param valueStager for producing the value
     * @return a stager that converts a collection of Ps into
     *         a K , V map
     **/
    @ UseConstructor ( StagerMapEntry . class )
	abstract
	< K , V , P >
	Map . Entry < K , V >
	getStagerMapEntry
	(
	 P item ,
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;

    /**
     * Gets a stager map entry iterator for testing.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param iterator the underlying data
     * @param keyStager for producing the key
     * @param valueStager for producing the value
     * @return a stager that converts a collection of Ps into
     *         a K , V map
     **/
    @ UseConstructor ( StagerMapEntryIterator . class )
	abstract
	< K , V , P >
	Iterator < Map . Entry < K , V > >
	getStagerMapEntryIterator
	(
	 Iterator < ? extends P > iterator ,
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;

    /**
     * Gets a stager map entry set for testing.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param collection the underlying data
     * @param keyStager for producing the key
     * @param valueStager for producing the value
     * @return a stager that converts a collection of Ps into
     *         a K , V map
     **/
    @ UseConstructor ( StagerMapEntrySet . class )
	abstract
	< K , V , P >
	Set < Map . Entry < K , V > >
	getStagerMapEntrySet
	(
	 Collection < ? extends P > collection ,
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;

    /**
     * Gets a stager map for testing.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param collection the underlying data
     * @param keyStager for producing the key
     * @param valueStager for producing the value
     * @return a stager that converts a collection of Ps into
     *         a K , V map
     **/
    @ UseConstructor ( StagerMap . class )
	abstract
	< K , V , P >
	Map < K , V >
	getStagerMap
	(
	 Collection < ? extends P > collection ,
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;

    /**
     * Gets a map stager for testing.
     *
     * @param <K> key type
     * @param <V> value type
     * @param <P> data type
     * @param keyStager for producing the key
     * @param valueStager for producing the value
     * @return a stager that converts a collection of Ps into
     *         a K , V map
     **/
    @ UseConstructor ( MapStager . class )
	abstract
	< K , V , P >
	Stager < Map < ? extends K , ? extends V > , Collection < ? extends P > > //
	getMapStager
	(
	 Stager < ? extends K , ? super P > keyStager ,
	 Stager < ? extends V , ? super P > valueStager
	 ) ;

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
     * Gets an IterableStager for testing.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param stager the stager
     * @return an iterable stager
     **/
    @ UseConstructor ( IterableStager . class )
	abstract
	< R , P >
	Stager < ? extends Iterable < ? extends R > , ? super Iterable < ? extends P > > //
										   getIterableStager //
										   ( //
										    Stager < ? extends R , ? super P > //
										    stager //
										    ) ; //


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
     * Get a ProcessorStager for testing.
     *
     * @return a processor stager.
     **/
    @ UseConstructor ( ProcessorStager . class )
	abstract
	Stager < ? , ? super Element >
	getProcessorStager
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
