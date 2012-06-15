
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class UniqueNameMapElementWrangler < P , A > extends SimpleElementVisitor6 < ElementVisitor < ? extends Object , ? super A > , P >
{
    @ Override
	protected ElementVisitor < ? extends Element , ? super A > defaultAction ( Element element , P data )
    {
	
    }

    @ UseConsructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;
}