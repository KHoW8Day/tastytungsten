
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
abstract class UseConstructorMethodWriter implements Runnable
{
    /**
     * {@inheritDoc}.
     *
     * Writes a method implementation (body) based on calling a constructor.
     **/
    @ Override
	public void run ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object returnConstant = getReturnConstant ( ) ;
	stringBuilder . append ( returnConstant ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Object newConstant = getNewConstant ( ) ;
	stringBuilder . append ( newConstant ) ;
	stringBuilder . append ( spaceConstant ) ;
	Element element = getElement ( ) ;
	Object simpleName = element . getSimpleName ( ) ;
	stringBuilder . append ( simpleName ) ;
    }

    /**
     * Return the StringBuilder for writing.
     *
     * @return the StringBuilder
     **/
    @ UseParameter
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
     * Returns the new constant.
     *
     * @return new
     **/
    @ UseStringConstant ( "new" )
	abstract Object getNewConstant ( ) ;

    /**
     * Returns the semicolon constant.
     *
     * @return the semicolon
     **/
    @ UseStringConstant ( ";" )
	abstract Object getSemicolonConstant ( ) ;
}