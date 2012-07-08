
package tastytungsten ;

import java . util . Map ;

abstract class MapEntryTransformer < K , V , P > implements Transformer < Map . Entry < K , V > , P >
{
    final Map . Entry < K , V > transform ( final P data , @ UseParameter final Transformer < ? extends K , ? super P > keyTransformer , @ UseParameter final Transformer < ? extends V , ? super P > valueTransformer )
	{
	    Map . Entry < K , V > transformerMapEntry = getTransformerMapEntry ( data , keyTransformer , valueTransformer ) ;
	    return transformerMapEntry ;
	}

    @ UseConstructor ( TransformerMapEntry . class )
	abstract < K , V , P > Map . Entry < K , V > getTransformerMapEntry ( P data , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
}
