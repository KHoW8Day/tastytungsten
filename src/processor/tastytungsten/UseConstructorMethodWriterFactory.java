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

import javax . lang . model . element . AnnotationValue ;
import javax . lang . model . element . Element ;
import javax . lang . model . util . Elements ;

/**
 * Makes a writer for {@see UseStringConstant} method.
 **/
abstract class UseConstructorMethodWriterFactory implements WriterFactory
{
    /**
     * {@inheritDoc}.
     *
     * @param element {@inheritDoc}
     * @param annotationValue {@inheritDoc}
     * @param elementUtils {@inheritDoc}
     * @param uniqueName {@inheritDoc}
     * @return a writer
     **/
	public
	    Callable < ? >
	    make
	    (
	     final Element element ,
	     final AnnotationValue annotationValue ,
	     final Elements elementUtils ,
	     final Object uniqueName
	     )
    {
	Callable < ? > useConstructorMethodWriter =
	    getUseConstructorMethodWriter
	    ( element , annotationValue , elementUtils ) ;
	return useConstructorMethodWriter ;
    }

    /**
     * Gets a UseConstructorMethodWriter.
     *
     * @param element the method to implement
     * @param annotationValue for the class of the constructor
     * @param elementUtils for annotation mirrors
     * @return a UseConstructorMethodWriter
     **/
    @ UseConstructor ( UseConstructorMethodWriter . class )
	abstract Callable < ? >
	getUseConstructorMethodWriter
	(
	 Element element ,
	 AnnotationValue annotationValue ,
	 Elements elementUtils
	 ) ;
}
