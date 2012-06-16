
package tastytungsten ;

import javax . lang . model . type . TypeMirror ;
import javax . lang . model . util . SimpleAnnotationValueVisitor6 ;

abstract class TypeMirrorAnnotationValueCaster < P > extends SimpleAnnotationValueVisitor6 < TypeMirror , P >
{
    public TypeMirror visitType ( TypeMirror value , P data )
    {
	return value ;
    }
}