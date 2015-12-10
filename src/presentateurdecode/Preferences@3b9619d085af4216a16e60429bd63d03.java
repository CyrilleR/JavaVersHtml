package presentateurdecode;

import bluej.extensions.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * gestion des preferences de l'extension JavaVersHtml
 * 
 * @author Cyrille Richard
 * 
 * @version v0.8
 */

class Preferences implements PreferenceGenerator, ActionListener
{
    private static final String[] FOND_COULEURS = { "FFFFFF", "F8F8F8", "EEEEFF", "FFFFEE", "111111"};
    private static final String[] TEXTE_COULEURS = { "000000", "000088", "880000", "444444", "EEEEEE"};
    private static final String[] COMMS_COULEURS = { "555555", "333333", "0000FF", "005500", "888888"};
    private static final String[] CHAINES_COULEURS = { "00AA00", "EE6600", "0000FF", "005500", "33FF33"};
    private static final String[] RESERVES1_COULEURS = { "990033", "FF00CC", "0000CC", "006600", "FF0000"};
    private static final String[] RESERVES2_COULEURS = { "BB0000", "AA0000", "990033", "000000", "FF6600"};
    private static final String[] PRIMITIFS_COULEURS = { "FF6600", "990033", "660066", "006699", "FFCC00"};
    private static final String[] JAVADOC_COULEURS = { "0000FF", "3366CC", "555555", "005500", "888888"};
    private static final String[] CADRE_STYLES = { "border:none; margin:10px; padding:10px;", 
                                                    "border:solid 1px black; margin:10px; padding:10px;", 
                                                    "border:solid 1px blue; margin:10px; padding:10px;",
                                                    "border:dashed 1px blue; margin:10px; padding:10px;",
                                                    "border:outset 2px black; margin:10px; padding:10px;",
                                                    "border:solid 1px red; margin:10px; padding:10px;",
                                                    };
    
    private int          prefFondCouleur;
    private int          prefTexteCouleur;          
    private boolean      prefTexteGras;
    private int          prefCommentairesCouleur;
    private boolean      prefCommentairesGras;
    private int          prefReserves1Couleur;
    private boolean      prefReserves1Gras;
    private int          prefReserves2Couleur;
    private boolean      prefReserves2Gras;
    private int          prefChainesCouleur;
    private boolean      prefChainesGras;
    private int          prefPrimitifsCouleur;
    private boolean      prefPrimitifsGras;
    private int          prefJavadocCouleur;
    private boolean      prefJavadocGras;
    private int          prefCadreStyle;
    private boolean      prefEntete;
    
    private JPanel          panneau; 
    private JCheckBox       checkDebutFin;
    
    private JLabel          labelCouleurDeFond;
    private JRadioButton[]  radioFondCouleur;

    
    private JLabel          labelCouleurDeTexte;
    private JCheckBox       checkTexteGras;
    private JRadioButton[]  radioTexteCouleur;
    
    private JLabel          labelCouleurDeCommentaires;
    private JCheckBox       checkCommentairesGras;
    private JRadioButton[]  radioCommentairesCouleur;
    
    private JLabel          labelCouleurDeChaines;
    private JCheckBox       checkChainesGras;
    private JRadioButton[]  radioChainesCouleur;
    
    private JLabel          labelCouleurDeReserves1;
    private JCheckBox       checkReserves1Gras;
    private JRadioButton[]  radioReserves1Couleur;
    
    private JLabel          labelCouleurDeReserves2;
    private JCheckBox       checkReserves2Gras;
    private JRadioButton[]  radioReserves2Couleur;
    
    private JLabel          labelCouleurDePrimitifs;
    private JCheckBox       checkPrimitifsGras;
    private JRadioButton[]  radioPrimitifsCouleur;
    
    private JLabel          labelCouleurJavadoc;
    private JCheckBox       checkJavadocGras;
    private JRadioButton[]  radioJavadocCouleur;
    
    private JLabel          labelCouleurDeAutres2;
 /*   private JCheckBox       checkTexteGras;
    private JRadioButton    radioTexteCouleur1;*/
    
    private JLabel          labelCouleurDeAutres3;
 /*   private JCheckBox       checkTexteGras;
    private JRadioButton    radioTexteCouleur1;*/

    private JLabel          labelCadre;
    private JRadioButton[]  radioCadreStyle;
    
