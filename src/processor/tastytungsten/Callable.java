
package tastytungsten ;

interface Callable < T > extends java . util . concurrent . Callable < T >
{
    @ Override
	@ UseNull
	T call ( ) ;
}