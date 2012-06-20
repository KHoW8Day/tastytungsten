
package tastytungsten ;

abstract class MockAnnotationMirror implements AnnotationMirror
{
    @ UseNull
	abstract DeclaredType getAnnotationType ( ) ;

    @ UseParameter
	abstract Map < ? extends ExecutableElement , ? extends AnnotationValue > getElementValues ( ) ;
}
