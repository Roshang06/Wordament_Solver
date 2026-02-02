# Summary
Plays Microsoft Wordament
Open Microsoft wordament and start a wordament game.
Run the file WordEnterer_algo.java and enter the approximate number of pixels your wordament board is.
Enter the letters of the 16 square board from left to right, top to bottom.
Place your mouse at the first square of the board. In 5 seconds the program will use java Robot class to use 
mouse movement and click commands to play wordament. Enjoy!

# How it works:
A dictionary is stored within a Hashmap indexed using the first two letters of a word. For example, bannana, barcode, band, etc. are all in the same hash linked list.
A recursive function is used to loop through every combination of letters that can be made using the given board, and the Hash function is used to see if this combination of letters exists within the dictionary. If it does, it is added to an array of verified words.
Once all the words that exist on the given board are found, which takes about 2-4 seconds, the HashMap_algo.java will send the array of words, with cooresponding screen/board coordinates, to the WordEnterer_algo.java file.
This file uses the java Robot class to move the mouse through different points on the screen to enter each word, and prints the words in the terminal as they are being entered.

# Potential Improvements
- Using a binary tree to store the dictionary of words and verify if a word exists in the dictionary will be much faster algorithmically than using a hashmap.
- Currently, the user needs to enter the letters that exist on the board into the terminal window, which is annoying. This can be improved perhaps by using an API or taking a screenshot of the screen and identifying letters.

