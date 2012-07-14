
package tastytungsten ;

import java . util . Map ;

abstract class MapEntryValueTransformer < V > implements Transformer < V , Map . Entry < ? , ? extends V > >
{
    @ Override
	public final V transform ( Map . Entry < ? , ? extends V > entry )
	{
	    V value = entry . getValue ( ) ;
	    return value ;
	}
}
