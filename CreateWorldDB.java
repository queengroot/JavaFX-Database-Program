import java.sql.*;   // Needed for JDBC classes

/**
 * This program creates the CoffeeDB database.
 */
public class CreateWorldDB
{
   public static void main(String[] args)
   {
      // Create a named constant for the URL.
      // NOTE: This value is specific for Java DB.
      final String DB_URL = "jdbc:derby:WorldDB;create=true";
      
      try
      {
         // Create a connection to the database.
         Connection conn =
                DriverManager.getConnection(DB_URL);
					 
			// If the DB already exists, drop the tables.
			dropTables(conn);
			
			// Build the Coffee table.
			buildCityTable(conn);
			
			// Build the Customer table.
			buildCountryTable(conn);
			
			// Build the UnpaidInvoice table.
			buildLanguageTable(conn);

         // Close the connection.
         conn.close();
      }
      catch (Exception ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
   }
	
	/**
	 * The dropTables method drops any existing
	 * in case the database already exists.
	 */
	public static void dropTables(Connection conn)
	{
		System.out.println("Checking for existing tables.");
		
		try
		{
			// Get a Statement object.
			Statement stmt  = conn.createStatement();

			try
			{
	         // Drop the Language table.
	         stmt.execute("DROP TABLE Language");
				System.out.println("Language table dropped.");
			}
			catch(SQLException ex)
			{
				// No need to report an error.
				// The table simply did not exist.
			}

			try
			{
	         // Drop the Customer table.
	         stmt.execute("DROP TABLE Country");
				System.out.println("Country table dropped.");				
			}
			catch(SQLException ex)
			{
				// No need to report an error.
				// The table simply did not exist.
			}

			try
			{
	         // Drop the Coffee table.
	         stmt.execute("DROP TABLE City");
				System.out.println("City table dropped.");
			}
			catch(SQLException ex)
			{
				// No need to report an error.
				// The table simply did not exist.
			}
		}
  		catch(SQLException ex)
		{
	      System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/**
	 * The buildCityTable method creates the
	 * Coffee table and adds some rows to it.
	 */
	public static void buildCityTable(Connection conn)
	{
		try
		{
			System.out.println("Build City Table");
         // Get a Statement object.
         Statement stmt = conn.createStatement();
         
			// Create the table.
			stmt.execute("CREATE TABLE City (" +
					  "COUNTRYCODE CHAR(3) NOT NULL PRIMARY KEY, " +
   				      "CITYNAME CHAR(35), " +
                      "POPULATION INT" +
                      ")");
			
			System.out.println("Successful create City table");
			
			// Insert row #1.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'SLV', " +
                      "'San Salvador', " +                     
                      "415346 )" );
			
			

			
			// Insert row #2.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'ARG', " +
                    "'Bueno Aires', " +                   
                    "2982146 )" );

			// Insert row #3.
			stmt.execute("INSERT INTO City VALUES ( " +
					"'GBR', " +
                    "'London', " +                    
                    "7285000 )" );

			// Insert row #5.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'EGY', " +
                    "'Cairo', " +                   
                    "6789479 )" );

			// Insert row #6.
			stmt.execute("INSERT INTO City VALUES ( " +
				  "'ESP', " +
                  "'Madrid', " +                
                  "2879052 )" );

			// Insert row #7.
			stmt.execute("INSERT INTO City VALUES ( " +
				  "'HTI', " +
                  "'Port-au-Prince', " +                  
                  "884472 )" );

			// Insert row #8.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'FRA', " +
                  "'Paris', " +                 
                  "2125246 )" );

			// Insert row #9.
			stmt.execute("INSERT INTO City VALUES ( " +
					"'RUS', " +
                  "'Moscow', " +                  
                  "8389200 )" );

			// Insert row #10.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'CHN', " +
                  "'Shanghai', " +
                  "9696300 )" );

