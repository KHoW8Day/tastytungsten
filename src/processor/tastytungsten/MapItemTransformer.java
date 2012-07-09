
package tastytungsten ;

import java . util . Map ;

abstract class MapItemTransformer < K , V > implements Transformer < V , Map < ? extends K , ? extends V > >
{
    final V transform ( Map < ? extends K , ? extends V > map , @ UseParameter final K key )
    {
	V value = map . get ( key ) ;
	return value ;
    }
}

/**
23 	  	

     public final String transform ( final String value )

 24 	  	

     {

 25 	 0 	

         Transformer < ? extends String , ? super String > qualifiedNameTransformer = getQualifiedNameTransformer ( ) ;

 26 	 0 	

         String val1 = qualifiedNameTransformer . transform ( value ) ;

 27 	 0 	

         String beforeFirstTerm = getBeforeFirstTerm ( ) ;

 28 	 0 	

         String packageTerm = getPackageTerm ( ) ;

 29 	 0 	

         String val2 = val1 . replaceAll ( beforeFirstTerm , packageTerm ) ;

 30 	 0 	

         String afterLastTerm = getAfterLastTerm ( ) ;

 31 	 0 	

         String terminatorTerm = getTerminatorTerm ( ) ;

 32 	 0 	

         String val3 = val2 . replaceAll ( afterLastTerm , terminatorTerm ) ;

 33 	 0 	

         String allPackageTerm = getAllPackageTerm ( ) ;

 34 	 0 	

         String blankTerm = getBlankTerm ( ) ;

 35 	 0 	

         String val4 = val3 . replaceAll ( allPackageTerm , blankTerm ) ;

 36 	 0 	

         String lastTerm = getLastTerm ( ) ;

 37 	 0 	

         String val5 = val4 . replaceAll ( lastTerm , terminatorTerm ) ;

 38 	 0 	

         String periodTerm = getPeriodTerm ( ) ;

 39 	 0 	

         String preferredPeriodTerm = getPreferredPeriodTerm ( ) ;

 40 	 0 	

         String val6 = val5 . replaceAll ( periodTerm , preferredPeriodTerm ) ;

 41 	 0 	

         return val6 ;

 42 	  	

     }

 43 	  	

 

 44 	  	

     private String getLastTerm ( )

 45 	  	

     {

 46 	 0 	

         String lastTermFormat = getLastTermFormat ( ) ;

 47 	 0 	

         Object periodConstant = getPeriodConstant ( ) ;

 48 	 0 	

         Object wildcardConstant = getWildcardConstant ( ) ;

 49 	 0 	

         Object multipleConstant = getMultipleConstant ( ) ;

 50 	 0 	

         Object terminatorTerm = getTerminatorTerm ( ) ;

 51 	 0 	

         Object endingConstant = getEndingConstant ( ) ;

 52 	 0 	

         String string = format ( lastTermFormat , periodConstant , wildcardConstant , multipleConstant , terminatorTerm , endingConstant ) ;

 53 	 0 	

         return string ;

 54 	  	

     }

 55 	  	

 

 56 	  	

     private String getBlankTerm ( )

 57 	  	

     {

 58 	 0 	

         Object blankTerm = getBlankConstant ( ) ;

 59 	 0 	

         String string = blankTerm . toString ( ) ;

 60 	 0 	

         return string ;

 61 	  	

     }

 62 	  	

 

 63 	  	

     private String getBeforeFirstTerm ( )

 64 	  	

     {

 65 	 0 	

         Object startingConstant = getStartingConstant ( ) ;

 66 	 0 	

         String string = startingConstant . toString ( ) ;

 67 	 0 	

         return string ;

 68 	  	

     }

 69 	  	

 

 70 	  	

     private String getPackageTerm ( )

 71 	  	

     {

 72 	 0 	

         String packageTermFormat = getPackageTermFormat ( ) ;

 73 	 0 	

         Object packageConstant = getPackageConstant ( ) ;

 74 	 0 	

         Object spaceConstant = getSpaceConstant ( ) ;

 75 	 0 	

         String string = format ( packageTermFormat , packageConstant , spaceConstant ) ;

 76 	 0 	

         return string ;

 77 	  	

     }

 78 	  	

 

 79 	  	

     private String getAfterLastTerm ( )

 80 	  	

     {

 81 	 0 	

         Object endingConstant = getEndingConstant ( ) ;

 82 	 0 	

         String string = endingConstant . toString ( ) ;

 83 	 0 	

         return string ;

 84 	  	

     }

 85 	  	

 

 86 	  	

     private String getTerminatorTerm ( )

 87 	  	

     {

 88 	 0 	

         String terminatorTermFormat = getTerminatorTermFormat ( ) ;

 89 	 0 	

         Object spaceConstant = getSpaceConstant ( ) ;

 90 	 0 	

         Object semicolonConstant = getSemicolonConstant ( ) ;

 91 	 0 	

         String string = format ( terminatorTermFormat , spaceConstant , semicolonConstant ) ;

 92 	 0 	

         return string ;

 93 	  	

     }

 94 	  	

 

 95 	  	

     private String getAllPackageTerm ( )

 96 	  	

     {

 97 	 0 	

         String getAllPackageTermFormat = getAllPackageTermFormat ( ) ;

 98 	 0 	

         Object startingConstant = getStartingConstant ( ) ;

 99 	 0 	

         Object packageTerm = getPackageTerm ( ) ;

 100 	 0 	

         Object notPeriodTerm = getNotPeriodTerm ( ) ;

 101 	 0 	

         Object terminatorTerm = getTerminatorTerm ( ) ;

 102 	 0 	

         Object endingConstant = getEndingConstant ( ) ;

 103 	 0 	

         String string = format ( getAllPackageTermFormat , startingConstant , packageTerm , notPeriodTerm , terminatorTerm , endingConstant ) ;

 104 	 0 	

         return string ;

 105 	  	

     }

 106 	  	

 

 107 	  	

     private String getNotPeriodTerm ( )

 108 	  	

     {

 109 	 0 	

         String notPeriodTermFormat = getNotPeriodTermFormat ( ) ;

 110 	 0 	

         Object openNotConstant = getOpenNotConstant ( ) ;

 111 	 0 	

         Object periodTerm = getPeriodTerm ( ) ;

 112 	 0 	

         Object closeNotConstant = getCloseNotConstant ( ) ;

 113 	 0 	

         Object multipleConstant = getMultipleConstant ( ) ;

 114 	 0 	

         String string = format ( notPeriodTermFormat , openNotConstant , periodTerm , closeNotConstant , multipleConstant ) ;

 115 	 0 	

         return string ;

 116 	  	

     }

 117 	  	

 

 118 	  	

     private String getPeriodTerm ( )

 119 	  	

     {

 120 	 0 	

         Object periodConstant = getPeriodConstant ( ) ;

 121 	 0 	

         String string = periodConstant . toString ( ) ;

 122 	 0 	

         return string ;

 123 	  	

     }

 124 	  	

 

 125 	  	

     private String getPreferredPeriodTerm ( )

 126 	  	

     {

 127 	 0 	

         String preferredPeriodTermFormat = getPreferredPeriodTermFormat ( ) ;

 128 	 0 	

         Object spaceConstant = getSpaceConstant ( ) ;

 129 	 0 	

         Object wildcardConstant = getWildcardConstant ( ) ;

 130 	 0 	

         String string = format ( preferredPeriodTermFormat , spaceConstant , wildcardConstant , spaceConstant ) ;

 131 	 0 	

         return string ;

 132 	  	

     }

 133 	  	

 

 134 	  	

     @ UseStringConstant ( "%s%s" )

 135 	  	

         abstract String getTerminatorTermFormat ( ) ;

 136 	  	

 

 137 	  	

     @ UseStringConstant ( "%s%s" )

 138 	  	

         abstract String getPackageTermFormat ( ) ;

 139 	  	

 

 140 	  	

     @ UseStringConstant ( "%s%s%s%s%s" )

 141 	  	

         abstract String getLastTermFormat ( ) ;

 142 	  	

 

 143 	  	

     @ UseStringConstant ( "%s%s%s%s%s" )

 144 	  	

         abstract String getAllPackageTermFormat ( ) ;

 145 	  	

 

 146 	  	

     @ UseStringConstant ( "%s%s%s%s" )

 147 	  	

         abstract String getNotPeriodTermFormat ( ) ;

 148 	  	

 

 149 	  	

     @ UseStringConstant ( "%s%s%s" )

 150 	  	

         abstract String getPreferredPeriodTermFormat ( ) ;

 151 	  	

 

 152 	  	

     @ UseStaticMethod ( String . class )

 153 	  	

         abstract String format ( String format , Object ... arguments ) ;

 154 	  	

 

 155 	  	

     @ UseConstructor ( QualifiedNameTransformer . class )

 156 	  	

         abstract Transformer < ? extends String , ? super String > getQualifiedNameTransformer ( ) ;

 157 	  	

 

 158 	  	

     @ UseStringConstant ( "\\." )

 159 	  	

         abstract Object getPeriodConstant ( ) ;

 160 	  	

 

 161 	  	

     @ UseStringConstant ( "." )

 162 	  	

         abstract Object getWildcardConstant ( ) ;

 163 	  	

 

 164 	  	

     @ UseStringConstant ( "*" )

 165 	  	

         abstract Object getMultipleConstant ( ) ;

 166 	  	

 

 167 	  	

     @ UseStringConstant ( "^" )

 168 	  	

         abstract Object getStartingConstant ( ) ;

 169 	  	

 

 170 	  	

     @ UseStringConstant ( "$" )

 171 	  	

         abstract Object getEndingConstant ( ) ;

 172 	  	

 

 173 	  	

     @ UseStringConstant ( "" )

 174 	  	

         abstract Object getBlankConstant ( ) ;

 175 	  	

 

 176 	  	

     @ UseStringConstant ( " " )

 177 	  	

         abstract Object getSpaceConstant ( ) ;

 178 	  	

 

 179 	  	

     @ UseStringConstant ( ";" )

 180 	  	

         abstract Object getSemicolonConstant ( ) ;

 181 	  	

 

 182 	  	

     @ UseStringConstant ( "package" )

 183 	  	

         abstract Object getPackageConstant ( ) ;

 184 	  	

 

 185 	  	

     @ UseStringConstant ( "[^" )

 186 	  	

         abstract Object getOpenNotConstant ( ) ;

 187 	  	

 

 188 	  	

     @ UseStringConstant ( "]" )

 189 	  	

         abstract Object getCloseNotConstant ( ) ;
**/