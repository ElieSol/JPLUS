import java.*;
import java.io.*;
import java.util.*;

public class Enzymes extends Elements {

  public Enzymes(String id){
    super(id);
    super.setType("enzymes");
  }
  
  public Enzymes(String nom, String couleur){
    super(nom, couleur);
    super.setType("enzymes");
  }
  
  public Enzymes(String nom, String couleur, String joueur) {
  	super(nom, couleur, joueur);
    super.setType("enzymes");
  }

  public void affiche(){
      super.afficheId();
  }
}
