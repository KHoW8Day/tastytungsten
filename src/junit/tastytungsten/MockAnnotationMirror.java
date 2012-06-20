
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . ExecutableElement ;

abstract class MockAnnotationMirror implements AnnotationMirror
{
    @ UseNull
	abstract DeclaredType getAnnotationType ( ) ;

    @ UseParameter
	abstract Map < ? extends ExecutableElement , ? extends AnnotationValue > getElementValues ( ) ;
}
