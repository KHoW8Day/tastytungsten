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

import java . util . Map ;
import javax . lang . model . element . Element ;

/**
 * A String keyed Map Entry based on an Element keyed Map Entry.
 *
 * @param <V> usually {@link javax.lang.model.element.AnnotationValue}
 **/
@ SuppressWarnings ( "unchecked" )
    abstract class ElementValueMapEntry < V >
    implements Map . Entry < String , V >
{
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public String getKey ( )
    {
	Map . Entry < ? extends Element , ? > entry = getEntry ( ) ;
	Element element = entry . getKey ( ) ;
	Object simpleName = element . getSimpleName ( ) ;
	String string = simpleName . toString ( ) ;
	Class < ? > clazz = getClass ( ) ;
	Logging logging = getLogging ( ) ;
	String keyMessage = getKeyMessage ( ) ;
	logging . finest ( this , keyMessage , string ) ;
	return string ;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public V getValue ( )
	{
	    Map . Entry < ? , ? extends V > entry = getEntry ( ) ;
	    V annotationValue = entry . getValue ( ) ;
	    Logging logging = getLogging ( ) ;
	    String valueMessage = getValueMessage ( ) ;
	    logging . finest ( this , valueMessage , annotationValue ) ;
	    return annotationValue ;
	}

    /**
     * {@inheritDoc}.
     *
     * @param annotationValue {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public
	abstract
	V
	setValue
	( final V annotationValue ) ;

    /**
     * {@inheritDoc}
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     **/
    @ Override
	public boolean equals ( final Object o )
	{
	    Class < ? > oClass = o . getClass ( ) ;
	    boolean isAssignableFrom =
		ElementValueMapEntry . class . isAssignableFrom ( oClass ) ;
	    boolean equals ;
	    if ( isAssignableFrom )
		{
		    ElementValueMapEntry < ? > oo =
			ElementValueMapEntry . class . cast ( o ) ;
		    equals = equals ( oo ) ;
		}
	    else
		{
		    equals = false ;
		}
	    Logging logging = getLogging ( ) ;
	    String equalsMessage = getEqualsMessage ( ) ;
	    logging . finest
		(
		 this ,
		 equalsMessage ,
		 this ,
		 o ,
		 isAssignableFrom ,
		 equals
		 ) ;
	    return equals ;
	}

    /**
     * Tests for equality with the specified
     * ElementValueMapEntry.
     * has access to all methods.
     *
     * @param o the specified other ElementValueMapEntry
     * @return true iff they are equal
     **/
    private boolean equals ( final ElementValueMapEntry < ? > o )
    {
	Object e1 = getEntry ( ) ;
	Object e2 = o . getEntry ( ) ;
	boolean equals = e1 . equals ( e2 ) ;
	return equals ;
    }

    /**
     * {@inheritDoc}.
     *
     * @return {@inheritDoc}
     **/
    @ Override
	public int hashCode ( )
	{
	    Object entry = getEntry ( ) ;
	    int hashCode = entry . hashCode ( ) ;
	    return hashCode ;
	}

    /**
     * Gets the Element based entry.
     *
     * @return the Element based entry
     **/
    @ UseParameter
	abstract
	Map . Entry < ? extends Element , ? extends V >
	getEntry
	( ) ;

    /**
     * gets the logging utils.
     *
     * @return the logging utils
     **/
    @ UseConstructor ( Logging . class )
	abstract Logging getLogging ( ) ;

    /**
     * gets the key logging message template.
     *
     * @return the key logging message template
     **/
    @ UseStringConstant ( "key = ?" )
	abstract String getKeyMessage ( ) ;

    /**
     * Gets the value logging message template.
     *
     * @return the value logging message template
     **/
    @ UseStringConstant ( "value = ?" )
	abstract String getValueMessage ( ) ;

    /**
     * Gets the equals logging message template.
     *
     * @return the equals logging message template
     **/
    @ UseStringConstant
	( "e1 = ? , e2 = ? , isAssignableFrom = ? , equals = ?" )
	abstract String getEqualsMessage ( ) ;
}
