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
public class EtatCommentaireDeLigne implements Etat {
    ConstructeurHtml constructeur;

    public EtatCommentaireDeLigne(ConstructeurHtml c) {
        constructeur = c;
    }
    @Override
    public void asterisque() {
        constructeur.addStringToTemp("*");
    }
    @Override
    public void slash() {
        constructeur.addStringToTemp("/");
    }
    @Override
    public void guillemetSimple() {
        constructeur.addStringToTemp("'");
    }
    @Override
    public void guillementDouble() {
        constructeur.addStringToTemp("\"");
    }
    @Override
    public void escape() {
        constructeur.setEtat(constructeur.getEtatEscape());
    }
    @Override
    public void divers(char carac) {
        constructeur.addStringToTemp(String.valueOf(carac));
    }
    @Override
    public void sautDeLigne() {
        constructeur.setEtat(constructeur.getEtatNormal());
        constructeur.addTempToHtml();
        constructeur.addBaliseOutCommentaires();
        constructeur.endLigne();
    }
    @Override
    public void finirLigne() {
        constructeur.addTempToHtml();
        constructeur.addBaliseOutCommentaires();
    }
}
