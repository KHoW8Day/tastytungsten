
package tastytungsten ;

import java . util . ArrayList ;
import java . util . Collection ;
import java . util . Iterator ;
import java . util . List ;
import java . util . Map ;

abstract class IteratorBetaStager < R , P > implements Stager < List < ? extends R > , Iterator < ? extends P > >
{
    @ Override
	public Iterable < ? extends R > stage ( Iterator < ? extends P > iterator )
	{
	    List < R > list = getList ( ) ;
	    Stager < ? extends R , ? super P > stager = getStager ( ) ;
	    P p = iterator . next ( ) ;
	    R r = stager . stage ( p ) ;
	    list . add ( r ) ;
	    Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > iteratorAlphaStager = getIteratorAlphaStager ( ) ;
	    Collectioon < ? extends R > stage = iteratorAlphaStager . stage ( iterator ) ;
	    list . addAll ( stage ) ;
	    return list ;
	}

    @ UseParameter
	abstract Stager < ? extends R , ? super P > getStager ( ) ;

    @ UseParameter
	abstract Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > getIteratorAlphaStager ( ) ;

    @ UseConstructor ( ArrayList . class )
	abstract < T > List < T > getList ( ) ;
}
