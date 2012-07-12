
package tastytungsten ;

abstract class ReplaceAllTransformer implements Transformer < String , Object >
{
    public final String transform ( final Object input )
    {
	String inputString = input . toString ( ) ;
	Object regex = getRegex ( ) ;
	String regexString = regex . toString ( ) ;
	Object replace = getReplace ( ) ;
	String replaceString = replace . toString ( ) ;
	String transform = inputString . replaceAll ( regexString , replaceString ) ;
	return transform ;
    }

    @ UseParameter
       abstract Object getRegex ( ) ;

    @ UseParameter
	abstract Object getReplace ( ) ;
}
