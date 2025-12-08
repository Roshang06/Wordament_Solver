import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Robot_testings extends JFrame{

    /*public Robot_testings() {
        super("My GUI App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);

        // Add components (buttons, labels, etc.) to the panel
        // For example:
        JButton button = new JButton("Click me!");
        panel.add(button);

        //add key listener
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.KEY_PRESSED) {
                    System.out.println("Shift key pressed!");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        panel.addKeyListener(listener);
        // Add the panel to the JFrame
        add(panel);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }*/


    public static void main(String[] args) throws AWTException{
        JFrame frame = new JFrame("Key Bindings Example");
        JPanel panel = new JPanel();

        // Create an action for the key press
        Action myAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Key pressed!");
            }
        };

        // Bind the action to the specific key (e.g., VK_SPACE)
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "myAction");
        panel.getActionMap().put("myAction", myAction);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /*Robot robot = new Robot();
        robot.delay(3000);
        Point start_pos = MouseInfo.getPointerInfo().getLocation();
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(start_pos.x + 50, start_pos.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(start_pos.x + 50, start_pos.y + 50);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(start_pos.x, start_pos.y + 50);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);*/
    }

    /*@Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            System.out.println("Shift key pressed!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }*/

   /* public static boolean isKeyPressed() {
        try {
            return System.in.available() > 0;
        } catch (IOException e) {
          -  e.printStackTrace();
            return false;
        }
    }*/



}
