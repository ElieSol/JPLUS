import java.*;
import java.io.*;
import java.util.*;

public class Grille {
  //Attributs
  private int nblig;
  private int nbcol;
  private Elements temp; // Creation d'une variable temporaire pour le stockage de l'élément contenu dans une case
  private Vector tempPM = new Vector();
  Position metabolites = new Position("metabolites");
  Elements grille [][];

// Défintion de la grille de jeu
  public Grille(int n, int p) { // n est le nb de lignes et p le nb de colonnes
    nblig = n;
    nbcol = p;
    grille = new Elements [nblig][nbcol]; // taille de la Grille
    for(int i=0 ; i<nblig; i++){
      for(int j=0 ; j<nbcol; j++){
        grille[i][j] = null; //on remplit la grille de x
      }
    }
  }
//Methodes
  public void afficher(){
    System.out.println(); // avoir un retour à la ligne
    System.out.print(" ");
    for(int j=0 ; j<nbcol; j++){
      if(j<10){ System.out.print("  |  "+j); }
      if(j>=10){ System.out.print("  | "+j); }
    }
    System.out.println("");
    for(int i=0 ; i<nblig; i++){
      System.out.println(" -------------------------------------------------------------------------------------------");
      if(i<10){ System.out.print(i+" "); }
      if(i>=10){ System.out.print(i); }
      for(int j=0 ; j<nbcol; j++){
        if(grille[i][j] == null){ System.out.print(" | "+"..."); }
        if(grille[i][j] != null){
          String name = grille[i][j].getId();
          System.out.print(" | "+name);
        }
      }
      System.out.println(" | ");
    }
    System.out.println(" -------------------------------------------------------------------------------------------");
    System.out.println();
  }


  
  
  // PLACEMENT
// Placement des lipides + sauvegarde dans vecteur Joueur
  public void affichageLipidesJoueur1(Joueur player){
    String couleur = null;
    String nom = "1L1";
    for(int i=1; i<=3; i++){
      if(i==1){
        for(int j=3; j<=13; j+=2){
          setLipides(player, i, j,couleur,nom);
        }
      }
      if(i==2){
        for(int j=2; j<=14; j+=2){
          setLipides(player, i, j,couleur,nom);
        }
      }
      if(i==3){
        for(int j=1; j<=13; j+=2){
          setLipides(player, i, j,couleur,nom);
        }
      }
    }
  }

  public void affichageLipidesJoueur2(Joueur player){
    String couleur = null;
    String nom = "2L2";
    for(int i=11; i<=13; i++){
      if(i==11){
        for(int j=3; j<=13; j+=2){
            setLipides(player, i, j,couleur,nom);
        }
      }
      if(i==12){
        for(int j=2; j<=14; j+=2){
            setLipides(player, i, j,couleur,nom);
        }
      }
      if(i==13){
        for(int j=1; j<=13; j+=2){
            setLipides(player, i, j,couleur,nom);
        }
      }
    }
  }
// Placement des enzymes + sauvegarde dans vecteur Joueur
  public void affichageEzJ1(Joueur player){
    for(int j=0; j<=6; j+=2){
        setEnzymes(player, 0, j, "jaune", "1EJ");
    }
    for(int j=8; j<=14; j+=2){
      setEnzymes(player, 0, j, "vert", "1EV");
    }
  }

  public void affichageEzJ2(Joueur player){
    for(int j=0; j<=6; j+=2){
        setEnzymes(player, 14, j, "blue", "2EB");
    }
    for(int j=8; j<=14; j+=2){
        setEnzymes(player, 14, j, "red", "2ER");
    }
  }
// Placement des metabolites
  public void placementMetabolites(){
    setMetabolites("MJ ","jaune");
    setMetabolites("MV ","vert");
    setMetabolites("MR ","rouge");
    setMetabolites("MB ","bleu");
    int nbJ = countElements("MJ ");
    int nbV = countElements("MV ");
    int nbR = countElements("MR ");
    int nbB = countElements("MB ");
    System.out.println("Nombre de metabolites Jaune = "+nbJ);
    System.out.println("Nombre de metabolites Vert = "+nbV);
    System.out.println("Nombre de metabolites Rouge = "+nbR);
    System.out.println("Nombre de metabolites Bleu = "+nbB);
    afficher();
  }

