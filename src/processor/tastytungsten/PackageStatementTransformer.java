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

/**
 * For writing the package statement.
 **/
abstract class PackageStatementTransformer implements Transformer < String , String >
{
    /**
     * Writes a package statement.
     *
     * @param value a string containing
     *        the fully qualified name inconsistently whitespaced.
     * @return a consistently whitespaced package name statement
     *         (or a blank if in the unnamed package)
     **/
    public String transform ( final String value )
    {
	Transformer < ? extends String , ? super String > qualifiedNameTransformer =
	    getQualifiedNameTransformer ( ) ;
	String val1 = qualifiedNameTransformer . transform ( value ) ;
	String beforeFirstTerm = getBeforeFirstTerm ( ) ;
	String packageTerm = getPackageTerm ( ) ;
	String val2 = val1 . replaceAll ( beforeFirstTerm , packageTerm ) ;
	String afterLastTerm = getAfterLastTerm ( ) ;
	String terminatorTerm = getTerminatorTerm ( ) ;
	String val3 = val2 . replaceAll ( afterLastTerm , terminatorTerm ) ;
	String allPackageTerm = getAllPackageTerm ( ) ;
	String blankTerm = getBlankTerm ( ) ;
	String val4 = val3 . replaceAll ( allPackageTerm , blankTerm ) ;
	String lastTerm = getLastTerm ( ) ;
	String val5 = val4 . replaceAll ( lastTerm , terminatorTerm ) ;
	String periodTerm = getPeriodTerm ( ) ;
	String preferredPeriodTerm = getPreferredPeriodTerm ( ) ;
	String val6 = val5 . replaceAll ( periodTerm , preferredPeriodTerm ) ;
	Logging logging = getLogging ( ) ;
	String logMessage = getLogMessage ( ) ;
	logging . finest ( this , logMessage , val3 , allPackageTerm , val4 ) ;
	return val6 ;
    }

