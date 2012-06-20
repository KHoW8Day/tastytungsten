
package tastytungsten ;

import java . io . Writer ;
import java . util . Map ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . element . Name ;
import javax . lang . model . element . PackageElement ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . util . Elements ;

abstract class MockElements implements Elements
{
    @ Override
	@ UseNull
	public abstract PackageElement getPackageElement ( CharSequence qualifiedName ) ;

    @ Override
	@ UseNull
	public abstract TypeElement getTypeElement ( CharSequence qualifiedName ) ;

    @ Override
	@ UseNull
	public abstract Map < ? extends ExecutableElement , ? extends AnnotationValue > getElementValuesWithDefaults ( AnnotationMirror annotation ) ;

    @ Override
	@ UseNull
	public abstract String getDocComment ( Element element ) ;

    @ Override
	public boolean isDeprecated ( Element element )
    {
	return false ;
    }

    @ Override
	@ UseNull
	public abstract Name getBinaryName ( TypeElement typeElement ) ;

    @ Override
	@ UseNull
	public abstract javax.lang.model.element.PackageElement getPackageOf ( Element element );

    @ Override
	@ UseNull
	public abstract java.util.List < ? extends Element > getAllMembers ( TypeElement typeElement ) ;

    @ Override
	@ UseNull
	public abstract java.util.List < ? extends AnnotationMirror > getAllAnnotationMirrors ( Element element ) ;

    @ Override
	public boolean hides ( Element a , Element b )
    {
	return false ;
    }

    @ Override
	public boolean overrides ( ExecutableElement a , ExecutableElement b , TypeElement c )
    {
	return false ;
    }

    @ Override
	@ UseNull
	public abstract String getConstantExpression ( Object val ) ;

    public void printElements ( Writer writer , Element ... elements )
    {
    }

    @ Override
	@ UseNull
	public abstract Name getName ( CharSequence name ) ;
}
