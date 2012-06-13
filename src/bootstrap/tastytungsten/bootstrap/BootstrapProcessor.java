
package tastytungsten . bootstrap ;

import java . io . Writer ;
import java . util . Set ;
import java . util . List ;
import javax . annotation . processing . AbstractProcessor ;
import javax . annotation . processing . Filer ;
import javax . annotation . processing . Messager ;
import javax . annotation . processing . RoundEnvironment ;
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
import tastytungsten . annotations . UseConstructor ;
import tastytungsten . annotations . UseNull ;
import tastytungsten . annotations . UseParameter ;
import tastytungsten . annotations . UseStaticMethod ;
import tastytungsten . annotations . UseStringConstant ;

import java . io . IOException ;
import javax . lang . model . type . MirroredTypeException ;

@ SupportedAnnotationTypes ( "*" )
    public final class BootstrapProcessor extends AbstractProcessor
    {
	private boolean flag = true ;;

	public BootstrapProcessor ( )
	{
	}

	public boolean process ( Set < ? extends TypeElement > annotations , RoundEnvironment roundEnvironment )
	{
	    if ( flag )
		{
		    process ( roundEnvironment ) ;
		}
	    return true ;
	}

	private void process ( RoundEnvironment roundEnvironment )
	{
	    Set < ? extends Element > rootElements = roundEnvironment . getRootElements ( ) ;
	    StringBuilder stringBuilder = new StringBuilder ( ) ;
	    stringBuilder . append ( "\npackage tastytungsten . processor ;" ) ;
	    stringBuilder . append ( "\nclass Bootstrap" ) ;
	    stringBuilder . append ( "\n{" ) ;
	    process ( rootElements , stringBuilder ) ;
	    stringBuilder . append ( "\n}" ) ;
	    write ( stringBuilder ) ;
	    flag = false ;
	}

	private void process ( Set < ? extends Element > rootElements , StringBuilder stringBuilder )
	{
	    for ( Element rootElement : rootElements )
		{
		    process ( rootElement , stringBuilder ) ;
		}
	}

	private void process ( Element rootElement , StringBuilder stringBuilder )
	{
	    ElementKind kind = rootElement . getKind ( ) ;
	    switch ( kind )
		{
		case CLASS :
		    type ( rootElement , stringBuilder ) ;
		    break ;
		case PACKAGE :
		    break ;
		default :
		    assert false ;
		}
	}

	private void type ( Element rootElement , StringBuilder stringBuilder )
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
	    List < ? extends Element > enclosedElements = rootElement . getEnclosedElements ( ) ;
	    classParameters ( enclosedElements , 1 , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t\tBootstrap" ) ;
	    stringBuilder . append ( simpleName ) ;
	    stringBuilder . append ( "(" ) ;
	    classParameters ( enclosedElements , 2 , stringBuilder ) ;
	    stringBuilder . append ( ")" ) ;
	    stringBuilder . append ( "\n\t\t\t{" ) ;
	    classParameters ( enclosedElements , 3 , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t\t}" ) ;
	    methods ( enclosedElements , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t}" ) ;
	}

	private void typeParameters ( Element rootElement , ElementKind kind , StringBuilder stringBuilder )
	{
	    switch ( kind )
		{
		case CLASS :
		    typeParameters ( ( TypeElement ) ( rootElement ) , stringBuilder ) ;
		    break ;
		case METHOD :
		    typeParameters ( ( ExecutableElement ) ( rootElement ) , stringBuilder ) ;
		    break ;
		default :
		    assert false ;
		}
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
		    stringBuilder . append ( first ? "<" : "," ) ;
		    first = false ;
		    Object simpleName = typeParameter . getSimpleName ( ) ;
		    stringBuilder . append ( simpleName ) ;
		}
	    stringBuilder . append ( first ? "" : ">" ) ;
	}

	private void classParameters ( List < ? extends Element > enclosedElements , int level , StringBuilder stringBuilder )
	{
	    boolean first = true ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    UseParameter useParameter = enclosedElement . getAnnotation ( UseParameter . class ) ;
		    if ( null != useParameter )
			{
			    ExecutableElement executableElement = ( ExecutableElement ) ( enclosedElement ) ;
			    Object returnType = executableElement . getReturnType ( ) ;
			    Object simpleName = enclosedElement . getSimpleName ( ) ;
			    switch ( level )
				{
				case 1 :
				    stringBuilder . append ( "\n\t\t\t" ) ;
				    stringBuilder . append ( returnType ) ;
				    stringBuilder . append ( " " ) ;
				    stringBuilder . append ( simpleName ) ;
				    stringBuilder . append ( ";" ) ;
				    break ;
				case 2 :
				    stringBuilder . append ( first ? "" : "," ) ;
				    stringBuilder . append ( returnType ) ;
				    stringBuilder . append ( " " ) ;
				    stringBuilder . append ( simpleName ) ;
				    first = false ;
				    break ;
				default :
				    assert 3 == level ;
				    stringBuilder . append ( "\n\t\t\t\tthis .  " ) ;
				    stringBuilder . append ( simpleName ) ;
				    stringBuilder . append ( "=" ) ;
				    stringBuilder . append ( simpleName ) ;
				    stringBuilder.  append ( ";" ) ;
				    break ;
				}
			}
		}
	}

	private void methods ( List < ? extends Element > enclosedElements , StringBuilder stringBuilder )
	{
	    for ( Element enclosedElement : enclosedElements )
		{
		    method ( enclosedElement , stringBuilder ) ;
		}
	}

	private void method ( Element enclosedElement , StringBuilder stringBuilder )
	{
	    Set < Modifier > modifiers = enclosedElement . getModifiers ( ) ;
	    boolean isAbstract = modifiers . contains ( Modifier . ABSTRACT ) ;
	    method ( enclosedElement , isAbstract , stringBuilder ) ;
	}

	private void method ( Element enclosedElement , boolean isAbstract , StringBuilder stringBuilder )
	{
	    if ( isAbstract )
		{
		    method ( ( ExecutableElement ) ( enclosedElement ) , stringBuilder ) ;
		}
	}

	private void method ( ExecutableElement enclosedElement , StringBuilder stringBuilder )
	{
	    stringBuilder . append ( "\n\n\t\t\tpublic " ) ;
	    typeParameters ( enclosedElement , stringBuilder ) ;
	    Object returnType = enclosedElement . getReturnType ( ) ;
	    stringBuilder . append ( returnType ) ;
	    stringBuilder . append ( " " ) ;
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    stringBuilder . append ( simpleName ) ;
	    List < ? extends Element > parameters = enclosedElement . getParameters ( ) ;
	    parameters ( parameters , true , stringBuilder ) ;
	    stringBuilder . append ( "\n\t\t\t{" ) ;
	    stringBuilder . append ( "\n\t\t\t\treturn " ) ;
	    implementation ( enclosedElement , stringBuilder ) ;
	    stringBuilder . append ( ";" ) ;
	    stringBuilder . append ( "\n\t\t\t}" ) ;
	}

	private void implementation ( ExecutableElement enclosedElement , StringBuilder stringBuilder )
	{
	    UseConstructor useConstructor = enclosedElement . getAnnotation ( UseConstructor . class ) ;
	    UseNull useNull = enclosedElement . getAnnotation ( UseNull . class ) ;
	    UseParameter useParameter = enclosedElement . getAnnotation ( UseParameter . class ) ;
	    UseStaticMethod useStaticMethod = enclosedElement . getAnnotation ( UseStaticMethod . class ) ;
	    UseStringConstant useStringConstant = enclosedElement . getAnnotation ( UseStringConstant . class ) ;
	    implementation ( enclosedElement , useConstructor , useNull , useParameter , useStaticMethod , useStringConstant , stringBuilder ) ;
	}

	private void implementation ( ExecutableElement enclosedElement , UseConstructor useConstructor , UseNull useNull , UseParameter useParameter , UseStaticMethod useStaticMethod , UseStringConstant useStringConstant , StringBuilder stringBuilder )
	{
	    if ( null != useConstructor )
		{
		    implementation ( enclosedElement , useConstructor , stringBuilder ) ;
		}
	    else if ( null != useNull )
		{
		    implementation ( enclosedElement , useNull , stringBuilder ) ;
		}
	    else if ( null != useParameter )
		{
		    implementation ( enclosedElement , useParameter , stringBuilder ) ;
		}
	    else if ( null != useStaticMethod )
		{
		    implementation ( enclosedElement , useStaticMethod , stringBuilder ) ;
		}
	    else
		{
		    assert null != useStringConstant ;
		    implementation ( enclosedElement , useStringConstant , stringBuilder ) ;
		}
	}

	private void implementation ( ExecutableElement enclosedElement , UseConstructor useConstructor , StringBuilder stringBuilder )
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
	    String replace = string . replace ( "tastytungsten.processor." , "Bootstrap" ) ;
	    stringBuilder . append ( replace ) ;
	    typeParameters ( enclosedElement , stringBuilder ) ;
	    List < ? extends Element > parameters = enclosedElement . getParameters ( ) ;
	    parameters ( parameters , false , stringBuilder ) ;
	}

	private void implementation ( ExecutableElement enclosedElement , UseNull useNull , StringBuilder stringBuilder )
	{
	    stringBuilder . append ( "null" ) ;
	}

	private void implementation ( ExecutableElement enclosedElement , UseParameter useParameter , StringBuilder stringBuilder )
	{
	    Object simpleName = enclosedElement . getSimpleName ( ) ;
	    stringBuilder . append ( simpleName ) ;
	}

	private void implementation ( ExecutableElement enclosedElement , UseStaticMethod useStaticMethod , StringBuilder stringBuilder )
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
	    List < ? extends Element > parameters = enclosedElement . getParameters ( ) ;
	    parameters ( parameters , false , stringBuilder ) ;
	}

	private void implementation ( ExecutableElement enclosedElement , UseStringConstant useStringConstant , StringBuilder stringBuilder )
	{
	    if ( useStringConstant == null ) { throw new RuntimeException ( enclosedElement . toString ( ) ) ; }
	    Object value = useStringConstant . value ( ) ;
	    Elements elementUtils = processingEnv . getElementUtils ( ) ;
	    Object constantExpression = elementUtils . getConstantExpression ( value ) ;
	    stringBuilder . append ( constantExpression ) ;
	}

	private void parameters ( List < ? extends Element > parameters , boolean formal , StringBuilder stringBuilder )
	{
	    stringBuilder . append ( "(" ) ;
	    boolean first = true ;
	    for ( Element parameter : parameters )
		{
		    stringBuilder . append ( first ? "" : "," ) ;
		    Object type = parameter . asType ( ) ;
		    stringBuilder . append ( formal ? type : "" ) ;
		    stringBuilder . append ( " " ) ;
		    Object simpleName = parameter . getSimpleName ( ) ;
		    stringBuilder . append ( simpleName ) ;
		    first = false ;
		}
	    stringBuilder . append ( ")" ) ;
	}

	private void write ( Object source )
	{
	    try
		{
		    tryWrite ( source ) ;
		}
	    catch ( IOException cause )
		{
		    Messager messager = processingEnv . getMessager ( ) ;
		    String message = cause . toString ( ) ;
		    messager . printMessage ( Diagnostic . Kind . ERROR , message ) ;
		}
	}

	private void tryWrite ( Object source ) throws IOException
	{
	    Filer filer = processingEnv . getFiler ( ) ;
	    JavaFileObject file = filer . createSourceFile ( "tastytungsten.processor.Bootstrap" ) ;
	    Writer writer = file . openWriter ( ) ;
	    String string = source . toString ( ) ;
	    writer . write ( string ) ;
	    writer . close ( ) ;
	}
    }