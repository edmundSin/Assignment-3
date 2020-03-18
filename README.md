# Assignment-3 Movies List

My code ("MoviesList.java") basically operates as following:
1. use custom method to read in csv file and extract movie titles(years included) into an arraylist of strings 
2. sort the arraylist alphabetically
3. use custom method to split the movie titles and years, then add these elements into a Movies class object, 
   next add objects into an arraylist
4. in order to get the subsets of movies, I created another method to return a specific index when searching for a
   movie title, users will call this method twice to assign index values to the start and finish variables of their 
   subset search
5. another custom method will print the movie titles and years of all Movies objects between the start and finish indexes
   to the output file ("myoutput.txt")
