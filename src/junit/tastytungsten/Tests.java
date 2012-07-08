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
import java . util . HashMap ;
import java . util . HashSet ;
import java . util . Iterator ;
import java . util . Map ;
import java . util . Set ;
import javax . annotation . processing . ProcessingEnvironment ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . element . Name ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . type . DeclaredType ;
import org . junit . Assert ;
import org . mockito . Mockito ;
import org . mockito . stubbing . OngoingStubbing ;

import org . junit . Test ;

/**
 * The standard suite of tests.
 **/
@ SuppressWarnings ( "unchecked" )
    public abstract class Tests
{
    public Tests ( )
    {
    }

    final void testAnnotationMirrorKeyTransformer ( @ UseMock AnnotationMirrorKeyTransformer annotationMirrorKeyTransformer , @ UseMock ElementVisitor < Name , Object > qualifiedNameElementVisitor , @ UseMock Name qualifiedName , @ UseMock AnnotationMirror annotationMirror , @ UseMock DeclaredType annotationType , @ UseMock Element annotationElement , @ UseStringConstant ( "The key of an annotation mirror is the qualified name (%s)) of its annotation type's (%s) element (%s)." ) final String format )
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

    final void annotationMirrorKeyTransformer ( @ UseStringConstant ( "An AnnotationMirrorKeyTransformer has no parameters." ) final String message )
    {
	Object annotationMirrorKeyTransformer = getAnnotationMirrorKeyTransformer ( ) ;
	assertNotNull ( message , annotationMirrorKeyTransformer ) ;
    }

    @ UseConstructor ( AnnotationMirrorKeyTransformer . class )
	abstract Transformer < ? extends String , ? super AnnotationMirror > getAnnotationMirrorKeyTransformer ( ) ;

