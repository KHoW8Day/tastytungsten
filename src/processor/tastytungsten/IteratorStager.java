
package tastytungsten ;

import java . util . ArrayList ;
import java . util . Iterator ;
import java . util . Collection ;
import java . util . HashMap ;
import java . util . List ;
import java . util . Map ;

abstract class IteratorStager < R , P > implements Stager < Collection < ? extends R > , Iterator < ? super P > >
{
    @ Override
	public final List < ? extends R > stage ( Iterator < ? extends P > value )
	{
	    List < R > list = getList ( ) ;
	    Map < Boolean , Stager < ? , ? super Iterator < ? extends P > > > map = getMap ( ) ;
	    Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > iteratorAlphaStager = getIteratorAlphaStager ( map ) ;
	    Stager < ? extends R , ? super P > stager = getStager ( ) ;
	    Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > iteratorBetaStager = getIteratorAlphaStager ( stager , iteratorAlphaStager ) ;
	    Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > iteratorGammaStager = getIteratorAlphaStager ( ) ;
	    map . put ( true , iteratorBetaStager ) ;
	    map . put ( false , iteratorGammaStager ) ;
	}

    @ UseParameter
	abstract Stager < ? extends R , ? super P > getStager ( ) ;

    @ UseConstructor ( ArrayList . class )
	abstract < T > List < T > getList ( ) ;

    @ UseConstructor ( HashMap . class )
	abstract < K , V > Map < K , V > getMap ( ) ;

    @ UseConstructor ( IteratorAlphaStager . class )
	abstract Stager < ? extends Collection < ? extends R > , ? super Iterator < ? super P > > > getIteratorAlphaStager ( Map < ? extends Boolean , ? super Stager < ? , ? super Iterator < ? extends P > > map ) ;

    @ UseConstructor ( IteratorBetaStager . class )
	abstract Stager < ? extends Collection < ? extends R > , ? super Iterator < ? super P > > > getIteratorBetaStager ( Stager < ? extends R , ? super P > stager , Stager < ? extends Collection < ? extends R > , ? super Iterator < ? extends P > > iteratorAlphaStager ) ;

    @ UseConstructor ( IteratorGammaStager . class )
	abstract Stager < ? extends Collection < ? extends R > , ? super Iterator < ? super P > > > getIteratorGammaStager ( ) ;
}
