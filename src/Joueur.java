import java.*;
import java.io.*;
import java.util.*;

public class Joueur{
	private String identifiant;
	private Vector mesElements;
	private int nbMetabo;
	private Elements item;
	public Joueur(String chaine){
		identifiant = chaine;
		mesElements = new Vector();
		nbMetabo = 0;
	}

	public String getIdentifiant(){
		return identifiant;
	}

	public void afficheDonnees(){
	    System.out.println("Éléments du Joueur: "+identifiant);
	    System.out.println("-----------------------------");
	    for(Iterator it = mesElements.iterator(); it.hasNext();){
	      Elements courant = (Elements)it.next();
	      courant.affiche();
	   	}
			System.out.println("Vous avez capturé: "+nbMetabo+" métabolites");
	 }

	 public void ajouterElement(Elements item){
		 mesElements.addElement(item);
	 }

	 public void miseAJourNbMetabo(int nbMetabo){
		 this.nbMetabo = nbMetabo;
	 }
	 public void ajouterUnMetabo(){
		 nbMetabo ++;
	 }
	 public int score(){
		 return nbMetabo;
	 }
}