    final void testAnnotationValueVisitorTransformer ( @ UseMock final AnnotationValueVisitorTransformer < Object , Object , Object > annotationValueVisitorTransformer , @ UseMock final Object value , @ UseMock final AnnotationValueVisitor < ? , ? super Object > visitor , @ UseMock final Transformer < ? extends AnnotationValue , ? super Object > transformer , @ UseMock final AnnotationValue annotationValue , @ UseMock final Object data , @ UseMock final Object expected , @ UseStringConstant ( "Build a transformer by connecting a specified AnnotationValueVisitor (%s) to a specified Transformer (%s).  The transformer transforms input data (%s) into an AnnotationValue (%s).  The AnnotationValueVisitor visits the AnnotationValue (along with specified data (%s)) and the result is the output (%s)." ) final String format )
    {
	when ( transformer . transform ( value ) ) . thenReturn ( annotationValue ) ;
	when ( visitor . visit ( annotationValue , data ) ) . thenReturn ( expected ) ;
	Object observed = annotationValueVisitorTransformer . transform ( value , visitor , transformer , data ) ;
	String message = format ( format , visitor , transformer , value , annotationValue , data , observed ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void annotationValueVisitorTransformer ( @ UseMock final AnnotationValueVisitor < ? , ? super Object > visitor , @ UseMock final Transformer < ? extends AnnotationValue , ? super Object > transformer , @ UseMock final Object data , @ UseStringConstant ( "An AnnotationValueVisitorTransformer has 3 parameters:  a visitor (%s), a transformer (%s), and user data (%s)." ) final String format )
    {
	Object annotationValueVisitorTransformer = getAnnotationValueVisitorTransformer ( visitor , transformer , data ) ;
	String message = format ( format , visitor , transformer , data ) ;
	assertNotNull ( message , annotationValueVisitorTransformer ) ;
    }

    @ UseConstructor ( AnnotationValueVisitorTransformer . class )
	abstract < R , A , P > Transformer < ? extends R , ? super A > getAnnotationValueVisitorTransformer ( AnnotationValueVisitor < ? extends R , ? super P > visitor , Transformer < ? extends AnnotationValue , ? super A > transformer , P data ) ;

    final void testIdentityTransformer ( @ UseMock IdentityTransformer < Object > identityTransformer , @ UseMock final Object expected , @ UseStringConstant ( "An identity transformer always returns its input (%s)." ) final String format )
    {
	Object observed = identityTransformer . transform ( expected ) ;
	String message = format ( format , expected ) ;
	assertEquals ( message , expected , observed ) ;
    }

    final void identityTransformer ( @ UseStringConstant ( "An IdentityTransformer has no parameters." ) final String message  )
    {
	Object identityTransformer = getIdentityTransformer ( ) ;
	assertNotNull ( message , identityTransformer ) ;
    }

    @ UseConstructor ( IdentityTransformer . class )
	abstract < R > Transformer < ? extends R , ? super R > getIdentityTransformer ( ) ;

    abstract class MockIterableTransformer < R , P > extends IterableTransformer < R , P >
    {
	@ Override
	    abstract < R , P > Iterable < R > getTransformerIterable ( Iterable < ? extends P > iterable , Transformer < ? extends R , ? super P > transformer ) ;
    }

    void testIterableTransformer ( @ UseMock MockIterableTransformer < Object , Object > iterableTransformer , @ UseMock Iterable < Object > iterable  , @ UseMock final Transformer < Object, Object > transformer ,  @ UseMock final Iterable < Object > transformerIterable , @ UseStringConstant ( "An iterable transformer transforms an iterable of one type (%s) to another using a specified transformer (transformer)." ) final String format )
    {
	when ( iterableTransformer . getTransformerIterable ( iterable , transformer ) ) . thenReturn ( transformerIterable ) ;
	Iterable < ? > observed = iterableTransformer . transform ( iterable , transformer ) ;
	String message = format ( format , iterable , transformer ) ;
	assertEquals ( message , transformerIterable , observed ) ;
    }

    void iterableTransformer ( @ UseMock final Transformer < ? , ? super Object > transformer , @ UseStringConstant ( "An IterableTransformer has no parameters." ) final String message )
    {
	Object iterableTransformer = getIterableTransformer ( transformer ) ;
	assertNotNull ( message , iterableTransformer ) ;
    }

    @ UseConstructor ( IterableTransformer . class )
	abstract < R , P > Transformer < ? extends Iterable < ? extends R > , ? super Iterable < ? extends P > > getIterableTransformer ( Transformer < ? extends R , ? super P > transformer ) ;

    void testJoinTransformer ( @ UseMock final JoinTransformer < Object , Object , Object > joinTransformer , @ UseMock final Transformer < Object , Object > alpha , @ UseMock final Object value , @ UseMock final Transformer < Object , Object > beta , @ UseMock final Object a , @ UseMock final Object expected , @ UseStringConstant ( "A join transformer connects two specified transformers (%s and %s).  For a given input (%s) the output of one (%s) is the input of the other." ) final String format )
    {
	when ( alpha . transform ( value ) ) . thenReturn ( a ) ;
	when ( beta . transform ( a ) ) . thenReturn ( expected ) ;
	Object observed = joinTransformer . transform ( value , alpha , beta ) ;
	String message = format ( format , alpha , beta , value , a ) ;
	assertEquals ( message , expected , observed ) ;
    }

    void joinTransformer ( @ UseMock final Object value , @ UseMock final Transformer < Object , Object > alpha , @ UseMock final Transformer < Object , Object > beta , @ UseStringConstant ( "A join transformer has 2 parameters:  the two transformers it joins (%s and %s)." ) final String format )
    {
	Object joinTransformer = getJoinTransformer ( alpha , beta ) ;
	String message = format ( format , alpha , beta ) ;
	assertNotNull ( message , joinTransformer ) ;
    }

    @ UseConstructor ( JoinTransformer . class )
	abstract < R , A , P > Transformer < ? extends R , ? super P > getJoinTransformer ( Transformer < ? extends A , ? super P > alpha , Transformer < ? extends R , ? super A > beta ) ;

    final void nullWriterFactory ( @ UseStringConstant ( "In practice, this should never be instantiated.  This class exists solely to provide a type to an annotation.  (null is not allowed)." ) final String format )
    {
	Object nullWriterFactory = getNullWriterFactory ( ) ;
	assertNotNull ( format , nullWriterFactory ) ;
    }

    @ UseConstructor ( NullWriterFactory . class )
	abstract WriterFactory getNullWriterFactory ( ) ;

    final void testProcessTransformer ( @ UseMock ProcessTransformer processTransformer , @ UseStringConstant ( "" ) final String message )
    {
	// FIXME
    }

    final void processTransformer ( @ UseStringConstant ( "The ProcessTransformer does not have parameters." ) final String message )
      {
	  Object processTransformer = getProcessTransformer ( ) ;
	  assertNotNull ( message , processTransformer ) ;
      }

    @ UseConstructor ( ProcessTransformer . class )
	abstract Transformer < ? , ? super Element > getProcessTransformer ( ) ;

    abstract class MockProcessor extends Processor
    {
	@ Override
	    abstract Transformer < String , String > getQualifiedNameTransformer ( ) ;
    }

    final void testProcessorSupportedAnnotationTypes ( @ UseMock final MockProcessor processor , @ UseMock final Object inputObject ,@ UseMock final Object supportedAnnotationTypeObject , @ UseMock Transformer < String , String > qualifiedNameTransformer , @ UseMock Set < String > supportedAnnotationTypes , @ UseStringConstant ( "The processor supported annotation types should always be the same." ) final String format )
    {
	String input = inputObject . toString ( ) ;
	String supportedAnnotationType = supportedAnnotationTypeObject . toString ( ) ;
	when ( processor . getQualifiedNameTransformer ( ) ) . thenReturn ( qualifiedNameTransformer ) ;
	when ( qualifiedNameTransformer . transform ( input ) ) . thenReturn ( supportedAnnotationType ) ;
	when ( processor . singleton ( supportedAnnotationType ) ) . thenReturn ( supportedAnnotationTypes ) ;
	Set < String > observed = processor . getSupportedAnnotationTypes ( input ) ;
	String message = format ( format , supportedAnnotationType ) ;
	assertEquals ( message , supportedAnnotationTypes , observed ) ;
    }

    final void testProcessorProcess ( @ UseMock final Processor processor , @ UseMock final Set < ? extends TypeElement > annotations , @ UseMock RoundEnvironment roundEnvironment , @ UseStringConstant ( "" ) final String format )
    {
	boolean process = processor . process ( annotations , roundEnvironment ) ;
	String message = format ( format ) ;
	assertTrue ( message , process ) ;
    }

    final void processor ( @ UseStringConstant ( "The Processor necessary has no parameters" ) final String message )
    {
	Object processor = getProcessor ( ) ;
	assertNotNull ( message , processor ) ;
    }

    @ UseConstructor ( Processor . class )
	abstract javax . annotation . processing . Processor getProcessor ( ) ;

    final void testQualifiedNameElementVisitor ( @ UseMock QualifiedNameElementVisitor qualifiedNameElementVisitor , @ UseMock TypeElement element , @ UseMock Object data , @ UseMock Name qualifiedName , @ UseStringConstant ( "Returns the qualifed name of an element." ) final String format )
    {
	when ( element . getQualifiedName ( ) ) . thenReturn ( qualifiedName ) ;
	Name observed = qualifiedNameElementVisitor . visitType ( element , data ) ;
	assertEquals ( format , qualifiedName , observed ) ;
    }

    final void qualifiedNameElementVisitor ( @ UseStringConstant ( "The QualifiedNameElementVisitor does not have any parameters." ) final String format )
    {
	Object qualifiedNameElementVisitor = getQualifiedNameElementVisitor ( ) ;
	assertNotNull ( format , qualifiedNameElementVisitor ) ;
    }

    @ UseConstructor ( QualifiedNameElementVisitor . class )
	abstract ElementVisitor < ? extends Name , ? super Object > getQualifiedNameElementVisitor ( ) ;

    final void testReplaceAllTransformer ( @ UseMock ReplaceAllTransformer replaceAllTransformer , @ UseStringConstant ( "Hello" ) final Object regex , @ UseStringConstant ( "Hi" ) final Object replace , @ UseStringConstant ( "Hello World!" ) final Object input , @ UseStringConstant ( "Hi World!" ) final String expected , @ UseStringConstant ( "string=\"%s\" , regex=\"%s\" , replace=\"%s\"" ) final String format )
    {
	String observed = replaceAllTransformer . transform ( input , regex , replace ) ;
	String message = format ( format , input , regex , replace ) ;
	assertEquals ( message , expected , observed ) ;
    }
    
    final void replaceAllTransformer ( @ UseNull final String regex , @ UseNull final String replacement , @ UseStringConstant ( "A ReplaceAllTransformer has 2 parameters:  a regex (%s) and a replace (%s)." ) final String format )
    {
	Object replaceAllTransformer = getReplaceAllTransformer ( regex , replacement ) ;
	String message = format ( format , regex , replacement ) ;
	assertNotNull ( message , replaceAllTransformer ) ;
    }

    @ UseConstructor ( ReplaceAllTransformer . class )
	abstract Transformer < ? extends String , ? super String > getReplaceAllTransformer ( String regex , String replacement ) ;

    final void testStringAnnotationValueVisitor ( @ UseMock StringAnnotationValueVisitor stringAnnotationValueVisitor , @ UseMock Object data , @ UseMock Object valueObject , @ UseStringConstant ( "The StringAnnotationValueVisitor can get a String value." ) final String format )
    {
	String value = valueObject . toString ( ) ;
	String observed = stringAnnotationValueVisitor . visitString ( value , data ) ;
	assertEquals ( format , value , observed ) ;
    }

    final void stringAnnotationValueVisitor ( @ UseStringConstant ( "The StringAnnotationValueVisitor does not have any parameters." ) final String format )
    {
	Object stringAnnotationValueVisitor = getStringAnnotationValueVisitor ( ) ;
	assertNotNull ( format , stringAnnotationValueVisitor ) ;
    }

    abstract class MockTransformerIterator < R , P > extends TransformerIterator < R , P >
    {
	@ Override
	    abstract Iterator < P > getIterator ( ) ;
    }

    final void testTransformerIteratorHasNext ( @ UseMock final MockTransformerIterator < Object , Object > transformerIterator , @ UseMock final Iterator < Object > iterator , @ UseStringConstant ( "The TransformerIterator" ) final String format )
    {
	when ( transformerIterator . getIterator ( ) ) . thenReturn ( iterator ) ;
	boolean expected = iterator . hasNext ( ) ;
	boolean observed = transformerIterator . hasNext ( ) ;
	assertEquals ( format , expected , observed ) ;
    }

    final void testTransformerIteratorNext ( @ UseMock MockTransformerIterator < Object , Object > transformerIterator , @ UseMock Iterator < Object > iterator , @ UseMock Object next , @ UseStringConstant ( "The TransformerIterator will delegate the next to the specified iterator." ) final String format )
    {
	when ( transformerIterator . getIterator ( ) ) . thenReturn ( iterator ) ;
	when ( iterator . next ( ) ) . thenReturn ( next ) ;
	Object observed = transformerIterator . next ( ) ;
	assertEquals ( format , next , observed ) ;
    }

    final void transformerIterator ( @ UseMock Iterator < Object > iterator , @ UseMock Transformer < Object , Object > transformer , @ UseStringConstant ( "The TransformerIterator has two parameters:  an iterator %s and a transformer %s" ) String format )
    {
	Object transformerIterator = getTransformerIterator ( iterator , transformer ) ;
	String message = format ( format , iterator , transformer ) ;
	assertNotNull ( message , transformerIterator ) ;
    }

    @ UseConstructor ( TransformerIterator . class )
	abstract < R , P > Iterator < ? extends Iterator < ? extends R > > getTransformerIterator ( Iterator < ? extends P > iterator , Transformer < ? extends R , ? super P > transformer ) ;

    void transformerMap ( @ UseMock Transformer < Object , Object > keyTransformer , Transformer < Object , Object > valueTransformer , @ UseStringConstant ( "TransformerMap takes 2 parameters:  a keyTransformer %s and a valueTransformer %s." ) )
    {
	Object transformerMap = getTransformerMap ( keyTransformer , valueTransformer ) ;
	String message = format ( format , keyTransformer , valueTransformer ) ;
	assertNotNull ( message , transformerMap ) ;
    }

    @ UseConstructor ( TransformerMap . class )
	abstract < K , V , P > Map < ? extends K , ? extends V > getTransformerMap ( Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;

    @ UseConstructor ( StringAnnotationValueVisitor . class )
	abstract AnnotationValueVisitor < ? extends String , ? super Object > getStringAnnotationValueVisitor ( ) ;

    @ UseStaticMethod ( Assert . class )
	abstract void assertEquals ( String message , Object expected , Object observed ) ;

    @ UseStaticMethod ( Assert . class )
	abstract void assertFalse ( String message , boolean predicate ) ;

    @ UseStaticMethod ( Assert . class )
	abstract void assertNotNull ( String message , Object value ) ;

    @ UseStaticMethod ( Assert . class )
	abstract void assertTrue ( String message , boolean predicate ) ;

    @ UseStaticMethod ( String . class )
	abstract String format ( String format , Object ... arguments ) ;

    @ UseStaticMethod ( Mockito . class )
	abstract < T > T mock ( Class < T > clazz ) ;

    @ UseStaticMethod ( Mockito . class )
	abstract < T > OngoingStubbing < T > when ( T methodCall ) ;
}
