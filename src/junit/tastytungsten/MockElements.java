
package tastytungsten ;

import java . io . Writer ;
import java . util . List ;
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
	public Map < ? extends ExecutableElement , ? extends AnnotationValue > getElementValuesWithDefaults ( AnnotationMirror annotation )
    {
	Map < ? extends ExecutableElement , ? extends AnnotationValue > elementValuesWithDefaults = annotation . getElementValues ( ) ;
	return elementValuesWithDefaults ;
    }

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
	public abstract List < ? extends Element > getAllMembers ( TypeElement typeElement ) ;

    @ Override
	public List < ? extends AnnotationMirror > getAllAnnotationMirrors ( Element element )
    {
	List < ? extends AnnotationMirror > allAnnotationMirrors = element . getAnnotationMirrors ( ) ;
	return allAnnotationMirrors ;
    }

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
