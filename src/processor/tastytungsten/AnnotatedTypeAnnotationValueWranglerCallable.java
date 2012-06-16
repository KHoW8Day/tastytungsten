
package tastytungsten ;

import java . util . Map ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . Elements ;

abstract class AnnotatedTypeAnnotationValueWranglerCallable < P > implements Callable < AnnotationValueVisitor < ? , ? super P > >
{
    public AnnotationValueVisitor < ? , ? super P > call ( )
    {
	Elements elementUtils = getElementUtils ( ) ;
	AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P > elementValuesWithDefaultsAnnotationValueWrangler = getElementValuesWithDefaultsAnnotationValueWrangler ( elementUtils ) ;
	Object target = getTarget ( ) ;
	AnnotationValueVisitor < ? extends AnnotationValue , ? super P > annotationValuePunter = getAnnotationValuePunter ( elementValuesWithDefaultsAnnotationValueWrangler , target ) ;
	AnnotationValueVisitor < ? extends TypeMirror , ? super P > typeCaster = getTypeMirrorAnnotationValueCaster ( ) ;
	AnnotationValueVisitor < ? extends TypeMirror , ? super P > annotationValueAnnotationValueGaffer = getAnnotationValueAnnotationValueGaffer ( annotationValuePunter , typeCaster ) ;
	TypeVisitor < ? extends Element , ? super P > elementTypeWrangler = getElementTypeWrangler ( ) ;
	AnnotationValueVisitor < ? extends Element , ? super P > typeAnnotationValueGaffer =
	    getTypeMirrorAnnotationValueGaffer
	    (
	     annotationValueAnnotationValueGaffer ,
	     elementTypeWrangler
	     ) ;
	ElementVisitor < ? , ? super P > qualifiedNameElementWrangler = getQualifiedNameElementWrangler ( ) ;
	AnnotationValueVisitor < ? , ? super P > elementAnnotationValueGaffer = getElementAnnotationValueGaffer ( typeAnnotationValueGaffer , qualifiedNameElementWrangler ) ;
	return null ;
    }

    @ UseParameter
	abstract Elements getElementUtils ( ) ;

    @ UseConstructor ( ElementAnnotationValueGaffer . class )
	abstract < R , P , A , B , C , D > AnnotationValueVisitor < ? extends R , ? super P > getElementAnnotationValueGaffer ( AnnotationValueVisitor < ? extends Element , ? super C > wrangler , ElementVisitor < ? extends R , ? super D > visitor ) ;

    @ UseConstructor ( TypeMirrorAnnotationValueGaffer . class )
	abstract < R , P , A , B , C , D > AnnotationValueVisitor < ? extends R , ? super P > getTypeMirrorAnnotationValueGaffer ( AnnotationValueVisitor < ? extends TypeMirror , ? super C > wrangler , TypeVisitor < ? extends R , ? super D > visitor ) ;

    @ UseConstructor ( QualifiedNameElementWrangler . class )
	abstract < P > ElementVisitor < ? , ? super P > getQualifiedNameElementWrangler ( ) ;

    @ UseConstructor ( ElementTypeWrangler . class )
	abstract < P > TypeVisitor < ? extends Element , ? super P > getElementTypeWrangler ( ) ;

    @ UseConstructor ( TypeMirrorAnnotationValueGaffer . class )
	abstract < R , P , A , B , C , D > AnnotationValueVisitor < ? extends R , ? super P > getElementAnnotationValueGaffer ( AnnotationValueVisitor < ? extends TypeMirror , ? super C > wrangler , TypeVisitor < ? extends R , ? super D > visitor ) ;

    @ UseConstructor ( AnnotationValueAnnotationValueGaffer . class )
	abstract < R , P , A , B , C , D > AnnotationValueVisitor < ? extends R , ? super P > getAnnotationValueAnnotationValueGaffer ( AnnotationValueVisitor < ? extends AnnotationValue , ? super C > wrangler , AnnotationValueVisitor < ? extends R , ? super D > visitor ) ;

    @ UseConstructor ( AnnotationValuePunter . class )
	abstract < P , A , B > AnnotationValueVisitor < ? extends AnnotationValue , ? super P > getAnnotationValuePunter ( AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super B > mapper , Object target ) ;

    @ UseConstructor ( ElementValuesWithDefaultsAnnotationValueWrangler . class )
	abstract < P > AnnotationValueVisitor < ? extends Map < ? extends String , ? extends AnnotationValue > , ? super P > getElementValuesWithDefaultsAnnotationValueWrangler ( Elements elementUtils ) ;

    @ UseConstructor ( TypeMirrorAnnotationValueCaster . class )
	abstract < P > AnnotationValueVisitor < ? extends TypeMirror , ? super P > getTypeMirrorAnnotationValueCaster ( ) ;

    @ UseStringConstant ( "value" )
	abstract Object getTarget ( ) ;
}