  public void setMetabolites(String nom, String couleur) {
    int compt = 0;
    while (compt<10){
      Random e = new Random();
      int ord = 4 + e.nextInt(11 - 4);
      Random j = new Random();
      int abs = 0 + j.nextInt(15 - 0);
      int res = metabolites.comparaison(ord,abs);
      while (res==1){
        e = new Random();
        ord = 4 + e.nextInt(11 - 4);
        j = new Random();
        abs = 0 + j.nextInt(15 - 0);
        res = metabolites.comparaison(ord,abs);
      }
      grille[ord][abs] = new Metabolites(nom,couleur);
      grille[ord][abs].setPositionX(abs);
      grille[ord][abs].setPositionY(ord);
      if(grille[ord][abs].getId()==nom){
        compt++;
      }
      metabolites.ajouterPosition(grille[ord][abs]);
    }
  }
// DEPLACEMENT
// - Lipides
  public void deplacementLipides1(){
    System.out.println("Veuillez sélectionner le lipide que vous voulez déplacer:");
    System.out.println("Veuillez saisir les coordonnées en x:");
    int pX = saisie_entier();
    System.out.println("Veuillez saisir les coordonnées en y:");
    int pY = saisie_entier();
    while(grille[pY][pX]==null || grille[pY][pX].getId()!="1L1"){
      System.out.println("Erreur: Il n'y a rien à déplacer à cette endroit, veuillir refaire une sélection:");
      System.out.println("Veuillez saisir les coordonnées en x:");
      pX = saisie_entier();
      System.out.println("Veuillez saisir les coordonnées en y:");
      pY = saisie_entier();
    }
    System.out.println("De combien de cases voulez vous avancer ?");
    int rep = saisie_entier();
    while(rep > 3){ // Gestion des erreurs saisie du nb de cases
      System.out.println("Chiffre trop grand, veuillez recommencez");
      rep = saisie_entier();
    }
    rep = gestionLimites(pY,pX,rep, "Y+");
    for(int i = 1; i<=rep; i++){  // Gestion des obstacles en Bas
      if(grille[pY+i][pX] != null){
        rep = i + rep - (rep + 1);
        break;
      }
    }
    temp = grille[pY][pX];
    grille[pY+rep][pX]= temp;
    if(rep!=0){
      grille[pY][pX]=null;
    }
    afficher();
  }
//
  public void deplacementLipides2(){
    System.out.println("Veuillez sélectionner le lipide que vous voulez déplacer:");
    System.out.println("Veuillez saisir les coordonnées en x:");
    int pX = saisie_entier();
    System.out.println("Veuillez saisir les coordonnées en y:");
    int pY = saisie_entier();
    while(grille[pY][pX]==null || grille[pY][pX].getId()!="2L2"){
      System.out.println("Erreur: Il n'y a rien à déplacer à cette endroit, veuillir refaire une sélection:");
      System.out.println("Veuillez saisir les coordonnées en x:");
      pX = saisie_entier();
      System.out.println("Veuillez saisir les coordonnées en y:");
      pY = saisie_entier();
    }
    System.out.println("De combien de cases voulez vous avancer ?");
    int rep = saisie_entier();
    while(rep > 3){ // Gestion des erreurs saisie du nb de cases
      System.out.println("Chiffre trop grand, veuillez recommencez");
      rep = saisie_entier();
    }
    rep = gestionLimites(pY,pX,rep, "Y-");
    for(int i = 1; i<=rep; i++){   // Gestion des obstacles en Haut
      if(grille[pY-i][pX]!=null){
        rep = i - rep + (rep-1);
        break;
      }
    }
    temp = grille[pY][pX];
    grille[pY-rep][pX]= temp;
    if(rep!=0){
      grille[pY][pX]=null;
    }
    afficher();
  }
//

