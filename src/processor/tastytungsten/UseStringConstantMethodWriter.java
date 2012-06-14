
package tastytungsten . annotations ;

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . util . Elements ;

abstract class UseStringConstantMethodWriter implements Runnable
{
    @ Override public void run ( )
    {
	Elements elementUtils = getElementUtils ( ) ;
    }

    @ UseParameter
	abstract Elements getElementUtils ( ) ;

    abstract < P > AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValueVisitor > , ? super P > getElementValuesWithDefaultsWrangler ( Elements elementUtils ) ;

    abstract < P > AnnotationValueVisitor < ? extends String , ? super P > getStringCaster ( ) ;

    abstract < R , P , A , B > AnnotationValueVisitor < ? extends R , ? super P > getAnnotationValueAnnotationValueGaffer ( AnnotationValueVisitor < ? extends AnnotationVaue , super A > wrangler , AnnotationValueVisitor < ? extends R , ? super B > visitor ) ;
}
