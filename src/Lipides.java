import java.*;
import java.io.*;
import java.util.*;

public class Lipides extends Elements{
	private String type = "lipides";

  public Lipides(String nom){
    super(nom);
  }
	
  public Lipides(String nom, String couleur){
    super(nom, couleur);
  }
  
  public Lipides(String nom, String couleur, String joueur) {
  	super(nom, couleur, joueur);
  }
  
  public void affiche(){
		super.afficheId();
	}
}
