
package tastytungsten ;

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
    @ UseNull
	public abstract PackageElement getPackageElement ( CharSequence qualifiedName ) ;

    @ UseNull
	public abstract TypeElement getTypeElement ( CharSequence qualifiedName ) ;

    @ UseNull
	public abstract Map < ? extends ExecutableElement , ? extends AnnotationValue > getElementValuesWithDefaults ( AnnotationMirror annotation ) ;

    @ UseNull
	public abstract String getDocComment ( Element element ) ;

    @ UseNull
	public abstract boolean isDeprecated ( Element element ) ;

    @ UseNull
	public abstract Name getBinaryName ( TypeElement typeElement ) ;

    @ UseNull
	public abstract javax.lang.model.element.PackageElement getPackageOf ( Element element );
    public abstract java.util.List getAllMembers(javax.lang.model.element.TypeElement);
    public abstract java.util.List getAllAnnotationMirrors(javax.lang.model.element.Element);
    public abstract boolean hides(javax.lang.model.element.Element, javax.lang.model.element.Element);
    public abstract boolean overrides(javax.lang.model.element.ExecutableElement, javax.lang.model.element.ExecutableElement, javax.lang.model.element.TypeElement);
    public abstract java.lang.String getConstantExpression(java.lang.Object);
    public abstract void printElements(java.io.Writer, javax.lang.model.element.Element[]);
    public abstract javax.lang.model.element.Name getName(java.lang.CharSequence);

}