    /**
     * For matching the end of the line.
     *
     * @return a regular expression
     **/
    private String getLastTerm ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object periodConstant = getPeriodConstant ( ) ;
	stringBuilder . append ( periodConstant ) ;
	Object wildcardConstant = getWildcardConstant ( ) ;
	stringBuilder . append ( wildcardConstant ) ;
	Object multipleConstant = getMultipleConstant ( ) ;
	stringBuilder . append ( multipleConstant ) ;
	Object terminatorTerm = getTerminatorTerm ( ) ;
	stringBuilder . append ( terminatorTerm ) ;
	Object endingConstant = getEndingConstant ( ) ;
	stringBuilder . append ( endingConstant ) ;
	String string = stringBuilder . toString ( ) ;
	return string ;
    }

    /**
     * For blanking out some stuff.
     *
     * @return an empty string
     **/
    private String getBlankTerm ( )
    {
	Object blankTerm = getBlankConstant ( ) ;
	String string = blankTerm . toString ( ) ;
	return string ;
    }

    /**
     * Matches the beginning of the line.
     *
     * @return a regular expression
     **/
    private String getBeforeFirstTerm ( )
    {
	Object startingConstant = getStartingConstant ( ) ;
	String string = startingConstant . toString ( ) ;
	return string ;
    }

    /**
     * For writing the package keyword.
     *
     * @return a string
     **/
    private String getPackageTerm ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object packageConstant = getPackageConstant ( ) ;
	stringBuilder . append ( packageConstant ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	String string = stringBuilder . toString ( ) ;
	return string ;
    }

    /**
     * Matches the very end of a line.
     *
     * @return a regular expression
     **/
    private String getAfterLastTerm ( )
    {
	Object endingConstant = getEndingConstant ( ) ;
	String string = endingConstant . toString ( ) ;
	return string ;
    }

    /**
     * The terminator " ;".
     *
     * @return a regular expression
     **/
    private String getTerminatorTerm ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	stringBuilder . append ( semicolonConstant ) ;
	String string = stringBuilder . toString ( ) ;
	return string ;
    }

    /**
     * Matches a package statement without a package.
     *
     * @return a regular expression
     **/
    private String getAllPackageTerm ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object startingConstant = getStartingConstant ( ) ;
	stringBuilder . append ( startingConstant ) ;
	Object packageTerm = getPackageTerm ( ) ;
	stringBuilder . append ( packageTerm ) ;
	Object notPeriodTerm = getNotPeriodTerm ( ) ;
	stringBuilder . append ( notPeriodTerm ) ;
	Object terminatorTerm = getTerminatorTerm ( ) ;
	stringBuilder . append ( terminatorTerm ) ;
	Object endingConstant = getEndingConstant ( ) ;
	stringBuilder . append ( endingConstant ) ;
	String string = stringBuilder . toString ( ) ;
	return string ;
    }

    /**
     * Gets a regular expression that matches anything
     * but a period.
     *
     * @return a regular expression
     **/
    private String getNotPeriodTerm ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object openNotConstant = getOpenNotConstant ( ) ;
	stringBuilder . append ( openNotConstant ) ;
	Object periodTerm = getPeriodTerm ( ) ;
	stringBuilder . append ( periodTerm ) ;
	Object closeNotConstant = getCloseNotConstant ( ) ;
	stringBuilder . append ( closeNotConstant ) ;
	Object multipleConstant = getMultipleConstant ( ) ;
	stringBuilder . append ( multipleConstant ) ;
	String string = stringBuilder . toString ( ) ;
	return string ;
    }

    /**
     * Gets a regular expression for a period.
     *
     * @return "."
     **/
    private String getPeriodTerm ( )
    {
	Object periodConstant = getPeriodConstant ( ) ;
	String string = periodConstant . toString ( ) ;
	return string ;
    }

    /**
     * Constructs my preferred form of period
     * " . ".
     *
     * @return " . "
     **/
    private String getPreferredPeriodTerm ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	stringBuilder . append ( spaceConstant ) ;
	Object wildcardConstant = getWildcardConstant ( ) ;
	stringBuilder . append ( wildcardConstant ) ;
	stringBuilder . append ( spaceConstant ) ;
	String string = stringBuilder . toString ( ) ;
	return string ;
    }

    /**
     * Constructs a new string builder.
     *
     * @return a new string builder
     **/
    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    /**
     * Gets a qualified name transformer.
     *
     * @return a transformer that converts names into qualified names
     **/
    @ UseConstructor ( QualifiedNameTransformer . class )
	abstract
	Transformer < ? extends String , ? super String >
	getQualifiedNameTransformer
	( ) ;

    /**
     * Returns the regular expression that matches
     * a period.
     *
     * @return an escaped regular expression
     **/
    @ UseStringConstant ( "\\." )
	abstract Object getPeriodConstant ( ) ;

    /**
     * Gets a period - which can match anything.
     *
     * @return a period
     **/
    @ UseStringConstant ( "." )
	abstract Object getWildcardConstant ( ) ;

    /**
     * Gets the regular expression matching
     * zero, one, or more of the previous.
     *
     * @return an asterix
     **/
    @ UseStringConstant ( "*" )
	abstract Object getMultipleConstant ( ) ;

    /**
     * Gets the regular expression matching the
     * beginning of a line.
     *
     * @return a caret
     **/
    @ UseStringConstant ( "^" )
	abstract Object getStartingConstant ( ) ;

    /**
     * Gets the regular expression matching the end
     * of a line.
     *
     * @return a dollar sign
     **/
    @ UseStringConstant ( "$" )
	abstract Object getEndingConstant ( ) ;

    /**
     * Gets the blank constant.
     *
     * @return a blank
     **/
    @ UseStringConstant ( "" )
	abstract Object getBlankConstant ( ) ;

    /**
     * Gets the space constant.
     *
     * @return a space
     **/
    @ UseStringConstant ( " " )
	abstract Object getSpaceConstant ( ) ;

    /**
     * Gets the semicolon constant.
     *
     * @return ";"
     **/
    @ UseStringConstant ( ";" )
	abstract Object getSemicolonConstant ( ) ;

    /**
     * Gets the package keyword.
     *
     * @return package
     **/
    @ UseStringConstant ( "package" )
	abstract Object getPackageConstant ( ) ;

    /**
     * Gets the opener for the not.
     *
     * @return the opener for the not
     **/
    @ UseStringConstant ( "[^" )
	abstract Object getOpenNotConstant ( ) ;

    /**
     * Gets the closer for the not.
     *
     * @return the closer for not
     **/
    @ UseStringConstant ( "]" )
	abstract Object getCloseNotConstant ( ) ;

    /**
     * Gets the logging.
     *
     * @return the logging
     **/
    @ UseConstructor ( Logging . class )
	abstract Logging getLogging ( ) ;

    /**
     * Gets the logging template.
     *
     * @return the logging template
     **/
    @ UseStringConstant ( "val3=? , allPackageTerm=? , val4=?" )
	abstract String getLogMessage ( ) ;
}
