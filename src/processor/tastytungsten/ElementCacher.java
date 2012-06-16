
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class ElementCacher < R , P > extends SimpleElementVisitor6 < R , P >
{
    @ Override
	protected R defaultAction ( Element element , P data )
	{
	    Map < Element , R > map = getMap ( ) ;
	    boolean containsKey = map . containsKey ( element ) ;
	    R visit = null ;
	    if ( containsKey )
		{
		    visit = map . get ( element ) ;
		}
	    else
		{
		    ElementVisitor < ? extends R , ? super P > visitor = getVisitor ( ) ;
		    visit = visitor . visit ( element , data ) ;
		    map . put ( element , visit ) ;
		}
	    return visit ;
	}

    @ UseParameter
	abstract Map < Element , R > getMap ( ) ;

    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super P > getVisitor ( ) ;
}