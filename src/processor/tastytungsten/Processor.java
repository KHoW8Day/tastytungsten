// Copyright © (C) 2012 Emory Hughes Merryman, III
//
// This file is part of tastytungsten.
//
// tastytungsten is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// tastytungsten is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//

package tastytungsten ;

import java . util . Collections ;
import java . util . Set ;
import javax . annotation . processing . AbstractProcessor ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . TypeElement ;

abstract class Processor extends AbstractProcessor
{
    final Set < String > getSupportedAnnotationTypes ( @ UseStringConstant ( "tastytungsten . Implementation" ) String supportedAnnotationType )
    {
	Transformer < ? extends String , ? super String > qualifiedNameTransformer = getQualifiedNameTransformer ( ) ;
	String qualifiedName = qualifiedNameTransformer . transform ( supportedAnnotationType ) ;
	Set < String > supportedAnnotationTypes = singleton ( qualifiedName ) ;
	return supportedAnnotationTypes ;
    }

    @ Override
	public boolean process ( final Set < ? extends TypeElement > annotations , final RoundEnvironment roundEnvironment )
    {
	Transformer < ? , ? super Element > processorTransformer = getProcessorTransformer ( ) ;
	Transformer < ? , ? super Iterable < ? extends Element > > iterableTransformer = getIterableTransformer ( processorTransformer ) ;
	iterableTransformer . transform ( annotations ) ;
	return true ;
    }

    @ UseStaticMethod ( Collections . class )
	abstract < T > Set < T > singleton ( T item ) ;

    @ UseConstructor ( QualifiedNameTransformer . class )
	abstract Transformer < ? extends String , ? super String > getQualifiedNameTransformer ( ) ;

    @ UseConstructor ( ProcessorTransformer . class )
	abstract Transformer < ? , ? super Element > getProcessorTransformer ( ) ;

    @ UseConstructor ( IterableTransformer . class )
	abstract < R , P > Transformer < ? extends Iterable < ? extends R > , ? super Iterable < ? extends P > > getIterableTransformer ( Transformer < ? extends R , ? super P > transformer ) ;
}
