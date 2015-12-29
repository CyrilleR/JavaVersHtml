/*
 * Copyright 2016 Cyrille Richard
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

/**
 *
 *  objet Etat utilisé par le patron State
 * 
 * @author Cyrille
 */
public class EtatSortieJavadoc implements Etat {
    ConstructeurHtml constructeur;

    public EtatSortieJavadoc(ConstructeurHtml c) {
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
        constructeur.addBaliseOutJavadoc();
    }
    @Override
    public void guillemetSimple() {
        constructeur.setEtat(constructeur.getEtatJavadoc());
        constructeur.addStringToTemp("*\'");
    }
    @Override
    public void guillementDouble() {
        constructeur.setEtat(constructeur.getEtatJavadoc());
        constructeur.addStringToTemp("*\"");
    }
    @Override
    public void escape() {
        constructeur.setEtat(constructeur.getEtatJavadoc());
        constructeur.addStringToTemp("*");
        constructeur.setEtat(constructeur.getEtatEscape());
    }
    @Override
    public void divers(char carac) {
        constructeur.setEtat(constructeur.getEtatJavadoc());
        constructeur.addStringToTemp("*" + String.valueOf(carac));
    }
    @Override
    public void sautDeLigne() {
        constructeur.setEtat(constructeur.getEtatJavadoc());
        constructeur.addStringToTemp("*");
        constructeur.addTempToHtml();
        constructeur.endLigne();
    }
    @Override
    public void finirLigne() {
        constructeur.addTempToHtml();
    }
}