  public void afficheChoix(){
    System.out.println("Tapez 1 pour aller en haut");
    System.out.println("Tapez 2 pour aller à droite");
    System.out.println("Tapez 3 pour aller à gauche");
    System.out.println("Tapez 4 pour aller en bas");
  }
// - Enzymes
  public void deplacementEnzymes(Joueur player){
    System.out.println("Veuillez sélectionner l'enzyme que vous voulez déplacer:");
    System.out.println("Veuillez saisir les coordonnées en x:");
    int pX = saisie_entier();
    System.out.println("Veuillez saisir les coordonnées en y:");
    int pY = saisie_entier();
    if(player.getIdentifiant() == "Joueur1"){
      while(grille[pY][pX]==null || grille[pY][pX].getId() != "1EV" && grille[pY][pX].getId() != "1EJ"){
        System.out.println("Erreur: L'élément que vous avez sélectionné n'est pas une enzyme !");
        System.out.println("Veuillez saisir les coordonnées en x:");
        pX = saisie_entier();
        System.out.println("Veuillez saisir les coordonnées en y:");
        pY = saisie_entier();
      }
    }
    if(player.getIdentifiant() == "Joueur2"){
      while(grille[pY][pX]==null || grille[pY][pX].getId() != "2ER" && grille[pY][pX].getId() != "2EB"){
        System.out.println("Erreur: L'élément que vous avez sélectionné n'est pas une enzyme !");
        System.out.println("Veuillez saisir les coordonnées en x:");
        pX = saisie_entier();
        System.out.println("Veuillez saisir les coordonnées en y:");
        pY = saisie_entier();
      }
    }
    System.out.println("Veuillez sélectionner dans quel sens vous voulez déplacer l'enzyme:");
    afficheChoix();
    int direction = saisie_entier();
    int nb  = 1;
    String couleurEz = grille[pY][pX].getCouleur();
    switch (direction) {
      case 1: depHaut(pY, pX, temp, nb, couleurEz, player) ;break;
      case 2: depDroite(pY, pX, temp, nb, couleurEz, player) ;break;
      case 3: depGauche(pY, pX, temp, nb, couleurEz, player) ;break;
      case 4: depBas(pY, pX, temp, nb, couleurEz, player) ;break;
      default:
      System.out.println("Veuillez recommencer votre saisie:");
    }
    afficher();
  }
// Deplacement multidirectionnel - HDGB - Enzymes
  public void depHaut(int pY, int pX, Elements temp, int nb, String couleurEz, Joueur player){
    nb = gestionLimites(pY, pX, nb, "Y-");
    if(grille[pY-nb][pX]!=null){
      if(grille[pY-nb][pX].getCouleur()==couleurEz && grille[pY-nb][pX].getType() == "metabolites"){
        player.ajouterUnMetabo();
        nb = 1;
      }
      else{
        nb = 0;
      }
    }
    temp = grille[pY][pX];
    grille[pY-nb][pX]= temp;
    if(nb!=0){
      grille[pY][pX]=null;
    }
  }
  public void depDroite(int pY, int pX, Elements temp, int nb, String couleurEz, Joueur player){
    nb = gestionLimites(pY, pX, nb, "X+");
    if(grille[pY][pX+nb]!=null){
      if(grille[pY][pX+nb].getCouleur()==couleurEz && grille[pY][pX+nb].getType() == "metabolites"){
        player.ajouterUnMetabo();
        nb = 1;
      }
      else{
        nb = 0;
      }
    }
    temp = grille[pY][pX];
    grille[pY][pX+nb]= temp;
    if(nb!=0){
      grille[pY][pX]=null;
    }
  }
  public void depGauche(int pY, int pX, Elements temp, int nb, String couleurEz, Joueur player){
    nb = gestionLimites(pY, pX, nb, "X-");
    if(grille[pY][pX-nb]!=null){
      if(grille[pY][pX-nb].getCouleur()==couleurEz && grille[pY][pX+nb].getType() == "metabolites"){
        player.ajouterUnMetabo();
        nb = 1;
      }
      else{
        nb = 0;
      }
    }
    temp = grille[pY][pX];
    grille[pY][pX-nb]= temp;
    if(nb!=0){
      grille[pY][pX]=null;
    }
  }
  public void depBas(int pY, int pX, Elements temp, int nb, String couleurEz, Joueur player){
    nb = gestionLimites(pY, pX, nb, "Y+");
    if(grille[pY+nb][pX]!=null){
      if(grille[pY+nb][pX].getCouleur()==couleurEz && grille[pY][pX+nb].getType() == "metabolites"){
        player.ajouterUnMetabo();
        nb = 1;
      }
      else{
        nb = 0;
      }
    }
    temp = grille[pY][pX];
    grille[pY+nb][pX]= temp;
    if(nb!=0){
      grille[pY][pX]=null;
    }
  }
// - Metabolites
  public void deplacementMetabolites(){
    for(int i=0; i<=39; i++){
      metabolites.selectionPosition();
      int pX = metabolites.getPX();
      int pXo = pX;
      int pY = metabolites.getPY();
      int pYo = pY;
      temp = grille[pY][pX];
      Random n = new Random();
      int nb = 1+ n.nextInt(3 - 1);
      Random d = new Random();
      int dir = 1+ d.nextInt(4 - 1);
      switch (dir) {
        case 1: pY = dpHaut(pY, pX, temp, nb) ;break;
        case 2: pX = dpDroite(pY, pX, temp, nb) ;break;
        case 3: pX =dpGauche(pY, pX, temp, nb) ;break;
        case 4: pY =dpBas(pY, pX, temp, nb) ;break;
      }
      temp.setPositionX(pX);
      temp.setPositionY(pY);
      grille[pY][pX] = temp;
      if(pY != pYo || pX != pXo){
        grille[pYo][pXo]=null;
      }
      tempPM.addElement(temp);
      metabolites.supprimePosition();
      }
      for(int i=0; i<=39; i++){
        for(Iterator it = tempPM.iterator(); it.hasNext();){
          Elements courant = (Elements)it.next();
          temp = courant;
          break;
        }
        metabolites.ajouterPosition(temp);
        for(Iterator it = tempPM.iterator(); it.hasNext();){
          Elements courant = (Elements)it.next();
          tempPM.remove(courant);
          break;
      }
    }
  }

