
package tastytungsten ;

import java . util . HashMap ;
import java . util . Map ;

abstract class InnerPackageStatementStager implements Stager < StringBuilder , String >
{
    @ Override
	public StringBuilder stage ( String value )
	{
	    StringBuilder stringBuilder = getStringBuilder ( ) ;
	    Object packageConstant = getPackageConstant ( ) ;
	    stringBuilder . append ( packageConstant ) ;
	    Object spaceConstant = getSpaceConstant ( ) ;
	    stringBuilder . append ( spaceConstant ) ;
	    Object semicolonConstant = getSemicolonConstant ( ) ;
	    stringBuilder . append ( semicolonConstant ) ;
	    return stringBuilder ;
	}

    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    @ UseStringConstant ( "package" )
	abstract String getPackageConstant ( ) ;

    @ UseStringConstant ( " " )
	abstract String getSpaceConstant ( ) ;

    @ UseStringConstant ( ";" )
	abstract String getSemicolonConstant ( ) ;
}
