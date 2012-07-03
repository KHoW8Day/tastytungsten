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

import java . io . Writer ;
import java . util . HashMap ;
import java . util . Map ;
import java . util . Set ;
import java . util . List ;
import javax . annotation . processing . AbstractProcessor ;
import javax . annotation . processing . Filer ;
import javax . annotation . processing . Messager ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . SourceVersion ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementKind ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . element . Modifier ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . element . TypeParameterElement ;
import javax . lang . model . type . DeclaredType ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeKind ;
import javax . lang . model . type . TypeVariable ;
import javax . lang . model . util . Elements ;
import javax . tools . Diagnostic ;
import javax . tools . JavaFileObject ;

import javax . annotation . processing . SupportedAnnotationTypes ;
import javax . annotation . processing . SupportedOptions ;
import javax . annotation . processing . SupportedSourceVersion ;

import java . io . IOException ;
import javax . lang . model . type . MirroredTypeException ;

/**
 * This is the BootstrapProcessor that processes the other files.
 *
 * It makes a number of simplifying assumptions,
 * which happen to be true of the original file set.
 * So it works on them, but it is not intended for general use.
 **/
@ SupportedAnnotationTypes ( "*" )
    @ SupportedSourceVersion ( SourceVersion . RELEASE_6 )
    @ SuppressWarnings ( "unchecked" )
    @ SupportedOptions ( { "production" , "unitTest" } )
    public final class BootstrapProcessor extends AbstractProcessor
    {
	private static final String NEWLINE = "\n" ;

	private static final String TAB = "\t" ;

	private static final String AT = "@" ;

	private static final String UNDERSCORE = "_" ;

	/**
	 * The semicolon constant.
	 **/
	private static final String SEMICOLON = ";" ;

	private static final String EQUALS = "=" ;

	private static final String PERIOD = "." ;

	private static final String QUESTION = "?" ;

	/**
	 * The space constant.
	 **/
	private static final String SPACE = " " ;

	/**
	 * The comma constant.
	 **/
	private static final String COMMA = "," ;

	private static final String OPEN_ANGLE = "<" ;

	private static final String CLOSE_ANGLE = ">" ;

	private static final String OPEN_CURLY = "{" ;

	private static final String CLOSE_CURLY = "}" ;

	/**
	 * The open parenthesis constant.
	 **/
	private static final String OPEN_PAREN = "(" ;

	/**
	 * The close parenthesis constant.
	 **/
	private static final String CLOSE_PAREN = ")" ;

	/**
	 * Open brace level 3.
	 **/
	private static final String OPEN_BRACE_3 = "\n\t\t\t{" ;

	/**
	 * Close brace level 3.
	 **/
	private static final String CLOSE_BRACE_3 = "\n\t\t\t}" ;

	private static final String PACKAGE = "package" ;

	private static final String THIS = "this" ;

	private static final String CLASS = "class" ;

	private static final String CLASS_OBJECT = "Class" ;

	private static final String BOOTSTRAP = "Bootstrap" ;

	private static final String TASTYTUNGSTEN = "tastytungsten" ;

	private static final String UNCHECKED = "\"unchecked\"" ;

	private static final String RAWTYPES = "\"rawtypes\"" ;

	private static final String SUPPRESS_WARNINGS = "SuppressWarnings" ;

	private static final String TEST_PREFIX = "test" ;

	private static final String JAVA = "java" ;

	private static final String LANG = "lang" ;

	private static final String OBJECT = "Object" ;

	private static final String ORG = "org" ;

	private static final String JUNIT = "junit" ;

	private static final String MOCKITO_PACKAGE = "mockito" ;

	private static final String MOCKITO_CLASS = "Mockito" ;

	private static final String MOCK = "mock" ;

	private static final String TEST = "Test" ;

	private static final String PUBLIC = "public" ;

	private static final String VOID = "void" ;

	private static final String EXTENDS = "extends" ;

	/**
	 * The return keyword.
	 **/
	private static final String RETURN = "return" ;

	/**
	 * The throw keyword.
	 **/
	private static final String THROW = "throw" ;

	/**
	 * The new keyword.
	 **/
	private static final String NEW = "new" ;

	/**
	 * Used for class parameters to indicate
	 * declaration processing, int x.
	 **/
	private static final int DECLARATION  = 1 ;

	/**
	 * Used for class parameters to indicate
	 * parameter processing, int x.
	 **/
	private static final int PARAMETER  = 2 ;

	/**
	 * Used for class parameters to indicate
	 * assignment processing, "this.x=x".
	 **/
	private static final int ASSIGNMENT = 3 ;

	private static final int ARGUMENT = 4 ;

	/**
	 * A flag used to prevent duplicate processing.
	 **/
	private boolean flag = true ;;

	/**
	 * A public constructor is necessary for the tool to work.
	 **/
	public BootstrapProcessor ( )
	{
	}

	/**
	 * {@inheritDoc}.
	 *
	 * @param annotations {@inheritDoc}
	 * @param roundEnvironment {@inheritDoc}
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
	    if ( flag )
		{
		    process ( roundEnvironment ) ;
		}
	    return true ;
	}

	private void append ( StringBuilder stringBuilder , boolean predicate , Object object )
	{
	    if ( predicate )
		{
		    stringBuilder . append ( object ) ;
		}
	}

	/**
	 * Process things.
	 *
	 * @param roundEnvironment for getting the root elements.
	 **/
	private
	    void
	    process
	    ( final RoundEnvironment roundEnvironment )
	{
	    Set < ? extends Element > rootElements =
		roundEnvironment . getRootElements ( ) ;
	    StringBuilder stringBuilder = new StringBuilder ( ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , PACKAGE ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , TASTYTUNGSTEN ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , SEMICOLON ) ;
	    suppressWarningsAnnotation ( stringBuilder ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , PUBLIC ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , CLASS ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , BOOTSTRAP ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , OPEN_CURLY ) ;
	    process ( rootElements , stringBuilder ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , CLOSE_CURLY ) ;
	    write ( stringBuilder ) ;
	    flag = false ;
	}

	private void suppressWarningsAnnotation ( StringBuilder stringBuilder )
	{
	    boolean isUnitTest = isUnitTest ( ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , AT ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , JAVA ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , PERIOD ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , LANG ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , PERIOD ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , SUPPRESS_WARNINGS ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , OPEN_PAREN ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , OPEN_CURLY ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , UNCHECKED ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , COMMA ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , RAWTYPES ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , CLOSE_CURLY ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , CLOSE_PAREN ) ;
	}

	/**
	 * Process the specified elements.
	 *
	 * @param rootElements the set of elements
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    process
	    (
	     final Set < ? extends Element > rootElements ,
	     final StringBuilder stringBuilder
	     )
	{
	    for ( Element rootElement : rootElements )
		{
		    process ( rootElement , stringBuilder ) ;
		}
	}

	/**
	 * process the specified element.
	 *
	 * @param rootElement the specified element
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    process
	    (
	     final Element rootElement ,
	     final StringBuilder stringBuilder
	     )
	{
	    ElementKind kind = rootElement . getKind ( ) ;
	    switch ( kind )
		{
		case CLASS :
		    type ( rootElement , stringBuilder ) ;
		    break ;
		case ANNOTATION_TYPE :
		case INTERFACE :
		case PACKAGE :
		    break ;
		default :
		    assert false : kind ;
		    break ;
		}
	}

	/**
	 * Writes the specified class element.
	 *
	 * @param rootElement the specified element
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    type
	    (
	     final Element rootElement ,
	     final StringBuilder stringBuilder
	     )
	{
	    Set < Modifier > modifiers = rootElement . getModifiers ( ) ;
	    boolean isAbstract = modifiers . contains ( Modifier . ABSTRACT ) ;
	    type ( rootElement , isAbstract , stringBuilder ) ;
	}

	/**
	 * Writes the specified class element (if it is abstract).
	 *
	 * @param rootElement the specified element
	 * @param isAbstract if true then write
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    type
	    (
	     final Element rootElement ,
	     final boolean isAbstract ,
	     final StringBuilder stringBuilder
	     )
	{
	    if ( isAbstract )
		{
		    TypeElement element = ( TypeElement ) ( rootElement ) ;
		    clazz ( element , stringBuilder ) ;
		}
	}

	/**
	 * Writes a class implementation.
	 *
	 * @param rootElement the class
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    clazz
	    (
	     final TypeElement rootElement ,
	     final StringBuilder stringBuilder
	     )
	{
	    implementClass ( rootElement , stringBuilder ) ;
	    testUnit ( rootElement , stringBuilder ) ;
	}

	private
	    void
	    implementClass
	    (
	     TypeElement rootElement ,
	     StringBuilder stringBuilder
	     )
	{
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , CLASS ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    Object simpleName = rootElement . getSimpleName ( ) ;
	    append ( stringBuilder , true , simpleName ) ;
	    append ( stringBuilder , true , UNDERSCORE ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    List < ? extends TypeParameterElement > typeParameters =
		rootElement . getTypeParameters ( ) ;
	    typeParameters ( rootElement , true , true , stringBuilder ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , EXTENDS ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    Object qualifiedName = rootElement . getQualifiedName ( ) ;
	    append ( stringBuilder , true , qualifiedName ) ;
	    typeParameters ( rootElement , true , true , stringBuilder ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , OPEN_CURLY ) ;
	    List < ? extends Element > enclosedElements =
		rootElement . getEnclosedElements ( ) ;
	    classParameters ( typeParameters , enclosedElements , DECLARATION , stringBuilder ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , simpleName ) ;
	    append ( stringBuilder , true , UNDERSCORE ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , OPEN_PAREN ) ;
	    classParameters ( typeParameters , enclosedElements , PARAMETER , stringBuilder ) ;
	    append ( stringBuilder , true , CLOSE_PAREN ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , OPEN_CURLY ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    classParameters ( typeParameters , enclosedElements , ASSIGNMENT , stringBuilder ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , CLOSE_CURLY ) ;
	    methods ( enclosedElements , stringBuilder ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , CLOSE_CURLY ) ;
	}

	private 
	    void
	    testUnit
	    ( TypeElement rootElement , StringBuilder stringBuilder )
	{
	    boolean isUnitTest = isUnitTest ( ) ;
	    publicTestUnit ( rootElement , stringBuilder ) ;
	    privateTestUnit ( rootElement , stringBuilder ) ;
	}

	private void publicTestUnit ( TypeElement rootElement , StringBuilder stringBuilder )
	{
	    boolean isUnitTest = isUnitTest ( ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    testAnnotation ( stringBuilder ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , PUBLIC ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    typeParameters ( rootElement , isUnitTest , true , stringBuilder ) ;
	    append ( stringBuilder , isUnitTest , VOID ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , TEST_PREFIX ) ;
	    Object simpleName = rootElement . getSimpleName ( ) ;
	    append ( stringBuilder , isUnitTest , simpleName ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , OPEN_PAREN ) ;
	    append ( stringBuilder , isUnitTest , CLOSE_PAREN ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , OPEN_CURLY ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , TEST_PREFIX ) ;
	    append ( stringBuilder , isUnitTest , simpleName ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , OPEN_PAREN ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , NEW ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , simpleName ) ;
	    append ( stringBuilder , isUnitTest , UNDERSCORE ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    typeParameters ( rootElement , isUnitTest , false , stringBuilder ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , OPEN_PAREN ) ;
	    boolean first = true ;
	    for ( TypeParameterElement typeParemeter : rootElement . getTypeParameters ( ) )
		{
		    append ( stringBuilder , isUnitTest , SPACE ) ;
		    append ( stringBuilder , isUnitTest && ! first , COMMA ) ;
		    append ( stringBuilder , isUnitTest && ! first , SPACE ) ;
		    append ( stringBuilder , isUnitTest , JAVA ) ;
		    append ( stringBuilder , isUnitTest , SPACE ) ;
		    append ( stringBuilder , isUnitTest , PERIOD ) ;
		    append ( stringBuilder , isUnitTest , SPACE ) ;
		    append ( stringBuilder , isUnitTest , LANG ) ;
		    append ( stringBuilder , isUnitTest , SPACE ) ;
		    append ( stringBuilder , isUnitTest , PERIOD ) ;
		    append ( stringBuilder , isUnitTest , SPACE ) ;
		    append ( stringBuilder , isUnitTest , OBJECT ) ;
		    append ( stringBuilder , isUnitTest , SPACE ) ;
		    append ( stringBuilder , isUnitTest , PERIOD ) ;
		    append ( stringBuilder , isUnitTest , SPACE ) ;
		    append ( stringBuilder , isUnitTest , CLASS ) ;
		    first = false ;
		}
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , CLOSE_PAREN ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , CLOSE_PAREN ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , SEMICOLON ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , CLOSE_CURLY ) ;
	}

	private void testAnnotation ( StringBuilder stringBuilder )
	{
	    boolean isUnitTest = isUnitTest ( ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , AT ) ;
	    append ( stringBuilder , isUnitTest , ORG ) ;
	    append ( stringBuilder , isUnitTest , PERIOD ) ;
	    append ( stringBuilder , isUnitTest , JUNIT ) ;
	    append ( stringBuilder , isUnitTest , PERIOD ) ;
	    append ( stringBuilder , isUnitTest , TEST ) ;
	}

	private void privateTestUnit ( TypeElement rootElement , StringBuilder stringBuilder )
	{
	    boolean isUnitTest = isUnitTest ( ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , VOID ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    Object simpleName = rootElement . getSimpleName ( ) ;
	    append ( stringBuilder , isUnitTest , TEST_PREFIX ) ;
	    append ( stringBuilder , isUnitTest , simpleName ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , OPEN_PAREN ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    Object qualifiedName = rootElement . getQualifiedName ( ) ;
	    append ( stringBuilder , isUnitTest , qualifiedName ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    typeParameters ( rootElement , isUnitTest , false , stringBuilder ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , simpleName ) ;
	    append ( stringBuilder , isUnitTest , SPACE ) ;
	    append ( stringBuilder , isUnitTest , CLOSE_PAREN ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , OPEN_CURLY ) ;
	    append ( stringBuilder , isUnitTest , NEWLINE ) ;
	    append ( stringBuilder , isUnitTest , TAB ) ;
	    append ( stringBuilder , isUnitTest , CLOSE_CURLY ) ;
	}

	/**
	 * Writes the typeParameters for a class.
	 *
	 * @param element the specified class
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    typeParameters
	    (
	     final TypeElement element ,
	     boolean print ,
	     boolean parameter ,
	     final StringBuilder stringBuilder
	     )
	{
	    List < ? extends TypeParameterElement > typeParameters =
		element . getTypeParameters ( ) ;
	    typeParameters ( typeParameters , print , parameter , stringBuilder ) ;
	}

	/**
	 * Writes the typeParameters for a method.
	 *
	 * @param element the specified method
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    typeParameters
	    (
	     final ExecutableElement element ,
	     final boolean print ,
	     final boolean parameter ,
	     final StringBuilder stringBuilder
	     )
	{
	    List < ? extends TypeParameterElement > typeParameters =
		element . getTypeParameters ( ) ;
	    typeParameters ( typeParameters , print , parameter , stringBuilder ) ;
	}

	/**
	 * Writes the type parameters.
	 * Assumes that type parameters are always simple:
	 * no wildcards, extends, or supers
	 *
	 * @param typeParameters the type parameters
	 * @param stringBuilder for writing.
	 **/
	private
	    void
	    typeParameters
	    (
	     final List < ? extends TypeParameterElement > typeParameters ,
	     boolean print ,
	     boolean parameter ,
	     final StringBuilder stringBuilder
	     )
	{
	    boolean first = true ;
	    for ( TypeParameterElement typeParameter : typeParameters )
		{
		    boolean printParameter = print && parameter ;
		    boolean printArgument = print && ! parameter ;
		    boolean printOpen = print && first ;
		    append ( stringBuilder , printOpen , SPACE ) ;
		    append ( stringBuilder , printOpen , OPEN_ANGLE ) ;
		    boolean printComma = print && ! first ;
		    append ( stringBuilder , printComma , SPACE ) ;
		    append ( stringBuilder , printComma , COMMA ) ;
		    first = false ;
		    Object simpleName = typeParameter . getSimpleName ( ) ;
		    append ( stringBuilder , print , SPACE ) ;
		    append ( stringBuilder , printParameter , simpleName ) ;	
		    append ( stringBuilder , printArgument , JAVA ) ;
		    append ( stringBuilder , printArgument , PERIOD ) ;
		    append ( stringBuilder , printArgument , LANG ) ;
		    append ( stringBuilder , printArgument , PERIOD ) ;
		    append ( stringBuilder , printArgument , OBJECT ) ;
		}
	    boolean printClose = print && ! first ;
	    append ( stringBuilder , printClose , SPACE ) ;
	    append ( stringBuilder , printClose , CLOSE_ANGLE ) ;
	}

	private void classParameters
	    (
	     final List < ? extends TypeParameterElement > typeParameters ,
	     final List < ? extends Element > enclosedElements ,
	     final int level ,
	     final StringBuilder stringBuilder
	     )
	{
	    classParametersType ( typeParameters , level , stringBuilder ) ;
	    classParametersEnclosed ( enclosedElements , level , stringBuilder ) ;
	}

	private void classParametersEnclosed
	    (
	     final List < ? extends Element > enclosedElements ,
	     final int level ,
	     final StringBuilder stringBuilder
	     )
	{
	    boolean first = true ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    ExecutableElement executableElement =
			( ExecutableElement ) ( enclosedElement ) ;
		    TypeMirror returnType =
			executableElement . getReturnType ( ) ;
		    first = classParameterEnclosed ( executableElement , returnType , level , first , stringBuilder ) ;
		}
	}

	private void classParametersType
	    (
	     final List < ? extends TypeParameterElement > typeParameters ,
	     int level ,
	     final StringBuilder stringBuilder
	     )
	{
	    boolean first = true ;
	    for ( Element typeParameter : typeParameters )
		{
		    TypeMirror returnType = typeParameter . asType ( ) ;
		    first = classParameterType ( typeParameter , returnType , level , first , stringBuilder ) ;
		}
	}

	private boolean classParameterEnclosed
	    (
	     Element element ,
	     TypeMirror returnType ,
	     int level ,
	     boolean first ,
	     StringBuilder stringBuilder
	     )
	{
	    Object simpleName = element . getSimpleName ( ) ;
	    boolean isProduction = isProduction ( ) ;
	    boolean isDeclaration = DECLARATION == level ;
	    boolean isProductionDeclaration = isProduction && isDeclaration ;
	    append ( stringBuilder , isProductionDeclaration , NEWLINE ) ;
	    append ( stringBuilder , isProductionDeclaration , NEWLINE ) ;
	    append ( stringBuilder , isProductionDeclaration , TAB ) ;
	    append ( stringBuilder , isProductionDeclaration , TAB ) ;
	    append ( stringBuilder , isProductionDeclaration , returnType ) ;
	    append ( stringBuilder , isProductionDeclaration , SPACE ) ;
	    append ( stringBuilder , isProductionDeclaration , simpleName ) ;
	    append ( stringBuilder , isProductionDeclaration , SEMICOLON ) ;
	    boolean isParameter = PARAMETER == level ;
	    boolean isProductionParameter = isProduction && isParameter ;
	    boolean isProductionParameterComma = isProductionParameter && ! first ;
	    append ( stringBuilder , isProductionParameter , SPACE ) ;
	    append ( stringBuilder , isProductionParameterComma , COMMA ) ;
	    append ( stringBuilder , isProductionParameterComma , SPACE ) ;
	    append ( stringBuilder , isProductionParameter , returnType ) ;
	    append ( stringBuilder , isProductionParameter , SPACE ) ;
	    append ( stringBuilder , isProductionParameter , simpleName ) ;
	    boolean isAssignment = ASSIGNMENT == level ;
	    boolean isProductionAssignment = isProduction && isAssignment ;
	    append ( stringBuilder , isProductionAssignment , NEWLINE ) ;
	    append ( stringBuilder , isProductionAssignment , TAB ) ;
	    append ( stringBuilder , isProductionAssignment , TAB ) ;
	    append ( stringBuilder , isProductionAssignment , TAB ) ;
	    append ( stringBuilder , isProductionAssignment , THIS ) ;
	    append ( stringBuilder , isProductionAssignment , SPACE ) ;
	    append ( stringBuilder , isProductionAssignment , PERIOD ) ;
	    append ( stringBuilder , isProductionAssignment , SPACE ) ;
	    append ( stringBuilder , isProductionAssignment , simpleName ) ;
	    append ( stringBuilder , isProductionAssignment , SPACE ) ;
	    append ( stringBuilder , isProductionAssignment , EQUALS ) ;
	    append ( stringBuilder , isProductionAssignment , SPACE ) ;
	    append ( stringBuilder , isProductionAssignment , simpleName ) ;
	    append ( stringBuilder , isProductionAssignment , SPACE ) ;
	    append ( stringBuilder , isProductionAssignment , SEMICOLON ) ;
	    boolean isArgument = ARGUMENT == level ;
	    boolean isProductionArgument = isProduction && isArgument ;
	    boolean isNotFirstProductionArgument = isProductionArgument && ! first ;
	    append ( stringBuilder , isNotFirstProductionArgument , COMMA ) ;
	    append ( stringBuilder , isProductionArgument , simpleName ) ;
	    UseParameter useParameter = element . getAnnotation ( UseParameter . class ) ;
	    UseConstructor useConstructor = element . getAnnotation ( UseConstructor . class ) ;
	    boolean use = ( null != useParameter ) || ( null != useConstructor ) ;
	    boolean isUnitTest = isUnitTest ( ) ;
	    boolean isUnitTestDeclaration = isUnitTest && isDeclaration && use ;
	    append ( stringBuilder , isUnitTestDeclaration , NEWLINE ) ;
	    append ( stringBuilder , isUnitTestDeclaration , NEWLINE ) ;
	    append ( stringBuilder , isUnitTestDeclaration , TAB ) ;
	    append ( stringBuilder , isUnitTestDeclaration , TAB ) ;
	    TypeKind kind = returnType . getKind ( ) ;
	    switch ( kind )
		{
		case DECLARED :
		    DeclaredType declaredType = ( DeclaredType ) ( returnType ) ;
		    Element e1 = declaredType . asElement ( ) ;
		    TypeElement typeElement = ( TypeElement ) ( e1 ) ;
		    Object qualifiedName = typeElement . getQualifiedName ( ) ;
		    append ( stringBuilder , isUnitTestDeclaration , qualifiedName ) ;
		    break ;
		case TYPEVAR :
		    TypeVariable typeVariable = ( TypeVariable ) ( returnType ) ;
		    Element e2 = typeVariable . asElement ( ) ;
		    Object sn = e2 . getSimpleName ( ) ;
		    append ( stringBuilder , isUnitTestDeclaration , sn ) ;
		}
	    append ( stringBuilder , isUnitTestDeclaration , SPACE ) ;
	    append ( stringBuilder , isUnitTestDeclaration , simpleName ) ;
	    append ( stringBuilder , isUnitTestDeclaration , SPACE ) ;
	    append ( stringBuilder , isUnitTestDeclaration , SEMICOLON ) ;
	    boolean isFinal = false ;
	    switch ( kind )
		{
		case DECLARED :
		    DeclaredType declaredType = ( DeclaredType ) ( returnType ) ;
		    Element e1 = declaredType . asElement ( ) ;
		    Set < Modifier > modifiers = e1 . getModifiers ( ) ;
		    isFinal = modifiers . contains ( Modifier . FINAL ) ;
		    break ;
		}
	    boolean isUnitTestAssignment = isUnitTest && isAssignment && use && ! isFinal ;
	    append ( stringBuilder , isUnitTestAssignment , NEWLINE ) ;
	    append ( stringBuilder , isUnitTestAssignment , TAB ) ;
	    append ( stringBuilder , isUnitTestAssignment , TAB ) ;
	    append ( stringBuilder , isUnitTestAssignment , TAB ) ;
	    append ( stringBuilder , isUnitTestAssignment , simpleName ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , EQUALS ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , ORG ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , PERIOD ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , MOCKITO_PACKAGE ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , PERIOD ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , MOCKITO_CLASS ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , PERIOD ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , MOCK ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , OPEN_PAREN ) ;
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    switch ( kind )
		{
		case DECLARED :
		    DeclaredType declaredType = ( DeclaredType ) ( returnType ) ;
		    Element e = declaredType . asElement ( ) ;
		    TypeElement typeElement = ( TypeElement ) ( e ) ;
		    Object qualifiedName = typeElement . getQualifiedName ( ) ;
		    append ( stringBuilder , isUnitTestAssignment , qualifiedName ) ;
		    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
		    append ( stringBuilder , isUnitTestAssignment , PERIOD ) ;
		    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
		    append ( stringBuilder , isUnitTestAssignment , CLASS ) ;
		    break ;
		case TYPEVAR :
		    TypeVariable typeVariable = ( TypeVariable ) ( returnType ) ;
		    Element e2 = typeVariable . asElement ( ) ;
		    Object sn = e2 . getSimpleName ( ) ;
		    append ( stringBuilder , isUnitTestAssignment , sn ) ;
		    break ;
		}
	    append ( stringBuilder , isUnitTestAssignment , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignment , CLOSE_PAREN ) ;
	    append ( stringBuilder , isUnitTestAssignment , SEMICOLON ) ;
	    boolean isUnitTestAssignmentFinal = isUnitTest && isAssignment && use && isFinal ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , NEWLINE ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , NEWLINE ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , TAB ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , TAB ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , TAB ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , THIS ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , PERIOD ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , simpleName ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , EQUALS ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , NEW ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , returnType ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , SPACE ) ;
	    ExecutableElement executableElement = ( ExecutableElement ) ( element ) ;
	    List < ? extends Element > parameters = executableElement . getParameters ( ) ;
	    parameters ( parameters , isUnitTestAssignmentFinal , false , stringBuilder ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , SPACE ) ;
	    append ( stringBuilder , isUnitTestAssignmentFinal , SEMICOLON ) ;
	    return false ;
	}

	private boolean classParameterType
	    (
	     Element element ,
	     TypeMirror returnType ,
	     int level ,
	     boolean first ,
	     StringBuilder stringBuilder
	     )
	{
	    boolean isUnitTest = isUnitTest ( ) ;
	    boolean isDeclaration = DECLARATION == level ;
	    boolean printDeclaration = isUnitTest && isDeclaration ;
	    append ( stringBuilder , printDeclaration , NEWLINE ) ;
	    append ( stringBuilder , printDeclaration , NEWLINE ) ;
	    append ( stringBuilder , printDeclaration , TAB ) ;
	    append ( stringBuilder , printDeclaration , TAB ) ;
	    append ( stringBuilder , printDeclaration , JAVA ) ;
	    append ( stringBuilder , printDeclaration , PERIOD ) ;
	    append ( stringBuilder , printDeclaration , LANG ) ;
	    append ( stringBuilder , printDeclaration , PERIOD ) ;
	    append ( stringBuilder , printDeclaration , CLASS_OBJECT ) ;
	    append ( stringBuilder , printDeclaration , SPACE ) ;
	    append ( stringBuilder , printDeclaration , OPEN_ANGLE ) ;
	    append ( stringBuilder , printDeclaration , SPACE ) ;
	    append ( stringBuilder , printDeclaration , returnType ) ;
	    append ( stringBuilder , printDeclaration , SPACE ) ;
	    append ( stringBuilder , printDeclaration , CLOSE_ANGLE ) ;
	    append ( stringBuilder , printDeclaration , SPACE ) ;
	    Object simpleName = element . getSimpleName ( ) ;
	    append ( stringBuilder , printDeclaration , simpleName ) ;
	    append ( stringBuilder , printDeclaration , SPACE ) ;
	    append ( stringBuilder , printDeclaration , SEMICOLON ) ;
	    boolean isParameter = PARAMETER == level ;
	    boolean printParameter = isUnitTest && isParameter ;
	    boolean printNotFirstParameter = printParameter && ! first ;
	    append ( stringBuilder , printParameter , SPACE ) ;
	    append ( stringBuilder , printNotFirstParameter , COMMA ) ;
	    append ( stringBuilder , printNotFirstParameter , SPACE ) ;
	    append ( stringBuilder , printParameter , JAVA ) ;
	    append ( stringBuilder , printParameter , PERIOD ) ;
	    append ( stringBuilder , printParameter , LANG ) ;
	    append ( stringBuilder , printParameter , PERIOD ) ;
	    append ( stringBuilder , printParameter , CLASS_OBJECT ) ;
	    append ( stringBuilder , printParameter , SPACE ) ;
	    append ( stringBuilder , printParameter , OPEN_ANGLE ) ;
	    append ( stringBuilder , printParameter , SPACE ) ;
	    append ( stringBuilder , printParameter , returnType ) ;
	    append ( stringBuilder , printParameter , SPACE ) ;
	    append ( stringBuilder , printParameter , CLOSE_ANGLE ) ;
	    append ( stringBuilder , printParameter , SPACE ) ;
	    append ( stringBuilder , printParameter , simpleName ) ;
	    boolean isAssignment = ASSIGNMENT == level ;
	    boolean printAssignment = isUnitTest && isAssignment ;
	    append ( stringBuilder , printAssignment , NEWLINE ) ;
	    append ( stringBuilder , printAssignment , TAB ) ;
	    append ( stringBuilder , printAssignment , TAB ) ;
	    append ( stringBuilder , printAssignment , TAB ) ;
	    append ( stringBuilder , printAssignment , THIS ) ;
	    append ( stringBuilder , printAssignment , SPACE ) ;
	    append ( stringBuilder , printAssignment , PERIOD ) ;
	    append ( stringBuilder , printAssignment , SPACE ) ;
	    append ( stringBuilder , printAssignment , simpleName ) ;
	    append ( stringBuilder , printAssignment , SPACE ) ;
	    append ( stringBuilder , printAssignment , EQUALS ) ;
	    append ( stringBuilder , printAssignment , SPACE ) ;
	    append ( stringBuilder , printAssignment , simpleName ) ;
	    append ( stringBuilder , printAssignment , SPACE ) ;
	    append ( stringBuilder , printAssignment , SEMICOLON ) ;
	    return false ;
	}

	/**
	 * Writes the specified methods.
	 *
	 * @param enclosedElements the specified methods
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    methods
	    (
	     final List < ? extends Element > enclosedElements ,
	     final StringBuilder stringBuilder
	     )
	{
	    for ( Element enclosedElement : enclosedElements )
		{
		    method ( enclosedElement , stringBuilder ) ;
		}
	}

	/**
	 * Writes the specified method (if appropriate).
	 *
	 * @param enclosedElement the specified method
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    method
	    (
	     final Element enclosedElement ,
	     final StringBuilder stringBuilder
	     )
	{
	    Set < Modifier > modifiers = enclosedElement . getModifiers ( ) ;
	    boolean isAbstract = modifiers . contains ( Modifier . ABSTRACT ) ;
	    method ( enclosedElement , isAbstract , stringBuilder ) ;
	}

	/**
	 * Writes the specified method.
	 *
	 * @param enclosedElement the specified method
	 * @param isAbstract (if not true do nothing)
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    method
	    (
	     final Element enclosedElement ,
	     final boolean isAbstract ,
	     final StringBuilder stringBuilder
	     )
	{
	    if ( isAbstract )
		{
		    method
			(
			 ( ExecutableElement ) ( enclosedElement ) ,
			 stringBuilder
			 ) ;
		}
	}

	/**
	 * Writes the specified method.
	 *
	 * @param enclosedElement the specified method
	 * @param stringBuilder for writing
	 **/
	private
	    void method
	    (
	     final ExecutableElement enclosedElement ,
	     final StringBuilder stringBuilder
	     )
	{
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , "public" ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    typeParameters ( enclosedElement , true , true , stringBuilder ) ;
	    Object returnType = enclosedElement . getReturnType ( ) ;
	    append ( stringBuilder , true , returnType ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    append ( stringBuilder , true , simpleName ) ;
	    List < ? extends Element > parameters =
		enclosedElement . getParameters ( ) ;
	    parameters ( parameters , true , true , stringBuilder ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , OPEN_CURLY ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , TAB ) ;
	    implementation ( enclosedElement , stringBuilder ) ;
	    append ( stringBuilder , true , SEMICOLON ) ;
	    append ( stringBuilder , true , NEWLINE ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , TAB ) ;
	    append ( stringBuilder , true , CLOSE_CURLY ) ;
	}

	/**
	 * Implements the specified method (body).
	 *
	 * @param enclosedElement the specified element
	 * @param stringBuilder for writing the implementation
	 **/
	private
	    void
	    implementation
	    (
	     final ExecutableElement enclosedElement ,
	     final StringBuilder stringBuilder
	     )
	{
	    UseConstructor useConstructor =
		enclosedElement . getAnnotation ( UseConstructor . class ) ;
	    UseNull useNull =
		enclosedElement . getAnnotation ( UseNull . class ) ;
	    UseParameter useParameter =
		enclosedElement . getAnnotation ( UseParameter . class ) ;
	    UseStaticMethod useStaticMethod =
		enclosedElement . getAnnotation ( UseStaticMethod . class ) ;
	    UseStringConstant useStringConstant =
		enclosedElement . getAnnotation ( UseStringConstant . class ) ;
	    implementation
		(
		 enclosedElement ,
		 useConstructor ,
		 useNull ,
		 useParameter ,
		 useStaticMethod ,
		 useStringConstant ,
		 stringBuilder
		 ) ;
	}

	/**
	 * Writes the implementation.
	 *
	 * @param enclosedElement the method to implement
	 * @param useConstructor (if not null) the constructor to use
	 * @param useNull (if not null) then return null
	 * @param useParameter (if not null) the parameter
	 * @param useStaticMethod (if not null) the static method
	 * @param useStringConstant (if not null) the string constant
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    implementation
	    (
	     final ExecutableElement enclosedElement ,
	     final UseConstructor useConstructor ,
	     final UseNull useNull ,
	     final UseParameter useParameter ,
	     final UseStaticMethod useStaticMethod ,
	     final UseStringConstant useStringConstant ,
	     final StringBuilder stringBuilder
	     )
	{
	    if ( null != useConstructor )
		{
		    implementation
			( enclosedElement , useConstructor , stringBuilder ) ;
		}
	    else if ( null != useNull )
		{
		    implementation
			( enclosedElement , useNull , stringBuilder ) ;
		}
	    else if ( null != useParameter )
		{
		    implementation
			( enclosedElement , useParameter , stringBuilder ) ;
		}
	    else if ( null != useStaticMethod )
		{
		    implementation
			( enclosedElement , useStaticMethod , stringBuilder ) ;
		}
	    else if ( null != useStringConstant )
		{
		    implementation
			(
			 enclosedElement ,
			 useStringConstant ,
			 stringBuilder
			 ) ;
		}
	    else
		{
		    implementation
			(
			 stringBuilder
			 ) ;
		}
	}

	/**
	 * Writes the method implementation.
	 *
	 * @param enclosedElement the method to implement
	 * @param useConstructor the constructor to use
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    implementation
	    (
	     final ExecutableElement enclosedElement ,
	     final UseConstructor useConstructor ,
	     final StringBuilder stringBuilder
	     )
	{
	    append ( stringBuilder , true , RETURN ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    TypeMirror type = null ;
	    try
		{
		    useConstructor . value ( ) ;
		}
	    catch ( MirroredTypeException cause )
		{
		    type = cause . getTypeMirror ( ) ;
		}
	    boolean isProduction = isProduction ( ) ;
	    append ( stringBuilder , isProduction , NEW ) ;
	    append ( stringBuilder , isProduction , SPACE ) ;
	    append ( stringBuilder , isProduction , type ) ;
	    List < ? extends Element > parameters =
		enclosedElement . getParameters ( ) ;
	    parameters ( parameters , isProduction , false , stringBuilder ) ;
	    boolean isUnitTest = isUnitTest ( ) ;
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    append ( stringBuilder , isUnitTest , simpleName ) ;
	}

	/**
	 * Writes the method implementation.
	 *
	 * @param enclosedElement the method to implement
	 * @param useNull null
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    implementation
	    (
	     final ExecutableElement enclosedElement ,
	     final UseNull useNull ,
	     final StringBuilder stringBuilder
	     )
	{
	    append ( stringBuilder , true , RETURN ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , "null" ) ;
	}

	/**
	 * Writes the method implementation.
	 *
	 * @param enclosedElement the method to implement
	 * @param useParameter the parameter
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    implementation
	    (
	     final ExecutableElement enclosedElement ,
	     final UseParameter useParameter ,
	     final StringBuilder stringBuilder
	     )
	{
	    append ( stringBuilder , true , RETURN ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    append ( stringBuilder , true , simpleName ) ;
	}

	/**
	 * Writes the method implementation.
	 *
	 * @param enclosedElement the method to implement
	 * @param useStaticMethod the static method
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    implementation
	    (
	     final ExecutableElement enclosedElement ,
	     final UseStaticMethod useStaticMethod ,
	     final StringBuilder stringBuilder
	     )
	{
	    Object type = null ;
	    try
		{
		    useStaticMethod . value ( ) ;
		}
	    catch ( MirroredTypeException cause )
		{
		    type = cause . getTypeMirror ( ) ;
		}
	    TypeMirror returnType = enclosedElement . getReturnType ( ) ;
	    TypeKind kind = returnType . getKind ( ) ;
	    boolean isVoid = TypeKind . VOID . equals ( kind ) ;
	    if ( ! isVoid )
		{
		    append ( stringBuilder , true , RETURN ) ;
		    append ( stringBuilder , true , SPACE ) ;
		}
	    append ( stringBuilder , true , type ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    append ( stringBuilder , true , simpleName ) ;
	    List < ? extends Element > parameters =
		enclosedElement . getParameters ( ) ;
	    parameters ( parameters , true , false , stringBuilder ) ;
	}

	/**
	 * Write the method implementation.
	 *
	 * @param enclosedElement the method to implement
	 * @param useStringConstant holds the string constant
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    implementation
	    (
	     final ExecutableElement enclosedElement ,
	     final UseStringConstant useStringConstant ,
	     final StringBuilder stringBuilder
	     )
	{
	    append ( stringBuilder , true , RETURN ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    Object value = useStringConstant . value ( ) ;
	    Elements elementUtils = processingEnv . getElementUtils ( ) ;
	    Object constantExpression =
		elementUtils . getConstantExpression ( value ) ;
	    append ( stringBuilder , true , constantExpression ) ;
	}

	/**
	 * If the method is unannotated, it is not supported.
	 *
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    implementation
	    (
	     final StringBuilder stringBuilder
	     )
	{
	    append ( stringBuilder , true , THROW ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , NEW ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , "UnsupportedOperationException" ) ;
	    append ( stringBuilder , true , OPEN_PAREN ) ;
	    append ( stringBuilder , true , CLOSE_PAREN ) ;
	}

	/**
	 * Write out the parameters.
	 *
	 * @param parameters the parameters
	 * @param formal write out formal parameters (true) or arguments (false)
	 * @param stringBuilder used for writing
	 **/
	private void parameters
	    (
	     final List < ? extends Element > parameters ,
	     final boolean print ,
	     final boolean formal ,
	     final StringBuilder stringBuilder
	     )
	{
	    append ( stringBuilder , print , SPACE ) ;
	    append ( stringBuilder , print , OPEN_PAREN ) ;
	    boolean first = true ;
	    for ( Element parameter : parameters )
		{
		    first =
			parameter
			( first , print , parameter , formal , stringBuilder ) ;
		}
	    append ( stringBuilder , print , SPACE ) ;
	    append ( stringBuilder , print , CLOSE_PAREN ) ;
	}

	/**
	 * Writes a parameter.
	 *
	 * @param first tells us whether we need a comma or not
	 * @param parameter the parameter to write
	 * @param formal if it is in a formal parameter list (true)
	 *               or an argument list (false).
	 *               This determines whether we print the type.
	 * @param stringBuilder used to write
	 * @return false always
	 **/
	private boolean parameter
	    (
	     final boolean first ,
	     final boolean print ,
	     final Element parameter ,
	     final boolean formal ,
	     final StringBuilder stringBuilder
	     )
	{
	    boolean printComma = print && ! first ;
	    append ( stringBuilder , print , SPACE ) ;
	    append ( stringBuilder , printComma , COMMA ) ;
	    append ( stringBuilder , printComma , SPACE ) ;
	    boolean printType = formal && print ;
	    Object type = parameter . asType ( ) ;
	    append ( stringBuilder , printType , type ) ;
	    append ( stringBuilder , printType , SPACE ) ;
	    Object simpleName = parameter . getSimpleName ( ) ;
	    append ( stringBuilder , print , simpleName ) ;
	    return false ;
	}

	/**
	 * Writes the source to file and handles exceptions.
	 * (There should not be any other source of checked exceptions.)
	 *
	 * @param source the source code
	 **/
	private void write ( final Object source )
	{
	    try
		{
		    tryWrite ( source ) ;
		}
	    catch ( IOException cause )
		{
		    Messager messager = processingEnv . getMessager ( ) ;
		    String message = cause . toString ( ) ;
		    messager . printMessage
			( Diagnostic . Kind . ERROR , message ) ;
		}
	}

	/**
	 * Try to write the source to file.
	 *
	 * @param source the source code
	 * @throws IOException if anything bad happens
	 **/
	private void tryWrite ( final Object source ) throws IOException
	{
	    Filer filer = processingEnv . getFiler ( ) ;
	    JavaFileObject file =
		filer . createSourceFile
		( "tastytungsten.Bootstrap" ) ;
	    Writer writer = file . openWriter ( ) ;
	    String string = source . toString ( ) ;
	    writer . write ( string ) ;
	    writer . close ( ) ;
	}

	/**
	 *
	 **/
	private boolean isProduction ( )
	{
	    boolean is = is ( "production" ) ;
	    return is ;
	}

	private boolean isUnitTest ( )
	{
	    boolean is = is ( "unitTest" ) ;
	    return is ;
	}

	private boolean is ( String target )
	{
	    Map < String , String > options = processingEnv . getOptions ( ) ;
	    boolean containsKey = options . containsKey ( target ) ;
	    return containsKey ;
	}
    }
