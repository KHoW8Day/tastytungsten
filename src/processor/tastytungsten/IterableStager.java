
package tastytungsten ;

import java . util . Iterator ;
import java . util . List ;

abstract class IterableStager < R , P > implements Stager < List < ? extends R > , Iterable < ? extends P > >
{
    @ Override
	public List < ? extends R > stage ( Iterable < ? extends P > value )
	{
	    Iterator < ? extends P > v = value . iterator ( ) ;
	    return null ;
	}

    @ UseParameter
	abstract Stager < ? extends R , ? super P > getStager ( ) ;
}