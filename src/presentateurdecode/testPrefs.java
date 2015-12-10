/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentateurdecode;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author Cyrille
 */
public class testPrefs extends JFrame{
    JPanel p;
    public static void main(String[] args){
        testPrefs tp=new testPrefs();
        tp.setVisible(true);
    }
        
    public testPrefs() {
        setTitle("Analyse Audio - NFP136");
        setBounds(100,100,1000,600);
        setResizable(true);
        
        //Quitter quand on ferme la fenêtre
        addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });
        
        Preferences prefs = new Preferences(null);
        
        p = prefs.getPanel();
        
        add(p);
    }
}
