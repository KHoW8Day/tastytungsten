
package tastytungsten ;

import java . util . Map ;

abstract class MapEntryKeyTransformer < K > implements Transformer < K , Map . Entry < ? extends K , ? > >
{
    @ Override
	public final K transform ( Map . Entry < ? extends K , ? > entry )
	{
	    K key = entry . getKey ( ) ;
	    return key ;
	}
}
