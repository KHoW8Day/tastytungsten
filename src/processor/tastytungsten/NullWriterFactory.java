
package tastytungsten ;

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
	public Callable make ( Element element , AnnotationValue annotationValue , Elements elementUtils , Object uniqueName )
    {
	assert false ;
	return null ;
    }
}