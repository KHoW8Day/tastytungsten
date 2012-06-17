
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class TypeElementWrangler < P > extends SimpleElementVisitor6 < TypeMirror , P >
{
    /**
     * Gets the type of an element.
     *
     * @return the element type
     * @
     **/
    @ Override
	protected TypeMirror defaultAction ( Element element , P data )
	{
	    TypeMirror type = element . asType ( ) ;
	    return type ;
	}
}
