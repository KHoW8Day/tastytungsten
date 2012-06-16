
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . SimpleTypeVisitor6 ;

abstract class ElementTypeGaffer < R , P , A , B > extends SimpleTypeVisitor6 < R , P >
{
    @ Override
	protected R defaultAction ( TypeMirror type , P data )
	{
	    ElementVisitor < ? extends R , ? super B > visitor = getVisitor ( ) ;
	    TypeVisitor < ? extends Element , ? super A > wrangler = getWrangler ( ) ;
	    A a = getA ( data ) ;
	    Element element = wrangler . visit ( type , a ) ;
	    B b = getB ( data ) ;
	    R visit = visitor . visit ( element , b ) ;
	    return visit ;
	}

    @ UseParameter
	abstract TypeVisitor < ? extends Element , ? super A > getWrangler ( ) ;

    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super B > getVisitor ( ) ;

    @ UseNull
	abstract A getA ( P data ) ;

    @ UseNull
	abstract B getB ( P data ) ;
}
