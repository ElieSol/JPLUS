import java.*;
import java.io.*;

public class Main {
  public static void main(String[] args){
    Joueur J1 = new Joueur("Joueur1");
    Joueur J2 = new Joueur("Joueur2");
    Grille g1 = new Grille(15,15);
    menuJeu(g1,J1,J2);
  }
  public static void menuJeu(Grille g1, Joueur J1, Joueur J2){
    for(;;){
      logo();
      System.out.println("Que voulez vous faire ?");
      System.out.println("_______________________MENU_______________________");
      System.out.println("Tapez 1 pour démarrer une nouvelle partie");
      System.out.println("Tapez 2 pour quitter");
      System.out.println("__________________________________________________");
      int rep = saisie_entier();
      switch (rep) {
        case 1 : nouvellePartie(g1,J1,J2) ;break;
        case 2 : System.exit(0) ;break;
        default:
        System.out.println("Erreur de saisie: Veuillez saisir un choix a nouveau ");
      }
    }
  }

  public static void nouvellePartie(Grille g1, Joueur J1, Joueur J2){
    g1.affichageLipidesJoueur1(J1);
    g1.affichageLipidesJoueur2(J2);
    g1.affichageEzJ1(J1);
    g1.affichageEzJ2(J2);
    g1.placementMetabolites();
    for(;;){
      int scoreJoueur1 = J1.score();
      int scoreJoueur2 = J2.score();
      int nbMetaboTotal = g1.countElements("MJ ") + g1.countElements("MV ") + g1.countElements("MR ") + g1.countElements("MB ");
      g1.deplacementMetabolites();
      System.out.println(" ------------------------------------Tour du Joueur 1------------------------------------");
      g1.deplacementLipides1();
      g1.deplacementEnzymes(J1);
      g1.affichageLipidesJoueur2(J2);
      System.out.println(" ------------------------------------Tour du Joueur 2------------------------------------");
      g1.deplacementLipides2();
      g1.deplacementEnzymes(J2);
      if(scoreJoueur1 >= 25){
        System.out.println("Le joueur 1 a gagné la partie !"); break;
      }
      if(scoreJoueur2 >= 25){
        System.out.println("Le joueur 2 a gagné la partie !"); break;
      }
      if(nbMetaboTotal == 0){
        System.out.println("Fin de la partie: tous les métabolites ont été capturés !"); break;
      }
    }
  }

//
  public static void logo(){
    System.out.println(" ---------------------◼︎◼︎-◼︎◼︎◼︎◼︎◼︎◼︎◼︎---◼︎◼︎------◼︎◼︎------◼︎◼︎---◼︎◼︎◼︎◼︎◼︎----------------------------");
    System.out.println(" ---------------------◼︎◼︎-◼︎◼︎----◼︎◼︎--◼︎◼︎------◼︎◼︎------◼︎◼︎--◼︎◼︎---◼︎◼︎--------◼︎------------------");
    System.out.println(" ---------------------◼︎◼︎-◼︎◼︎-----◼︎◼︎-◼︎◼︎------◼︎◼︎------◼︎◼︎-◼︎◼︎-----◼︎◼︎-------◼︎------------------");
    System.out.println(" ---------------------◼︎◼︎-◼︎◼︎-----◼︎◼︎-◼︎◼︎------◼︎◼︎------◼︎◼︎--◼︎◼︎---------◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎--------------");
    System.out.println(" ---------------------◼︎◼︎-◼︎◼︎----◼︎◼︎--◼︎◼︎------◼︎◼︎------◼︎◼︎---◼︎◼︎◼︎◼︎◼︎---------◼︎------------------");
    System.out.println(" ---------------------◼︎◼︎-◼︎◼︎◼︎◼︎◼︎◼︎----◼︎◼︎------◼︎◼︎------◼︎◼︎-------◼︎◼︎--------◼︎------------------");
    System.out.println(" --------------◼︎◼︎-----◼︎◼︎-◼︎◼︎--------◼︎◼︎------◼︎◼︎------◼︎◼︎--------◼︎◼︎--------------------------");
    System.out.println(" --------------◼︎◼︎-----◼︎◼︎-◼︎◼︎--------◼︎◼︎------◼︎◼︎------◼︎◼︎-◼︎◼︎-----◼︎◼︎--------------------------");
    System.out.println(" ---------------◼︎◼︎----◼︎◼︎-◼︎◼︎--------◼︎◼︎-------◼︎◼︎----◼︎◼︎---◼︎◼︎----◼︎◼︎--------------------------");
    System.out.println(" ----------------◼︎◼︎◼︎◼︎◼︎◼︎--◼︎◼︎--------◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎--◼︎◼︎◼︎◼︎◼︎◼︎-----◼︎◼︎◼︎◼︎◼︎◼︎---------------------------");
  }
//
  public static int saisie_entier (){
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
