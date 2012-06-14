
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . util . Elements ;

abstract class UseStringConstantMethodWriter implements Runnable
{
    /**
     *
     **/
    @ Override public void run ( )
    {
	Elements elementUtils = getElementUtils ( ) ;
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super Object > elementValuesWithDefaultsAnnotationValueWrangler = getElementValuesWithDefaultsAnnotationValueWrangler ( elementUtils ) ;
	Object target = getTarget ( ) ;
	AnnotationValueVisitor < ? extends AnnotationValue , ? super Object > punter = getAnnotationValuePunter ( elementValuesWithDefaultsAnnotationValueWrangler , target ) ;
	AnnotationValueVisitor < ? extends String , ? super Object > stringAnnotationValueCaster = getStringAnnotationValueCaster ( ) ;
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
    @ UseConstructor ( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract < P > AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P > getElementValuesWithDefaultsAnnotationValueWrangler ( Elements elementUtils ) ;

    /**
     * Gets a visitor to convert a string annotation to a string.
     *
     * @param <P> the data type
     * @return a string caster
     **/
    @ UseConstructor ( StringAnnotationValueCaster . class )
	abstract < P > AnnotationValueVisitor < ? extends String , ? super P > getStringAnnotationValueCaster ( ) ;

    /**
     * Gets an annotation value punter.
     *
     * @param mapper the specified mapper
     * @param target the specified target
     * @return a punter
     **/
    @ UseConstructor ( AnnotationValuePunter . class )
	abstract < R , P , A , B > AnnotationValueVisitor < ? extends R , ? super P > getAnnotationValuePunter ( AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super A > mapper , Object target ) ;

    /**
     * Gets a punter.
     *
     * @param <R> the return type
     * @param <P> the data type
     * @param <A> the wrangler's data type
     * @param <B> the visitor's data type
     * @param wrangler wrangles an annotation value
     * @param visitor visits an annotation
     * @return a punter
     **/
    @ UseConstructor ( AnnotationValueAnnotationValueGaffer . class )
	abstract
	< R , P , A , B >
	AnnotationValueVisitor < ? extends R , ? super P >
					   getAnnotationValueAnnotationValueGaffer
					   (
					    AnnotationValueVisitor < ? extends AnnotationValue , ? super A > wrangler ,
					    AnnotationValueVisitor < ? extends R , ? super B > visitor
					    ) ;

    /**
     * Gets the target.
     *
     * @return the target
     **/
    @ UseStringConstant ( "value" )
	abstract Object getTarget ( ) ;
}
