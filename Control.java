package Arduino;

import arduino.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Control extends JPanel {
    private int speed, pulselength;
    private Arduino arduino;

    public Control(Arduino ard) {
        arduino = ard;
        JFrame frame = new JFrame("EGEN Control App");
        frame.setSize(512, 512); // Pixel width and pixel height of window
        try { // Attempts to add image to control
            frame.setContentPane(new JLabel((new ImageIcon(ImageIO.read(new File("src/Control Image.png"))))));
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the window closes, the program stops        
        KeyListener listener = new MyKeyListener(); // This will be what listens to when the keyboard is pressed
        addKeyListener(listener);
        setFocusable(true);
    }

    public class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) { // Unnecesary function implementation
        }

        @Override
        public void keyPressed(KeyEvent e) {
            String tosend = ""; // Message to be sent to arduino port.
            //Message denoted as: "L<leftmotorspeed>R<rightmotorspeed><pulseduration>"  eg: L5R37
            switch (KeyEvent.getKeyText(e.getKeyCode())) {
                //Servo direction specification
                case "Q":
                    System.out.println("Left forward");
                    tosend = "L" + speed + "R" + 5 + pulselength;
                    arduino.serialWrite(tosend);
                    break;
                case "E":
                    System.out.println("Right forward");
                    tosend = "L" + 5 + "R" + speed + pulselength;
                    arduino.serialWrite(tosend);
                    break;
                case "A":
                    System.out.println("Left backward");
                    tosend = "L" + (5 - (speed - 5)) + "R" + 5 + pulselength;
                    arduino.serialWrite(tosend);                    
                    break;
                case "D":
                    System.out.println("Right backward");
                    tosend = "L" + 5 + "R" + (5 - (speed - 5)) + pulselength;
                    arduino.serialWrite(tosend);
                    break;
                case "W":
                    System.out.println("Both forward");
                    tosend = "L" + speed + "R" + speed + pulselength;
                    arduino.serialWrite(tosend);
                    break;
                case "S":
                    System.out.println("Both backward");
                    tosend = "L" + (5 - (speed-5)) + "R" + (5 - (speed-5)) + pulselength;
                    arduino.serialWrite(tosend);
                    break;
                case "1": 
                    System.out.println("Set speed to -4");
                    speed = 1;
                    break;
                case "2":
                    System.out.println("Set speed to -3");
                    speed = 2;
                    break;
                case "3":
                    System.out.println("Set speed to -2");
                    speed = 3;
                    break;
                case "4":
                    System.out.println("Set speed to -1");
                    speed = 4;
                    break;
                case "5":
                    System.out.println("Set speed to 0");
                    speed = 5;
                    break;
                case "6":
                    System.out.println("Set speed to 1");
                    speed = 6;
                    break;
                case "7":
                    System.out.println("Set speed to 2");
                    speed = 7;
                    break;
                case "8":
                    System.out.println("Set speed to 3");
                    speed = 8;
                    break;
                case "9":
                    System.out.println("Set speed to 4");
                    speed = 9;
                    break;
                case "Z":
                    System.out.println("Set pulse length to 100ms");
                    pulselength = 1;
                    break;   
                case "X":
                    System.out.println("Set pulse length to 200ms");
                    pulselength = 2;
                    break;
                case "C":
                    System.out.println("Set pulse length to 300ms");
                    pulselength = 3;
                    break;
                case "V":
                    System.out.println("Set pulse length to 400ms");
                    pulselength = 4;
                    break;
                case "B":
                    System.out.println("Set pulse length to 500ms");
                    pulselength = 5;
                    break;
                case "N":
                    System.out.println("Set pulse length to 600ms");
                    pulselength = 6;
                    break;
                case "M":
                    System.out.println("Set pulse length to 700ms");
                    pulselength = 7;
                    break;
                case ",":
                    System.out.println("Set pulse length to 800ms");
                    pulselength = 8;
                    break;
                case ".":
                    System.out.println("Set pulse length to 900ms");
                    pulselength = 9;
                    break;
                default:
                    arduino.serialWrite("L5R51"); // Both motors go stationary
                    try {
                        speed = Integer.parseInt(KeyEvent.getKeyText(e.getKeyCode()));
                    } catch (Exception exception) {

                    }
                    break;
            }
            try { // Short delay to prevent thread interruptions
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch(KeyEvent.getKeyText(e.getKeyCode())) {
                case "Q":
                    System.out.println("Stop left forward");
                    break;
                case "E":
                    System.out.println("Stop right forward");
                    break;
                case "A":
                    System.out.println("Stop left backward");
                    break;
                case "D":
                    System.out.println("Stop right backward");
                    break;
                case "W":
                    System.out.println("Stop both forward");
                    break;
                case "S":
                    System.out.println("Stop both backward");
                    break;
                default:
                    break;
            }
            arduino.serialWrite("L5R5");
        }
    }
}