
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . util . Elements ;
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
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P > annotationValueReverser = getAnnotationValueReverser ( ) ;
	AnnotationValue annotationValue = annotationValueReverser . visitString ( value , data ) ;
	assertNotNull ( annotationValue ) ;
	Object observed2 = qualifiedNameAnnotationValueWrangler . visit ( annotationValue , data ) ;
	assertEquals ( expected , observed2 ) ;
    }

    void
	elementValuesWithDefaultsAnnotationValueWrangler
	( )
    {
	Elements elementUtils = getElementUtils ( ) ;
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super Object > elementValuesWithDefaultsAnnotationValueWrangler = getElementValuesWithDefaultsAnnotationValueWrangler ( elementUtils ) ;
    }

    private
    void
	elementValuesWithDefaultsAnnotationValueWrangler
	(
	 AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super Object > elementValuesWithDefaultsAnnotationValueWrangler ,
	 AnnotationMirror value ,
	 Object target ,
	 Object expected ,
	 P data
	 )
    {
	Object observed1 = elementValuesWithDefaultsAnnotationValueWrangler . visit ( value , data ) ;
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P > annotationValueReverser = getAnnotationValueReverser ( ) ;
	AnnotationValue annotationValue = annotationValueReverser . visitAnnotation ( value , data ) ;
	assertNotNull ( annotationValue ) ;
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

    @ UseConstructor ( MockElements . class )
	abstract Elements getElementUtils ( ) ;

    @ UseConstructor ( AnnotationValueReverser . class )
	abstract < P > AnnotationValueVisitor < ? extends AnnotationValue , ? super P > getAnnotationValueReverser ( ) ;

    @ UseConstructor ( QualifiedNameAnnotationValueWrangler . class )
	abstract < P > AnnotationValueVisitor < ? extends Object , ? super P > getQualifiedNameAnnotationValueWrangler ( ) ;

    @ UseConstructor ( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract < P > AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P > getElementValuesWithDefaultsAnnotationValueWrangler ( Elements elements ) ;
}