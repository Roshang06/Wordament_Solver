//package bot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

    public class WordEnterer_algo{
        public static Point start_mouseLocation;
        public static int square_length = 65;
        public static boolean terminated = false;
        public static int wait_time = 57;
        private static String[][] Board =
                    {{"p", "a", "s", "e"},
                    {"b", "y", "t", "r"},
                    {"t", "o", "i", "n"},
                    {"a", "o", "e", "u"}};

    public static void main(String[] args) throws IOException, AWTException{
        Robot robot = new Robot();
        Scanner scanner = new Scanner(System.in);

        HashMap_algo.Store_Dict(); //loads the dictionary file
        System.out.print("What is the size of one square (pixels)? ");
        square_length = scanner.nextInt();
        scanner.nextLine();

        System.out.println();

        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                System.out.print("Enter square (" + y + ", " + x + "): ");
                Board[y][x] = scanner.nextLine();
            }
        }
        HashMap_algo.Update_Board(Board);
        HashMap_algo.Print_Board();

        /*for (String[] row: Board){ //prints the board for troubleshooting
            for (String square: row){
                System.out.print(square + " ");
            }
            System.out.println();
        }*/

        System.out.println("Please place curser on square 1...(wait 3 seconds)");
        robot.delay(5000);

        //Lets begin!
        start_mouseLocation = MouseInfo.getPointerInfo().getLocation();
        int x = (int) start_mouseLocation.getX();
        int y = (int) start_mouseLocation.getY();
        System.out.println("Start point: (" + x + " " + y + ")");

        //loop through words
        for (ArrayList<Object[]> list: HashMap_algo.Word_Hunt_algorithm()){
            for (Object[] item: list){
                ArrayList<int[]> sequence = (ArrayList<int[]>) item[1];
                //print the element
                System.out.print(item[0] + ": ");
                for (int[] tuple: sequence){
                    System.out.print("(" + tuple[0] + " " + tuple[1] + ") ");
                }
                System.out.println();

                //now, trace the element using the mouse
                Point screen_point_toMove = convertTP(sequence.get(0));

                robot.mouseMove(screen_point_toMove.x, screen_point_toMove.y);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.delay(wait_time);
                for (int i = 1; i < sequence.size(); i++){
                    screen_point_toMove = convertTP(sequence.get(i));

                    robot.mouseMove(screen_point_toMove.x, screen_point_toMove.y);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.delay(wait_time);
                }
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                robot.delay(wait_time);
            }
        }
    }
    public static Point convertTP(int[] coords){
            return new Point((int)((coords[1] * square_length) + start_mouseLocation.getX()), (int)((coords[0] * square_length) + start_mouseLocation.getY()));
    }


}
