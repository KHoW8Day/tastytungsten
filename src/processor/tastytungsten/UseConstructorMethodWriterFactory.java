
package tastytungsten ;

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . Elements ;

abstract class UseConstructorMethodWriterFactory implements WriterFactory
{
    @ Override
	public Runnable make ( StringBuilder stringBuilder , Element element , AnnotationValue annotationValue , Elements elementUtils )
    {
	return null ;
    }
}