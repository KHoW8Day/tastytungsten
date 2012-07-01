
package tastytungsten ;

import java . util . Map ;

abstract class MapItemStager < K , V > implements Stager < V , Map < ? extends K , ? extends V > >
{
    @ Override
	public V stage ( Map < ? extends K , ? extends V > map )
	{
	    K key = getKey ( ) ;
	    V value = map . get ( key ) ;
	    return value ;
	}

    @ UseParameter
	abstract K getKey ( ) ;
}
