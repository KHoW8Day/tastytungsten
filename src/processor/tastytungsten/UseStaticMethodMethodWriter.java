
package tastytungsten ;

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . util . Elements ;

/**
 * {@inheritDoc}.
 *
 * Writes a method implementation based on calling a constructor.
 **/
abstract class UseStaticMethodMethodWriter implements Callable < StringBuilder >
{
    /**
     * {@inheritDoc}.
     *
     * Writes a method implementation (body) based on calling a constructor.
     **/
    @ Override
	public StringBuilder call ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object returnConstant = getReturnConstant ( ) ;
	stringBuilder . append ( returnConstant ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Element element = getElement ( ) ;
	Object simpleName = element . getSimpleName ( ) ;
	stringBuilder . append ( simpleName ) ;
	Callable < ? extends ElementVisitor < ? , ? super Object > > argumentsElementWriterCallable = getArgumentsElementWriterCallable ( ) ;
	ElementVisitor < ? , ? super Object > argumentsElementWriter = argumentsElementWriterCallable . call ( ) ;
	Object arguments = argumentsElementWriter . visit ( element , null ) ;
	stringBuilder . append ( arguments ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	stringBuilder . append ( semicolonConstant ) ;
	return stringBuilder ;
    }

    /**
     * Return the StringBuilder for writing.
     *
     * @return the StringBuilder
     **/
    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    /**
     * This is not used,
     * but provided
     * so that the constructor will have the right signature.
     *
     * @return the element (not used)
     **/
    @ UseParameter
	abstract Element getElement ( ) ;

    /**
     * Gets the annotation value.
     *
     * @return the annotation value
     **/
    @ UseParameter
	abstract AnnotationValue getAnnotationValue ( ) ;

    /**
     * Gets element utils.
     *
     * @return element utils
     **/
    @ UseParameter
	abstract Elements getElementUtils ( ) ;

    /**
     * Returns the return constant.
     *
     * @return return
     **/
    @ UseStringConstant ( "return" )
	abstract Object getReturnConstant (  );

    /**
     * Returns the space constant.
     *
     * @return space
     **/
    @ UseStringConstant ( " " )
	abstract Object getSpaceConstant ( ) ;

    /**
     * Returns the semicolon constant.
     *
     * @return the semicolon
     **/
    @ UseStringConstant ( ";" )
	abstract Object getSemicolonConstant ( ) ;

    /**
     * Used to write type parameters.
     *
     * @return a callable that will write parameters.
     **/
    @ UseConstructor ( ArgumentsElementWriterCallable . class )
	abstract < P > Callable < ? extends ElementVisitor < ? , ? super P > > getArgumentsElementWriterCallable ( ) ;
}