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
//        Scanner reader = new Scanner(System.in);


        Arduino arduino = new Arduino("/dev/tty.usbmodem14311", 230400);
        arduino.openConnection();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SerialComm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Control control = new Control(arduino);     
        
//        arduino.serialWrite("Hello World");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(SerialComm.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String callback = arduino.serialRead(0);
//        System.out.println(callback);
//
//        String input = "";
//        while(!input.equals("q")) {
//            System.out.println("Enter a value: ");
//            input = reader.next();
//            arduino.serialWrite(input);
//        }
//        arduino.serialWrite("10");
//        arduino.closeConnection();
//        //System.out.println(arduino.serialRead(1));
    }
}
