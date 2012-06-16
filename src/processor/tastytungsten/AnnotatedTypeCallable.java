
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . Elements ;

abstract class AnnotatedTypeCallable < P > implements Callable < AnnotationValueVisitor < ? , ? super P > >
{
    public AnnotationValueVisitor < ? , ? super P > call ( )
    {
	Elements elementUtils = getElementUtils ( ) ;
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P > elementValuesWithDefaultsAnnotationValueWrangler = getElementValuesWithDefaultsAnnotationValueWrangler ( elementUtils ) ;
	Object target = getTarget ( ) ;
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P > annotationValuePunter = getAnnotationValuePunter ( elementValuesWithDefaultsAnnotationValueWrangler , target ) ;
	AnnotationValueVisitor < ? extends TypeMirror , ? super P > typeCaster = getTypeMirrorAnnotationValueCaster ( ) ;
	AnnotationValueVisitor < ? extends TypeMirror , ? super P > annotationValueAnnotationValueGaffer = getAnnotationValueAnnotationValueGaffer ( annotationValuePunter , typeCaster ) ;
	return null ;
    }

    @ UseParameter
	abstract Elements getElementUtils ( ) ;

    @ UseConstructor ( TypeMirrorAnnotationValueVisitor . class )
	abstract < R , P , A , B > AnnotationValueVisitor < ? extends R , ? super P > getElementAnnotationValueGaffer ( AnnotationValueVisitor < ? extends TypeMirror , ? super A > wrangler , TypeVisitor < ? extends R , ? super B > visitor ) ;

    @ UseConstructor ( AnnotationValueAnnotationValueGaffer . class )
	abstract < R , P , A , B > AnnotationValueVisitor < ? extends R , ? super P > getAnnotationValueAnnotationValueGaffer ( AnnotationValueVisitor < ? extends AnnotationValue , ? super A > wrangler , AnnotationValueVisitor < ? extends R , ? super B > visitor ) ;

    @ UseConstructor ( AnnotationValuePunter . class )
	abstract < P > AnnotationValueVisitor < ? extends AnnotationValue , ? super P > getAnnotationValuePunter ( AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P > mapper , Object target ) ;

    @ UseConstructor ( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract < P > AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P > getElementValuesWithDefaultsAnnotationValueWrangler ( Elements elementUtils ) ;

    @ UseConstructor ( TypeMirrorAnnotationValueCaster . class )
	abstract < P > AnnotationValueVisitor < ? extends TypeMirror , ? super P > getTypeMirrorAnnotationValueCaster ( ) ;

    @ UseStringConstant ( "value" )
	abstract Object getTarget ( ) ;
}