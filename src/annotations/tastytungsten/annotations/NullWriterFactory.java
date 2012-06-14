
package tastytungsten . annotations ;

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . Elements ;

final class NullWriterFactory implements WriterFactory
{
    private NullWriterFactory ( )
    {
	assert false ;
    }

    @ Override
	public Runnable make ( StringBuilder stringBuilder , Element element , AnnotationValue annotationValue , Elements elementUtils )
    {
	RuntimeException error = null ;
	throw error ;
    }
}