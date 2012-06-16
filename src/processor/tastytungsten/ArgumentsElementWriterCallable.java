
package tastytungsten ;

import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;

abstract class ArgumentsElementWriterCallable < P > implements Callable < ElementVisitor < ? , ? super P > >
{
    @ Override
	public ElementVisitor < ? , ? super P > call ( )
	{
	    ElementVisitor < ? extends Iterable < ? extends Element > , ? super P > typeParametersElementWrangler = getTypeParametersElementWrangler ( ) ;
	    ElementVisitor < ? , ? super P > simpleNameElementWrangler = getSimpleNameElementWrangler ( ) ;
	    Object blankConstant = getBlankConstant ( ) ;
	    Object openParenthesisConstant = getOpenParenthesisConstant ( ) ;
	    Object commaConstant = getCommaConstant ( ) ;
	    Object closeParenthesisConstant = getCloseParenthesisConstant ( ) ;
	    ElementVisitor < ? extends Iterable < ? > , ? super P > elementElementTrainer = getElementElementTrainer ( typeParametersElementWrangler , simpleNameElementWrangler ) ;
	    ElementVisitor < ? , ? super P > elementWriter = getElementWriter ( elementElementTrainer , openParenthesisConstant , closeParenthesisConstant , blankConstant , commaConstant , blankConstant , commaConstant , blankConstant , blankConstant ) ;
	    return elementWriter ;
	}

    @ UseConstructor ( ElementWriter . class )
	abstract < P > ElementVisitor < ? , ? super P > getElementWriter ( ElementVisitor < ? , ? super P > lister , Object beforeList , Object afterList , Object beforeFirst , Object afterFirst , Object beforeItem , Object afterItem , Object beforeLast , Object afterLast ) ;

    @ UseConstructor ( ElementElementTrainer . class )
	abstract < R , P , A , B > ElementVisitor < ? extends Iterable < ? extends R > , ? super P > getElementElementTrainer ( ElementVisitor < ? extends Iterable < ? extends Element > , ? super A > lister , ElementVisitor < ? extends R , ? super B > visitor ) ;

    @ UseConstructor ( TypeParametersElementWrangler . class )
	abstract < P > ElementVisitor < ? extends Iterable < ? extends Element > , ? super P > getTypeParametersElementWrangler ( ) ;

    @ UseConstructor ( SimpleNameElementWrangler . class )
	abstract < P > ElementVisitor < ? extends Object , ? super P > getSimpleNameElementWrangler ( ) ;

    @ UseStringConstant ( "" )
	abstract Object getBlankConstant ( ) ;

    @ UseStringConstant ( "<" )
	abstract Object getOpenParenthesisConstant ( ) ;

    @ UseStringConstant ( "," )
	abstract Object getCommaConstant ( ) ;

    @ UseStringConstant ( ">" )
	abstract Object getCloseParenthesisConstant ( ) ;
}