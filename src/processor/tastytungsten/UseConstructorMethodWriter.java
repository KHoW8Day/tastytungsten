
package tastytungsten ;

import javax . lang . model . element . Element ;

abstract class UseConstructorMethodWriter implements Runnable
{
    @ Override
	public void run ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object returnConstant = getReturnConstant ( ) ;
	stringBuilder . append ( returnConstant ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Object newConstant = getNewConstant ( ) ;
	Element element = getElement ( ) ;
	Object simpleName = element . getSimpleName ( ) ;
	stringBuilder . append ( simpleName ) ;
    }

    @ UseParameter
	abstract StringBuilder getStringBuilder ( ) ;

    @ UseParameter
	abstract Element getElement ( ) ;

    @ UseStringConstant ( "return" )
	abstract Object getReturnConstant (  );

    @ UseStringConstant ( " " )
	abstract Object getSpaceConstant ( ) ;

    @ UseStringConstant ( "new" )
	abstract Object getNewConstant ( ) ;

    @ UseStringConstant ( ";" )
	abstract Object getSemicolonConstant ( ) ;
}