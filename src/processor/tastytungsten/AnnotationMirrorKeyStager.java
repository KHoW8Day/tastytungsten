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

import javax . lang . model . element . AnnotationMirror ;
import javax . lang . model . element . Element ;
import javax . lang . model . element . ElementVisitor ;
import javax . lang . model . element . Name ;
import javax . lang . model . type . DeclaredType ;

/**
 * A stager that gets the name of an annotation mirror.
 **/
abstract class AnnotationMirrorKeyStager
    implements Stager < String , AnnotationMirror >
{
    @ Override
	public
	final
	String
	stage
	( final AnnotationMirror value )
	{
	    ElementVisitor < ? extends Name , ? super Object >
		qualifiedNameElementVisitor =
		getQualifiedNameElementVisitor ( ) ;
	    DeclaredType annotationType = value . getAnnotationType ( ) ;
	    Element annotationElement = annotationType . asElement ( ) ;
	    Name qualifiedName =
		qualifiedNameElementVisitor . visit
		( annotationElement ) ;
	    String string = qualifiedName . toString ( ) ;
	    return string ;
	}

    abstract class Stuff
    {
	void run ( )
	{
	}
    }

    /**
     * Gets a qualified name element visitor.
     *
     * @return a qualified name element visitor
     **/
    @ UseConstructor ( QualifiedNameElementVisitor . class )
	abstract
	ElementVisitor < Name , Object >
	getQualifiedNameElementVisitor
	( ) ;
}
