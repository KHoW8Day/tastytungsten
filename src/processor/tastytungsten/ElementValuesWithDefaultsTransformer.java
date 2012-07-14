
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . util . Elements ;

abstract class ElementValuesWithDefaultsTransformer implements Transformer < Map < ? extends ExecutableElement , ? extends AnnotationValue > , AnnotationMirror >
{
    final Map < ? extends ExecutableElement , ? extends AnnotationValue > transform ( AnnotationMirror annotationMirror , @ UseParameter Elements elementUtils )
	{
	    Map < ? extends ExecutableElement , ? extends AnnotationValue > elementValuesWithDefaults = elementUtils . getElementValuesWithDefaults ( annotationMirror ) ;
	    return elementValuesWithDefaults ;
	}
}
