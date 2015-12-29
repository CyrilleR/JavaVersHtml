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
 * interface pour la gestion de l'état de la chaine durant la conversion
 * 
 * @author Cyrille Richard
 */
public interface Etat {
    public void asterisque();
    public void slash();
    public void guillemetSimple();
    public void guillementDouble();
    public void escape();
    public void divers(char carac);
    public void sautDeLigne();
    public void finirLigne();
}