			// Insert row #11.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'NOR', " +
                  "'Oslo', " +                 
                  "508726 )" );

			// Insert row #12.
			stmt.execute("INSERT INTO City VALUES ( " +
					"'ZAF', " +
                  "'Johannesburg', " +                  
                  "756653 )" );

			// Insert row #13.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'USA', " +
                  "'New York', " +                 
                  "8008278 )" );

			// Insert row #14.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'ARE', " +
                  "'Abu Dhabi', " +                 
                  "398695 )" );

			// Insert row #15.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'AUS', " +
                  "'Sydney', " +                 
                  "3276207 )" );

			// Insert row #16.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'THA', " +
                  "'Bangkok', " +                 
                  "6320174 )" );
			
			// Insert row #17.
			stmt.execute("INSERT INTO City VALUES ( " +
					 "'BHS', " +
                  "'Nassau', " +                 
                  "172000 )" );
							 
			System.out.println("City table created.");
		}
		catch (SQLException ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
	}

	/**
	 * The buildCountryTable method creates the
	 * Country table and adds some rows to it.
	 */
	public static void buildCountryTable(Connection conn)
	{
      try
      {
    	  
    	  System.out.println("Build Country Table");
         // Get a Statement object.
         Statement stmt = conn.createStatement();
         
         // Create the table.
         stmt.execute("CREATE TABLE Country" +
            "( CountryCode CHAR(3) NOT NULL PRIMARY KEY, " +
            "  Name CHAR(25),"    +
            "  Continent CHAR(25)," +
            "  Population INT,"    +
            "  LifeExpectancy FLOAT )");
         
         System.out.println("Successful build Country Table");

         // Add some rows to the new table.
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
               "('SLV', 'El Salvador', 'South America'," +
               "37032000, 75.1)");
         
         System.out.println("Successful populate 1st Country");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('ARG', 'Argentina', 'North America'," +
                 "6276000, 69.7)");

         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('GBR', 'United Kingdom', 'Europe'," +
                 "59623400, 77.7)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('EGY', 'Egypt', 'Africa'," +
                 "68470000, 63.3)");
					
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('ESP', 'Spain', 'Europe'," +
                 "39441700, 78.8)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('HTI', 'Haiti', 'North America'," +
                 "8222000, 49.2)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('FRA', 'France', 'Europe'," +
                 "59225700, 78.8)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('RUS', 'Russian Federation', 'Europe'," +
                 "146934000, 67.2)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('CHN', 'China', 'Asia'," +
                 "1277558000, 71.4)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('NOR', 'Norway', 'Europe'," +
                 "4478500, 78.7)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('ZAF', 'South Africa', 'Africa'," +
                 "40377000, 51.1)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('USA', 'United States', 'North America'," +
                 "278357000, 77.1)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('ARE', 'United Arab Emirates', 'Asia'," +
                 "2441000, 74.1)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('AUS', 'Australia', 'Oceania'," +
                 "18886000, 79.8)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('THA', 'Thailand', 'Asia'," +
                 "61399000, 68.6)");
         
         stmt.executeUpdate("INSERT INTO COUNTRY VALUES" +
                 "('BHS', 'Bahamas', 'North America'," +
                 "307000, 71.1)");
         
			System.out.println("Country table created.");
		}
		catch (SQLException ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
	}

	/**
	 * The buildLanguageTable method creates
	 * the Language table.
	 */

	public static void buildLanguageTable(Connection conn)
	{
      try
      {
    	  
    	  System.out.println("Build Language Table");
         // Get a Statement object.
         Statement stmt = conn.createStatement();
			
         // Create the table.
         stmt.execute("CREATE TABLE Language " +
				"( CountryCode CHAR(3) NOT NULL REFERENCES Country(CountryCode), "+
				"  Language CHAR(20) )");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('SLV', 'Spanish')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('ARG', 'Spanish')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('GBR', 'English')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('EGY', 'Arabic')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('ESP', 'Spanish')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('HTI', 'French')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('FRA', 'French')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('RUS', 'Russian')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('CHN', 'Chinese')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('NOR', 'Norwegian')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('ZAF', 'Zulu')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('USA', 'English')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('ARE', 'Arabic')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('AUS', 'English')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('THA', 'Thai')");
         
         stmt.executeUpdate("INSERT INTO LANGUAGE VALUES" +
                 "('BHS', 'Creole English')");
                 
								
				
			System.out.println("Language table created.");
		}
		catch (SQLException ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
	}
}





