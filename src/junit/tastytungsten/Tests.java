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
import java . util . Iterator ;
import java . util . List ;
import java . util . Map ;
import java . util . Set ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . element . Name ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . type . DeclaredType ;
import javax . lang . model . util . Elements ;
import org . junit . Assert ;
import org . mockito . Mockito ;
import org . mockito . stubbing . OngoingStubbing ;

/**
 * The standard suite of tests.
 **/
@ SuppressWarnings ( "unchecked" )
    public abstract class Tests
{
    public Tests ( )
    {
    }

    void testAllAnnotationMirrorsTransformer_transform ( @ UseMock AllAnnotationMirrorsTransformer allAnnotationMirrorsTransformer , @ UseMock Element element , @ UseMock MockElements elementUtils , @ UseMock List < AnnotationMirror > expected , @ UseStringConstant ( "elementUtils=%s" ) String format )
    {
	when ( elementUtils . getAllAnnotationMirrors ( element ) ) . thenReturn ( expected ) ;
	List < ? extends AnnotationMirror > observed = allAnnotationMirrorsTransformer . transform ( element , elementUtils ) ;
	assertEquals ( expected , observed , format , elementUtils ) ;
    }

    void testAllAnnotationMirrorsTransformer ( @ UseMock Elements elementUtils , @ UseStringConstant ( "elementUtils=%s" ) String format )
    {
	Object allAnnotationMirrorsTransformer = getAllAnnotationMirrorsTransformer ( elementUtils ) ;
	assertNotNull ( allAnnotationMirrorsTransformer , format , elementUtils ) ;
    }

    @ UseConstructor ( AllAnnotationMirrorsTransformer . class )
	abstract Transformer < ? extends Collection < ? extends AnnotationMirror > , ? super Element > getAllAnnotationMirrorsTransformer ( Elements elementUtils ) ;

