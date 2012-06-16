
package tastytungsten ;

import javax . lang . model . util . Elements ;
import javax . lang . model . util . SimpleAnnotationValueVisitor6 ;

abstract class ConstantExpressionAnnotationValueWrangler < P > extends SimpleAnnotationValueVisitor6 < String , P >
{
    @ Override
	public String visitString ( String value , P data )
	{
	    Elements elementUtils = getElementUtils ( ) ;
	    String constantExpression = elementUtils . getConstantExpression ( value ) ;
	    return constantExpression ;
	}

    @ UseParameter
	abstract Elements getElementUtils ( ) ;
}