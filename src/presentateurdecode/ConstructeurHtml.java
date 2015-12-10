/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentateurdecode;

import java.util.ArrayList;

/**
 *
 * @author Cyrille
 */
public class ConstructeurHtml {
    /* Liste des mots-clefs*/
    private final static String motsclefs1[] = { "abstract", "continue", "for", "new", "switch",
                                                "assert", "default", "goto",  "synchronized",
                                                 "do", "if", "private", "this",
                                                "break",   "protected", "throw",
                                                 "else",  "public", "throws",
                                                "case",	 "instanceof", "return", "transient",
                                                "catch",  "try", "final",  "static", 
                                                 "finally",  "strictfp", "volatile",
                                                "const",  "native", "super", "while"};
    private final static String motsclefs2[] = { "package", "implements", "import","enum",
                                                 "extends", "interface", "void", "class" };
    private final static String primitifs[] = { "boolean", "double", "byte", "int", "short",
                                                "char","long", "float" };
    /* Code HTML des balises */
    private static String BALISE_OUVRE_COM;
    private static String BALISE_FERME_COM;
    private static String BALISE_OUVRE_GUILL;
    private static String BALISE_FERME_GUILL;
    private static String BALISE_OUVRE_RESERVE;
    private static String BALISE_FERME_RESERVE;
    private static String BALISE_OUVRE_RESERVE2;
    private static String BALISE_FERME_RESERVE2;
    private static String BALISE_OUVRE_JAVADOC;
    private static String BALISE_FERME_JAVADOC;
    private static String BALISE_OUVRE_PRIMITIFS;
    private static String BALISE_FERME_PRIMITIFS;
    
    /*Gestion des états*/
    private Etat etat;
    private Etat etatPrecedent;
    
    private final Etat etatNormal;
    private final Etat etatSlash;
    private final Etat etatCommentaireDeLigne;
    private final Etat etatSlashEtoile;
    private final Etat etatJavadoc;
    private final Etat etatCommentaireLong;
    private final Etat etatGuillemetsSimples;
    private final Etat etatGuillemetsDoubles;
    private final Etat etatSortieCommentaireLong;
    private final Etat etatSortieJavadoc;
    private final Etat etatEscape;
    
    ArrayList<String> lignesHtml;
    String ligneHtml;
    String tempString;
    
    public ConstructeurHtml() {
        lignesHtml = new ArrayList<>();
        etatSlash = new EtatSlash(this);
        etatNormal = new EtatNormal(this);
        etatSlashEtoile = new EtatSlashEtoile(this);
        etatCommentaireDeLigne = new EtatCommentaireDeLigne(this);
        etatJavadoc = new EtatJavadoc(this);
        etatCommentaireLong = new EtatCommentaireLong(this);
        etatGuillemetsDoubles = new EtatGuillemetsDoubles(this);
        etatGuillemetsSimples = new EtatGuillemetsSimples(this);
        etatEscape = new EtatEscape(this);
        etatSortieCommentaireLong = new EtatSortieCommentaireLong(this);
        etatSortieJavadoc = new EtatSortieJavadoc(this);
        
        etat = etatNormal;
        etatPrecedent = etatNormal;
    }
    
