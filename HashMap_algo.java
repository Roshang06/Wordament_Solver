import java.util.*;
import java.io.File;
//import java.util.HashMap;
import java.io.FileNotFoundException;
//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class HashMap_algo {
    public static final int SIZE = 676; //26 * 26 ---  aa, ab, ac...zy, zz
    public static ArrayList<String>[] organized_dictionary = new ArrayList[SIZE];
    public static ArrayList<Object[]> List_of_words = new ArrayList<Object[]>();
    //public static ArrayList<int[]> List_of_cords = new ArrayList<int[]>();
    public static int[] word_length_range = {3, 11};
    private static String[][] Board =
            {{"i", "d", "i", "c"},
            {"s", "t", "o", "u"},
            {"r", "t", "u", "c"},
            {"o", "a", "e", "e"}};
    public static void Print_Board(){
        for (String[] row: Board){ //prints the board for troubleshooting
            for (String square: row){
                System.out.print(square + " ");
            }
            System.out.println();
        }
    }
    public static void Update_Board(String[][] input_Board){
        Board = input_Board;
    }
    public static int Hash_func(String prefix){
        return (((int)prefix.charAt(0) - 97) * 26) + (int)prefix.charAt(1) - 97;
    }
    public static void Store_Dict() throws FileNotFoundException {
        for (int i = 0; i < organized_dictionary.length; i++){
            organized_dictionary[i] = new ArrayList<String>();
        }

        String file_address = "src/wordament_dict.txt";
        File dictionary = new File(file_address);
        Scanner scanner = new Scanner(dictionary);

        String word;
        String index;
        while (scanner.hasNext()){
            word = scanner.nextLine();

            if(word.length() >=2){
                index = word.substring(0, 2);
                organized_dictionary[Hash_func(index)].add(word);
            }
        }
        scanner.close();
    }
    public static boolean is_valid(String potential_word){
        int index = Hash_func(potential_word.substring(0, 2));
        for (String word: organized_dictionary[index]){
            if (word.equals(potential_word)){
                return true;
            }
        }
        return false;
    }
    public static void Recursive_Iterator(int y, int x, String prefix, ArrayList<int[]> prefix_coords){
        boolean point_is_valid = true;
        prefix_coords.add(new int[] {y, x});

        String potential_word = prefix + Board[y][x]; //TODO: make it use less memory
        if (potential_word.length() >= word_length_range[0]){// && potential_word.length() <= word_length_range[1]){
            if (is_valid(potential_word)){
                List_of_words.add(new Object[] {potential_word, prefix_coords});
            }
        }
        if (potential_word.length() >= word_length_range[1]){
            return;
        }

        for (int i = y - 1; i < y + 2; i++){
            if (i < 4 && i > -1){
                for (int j = x - 1; j < x + 2; j++){
                    point_is_valid = true;
                    for (int[] tuple: prefix_coords){
                        if (i == tuple[0] && j == tuple[1]){
                            //System.out.println("(" + tuple[0] + " " + tuple[1] + ") ");
                            point_is_valid = false;
                        }
                    }
                    if (j >= 4 || j < 0){
                        point_is_valid = false;
                    }
                    if (point_is_valid){ //  && prefix_coords.contains(new int[] {i, j}) && !(i == y && j == x)
                        Recursive_Iterator(i, j, potential_word, (ArrayList<int[]>) prefix_coords.clone());
                    }
                }
            }
        }
    }
    public static ArrayList<Object[]>[] Word_Hunt_algorithm(){
        //check if dictionary has been stored
        if (organized_dictionary[0] == null){
            System.out.println("Error: Dictionary has not been loaded.");
            return null;
        }
        //create a timer
        long time_toFindWords = System.currentTimeMillis();

        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                Recursive_Iterator(y, x, "", new ArrayList<int[]>());
            }
        }

        ArrayList<Object[]>[] Ordered = new ArrayList[16];
        for (int i = 0; i < 16; i++){
            Ordered[i] = new ArrayList<Object[]>();
        }

        ArrayList<String> words_already_added = new ArrayList<String>();
        int index;
        String word;
        for (Object[] tuple: List_of_words){
            word = (String)tuple[0];
            index = 16 - word.length();
            if (!words_already_added.contains(word)){
                Ordered[index].add(tuple);
                words_already_added.add(word);
            }
        }
        time_toFindWords = (System.currentTimeMillis() - time_toFindWords);

        System.out.println("Time to find words: " + (double)time_toFindWords/1000d + " seconds");
        return Ordered;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Store_Dict();
         //print the words that can be created from Board
        for (ArrayList<Object[]> list: Word_Hunt_algorithm()){
            for (Object[] item: list){ //for printing List_of_words
                System.out.print(item[0] + ": ");
                for (int[] tuple: (ArrayList<int[]>) item[1]){
                    System.out.print("(" + tuple[0] + " " + tuple[1] + ") ");
                }
                System.out.println();
            }
        }
    }
}
