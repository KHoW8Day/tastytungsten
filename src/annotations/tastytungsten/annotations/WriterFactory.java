
package tastytungsten . annotations ;

import java . util . concurrent . Callable ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;

public interface WriterFactory
{
    Callable < ? > make ( Element element , AnnotationValue annotationValue ) ;
}