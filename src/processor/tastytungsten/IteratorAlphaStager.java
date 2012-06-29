
package tastytungsten ;

import java . util . Iterator ;
import java . util . List ;
import java . util . Map ;

abstract class IteratorAlphaStager < R , P > implements Stager < List < ? extends R > , Iterator < ? extends P > >
{
    @ Override
	public Iterable < ? extends R > stage ( Iterator < ? extends P > iterator )
	{
	    Map < ? extends Boolean , ? extends Stager < ? extends Collection < ? extends R > , ? extends Iterator < ? extends P > > > map = getMap ( ) ;
	    boolean hasNext = iterator . hasNext ( ) ;
	    Stager < ? extends Iterable < ? extends R > , ? extends Collection < ? extends P > > stager = map . get ( hasNext ) ;
	    Collection < ? extends R > stage = stager . stage ( iterator ) ;
	    return stage ;
	}

    @ UseParameter
	abstract Map < ? extends Boolean , ? extends Stager < ? extends Iterable < ? extends R > , ? extends Iterator < ? extends P > > > getMap ( ) ;
}
