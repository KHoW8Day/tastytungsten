
package tastytungsten ;

import java . util . List ;
import javax . lang . model . element . ExecutableElement ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . element . TypeParameterElement ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class TypeParametersElementWrangler < P > extends SimpleElementVisitor6 < List < ? extends TypeParameterElement > , P >
{
    @ Override
	public List < ? extends TypeParameterElement > visitType ( TypeElement element , P data )
	{
	    List < ? extends TypeParameterElement > typeParameters = element . getTypeParameters ( ) ;
	    return typeParameters ;
	}

    @ Override
	public List < ? extends TypeParameterElement > visitExecutable ( ExecutableElement element , P data )
	{
	    List < ? extends TypeParameterElement > typeParameters = element . getTypeParameters ( ) ;
	    return typeParameters ;
	}
}
