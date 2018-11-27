import java.*;
import java.io.*;
import java.util.*;

public class Position{
  private String positionElement;
  private int positionX; // numero de la colonne
  private int positionY; // numero de la ligne
  private Vector positionXY;
  private Elements item;
  private int abs;
  private int ord;
  public Position(String chaine){
		positionElement = chaine;
		positionXY = new Vector();
	}
//
	public int getPY() {
		for(Iterator it = positionXY.iterator(); it.hasNext();){
    	Elements courant = (Elements)it.next();
			int positionY = courant.getPositionY();
			return positionY;
		}
		return positionY;
	}

	public int getPX() {
		for(Iterator it = positionXY.iterator(); it.hasNext();){
    	Elements courant = (Elements)it.next();
			int positionX = courant.getPositionX();
			return positionX;
		}
		return positionX;
	}

	public int getPositionY(Elements item) {
		int positionY = item.getPositionY();
		return positionY;
	}

	public int getPositionX(Elements item) {
		int positionX = item.getPositionX();
		return positionX;
	}

	public String getIdentifiant(){
    return positionElement;
  }
//
	public int comparaison(int ord, int abs){
		for(Iterator it = positionXY.iterator(); it.hasNext();){
    	Elements courant = (Elements)it.next();
			int Xcomp = courant.getPositionX();
			int Ycomp = courant.getPositionY();
			if (Xcomp==abs && Ycomp==ord){
      	return 1;
    	}
		}
		return 0;
	}
//
	public void selectionPosition(){
		for(Iterator it = positionXY.iterator(); it.hasNext();){
    	Elements courant = (Elements)it.next();
			break;
		}
	}
//
	public void supprimePosition(){
		for(Iterator it = positionXY.iterator(); it.hasNext();){
			Elements courant = (Elements)it.next();
			positionXY.remove(courant);
			break;
		}
	}
//
	public void ajouterPosition(Elements item){
		positionXY.addElement(item);
	}

	public void ajouterElement(Elements item){
		positionXY.addElement(item);
	}

//
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
//
	public void affichePosition(){
			int compteur = 0;
			System.out.println("Position de : "+positionElement);
			System.out.println("-----------------------------");
			for(Iterator it = positionXY.iterator(); it.hasNext();){
				Elements courant = (Elements)it.next();
				compteur++;
  	   }
			System.out.println("Nombre de vecteurs = "+compteur);
  	 }
}
