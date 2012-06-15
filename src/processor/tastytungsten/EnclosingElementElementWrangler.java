
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class EnclosingElementElementWrangler < P > extends SimpleElementVisitor6 < Element , P >
{
    @ Override
	protected Element defaultAction ( Element element , P data )
    {
	Element enclosingElement = element . getEnclosingElement ( ) ;
	return enclosingElement ;
    }
}