    public void addString(String s) {
        lignesHtml.add(s);
    }
    public void addStringToHtml(String s) {
        ligneHtml += s;
    }
    public void addTempToHtml() {
        ligneHtml += tempString;
        tempString = "";
    }
    public void addStringToHtmlMotsClefs(String s) {
        ligneHtml += motsClefs(s);
    }
    public void addTempToHtmlMotsClefs() {
        ligneHtml += motsClefs(tempString);
        tempString = "";
    }
    public void addStringToTemp(String s) {
        tempString += s;
    }
    public void endLigne() {
        ligneHtml += "\n";
        lignesHtml.add(ligneHtml);
        ligneHtml = "";
        tempString = "";
    }
    public String getTexteHtml() {
        String texteHtml="";
        for (int i = 0; i < lignesHtml.size(); i++) {
            texteHtml += lignesHtml.get(i);
        }
        return texteHtml;
    }
    public void addLigneJava(String ligneJava) {
        ligneHtml="";
        tempString="";
        for(int j = 0; j < ligneJava.length(); j++) {
            char c = ligneJava.charAt(j);
            switch(c) {
                case '/' :  etat.slash();
                            break;
                case '*' :  etat.asterisque();
                            break;
                case '"' :  etat.guillementDouble();
                            break;
                case '\'' :  etat.guillemetSimple();
                            break;
                case '\\' :  etat.escape();
                            break;
                case '\n' :  etat.sautDeLigne();
                            break;
                default :   etat.divers(c);
                            break;
            }  
        } 
        etat.sautDeLigne();
    }
    /**
    * entoure les mots cles par les balises choisies
    * @param s
    *      la chaine où chercher les mots clefs
    * @return 
    *      la chaine où les mots clefs ont été remplacés
    */
    private static String motsClefs (String s) {
        for (String motsclef : motsclefs1) {
            s = s.replaceAll(motsclef + " ", BALISE_OUVRE_RESERVE + motsclef + BALISE_FERME_RESERVE + " ");
            s = s.replaceAll(motsclef + "\\(", BALISE_OUVRE_RESERVE + motsclef + BALISE_FERME_RESERVE + "(");
            s = s.replaceAll(motsclef + "\\{", BALISE_OUVRE_RESERVE + motsclef + BALISE_FERME_RESERVE + "{");
            s = s.replaceAll(motsclef + "\\[", BALISE_OUVRE_RESERVE + motsclef + BALISE_FERME_RESERVE + "[");
        }
        for (String motsclef : motsclefs2) {
            s = s.replaceAll(motsclef + " ", BALISE_OUVRE_RESERVE2 + motsclef + BALISE_FERME_RESERVE2 + " ");
            s = s.replaceAll(motsclef + "\\(", BALISE_OUVRE_RESERVE2 + motsclef + BALISE_FERME_RESERVE2 + "(");
            s = s.replaceAll(motsclef + "\\{", BALISE_OUVRE_RESERVE2 + motsclef + BALISE_FERME_RESERVE2 + "{");
            s = s.replaceAll(motsclef + "\\[", BALISE_OUVRE_RESERVE2 + motsclef + BALISE_FERME_RESERVE2 + "[");
        }
        for (String motsclef : primitifs) {
            s = s.replaceAll(motsclef + " ", BALISE_OUVRE_PRIMITIFS + motsclef + BALISE_FERME_PRIMITIFS + " ");
            s = s.replaceAll(motsclef + "\\(", BALISE_OUVRE_PRIMITIFS + motsclef + BALISE_FERME_PRIMITIFS + "(");
            s = s.replaceAll(motsclef + "\\{", BALISE_OUVRE_PRIMITIFS + motsclef + BALISE_FERME_PRIMITIFS + "{");
            s = s.replaceAll(motsclef + "\\[", BALISE_OUVRE_PRIMITIFS + motsclef + BALISE_FERME_PRIMITIFS + "[");
        }
        return s;
    }
    
    public void setEtat(Etat e) {
        etatPrecedent = etat;
        etat = e;
    }
    public Etat getEtat() {
        return etat;
    }

    public Etat getEtatPrecedent() {
        return etatPrecedent;
    }

    public Etat getEtatNormal() {
        return etatNormal;
    }

    public Etat getEtatSlash() {
        return etatSlash;
    }

    public Etat getEtatCommentaireDeLigne() {
        return etatCommentaireDeLigne;
    }

    public Etat getEtatSlashEtoile() {
        return etatSlashEtoile;
    }

    public Etat getEtatJavadoc() {
        return etatJavadoc;
    }

    public Etat getEtatCommentaireLong() {
        return etatCommentaireLong;
    }

    public Etat getEtatGuillemetsSimples() {
        return etatGuillemetsSimples;
    }

