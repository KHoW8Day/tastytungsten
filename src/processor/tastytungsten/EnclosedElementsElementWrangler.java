
package tastytungsten ;

import java . util . List ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class EnclosedElementsElementWrangler < P > extends SimpleElementVisitor6 < List < ? extends Element > , P >
{
    @ Override
	protected List < ? extends Element > defaultAction ( Element element , P data )
    {
	List < ? extends Element > enclosedElements = element . getEnclosedElements ( ) ;
	return enclosedElements ;
    }
}