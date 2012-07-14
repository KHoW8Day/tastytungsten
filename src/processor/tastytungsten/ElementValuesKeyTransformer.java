
package tastytungsten ;

import javax . lang . model . element . Element ;

abstract class ElementValuesKeyTransformer implements Transformer < String , Element >
{
    @ Override
	public final String transform ( Element element )
	{
	    Object simpleName = element . getSimpleName ( ) ;
	    String string = simpleName . toString ( ) ;
	    return string ;
	}
}
