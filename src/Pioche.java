import java.util.Stack;

public class Pioche {
    private Stack<Pokemon> m_pioche;
    public Pioche(Jeu jeu, int taille){
        m_pioche = new Stack<>();
        for(int i = 0; i<taille;i++){
            m_pioche.push(new Pokemon(jeu));
        }
    }
    public Pokemon piocher(){
        return this.m_pioche.pop();
    }
}
