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
import java . util . HashSet ;
import java . util . List ;
import java . util . Set ;
import javax . annotation . processing . AbstractProcessor ;
import javax . annotation . processing . Filer ;
import javax . annotation . processing . Messager ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . SourceVersion ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementKind ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . element . Modifier ;
import javax . lang . model . element . PackageElement ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . element . TypeParameterElement ;
import javax . lang . model . element . VariableElement ;
import javax . lang . model . type . DeclaredType ;
import javax . lang . model . type . TypeKind ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . util . Elements ;
import javax . tools . Diagnostic ;
import javax . tools . JavaFileObject ;

import javax . annotation . processing . SupportedAnnotationTypes ;
import javax . annotation . processing . SupportedSourceVersion ;

import java . io . IOException ;
import javax . lang . model . type . MirroredTypeException ;

@ SupportedAnnotationTypes ( "*" )
    @ SupportedSourceVersion ( SourceVersion . RELEASE_6 )
    public class BootstrapProcessor extends AbstractProcessor
    {
	private static final String OPEN_ANGLE = "<" ;

	private static final String CLOSE_ANGLE = ">" ;
	
	private static final String OPEN_CURLY = "{" ;

	private static final String CLOSE_CURLY = "}" ;
	
	private static final String OPEN_PAREN = "(" ;

	private static final String CLOSE_PAREN = ")" ;

	private static final String OPEN_DOUBLE_QUOTE = "\"" ;

	private static final String CLOSE_DOUBLE_QUOTE = "\"" ;

	private static final String BLANK = "" ;

	private static final String NEWLINE = "\n" ;

	private static final String SPACE = " " ;

	private static final String TAB = "\t" ;

	private static final String AT = "@" ;

	private static final String COMMA = "," ;

	private static final String EQUALS = "=" ;

	private static final String PERIOD = "." ;

	private static final String SEMICOLON = ";" ;

	private static final String UNDERSCORE = "_" ;

	private static final String CLASS = "class" ;

	private static final String EXTENDS = "extends" ;

	private static final String NEW = "new" ;

	private static final String RETURN = "return" ;

	private static final String SUPER = "super" ;

	private static final String THIS = "this" ;

	private static final String THROW = "throw" ;

	private static final String PACKAGE = "package" ;

	private static final String PUBLIC = "public" ;

	private static final String GENERATED = "Generated" ;

	private static final String ANNOTATION = "annotation" ;

	private static final String JAVA = "java" ;

	private static final String JAVAX = "javax" ;

	private static final String LANG = "lang" ;

	private static final String OVERRIDE = "Override" ;

	private static final String SUPPRESS_WARNINGS = "SuppressWarnings" ;

	private static final String UNCHECKED = "unchecked" ;

	private static final String UNSUPPORTED_OPERATION_EXCEPTION = "UnsupportedOperationException" ;

	private static final String JUNIT = "junit" ;

	private static final String MOCK = "mock" ;

	private static final String MOCKITO_CLASS = "Mockito" ;

	private static final String MOCKITO_PACKAGE = "mockito" ;

	private static final String ORG = "org" ;

	private static final String TEST = "Test" ;

	private static final String BOOTSTRAP = "Bootstrap" ;

	private Set < String > processed = new HashSet < String > ( ) ;

	@ Override
	    public final boolean process ( Set < ? extends TypeElement > annotations , RoundEnvironment roundEnvironment )
	{
	    Set < ? extends Element > rootElements = roundEnvironment . getRootElements ( ) ;
	    process ( rootElements ) ;
	    return true ;
	}

	private void process ( Set < ? extends Element > rootElements )
	{
	    for ( Element rootElement : rootElements )
		{
		    process ( rootElement ) ;
		}
	}

	private void process ( Element rootElement )
	{
	    ElementKind kind = rootElement . getKind ( ) ;
	    switch ( kind )
		{
		case PACKAGE :
		    process ( ( PackageElement ) ( rootElement ) ) ;
		    break ;
		case CLASS :
		case ANNOTATION_TYPE :
		case INTERFACE :
		case ENUM :
		    Element enclosingElement = rootElement . getEnclosingElement ( ) ;
		    process ( enclosingElement ) ;
		    break ;
		default :
		    assert false : kind ;
		}
	}

	private void process ( PackageElement element )
	{
	    Object qualifiedName = element . getQualifiedName ( ) ;
	    String key = qualifiedName . toString ( ) ;
	    boolean contains = processed . contains ( key ) ;
	    if ( ! contains )
		{
		    StringBuilder stringBuilder = new StringBuilder ( ) ;
		    append ( stringBuilder , true , 0 , PACKAGE ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , qualifiedName ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    append ( stringBuilder , true , NEWLINE ) ;
		    printGeneratedAnnotation ( stringBuilder , 0 ) ;
		    append ( stringBuilder , true , 0 , PUBLIC ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , CLASS ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , BOOTSTRAP ) ;
		    append ( stringBuilder , true , 0 , OPEN_CURLY ) ;
		    List < ? extends Element > enclosedElements = element . getEnclosedElements ( ) ;
		    boolean first = true ;
		    for ( Element enclosedElement : enclosedElements )
			{
			    first = manage ( enclosedElement , stringBuilder , first ) ;
			}
		    append ( stringBuilder , true , 0 , CLOSE_CURLY ) ;
		    append ( stringBuilder , true , NEWLINE ) ;
		    String source = stringBuilder . toString ( ) ;
		    StringBuilder name = new StringBuilder ( ) ;
		    append ( name , true , qualifiedName ) ;
		    append ( name , true , PERIOD ) ;
		    append ( name , true , BOOTSTRAP ) ;
		    Filer filer = processingEnv . getFiler ( ) ;
		    try
			{
			    JavaFileObject file = filer . createSourceFile ( name , element ) ;
			    Writer writer = file . openWriter ( ) ;
			    writer . write ( source ) ;
			    writer . close ( ) ;
			}
		    catch ( IOException cause )
			{
			    Messager messager = processingEnv . getMessager ( ) ;
			    String message = cause . getMessage ( ) ;
			    messager . printMessage ( Diagnostic . Kind . ERROR , message , element ) ;
			}
		    processed . add ( key ) ;
		}
	}

	private boolean manage ( Element enclosedElement , StringBuilder stringBuilder , boolean first )
	{
	    ElementKind kind = enclosedElement . getKind ( ) ;
	    switch ( kind )
		{
		case CLASS :
		    first = manage ( ( TypeElement ) ( enclosedElement ) , 1 , stringBuilder , first ) ;
		    break ;
		case ANNOTATION_TYPE :
		case INTERFACE :
		case ENUM :
		case CONSTRUCTOR :
		case METHOD :
		    break ;
		default :
		    assert false : kind ;
		}
	    return first ;
	}

	private boolean manage ( TypeElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    boolean isAbstract = isAbstract ( element ) ;
	    if ( isAbstract )
		{
		    append ( stringBuilder , ! first , NEWLINE ) ;
		    printGeneratedAnnotation ( stringBuilder , indent ) ;
		    append ( stringBuilder , true , indent , PUBLIC ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , CLASS ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    Object simpleName = element . getSimpleName ( ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    append ( stringBuilder , true , UNDERSCORE ) ;
		    typeParameters ( element , stringBuilder ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , EXTENDS ) ;
		    Element enclosingElement = element . getEnclosingElement ( ) ;
		    ElementKind kind = enclosingElement . getKind ( ) ;
		    switch ( kind )
			{
			case CLASS :
			    append ( stringBuilder , true , SPACE ) ;
			    TypeElement typeElement = ( TypeElement ) ( enclosingElement ) ;
			    Object te = typeElement . asType ( ) ;
			    append ( stringBuilder , true , te ) ;
			    append ( stringBuilder , true , SPACE ) ;
			    append ( stringBuilder , true , PERIOD ) ;
			    break ;
			case PACKAGE :
			    break ;
			default :
			    assert false : kind ;
			}
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    typeParameters ( element , stringBuilder ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , indent , OPEN_CURLY ) ;
		    boolean declarations = declarations ( element , indent + 1 , stringBuilder , true ) ;
		    append ( stringBuilder , ! declarations , NEWLINE ) ;
		    append ( stringBuilder , true , indent + 1 , simpleName ) ;
		    append ( stringBuilder , true , UNDERSCORE ) ;
		    boolean parameters = parameters ( element , indent + 1 , stringBuilder , true ) ;
		    append ( stringBuilder , true , indent + 1 , OPEN_CURLY ) ;
		    switch ( kind )
			{
			case CLASS :
			    Object sn = enclosingElement . getSimpleName ( ) ;
			    append ( stringBuilder , true , indent , sn ) ;
			    append ( stringBuilder , true , SPACE ) ;
			    append ( stringBuilder , true , PERIOD ) ;
			    append ( stringBuilder , true , SPACE ) ;
			    append ( stringBuilder , true , SUPER ) ;
			    append ( stringBuilder , true , SPACE ) ;
			    append ( stringBuilder , true , OPEN_PAREN ) ;
			    append ( stringBuilder , true , SPACE ) ;
			    append ( stringBuilder , true , CLOSE_PAREN ) ;
			    append ( stringBuilder , true , SPACE ) ;
			    append ( stringBuilder , true , SEMICOLON ) ;
			    break ;
			case PACKAGE :
			    break ;
			default :
			    assert false : kind ;
			}
		    boolean assignments = assignments ( element , indent + 1 , stringBuilder , true ) ;
		    append ( stringBuilder , true , indent + 1 , CLOSE_CURLY ) ;
		    boolean implementations = implementations ( element , indent + 1 , stringBuilder , true ) ;
		    append ( stringBuilder , true , indent , CLOSE_CURLY ) ;
		    List < ? extends Element > enclosedElements = element . getEnclosedElements ( ) ;
		    /*
		    for ( Element enclosedElement : enclosedElements )
			{
			    manage ( enclosedElement , stringBuilder , true ) ;
			}
		    */
		    first = false ;
		}
	    return false ;
	}

	private boolean declarations ( TypeElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    List < ? extends Element > enclosedElements = element . getEnclosedElements ( ) ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    first = declaration ( enclosedElement , indent , stringBuilder , first ) ;
		}
	    return first ;
	}

	private boolean declaration ( Element enclosedElement , int indent , StringBuilder stringBuilder , boolean first )
	{
	    ElementKind kind = enclosedElement . getKind ( ) ;
	    switch ( kind )
		{
		case METHOD :
		    first = declaration ( ( ExecutableElement ) ( enclosedElement ) , indent , stringBuilder , first ) ;
		    break ;
		case CONSTRUCTOR :
		case CLASS :
		    break ;
		default :
		    assert false : kind ;
		break ;
		}
	    return first ;
	}

	private boolean declaration ( ExecutableElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    UseParameter useParameter = element . getAnnotation ( UseParameter . class ) ;
	    Object type = element . getReturnType ( ) ;
	    Object simpleName = element . getSimpleName ( ) ;
	    first = declaration ( useParameter , type , simpleName , indent , stringBuilder , first ) ;
	    List < ? extends VariableElement > parameters = element . getParameters ( ) ;
	    for ( VariableElement parameter : parameters )
		{
		    first = declaration ( parameter , indent , stringBuilder , first ) ;
		}
	    return first ;
	}

	private boolean declaration ( VariableElement parameter , int indent , StringBuilder stringBuilder , boolean first )
	{
	    UseParameter useParameter = parameter . getAnnotation ( UseParameter . class ) ;
	    Object type = parameter . asType ( ) ;
	    Object simpleName = parameter . getSimpleName ( ) ;
	    first = declaration ( useParameter , type , simpleName , indent , stringBuilder , first ) ;
	    return first ;
	}

	private boolean declaration ( UseParameter useParameter , Object type , Object simpleName , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useParameter )
		{
		    printGeneratedAnnotation ( stringBuilder , indent ) ;
		    append ( stringBuilder , ! first , NEWLINE ) ;
		    append ( stringBuilder , true , indent , type ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean parameters ( TypeElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , OPEN_PAREN ) ;
	    Element enclosingElement = element . getEnclosingElement ( ) ;
	    ElementKind kind = enclosingElement . getKind ( ) ;
	    switch ( kind )
		{
		case CLASS :
		    Object type = enclosingElement . asType ( ) ;
		    Object simpleName = enclosingElement . getSimpleName ( ) ;
		    first = parameter ( type , simpleName , indent , stringBuilder , first ) ;
		    break ;
		case PACKAGE :
		    break ;
		default :
		    assert false : kind ;
		}
	    List < ? extends Element > enclosedElements = element . getEnclosedElements ( ) ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    first = parameter ( enclosedElement , indent , stringBuilder , first ) ;
		}
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , CLOSE_PAREN ) ;
	    return first ;
	}

	private boolean parameter ( Element enclosedElement , int indent , StringBuilder stringBuilder , boolean first )
	{
	    ElementKind kind = enclosedElement . getKind ( ) ;
	    switch ( kind )
		{
		case METHOD :
		    first = parameter ( ( ExecutableElement ) ( enclosedElement ) , indent , stringBuilder , first ) ;
		    break ;
		case CONSTRUCTOR :
		case CLASS :
		    break ;
		default :
		    assert false : kind ;
		break ;
		}
	    return first ;
	}

	private boolean parameter ( ExecutableElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    UseParameter useParameter = element . getAnnotation ( UseParameter . class ) ;
	    Object type = element . getReturnType ( ) ;
	    Object simpleName = element . getSimpleName ( ) ;
	    first = parameter ( useParameter , type , simpleName , indent , stringBuilder , first ) ;
	    List < ? extends VariableElement > parameters = element . getParameters ( ) ;
	    for ( VariableElement parameter : parameters )
		{
		    first = parameter ( parameter , indent , stringBuilder , first ) ;
		}
	    return first ;
	}

	private boolean parameter ( VariableElement parameter , int indent , StringBuilder stringBuilder , boolean first )
	{
	    UseParameter useParameter = parameter . getAnnotation ( UseParameter . class ) ;
	    Object type = parameter . asType ( ) ;
	    Object simpleName = parameter . getSimpleName ( ) ;
	    first = parameter ( useParameter , type , simpleName , indent , stringBuilder , first ) ;
	    return first ;
	}

	private boolean parameter ( UseParameter useParameter , Object type , Object simpleName , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useParameter )
		{
		    first = parameter ( type , simpleName , indent , stringBuilder , first ) ;
		}
	    return first ;
	}

	private boolean parameter ( Object type , Object simpleName , int indent , StringBuilder stringBuilder , boolean first )
	{
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , ! first , COMMA ) ;
	    append ( stringBuilder , ! first , SPACE ) ;
	    printGeneratedAnnotation ( stringBuilder ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , type ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , simpleName ) ;
	    return false ;
	}

	private boolean assignments ( TypeElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    List < ? extends Element > enclosedElements = element . getEnclosedElements ( ) ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    first = assignment ( enclosedElement , indent , stringBuilder , first ) ;
		}
	    return first ;
	}

	private boolean assignment ( Element enclosedElement , int indent , StringBuilder stringBuilder , boolean first )
	{
	    ElementKind kind = enclosedElement . getKind ( ) ;
	    switch ( kind )
		{
		case METHOD :
		    assignment ( ( ExecutableElement ) ( enclosedElement ) , indent , stringBuilder , first ) ;
		    first = false ;
		    break ;
		case CONSTRUCTOR :
		case CLASS :
		    break ;
		default :
		    assert false : kind ;
		break ;
		}
	    return first ;
	}

	private boolean assignment ( ExecutableElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    UseParameter useParameter = element . getAnnotation ( UseParameter . class ) ;
	    Object simpleName = element . getSimpleName ( ) ;
	    first = assignment ( useParameter , simpleName , indent , stringBuilder , first ) ;
	    List < ? extends VariableElement > parameters = element . getParameters ( ) ;
	    for ( VariableElement parameter : parameters )
		{
		    first = assignment ( parameter , indent , stringBuilder , first ) ;
		}
	    return first ;
	}

	private boolean assignment ( VariableElement parameter , int indent , StringBuilder stringBuilder , boolean first )
	{
	    UseParameter useParameter = parameter . getAnnotation ( UseParameter . class ) ;
	    Object simpleName = parameter . getSimpleName ( ) ;
	    first = assignment ( useParameter , simpleName , indent , stringBuilder , first ) ;
	    return first ;
	}

	private boolean assignment ( UseParameter useParameter , Object simpleName , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useParameter )
		{
		    append ( stringBuilder , true , indent + 1 , THIS ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , EQUALS ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean implementations ( TypeElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    List < ? extends Element > enclosedElements = element . getEnclosedElements ( ) ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    first = implementation ( enclosedElement , indent , stringBuilder , first ) ;
		}
	    return first ;
	}

	private boolean implementation ( Element enclosedElement , int indent , StringBuilder stringBuilder , boolean first )
	{
	    ElementKind kind = enclosedElement . getKind ( ) ;
	    switch ( kind )
		{
		case METHOD :
		    first = implementation ( ( ExecutableElement ) ( enclosedElement ) , indent , stringBuilder , first ) ;
		    break ;
		case CONSTRUCTOR :
		case CLASS :
		    break ;
		default :
		    assert false : kind ;
		break ;
		}
	    return first ;
	}

	private boolean implementation ( ExecutableElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    UseParameter useParameter = element . getAnnotation ( UseParameter . class ) ;
	    first = instrument ( element , useParameter , indent , stringBuilder , first ) ;
	    UseConstructor useConstructor = element . getAnnotation ( UseConstructor . class ) ;
	    first = instrument ( element , useConstructor , indent , stringBuilder , first ) ;
	    UseStaticMethod useStaticMethod = element . getAnnotation ( UseStaticMethod . class ) ;
	    first = instrument ( element , useStaticMethod , indent , stringBuilder , first ) ;
	    UseStringConstant useStringConstant = element . getAnnotation ( UseStringConstant . class ) ;
	    first = instrument ( element , useStringConstant , indent , stringBuilder , first ) ;
	    UseUnsupportedOperationException useUnsupportedOperationException = element . getAnnotation ( UseUnsupportedOperationException . class ) ;
	    first = instrument ( element , useUnsupportedOperationException , indent , stringBuilder , first ) ;
	    first = instrument ( element , indent , stringBuilder , first ) ;
	    return first ;
	}

	private boolean instrument ( ExecutableElement element , UseParameter useParameter , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useParameter )
		{
		    openInstrument ( element , false , indent , stringBuilder ) ;
		    append ( stringBuilder , true , indent + 1 , RETURN ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    Object simpleName = element . getSimpleName ( ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    closeInstrument ( indent , stringBuilder ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean instrument ( ExecutableElement element , UseConstructor useConstructor , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useConstructor )
		{
		    openInstrument ( element , false , indent , stringBuilder ) ;
		    TypeMirror type = null ;
		    try
			{
			    useConstructor . value ( ) ;
			}
		    catch ( MirroredTypeException cause )
			{
			    type = cause . getTypeMirror ( ) ;
			}
		    catch ( Exception cause )
			{
			    Object simpleName = element . getSimpleName ( ) ;
			    String message = simpleName . toString ( ) ;
			    RuntimeException error = new RuntimeException ( message , cause ) ;
			    throw error ;
			}
		    DeclaredType declaredType = ( DeclaredType ) ( type ) ;
		    Element e1 = declaredType . asElement ( ) ;
		    boolean isAbstract = isAbstract ( e1 ) ;
		    TypeElement e2 = ( TypeElement ) ( e1 ) ;
		    append ( stringBuilder , true , indent + 1 , RETURN ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , NEW ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    if ( isAbstract )
			{
			    Object sn = e2 . getSimpleName ( ) ;
			    append ( stringBuilder , true , sn ) ;
			    append ( stringBuilder , true , UNDERSCORE ) ;
			}
		    else
			{
			    Object qn = e2 . getQualifiedName ( ) ;
			    append ( stringBuilder , true , qn ) ;
			}
		    append ( stringBuilder , true , SPACE ) ;
		    typeParameters ( element , stringBuilder ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    parameters ( element , false , false , stringBuilder ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    closeInstrument ( indent , stringBuilder ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean instrument ( ExecutableElement element , UseStaticMethod useStaticMethod , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useStaticMethod )
		{
		    openInstrument ( element , false , indent , stringBuilder ) ;
		    TypeMirror returnType = element . getReturnType ( ) ;
		    TypeKind kind = returnType . getKind ( ) ;
		    boolean isVoid = TypeKind . VOID . equals ( kind ) ;
		    append ( stringBuilder , isVoid , indent + 1 , BLANK ) ;
		    append ( stringBuilder , ! isVoid , indent + 1 , RETURN ) ;
		    append ( stringBuilder , ! isVoid , SPACE ) ;
		    Object simpleName = element . getSimpleName ( ) ;
		    try
			{
			    useStaticMethod . value ( ) ;
			}
		    catch ( MirroredTypeException cause )
			{
			    Object type = cause . getTypeMirror ( ) ;
			    append ( stringBuilder , true , type ) ;
			}
		    catch ( Exception cause )
			{
			    String message = simpleName . toString ( ) ;
			    RuntimeException error = new RuntimeException ( message , cause ) ;
			    throw error ;
			}
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    parameters ( element , false , false , stringBuilder ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    closeInstrument ( indent , stringBuilder ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean instrument ( ExecutableElement element , UseStringConstant useStringConstant , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useStringConstant )
		{
		    openInstrument ( element , false , indent , stringBuilder ) ;
		    append ( stringBuilder , true , indent + 1 , RETURN ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    Elements elementUtils = processingEnv . getElementUtils ( ) ;
		    String stringConstant = useStringConstant . value ( ) ;
		    String constantExpression = elementUtils . getConstantExpression ( stringConstant ) ;
		    append ( stringBuilder , true , constantExpression ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    closeInstrument ( indent , stringBuilder ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean instrument ( ExecutableElement element , UseUnsupportedOperationException useUnsupportedOperationException , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useUnsupportedOperationException )
		{
		    openInstrument ( element , false , indent , stringBuilder ) ;
		    append ( stringBuilder , true , indent + 1 , THROW ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , NEW ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , JAVA ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , LANG ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , UNSUPPORTED_OPERATION_EXCEPTION ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , OPEN_PAREN ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , CLOSE_PAREN ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    closeInstrument ( indent , stringBuilder ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean instrument ( ExecutableElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    boolean isInstrumented = isInstrumented ( element ) ;
	    if ( isInstrumented )
		{
		    printTestAnnotation ( stringBuilder , indent ) ;
		    printSuppressWarningsAnnotation ( stringBuilder , indent , UNCHECKED ) ;
		    openInstrument ( element , true , indent , stringBuilder ) ;
		    List < ? extends VariableElement > parameters = element . getParameters ( ) ;
		    for ( VariableElement parameter : parameters )
			{
			    instrument ( parameter , indent , stringBuilder , true ) ;
			}
		    TypeMirror returnType = element . getReturnType ( ) ;
		    TypeKind kind = returnType . getKind ( ) ;
		    boolean isVoid = TypeKind . VOID . equals ( kind ) ;
		    append ( stringBuilder , isVoid , indent + 1 , BLANK ) ;
		    append ( stringBuilder , ! isVoid , indent + 1 , RETURN ) ;
		    append ( stringBuilder , ! isVoid , SPACE ) ;
		    Object simpleName = element . getSimpleName ( ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    parameters ( element , false , false , stringBuilder ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    closeInstrument ( indent , stringBuilder ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean instrument ( VariableElement element , int indent , StringBuilder stringBuilder , boolean first )
	{
	    boolean isInstrumented = isInstrumented ( element ) ;
	    if ( isInstrumented )
		{
		    TypeMirror type = element . asType ( ) ;
		    append ( stringBuilder , true , indent + 1 , type ) ;
		    TypeKind kind = type . getKind ( ) ;
		    append ( stringBuilder , true , "/**" + kind + "**/" ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    Object simpleName = element . getSimpleName ( ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , EQUALS ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    UseParameter useParameter = element . getAnnotation ( UseParameter . class ) ;
		    first = instrument ( element , useParameter , indent , stringBuilder , first ) ;
		    UseStringConstant useStringConstant = element . getAnnotation ( UseStringConstant . class ) ;
		    first = instrument ( element , useStringConstant , indent , stringBuilder , first ) ;
		    UseMock useMock = element . getAnnotation ( UseMock . class ) ;
		    first = instrument ( element , useMock , indent , stringBuilder , first ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		}
	    return first ;
	}

	private boolean instrument ( VariableElement element , UseParameter useParameter , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useParameter )
		{
		    append ( stringBuilder , true , THIS ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    Object simpleName = element. getSimpleName ( ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean instrument ( VariableElement element , UseStringConstant useStringConstant , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useStringConstant )
		{
		    Elements elementUtils = processingEnv . getElementUtils ( ) ;
		    String stringConstant = useStringConstant . value ( ) ;
		    String constantExpression = elementUtils . getConstantExpression ( stringConstant ) ;
		    append ( stringBuilder , true , constantExpression ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean instrument ( VariableElement element , UseMock useMock , int indent , StringBuilder stringBuilder , boolean first )
	{
	    if ( null != useMock )
		{
		    append ( stringBuilder , true , ORG ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , MOCKITO_PACKAGE ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , MOCKITO_CLASS ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , MOCK ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , OPEN_PAREN ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    TypeMirror type = element . asType ( ) ;
		    DeclaredType declaredType = ( DeclaredType ) ( type ) ;
		    Element e1 = declaredType . asElement ( ) ;
		    TypeElement e2 = ( TypeElement ) ( e1 ) ;
		    Object qualifiedName = e2 . getQualifiedName ( ) ;
		    append ( stringBuilder , true , qualifiedName ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , PERIOD ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , CLASS ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , CLOSE_PAREN ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , SEMICOLON ) ;
		    first = false ;
		}
	    return first ;
	}

	private boolean isInstrumented ( ExecutableElement element )
	{
	    boolean isAnnotated = false ;
	    List < ? extends VariableElement > parameters = element . getParameters ( ) ;
	    for ( VariableElement parameter : parameters )
		{
		    boolean is = isInstrumented ( parameter ) ;
		    isAnnotated = isAnnotated || is ;
		}
	    return isAnnotated ;
	}

	private void openInstrument ( ExecutableElement element , boolean instrumentParameters , int indent , StringBuilder stringBuilder )
	{
	    append ( stringBuilder , true , NEWLINE ) ;
	    printGeneratedAnnotation ( stringBuilder , indent ) ;
	    if ( ! instrumentParameters )
		{
		    printOverrideAnnotation ( stringBuilder , indent ) ;
		}
	    append ( stringBuilder , true , indent , PUBLIC ) ;
	    typeParameters ( element , stringBuilder ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    Object returnType = element . getReturnType ( ) ;
	    append ( stringBuilder , true , returnType ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    Object simpleName = element . getSimpleName ( ) ;
	    append ( stringBuilder , true , simpleName ) ;
	    parameters ( element , instrumentParameters , true , stringBuilder ) ;
	    append ( stringBuilder , true , indent , OPEN_CURLY ) ;
	}

	private void closeInstrument ( int indent , StringBuilder stringBuilder )
	{
	    append ( stringBuilder , true , indent , CLOSE_CURLY ) ;
	}

	private void typeParameters ( TypeElement element , StringBuilder stringBuilder )
	{
	    List < ? extends TypeParameterElement > typeParameters = element . getTypeParameters ( ) ;
	    typeParameters ( typeParameters , stringBuilder ) ;
	}

	private void typeParameters ( ExecutableElement element , StringBuilder stringBuilder )
	{
	    List < ? extends TypeParameterElement > typeParameters = element . getTypeParameters ( ) ;
	    typeParameters ( typeParameters , stringBuilder ) ;
	}

	private void typeParameters ( List < ? extends TypeParameterElement > typeParameters , StringBuilder stringBuilder )
	{
	    boolean first = true ;
	    for ( TypeParameterElement typeParameter : typeParameters )
		{
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , first , OPEN_ANGLE ) ;
		    append ( stringBuilder , ! first , COMMA ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    Object simpleName = typeParameter . getSimpleName ( ) ;
		    append ( stringBuilder , true , simpleName ) ;
		    first = false ;
		}
	    append ( stringBuilder , ! first , SPACE ) ;
	    append ( stringBuilder , ! first , CLOSE_ANGLE ) ;
	}

	private void parameters ( ExecutableElement element , boolean instrumentParameters , boolean formal , StringBuilder stringBuilder )
	{
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , OPEN_PAREN ) ;
	    List < ? extends VariableElement > parameters = element . getParameters ( ) ;
	    boolean first = true ;
	    for ( VariableElement parameter : parameters )
		{
		    boolean isInstrumented = isInstrumented ( parameter ) ;
		    if ( ! instrumentParameters || ! isInstrumented )
			{
			    append ( stringBuilder , true , SPACE ) ;
			    append ( stringBuilder , ! first , COMMA ) ;
			    append ( stringBuilder , ! first , SPACE ) ;
			    Object type = parameter . asType ( ) ;
			    append ( stringBuilder , formal , type ) ;
			    append ( stringBuilder , formal , SPACE ) ;
			    Object simpleName = parameter . getSimpleName ( ) ;
			    append ( stringBuilder , true , simpleName ) ;
			    first = false ;
			}
		}
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , CLOSE_PAREN ) ;
	}

	private boolean is ( Element element , Modifier modifier )
	{
	    Set < Modifier > modifiers = element . getModifiers ( ) ;
	    boolean isAbstract = modifiers . contains ( modifier ) ;
	    return isAbstract ;
	}

	private boolean isAbstract ( Element element )
	{
	    boolean isAbstract = is ( element , Modifier . ABSTRACT ) ;
	    return isAbstract ;
	}

	private boolean isFinal ( Element element )
	{
	    boolean isFinal = is ( element , Modifier . FINAL ) ;
	    return isFinal ;
	}

	private boolean isInstrumented ( VariableElement element )
	{
	    boolean isAnnotated = false ;
	    UseParameter useParameter = element . getAnnotation ( UseParameter . class ) ;
	    isAnnotated = isAnnotated || ( null != useParameter ) ;
	    UseStringConstant useStringConstant = element . getAnnotation ( UseStringConstant . class ) ;
	    isAnnotated = isAnnotated || ( null != useStringConstant ) ;
	    UseMock useMock = element . getAnnotation ( UseMock . class ) ;
	    isAnnotated = isAnnotated || ( null != useMock ) ;
	    return isAnnotated ;
	}

	private void printGeneratedAnnotation ( StringBuilder stringBuilder , int indent )
	{
	    append ( stringBuilder , true , indent , BLANK ) ;
	    printGeneratedAnnotation ( stringBuilder ) ;
	}

	private void printGeneratedAnnotation ( StringBuilder stringBuilder )
	{
	    append ( stringBuilder , true , AT ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , JAVAX ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , ANNOTATION ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , GENERATED ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , OPEN_PAREN ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , OPEN_DOUBLE_QUOTE ) ;
	    Class < ? > thisClass = getClass ( ) ;
	    Object thisName = thisClass . getCanonicalName ( ) ;
	    append ( stringBuilder , true ,  thisName ) ;
	    append ( stringBuilder , true , CLOSE_DOUBLE_QUOTE ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , CLOSE_PAREN ) ;
	}

	private void printOverrideAnnotation ( StringBuilder stringBuilder , int indent )
	{
	    append ( stringBuilder , true , indent , AT ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , JAVA ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , LANG ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , OVERRIDE ) ;
	}

	private void printTestAnnotation ( StringBuilder stringBuilder , int indent )
	{
	    append ( stringBuilder , true , indent , AT ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , ORG ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    append ( stringBuilder , true , JUNIT ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , TEST ) ;
	}

	private void printSuppressWarningsAnnotation ( StringBuilder stringBuilder , int indent , String ... warnings )
	{
	    append ( stringBuilder , true , indent , AT ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , JAVA ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    append ( stringBuilder , true , LANG ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , PERIOD ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , SUPPRESS_WARNINGS ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , OPEN_PAREN ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , OPEN_CURLY ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    for ( String warning : warnings )
		{
		    Elements elementUtils = processingEnv . getElementUtils ( ) ;
		    String constantExpression = elementUtils . getConstantExpression ( warning ) ;
		    append ( stringBuilder , true , constantExpression ) ;
		    append ( stringBuilder , true , SPACE ) ;
		    append ( stringBuilder , true , COMMA ) ;
		}
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , CLOSE_CURLY ) ;
	    append ( stringBuilder , true , SPACE ) ;
	    append ( stringBuilder , true , CLOSE_PAREN ) ;
	}

	private void append ( StringBuilder stringBuilder , boolean predicate , int indent , Object value )
	{
	    append ( stringBuilder , predicate , NEWLINE ) ;
	    for ( int i = 0 ; i < indent ; i ++ )
		{
		    append ( stringBuilder , predicate , TAB ) ;
		}
	    append ( stringBuilder , predicate , value ) ;
	}

	private void append ( StringBuilder stringBuilder , boolean predicate , Object value )
	{
	    if ( predicate )
		{
		    stringBuilder . append ( value ) ;
		}
	}
    }
