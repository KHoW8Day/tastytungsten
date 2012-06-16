
package tastytungsten ;

import java . util . ArrayList ;
import java . util . Collection ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . type . TypeMirror ;
import javax . lang . model . type . TypeVisitor ;
import javax . lang . model . util . SimpleElementVisitor6 ;

abstract class TypeElementTrainer < R , P , A , B > extends SimpleElementVisitor6 < Iterable < ? extends R > , P >
{
    @ Override
	protected Iterable < ? extends R > defaultAction ( Element element , P data )
	{
	    ElementVisitor < ? extends Iterable < ? extends TypeMirror > , ? super A > wrangler = getWrangler ( ) ;
	    A a = getA ( data ) ;
	    Iterable < ? extends TypeMirror > input = wrangler . visit ( element , a ) ;
	    Collection < R > output = getCollection ( ) ;
	    process ( input , output , data ) ;
	    return output ;
	}

    private void process ( Iterable < ? extends TypeMirror > input , Collection < ? super R > output , P data )
    {
	for ( TypeMirror i : input )
	    {
		process ( i , output , data ) ;
	    }
    }

    private void process ( TypeMirror i , Collection < ? super R > output , P data )
    {
	TypeVisitor < ? extends R , ? super B > visitor = getVisitor ( ) ;
	B b = getB ( data ) ;
	R o = visitor . visit ( i , b ) ;
	output . add ( o ) ;
    }
    
    @ UseParameter
	abstract ElementVisitor < ? extends Iterable < ? extends TypeMirror > , ? super A > getWrangler ( ) ;

    @ UseParameter
	abstract TypeVisitor < ? extends R , ? super B > getVisitor ( ) ;

    @ UseConstructor ( ArrayList . class )
	abstract < T > Collection < T > getCollection ( ) ;

    @ UseNull
	abstract A getA ( P data ) ;

    @ UseNull
	abstract B getB ( P data ) ;
}