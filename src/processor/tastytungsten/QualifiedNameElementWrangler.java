
package tastytungsten ;

import javax . lang . model . element . Name ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class QualifiedNameElementWrangler < P > extends SimpleElementVisitor6 < Name , P >
{
    @ Override
	public Name visitType ( TypeElement element , P data )
    {
	Name qualifiedName = element . getQualifiedName ( ) ;
	return qualifiedName ;
    }
}