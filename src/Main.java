import java.util.ArrayList;

public class Main {
    private ArrayList<Pokemon> m_main;
    public Main(Pioche pioche){
        m_main = new ArrayList<>(5);
        for(int i = 0; i<5;i++){
            this.m_main.add(pioche.piocher());
        }
    }
    public void piocher(Pioche pioche){
        this.m_main.add(pioche.piocher());
    }
}
