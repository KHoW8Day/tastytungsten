
package tastytungsten ;

import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class ReturnTypeElementWrangler < P > extends SimpleElementVisitor6 < TypeMirror , P >
{
    @ Override
	public TypeMirror visitExecutable ( ExecutableElement element , P data )
	{
	    TypeMirror returnType = element . getReturnType ( ) ;
	    return returnType ;
	}
}