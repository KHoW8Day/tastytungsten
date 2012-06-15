
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . element . Name ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class SimpleNameElementWrangler < P > extends SimpleElementVisitor6 < Name , P >
{
    @ Override
	protected Name defaultAction ( Element element , P data )
	{
	    Name simpleName = element . getSimpleName ( ) ;
	    return simpleName ;
	}
}
