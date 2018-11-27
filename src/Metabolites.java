import java.*;
import java.io.*;
import java.util.*;

public class Metabolites extends Elements{
  private String joueur = null;

  public Metabolites(String nom){
    super(nom);
  }
  
  public Metabolites(String nom, String couleur){
    super(nom, couleur);
    super.setType("metabolites");
  }
  
  public void affiche(){
  		super.afficheId();
  }
}
