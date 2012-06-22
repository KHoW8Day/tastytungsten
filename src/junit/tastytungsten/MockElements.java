
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . util . Elements ;

interface MockElements extends Elements
{
    @ Override
	Map < ExecutableElement , AnnotationValue > getElementValuesWithDefaults ( AnnotationMirror annotationMirror ) ;
}
