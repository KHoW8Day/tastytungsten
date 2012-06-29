
package tastytungsten ;

import java . util . Collections ;
import java . util . Iterator ;
import java . util . List ;

abstract class IteratorGammaStager < R , P > implements Stager < List < ? extends R > , Iterator < ? extends P > >
{
    @ Override
	public List < ? extends R > stage ( Iterator < ? extends P > iterator )
	{
	    List < ? extends R > stage = emptyList ( ) ;
	    return stage ;
	}

    @ UseStaticMethod ( Collections . class )
	abstract < T > List < T > emptyList ( ) ;
}
