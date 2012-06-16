
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . type . DeclaredType ;
import javax . lang . model . util . SimpleTypeVisitor6 ;

abstract class ElementTypeWrangler < P > extends SimpleTypeVisitor6 < Element , P >
{
    @ Override
	public Element visitDeclared ( DeclaredType type , P data )
    {
	Element element = type . asElement ( ) ;
	return element ;
    }
}
