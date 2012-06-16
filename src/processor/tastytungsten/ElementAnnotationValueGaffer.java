
package tastytungsten ;

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;

abstract class ElementAnnotationValueGaffer < R , P , A , B , C , D > extends AbstractAnnotationValueVisitor < R , P , A , B >
{
    @ Override
	R defaultAction ( AnnotationValue value , B data )
	{
	    ElementVisitor < ? extends R , ? super D > visitor = getVisitor ( ) ;
	    AnnotationValueVisitor < ? extends Element , ? super C > wrangler = getWrangler ( ) ;
	    C c = getC ( data ) ;
	    Element element = wrangler . visit ( value , c ) ;
	    D d = getD ( data ) ;
	    R visit = visitor . visit ( element , d ) ;
	    return visit ;
	}

    @ UseParameter
	abstract AnnotationValueVisitor < ? extends Element , ? super C > getWrangler ( ) ;

    @ UseParameter
	abstract ElementVisitor < ? extends R , ? super D > getVisitor ( ) ;

    @ UseNull
	abstract C getC ( B data ) ;

    @ UseNull
	abstract D getD ( B data ) ;

    /**
     * Gets a reverser or upcaster.
     *
     * @return an annotation value reverser
     **/
    @ Override
	@ UseConstructor ( AnnotationValueReverser . class )
	abstract
	AnnotationValueVisitor < ? extends AnnotationValue , A >
	getAnnotationValueReverser
	( ) ;

    /**
     * Gets the data for the reverser.
     *
     * @param data user data
     * @return the data
     **/
    @ Override
	@ UseNull
	abstract A getA ( P data ) ;

    /**
     * Gets the data for the secondary visit.
     *
     * @param data user data
     * @return the data
     **/
    @ Override
    @ UseNull
	abstract B getB ( P data ) ;
}
