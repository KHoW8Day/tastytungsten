
package tastytungsten ;

import java . util . Map ;

abstract class MapItemTransformer < K , V > implements Transformer < V , Map < ? extends K , ? extends V > >
{
    V transform ( Map < ? extends K , ? extends V > map , @ UseParameter final K key )
    {
	V value = map . get ( key ) ;
	return value ;
    }
}
