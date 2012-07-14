
package tastytungsten ;

import java . util . List ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . Elements ;

abstract class AllAnnotationMirrorsTransformer implements Transformer < List < ? extends AnnotationMirror > , Element >
{
    final List < ? extends AnnotationMirror > transform ( Element element , @ UseParameter Elements elementUtils )
    {
	List < ? extends AnnotationMirror > allAnnotationMirrors = elementUtils . getAllAnnotationMirrors ( element ) ;
	return allAnnotationMirrors ;
    }
}