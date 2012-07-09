
package tastytungsten ;

enum RegularExpressions
{
    WHITESPACE ( "[ \\t\\r\\n\\v\\f]" )
	;

    private String value ;

    RegularExpressions ( String value )
	{
	    this . value = value ;
	}

    public String toString ( )
    {
	return value ;
    }
}