    private ButtonGroup     groupFondCouleur;
    private ButtonGroup     groupTexteCouleur;
    private ButtonGroup     groupCommentairesCouleur;
    private ButtonGroup     groupChainesCouleur;
    private ButtonGroup     groupReserves1Couleur;
    private ButtonGroup     groupReserves2Couleur;
    private ButtonGroup     groupPrimitifsCouleur;
    private ButtonGroup     groupJavadocCouleur;
    private ButtonGroup     groupCadreStyle;
    
    private JEditorPane     panneauExemple;
    private static final String exemple = "Exemple HTML\n" + "/*\n" +
            " * license header\n" +
            " */ \n" +
            "package myPackage;\n" +
            "\n" +
            "import javax.util.*;\n" +
            "\n" +
            "/**\n" +
            " *\n" +
            " * @author auteur\n" +
            " */\n" +
            "public class Exemple extends Exemple{\n" +
            "private static final int entier;/\n" +
            "    public static void main(String[] args){\n" +
            "    String test = \"Un test\";\n" +
            "    }\n" +
            "}\n";
    
    private BlueJ bluej;

    // Construct the panel, and initialise it from any stored values
    public Preferences(BlueJ bluej)
    {
        this.bluej = bluej;
        JavaVersHtml.setPrefs(this);
        // Load the default value
        loadValues();    
        //INITIALISATIONS
        panneau = new JPanel();
        
        checkDebutFin = new JCheckBox("Signaler début et fin de code avec un commentaire HTML");
        checkDebutFin.setSelected(prefEntete);
        checkDebutFin.addActionListener(this); checkDebutFin.setActionCommand("ENTT");
        labelCouleurDeFond = new JLabel("Fond");                      //FOND
        radioFondCouleur = new JRadioButton[5];
        groupFondCouleur = new ButtonGroup();
        for (int i = 0; i < 5; i++) {                                   
            radioFondCouleur[i] = new JRadioButton("\u2588\u2588\u2588");
            groupFondCouleur.add(radioFondCouleur[i]);
            radioFondCouleur[i].setBackground((new Color(Integer.parseInt(FOND_COULEURS[i], 16))));
            radioFondCouleur[i].addActionListener(this); radioFondCouleur[i].setActionCommand("FOND " + i);
            if( prefFondCouleur == i) radioFondCouleur[i].setSelected(true);
        }
        
        labelCouleurDeTexte = new JLabel("Texte");                  //TEXTE
        checkTexteGras = new JCheckBox("Bold"); checkTexteGras.setSelected(prefTexteGras);
        checkTexteGras.addActionListener(this); checkTexteGras.setActionCommand("TEXB");
        groupTexteCouleur = new ButtonGroup();
        radioTexteCouleur = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {                                   
            radioTexteCouleur[i] = new JRadioButton("\u2588\u2588\u2588");
            groupTexteCouleur.add(radioTexteCouleur[i]);
            radioTexteCouleur[i].setForeground((new Color(Integer.parseInt(TEXTE_COULEURS[i], 16))));
            radioTexteCouleur[i].addActionListener(this); radioTexteCouleur[i].setActionCommand("TEXT " + i);
            if( prefTexteCouleur == i) radioTexteCouleur[i].setSelected(true);
        }
        
        
        
        labelCouleurDeCommentaires = new JLabel("Commentaires");                  //COMMENTAIRES
        checkCommentairesGras = new JCheckBox("Bold"); checkCommentairesGras.setSelected(prefCommentairesGras);
        checkCommentairesGras.addActionListener(this); checkCommentairesGras.setActionCommand("COMB");
        groupCommentairesCouleur = new ButtonGroup();
        radioCommentairesCouleur = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {                                   
            radioCommentairesCouleur[i] = new JRadioButton("\u2588\u2588\u2588");
            groupCommentairesCouleur.add(radioCommentairesCouleur[i]);
            radioCommentairesCouleur[i].setForeground((new Color(Integer.parseInt(COMMS_COULEURS[i], 16))));
            radioCommentairesCouleur[i].addActionListener(this); radioCommentairesCouleur[i].setActionCommand("COMM " + i);
            if( prefCommentairesCouleur == i) radioCommentairesCouleur[i].setSelected(true);
        }
        
        labelCouleurDeChaines = new JLabel("Chaines");                  //CHAINES
        checkChainesGras = new JCheckBox("Bold"); checkChainesGras.setSelected(prefChainesGras);
        checkChainesGras.addActionListener(this); checkChainesGras.setActionCommand("CHAB");
        groupChainesCouleur = new ButtonGroup();
        radioChainesCouleur = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {                                   
            radioChainesCouleur[i] = new JRadioButton("\u2588\u2588\u2588");
            groupChainesCouleur.add(radioChainesCouleur[i]);
            radioChainesCouleur[i].setForeground((new Color(Integer.parseInt(CHAINES_COULEURS[i], 16))));
            radioChainesCouleur[i].addActionListener(this); radioChainesCouleur[i].setActionCommand("CHAI " + i);
            if( prefChainesCouleur == i) radioChainesCouleur[i].setSelected(true);
        }
        
        labelCouleurDeReserves1 = new JLabel("Réservés1");                  //RESERVES1
        checkReserves1Gras = new JCheckBox("Bold"); checkReserves1Gras.setSelected(prefReserves1Gras);
        checkReserves1Gras.addActionListener(this); checkReserves1Gras.setActionCommand("RS1B");
        groupReserves1Couleur = new ButtonGroup();
        radioReserves1Couleur = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {                                   
            radioReserves1Couleur[i] = new JRadioButton("\u2588\u2588\u2588");
            groupReserves1Couleur.add(radioReserves1Couleur[i]);
            radioReserves1Couleur[i].setForeground((new Color(Integer.parseInt(RESERVES1_COULEURS[i], 16))));
            radioReserves1Couleur[i].addActionListener(this); radioReserves1Couleur[i].setActionCommand("RES1 " + i);
            if( prefReserves1Couleur == i) radioReserves1Couleur[i].setSelected(true);
        }
        
        labelCouleurDeReserves2 = new JLabel("Réservés2");                  //RESERVES2
        checkReserves2Gras = new JCheckBox("Bold"); checkReserves2Gras.setSelected(prefReserves2Gras);
        checkReserves2Gras.addActionListener(this); checkReserves2Gras.setActionCommand("RS2B");
        groupReserves2Couleur = new ButtonGroup();
        radioReserves2Couleur = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {                                   
            radioReserves2Couleur[i] = new JRadioButton("\u2588\u2588\u2588");
            groupReserves2Couleur.add(radioReserves2Couleur[i]);
            radioReserves2Couleur[i].setForeground((new Color(Integer.parseInt(RESERVES2_COULEURS[i], 16))));
            radioReserves2Couleur[i].addActionListener(this); radioReserves2Couleur[i].setActionCommand("RES2 " + i);
            if( prefReserves1Couleur == i) radioReserves2Couleur[i].setSelected(true);
        }
        
        labelCouleurDePrimitifs = new JLabel("Primitifs");                  //PRIMITIFS
        checkPrimitifsGras = new JCheckBox("Bold"); checkPrimitifsGras.setSelected(prefPrimitifsGras);
        checkPrimitifsGras.addActionListener(this); checkPrimitifsGras.setActionCommand("PRIB");
        groupPrimitifsCouleur = new ButtonGroup();
        radioPrimitifsCouleur = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {                                   
            radioPrimitifsCouleur[i] = new JRadioButton("\u2588\u2588\u2588");
            groupPrimitifsCouleur.add(radioPrimitifsCouleur[i]);
            radioPrimitifsCouleur[i].setForeground((new Color(Integer.parseInt(PRIMITIFS_COULEURS[i], 16))));
            radioPrimitifsCouleur[i].addActionListener(this); radioPrimitifsCouleur[i].setActionCommand("PRIM " + i);
            if( prefPrimitifsCouleur == i) radioPrimitifsCouleur[i].setSelected(true);
        }
        
        labelCouleurJavadoc = new JLabel("Javadoc");                //JAVADOC
        checkJavadocGras = new JCheckBox("Bold"); checkJavadocGras.setSelected(prefJavadocGras);
        checkJavadocGras.addActionListener(this); checkJavadocGras.setActionCommand("JAVB");
        radioJavadocCouleur = new JRadioButton[5];
        groupJavadocCouleur = new ButtonGroup();
        for (int i = 0; i < 5; i++) {                              
            radioJavadocCouleur[i] = new JRadioButton("\u2588\u2588\u2588");
            groupJavadocCouleur.add(radioJavadocCouleur[i]);
            radioJavadocCouleur[i].setForeground((new Color(Integer.parseInt(JAVADOC_COULEURS[i], 16))));
            radioJavadocCouleur[i].addActionListener(this); radioJavadocCouleur[i].setActionCommand("JAVA " + i);
            if( prefJavadocCouleur == i) radioJavadocCouleur[i].setSelected(true);
        }
        
        labelCadre = new JLabel("Cadre");
        radioCadreStyle = new JRadioButton[6];
        groupCadreStyle = new ButtonGroup();
        for (int i = 0; i < 6; i++) {                              
            radioCadreStyle[i] = new JRadioButton("style" + ( i + 1));
            groupCadreStyle.add(radioCadreStyle[i]);
            radioCadreStyle[i].addActionListener(this); radioCadreStyle[i].setActionCommand("CADR " + i);
            if( prefCadreStyle == i) radioCadreStyle[i].setSelected(true);
        }
        
        panneauExemple = new JEditorPane();                         //PANNEAU EXEMPLE
        panneauExemple.setMinimumSize(new Dimension(400, 600));
        panneauExemple.setEditable(false);
        
        //RANGEMENT
        panneau.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        //gbc.anchor=GridBagConstraints.NORTH;
        
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=12; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //COMMENTAIRES
        panneau.add(checkDebutFin, gbc);
        
        gbc.gridx=0; gbc.gridy=1; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //RADIOS COULEUR FOND
        panneau.add(labelCouleurDeFond, gbc);
        gbc.gridwidth=1; 
        for (int i = 0; i < 5; i++) {
            gbc.gridx = i + 3; panneau.add(radioFondCouleur[i], gbc);
        }
        
        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //RADIOS COULEUR TEXTE
        panneau.add(labelCouleurDeTexte, gbc);
        gbc.gridx=2; gbc.gridwidth=1; panneau.add(checkTexteGras, gbc);
        for (int i = 0; i < 5; i++) {
            gbc.gridx = i + 3; panneau.add(radioTexteCouleur[i], gbc);
        }
        
        gbc.gridx=0; gbc.gridy=3; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //RADIOS COULEUR COMMENTAIRES
        panneau.add(labelCouleurDeCommentaires, gbc);
        gbc.gridx=2; gbc.gridwidth=1; panneau.add(checkCommentairesGras, gbc);
        for (int i = 0; i < 5; i++) {
            gbc.gridx = i + 3; panneau.add(radioCommentairesCouleur[i], gbc);
        }
        
        gbc.gridx=0; gbc.gridy=4; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //RADIOS COULEUR Javadoc
        panneau.add(labelCouleurJavadoc, gbc);
        gbc.gridx=2; gbc.gridwidth=1; panneau.add(checkJavadocGras, gbc);
        for (int i = 0; i < 5; i++) {
            gbc.gridx = i + 3; panneau.add(radioJavadocCouleur[i], gbc);
        }
        
        gbc.gridx=0; gbc.gridy=5; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //RADIOS COULEUR CHAINES
        panneau.add(labelCouleurDeChaines, gbc);
        gbc.gridx=2; gbc.gridwidth=1; panneau.add(checkChainesGras, gbc);
        for (int i = 0; i < 5; i++) {
            gbc.gridx = i + 3; panneau.add(radioChainesCouleur[i], gbc);
        }
        
        gbc.gridx=0; gbc.gridy=6; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //RADIOS COULEUR RESERVES1
        panneau.add(labelCouleurDeReserves1, gbc);
        gbc.gridx=2; gbc.gridwidth=1; panneau.add(checkReserves1Gras, gbc);
        for (int i = 0; i < 5; i++) {
            gbc.gridx = i + 3; panneau.add(radioReserves1Couleur[i], gbc);
        }
        
        gbc.gridx=0; gbc.gridy=7; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //RADIOS COULEUR RESERVES2
        panneau.add(labelCouleurDeReserves2, gbc);
        gbc.gridx=2; gbc.gridwidth=1; panneau.add(checkReserves2Gras, gbc);
        for (int i = 0; i < 5; i++) {
            gbc.gridx = i + 3; panneau.add(radioReserves2Couleur[i], gbc);
        }
        
        gbc.gridx=0; gbc.gridy=8; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //RADIOS COULEUR PRIMITIFS
        panneau.add(labelCouleurDePrimitifs, gbc);
        gbc.gridx=2; gbc.gridwidth=1; panneau.add(checkPrimitifsGras, gbc);
        for (int i = 0; i < 5; i++) {
            gbc.gridx = i + 3; panneau.add(radioPrimitifsCouleur[i], gbc);
        }
        
        gbc.gridx=0; gbc.gridy=10; gbc.gridwidth=2; gbc.gridheight=1; gbc.weightx=100; gbc.weighty=100; //CADRE
        panneau.add(labelCadre, gbc);
        gbc.gridwidth=1;
        for (int i = 0; i < 6; i++) {
            gbc.gridx = i + 2; panneau.add(radioCadreStyle[i], gbc);
        }
        
        gbc.gridx=12; gbc.gridy=0; gbc.gridwidth=12; gbc.gridheight=20; gbc.weightx=1200; gbc.weighty=100;
        gbc.fill=GridBagConstraints.BOTH;
        panneauExemple.setContentType("text/html");
        panneauExemple.setText(new JavaVersHtml().javaToHtml(exemple, this));
        panneau.add(panneauExemple, gbc);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().substring(0, 4).equals("FOND")) {      //FOND
            prefFondCouleur = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if(ae.getActionCommand().substring(0, 4).equals("TEXT")) {      //TEXTE
            prefTexteCouleur = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if (ae.getActionCommand().equals("TEXB")) {
            prefTexteGras = !prefTexteGras;
        }
        if(ae.getActionCommand().substring(0, 4).equals("CHAI")) {      //CHAINES
            prefChainesCouleur = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if (ae.getActionCommand().equals("CHAB")) {
            prefChainesGras = !prefChainesGras;
        }
        if(ae.getActionCommand().substring(0, 4).equals("COMM")) {      //COMMMENTAIRES
            prefCommentairesCouleur = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if (ae.getActionCommand().equals("COMB")) {
            prefCommentairesGras = !prefCommentairesGras;
        }
        if(ae.getActionCommand().substring(0, 4).equals("RES1")) {      //Reserves1
            prefReserves1Couleur = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if (ae.getActionCommand().equals("RS1B")) {
            prefReserves1Gras = !prefReserves1Gras;
        }
        if(ae.getActionCommand().substring(0, 4).equals("RES2")) {      //Reserves2
            prefReserves2Couleur = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if (ae.getActionCommand().equals("RS2B")) {
            prefReserves2Gras = !prefReserves2Gras;
        }
        if(ae.getActionCommand().substring(0, 4).equals("JAVA")) {      //JAVADOC
            prefJavadocCouleur = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if (ae.getActionCommand().equals("JAVB")) {
            prefJavadocGras = !prefJavadocGras;
        }
        if(ae.getActionCommand().substring(0, 4).equals("PRIM")) {      //PRIMITIFS
            prefPrimitifsCouleur = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if (ae.getActionCommand().equals("PRIB")) {
            prefPrimitifsGras = !prefPrimitifsGras;
        }
        if(ae.getActionCommand().substring(0, 4).equals("CADR")) {      //CADRE
            prefCadreStyle = Integer.valueOf(ae.getActionCommand().substring(5, 6));
        }
        if (ae.getActionCommand().equals("ENTT")) {
            prefEntete = !prefEntete;
        }
        panneauExemple.setText(new JavaVersHtml().javaToHtml(exemple, this));
    }

    public JPanel getPanel () { return panneau; }

    public void saveValues ()
    {
        // Save the preference value in the BlueJ properties file
       bluej.setExtensionPropertyString("Entete_debut_fin", Boolean.toString(prefEntete));
       bluej.setExtensionPropertyString("Style_du_cadre", Integer.toString(prefCadreStyle));
       bluej.setExtensionPropertyString("Couleur_de_Fond", Integer.toString(prefFondCouleur));
       bluej.setExtensionPropertyString("Texte_en_gras", Boolean.toString(prefTexteGras));
       bluej.setExtensionPropertyString("Couleur_du_Texte", Integer.toString(prefTexteCouleur));
       bluej.setExtensionPropertyString("Commentaires_en_gras", Boolean.toString(prefCommentairesGras));
       bluej.setExtensionPropertyString("Couleur_des_Commentaires", Integer.toString(prefCommentairesCouleur));
       bluej.setExtensionPropertyString("Chaines_en_gras", Boolean.toString(prefChainesGras));
       bluej.setExtensionPropertyString("Couleur_des_Chaines", Integer.toString(prefChainesCouleur));
       bluej.setExtensionPropertyString("Reserves1_en_gras", Boolean.toString(prefReserves1Gras));
       bluej.setExtensionPropertyString("Couleur_des_Reserves1", Integer.toString(prefReserves1Couleur));
       bluej.setExtensionPropertyString("Reserves2_en_gras", Boolean.toString(prefReserves2Gras));
       bluej.setExtensionPropertyString("Couleur_des_Reserves2", Integer.toString(prefReserves2Couleur));
       bluej.setExtensionPropertyString("Javadoc_en_gras", Boolean.toString(prefJavadocGras));
       bluej.setExtensionPropertyString("Couleur_de_Javadoc", Integer.toString(prefJavadocCouleur));
       bluej.setExtensionPropertyString("Primitifs_en_gras", Boolean.toString(prefPrimitifsGras));
       bluej.setExtensionPropertyString("Couleur_des_Primitifs", Integer.toString(prefPrimitifsCouleur));
    }

    public void loadValues ()
    {
        // Load the property value from the BlueJ proerties file, 
        // default to an empty string
        try {
            prefEntete = Boolean.parseBoolean(bluej.getExtensionPropertyString("Entete_debut_fin", "true"));
            prefCadreStyle = Integer.valueOf(bluej.getExtensionPropertyString("Style_du_cadre","1"));
            prefFondCouleur = Integer.valueOf(bluej.getExtensionPropertyString("Couleur_de_Fond", "0"));
            prefChainesCouleur = Integer.valueOf(bluej.getExtensionPropertyString("Couleur_des_Chaines", "0"));
            prefChainesGras = Boolean.parseBoolean(bluej.getExtensionPropertyString("Chaines_en_gras", "false"));
            prefTexteCouleur = Integer.valueOf(bluej.getExtensionPropertyString("Couleur_du_Texte", "0"));
            prefTexteGras = Boolean.parseBoolean(bluej.getExtensionPropertyString("Texte_en_gras", "false"));
            prefCommentairesCouleur = Integer.valueOf(bluej.getExtensionPropertyString("Couleur_des_Commentaires", "0"));
            prefCommentairesGras = Boolean.parseBoolean(bluej.getExtensionPropertyString("Commentaires_en_gras", "false"));
            prefReserves1Couleur = Integer.valueOf(bluej.getExtensionPropertyString("Couleur_des_Reserves1", "0"));
            prefReserves1Gras = Boolean.parseBoolean(bluej.getExtensionPropertyString("Reserves1_en_gras", "true"));
            prefReserves2Couleur = Integer.valueOf(bluej.getExtensionPropertyString("Couleur_des_Reserves2", "0"));
            prefReserves2Gras = Boolean.parseBoolean(bluej.getExtensionPropertyString("Reserves2_en_gras", "true"));
            prefJavadocCouleur = Integer.valueOf(bluej.getExtensionPropertyString("Couleur_de_Javadoc", "0"));
            prefJavadocGras = Boolean.parseBoolean(bluej.getExtensionPropertyString("Javadoc_en_gras", "false"));
            prefPrimitifsCouleur = Integer.valueOf(bluej.getExtensionPropertyString("Couleur_des_Primitifs", "0"));
            prefPrimitifsGras = Boolean.parseBoolean(bluej.getExtensionPropertyString("Primitifs_en_gras", "true"));
        } catch(Exception e) {}
    }

    public String getPrefFondCouleur() {
        return FOND_COULEURS[prefFondCouleur];
    }

    public String getPrefTexteCouleur() {
        return TEXTE_COULEURS[prefTexteCouleur];
    }

    public boolean isPrefTexteGras() {
        return prefTexteGras;
    }

    public String getPrefCommentairesCouleur() {
        return COMMS_COULEURS[prefCommentairesCouleur];
    }

    public boolean isPrefCommentairesGras() {
        return prefCommentairesGras;
    }

    public String getPrefReserves1Couleur() {
        return RESERVES1_COULEURS[prefReserves1Couleur];
    }

    public boolean isPrefReserves1Gras() {
        return prefReserves1Gras;
    }

    public String getPrefReserves2Couleur() {
        return RESERVES2_COULEURS[prefReserves2Couleur];
    }

    public boolean isPrefReserves2Gras() {
        return prefReserves2Gras;
    }

    public String getPrefChainesCouleur() {
        return CHAINES_COULEURS[prefChainesCouleur];
    }

    public boolean isPrefChainesGras() {
        return prefChainesGras;
    }

    public String getPrefPrimitifsCouleur() {
        return PRIMITIFS_COULEURS[prefPrimitifsCouleur];
    }

    public boolean isPrefPrimitifsGras() {
        return prefPrimitifsGras;
    }

    public String getPrefJavadocCouleur() {
        return JAVADOC_COULEURS[prefJavadocCouleur];
    }

    public boolean isPrefJavadocGras() {
        return prefJavadocGras;
    }
    
    public String getPrefCadreStyle() {
        return CADRE_STYLES[prefCadreStyle];
    }
    
    public boolean isPrefEntete() {
        return prefEntete;
    }
}