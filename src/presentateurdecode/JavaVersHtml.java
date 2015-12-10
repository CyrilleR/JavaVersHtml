/*
 * Copyright 2015 Cyrille Richard
 * 
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package presentateurdecode;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard; 
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.util.ArrayList;

/**
 * 
 * met en forme du code Java en Html et place cette présentation 
 * dans le presse papier.
 * 
 * Historique des versions:
 *  v0.8 - Différenciation commenatires / javadoc
 * 
 *  v0.7 - ajout des preferences, choix de couleurs
 * 
 *  v0.6 - passage en extension BlueJ
 * 
 *  v0.5 - changement des paramètres et du retour de la fontion principale
 *       - envoi dans le presse papier
 * 
 *  v0.1 - fonctionnement basique
 * 
 * @author Cyrille RICHARD
 *         dans le cadre de l'enseignement NFP121 - CNAM
 * 
 * @version 0.8
 * 
 */

public class JavaVersHtml {
    
    private static Preferences prefs;
    
    /* @author, @param...*/
    /* ESCAPE MAL GERE? */

    /**
     * Traduit chaque ligne de code Java en html et le met dans le clipboard
     * @param texteJava
     *           les lignes de code à présenter
     */
    public static void htmlToClipboard(String texteJava) {
        JavaVersHtml jvh = new JavaVersHtml();
        String texteHtml = jvh.javaToHtml(texteJava, prefs);
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Clipboard pressePapier = toolKit.getSystemClipboard();
        //pressePapier.setContents(new StringSelection("TEST OK"),null);
        pressePapier.setContents(new StringSelection(texteHtml), null);
    }
    
    /**
     * assigne les preferences de référence
     * @param p 
     *          preferences de BlueJ
     */
    public static void setPrefs(Preferences p) {
        prefs = p;
    }

    /**
     * convertit une chaine java en chaine html
     * @param texteJava
     *          chaine à convertir
     * @param prefs
     *          preferences de BlueJ
     * @return 
     */
    public String javaToHtml (String texteJava, Preferences prefs) {
      
        ArrayList<String> lignesJava = new ArrayList<>();
        
        //Conversion de la chaine en lignes séparées
        BufferedReader buffTexteJava = new BufferedReader(new StringReader(texteJava));
        String ligne; 
        try{
            while ( (ligne = buffTexteJava.readLine())!=null ) { 
                lignesJava.add(ligne);
            }
        }
        catch(Exception e) { System.out.println("Erreur lors du parcours du texte Java : " + e); }
         
        ConstructeurHtml constructeur = new ConstructeurHtml();
        constructeur.generationBalises(prefs);
        //En tête du code HTML
        if (prefs.isPrefEntete()) {
            constructeur.addString("<!-- DEBUT DE CODE JAVA - JavaVersHtml -->" + "\n");
        }
        constructeur.addString("<div style=\"");
        constructeur.addString("background:#"+prefs.getPrefFondCouleur()+"; ");
        constructeur.addString("color:#"+prefs.getPrefTexteCouleur()+"; ");
        if (prefs.isPrefTexteGras()) {
            constructeur.addString("font-weight:bold; ");
        }
        constructeur.addString(prefs.getPrefCadreStyle());
        constructeur.addString("\"> " + "\n");
        constructeur.addString("<pre>"  + "\n");

        for (int i = 0; i < lignesJava.size(); i++) {
            String ligneJava = lignesJava.get(i);
            ligneJava = caracteresSpeciaux(ligneJava);  //Nettoyage des caractères spéciaux
            constructeur.addLigneJava(ligneJava);
        }   
            
        constructeur.addString("</pre></div>" + "\n");
        if (prefs.isPrefEntete()) {
            constructeur.addString(" <!-- FIN DE CODE JAVA - JavaVersHtml -->");
        }
        
        return constructeur.getTexteHtml();
    }
    

    
    
    /**
     * remplace les caractères <> et accents par leur code html
     * @param s
     *      la chaîne à filtrer
     * @return 
     *      la chaîne filtrée
     */     
    private static String caracteresSpeciaux (String s) {
        s = s.replaceAll("<", "&lt;");
        s = s.replaceAll(">", "&gt;");
        s = s.replaceAll("à", "&agrave;");
        s = s.replaceAll("â", "&acirc;");
        s = s.replaceAll("é", "&eacute;");
        s = s.replaceAll("è", "&egrave;");
        s = s.replaceAll("ë", "&euml;");
        s = s.replaceAll("ê", "&ecirc;");
        s = s.replaceAll("ï", "&iuml;");
        s = s.replaceAll("î", "&icirc;");
        s = s.replaceAll("ô", "&ocirc;");
        s = s.replaceAll("ö", "&ouml;");
        s = s.replaceAll("û", "&ucirc;");
        s = s.replaceAll("ù", "&ugrave;");
        s = s.replaceAll("ü", "&uuml;");
        return s;
    }
}
