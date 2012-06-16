
package tastytungsten ;

import java . util . List ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . PackageElement ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class UniqueNameElementAsker < P > extends SimpleElementVisitor6 < Boolean , P >
{
    @ Override
	public Boolean visitType ( TypeElement element , P data )
	{
	    Object target = getTarget ( ) ;
	    List < ? extends Element > enclosedElements = element . getEnclosedElements ( ) ;
	    for ( Element enclosedElement : enclosedElements )
		{
		    Object simpleName = enclosedElement . getSimpleName ( ) ;
		    String string1 = simpleName . toString ( ) ;
		    String string2 = target . toString ( ) ;
		    boolean match = string1 . equals ( string2 ) ;
		    if ( match )
			{
			    return false ;
			}
		}
	    TypeVisitor < ? extends Boolean , ? super P > uniqueNameTypeAsker = getUniqueNameTypeAsker ( target ) ;
	    TypeMirror superclass = element . getSuperclass ( ) ;
	    boolean isSuperclass = uniqueNameTypeAsker . visit ( superclass , data ) ;
	    if ( ! isSuperclass )
		{
		    return false ;
		}
	    Iterable < ? extends TypeMirror > interfaces = element . getInterfaces ( ) ;
	    for ( TypeMirror ntrfc : interfaces )
		{
		    boolean isInterface = uniqueNameTypeAsker . visit ( ntrfc , data ) ;
		    if ( ! isInterface )
			{
			    return false ;
			}
		}
	    return true ;
	}

    @ Override
	public Boolean visitPackage ( PackageElement element , P data )
	{
	    return true ;
	}

    @ UseParameter
	abstract Object getTarget ( ) ;

    @ UseConstructor ( UniqueNameTypeAsker . class )
	abstract < P > TypeVisitor < ? extends Boolean , ? super P > getUniqueNameTypeAsker ( Object target ) ;
}
