
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class ElementWriter < P > extends SimpleElementVisitor6 < Object , P >
{
    @ Override
	protected Object defaultAction ( Element element , P data )
	{
	    ElementVisitor < ? extends Iterable < ? > , ? super P > lister = getLister ( ) ;
	    Iterable < ? > list = lister . visit ( element , data ) ;
	    Object beforeList = getBeforeList ( ) ;
	    Object afterList = getAfterList ( ) ;
	    Object beforeFirst = getBeforeFirst ( ) ;
	    Object afterFirst = getAfterFirst ( ) ;
	    Object beforeItem = getBeforeItem ( ) ;
	    Object afterItem = getAfterItem ( ) ;
	    Object beforeLast = getBeforeLast ( ) ;
	    Object afterLast = getAfterLast ( ) ;
	    Callable < ? > listWriter = getListWriter ( list , beforeList , afterList , beforeFirst , afterFirst , beforeItem , afterItem , beforeLast , afterLast ) ;
	    Object visit = listWriter . call ( ) ;
	    return visit ;
	}

    @ UseConstructor ( ListWriter . class )
	abstract Callable < ? > getListWriter
	(
	 Iterable < ? > list ,
	 Object beforeList ,
	 Object afterList ,
	 Object beforeFirst ,
	 Object afterFirst ,
	 Object beforeItem ,
	 Object afterItem ,
	 Object beforeLast ,
	 Object afterLast
	 ) ;

    @ UseParameter
	abstract ElementVisitor < ? extends Iterable < ? > , ? super P > getLister ( ) ;

    @ UseParameter
	abstract Object getBeforeList ( ) ;

    @ UseParameter
	abstract Object getAfterList ( ) ;

    @ UseParameter
	abstract Object getBeforeFirst ( ) ;

    @ UseParameter
	abstract Object getAfterFirst ( ) ;

    @ UseParameter
	abstract Object getBeforeItem ( ) ;

    @ UseParameter
	abstract Object getAfterItem ( ) ;

    @ UseParameter
	abstract Object getBeforeLast ( ) ;

    @ UseParameter
	abstract Object getAfterLast ( ) ;
}