    public Etat getEtatGuillemetsDoubles() {
        return etatGuillemetsDoubles;
    }

    public Etat getEtatSortieCommentaireLong() {
        return etatSortieCommentaireLong;
    }

    public Etat getEtatSortieJavadoc() {
        return etatSortieJavadoc;
    }

    public Etat getEtatEscape() {
        return etatEscape;
    }

    public void generationBalises(Preferences prefs) {
            /* RECUPERATION DES PREFS */
        BALISE_OUVRE_COM = "<FONT color=\"#"+prefs.getPrefCommentairesCouleur()+"\"><i>";
        if (prefs.isPrefCommentairesGras()) BALISE_OUVRE_COM += "<b>";
        BALISE_FERME_COM = "</i></FONT>";
        if (prefs.isPrefCommentairesGras()) BALISE_FERME_COM = "</b>" + BALISE_FERME_COM;
        
        BALISE_OUVRE_GUILL = "<FONT color=\"#"+prefs.getPrefChainesCouleur()+"\">";
        if (prefs.isPrefChainesGras()) BALISE_OUVRE_GUILL += "<b>";
        BALISE_FERME_GUILL = "</FONT>";
        if (prefs.isPrefChainesGras()) BALISE_FERME_GUILL = "</b>" + BALISE_FERME_GUILL;
        
        BALISE_OUVRE_RESERVE = "<FONT color=\"#"+prefs.getPrefReserves1Couleur()+"\">";
        if (prefs.isPrefReserves1Gras()) BALISE_OUVRE_RESERVE += "<b>";
        BALISE_FERME_RESERVE = "</FONT>";
        if (prefs.isPrefReserves1Gras()) BALISE_FERME_RESERVE = "</b>" + BALISE_FERME_RESERVE;
        
        BALISE_OUVRE_RESERVE2 = "<FONT color=\"#"+prefs.getPrefReserves2Couleur()+"\">";
        if (prefs.isPrefReserves2Gras()) BALISE_OUVRE_RESERVE2 += "<b>";
        BALISE_FERME_RESERVE2 = "</FONT>";
        if (prefs.isPrefReserves2Gras()) BALISE_FERME_RESERVE2 = "</b>" + BALISE_FERME_RESERVE2;
        
        BALISE_OUVRE_JAVADOC = "<FONT color=\"#"+prefs.getPrefJavadocCouleur()+"\"><i>";
        if (prefs.isPrefJavadocGras()) BALISE_OUVRE_JAVADOC += "<b>";
        BALISE_FERME_JAVADOC = "</i></FONT>";
        if (prefs.isPrefJavadocGras()) BALISE_OUVRE_JAVADOC = "</b>" + BALISE_FERME_JAVADOC;
        
        BALISE_OUVRE_PRIMITIFS = "<FONT color=\"#"+prefs.getPrefPrimitifsCouleur()+"\">";
        if (prefs.isPrefPrimitifsGras()) BALISE_OUVRE_PRIMITIFS += "<b>";
        BALISE_FERME_PRIMITIFS = "</FONT>";
        if (prefs.isPrefPrimitifsGras()) BALISE_FERME_PRIMITIFS = "</b>" + BALISE_FERME_PRIMITIFS;
    }
    public void addBaliseInGuillemets() {
        addStringToHtml(BALISE_OUVRE_GUILL);
    }
    public void addBaliseOutGuillemets() {
        addStringToHtml(BALISE_FERME_GUILL);
    }
    public void addBaliseInCommentaires() {
        addStringToHtml(BALISE_OUVRE_COM);
    }
    public void addBaliseOutCommentaires() {
        addStringToHtml(BALISE_FERME_COM);
    }
    public void addBaliseInJavadoc() {
        addStringToHtml(BALISE_OUVRE_JAVADOC);
    }
    public void addBaliseOutJavadoc() {
        addStringToHtml(BALISE_FERME_JAVADOC);
    }
}