  // Deplacement multidirectionnel - HDGB - metabolites
  public int dpHaut(int pY, int pX, Elements temp, int nb){
    nb = gestionLimites(pY, pX, nb, "Y-");
    for(int i = 1; i<=nb; i++){
      if(grille[pY-i][pX]!=null){
        nb = i - nb + (nb-1);
        break;
      }
    }
    pY = pY-nb;
    return pY;
  }
  public int dpDroite(int pY, int pX, Elements temp, int nb){
    nb = gestionLimites(pY, pX, nb, "X+");
    for(int i = 1; i<=nb; i++){
      if(grille[pY][pX+i]!=null){
        nb = i + nb - (nb+1);
        break;
      }
    }
    pX = pX + nb;
    return pX;
  }
  public int dpGauche(int pY, int pX, Elements temp, int nb){
    nb = gestionLimites(pY, pX, nb, "X-");
    for(int i = 1; i<=nb; i++){
      if(grille[pY][pX-i]!=null){
        nb = i - nb + (nb-1);
        break;
      }
    }
    pX = pX - nb;
    return pX;
  }
  public int dpBas(int pY, int pX, Elements temp, int nb){
    nb = gestionLimites(pY, pX, nb, "Y+");
    for(int i = 1; i<=nb; i++){
      if(grille[pY+i][pX]!=null){
        nb = i + nb - (nb+1);
        break;
      }
    }
    pY = pY + nb;
    return pY;
  }

// FONCTIONS UTILITAIRES
// Gestion des limites du plateau
  public int gestionLimites(int pY, int pX, int rep, String valeurAGerer){
    if(valeurAGerer == "Y+"){
      while(pY+rep > 14){ // Limites bas
        rep = rep - 1;
        if(pY+rep<=14){
          break;
        }
      }
    }
    if(valeurAGerer == "Y-"){
      while(pY-rep < 0){ // Limites haut
        rep = rep - 1;
        if(pY-rep>=0){
          break;
        }
      }
    }
    if(valeurAGerer == "X+"){
      while(pX+rep > 14){ // Limites droite
        rep = rep - 1;
        if(pY+rep<=14){
          break;
        }
      }
    }
    if(valeurAGerer == "X-"){
      while(pX-rep < 0){ // Limites gauche
        rep = rep - 1;
        if(pY+rep>=0){
          break;
        }
      }
    }
    return rep;
  }
//
  public int countElements(String id){
    int compteur = 0;
    for(int i=0 ; i<nblig; i++){
      for(int j=0 ; j<nbcol; j++){
        if(grille[i][j] != null){
          if(grille[i][j].getId() == id){
            compteur ++;
          }
        }
      }
    }
    return compteur;
  }

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
// Sauvegarde des elements dans le vecteur Joueur
  public void setLipides(Joueur player, int i, int j, String couleur, String nom){
    grille[i][j] = new Lipides(nom,couleur);
    grille[i][j].setPositionY(i);
    grille[i][j].setPositionX(j);
    player.ajouterElement(grille[i][j]);
  }
//
  public void setEnzymes(Joueur player, int i, int j, String couleur, String nom){
    grille[i][j] = new Enzymes(nom,couleur);
    grille[i][j].setPositionY(i);
    grille[i][j].setPositionX(j);
    player.ajouterElement(grille[i][j]);
  }
// Saisie de caracteres
  public String saisie_chaine(){
    try{
      BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
      String chaine=buff.readLine();
      return chaine;
    }
    catch(IOException e){
      System.out.println(" Impossible de travailler" +e);
      return null;
    }
  }

  public int saisie_entier (){
    try{
      BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
      String chaine=buff.readLine();
      int num = Integer.parseInt(chaine);
      return num;
    }
    catch(IOException e){
      return 0;
    }
  }
}
