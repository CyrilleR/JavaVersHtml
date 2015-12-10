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
public class EtatGuillemetsDoubles implements Etat {
    ConstructeurHtml constructeur;

    public EtatGuillemetsDoubles(ConstructeurHtml c) {
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
        constructeur.addStringToTemp("\'");
    }
    @Override
    public void guillementDouble() {
        constructeur.setEtat(constructeur.getEtatNormal());
        constructeur.addStringToTemp("\"");
        constructeur.addTempToHtml();
        constructeur.addBaliseOutGuillemets();
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
        constructeur.addTempToHtml();
        constructeur.endLigne();
    }
    @Override
    public void finirLigne() {
        constructeur.addTempToHtml();
    }
}
