
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
}
