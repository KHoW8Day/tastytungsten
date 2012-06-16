
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . util . Elements ;


abstract class AnnotatedConstantExpressionAnnotationValueWranglerCallable < P > implements Callable < AnnotationValueVisitor < ? extends String , ? super P > >
{
    @ Override
	public AnnotationValueVisitor < ? extends String , ? super P > call ( )
	{
	    Elements elementUtils = getElementUtils ( ) ;
	    // CHECKSTYLE:OFF
	    AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P >
		// CHECKSTYLE:ON
		elementValuesWithDefaultsAnnotationValueWrangler =
		getElementValuesWithDefaultsAnnotationValueWrangler
		( elementUtils ) ;
	    Object target = getTarget ( ) ;
	    AnnotationValueVisitor < ? extends AnnotationValue , ? super P >
		punter =
		getAnnotationValuePunter
		( elementValuesWithDefaultsAnnotationValueWrangler , target ) ;
	    AnnotationValueVisitor < ? extends String , ? super P >
		constantExpressionAnnotationValueWrangler =
		getConstantExpressionAnnotationValueWrangler ( elementUtils ) ;
	    AnnotationValueVisitor < ? extends String , ? super P > gaffer =
		getAnnotationValueAnnotationValueGaffer
		( punter , constantExpressionAnnotationValueWrangler ) ;
	    return gaffer ;
	}

    /**
     * Gets element utils.
     *
     * @return element utils
     **/
    @ UseParameter
	abstract Elements getElementUtils ( ) ;

    /**
     * gets a element values with defaults wrangler.
     *
     * @param <P> the data type
     * @param elementUtils element utils
     * @return an element values with defaults wrangler
     **/
    @ UseConstructor
	( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract
	< P >
    // CHECKSTYLE:OFF
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P >
									      getElementValuesWithDefaultsAnnotationValueWrangler
									      ( final Elements elementUtils ) ;
    // CHECKSTYLE:ON

    /**
     * Gets a visitor to convert a string annotation to a string.
     *
     * @param <P> the data type
     * @return a string caster
     **/
    @ UseConstructor ( ConstantExpressionAnnotationValueWrangler . class )
	abstract
	< P >
	AnnotationValueVisitor < ? extends String , ? super P >
					   getConstantExpressionAnnotationValueWrangler
					   ( Elements elementUtils ) ;

    /**
     * Gets an annotation value punter.
     *
     * @param <P> user data type
     * @param <A> other data type
     * @param <B> mapper data type
     * @param mapper the specified mapper
     * @param target the specified target
     * @return a punter
     **/
    @ UseConstructor ( AnnotationValuePunter . class )
	abstract
	< P , A , B >
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P >
					   getAnnotationValuePunter
					   (
					    // CHECKSTYLE:OFF
					    AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super B > mapper ,
					    // CHECKSTYLE:ON
					    Object target
					    ) ;

    /**
     * Gets a punter.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param <A> the wrangler's data type
     * @param <B> the visitor's data type
     * @param <C> other data type
     * @param <D> other data type
     * @param wrangler wrangles an annotation value
     * @param visitor visits an annotation
     * @return a punter
     **/
    @ UseConstructor ( AnnotationValueAnnotationValueGaffer . class )
	abstract
	< R , P , A , B , C , D >
	AnnotationValueVisitor < ? extends R , ? super P >
					   // CHECKSTYLE:OFF
					   getAnnotationValueAnnotationValueGaffer
					   // CHECKSTYLE:ON
					   (
					    // CHECKSTYLE:OFF
					    AnnotationValueVisitor < ? extends AnnotationValue , ? super C > wrangler ,
					    AnnotationValueVisitor < ? extends R , ? super D > visitor
					    // CHECKSTYLE:ON
					    ) ;

    /**
     * Gets the target.
     *
     * @return the target
     **/
    @ UseStringConstant ( "value" )
	abstract Object getTarget ( ) ;
}