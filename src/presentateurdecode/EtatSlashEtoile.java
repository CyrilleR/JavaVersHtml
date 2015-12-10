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
public class EtatSlashEtoile implements Etat {
    ConstructeurHtml constructeur;

    public EtatSlashEtoile(ConstructeurHtml c) {
        constructeur = c;
    }
    @Override
    public void asterisque() {
        constructeur.setEtat(constructeur.getEtatJavadoc());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInJavadoc();
        constructeur.addStringToTemp("/**");
    }
    @Override
    public void slash() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInCommentaires();
        constructeur.addStringToTemp("/*/");
    }
    @Override
    public void guillemetSimple() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInCommentaires();
        constructeur.addStringToTemp("/*'");
    }
    @Override
    public void guillementDouble() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInCommentaires();
        constructeur.addStringToTemp("/*\"");
    }
    @Override
    public void escape() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.setEtat(constructeur.getEtatEscape());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInCommentaires();
        constructeur.addStringToTemp("/*");
    }
    @Override
    public void divers(char carac) {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInCommentaires();
        constructeur.addStringToTemp("/*" + String.valueOf(carac));
    }
    @Override
    public void sautDeLigne() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInCommentaires();
        constructeur.addStringToHtml("/*");
        constructeur.endLigne();
    }
    @Override
    public void finirLigne() {
        constructeur.addTempToHtmlMotsClefs();
        constructeur.addBaliseInCommentaires();
        constructeur.addStringToHtml("/*");
    }
}
