
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . type . DeclaredType ;

abstract class MockAnnotationMirror implements AnnotationMirror
{
    @ UseNull
	public abstract DeclaredType getAnnotationType ( ) ;

    @ UseParameter
	public abstract Map < ? extends ExecutableElement , ? extends AnnotationValue > getElementValues ( ) ;
}
