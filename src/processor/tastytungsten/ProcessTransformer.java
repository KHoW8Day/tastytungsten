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
import java . util . Map ;
import javax . annotation . processing . RoundEnvironment ;
import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . AnnotationValueVisitor ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . TypeElement ;
import javax . lang . model . util . Elements ;

abstract class ProcessTransformer implements Transformer < Object , TypeElement >
{
    final Object transform ( final TypeElement element , @ UseParameter final RoundEnvironment roundEnvironment , @ UseStringConstant ( "tastytungsten.Implementation" ) Object supportedAnnotationType )
    {
	return null ;
    }

    private Object getAnnotationKey ( TypeElement annotation )
    {
	Object key = annotation . getQualifiedName ( ) ;
	String string = key . toString ( ) ;
	return string ;
    }

    private Transformer < ? , ? super TypeElement > getQualifiedNameTransformer ( )
	{
	    AnnotationValueVisitor < ? , ? super Object > stringAnnotationValueVisitor = getStringAnnotationValueVisitor ( ) ;
	    Transformer < ? , ? super Object > qualifiedNameTransformer = getQualifiedNameTransformer ( ) ;
	    Transformer < ? , ? super Object > annotationValueVisitorTransformer = getAnnotationValueVisitorTransformer ( stringAnnotationValueVisitor , qualifiedNameTransformer , null ) ;
	    
	    return null ;
	}

    private < R > Transformer < ? extends R , ? super TypeElement > getTransformer ( Transformer < ? extends R , ? super AnnotationValue > input , Object key , RoundEnvironment roundEnvironment , TypeElement supportedAnnotationType , Elements elementUtils )
	{
	    Transformer < ? , ? super Element > keyTransformer = getElementValuesKeyTransformer ( ) ;
	    Transformer < ? extends AnnotationValue , ? super AnnotationValue > valueTransformer = getIdentityTransformer ( ) ;
	    Transformer < ? extends Map < ? , ? extends AnnotationValue > , ? super Map < ? extends Element , ? extends AnnotationValue > > mapMapTransformer = getMapMapTransformer ( keyTransformer , valueTransformer ) ;
	    Transformer < ? extends AnnotationValue , ? super Map < ? , ? extends AnnotationValue > > mapItemTransformer = getMapItemTransformer ( key ) ;
	    Transformer < ? extends AnnotationValue , ? super Map < ? extends Element , ? extends AnnotationValue > > join1 = getJoinTransformer ( mapMapTransformer , mapItemTransformer ) ;
	    Transformer < ? extends Map < ? extends Element , ? extends AnnotationValue > , ? super AnnotationMirror > elementValuesWithDefaultsTransformer = getElementValuesWithDefaultsTransformer ( elementUtils ) ;
	    Transformer < ? extends AnnotationValue , ? super AnnotationMirror > join2 = getJoinTransformer ( elementValuesWithDefaultsTransformer , join1 ) ;
	    Transformer < ? extends AnnotationValue , ? super TypeElement > transformer1 = getTransformer ( join2 , roundEnvironment , annotation , elementUtils ) ;
	    Transformer < ? extends R , ? super TypeElement > join3 = getJoinTransformer ( transformer1 , input ) ;
	    return join3 ;
	}

    private < R > Transformer < ? extends R , ? super TypeElement > getTransformer ( Transformer < ? extends R , ? super AnnotationMirror > input , RoundEnvironment roundEnvironment , TypeElement annotation , Elements elementUtils )
	{
	    Transformer < ? , ? super AnnotationMirror > keyTransformer = getAnnotationMirrorKeyTransformer ( ) ;
	    Transformer < ? extends AnnotationMirror , ? super AnnotationMirror > valueTransformer = getIdentityTransformer ( ) ;
	    Transformer < ? extends Map < ? , ? extends AnnotationMirror > , ? super Collection < ? extends AnnotationMirror > > mapTransformer = getMapTransformer ( keyTransformer , valueTransformer ) ;
	    Object annotationKey = getAnnotationKey ( annotation ) ;
	    Transformer < ? extends AnnotationMirror , ? super Map < ? , ? extends AnnotationMirror > > mapItemTransformer = getMapItemTransformer ( annotationKey ) ;
	    Transformer < ? extends AnnotationMirror , ? super Collection < ? extends AnnotationMirror > > join1 = getJoinTransformer ( mapTransformer , mapItemTransformer ) ;
	    Transformer < ? extends Collection < ? extends AnnotationMirror > , ? super Element > allAnnotationMirrorsTransformer = getAllAnnotationMirrorsTransformer ( elementUtils ) ;
	    Transformer < ? extends AnnotationMirror , ? super Element > join2 = getJoinTransformer ( allAnnotationMirrorsTransformer , join1 ) ;
	    Transformer < ? extends R , ? super Element > join3 = getJoinTransformer ( join2 , input ) ;
	    return join3 ;
	}
    
