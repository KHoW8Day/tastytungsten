
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class ElementElementGaffer < R , P , A , B > extends SimpleElementVisitor6 < R , P >
{
    @ Override
	protected R defaultAction ( Element element , P data )
	{
	    ElementVisitor < ? extends R , ? super B > visitor = getVisitor ( ) ;
	    ElementVisitor < ? extends Element , ? super A > wrangler = getWrangler ( ) ;
	    A a = getA ( data ) ;
	    Element e = wrangler . visit ( element , a ) ;
	    B b = getB ( data ) ;
	    R visit = visitor . visit ( e , b ) ;
	    return visit ;
	}

    @ UseParameter
	abstract ElementVisitor < ? extends Element , ? super A > getWrangler ( ) ;

    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super B > getVisitor ( ) ;

    @ UseNull
	abstract A getA ( P data ) ;

    @ UseNull
	abstract B getB ( P data ) ;
}
