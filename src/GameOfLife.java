
import java.util.HashMap;
import java.util.Random;

public class GameOfLife {

    private Taulukko taulukko;

    public GameOfLife(int leveys, int korkeus) {
        this.taulukko = new Taulukko(leveys, korkeus);
    }

    public void alustaSatunnaisesti() {
        Random satunnaistaja = new Random();

        for (int x = 0; x < taulukko.getLeveys(); x++) {
            for (int y = 0; y < taulukko.getKorkeus(); y++) {
                if (satunnaistaja.nextDouble() < 0.2) {
                    this.taulukko.aseta(x, y, true);
                }
            }
        }

    }

    @SuppressWarnings("empty-statement")
    public void kehity() {
        // säännöt kehittymiselle:

        // 1. jokainen elossa oleva alkio, jolla on alle 2 elossa olevaa naapuria kuolee
        // 2. jokainen elossa oleva alkio, jolla on 2 tai 3 elossa olevaa naapuria pysyy hengissä
        // 3. jokainen elossa oleva alkio, jolla on 4 tai enemmän naapureita kuolee
        // 4. jokainen kuollut alkio, jolla on tasan 3 naapuria muuttuu eläväksi
        // taulukossa arvo 1 kuvaa elävää alkiota, arvo 0 kuollutta alkiota
        Taulukko kopio = new Taulukko(taulukko.getLeveys(), taulukko.getKorkeus());
        
        for (int leveys = 0; leveys <= taulukko.getLeveys(); leveys++) {
            for (int korkeus = 0; korkeus <= taulukko.getKorkeus(); korkeus++) {
                if (taulukko.hae(korkeus, leveys) == true && elossaOleviaNaapureita(taulukko, korkeus, leveys)<2) {
                    kopio.aseta(korkeus, leveys, false);
                }
                if (taulukko.hae(korkeus, leveys) == true && elossaOleviaNaapureita(taulukko, korkeus, leveys)==2) {
                    kopio.aseta(korkeus, leveys, true);
                }
                if (taulukko.hae(korkeus, leveys) == true && elossaOleviaNaapureita(taulukko, korkeus, leveys)==3) {
                    kopio.aseta(korkeus, leveys, true);
                }
                if (taulukko.hae(korkeus, leveys) == true && elossaOleviaNaapureita(taulukko, korkeus, leveys)>=4) {
                    kopio.aseta(korkeus, leveys, false);
                }
                if (taulukko.hae(korkeus, leveys) == false && elossaOleviaNaapureita(taulukko, korkeus, leveys)==3) {
                    kopio.aseta(korkeus, leveys, true);
                }       
            }    
        }
        this.taulukko = kopio;

    }

    public Taulukko getTaulukko() {
        return this.taulukko;
    }

    public void setTaulukko(Taulukko taulukko) {
        this.taulukko = taulukko;
    }

    public int elossaOleviaNaapureita(Taulukko taulukko, int x, int y) {
        int naapureita = 0;

        if (taulukko.hae(x-1, y-1) == true) {
            naapureita++;
        }
        if (taulukko.hae(x-1, y) == true) {
            naapureita++;
        }   
        if (taulukko.hae(x-1, y+1) == true) {
            naapureita++;
        }
        if (taulukko.hae(x, y-1) == true) {
            naapureita++;
        }
        if (taulukko.hae(x, y+1) == true) {
            naapureita++;
        }
        if (taulukko.hae(x+1, y-1) == true) {
            naapureita++;
        }
        if (taulukko.hae(x+1, y) == true) {
            naapureita++;
        }
        if (taulukko.hae(x+1, y+1) == true) {
            naapureita++;
        }
          
        return naapureita;
    }
}