    final void testAnnotationMirrorKeyTransformer_transform ( @ UseMock AnnotationMirrorKeyTransformer annotationMirrorKeyTransformer , @ UseMock ElementVisitor < Name , Object > qualifiedNameElementVisitor , @ UseMock Name qualifiedName , @ UseMock AnnotationMirror annotationMirror , @ UseMock DeclaredType annotationType , @ UseMock Element annotationElement , @ UseStringConstant ( "The key of an annotation mirror is the qualified name (%s)) of its annotation type's (%s) element (%s)." ) final String format )
    {
	when ( annotationMirrorKeyTransformer . getQualifiedNameElementVisitor ( ) ) . thenReturn ( qualifiedNameElementVisitor ) ;
	when ( qualifiedNameElementVisitor . visit ( annotationElement ) ) . thenReturn ( qualifiedName ) ;
	when ( annotationMirror . getAnnotationType ( ) ) . thenReturn ( annotationType ) ;
	when ( annotationType . asElement ( ) ) . thenReturn ( annotationElement ) ;
	String expected = qualifiedName . toString ( ) ;
	String observed = annotationMirrorKeyTransformer . transform ( annotationMirror ) ;
	String message = format ( format , qualifiedName , annotationType , annotationElement ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testAnnotationMirrorKeyTransformer ( @ UseStringConstant ( "An AnnotationMirrorKeyTransformer has no parameters." ) final String message )
    {
	Object annotationMirrorKeyTransformer = getAnnotationMirrorKeyTransformer ( ) ;
	assertNotNull ( message , annotationMirrorKeyTransformer ) ;
    }

    @ UseConstructor ( AnnotationMirrorKeyTransformer . class )
	abstract Transformer < ? extends String , ? super AnnotationMirror > getAnnotationMirrorKeyTransformer ( ) ;

    final void testAnnotationValueVisitorTransformer_transform ( @ UseMock final AnnotationValueVisitorTransformer < Object , Object , Object > annotationValueVisitorTransformer , @ UseMock final Object value , @ UseMock final AnnotationValueVisitor < ? , ? super Object > visitor , @ UseMock final Transformer < ? extends AnnotationValue , ? super Object > transformer , @ UseMock final AnnotationValue annotationValue , @ UseMock final Object data , @ UseMock final Object expected , @ UseStringConstant ( "Build a transformer by connecting a specified AnnotationValueVisitor (%s) to a specified Transformer (%s).  The transformer transforms input data (%s) into an AnnotationValue (%s).  The AnnotationValueVisitor visits the AnnotationValue (along with specified data (%s)) and the result is the output (%s)." ) final String format )
    {
	when ( transformer . transform ( value ) ) . thenReturn ( annotationValue ) ;
	when ( visitor . visit ( annotationValue , data ) ) . thenReturn ( expected ) ;
	Object observed = annotationValueVisitorTransformer . transform ( value , visitor , transformer , data ) ;
	String message = format ( format , visitor , transformer , value , annotationValue , data , observed ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testAnnotationValueVisitorTransformer ( @ UseMock final AnnotationValueVisitor < ? , ? super Object > visitor , @ UseMock final Transformer < ? extends AnnotationValue , ? super Object > transformer , @ UseMock final Object data , @ UseStringConstant ( "An AnnotationValueVisitorTransformer has 3 parameters:  a visitor (%s), a transformer (%s), and user data (%s)." ) final String format )
    {
	Object annotationValueVisitorTransformer = getAnnotationValueVisitorTransformer ( visitor , transformer , data ) ;
	String message = format ( format , visitor , transformer , data ) ;
	assertNotNull ( message , annotationValueVisitorTransformer ) ;
    }

    @ UseConstructor ( AnnotationValueVisitorTransformer . class )
	abstract < R , A , P > Transformer < ? extends R , ? super A > getAnnotationValueVisitorTransformer ( AnnotationValueVisitor < ? extends R , ? super P > visitor , Transformer < ? extends AnnotationValue , ? super A > transformer , P data ) ;

    abstract class MockElementKeyTransformer extends ElementKeyTransformer
    {
	abstract ElementVisitor < Object , Object > getQualifiedNameElementVisitor ( ) ;
    }

    final void testElementKeyTransformer_transform ( @ UseMock MockElementKeyTransformer elementKeyTransformer , @ UseMock ElementVisitor < Object , Object > qualifiedNameElementVisitor , @ UseMock Element element , @ UseMock Object qualifiedName , @ UseStringConstant ( "" ) final String format )
    {
	when ( elementKeyTransformer . getQualifiedNameElementVisitor ( ) ) . thenReturn ( qualifiedNameElementVisitor ) ;
	when ( qualifiedNameElementVisitor . visit ( element ) ) . thenReturn ( qualifiedName ) ;
	String expected = qualifiedName . toString ( ) ;
	String observed = elementKeyTransformer . transform ( element ) ;
	String message = format ( format ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testElementKeyTransformer ( @ UseStringConstant ( "From a list of elements to a map keyed with the qualified name of the elements." ) final String format )
    {
	Object elementKeyTransformer = getElementKeyTransformer ( ) ;
	String message = format ( format ) ;
	assertNotNull ( message , elementKeyTransformer ) ;
    }

    @ UseConstructor ( ElementKeyTransformer . class )
	abstract Transformer < ? , ? super Element > getElementKeyTransformer ( ) ;

    abstract class MockRoundEnvironment implements RoundEnvironment
    {
	@ Override
	    public abstract Set < Element > getElementsAnnotatedWith ( TypeElement annotation ) ;
    }

    final void testElementsAnnotatedWithTransformer_transform ( @ UseMock ElementsAnnotatedWithTransformer elementsAnnotatedWithTransformer , @ UseMock MockRoundEnvironment roundEnvironment , @ UseMock TypeElement annotation , @ UseMock Set < Element > expected , @ UseStringConstant ( "Gets the elements annotated with the specified annotation." ) final String format )
    {
	when ( roundEnvironment . getElementsAnnotatedWith ( annotation ) ) . thenReturn ( expected ) ;
	Object observed = elementsAnnotatedWithTransformer . transform ( annotation , roundEnvironment ) ;
	String message = format ( format ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testElementsAnnotatedWithTransformer ( @ UseMock RoundEnvironment roundEnvironment , @ UseStringConstant ( "Depends on a round environment" ) final String format )
    {
	Object elementsAnnotatedWithTransformer = getElementsAnnotatedWithTransformer ( roundEnvironment ) ;
	String message = format ( format ) ;
	assertNotNull ( message , elementsAnnotatedWithTransformer ) ;
    }

    @ UseConstructor ( ElementsAnnotatedWithTransformer . class )
	abstract Transformer < ? extends Collection < ? extends Element > , ? super TypeElement > getElementsAnnotatedWithTransformer ( RoundEnvironment roundEnvironment ) ;

    final void testElementValuesKeyTransformer_transform ( @ UseMock ElementValuesKeyTransformer elementValuesKeyTransformer , @ UseMock ExecutableElement element , @ UseMock Name simpleName , @ UseStringConstant ( "elementValuesKeyTransformer=%s , element=%s" ) final String format )
    {
	when ( element . getSimpleName ( ) ) . thenReturn ( simpleName ) ;
	String expected = simpleName . toString ( ) ;
	String observed = elementValuesKeyTransformer . transform ( element ) ;
	assertEquals ( expected , observed , format , simpleName , elementValuesKeyTransformer , element ) ;
    }
    
    final void testElementValuesKeyTransformer ( @ UseStringConstant ( "ElementValuesKeyTransformer takes no parameters." ) final String format )
    {
	Object elementValuesKeyTransformer = getElementValuesKeyTransformer ( ) ;
	assertNotNull ( elementValuesKeyTransformer , format ) ;
    }

    final void testElementValuesWithDefaultsTransformer_transform ( @ UseMock ElementValuesWithDefaultsTransformer elementValuesWithDefaultsTransformer , @ UseMock MockElements elementUtils , @ UseMock AnnotationMirror annotationMirror , @ UseMock Map < ExecutableElement , AnnotationValue > expected , @ UseStringConstant ( "elementUtils=%s , annotationMirror=%s" ) String format )
    {
	when ( elementUtils . getElementValuesWithDefaults ( annotationMirror ) ) . thenReturn ( expected ) ;
	Map < ? extends ExecutableElement , ? extends AnnotationValue > observed = elementValuesWithDefaultsTransformer . transform ( annotationMirror , elementUtils ) ;
	assertEquals ( expected , observed , format , elementUtils , annotationMirror ) ;
    }

    final void testElementValuesWithDefaultsTransformer ( @ UseMock Elements elementUtils , @ UseStringConstant ( "Depends on elementUtils %s" ) final String format )
    {
	Object elementValuesWithDefaultsTransformer = getElementValuesWithDefaultsTransformer ( elementUtils ) ;
	assertNotNull ( elementValuesWithDefaultsTransformer , format , elementUtils ) ;
    }

    @ UseConstructor ( ElementValuesWithDefaultsTransformer . class )
	abstract Transformer < ? extends Map < ? extends Element , ? extends AnnotationValue > , ? super AnnotationMirror > getElementValuesWithDefaultsTransformer ( Elements elementUtils ) ;

    @ UseConstructor ( ElementValuesKeyTransformer . class )
	abstract Transformer < ? , ? super Element > getElementValuesKeyTransformer ( ) ;

    final void testIdentityTransformer_transform ( @ UseMock IdentityTransformer < Object > identityTransformer , @ UseMock final Object expected , @ UseStringConstant ( "An identity transformer always returns its input (%s)." ) final String format )
    {
	Object observed = identityTransformer . transform ( expected ) ;
	String message = format ( format , expected ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testIdentityTransformer ( @ UseStringConstant ( "An IdentityTransformer has no parameters." ) final String message  )
    {
	Object identityTransformer = getIdentityTransformer ( ) ;
	assertNotNull ( message , identityTransformer ) ;
    }

    @ UseConstructor ( IdentityTransformer . class )
	abstract < R > Transformer < ? extends R , ? super R > getIdentityTransformer ( ) ;

    final void testStringAnnotationValueVisitor_visitString ( @ UseMock StringAnnotationValueVisitor stringAnnotationValueVisitor , @ UseMock Object inputObject , @ UseMock Object data , @ UseStringConstant ( "Should act as an identity function returning its (string) input and discarding user data." ) String format )
    {
	String expected = inputObject . toString ( ) ;
	String observed = stringAnnotationValueVisitor . visitString ( expected , data ) ;
	String message = format ( format ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testStringAnnotationValueVisitor ( @ UseStringConstant ( "StringAnnotationValueVisitor has no parameters." ) String format )
    {
	Object stringAnnotationValueVisitor = getStringAnnotationValueVisitor ( ) ;
	String message = format ( format ) ;
	assertNotNull ( message , stringAnnotationValueVisitor ) ;
    }

    @ UseConstructor ( StringAnnotationValueVisitor . class )
			      abstract AnnotationValueVisitor < ? extends String , ? super Object > getStringAnnotationValueVisitor ( ) ;

    final void testJoinTransformer_transform ( @ UseMock final JoinTransformer < Object , Object , Object > joinTransformer , @ UseMock final Transformer < Object , Object > alpha , @ UseMock final Object value , @ UseMock final Transformer < Object , Object > beta , @ UseMock final Object a , @ UseMock final Object expected , @ UseStringConstant ( "A join transformer connects two specified transformers (%s and %s).  For a given input (%s) the output of one (%s) is the input of the other." ) final String format )
    {
	when ( alpha . transform ( value ) ) . thenReturn ( a ) ;
	when ( beta . transform ( a ) ) . thenReturn ( expected ) ;
	Object observed = joinTransformer . transform ( value , alpha , beta ) ;
	String message = format ( format , alpha , beta , value , a ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testJoinTransformer ( @ UseMock final Object value , @ UseMock final Transformer < Object , Object > alpha , @ UseMock final Transformer < Object , Object > beta , @ UseStringConstant ( "A join transformer has 2 parameters:  the two transformers it joins (%s and %s)." ) final String format )
    {
	Object joinTransformer = getJoinTransformer ( alpha , beta ) ;
	String message = format ( format , alpha , beta ) ;
	assertNotNull ( message , joinTransformer ) ;
    }

    @ UseConstructor ( JoinTransformer . class )
	abstract < R , A , P > Transformer < ? extends R , ? super P > getJoinTransformer ( Transformer < ? extends A , ? super P > alpha , Transformer < ? extends R , ? super A > beta ) ;

    void testMapEntryKeyTransformer_transform ( @ UseMock MapEntryKeyTransformer < Object > mapEntryKeyTransformer , @ UseMock Map . Entry < Object , Object > entry , @ UseMock Object expected , @ UseStringConstant ( "entry=%s" ) final String format )
    {
	when ( entry . getKey ( ) ) . thenReturn ( expected ) ;
	Object observed = mapEntryKeyTransformer . transform ( entry ) ;
	assertEquals ( expected , observed , format , entry ) ;
    }

    void testMapEntryKeyTransformer ( @ UseStringConstant ( "no parameters" ) final String format )
    {
	Object mapEntryKeyTransformer = getMapEntryKeyTransformer ( ) ;
	assertNotNull ( mapEntryKeyTransformer , format ) ;
    }

    @ UseConstructor ( MapEntryKeyTransformer . class )
	abstract < K > Transformer < ? extends K , ? super Map . Entry < ? extends K , ? > > getMapEntryKeyTransformer ( ) ;

    void testMapEntryValueTransformer_transform ( @ UseMock MapEntryValueTransformer < Object > mapEntryValueTransformer , @ UseMock Map . Entry < Object , Object > entry , @ UseMock Object expected , @ UseStringConstant ( "entry=%s" ) final String format )
    {
	when ( entry . getValue ( ) ) . thenReturn ( expected ) ;
	Object observed = mapEntryValueTransformer . transform ( entry ) ;
	assertEquals ( expected , observed , format , entry ) ;
    }

    void testMapEntryValueTransformer ( @ UseStringConstant ( "no parameters" ) final String format )
    {
	Object mapEntryValueTransformer = getMapEntryValueTransformer ( ) ;
	assertNotNull ( mapEntryValueTransformer , format ) ;
    }

    @ UseConstructor ( MapEntryValueTransformer . class )
	abstract < V > Transformer < ? extends V , ? super Map . Entry < ? , ? extends V > > getMapEntryValueTransformer ( ) ;

    abstract class MockMapMapTransformer < A , B , C , D > extends MapMapTransformer < A , B , C , D >
    {
	@ Override
	    abstract < K , V , P > Map < K , V > getTransformerMap ( Collection < ? extends P > data , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;

	@ Override
	    abstract < R , A , P > Transformer < R , P > getJoinTransformer ( Transformer < ? extends A , ? super P > alpha , Transformer < ? extends R , ? super A > beta ) ;

	@ Override
	    abstract < K > Transformer < K , Map . Entry < ? extends K , ? > > getMapEntryKeyTransformer ( ) ;

	@ Override
	    abstract < V > Transformer < V , Map . Entry < ? , ? extends V > > getMapEntryValueTransformer ( ) ;
    }

    final void testMapMapTransformer_transform ( @ UseMock MockMapMapTransformer < Object , Object , Object , Object > mapMapTransformer , @ UseMock Map < Object , Object > input , @ UseMock Set < Map . Entry < Object , Object > > entrySet , @ UseMock Transformer < Object , Map . Entry < ? , ? > > mapEntryKeyTransformer , @ UseMock Transformer < Object , Map . Entry < ? , ? > > mapEntryValueTransformer , @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseMock Transformer < Object , Map . Entry < ? , ? > > key , @ UseMock Transformer < Object , Map . Entry < ? , ? > > value , @ UseMock Map < Object , Object > expected , @ UseStringConstant ( "input=%s , entrySet=%s , keyTransformer=%s , valueTransformer=%s , " ) String format )
    {
	when ( input . entrySet ( ) ) . thenReturn ( entrySet ) ;
	when ( mapMapTransformer . getMapEntryKeyTransformer ( ) ) . thenReturn ( mapEntryKeyTransformer ) ;
	when ( mapMapTransformer . getJoinTransformer ( mapEntryKeyTransformer , keyTransformer ) ) . thenReturn ( key ) ;
	when ( mapMapTransformer . getMapEntryValueTransformer ( ) ) . thenReturn ( mapEntryValueTransformer ) ;
	when ( mapMapTransformer . getJoinTransformer ( mapEntryValueTransformer , valueTransformer ) ) . thenReturn ( value ) ;
	when ( mapMapTransformer . getTransformerMap ( entrySet , key , value ) ) . thenReturn ( expected ) ;
	Object observed = mapMapTransformer . transform ( input , keyTransformer , valueTransformer ) ;
	assertEquals ( expected , observed , format , input , entrySet , keyTransformer , valueTransformer ) ;
    }

    final void testMapMapTransformer ( @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseStringConstant ( "Depends on a keyTransformer %s and a valueTransformer %s." ) final String format )
    {
	Object mapMapTransformer = getMapMapTransformer ( keyTransformer , valueTransformer ) ;
	assertNotNull ( mapMapTransformer , format , keyTransformer , valueTransformer ) ;
    }

    @ UseConstructor ( MapMapTransformer . class )
	abstract < A , B , C , D > Transformer < ? extends Map < ? extends A , ? extends B > , ? super Map < ? extends C , ? extends D > > getMapMapTransformer ( Transformer < ? extends A , ? super C > keyTransformer , Transformer < ? extends B , ? super D > valueTransformer ) ;

    abstract class MockMapTransformer < K , V , P > extends MapTransformer < K , V , P >
    {
	@ Override
	    abstract < K , V , P > Map < K , V > getTransformerMap ( Collection < ? extends P > data , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
    }

    final void testMapTransformer_transform ( @ UseMock MockMapTransformer < Object , Object , Object > mapTransformer , @ UseMock Collection < Object > data , @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseMock Map < Object , Object > expected , @ UseStringConstant ( "Should make a map." ) String format )
    {
	when ( mapTransformer . getTransformerMap ( data , keyTransformer , valueTransformer ) ) . thenReturn ( expected ) ;
	Object observed = mapTransformer . transform ( data , keyTransformer , valueTransformer ) ;
	String message = format ( format ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testMapTransformer ( @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseStringConstant ( "Takes a keyTransformer and a valueTransformer." ) String format )
    {
	Object mapTransformer = getMapTransformer ( keyTransformer , valueTransformer ) ;
	String message = format ( format ) ;
	assertNotNull ( message , mapTransformer ) ;
    }

    @ UseConstructor ( MapTransformer . class )
	abstract < K , V , P > Transformer < ? extends Map < ? extends K , ? extends V > , ? super Collection < ? extends P > > getMapTransformer ( Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;

    final void testMapEntryTransformer_transform ( @ UseMock MapEntryTransformer < Object , Object , Object > mapEntryTransformer , @ UseMock Object data , @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseMock Map . Entry < Object , Object > expected , @ UseStringConstant ( "data = %s , keyTransformer = %s , valueTransformer = %s" ) String format )
    {
	when ( mapEntryTransformer . getTransformerMapEntry ( data , keyTransformer , valueTransformer ) ) . thenReturn ( expected ) ;
	Object observed = mapEntryTransformer . transform ( data , keyTransformer , valueTransformer ) ;
	String message = format ( format , data , keyTransformer , valueTransformer ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testMapEntryTransformer ( @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseStringConstant ( "The MapEntryTransformer takes two parameters: a key transformer %s and a value transformer %s." ) final String format )
    {
	Object mapEntryTransformer = getMapEntryTransformer ( keyTransformer , valueTransformer ) ;
	String message = format ( format , keyTransformer , valueTransformer ) ;
	assertNotNull ( message , mapEntryTransformer ) ;
    }

    @ UseConstructor ( MapEntryTransformer . class )
	abstract < K , V , P > Transformer < ? extends Map . Entry < K , V > , ? super P > getMapEntryTransformer ( Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;

    final void testMapItemTransformer_transform ( @ UseMock MapItemTransformer < Object , Object > mapItemTransformer , @ UseMock Object key , @ UseMock Map < Object , Object > map , @ UseMock Object expected , @ UseStringConstant ( "Returns the value %s associated with the key %s in the specified map %s" ) final String format )
    {
	when ( map . get ( key ) ) . thenReturn ( expected ) ;
	Object observed = mapItemTransformer . transform ( map , key ) ;
	String message = format ( format , expected , key , map ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testMapItemTransformer ( @ UseMock final Object key , @ UseStringConstant ( "The MapItemTransformer has 1 parameter: a key %s." ) final String format )
    {
	Object mapItemTransformer = getMapItemTransformer ( key ) ;
	String message = format ( format , key ) ;
	assertNotNull ( message , mapItemTransformer ) ;
    }

    @ UseConstructor ( MapItemTransformer . class )
	abstract < K , V > Transformer < ? extends V , ? super Map < ? extends K , ? extends V > > getMapItemTransformer ( K key ) ;

    final void testNullWriterFactory ( @ UseStringConstant ( "In practice, this should never be instantiated.  This class exists solely to provide a type to an annotation.  (null is not allowed)." ) final String format )
    {
	Object nullWriterFactory = getNullWriterFactory ( ) ;
	assertNotNull ( format , nullWriterFactory ) ;
    }

    @ UseConstructor ( NullWriterFactory . class )
	abstract WriterFactory getNullWriterFactory ( ) ;

    final void testProcessTransformer_transform ( @ UseMock ProcessTransformer processTransformer , @ UseMock TypeElement element , @ UseMock RoundEnvironment roundEnvironment , @ UseMock final Object supportedAnnotationTypeObject , @ UseStringConstant ( "" ) final String message )
    {
	String supportedAnnotationType = supportedAnnotationTypeObject . toString ( ) ;
	processTransformer . transform ( element , roundEnvironment , supportedAnnotationType ) ;
    }

    final void testProcessTransformer ( @ UseMock RoundEnvironment roundEnvironment , @ UseStringConstant ( "The ProcessTransformer's parameters include a roundEnvironment." ) final String message )
      {
	  Object processTransformer = getProcessTransformer ( roundEnvironment ) ;
	  assertNotNull ( message , processTransformer ) ;
      }

    @ UseConstructor ( ProcessTransformer . class )
	abstract Transformer < ? , ? super TypeElement > getProcessTransformer ( RoundEnvironment roundEnvironment ) ;

    abstract class MockProcessor extends Processor
    {
	@ Override
	    abstract Transformer < Object , Object > getQualifiedNameTransformer ( ) ;

	@ Override
	    abstract Transformer < Object , TypeElement > getProcessTransformer ( RoundEnvironment roundEnvironment ) ;

	@ Override
	    abstract < R , P > Transformer < Iterable < ? extends R > , Collection < ? extends P > > getSetTransformer ( Transformer < ? extends R , ? super P > transformer ) ;
    }

    final void testProcessor_getSupportedAnnotationTypes ( @ UseMock final MockProcessor processor , @ UseMock Transformer < Object , Object > qualifiedNameTransformer , @ UseMock final Object supportedAnnotationType1 , @ UseMock final Object supportedAnnotationType2 , @ UseMock final Set < String > expected , @ UseStringConstant ( "The processor supported annotation types should always be the same." ) final String format )
    {
	when ( processor . getQualifiedNameTransformer ( ) ) . thenReturn ( qualifiedNameTransformer ) ;
	when ( qualifiedNameTransformer . transform ( supportedAnnotationType1 ) ) . thenReturn ( supportedAnnotationType2 ) ;
	String supportedAnnotationType3 = supportedAnnotationType2 . toString ( ) ;
	when ( processor . singleton ( supportedAnnotationType3 ) ) . thenReturn ( expected ) ;
	Set < String > observed = processor . getSupportedAnnotationTypes ( supportedAnnotationType1 ) ;
	String message = format ( format ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testProcessor_process ( @ UseMock final MockProcessor processor , @ UseMock final Set < ? extends TypeElement > annotations , @ UseMock RoundEnvironment roundEnvironment , @ UseMock Transformer < Object , TypeElement > processTransformer , @ UseMock Transformer < Iterable < ? > , Collection < ? extends TypeElement > > setTransformer , @ UseStringConstant ( "Process the annotations." ) final String format )
    {
	when ( processor . getProcessTransformer ( roundEnvironment ) ) . thenReturn ( processTransformer ) ;
	when ( processor . getSetTransformer ( processTransformer ) ) . thenReturn ( setTransformer ) ;
	boolean process = processor . process ( annotations , roundEnvironment ) ;
	String message = format ( format ) ;
	assertTrue ( message , process ) ;
    }

    final void testProcessor ( @ UseStringConstant ( "The Processor necessary has no parameters" ) final String message )
    {
	Object processor = getProcessor ( ) ;
	assertNotNull ( message , processor ) ;
    }

    @ UseConstructor ( Processor . class )
	abstract javax . annotation . processing . Processor getProcessor ( ) ;

    final void testQualifiedNameElementVisitor_visitType ( @ UseMock QualifiedNameElementVisitor qualifiedNameElementVisitor , @ UseMock TypeElement element , @ UseMock Object data , @ UseMock Name qualifiedName , @ UseStringConstant ( "Returns the qualifed name of an element." ) final String format )
    {
	when ( element . getQualifiedName ( ) ) . thenReturn ( qualifiedName ) ;
	Name observed = qualifiedNameElementVisitor . visitType ( element , data ) ;
	assertEquals ( format , qualifiedName , observed ) ;
    }

    final void testQualifiedNameElementVisitor ( @ UseStringConstant ( "The QualifiedNameElementVisitor does not have any parameters." ) final String format )
    {
	Object qualifiedNameElementVisitor = getQualifiedNameElementVisitor ( ) ;
	assertNotNull ( format , qualifiedNameElementVisitor ) ;
    }

    @ UseConstructor ( QualifiedNameElementVisitor . class )
	abstract ElementVisitor < ? extends Name , ? super Object > getQualifiedNameElementVisitor ( ) ;

    final void testQualifiedNameTransformer_transform_nowhitespace ( @ UseMock QualifiedNameTransformer qualifiedNameTransformer , @ UseStringConstant ( "somepackage.SomeClass" ) final Object input )
    {
	testQualifiedNameTransformer_transform ( qualifiedNameTransformer , input , input ) ;
    }

    final void testQualifiedNameTransformer_transform_whitespace ( @ UseMock QualifiedNameTransformer qualifiedNameTransformer , @ UseStringConstant ( " somepackage . SomeClass" ) final Object input , @ UseStringConstant ( "somepackage.SomeClass" ) final Object expected )
    {
	testQualifiedNameTransformer_transform ( qualifiedNameTransformer , input , expected ) ;
    }

    @ UseStringConstant ( "The transformation of %s." )
	abstract String getFormat_TestQualifiedNameTransformer_transform ( ) ;

    private void testQualifiedNameTransformer_transform ( Transformer < ? , ? super Object > qualifiedNameTransformer , Object input , Object expected )
    {
	Object observed = qualifiedNameTransformer . transform ( input ) ;
	String format = getFormat_TestQualifiedNameTransformer_transform ( ) ;
	String message = format ( format , input ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testQualifiedNameTransformer ( @ UseStringConstant ( "A QualifiedNameTransformer does not have any parameters." ) String format )
    {
	Object qualifiedNameTransformer = getQualifiedNameTransformer ( ) ;
	String message = format ( format ) ;
	assertNotNull ( message , qualifiedNameTransformer ) ;
    }

    @ UseConstructor ( QualifiedNameTransformer . class )
	abstract Transformer < ? , ? super Object > getQualifiedNameTransformer ( ) ;

    abstract class MockSetTransformer < R , P > extends SetTransformer < R , P >
    {
	@ Override
	    abstract < R , P > Set < R > getTransformerSet ( Collection < ? extends P > collection , Transformer < ? extends R , ? super P > transformer ) ;
    }

    final void testSetTransformer_transform ( @ UseMock MockSetTransformer < Object , Object > setTransformer , @ UseMock Set < Object > input , @ UseMock Transformer < Object , Object > transformer , @ UseMock Set < Object > expected , @ UseStringConstant ( "Creates a set based on an input set and a transformer." ) final String format )
    {
	when ( setTransformer . getTransformerSet ( input , transformer ) ) . thenReturn ( expected ) ;
	Set < ? extends Object > observed = setTransformer . transform ( input , transformer ) ;
	String message = format ( format ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testSetTransformer ( @ UseMock Transformer < Object , Object > transformer , @ UseStringConstant ( "Transforms a set.  No parameters." ) final String format )
    {
	Object setTransformer = getSetTransformer ( transformer ) ;
	String message = format ( format ) ;
	assertNotNull ( message , setTransformer ) ;
    }

    @ UseConstructor ( SetTransformer . class )
	abstract < R , P > Transformer < ? extends Set < ? extends R > , ? super Collection < ? extends P > > getSetTransformer ( final Transformer < ? extends R , ? super P > transformer ) ;

    abstract class MockTransformerIterator < R , P > extends TransformerIterator < R , P >
    {
	@ Override
	    Iterator < P > getIterator ( )
	    {
		return null ;
	    }

	@ Override
	    Transformer < R , P > getTransformer ( )
	    {
		return null ;
	    }

	@ Override
	    public void remove ( )
	    {
	    }
    }

    final void testTransformerIterator_hasNext ( @ UseMock final MockTransformerIterator < Object , Object > transformerIterator , @ UseMock final Iterator < Object > iterator , @ UseStringConstant ( "The TransformerIterator" ) final String format )
    {
	when ( transformerIterator . getIterator ( ) ) . thenReturn ( iterator ) ;
	boolean expected = iterator . hasNext ( ) ;
	boolean observed = transformerIterator . hasNext ( ) ;
	assertEquals ( format , expected , observed ) ;
    }

    final void testTransformerIterator_Next ( @ UseMock MockTransformerIterator < Object , Object > transformerIterator , @ UseMock Transformer < Object , Object > transformer , @ UseMock Iterator < Object > iterator , @ UseMock Object next , @ UseMock Object expected , @ UseStringConstant ( "The TransformerIterator will delegate the next to the specified iterator." ) final String format )
    {
	when ( transformerIterator . getTransformer ( ) ) . thenReturn ( transformer ) ;
	when ( transformer . transform ( next ) ) . thenReturn ( expected ) ;
	when ( transformerIterator . getIterator ( ) ) . thenReturn ( iterator ) ;
	when ( iterator . next ( ) ) . thenReturn ( next ) ;
	Object observed = transformerIterator . next ( ) ;
	assertEquals ( format , expected , observed ) ;
    }

    final void testTransformerIterator ( @ UseMock Iterator < Object > iterator , @ UseMock Transformer < Object , Object > transformer , @ UseStringConstant ( "The TransformerIterator has two parameters:  an iterator %s and a transformer %s" ) String format )
    {
	Object transformerIterator = getTransformerIterator ( iterator , transformer ) ;
	String message = format ( format , iterator , transformer ) ;
	assertNotNull ( message , transformerIterator ) ;
    }

    @ UseConstructor ( TransformerIterator . class )
	abstract < R , P > Iterator < ? extends R > getTransformerIterator ( Iterator < ? extends P > iterator , Transformer < ? extends R , ? super P > transformer ) ;

    abstract class MockTransformerMap < K , V , P > extends TransformerMap < K , V , P >
    {
	@ Override
	    abstract < K , V , P > Transformer < Map . Entry < K , V > , P > getMapEntryTransformer ( Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
    }

    final void testTransformerMap_entrySet ( @ UseMock MockTransformerMap < Object , Object , Object > transformerMap , @ UseMock Collection < Object > collection , @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseMock Transformer < Map . Entry < Object , Object > , Object > mapEntryTransformer , @ UseMock Set < Map . Entry < Object , Object > > expected , @ UseStringConstant ( "collection=%s , keyTransformer=%s, valueTransformer=%s, mapEntryTransformer=%s" ) final String format )
    {
	when ( transformerMap . getMapEntryTransformer ( keyTransformer , valueTransformer ) ) . thenReturn ( mapEntryTransformer ) ;
	when ( transformerMap . getTransformerSet ( collection , mapEntryTransformer ) ) . thenReturn ( expected ) ;
	Object observed = transformerMap . entrySet ( collection , keyTransformer , valueTransformer ) ;
	String message = format ( format , collection , keyTransformer , valueTransformer , mapEntryTransformer ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testTransformerMap ( @ UseMock Collection < Object > collection , @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseStringConstant ( "TransformerMap takes 2 parameters:  a keyTransformer %s and a valueTransformer %s." ) final String format )
    {
	Object transformerMap = getTransformerMap ( collection , keyTransformer , valueTransformer ) ;
	String message = format ( format , keyTransformer , valueTransformer ) ;
	assertNotNull ( message , transformerMap ) ;
    }

    @ UseConstructor ( TransformerMap . class )
	abstract < K , V , P > Map < ? extends K , ? extends V > getTransformerMap ( Collection < ? extends P > collection , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;

    abstract class MockTransformerMapEntry < K , V , P > extends TransformerMapEntry < K , V , P >
    {
	@ Override
	    abstract Transformer < K , P > getKeyTransformer ( ) ;

	@ Override
	    abstract Transformer < V , P > getValueTransformer ( ) ;
    }

    final void testTransformerMapEntry_getKey ( @ UseMock MockTransformerMapEntry < Object , Object , Object > transformerMapEntry , @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Object entry , @ UseMock Object expected , @ UseStringConstant ( "keyTransformer=%s , entry=%s" ) String format )
    {
	when ( transformerMapEntry . getKeyTransformer ( ) ) . thenReturn ( keyTransformer ) ;
	when ( keyTransformer . transform ( entry ) ) . thenReturn ( expected ) ;
	when ( transformerMapEntry . getEntry ( ) ) . thenReturn ( entry ) ;
	Object observed = transformerMapEntry . getKey ( ) ;
	String message = format ( format , keyTransformer , entry ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testTransformerMapEntry_getValue ( @ UseMock MockTransformerMapEntry < Object , Object , Object > transformerMapEntry , @ UseMock Transformer < Object , Object > valueTransformer , @ UseMock Object entry , @ UseMock Object expected , @ UseStringConstant ( "valueTransformer=%s , entry=%s" ) String format )
    {
	when ( transformerMapEntry . getValueTransformer ( ) ) . thenReturn ( valueTransformer ) ;
	when ( valueTransformer . transform ( entry ) ) . thenReturn ( expected ) ;
	when ( transformerMapEntry . getEntry ( ) ) . thenReturn ( entry ) ;
	Object observed = transformerMapEntry . getValue ( ) ;
	String message = format ( format , valueTransformer , entry ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testTransformerMapEntry ( @ UseMock Object data , @ UseMock Transformer < Object , Object > keyTransformer , @ UseMock Transformer < Object , Object > valueTransformer , @ UseStringConstant ( "TransformerMapEntry has three parameters:  a data %s, a key transformer %s, and a value transformer %s." ) String format )
    {
	Object transformerMapEntry = getTransformerMapEntry ( data , keyTransformer , valueTransformer ) ;
	String message = format ( format , data , keyTransformer , valueTransformer ) ;
	assertNotNull ( message , transformerMapEntry ) ;
    }

    @ UseConstructor ( TransformerMapEntry . class )
	abstract < K , V , P > Map . Entry < ? extends K , ? extends V > getTransformerMapEntry ( P data , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;

    abstract class MockTransformerSet < R , P > extends TransformerSet < R , P >
    {
	@ Override
	    abstract Collection < P > getCollection ( ) ;

	@ Override
	    abstract Transformer < R , P > getTransformer ( ) ;

	@ Override
	    abstract < R , P > Iterator < R > getTransformerIterator ( Iterator < ? extends P > iterator , Transformer < ? extends R , ? super P > transformer ) ;
    }

    final void testTransformerSet_size ( @ UseMock final MockTransformerSet < Object , Object > transformerSet , @ UseMock final Collection < Object > collection , @ UseStringConstant ( "The transformer set should have the same size as the underlying collection - %s" ) final String format )
    {
	when ( transformerSet . getCollection ( ) ) . thenReturn ( collection ) ;
	int expected = collection . size ( ) ;
	int observed = transformerSet . size ( ) ;
	String message = format ( format , expected ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void testTransformerSet_iterator ( @ UseMock MockTransformerSet < Object , Object > transformerSet , @ UseMock Collection < Object > collection , @ UseMock Iterator < Object > iterator , @ UseMock Transformer < Object , Object > transformer , @ UseMock Iterator < Object > transformerIterator , @ UseStringConstant ( "The transformer set should be based on the underlying collection." ) final String format )
    {
	when ( transformerSet . getCollection ( ) ) . thenReturn ( collection ) ;
	when ( collection . iterator ( ) ) . thenReturn ( iterator ) ;
	when ( transformerSet . getTransformer ( ) ) . thenReturn ( transformer ) ;
	when ( transformerSet . getTransformerIterator ( iterator , transformer ) ) . thenReturn ( transformerIterator ) ;
	Iterator < Object > observed = transformerSet . iterator ( ) ;
	String message = format ( format ) ;
	assertEquals ( message , transformerIterator , observed ) ;
    }

    final void transformerSet ( @ UseMock Collection < Object > collection , @ UseMock Transformer < Object , Object > transformer , @ UseStringConstant ( "Transforms the collection %s using the transformer %s" ) final String format )
    {
	Object transformerSet = getTransformerSet ( collection , transformer ) ;
	String message = format ( format , collection , transformer ) ;
	assertNotNull ( message , transformerSet ) ;
    }

    @ UseConstructor ( TransformerSet . class )
	abstract < R , P > Set < ? extends R > getTransformerSet ( Collection < ? extends P > collection , Transformer < ? extends R , ? super P > transformer ) ;

    @ UseStaticMethod ( Assert . class )
	abstract void assertEquals ( String message , Object expected , Object observed ) ;

    private void assertEquals ( Object expected , Object observed , String format , Object ... arguments )
    {
	String message = format ( format , arguments ) ;
	assertEquals ( message , expected , observed ) ;
    }

    @ UseStaticMethod ( Assert . class )
	abstract void assertNotNull ( String message , Object value ) ;

    private void assertNotNull ( Object value , String format , Object ... arguments )
    {
	String message = format ( format , arguments ) ;
	assertNotNull ( message , value ) ;
    }

    @ UseStaticMethod ( Assert . class )
	abstract void assertTrue ( String message , boolean predicate ) ;

    @ UseStaticMethod ( String . class )
	abstract String format ( String format , Object ... arguments ) ;

    @ UseStaticMethod ( Mockito . class )
	abstract < T > OngoingStubbing < T > when ( T methodCall ) ;

    abstract class MockElements implements Elements
    {
	@ Override
	    public abstract Map < ExecutableElement , AnnotationValue > getElementValuesWithDefaults ( AnnotationMirror annotationMirror ) ;

	@ Override
	    public abstract List < AnnotationMirror > getAllAnnotationMirrors ( Element element ) ;
    }
}
