
package tastytungsten ;

import java . util . List ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class InterfacesElementWrangler < P > extends SimpleElementVisitor6 < List < ? extends TypeMirror > , P >
{
    @ Override
	public List < ? extends TypeMirror > visitType ( TypeElement element , P data )
	{
	    List < ? extends TypeMirror > interfaces = element . getInterfaces ( ) ;
	    return interfaces ;
	}
}
