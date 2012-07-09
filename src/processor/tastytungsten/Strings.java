
package tastytungsten ;

enum Strings
{
    BLANK ( "" )
	;

    private String value ;

    Strings ( String value )
	{
	    this . value = value ;
	}

    public String toString ( )
    {
	return value ;
    }
}
