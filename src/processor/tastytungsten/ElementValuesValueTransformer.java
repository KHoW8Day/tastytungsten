
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;

abstract class ElementValuesValueTransformer implements Transformer < AnnotationValue , Map . Entry < ? , ? extends AnnotationValue > >
{
    @ Override
	public final AnnotationValue transform ( Map . Entry < ? , ? extends AnnotationValue > entry )
	{
	    AnnotationValue annotationValue = entry . getValue ( ) ;
	    return annotationValue ;
	}
}
