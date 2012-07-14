
package tastytungsten ;

import java . util . Collection ;
import java . util . Map ;

abstract class MapMapTransformer < A , B , C , D > implements Transformer < Map < ? extends A , ? extends B > , Map < ? extends C , ? extends D > >
{
    final Map < ? extends A , ? extends B > transform ( Map < ? extends C , ? extends D > map , @ UseParameter Transformer < ? extends A , ? super C > keyTransformer , @ UseParameter Transformer < ? extends B , ? super D > valueTransformer )
	{
	    Transformer < ? extends C , ? super Map . Entry < ? extends C , ? > > mapEntryKeyTransformer = getMapEntryKeyTransformer ( ) ;
	    Transformer < ? extends A , ? super Map . Entry < ? extends C , ? > > key = getJoinTransformer ( mapEntryKeyTransformer , keyTransformer ) ;
	    Transformer < ? extends D , ? super Map . Entry < ? , ? extends D > > mapEntryValueTransformer = getMapEntryValueTransformer ( ) ;
	    Transformer < ? extends B , ? super Map . Entry < ? , ? extends D > > value = getJoinTransformer ( mapEntryValueTransformer , valueTransformer ) ;
	    Collection < ? extends Map . Entry < ? extends C , ? extends D > > entrySet = map . entrySet ( ) ;
	    Map < ? extends A , ? extends B > output = getTransformerMap ( entrySet , key , value ) ;
	    return output ;
	}

    @ UseConstructor ( MapEntryKeyTransformer . class )
	abstract < K > Transformer < ? extends K , ? super Map . Entry < ? extends K , ? > > getMapEntryKeyTransformer ( ) ;

    @ UseConstructor ( MapEntryValueTransformer . class )
	abstract < V > Transformer < ? extends V , ? super Map . Entry < ? , ? extends V > > getMapEntryValueTransformer ( ) ;

    @ UseConstructor ( JoinTransformer . class )
	abstract < R , A , P > Transformer < ? extends R , ? super P > getJoinTransformer ( Transformer < ? extends A , ? super P > alpha , Transformer < ? extends R , ? super A > beta ) ;

    @ UseConstructor ( TransformerMap . class )
	abstract < K , V , P > Map < ? extends K , ? extends V > getTransformerMap ( Collection < ? extends P > data , Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;
}
