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

@ Implementation ( "tastytungsten . StandardProcessor" )
    abstract class Processor extends AbstractProcessor
    {
	final Set < String > getSupportedAnnotationTypes ( @ UseStringConstant ( "tastytungsten . Implementation" ) final Object supportedAnnotationType )
	{
	    Transformers transformers = getTransformers ( ) ;
	    Transformer < ? , ? super Object > qualifiedNameTransformer = transformers . getQualifiedNameTransformer ( ) ;
	    Object qualifiedName = qualifiedNameTransformer . transform ( supportedAnnotationType ) ;
	    String string = qualifiedName . toString ( ) ;
	    Set < String > supportedAnnotationTypes = singleton ( string ) ;
	    return supportedAnnotationTypes ;
	}

	@ Override
	    public final boolean process ( final Set < ? extends TypeElement > annotations , final RoundEnvironment roundEnvironment )
	{
	    return true ;
	}

	@ UseStaticMethod ( Collections . class )
	    abstract < T > Set < T > singleton ( T item ) ;

	@ UseConstructor ( Transformers . class )
	    abstract Transformers getTransformers ( ) ;
    }
