
package tastytungsten ;

import java . util . HashMap ;
import java . util . Map ;

abstract class PackageStatementStager implements Stager < String , String >
{
    @ Override
	public String stage ( String value )
	{
	    Stager < ? extends String , ? super String > qualifiedNameStager = getQualifiedNameStager ( ) ;
	    String val0 = qualifiedNameStager . stage ( value ) ;
	    String lastTerm = getLastTerm ( ) ;
	    String blankTerm = getBlankTerm ( ) ;
	    String val1 = val0 . replaceAll ( lastTerm , blankTerm ) ;
	    String beforeFirstTerm = getBeforeFirstTerm ( ) ;
	    String packageTerm = getPackageTerm ( ) ;
	    String val2 = val1 . replaceAll ( beforeFirstTerm , packageTerm ) ;
	    String afterLastTerm = getAfterLastTerm ( ) ;
	    String terminatorTerm = getTerminatorTerm ( ) ;
	    String val3 = val2 . replaceAll ( afterLastTerm , terminatorTerm ) ;
	    String allPackageTerm = getAllPackageTerm ( ) ;
	    String val4 = val3 . replaceAll ( allPackageTerm , blankTerm ) ;
	    String periodTerm = getPeriodTerm ( ) ;
	    String preferredPeriodTerm = getPreferredPeriodTerm ( ) ;
	    String val5 = val4 . replaceAll ( periodTerm , preferredPeriodTerm ) ;
	    Logging logging = getLogging ( ) ;
	    String logMessage = getLogMessage ( ) ;
	    logging . finest ( this , logMessage , allPackageTerm ) ;
	    return val5 ;
	}

    private String getLastTerm ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object periodConstant = getPeriodConstant ( ) ;
	stringBuilder . append ( periodConstant ) ;
	Object wildcardConstant = getWildcardConstant ( ) ;
	stringBuilder . append ( wildcardConstant ) ;
	Object multipleConstant = getMultipleConstant ( ) ;
	stringBuilder . append ( multipleConstant ) ;
	Object endingConstant = getEndingConstant ( ) ;
	stringBuilder . append ( endingConstant ) ;
	String string = stringBuilder . toString ( ) ;
	return string ;
    }

    private String getBlankTerm ( )
    {
	Object blankTerm = getBlankConstant ( ) ;
	String string = blankTerm . toString ( ) ;
	return string ;
    }

    private String getBeforeFirstTerm ( )
    {
	Object startingConstant = getStartingConstant ( ) ;
	String string = startingConstant . toString ( ) ;
	return string ;
    }

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

    private String getAfterLastTerm ( )
    {
	Object endingConstant = getEndingConstant ( ) ;
	String string = endingConstant . toString ( ) ;
	return string ;
    }

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

    private String getAllPackageTerm ( )
    {
	StringBuilder stringBuilder = getStringBuilder ( ) ;
	Object startingConstant = getStartingConstant ( ) ;
	stringBuilder . append ( startingConstant ) ;
	Object packageTerm = getPackageTerm ( ) ;
	stringBuilder . append ( packageTerm ) ;
	Object terminatorTerm = getTerminatorTerm ( ) ;
	stringBuilder . append ( terminatorTerm ) ;
	Object endingConstant = getEndingConstant ( ) ;
	stringBuilder . append ( endingConstant ) ;
	String string = stringBuilder . toString ( ) ;
	return string ;
    }

    private String getPeriodTerm ( )
    {
	Object periodConstant = getPeriodConstant ( ) ;
	String string = periodConstant . toString ( ) ;
	return string ;
    }

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

    @ UseConstructor ( StringBuilder . class )
	abstract StringBuilder getStringBuilder ( ) ;

    @ UseConstructor ( QualifiedNameStager . class )
	abstract Stager < ? extends String , ? super String > getQualifiedNameStager ( ) ;

    @ UseStringConstant ( "\\." )
	abstract Object getPeriodConstant ( ) ;

    @ UseStringConstant ( "." )
	abstract Object getWildcardConstant ( ) ;

    @ UseStringConstant ( "*" )
	abstract Object getMultipleConstant ( ) ;

    @ UseStringConstant ( "^" )
	abstract Object getStartingConstant ( ) ;

    @ UseStringConstant ( "$" )
	abstract Object getEndingConstant ( ) ;

    @ UseStringConstant ( "" )
	abstract Object getBlankConstant ( ) ;

    @ UseStringConstant ( " " )
	abstract Object getSpaceConstant ( ) ;

    @ UseStringConstant ( ";" )
	abstract Object getSemicolonConstant ( ) ;

    @ UseStringConstant ( "package" )
	abstract Object getPackageConstant ( ) ;

    @ UseConstructor ( Logging . class )
	abstract Logging getLogging ( ) ;

    @ UseStringConstant ( "allPackageTerm=?" )
	abstract String getLogMessage ( ) ;
}
