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
public class EtatSortieCommentaireLong implements Etat {
    ConstructeurHtml constructeur;

    public EtatSortieCommentaireLong(ConstructeurHtml c) {
        constructeur = c;
    }
    @Override
    public void asterisque() {
        constructeur.addStringToTemp("*");
    }
    @Override
    public void slash() {
        constructeur.setEtat(constructeur.getEtatNormal());
        constructeur.addStringToTemp("*/");
        constructeur.addTempToHtml();
        constructeur.addBaliseOutCommentaires();
    }
    @Override
    public void guillemetSimple() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addStringToTemp("*\'");
    }
    @Override
    public void guillementDouble() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addStringToTemp("*\"");
    }
    @Override
    public void escape() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addStringToTemp("*");
        constructeur.setEtat(constructeur.getEtatEscape());
    }
    @Override
    public void divers(char carac) {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addStringToTemp("*" + String.valueOf(carac));
    }
    @Override
    public void sautDeLigne() {
        constructeur.setEtat(constructeur.getEtatCommentaireLong());
        constructeur.addStringToTemp("*");
        constructeur.addTempToHtml();
        constructeur.endLigne();
    }
    @Override
    public void finirLigne() {
        constructeur.addTempToHtml();
    }
}
