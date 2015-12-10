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
public class EtatNormal implements Etat {
    ConstructeurHtml constructeur;

    public EtatNormal(ConstructeurHtml c) {
        constructeur = c;
    }
    @Override
    public void asterisque() {
        constructeur.addStringToTemp("*");
    }
    @Override
    public void slash() {
        constructeur.setEtat(constructeur.getEtatSlash());
    }
    @Override
    public void guillemetSimple() {
        constructeur.setEtat(constructeur.getEtatGuillemetsSimples());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInGuillemets();
        constructeur.addStringToTemp("'");
    }
    @Override
    public void guillementDouble() {
        constructeur.setEtat(constructeur.getEtatGuillemetsDoubles());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInGuillemets();
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
        constructeur.addTempToHtmlMotsClefs();
        constructeur.endLigne();
    }
    @Override
    public void finirLigne() {
        constructeur.addTempToHtmlMotsClefs();
    }
}
