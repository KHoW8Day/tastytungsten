
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class TypeElementGaffer < R , P , A , B > extends SimpleElementVisitor6 < R , P >
{
    @ Override
	protected R defaultAction ( Element element , P data )
	{
	    TypeVisitor < ? extends R , ? super B > visitor = getVisitor ( ) ;
	    ElementVisitor < ? extends TypeMirror , ? super A > wrangler = getWrangler ( ) ;
	    A a = getA ( data ) ;
	    TypeMirror type = wrangler . visit ( element , a ) ;
	    B b = getB ( data ) ;
	    R visit = visitor . visit ( type , b ) ;
	    return visit ;
	}

    @ UseParameter
	abstract ElementVisitor < ? extends TypeMirror , ? super A > getWrangler ( ) ;

    @ UseParameter
	abstract TypeVisitor < ? extends R , ? super B > getVisitor ( ) ;

    @ UseNull
	abstract A getA ( P data ) ;

    @ UseNull
	abstract B getB ( P data ) ;
}
