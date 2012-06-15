
package tastytungsten ;

import java . util . Random ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class UniqueNameElementWrangler < P > extends SimpleElementVisitor6 < StringBuilder , P >
{
    @ Override
	protected StringBuilder defaultAction ( Element element , P data )
	{
	    StringBuilder stringBuilder = getStringBuilder ( ) ;
	    Object prefixConstant = getPrefixConstant ( ) ;
	    stringBuilder . append ( prefixConstant ) ;
	    Random random = getRandom ( ) ;
	    int next = random . nextInt ( ) ;
	    int abs = abs ( next ) ;
	    stringBuilder . append ( abs ) ;
	    return stringBuilder ;
	}

    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    @ UseStringConstant ( "_var_" )
	abstract Object getPrefixConstant ( ) ;

    @ UseParameter
	abstract Random getRandom ( ) ;

    @ UseStaticMethod ( Math . class )
	abstract int abs ( int x ) ;
}
