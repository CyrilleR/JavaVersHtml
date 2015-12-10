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
public class EtatSlash implements Etat {
    ConstructeurHtml Constructeur;

    public EtatSlash(ConstructeurHtml c) {
        Constructeur = c;
    }
    
    @Override
    public void asterisque() {
        
    }
    @Override
    public void slash() {
        
    }
    @Override
    public void guillemetSimple() {
        
    }
    @Override
    public void guillementDouble() {
        
    }
    @Override
    public void escape() {
        
    }
    @Override
    public void divers(char carac) {
        
    }
    @Override
    public void sautDeLigne() {
        
    }
}
