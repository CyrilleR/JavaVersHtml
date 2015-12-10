
package presentateurdecode;

import bluej.extensions.*;
import bluej.extensions.editor.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
/* This class shows how you can bind different menus to different parts of BlueJ
 * Remember:
 * - getToolsMenuItem, getClassMenuItem and getObjectMenuItem may be called by BlueJ at any time.
 * - They must generate a new JMenuItem each time they are called.
 * - No reference to the JMenuItem should be stored in the extension.
 * - You must be quick in generating your menu.
 */

/**
 *
 *  gestion du menu de l'extension JavaVersHtml pour BlueJ
 * 
 * @author Cyrille Richard
 * 
 * @version 0.8
 */
class MenuBuilder extends MenuGenerator {

    private BClass curClass;

    @Override
    public JMenuItem getClassMenuItem(BClass aClass)
    {
        return new JMenuItem(new envoyerSelection("HTML dans le clipboard", "Class menu:"));
    }
    
    public MenuBuilder() {
    }

    public void notifyPostClassMenu(BClass bc, JMenuItem jmi) {
        curClass = bc ;
    }
    
    // The nested class that instantiates the different (simple) menus.
    class envoyerSelection extends AbstractAction {
        private String msgHeader;
        Editor classEditor = null;
        public envoyerSelection(String menuName, String msg) {
            /*try
                {
                    classEditor = curClass.getEditor();
                }
            catch (Exception e) { }
            if(classEditor != null) { */      
                putValue(AbstractAction.NAME, menuName);
                msgHeader = msg;
            /*}*/
        }
        
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            envoyerHtmlDansPressePapier();
        }
    }
    /**
     * si un éditeur de texte est disponible pour la classe courante,
     * envoie le texte selectionné dans cet éditeur vers la méthode
     * de conversion.
     */
    private void envoyerHtmlDansPressePapier() {
        Editor classEditor = null;
        try
        {
            classEditor = curClass.getEditor();
        }
        catch (Exception e) { }
        if(classEditor == null)
        {
            return;
        }
        TextLocation debut = classEditor.getSelectionBegin();
        TextLocation fin = classEditor.getSelectionEnd();
        /*if (debut.equals(fin)) {
            debut = classEditor.getTextLocationFromOffset(0);
            fin = classEditor.getTextLocationFromOffset(classEditor.getTextLength()-1); 
        }*/
        String texte = classEditor.getText(debut, fin);
        JavaVersHtml.htmlToClipboard(texte);
    }
}
