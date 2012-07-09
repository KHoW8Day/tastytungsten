
package tastytungsten ;

abstract class Transformers
{
    final Transformer < ? extends Object , ? super Object > getQualifiedNameTransformer (  )
    {
	Transformer < ? , ? super Object > qualifiedNameTransformer = getReplaceAllTransformer ( RegularExpressions . WHITESPACE , Strings . BLANK ) ;
	return qualifiedNameTransformer ;
    }

    @ UseConstructor ( ReplaceAllTransformer . class )
	abstract Transformer < ? , ? super Object > getReplaceAllTransformer ( Object regex , Object replace ) ;
}
