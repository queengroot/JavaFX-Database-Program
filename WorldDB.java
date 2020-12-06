//Kate Blunt
//COP2552
//Project 7 -Databases with JavaFx and JavaDB
//November 30th, 2020
//TO DO
//set up database and get it working properly
//create layout of the GUI with JavaFX
//display correct information from database into the listview.
//update the textarea box accordingly depending upon what is clicked and selected
//add a clear and exit button for user ease

//necessary imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class WorldDB extends Application{

	public static void main(String[] args) {
		launch(args);
	}
		
	//these are needed as static so they keep the previous value	
	public static int tempPop = 0;
	public static double tempLife = 0;
		
		
@Override
public void start (Stage primaryStage)
{
			//final String DB_URL = "jdbc:derby:WorldDB";
			//Connection conn = DriverManager.getConnection(DB_URL);
			
	
				//Instructions are in a pop-up message. This should really be changed since most users will just click the box and exit out of it
				// If I added the instructions into my gridpane they looked really odd. 
	
	 			String instructionsLabel = "Click on any of the three radio buttons to display a list of either cities, countries, or "
		   		+ "languages.\nTo find out more information about any item listed, check the checkboxes and select an item from the provided list.";
				JOptionPane.showMessageDialog(null, instructionsLabel);
				// Create a named constant for the URL.
			    // NOTE: This value is specific for Java DB.
			    final String DB_URL = "jdbc:derby:WorldDB";
			    
			   try
			    {
			      // Create a connection to the database.
			     Connection conn = DriverManager.getConnection(DB_URL);
			     
			     
			     //System.out.println("Connection created to WorldDB.");
			          
			          // Close the connection.
			          conn.close();
			           //System.out.println("Connection closed.");
			         }
			         catch(Exception ex)
			         {
			           System.out.println("ERROR: " + ex.getMessage());
			         }
			   
			 //javafx for database -- sometimes would crash database, be careful with how this is set up
			   ListView<String> countriesListView = new ListView<>();
			   countriesListView.setPrefWidth(100);
			   countriesListView.setPrefHeight(80);
			   
			   Connection conn;
			try {
				conn = DriverManager.getConnection(DB_URL);
				getInfo(conn, countriesListView);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			   
			//GUI set up
			   primaryStage.setTitle("World");
			   primaryStage.show();
			   RadioButton radioCityNames = new RadioButton("Display City Names");
			   RadioButton radioCountryNames = new RadioButton("Display Country Names");
			   RadioButton radioLanguages = new RadioButton("Display Languages");
			   
			   //add radio buttons to toggle group
			   ToggleGroup radioGroup = new ToggleGroup();
			   radioCityNames.setToggleGroup(radioGroup);
			   radioCountryNames.setToggleGroup(radioGroup);
			   radioLanguages.setToggleGroup(radioGroup); 
			   
			   //initially have city names checked
			   radioCityNames.setSelected(true);
			   
			   //adding event handlers so if a radio button is selected, it populates the ListView correctly
			   radioCityNames.setOnAction(event ->
				{
					  try
					    {
					      // Create a connection to the database.
					     Connection connect = DriverManager.getConnection(DB_URL);
					     getCityNames(connect, countriesListView);
					     
					    
					     
					     
					     //System.out.println("Connection created to WorldDB.");
					          
					          // Close the connection.
					          connect.close();
					           //ystem.out.println("Connection closed.");
					         }
					         catch(Exception ex)
					         {
					           System.out.println("ERROR: " + ex.getMessage());
					         }
					
			
				});
			   
			   radioCountryNames.setOnAction(event ->
				{
					  try
					    {
					      // Create a connection to the database.
					     Connection connect = DriverManager.getConnection(DB_URL);
					     getCountryNames(connect, countriesListView);
					     
					    // System.out.println("Connection created to WorldDB.");
					          
					          // Close the connection.
					          connect.close();
					           //System.out.println("Connection closed.");
					         }
					         catch(Exception ex)
					         {
					           System.out.println("ERROR: " + ex.getMessage());
					         }
					
			
				});
			   
			   radioLanguages.setOnAction(event ->
				{
					  try
					    {
					      // Create a connection to the database.
					     Connection connect = DriverManager.getConnection(DB_URL);
					     getLanguages(connect, countriesListView);
					     
					     //System.out.println("Connection created to WorldDB.");
					          
					          // Close the connection.
					          connect.close();
					           //System.out.println("Connection closed.");
					         }
					         catch(Exception ex)
					         {
					           System.out.println("ERROR: " + ex.getMessage());
					         }
					
			
				});
			   
			  
			   
			   //declare the five checkboxes
			   CheckBox checkCountryName = new CheckBox("Display Country Name");
			   CheckBox checkContinent = new CheckBox("Display Continent");
			   CheckBox checkPopulation = new CheckBox("Display Population");
			   CheckBox checkLifeExpectancy = new CheckBox("Display Life Expectancy");
			   CheckBox checkLanguage = new CheckBox("Display Language");
			   
			  
			   //TextArea field
			   TextArea textArea = new TextArea("");
			   textArea.setPrefColumnCount(20);
			   textArea.setPrefRowCount(5);
			   
			   //set up text wrapping so the text wraps around
			   textArea.setWrapText(true);
			   
			
			   
			   //event handler for listView -- if user selects one of the cities
			   countriesListView.getSelectionModel().selectedItemProperty().addListener(event ->
					   {
						   
						   
						   //get whatever is selected or highlighted in the list view
						   String selected = countriesListView.getSelectionModel().getSelectedItem();		
						   
						   
						   //depending on the radio button clicked, it will populate the listview automatically 
						   //based on the selection already made
						   if (selected != null)
						   {
						   //determine if checkboxes are selected
						   if(radioCountryNames.isSelected())
						   {
							   //display country name
							   textArea.setText("Country Name: " + selected);
						   }
						   
						   if(radioCityNames.isSelected())
						   {
							   //display city name
							   textArea.setText("City Name: " + selected);
						   }
						   if(radioLanguages.isSelected())
						   {
							   
							   //String selected = countriesListView.getSelectionModel().getSelectedItem();
							   
							   
							   //display Language
							   textArea.setText("Language: " + selected);
							   
						   }
						   }
						   
						   //need to check is selected is null because if you switch radio buttons it causes errors
						   //for each of the checkboxes we need to check to see which radio button is selected so we can display the 
						   //correct information
						   if (checkCountryName.isSelected() && selected != null)
						   {
							   try
							    {
							      // Create a connection to the database.
							     Connection connect = DriverManager.getConnection(DB_URL);
							     
							     if(radioCityNames.isSelected())
							     {
							     int num =1;
							     getCountryCode(connect, selected, textArea, num);
							     }
							     
							     else if(radioLanguages.isSelected())
							     {
							    	 
							    	 int num = 4;
							    	 getLangCharCode(connect, selected, textArea, num);
							     }
							     
							     //System.out.println("Connection created to WorldDB.");
							          
							          // Close the connection.
							          connect.close();
							           //System.out.println("Connection closed.");
							         }
							         catch(Exception ex)
							         {
							           System.out.println("ERROR: " + ex.getMessage());
							         }
						   }
						   
						   if(checkContinent.isSelected() && selected != null)
						   {
							   try
							    {
							      // Create a connection to the database.
							     Connection connect = DriverManager.getConnection(DB_URL);
							     
							     if(radioCountryNames.isSelected())
							     {
							     getContinent(connect, selected, textArea);
							     }
							     else if (radioCityNames.isSelected())
							     {
							    	 int num = 2;
							    	 getCountryCode(connect, selected, textArea, num);
							     }
							     
							     //languages button selected
							     else if(radioLanguages.isSelected()) 
							     {
							    	 int num = 1;
							    	 getLangCharCode(connect, selected, textArea, num);
							     }
							     
							     //System.out.println("Connection created to WorldDB.");
							          
							          // Close the connection.
							          connect.close();
							           //System.out.println("Connection closed.");
							         }
							         catch(Exception ex)
							         {
							           System.out.println("ERROR: " + ex.getMessage());
							         }
						   }
						   
						   if(checkPopulation.isSelected() && selected != null)
						   {
							   try
							    {
							      // Create a connection to the database.
							     Connection connect = DriverManager.getConnection(DB_URL);
							     int num = 2;
							     if(radioCountryNames.isSelected())
							     {
							     getCountryPopulation(connect, selected, textArea);
							     }
							     else if (radioCityNames.isSelected()){
							    	 getCityPopulation(connect, selected, textArea);
							    	 }
							     else if(radioLanguages.isSelected())
							     {
							    	 getLangCharCode(connect, selected, textArea, num);
							     }
							     
							     
							     //System.out.println("Connection created to WorldDB.");
							          
							          // Close the connection.
							          connect.close();
							           //System.out.println("Connection closed.");
							         }
							         catch(Exception ex)
							         {
							           System.out.println("ERROR: " + ex.getMessage());
							         }
							   
						   }
						   
						   if(checkLifeExpectancy.isSelected() && selected != null)
						   {
							   try
							    {
							      // Create a connection to the database.
							     Connection connect = DriverManager.getConnection(DB_URL);
							     
							     if(radioCountryNames.isSelected())
							     {
							     getLifeExpectancy(connect, selected, textArea);
							     }
							     else if(radioCityNames.isSelected())
							     {
							    	 int num = 3;
							    	getCountryCode(connect, selected, textArea, num);
							     }
							     
							     else if(radioLanguages.isSelected()) 
							     {
							    	 int num = 3;
							    	 getLangCharCode(connect, selected, textArea, num);
							     }
							     //System.out.println("Connection created to WorldDB.");
							          
							          // Close the connection.
							          connect.close();
							           //System.out.println("Connection closed.");
							         }
							         catch(Exception ex)
							         {
							           System.out.println("ERROR: " + ex.getMessage());
							         }
							   
						   }
						   
						   if(checkLanguage.isSelected() && selected != null)
						   {
							   try
							    {
							      // Create a connection to the database.
							     Connection connect = DriverManager.getConnection(DB_URL);
							     if(radioCountryNames.isSelected())
							     {
							     getCharCode(connect, selected, textArea);
							     }
							     
							     if(radioCityNames.isSelected())
							     {
							    	 getCharCityLanguage(connect, selected, textArea);
							     }
							     
							     //System.out.println("Connection created to WorldDB.");
							          
							          // Close the connection.
							          connect.close();
							           //System.out.println("Connection closed.");
							         }
							         catch(Exception ex)
							         {
							           System.out.println("ERROR: " + ex.getMessage());
							         }
						   }
						   
						  
						   
						   
						
					   
					   
					   });
			   
			   
			   //buttons to exit the application and clear the application
			   Button exitButton = new Button("Exit");
			   Button clearButton = new Button("Clear");
			   
			   exitButton.setOnAction(event ->
			   {
				  //exit out of the application
				   System.exit(0);
				   Platform.exit();
				  
				   
			   });
			   
			   //set up event handler a little bit different than last time
			   clearButton.setOnAction(event ->
			   { 
				   //clear all the fiels, listview, textarea, radio buttons, and checkboxes
				   countriesListView.getItems().clear();
				   textArea.setText("");
				   radioCityNames.setSelected(false);
				   radioCountryNames.setSelected(false);
				   radioLanguages.setSelected(false);
				   
				   checkCountryName.setSelected(false);
				   checkContinent.setSelected(false);
				   checkPopulation.setSelected(false);
				   checkLifeExpectancy.setSelected(false);
				   checkLanguage.setSelected(false);
		
			   });
			   
			   //create a Label control
			   Label titleLabel = new Label("World Demographics Program");
			  
			   
			   //image one 
			   Image imageOne = new Image("file:britishflag.png");
			   
			   //display the image one
			   ImageView imageViewOne = new ImageView(imageOne);
			   
			   //set width and height of image
				imageViewOne.setFitWidth(150);
				imageViewOne.setFitHeight(100);
				
				//image two
				 Image imageTwo = new Image("file:austflag.png");
				   
				 //display the image two 
				 ImageView imageViewTwo = new ImageView(imageTwo);
				   
				 //set width and height of image
				imageViewTwo.setFitWidth(150);
				imageViewTwo.setFitHeight(100);
			   
				//Need to study the animations and css more. 
				titleLabel.setFont(new Font("Arial", 18));
				
				//gridpane to display everything on the GUI
			   GridPane gridpane = new GridPane();
			   
			  
			   //set space between column and rows for the gridpane
			   gridpane.setHgap(5);
			   gridpane.setVgap(10);
			   gridpane.setPadding(new Insets(20));
			   
			   gridpane.add(imageViewOne, 0, 0);
			   gridpane.add(imageViewTwo, 3, 0);
			   gridpane.add(titleLabel, 2, 0);
			   
			   gridpane.add(countriesListView, 0, 1);
			   gridpane.add(radioCityNames, 0, 2);
			   gridpane.add(radioCountryNames, 0, 3);
			   gridpane.add(radioLanguages, 0, 4);
			   gridpane.add(checkCountryName, 2, 2);
			   gridpane.add(checkContinent, 2, 3);
			   gridpane.add(checkPopulation, 2, 4);
			   gridpane.add(checkLifeExpectancy, 2,5);
			   gridpane.add(checkLanguage, 2, 6);
			   gridpane.add(textArea, 2, 1);
			   gridpane.add(clearButton, 3, 1);
			   gridpane.add(exitButton, 3, 7);
			  
			   
			   //set size of listview
			   countriesListView.setPrefSize(120, 100);
			 
			   //add the gridpane to the scene
			   Scene scene = new Scene(gridpane,600, 450);
				
			primaryStage.setScene(scene);
				
			   
}

//this populates the with the city names
public void getInfo(Connection conn, ListView<String> countriesListView)
{
	
	try {
		
		ArrayList<String> cityName = new ArrayList<String>();
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		String sqlStatement = "SELECT cityName FROM City";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		while (result.next())
		{
			
			String next = result.getString("cityName");
			cityName.add(next);
			
			
			//System.out.println(result.getString("cityName"));
		}
		
		Collections.sort(cityName);
		countriesListView.getItems().addAll(cityName);
		stmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
}



//start of the program
public static void getCityNames(Connection conn, ListView<String> countriesListView)
{
	
	try {
		ArrayList<String> cityName = new ArrayList<String>();
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		String sqlStatement = "SELECT cityName FROM City";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		while (result.next())
		{
			
			String next = result.getString("cityName");
			cityName.add(next);
			
			
			//System.out.println(result.getString("cityName"));
		}
		
		Collections.sort(cityName);
		countriesListView.getItems().setAll(cityName);
		stmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

//this handles if the radio button that says country name has been clicked. It fills the listview appropriately
public static void getCountryNames(Connection conn, ListView<String> countriesListView)
{
	
	try {
		ArrayList<String> countryName = new ArrayList<String>();
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		String sqlStatement = "SELECT Name FROM Country";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		while (result.next())
		{
			
			String next = result.getString("Name");
			countryName.add(next);
			
			
			//System.out.println(result.getString("Name"));
		}
		
		Collections.sort(countryName);
		countriesListView.getItems().setAll(countryName);
		stmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}


//this is for if the radio button that says languages is clicked. It fills the listview with the languages
//Distinct is used so we do not get double entries into the listview
public static void getLanguages(Connection conn, ListView<String> countriesListView)
{
	
	try {
		ArrayList<String> countryLang = new ArrayList<String>();
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		String sqlStatement = "SELECT DISTINCT language FROM Language";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		while (result.next())
		{
			
			String next = result.getString("language");
			
			countryLang.add(next);
		
			
			//System.out.println(result.getString("language"));
		}
		
		Collections.sort(countryLang);
		countriesListView.getItems().setAll(countryLang);
		stmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}


//The radio buttons up for country name and city were straight for
public static void getContinent(Connection conn, String selected, TextArea textArea)
{
	String continent = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT continent FROM Country WHERE Name = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
			
		continent = result.getString("continent");
		//System.out.println(continent);
			
			

		//System.out.println(result.getString("continent"));
		
		String previous = textArea.getText();
		textArea.setText(previous +"\nContinent: " + continent);
		stmt.close();
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}

public static void getCountryPopulation(Connection conn, String selected, TextArea textArea)
{
	//doing country right now
	//String population = "";
	int populationCountry = 0;
	
	try {
		
		//works and tested
		//System.out.println("Made it" + "***" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT population FROM Country WHERE Name = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		populationCountry  = result.getInt("population");
		
		//System.out.println(populationCountry + "this is the population of argentina");

		//System.out.println(result.getString("continent"));
		
		String previous = textArea.getText();
		textArea.setText(previous + "\nPopulation: " + populationCountry);
		stmt.close();
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public static void getCityPopulation(Connection conn, String selected, TextArea textArea)
{
	//doing country right now
	//String population = "";
	int populationCity = 0;
	
	try {
		
		
		//works and tested
		//System.out.println("Made it" + "***" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT population FROM City WHERE cityName = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		//System.out.println(result);
		populationCity = result.getInt("population");
		

		//System.out.println(result.getString("continent"));
		
		String previous = textArea.getText();
		textArea.setText(previous + "\nPopulation: " + populationCity);
		stmt.close();
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}

public static void getLifeExpectancy(Connection conn, String selected, TextArea textArea)
{
	//doing country right now
	//String population = "";
	double lifeExpectancy = 0;
	
	try {
		
		//works and tested
		//System.out.println("Made it" + "***" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT lifeExpectancy FROM Country WHERE Name = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		//System.out.println(result);
		lifeExpectancy  = result.getDouble("lifeExpectancy");
		

		//System.out.println(result.getString("continent"));
		
		String previous = textArea.getText();
		textArea.setText(previous + "\nLife expectancy: " + lifeExpectancy);
		stmt.close();
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void getCharCode(Connection conn, String selected, TextArea textArea)
{
	String countryCode = "";
	String language = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***8" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT countryCode FROM Country WHERE Name = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		countryCode = result.getString("countryCode");
		
		//System.out.println(countryCode);
		//getting the countries from the first table by city code
		
		
		getLanguage(conn, countryCode, textArea);
		stmt.close();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void getLanguage(Connection conn, String countryCode, TextArea textArea)
{
	
	String language = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***" + countryCode);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT language FROM Language WHERE countryCode = '" + countryCode +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		language = result.getString("language");
		
		//System.out.println(language);
		//getting the countries from the first table by city code
		
		//System.out.println(result.getString("continent"));
		
				String previous = textArea.getText();
				textArea.setText(previous + "\nLanguage: " + language);
				stmt.close();
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public static void getCharCityLanguage(Connection conn, String selected, TextArea textArea)
{
	String countryCode = "";
	String language = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT countryCode FROM City WHERE cityName = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		countryCode = result.getString("countryCode");
		
		//System.out.println(countryCode);
		//getting the countries from the first table by city code
		
		
		getCityLanguage(conn, countryCode, textArea);
		stmt.close();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public static void getCityLanguage(Connection conn, String countryCode, TextArea textArea)
{
	
	String language = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***" + countryCode);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT language FROM Language WHERE countryCode = '" + countryCode +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		language = result.getString("language");
		
		//System.out.println(language);
		//getting the countries from the first table by city code
		
		//System.out.println(result.getString("continent"));
		
		String previous = textArea.getText();
		textArea.setText(previous + "\nLanguage: " + language);
		stmt.close();
		
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
}

public static void getCountryCode(Connection conn, String selected, TextArea textArea, int num)
{
	String countryCode = "";
	String language = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***8" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT countryCode FROM City WHERE cityName = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		countryCode = result.getString("countryCode");
		
		//System.out.println(countryCode);
		//getting the countries from the first table by city code
		
		if(num ==1)
		{
		getCityCountry(conn, countryCode, textArea);
		}
		else if (num ==2){
			getCityContinent(conn, countryCode, textArea);
		}
		else
		{
			getCityLifeExpectancy(conn, countryCode, textArea);
		}
		stmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public static void getCityCountry(Connection conn, String countryCode, TextArea textArea)
{
	
	String name = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***8" + countryCode);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT Name FROM Country WHERE countryCode = '" + countryCode +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		name = result.getString("Name");
		
		//System.out.println(name);
		//getting the countries from the first table by city code
		
		//System.out.println(result.getString("continent"));
		
		String previous = textArea.getText();
		textArea.setText(previous + "\nCountry Name: " + name );
		stmt.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public static void getCityContinent(Connection conn, String countryCode, TextArea textArea)
{
	
	String continent = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***" + countryCode);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT continent FROM Country WHERE countryCode = '" + countryCode +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		continent = result.getString("continent"
				+ "");
		
		//System.out.println(continent);
		//getting the countries from the first table by city code
		
		//System.out.println(result.getString("continent"));
		
		String previous = textArea.getText();
		textArea.setText(previous + "\nContinent: " + continent );
		stmt.close();
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public static void getCityLifeExpectancy(Connection conn, String selected, TextArea textArea)
{
	//doing country right now
	//String population = "";
	double lifeExpectancy = 0;
	
	try {
		
		//works and tested
		//System.out.println("Made it" + "***" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT lifeExpectancy FROM Country WHERE countryCode = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		//System.out.println(result);
		lifeExpectancy  = result.getDouble("lifeExpectancy");
	
		//System.out.println(result.getString("continent"));
		
		String previous = textArea.getText();
		textArea.setText(previous + "\nLife expectancy: " + lifeExpectancy);
		stmt.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

//*****************************************************Language Queries *****************************************

//when the radio button for languages is clicked these are a bit trickier because we have to use the country code and we
//also have to add populations and average life expectancies

public static void getLangCharCode(Connection conn, String selected, TextArea textArea, int num)
{
	//String countryCode = "";
	//String language = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***8" + selected);
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT countryCode FROM Language WHERE language = '" + selected +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		ArrayList<String> list = new ArrayList();
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		while(result.next())
		{
			
		list.add(result.getString("countrycode"));
		//countryCode = result.getString("countryCode");
		//System.out.println(countryCode);
		//getting the countries from the first table by city code
		}
		
		//depending on the number passed through, we will be getting either population, continent, etc
		if(num == 1)
		{
		for(int i = 0; i < list.size(); i++)
		{
			getLanguageContinent(conn, list.get(i), textArea, i);
		}
		}
		
		if (num == 2)
			for(int i = 0; i < list.size(); i++)
			{
				getLanguagePopulation(conn, list.get(i), textArea, list, i);
			}
		
		if(num == 3)
		{
			for(int i = 0; i < list.size(); i++)
		
			{
				
			getLanguageLifeExpectancy(conn, list.get(i), textArea, list, i);
			}
			}
		
		if(num == 4)
		{
			for(int i = 0; i < list.size(); i++)
		
			{
			getLanguageCountry(conn, list.get(i), textArea, i);
			}
			}
		
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void getLanguageContinent(Connection conn, String countryCode, TextArea textArea, Integer num)
{
	
	String continent = "";
	
	try {
		
		//works
		//System.out.println("Made it" + "***" + countryCode + "Country with that language");
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT continent FROM Country WHERE countryCode = '" + countryCode +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		continent = result.getString("continent");
		//System.out.println(continent + "this should be in text field");
		
		String previous = textArea.getText();
		
		if(num == 0)
		{
		textArea.setText(previous + "\nContinent(s): " + "\n-" + continent);
		}
		
		
		else 
		{
			textArea.setText(previous + "\n-" + continent);
		}
		stmt.close();
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void getLanguagePopulation(Connection conn, String countryCode, TextArea textArea, ArrayList<String> list, int num)
{
	
	int population = 0;

	try {
		
	
		
		//works
		//System.out.println("Made it" + "***" + countryCode + "Country with that language");
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT population FROM Country WHERE countryCode = '" + countryCode +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		population = result.getInt("population");
		
		
		tempPop = tempPop + population;
			
		//System.out.println(population+ "this should be in text field");
		
		String previous = textArea.getText();
		
		
		if(list.size() == 1)
		{
			textArea.setText(previous + "\nPopulation: " + tempPop);
			//tempPop = 0;
		}
		
		//end of the list print the results
		if(num == list.size() - 1)
		{
			
		textArea.setText(previous + "\nPopulation: " + tempPop);
		tempPop = 0;

		}
		stmt.close();
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void getLanguageLifeExpectancy(Connection conn, String countryCode, TextArea textArea, ArrayList<String> list, int num)
{
	
	double lifeExpectancy = 0;
	
	try {

		//works
		//System.out.println("Made it" + "***" + countryCode + "Country with that language");
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT lifeExpectancy FROM Country WHERE countryCode = '" + countryCode +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		lifeExpectancy = result.getDouble("lifeExpectancy");
		
		tempLife = tempLife + lifeExpectancy;
		
		String previous = textArea.getText();
		
		
		//if the list is only one, print lifeExpectancy as opposed to the tempLife variable
		if(list.size() == 1)
		{
			textArea.setText(previous + "\nLife Expectancy: " + lifeExpectancy);
			tempLife = 0;
		}
		//end of the list, print the results
		else if(num == list.size() -1)
			{
				//System.out.println(tempLife + " this is before the division");
				
				tempLife = tempLife/(num +1);
				
				//System.out.println(tempLife  + " this is after the division");
				
				textArea.setText(previous + "\nLife Expectancy: " + String.format("%.2f", tempLife));
				tempLife = 0;
			}
		
	
		stmt.close();
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}

public static void getLanguageCountry(Connection conn, String countryCode, TextArea textArea, int i)
{
	
	String country = "";
	
	try {
		
	
		//getting the countries from the first table by city code
		Statement stmt = conn.createStatement();
		
		//grabbing correct continent :)
		String sqlStatement = "SELECT Name FROM Country WHERE countryCode = '" + countryCode +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
		
		//this caused a lot of problems -- specifically the invalid cursor state. You must move the cursor up one in a database
		result.next();
		
		country = result.getString("Name");
		
		
		String previous = textArea.getText();
		
		if(i == 0)
		{
		textArea.setText(previous + "\nCountry/Countries: " + "\n-" + country);
		}
		
		else 
		{
			textArea.setText(previous + "\n-" + country);
		}
	
		stmt.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}
