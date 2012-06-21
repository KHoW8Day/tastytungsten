
package tastytungsten ;

import java . io . Writer ;
import java . util . List ;
import java . util . Map ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . element . Name ;
import javax . lang . model . element . PackageElement ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . util . Elements ;

interface MockElements extends Elements
{
    Map < ExecutableElement , AnnotationValue > getElementValuesWithDefaults ( AnnotationMirror annotationMirror ) ;
}
