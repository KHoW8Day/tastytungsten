
package tastytungsten ;

import javax . lang . model . element . TypeElement ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class SuperclassElementWrangler < P > extends SimpleElementVisitor6 < TypeMirror , P >
{
    @ Override
    public TypeMirror visitType ( TypeElement element , P data )
    {
	TypeMirror superclass = element . getSuperclass ( ) ;
	return superclass ;
    }
}
