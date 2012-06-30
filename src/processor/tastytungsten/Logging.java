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

import java . util . logging . Logger ;

/**
 * Logger.
 **/
abstract class Logging
{
    /**
     * Log at the finest level.
     *
     * @param object the source of the event
     * @param template the event template
     * @param params for insertion into the template
     **/
    void
	finest
	(
	 final Object object ,
	 final String template ,
	 final Object ... params
	 )
    {
	Class < ? > clazz = object . getClass ( ) ;
	String canonicalName = clazz . getCanonicalName ( ) ;
	Logger logger = getLogger ( canonicalName ) ;
	String questionMark = getQuestionMark ( ) ;
	String converter = getS ( ) ;
	String format = template . replace ( questionMark , converter ) ;
	String message = format ( format , params ) ;
	logger . finest ( message ) ;
    }

    /**
     * Get a logger.
     *
     * @param name the name of the logger
     * @return a logger
     **/
    @ UseStaticMethod ( Logger . class )
	abstract Logger getLogger ( String name ) ;

    /**
     * Gets the template holder.
     *
     * @return the template holder
     **/
    @ UseStringConstant ( "?" )
	abstract String getQuestionMark ( ) ;

    /**
     * Gets the to string converter.
     *
     * @return the to string coverter
     **/
    @ UseStringConstant ( "%s" )
	abstract String getS ( ) ;

    /**
     * Formats a string.
     * {@link String}
     *
     * @param format the format
     * @param args arguments
     * @return a formatted string
     **/
    @ UseStaticMethod ( String . class )
	abstract String format ( String format , Object [ ] args ) ;
}
