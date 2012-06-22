
package tastytungsten ;

import java . util . HashMap ;
import java . util . Map ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . element . Name ;
import javax . lang . model . util . Elements ;
import org . mockito . stubbing . OngoingStubbing ;
import org . junit . Test ;
import static org . junit . Assert . * ;

abstract class Tests
{
    void easyTest ( boolean passOrFail )
    {
	assertTrue ( passOrFail ) ;
    }

    void qualifiedNameAnnotationValueWrangler ( )
    {
	AnnotationValueVisitor < ? extends Object , ? super Object > qualifiedNameAnnotationValueWrangler = getQualifiedNameAnnotationValueWrangler ( ) ;
	Object data = null ;
	Object alphaValue = getAlphaValue ( ) ;
	Object alphaExpected = getAlphaExpected ( ) ;
	qualifiedNameAnnotationValueWrangler ( qualifiedNameAnnotationValueWrangler , alphaValue , alphaExpected , data ) ;
	Object betaValue = getBetaValue ( ) ;
	Object betaExpected = getBetaExpected ( ) ;
	qualifiedNameAnnotationValueWrangler ( qualifiedNameAnnotationValueWrangler , betaValue , betaExpected , data ) ;
    }

    private
	< P >
	void
	qualifiedNameAnnotationValueWrangler
	(
	 AnnotationValueVisitor < ? , ? super P > qualifiedNameAnnotationValueWrangler ,
	 Object valueObject ,
	 Object expected ,
	 P data
	 )
    {
	String value = valueObject . toString ( ) ;
	Object observed1 = qualifiedNameAnnotationValueWrangler . visitString ( value , data ) ;
	assertEquals ( expected , observed1 ) ;
    }

    void
	elementValuesWithDefaultsAnnotationValueWrangler
	( )
    {
	MockElements elementUtils = mock ( MockElements . class ) ;
	Map < ExecutableElement , AnnotationValue > elementValues = getMap ( ) ;
	ExecutableElement key = mock ( ExecutableElement . class ) ;
	Name simpleName = mock ( Name . class ) ;
	String string = getElementValuesWithDefaultsAnnotationValueWranglerSimpleName ( ) ;
	when ( simpleName . toString ( ) ) . thenReturn ( string ) ;
	when ( key . getSimpleName ( ) ) . thenReturn ( simpleName ) ;
	AnnotationValue value1 = mock ( AnnotationValue . class ) ;
	elementValues . put ( key , value1 ) ;
	AnnotationMirror annotationMirror = mock ( AnnotationMirror . class ) ;
	when ( elementUtils . getElementValuesWithDefaults ( annotationMirror ) ) . thenReturn ( elementValues ) ;
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super Object > elementValuesWithDefaultsAnnotationValueWrangler = getElementValuesWithDefaultsAnnotationValueWrangler ( elementUtils ) ;
	Map < ? extends String , ? extends AnnotationValue > visit = elementValuesWithDefaultsAnnotationValueWrangler . visitAnnotation ( annotationMirror , null ) ;
	boolean hasKey = visit . containsKey ( string ) ;
	assertTrue ( hasKey ) ;
	AnnotationValue value2 = visit . get ( string ) ;
	assertEquals ( value1 , value2 ) ;
    }

    void
	annotationValuePunter
	( )
    {
	
    }

    private < R , P > void testAnnotationValueVisitor
	(
	 AnnotationValueVisitor < ? extends R , ? super P > annotationValueVisitor ,
	 AnnotationValue value ,
	 P data ,
	 R expected
	 )
    {
	R observed = annotationValueVisitor . visit ( value , data ) ;
	assertEquals ( expected , observed ) ;
    }

    @ UseStringConstant ( "org.junit.test" )
	abstract Object getAlphaValue ( ) ;

    private Object getAlphaExpected ( )
    {
	Object alphaExpected = getAlphaValue ( ) ;
	return alphaExpected ;
    }

    @ UseStringConstant ( "             org         .          junit .           test               " )
	abstract Object getBetaValue ( ) ;

    private Object getBetaExpected ( )
    {
	Object betaExpected = getAlphaValue ( ) ;
	return betaExpected ;
    }

    @ UseStringConstant ( "simple" )
	abstract String getElementValuesWithDefaultsAnnotationValueWranglerSimpleName ( ) ;

    @ UseStaticMethod ( org . mockito . Mockito . class )
	abstract < T > T mock ( Class < ? extends T > clazz ) ;

    @ UseStaticMethod ( org . mockito . Mockito . class )
	abstract < T > OngoingStubbing < T > when ( T methodCall ) ;

    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;

    @ UseConstructor ( QualifiedNameAnnotationValueWrangler . class )
	abstract < P > AnnotationValueVisitor < ? extends Object , ? super P > getQualifiedNameAnnotationValueWrangler ( ) ;

    @ UseConstructor ( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract < P > AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P > getElementValuesWithDefaultsAnnotationValueWrangler ( Elements elements ) ;

    @ UseConstructor ( AnnotationValueBunter . class )
	abstract < P > AnnotationValueVisitor < ? extends R , super P > annotationValueBunter ( AnnotationValueVisitor < ? extends Map < ? extends K , ? extends V > , ? super P > mapper , Bunter < ? extends R , ? su
}