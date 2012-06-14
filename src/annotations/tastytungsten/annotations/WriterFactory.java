
package tastytungsten . annotations ;

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . Elements ;

public interface WriterFactory
{
    Runnable make ( StringBuilder stringBuilder , Element element , AnnotationValue annotationValue , Elements elementUtils ) ;
}