    /*
    private < R > Transformer < ? extends R , ? super TypeElement > getTransformer ( Transformer < ? extends R , ? super Element > input , RoundEnvironment roundEnvironment , Object supportedAnnotationType )
	{
	    Transformer < ? extends String , ? super Element > keyTransformer = getElementKeyTransformer ( ) ;
	    Transformer < ? extends Element , ? super Element > valueTransformer = getIdentityTransformer ( ) ;
	    Transformer < ? extends Map < ? extends String , ? extends Element > , ? super Collection < ? extends Element > > mapTransformer = getMapTransformer ( keyTransformer , valueTransformer ) ;
	    Transformer < ? extends Collection < ? extends Element > , ? super TypeElement > elementsAnnotatedWithTransformer = getElementsAnnotatedWithTransformer ( roundEnvironment ) ;
	    Transformer < ? extends Map < ? extends String , ? extends Element > , ? super TypeElement > join1Transformer = getJoinTransformer ( elementsAnnotatedWithTransformer , mapTransformer ) ;
	    String key = supportedAnnotationType . toString ( ) ;
	    Transformer < ? extends Element , ? super Map < ? extends String , ? extends Element > > mapItemTransformer = getMapItemTransformer ( key ) ;
	    Transformer < ? extends Element , ? super TypeElement > join2Transformer = getJoinTransformer ( join1Transformer , mapItemTransformer ) ;
	    Transformer < ? extends R , ? super TypeElement > output = getJoinTransformer ( join2Transformer , input ) ;
	    return output ;
	}
    */

    @ UseConstructor ( StringAnnotationValueVisitor . class )
	abstract AnnotationValueVisitor < ? , ? super Object > getStringAnnotationValueVisitor ( ) ;

    @ UseConstructor ( QualifiedNameTransformer . class )
	abstract Transformer < ? , ? super Object > getQualifiedNameTransformer ( ) ;

    @ UseConstructor ( AnnotationValueVisitorTransformer . class )
	abstract < R , A , P > Transformer < ? extends R , ? extends A > getAnnotationValueVisitorTransformer ( AnnotationValueVisitor < ? extends R , ? super P > visitor , Transformer < ? extends A , ? super AnnotationValue > transformer , P data ) ;

    @ UseConstructor ( AnnotationMirrorKeyTransformer . class )
	abstract Transformer < ? , ? super AnnotationMirror > getAnnotationMirrorKeyTransformer ( ) ;

    @ UseConstructor ( ElementValuesKeyTransformer . class )
	abstract Transformer < ? , ? super Element > getElementValuesKeyTransformer ( ) ;

    @ UseConstructor ( MapMapTransformer . class )
	abstract < A , B , C , D > Transformer < ? extends Map < ? extends A , ? extends B > , ? super Map < ? extends C , ? extends D > > getMapMapTransformer ( Transformer < ? extends A , ? super C > keyTransformer , Transformer < ? extends B , ? super D > valueTransformer ) ;

    @ UseConstructor ( ElementsAnnotatedWithTransformer . class )
	abstract Transformer < ? extends Collection < ? extends Element > , ? super TypeElement > getElementsAnnotatedWithTransformer ( RoundEnvironment roundEnvironment ) ;

    @ UseConstructor ( MapTransformer . class )
	abstract < K , V , P > Transformer < ? extends Map < ? extends K , ? extends V > , ? super Collection < ? extends P > > getMapTransformer ( Transformer < ? extends K , ? super P > keyTransformer , Transformer < ? extends V , ? super P > valueTransformer ) ;

    @ UseConstructor ( ElementKeyTransformer . class )
	abstract Transformer < ? extends String , ? super Element > getElementKeyTransformer ( ) ;

    @ UseConstructor ( IdentityTransformer . class )
	abstract < R > Transformer < R , ? super R > getIdentityTransformer ( ) ;

    @ UseConstructor ( JoinTransformer . class )
	abstract < R , A , P > Transformer < ? extends R , ? super P > getJoinTransformer ( Transformer < ? extends A , ? super P > alpha , Transformer < ? extends R , ? super A > beta ) ;

    @ UseConstructor ( MapItemTransformer . class )
	abstract < K , V > Transformer < ? extends V , ? super Map < ? extends K , ? extends V > > getMapItemTransformer ( K key ) ;

    @ UseConstructor ( ElementValuesWithDefaultsTransformer . class )
	abstract Transformer < ? extends Map < ? extends Element , ? extends AnnotationValue > , ? super AnnotationMirror > getElementValuesWithDefaultsTransformer ( Elements elementUtils ) ;

    @ UseConstructor ( AllAnnotationMirrorsTransformer . class )
	abstract Transformer < ? extends Collection < ? extends AnnotationMirror > , ? super Element > getAllAnnotationMirrorsTransformer ( Elements elementUtils ) ;
}
