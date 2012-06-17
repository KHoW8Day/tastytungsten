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
 * This class is solely for providing a default value to
 * annotations.
 *
 * Instances of this class should never exist.
 **/
final class NullWriterFactory implements WriterFactory
{
    /**
     * This constructor is private and the class is package level.
     * The constructor should never be invoked.
     * If it is invoked, then it will assert false.
     **/
    private NullWriterFactory ( )
    {
	assert false ;
    }

    /**
     * This should never actually be invoked.
     * If invoked it will assert false.
     *
     * @param element useless
     * @param annotationValue useless
     * @param elementUtils useless
     * @param uniqueName useless
     * @return never
     **/
    @ Override
	public
	Callable make
	(
	 final Element element ,
	 final AnnotationValue annotationValue ,
	 final Elements elementUtils ,
	 final Object uniqueName
	 )
    {
	assert false ;
	return null ;
    }
}
