/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arduino;

import arduino.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author forestedwards
 */
public class SerialComm {
    public static void main(String[] args) {
        Arduino arduino = new Arduino("/dev/tty.usbmodem14311", 230400); // Sets up connection on specified port and baudrate
        arduino.openConnection(); // Starts connection
        try {
            Thread.sleep(2000); // Short delay to give time for connection
        } catch (InterruptedException ex) {
            Logger.getLogger(SerialComm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Control control = new Control(arduino); // Opens and starts control app.
    }
}
