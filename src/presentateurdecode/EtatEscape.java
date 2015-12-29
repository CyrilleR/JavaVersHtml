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
