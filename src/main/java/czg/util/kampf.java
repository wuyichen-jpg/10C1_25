import java.util.random.*;

public class kampf {
  private String name;
  private int lehrer_leben, schueler_leben, multiplier, grundleben;
  List<Integer> items = new ArrayList<>();

  multiplier = 2;
  grundleben = 5;
  
  //Spieler und Lehrer hinzufügen

  // Implementierung der Züge
    // Angriff
    // Verteidigung
    // Berechnung des Schadens
    // Wiederholung

  public Lehrer addLehrer(string Name) {
    lehrer_object = new Lehrer(Name);
    int leben = lehrer_object.getLevel() * multiplier + grundleben;
    items = lehrer_object.getItems();
  }

  public Schueler addSchueler(string Name){
    schueler_object = new Schueler(Name);
    int leben = schueler_object.getLeben();

  } 
  // Frage? Wer ist dran?
  // -> Lehrer Zufall zieht Item??Random package???
  // -> Schüler kann Item aussuchen
  
  public void lehrer_zug() {
    Random rand = new Random();
    
    gewaehlt = rand.nextInt(4);
    
    schueler_object.setLeben(schueler_object.getLeben() - attack(gewaehlt - 1));
    
    
    
  }
  

  
}
