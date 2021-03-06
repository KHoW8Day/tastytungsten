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
 * A class that can make a writer.
 **/
interface WriterFactory
{
    /**
     * Make a Runnable to write something.
     *
     * @param element the method to write about
     * @param annotationValue the annotation value to write about
     * @param elementUtils useful element utils
     * @param uniqueName a name that is guaranteed to be unique
     * @return a writer
     **/
    Callable < ? >
	make
	(
	 Element element ,
	 AnnotationValue annotationValue ,
	 Elements elementUtils ,
	 Object uniqueName
	 ) ;
}
