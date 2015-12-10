
package presentateurdecode;

import bluej.extensions.*;
import java.net.URL;

/**
 *  gestion de l'extension JavaVersHtml pour BlueJ
 * 
 * @author Cyrille Richard
 * 
 * @version 0.8
 */
public class JavaVersHtmlExtension extends Extension {

    public void startup (BlueJ bluej) {
           
        // Register a generator for menu items
        bluej.setMenuGenerator(new MenuBuilder());
        // Register a "preferences" panel generator
        Preferences prefs = new Preferences(bluej);
        bluej.setPreferenceGenerator(prefs);
    }

    public boolean isCompatible () { 
        return true; 
    }

    public String  getVersion () { 
        return ("0.8");  
    }

    public String  getName () { 
        return ("JavaVersHtml Extension");  
    }

    public void terminate() {
        System.out.println ("JavaVersHtml a quitté.");
    }
    
    public String getDescription () {
        return ("Extension permettant l'export de code Java formaté html dans le presse-papier.");
    }

    public URL getURL () {
        try {
            return new URL("http://javavershtml.recoding.html");
        } catch ( Exception eee ) {
            // The link is either dead or otherwise unreachable
            System.out.println ("Simple extension: getURL: Exception="+eee.getMessage());
            return null;
        }
    }
}

