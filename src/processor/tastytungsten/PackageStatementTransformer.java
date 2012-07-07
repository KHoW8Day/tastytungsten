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

abstract class PackageStatementTransformer implements Transformer < String , String >
{
    public final String transform ( final String value )
    {
	Transformer < ? extends String , ? super String > qualifiedNameTransformer = getQualifiedNameTransformer ( ) ;
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
	return val6 ;
    }

    private String getLastTerm ( )
    {
	String lastTermFormat = getLastTermFormat ( ) ;
	Object periodConstant = getPeriodConstant ( ) ;
	Object wildcardConstant = getWildcardConstant ( ) ;
	Object multipleConstant = getMultipleConstant ( ) ;
	Object terminatorTerm = getTerminatorTerm ( ) ;
	Object endingConstant = getEndingConstant ( ) ;
	String string = format ( lastTermFormat , periodConstant , wildcardConstant , multipleConstant , terminatorTerm , endingConstant ) ;
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
	String packageTermFormat = getPackageTermFormat ( ) ;
	Object packageConstant = getPackageConstant ( ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	String string = format ( packageTermFormat , packageConstant , spaceConstant ) ;
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
	String terminatorTermFormat = getTerminatorTermFormat ( ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	Object semicolonConstant = getSemicolonConstant ( ) ;
	String string = format ( terminatorTermFormat , spaceConstant , semicolonConstant ) ;
	return string ;
    }

    private String getAllPackageTerm ( )
    {
	String getAllPackageTermFormat = getAllPackageTermFormat ( ) ;
	Object startingConstant = getStartingConstant ( ) ;
	Object packageTerm = getPackageTerm ( ) ;
	Object notPeriodTerm = getNotPeriodTerm ( ) ;
	Object terminatorTerm = getTerminatorTerm ( ) ;
	Object endingConstant = getEndingConstant ( ) ;
	String string = format ( getAllPackageTermFormat , startingConstant , packageTerm , notPeriodTerm , terminatorTerm , endingConstant ) ;
	return string ;
    }

    private String getNotPeriodTerm ( )
    {
	String notPeriodTermFormat = getNotPeriodTermFormat ( ) ;
	Object openNotConstant = getOpenNotConstant ( ) ;
	Object periodTerm = getPeriodTerm ( ) ;
	Object closeNotConstant = getCloseNotConstant ( ) ;
	Object multipleConstant = getMultipleConstant ( ) ;
	String string = format ( notPeriodTermFormat , openNotConstant , periodTerm , closeNotConstant , multipleConstant ) ;
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
	String preferredPeriodTermFormat = getPreferredPeriodTermFormat ( ) ;
	Object spaceConstant = getSpaceConstant ( ) ;
	Object wildcardConstant = getWildcardConstant ( ) ;
	String string = format ( preferredPeriodTermFormat , spaceConstant , wildcardConstant , spaceConstant ) ;
	return string ;
    }

    @ UseStringConstant ( "%s%s" )
	abstract String getTerminatorTermFormat ( ) ;

    @ UseStringConstant ( "%s%s" )
	abstract String getPackageTermFormat ( ) ;

    @ UseStringConstant ( "%s%s%s%s%s" )
	abstract String getLastTermFormat ( ) ;

    @ UseStringConstant ( "%s%s%s%s%s" )
	abstract String getAllPackageTermFormat ( ) ;

    @ UseStringConstant ( "%s%s%s%s" )
	abstract String getNotPeriodTermFormat ( ) ;

    @ UseStringConstant ( "%s%s%s" )
	abstract String getPreferredPeriodTermFormat ( ) ;

    @ UseStaticMethod ( String . class )
	abstract String format ( String format , Object ... arguments ) ;

    @ UseConstructor ( QualifiedNameTransformer . class )
	abstract Transformer < ? extends String , ? super String > getQualifiedNameTransformer ( ) ;

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

    @ UseStringConstant ( "[^" )
	abstract Object getOpenNotConstant ( ) ;

    @ UseStringConstant ( "]" )
	abstract Object getCloseNotConstant ( ) ;
}
