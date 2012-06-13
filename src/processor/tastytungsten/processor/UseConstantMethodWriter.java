
package tastytungsten . processor ;

import javax . lang . model . element . Element ;
import javax . lang . model . util . Elements ;
import javax . lang . model . util . SimpleElementVisitor6 ;

import tastytungsten . annotations . UseParameter ;

abstract class UseConstantMethodWriter extends SimpleElementVisitor6 < Object , Object >
{
    @ Override
	protected final Object defaultAction ( Element element , Object data )
	{
	    return null ;
	}

    @ UseParameter
    abstract Elements getElementUtils ( ) ;
}
