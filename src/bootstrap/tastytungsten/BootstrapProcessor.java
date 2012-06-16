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
import javax . lang . model . util . Elements ;
import javax . tools . Diagnostic ;
import javax . tools . JavaFileObject ;

import javax . annotation . processing . SupportedAnnotationTypes ;
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
    public final class BootstrapProcessor extends AbstractProcessor
    {
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
	    stringBuilder . append ( "\npackage tastytungsten ;" ) ;
	    stringBuilder . append ( "\nclass Bootstrap" ) ;
	    stringBuilder . append ( "\n{" ) ;
	    process ( rootElements , stringBuilder ) ;
	    stringBuilder . append ( "\n}" ) ;
	    write ( stringBuilder ) ;
	    flag = false ;
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
		default :
		    assert ElementKind . PACKAGE . equals ( kind ) ;
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
		    clazz ( rootElement , stringBuilder ) ;
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
	     final Element rootElement ,
	     final StringBuilder stringBuilder
	     )
	{
	    stringBuilder . append ( "\n\n\tstatic class Bootstrap" ) ;
	    Object simpleName = rootElement . getSimpleName ( ) ;
	    stringBuilder . append ( simpleName ) ;
	    ElementKind kind = rootElement . getKind ( ) ;
	    typeParameters ( rootElement , kind , stringBuilder ) ;
	    stringBuilder . append ( " extends " ) ;
	    stringBuilder . append ( simpleName ) ;
	    typeParameters ( rootElement , kind , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t{" ) ;
	    List < ? extends Element > enclosedElements =
		rootElement . getEnclosedElements ( ) ;
	    classParameters ( enclosedElements , DECLARATION , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t\tBootstrap" ) ;
	    stringBuilder . append ( simpleName ) ;
	    stringBuilder . append ( "(" ) ;
	    classParameters ( enclosedElements , PARAMETER , stringBuilder ) ;
	    stringBuilder . append ( ")" ) ;
	    stringBuilder . append ( "\n\t\t\t{" ) ;
	    classParameters ( enclosedElements , ASSIGNMENT , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t\t}" ) ;
	    methods ( enclosedElements , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t}" ) ;
	}

	/**
	 * Writes the typeParameters for an element.
	 *
	 * @param rootElement the specified element
	 * @param kind the kind of element
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    typeParameters
	    (
	     final Element rootElement ,
	     final ElementKind kind ,
	     final StringBuilder stringBuilder
	     )
	{
	    switch ( kind )
		{
		case CLASS :
		    typeParameters
			(
			 ( TypeElement ) ( rootElement ) ,
			 stringBuilder
			 ) ;
		    break ;
		default :
		    assert ElementKind . METHOD . equals ( kind ) ;
		    typeParameters
			(
			 ( ExecutableElement ) ( rootElement ) ,
			 stringBuilder
			 ) ;
		    break ;
		}
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
	     final StringBuilder stringBuilder
	     )
	{
	    List < ? extends TypeParameterElement > typeParameters =
		element . getTypeParameters ( ) ;
	    typeParameters ( typeParameters , stringBuilder ) ;
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
	     final StringBuilder stringBuilder
	     )
	{
	    List < ? extends TypeParameterElement > typeParameters =
		element . getTypeParameters ( ) ;
	    typeParameters ( typeParameters , stringBuilder ) ;
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
	     final StringBuilder stringBuilder
	     )
	{
	    boolean first = true ;
	    for ( TypeParameterElement typeParameter : typeParameters )
		{
		    stringBuilder . append ( first ? "<" : "," ) ;
		    first = false ;
		    Object simpleName = typeParameter . getSimpleName ( ) ;
		    stringBuilder . append ( simpleName ) ;
		}
	    stringBuilder . append ( first ? "" : ">" ) ;
	}

	/**
	 * Writes the class parameters.
	 *
	 * @param enclosedElements the class parameters.
	 * @param level {@see #DECLARATION}, {@see PARAMETER}, or
	 *        {@see #ASSIGNMENT}.
	 * @param stringBuilder for writing
	 **/
	private
	    void
	    classParameters
	    (
	     final List < ? extends Element > enclosedElements ,
	     final int level ,
	     final StringBuilder stringBuilder
	     )
	{
	    boolean first = true ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    UseParameter useParameter =
			enclosedElement . getAnnotation
			( UseParameter . class ) ;
		    if ( null != useParameter )
			{
			    ExecutableElement executableElement =
				( ExecutableElement ) ( enclosedElement ) ;
			    Object returnType =
				executableElement . getReturnType ( ) ;
			    Object simpleName =
				enclosedElement . getSimpleName ( ) ;
			    switch ( level )
				{
				case DECLARATION :
				    stringBuilder . append ( "\n\t\t\t" ) ;
				    stringBuilder . append ( returnType ) ;
				    stringBuilder . append ( " " ) ;
				    stringBuilder . append ( simpleName ) ;
				    stringBuilder . append ( ";" ) ;
				    break ;
				case PARAMETER :
				    stringBuilder . append
					( first ? "" : "," ) ;
				    stringBuilder . append ( returnType ) ;
				    stringBuilder . append ( " " ) ;
				    stringBuilder . append ( simpleName ) ;
				    first = false ;
				    break ;
				default :
				    assert level == ASSIGNMENT ;
				    stringBuilder . append
					( "\n\t\t\t\tthis .  " ) ;
				    stringBuilder . append ( simpleName ) ;
				    stringBuilder . append ( "=" ) ;
				    stringBuilder . append ( simpleName ) ;
				    stringBuilder.  append ( ";" ) ;
				    break ;
				}
			}
		}
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
	    stringBuilder . append ( "\n\n\t\t\tpublic " ) ;
	    typeParameters ( enclosedElement , stringBuilder ) ;
	    Object returnType = enclosedElement . getReturnType ( ) ;
	    stringBuilder . append ( returnType ) ;
	    stringBuilder . append ( " " ) ;
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    stringBuilder . append ( simpleName ) ;
	    List < ? extends Element > parameters =
		enclosedElement . getParameters ( ) ;
	    parameters ( parameters , true , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t\t{" ) ;
	    stringBuilder . append ( "\n\t\t\t\treturn " ) ;
	    implementation ( enclosedElement , stringBuilder ) ;
	    stringBuilder . append ( ";" ) ;
	    stringBuilder . append ( "\n\t\t\t}" ) ;
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
	    else
		{
		    assert null != useStringConstant ;
		    implementation
			(
			 enclosedElement ,
			 useStringConstant ,
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
	    Object type = null ;
	    try
		{
		    useConstructor . value ( ) ;
		}
	    catch ( MirroredTypeException cause )
		{
		    type = cause . getTypeMirror ( ) ;
		}
	    stringBuilder . append ( "new " ) ;
	    String string = type . toString ( ) ;
	    String replace =
		string . replace ( "tastytungsten." , "Bootstrap" ) ;
	    stringBuilder . append ( replace ) ;
	    typeParameters ( enclosedElement , stringBuilder ) ;
	    List < ? extends Element > parameters =
		enclosedElement . getParameters ( ) ;
	    parameters ( parameters , false , stringBuilder ) ;
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
	    stringBuilder . append ( "null" ) ;
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
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    stringBuilder . append ( simpleName ) ;
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
	    stringBuilder . append ( type ) ;
	    stringBuilder . append ( "." ) ;
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    stringBuilder . append ( simpleName ) ;
	    List < ? extends Element > parameters =
		enclosedElement . getParameters ( ) ;
	    parameters ( parameters , false , stringBuilder ) ;
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
	    Object value = useStringConstant . value ( ) ;
	    Elements elementUtils = processingEnv . getElementUtils ( ) ;
	    Object constantExpression =
		elementUtils . getConstantExpression ( value ) ;
	    stringBuilder . append ( constantExpression ) ;
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
	     final boolean formal ,
	     final StringBuilder stringBuilder
	     )
	{
	    stringBuilder . append ( "(" ) ;
	    boolean first = true ;
	    for ( Element parameter : parameters )
		{
		    first =
			parameter
			( first , parameter , formal , stringBuilder ) ;
		}
	    stringBuilder . append ( ")" ) ;
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
	     final Element parameter ,
	     final boolean formal ,
	     final StringBuilder stringBuilder
	     )
	{
	    Map < Boolean , String > alphaMap =
		new HashMap < Boolean , String > ( ) ;
	    alphaMap . put ( true , "" ) ;
	    alphaMap . put ( false , "," ) ;
	    String alpha = alphaMap . get ( first ) ;
	    stringBuilder . append ( alpha ) ;
	    Map < Boolean , Object > betaMap =
		new HashMap < Boolean , Object > ( ) ;
	    Object type = parameter . asType ( ) ;
	    betaMap . put ( true , type ) ;
	    betaMap . put ( false , "" ) ;
	    Object beta = betaMap . get ( formal ) ;
	    stringBuilder . append ( beta ) ;
	    stringBuilder . append ( " " ) ;
	    Object simpleName = parameter . getSimpleName ( ) ;
	    stringBuilder . append ( simpleName ) ;
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
    }
