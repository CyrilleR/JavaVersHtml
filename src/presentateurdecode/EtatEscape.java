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
public class EtatEscape implements Etat {
    ConstructeurHtml constructeur;

    public EtatEscape(ConstructeurHtml c) {
        constructeur = c;
    }
    @Override
    public void asterisque() {
        constructeur.setEtat(constructeur.getEtatPrecedent());
        constructeur.addStringToTemp("\\*");
    }
    @Override
    public void slash() {
        constructeur.setEtat(constructeur.getEtatPrecedent());
        constructeur.addStringToTemp("\\/");
    }
    @Override
    public void guillemetSimple() {
        constructeur.setEtat(constructeur.getEtatPrecedent());
        constructeur.addStringToTemp("\\\'");
    }
    @Override
    public void guillementDouble() {
        constructeur.setEtat(constructeur.getEtatPrecedent());
        constructeur.addStringToTemp("\\\"");
    }
    @Override
    public void escape() {
        constructeur.setEtat(constructeur.getEtatPrecedent());
        constructeur.addStringToTemp("\\\\");
    }
    @Override
    public void divers(char carac) {
        constructeur.setEtat(constructeur.getEtatPrecedent());
        constructeur.addStringToTemp("\\" + String.valueOf(carac));
    }
    @Override
    public void sautDeLigne() {
        constructeur.setEtat(constructeur.getEtatPrecedent());
        constructeur.addStringToTemp("\\");
        constructeur.getEtat().finirLigne();
        constructeur.endLigne();
    }
    @Override
    public void finirLigne() {
        constructeur.addTempToHtml();
    }
}
