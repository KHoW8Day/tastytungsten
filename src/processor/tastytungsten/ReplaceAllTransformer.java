
package tastytungsten ;

abstract class ReplaceAllTransformer implements Transformer < String , Object >
{
    final String transform ( final Object input , @ UseParameter final Object regex , @ UseParameter final Object replace )
    {
	String inputString = input . toString ( ) ;
	String regexString = regex . toString ( ) ;
	String replaceString = replace . toString ( ) ;
	String transform = inputString . replaceAll ( regexString , replaceString ) ;
	return transform ;
    }
}
