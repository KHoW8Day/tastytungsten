
package tastytungsten ;

import java . util . Iterator ;

abstract class StagerIterator < R , P > implements Iterator < R >
{
    @ Override
	public final boolean hasNext ( )
	{
	    Iterator < P > iterator = getIterator ( ) ;
	    boolean hasNext = iterator . hasNext ( ) ;
	    return hasNext ;
	}

    @ Override
	public final R next ( )
	{
	    Stager < ? extends R , ? super P > stager = getStager ( ) ;
	    Iterator < P > iterator = getIterator ( ) ;
	    P p = iterator . next ( ) ;
	    R r = stager . stage ( p ) ;
	    return r ;
	}

    @ Override
	public abstract void remove ( ) ;

    @ UseParameter
	abstract Iterator < P > getIterator ( ) ;

    @ UseParameter
	abstract Stager < ? extends R , ? super P > getStager ( ) ;
}
