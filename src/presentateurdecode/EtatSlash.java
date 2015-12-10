/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentateurdecode;

/**
 *
 * @author Cyrille
 */
public class EtatSlash implements Etat {
    ConstructeurHtml constructeur;

    public EtatSlash(ConstructeurHtml c) {
        constructeur = c;
    }
    
    @Override
    public void asterisque() {
        constructeur.setEtat(constructeur.getEtatSlashEtoile());
    } 
    @Override
    public void slash() {
        constructeur.setEtat(constructeur.getEtatCommentaireDeLigne());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInCommentaires();
        constructeur.addStringToHtml("//");
    }
    @Override
    public void guillemetSimple() {
        constructeur.addStringToTemp("/");
        constructeur.setEtat(constructeur.getEtatNormal());
    }
    @Override
    public void guillementDouble() {
        constructeur.addStringToTemp("\"");
        constructeur.setEtat(constructeur.getEtatNormal());
    }
    @Override
    public void escape() {
        constructeur.setEtat(constructeur.getEtatNormal()); 
        constructeur.addStringToTemp("/");
        constructeur.setEtat(constructeur.getEtatEscape());        
    }
    @Override
    public void divers(char carac) {
        constructeur.addStringToTemp("/" + String.valueOf(carac));
        constructeur.setEtat(constructeur.getEtatNormal());     
    }
    @Override
    public void sautDeLigne() {
        constructeur.addStringToTemp("/");
        constructeur.addTempToHtmlMotsClefs();
        constructeur.endLigne();
    }
    @Override
    public void finirLigne() {
        constructeur.addTempToHtmlMotsClefs();
    }
}
