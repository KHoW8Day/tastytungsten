
package tastytungsten ;

import java . util . Iterator ;

abstract class ListWriter implements Callable < StringBuilder >
{
    @ Override
	public StringBuilder call ( )
    {
	boolean isFirst = true ;
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object beforeList = getBeforeList ( ) ;
	stringBuilder . append ( beforeList ) ;
	Iterable < ? > list = getList ( ) ;
	Iterator < ? > iterator = list . iterator ( ) ;
	for ( Object item = iterator . next ( ) ; iterator . hasNext ( ) ; item = iterator . next ( ) )
	    {
		boolean isLast = iterator . hasNext ( ) ;
		Object beforeItem = null ;
		if ( isFirst )
		    {
			beforeItem = getBeforeFirst ( ) ;
		    }
		else if ( isLast )
		    {
			beforeItem = getBeforeLast ( ) ;
		    }
		else
		    {
			beforeItem = getBeforeItem ( ) ;
		    }
		stringBuilder . append ( beforeItem ) ;
		stringBuilder . append ( item ) ;
		Object afterItem = null ;
		if ( isLast )
		    {
			afterItem = getAfterLast ( ) ;
		    }
		else if ( isFirst )
		    {
			afterItem = getAfterFirst ( ) ;
		    }
		else
		    {
			afterItem = getAfterItem ( ) ;
		    }
		stringBuilder . append ( afterItem ) ;
		isFirst = true ;
	    }
	Object afterList = getAfterList ( ) ;
	stringBuilder . append ( afterList ) ;
	return stringBuilder ;
    }

    @ UseParameter
	abstract Iterable < ? > getList ( ) ;

    @ UseParameter
	abstract Object getBeforeList ( ) ;

    @ UseParameter
	abstract Object getAfterList ( ) ;

    @ UseParameter
	abstract Object getBeforeFirst ( ) ;

    @ UseParameter
	abstract Object getAfterFirst ( ) ;

    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    @ UseParameter
	abstract Object getBeforeItem ( ) ;

    @ UseParameter
	abstract Object getAfterItem ( ) ;

    @ UseParameter
	abstract Object getBeforeLast ( ) ;

    @ UseParameter
	abstract Object getAfterLast ( ) ;
}