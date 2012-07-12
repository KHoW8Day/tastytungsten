
package tastytungsten ;

abstract class QualifiedNameTransformer extends ReplaceAllTransformer
{
    @ Override
	final Object getRegex ( )
    {
	return RegularExpressions . WHITESPACE ;
    }

    @ Override
	final Object getReplace ( )
    {
	return Strings . BLANK ;
    }
}