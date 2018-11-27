import java.*;
import java.io.*;
import java.util.*;

abstract public class Elements {
	private int positionX;
	private int positionY;
	private String type;
	private String id; // Enzymes, Metabolites ou Lipides
	private String couleur; // Rouge, Bleu, Vert, Jaune ou None
	private String joueur; // Joueur1, Joueur2, ou None

	public Elements(String nom) {
		positionX = 0;
		positionY = 0;
		type = "none";
		id = nom;
		couleur = "none";
		joueur = "none";
	}
	public Elements(String nom, String couleur) {
		positionX = 0;
		positionY = 0;
		type = "none";
		id = nom;
		setCouleur(couleur);
		joueur = "none";
	}
	public Elements(String nom, String couleur, String joueur) {
		setPositionX(positionX);
		setPositionY(positionY);
		setType(type);
		setId(nom);
		setCouleur(couleur);
		setJoueur(joueur);
	}
// Getter
	public String getType(){
		return type;
	}
	public String getId() {
		return id;
	}
	public String getCouleur() {
		return couleur;
	}
	public String getJoueur() {
		return joueur;
	}
	public int getPositionX() {
		return positionX;
	}
	public int getPositionY() {
		return positionY;
	}
// Setter
	public void setType(String type){
		if ((type == "enzymes") || (type == "metabolites") || (type == "lipides") || (type == "non")){
			this.type = type;
		}
	}

	public void setId(String nom) {
		id = nom;
	}
	public void setCouleur(String couleur) {
		if ((couleur == "rouge") || (couleur == "bleu") || (couleur == "jaune") || (couleur == "vert") || (couleur == "none")){
			this.couleur = couleur;
		}
	}
	public void setJoueur(String joueur) {
		if ((joueur == "Joueur1") || (joueur == "Joueur2") || (joueur == "none")){
			this.joueur = joueur;
		}
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	abstract public void affiche();

	public void afficheId(){
		System.out.println("Type: "+type);
		System.out.println("Nom: "+id);
		System.out.println("Coordonnées en X: "+positionX);
		System.out.println("Coordonnées en Y: "+positionY);
	}
}
