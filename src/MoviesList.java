import java.io.*;
import java.util.*;

/* Edmund Sin
  * CISC 3130-TY9
  * Professor Chuang
  * 3/18/20
  * Assignment 3
  */ 

import java.io.*;
import java.util.*;

/* An example for working with multiple text files */
public class MoviesList {
  
  public static void main (String [] args)throws IOException{
  
   //arraylist of csv file names
   ArrayList<String> myFiles = new ArrayList<String>();
   
   //add movies.csv file name to arraylist
   myFiles.add("movies.csv");

  
  //array of movie names
    ArrayList<String> movieTitles = new ArrayList<String>();
    
   
   String outFile = "myoutput.txt";
   PrintWriter output = new PrintWriter(outFile);
   
   //call readFiles method to add movies into movieTitles arraylist
   readFiles(myFiles,movieTitles);
   
   //sort movieTitles alphabetically
   Collections.sort(movieTitles);
   
   //create arraylist of Movies class objects
   ArrayList<Movies> movieList = new ArrayList<Movies>();
   
   //call splitter method to extract years from movie titles and creates Movies objects to add into movieList 
   splitter(movieTitles,movieList);
   
   //first example
   //call movieSearch method to return index of movie title and assign value to start
   int start = movieSearch(movieList, "Heat ");
   //call movieSearch method to return index of movie title and assign value to finish
   int finish = movieSearch(movieList, "Toy Story ");
   //call printSubset method to print movie titles and years of those in between the two indexes 
   printSubset(movieList, output, start, finish);
   
   //second example
   start = movieSearch(movieList, "Balto ");
   finish = movieSearch(movieList, "Four Rooms ");
   printSubset(movieList, output, start, finish);
   
   //third example
   start = movieSearch(movieList, "Casino ");
   finish = movieSearch(movieList, "Waiting to Exhale ");
   printSubset(movieList, output, start, finish);
   

   
   output.flush();
   output.close();
  }
  
  //method to read data from csv files into an arraylist
  public static void readFiles(ArrayList<String>files, ArrayList<String>list)throws IOException{
    
   //to read file
   FileInputStream in = new FileInputStream(files.get(0));
   BufferedReader myInput = new BufferedReader(new InputStreamReader(in));
   String thisLine;
     
      while ((thisLine = myInput.readLine()) != null) {
      String str[] = thisLine.split(",");
      //gets rid of initial "title" label in .csv table
      if(!str[1].equals("title"))
      //fill movieTitles arraylist with the title stored at index 1
      list.add(str[1]);
      //stop adding after 20 movie titles
      if(str[0].equals ("20"))
        break;
        }
   
  }
  
  //method to split a movie title and the year, also creates Movies class objects to add into an arraylist
  public static void splitter(ArrayList<String>str, ArrayList<Movies> list)throws IOException{
   
   //traverse through arraylist of movie titles 
   for(int i = 0; i<str.size();i++){
   String thisLine = str.get(i);
   String stri[] = thisLine.split(" ");
   //the movie title is the substring minus 6 char value of the "(YEAR)"
   String title = thisLine.substring(0,thisLine.length()-6);
   //the year is in the last index of the string array of split strings 
   String year = stri[stri.length-1];
      
      //create Movies object with the title and year with constructor  
      Movies movie = new Movies(title , year );
      //add movie object to the arraylist 
      list.add(movie);
    }
      
  }
  
  //method that searches for an equal string from the movie titles in the arraylist 
  public static int movieSearch(ArrayList<Movies>list,String str){
    
    int index;
    for(int i = 0; i<list.size();i++){
      //if inputed string from arguement equals any of the titles from arraylist, index is returned
      if(str.equals(list.get(i).getTitle()))
      return i; 
        }
    //else, return -1
    return -1;
  }
  
  //method to print movie titles/years between the indexes of start and finish to output file
  public static void printSubset(ArrayList<Movies>list, PrintWriter outFile, int start, int finish){
    
    outFile.println("Movie                         Year");
    outFile.println("___________________________________");
    //traverse arraylist from start+1 and finish-1 to retrieve titles between the two indexes
    for(int i=start+1; i<=finish-1; i++){
      //print title and year to outfile
      outFile.printf("%-30s%s%n",list.get(i).getTitle() , list.get(i).getYear());
      
    }
    outFile.println();
    outFile.println();
  }
}

