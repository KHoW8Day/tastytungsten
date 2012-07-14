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

import java . util . Collection ;
import java . util . Collections ;
import java . util . Set ;
import javax . annotation . processing . AbstractProcessor ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . TypeElement ;

@ Implementation ( "tastytungsten . StandardProcessor" )
    abstract class Processor extends AbstractProcessor
    {
	final Set < String > getSupportedAnnotationTypes ( @ UseStringConstant ( "tastytungsten . Implementation" ) final Object supportedAnnotationType )
	{
	    Transformer < ? , ? super Object > qualifiedNameTransformer = getQualifiedNameTransformer ( ) ;
	    Object qualifiedName = qualifiedNameTransformer . transform ( supportedAnnotationType ) ;
	    String string = qualifiedName . toString ( ) ;
	    Set < String > supportedAnnotationTypes = singleton ( string ) ;
	    return supportedAnnotationTypes ;
	}

	@ Override
	    public final boolean process ( final Set < ? extends TypeElement > annotations , final RoundEnvironment roundEnvironment )
	{
	    Transformer < ? , ? super TypeElement > processTransformer = getProcessTransformer ( roundEnvironment ) ;
	    Transformer < ? , ? super Collection < ? extends TypeElement > > setTransformer = getSetTransformer ( processTransformer ) ;
	    setTransformer . transform ( annotations ) ;
	    return true ;
	}

	@ UseStaticMethod ( Collections . class )
	    abstract < T > Set < T > singleton ( T item ) ;

	@ UseConstructor ( QualifiedNameTransformer . class )
	    abstract Transformer < ? , ? super Object > getQualifiedNameTransformer ( ) ;

	@ UseConstructor ( ProcessTransformer . class )
	    abstract Transformer < ? , ? super TypeElement > getProcessTransformer ( RoundEnvironment roundEnvironment ) ;

	@ UseConstructor ( SetTransformer . class )
	    abstract < R , P > Transformer < ? extends Iterable < ? extends R > , ? super Collection < ? extends P > > getSetTransformer ( Transformer < ? extends R , ? super P > transformer ) ;
    }
