package tetris.binarytetris.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tetris.binarytetris.logic.PalikkaTaulukko;
import tetris.binarytetris.logic.TetrisPeli;

/**
 *
 * @author Venla Viljamaa
 */
public class Kayttoliittyma extends Application {

//    private int korkeus;
//    private int leveys;
//    private int ruudunkoko;
//    private int summa;
//    private TetrisPeli peli;
//    private PalikkaTaulukko taulu;
//
//    public Kayttoliittyma(int korkeus, int leveys, int ruudunkoko, int summa, TetrisPeli peli, PalikkaTaulukko taulu) {
//        this.korkeus = korkeus;
//        this.leveys = leveys;
//        this.ruudunkoko = ruudunkoko;
//        this.summa = summa;
//        this.peli = new TetrisPeli(korkeus, leveys, summa);
//        this.taulu = peli.getTaulukko();
//    }
    
    @Override
    public void start(Stage ikkuna) throws Exception {
        int ruudunkoko = 50;
        int leveys = 5;
        int korkeus = 5;
        int summa = 5;
        TetrisPeli peli = new TetrisPeli(korkeus, leveys, summa);
//        PalikkaTaulukko taulu = peli.getTaulukko();

        BorderPane asettelu = new BorderPane();
        asettelu.setPrefSize(leveys * ruudunkoko + ruudunkoko * 2, korkeus * ruudunkoko + ruudunkoko);

        VBox sivupalkki = new VBox();
        
        peli.setUusiArvo();
        Label palikkaOhje = new Label("Next:");
        Label seuraavaPalikka = new Label(Integer.toBinaryString((peli.getUusiArvo())));       
//        Label seuraavaPalikka = new Label(String.valueOf(peli.getUusiArvo()));
        seuraavaPalikka.setPrefSize(ruudunkoko, ruudunkoko);
        seuraavaPalikka.setAlignment(Pos.CENTER);
        seuraavaPalikka.setStyle("-fx-border-color: black;");

        Label pisteOhje = new Label("Points:");
        Label pisteet = new Label("0");

        Label tasoOhje = new Label("Level:");
        Label vaikeustaso = new Label(String.valueOf(summa));

        sivupalkki.getChildren().addAll(palikkaOhje, seuraavaPalikka, pisteOhje, pisteet, tasoOhje, vaikeustaso);
        

        HBox ylapalkki = new HBox();
        for (int i = 0; i < leveys; i++) {
            int nro = i + 1;
            Button btnNumber = new Button();
            btnNumber.setPrefSize(ruudunkoko, ruudunkoko);
            btnNumber.setText(String.valueOf(nro));
            btnNumber.setOnAction((actionEvent) -> {
                PalikkaTaulukko taulu = peli.getTaulukko();
                peli.paivita(nro);
                System.out.println(peli.getHaluttuSumma());
                GridPane taulukko = taulukko(korkeus, leveys, taulu, ruudunkoko);
                pisteet.setText(String.valueOf(peli.getPisteet()));
                vaikeustaso.setText(String.valueOf(peli.getHaluttuSumma()));
                asettelu.setCenter(taulukko);
                peli.setUusiArvo();
                seuraavaPalikka.setText(Integer.toBinaryString((peli.getUusiArvo())));
//                seuraavaPalikka.setText(String.valueOf(peli.getUusiArvo()));
            });
            ylapalkki.getChildren().add(btnNumber);
        }
//        HBox ylapalkki = ylapalkki(leveys, korkeus, ruudunkoko, sivupalkki, peli);

        asettelu.setTop(ylapalkki);
        asettelu.setRight(sivupalkki);
        
//        GridPane taulukko = taulukko(korkeus, leveys, taulu, ruudunkoko);
//        asettelu.setCenter(taulukko);

//        new AnimationTimer() {
//            private long edellinen;
//
//            @Override
//            public void handle(long nykyhetki) {
//                if (nykyhetki - edellinen < 1_000_000_000 / 10) {
//                    return;
//                }
//                edellinen = nykyhetki;
//
//                while (peli.onkoTaulukossaTilaa()) {
//                    peli.setUusiArvo();
//                    seuraavaPalikka.setText("" + peli.getUusiArvo());
////                    paivitaPeli();
//                }
//                System.out.println("Peli loppui");
//            }
//        }.start();

        Scene nakyma = new Scene(asettelu);

        ikkuna.setScene(nakyma);
        ikkuna.show();
    }

    public static void main(String[] args) {

        launch(Kayttoliittyma.class);

    }

    public GridPane taulukko(int korkeus, int leveys, PalikkaTaulukko taulu, int ruudunkoko) {
        GridPane taulukko = new GridPane();
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                Label palikka = new Label();
                if (taulu.getPalikka(y, x).getArvo() != 0) {
                    palikka.setText(Integer.toBinaryString(taulu.getPalikka(y, x).getArvo()));
//                    palikka.setText(String.valueOf(taulu.getPalikka(y, x).getArvo()));
                    palikka.setStyle("-fx-border-color: black;");
//                } else {
//                    palikka.setText("");
//                    
                }

                palikka.setPrefSize(ruudunkoko, ruudunkoko);
                palikka.setAlignment(Pos.CENTER);
                taulukko.add(palikka, x, y);
            }
        }
        return taulukko;
    }
    
//    public HBox ylapalkki(int leveys, int korkeus, int ruudunkoko, VBox sivupalkki, TetrisPeli peli) {
//        HBox ylapalkki = new HBox();
//        for (int i = 0; i < leveys; i++) {
//            int nro = i + 1;
//            Button btnNumber = new Button();
//            btnNumber.setPrefSize(ruudunkoko, ruudunkoko);
//            btnNumber.setText(String.valueOf(nro));
//            btnNumber.setOnAction((actionEvent) -> {
//                PalikkaTaulukko taulu = peli.getTaulukko();
//                peli.paivita(nro);
//                System.out.println(peli.getHaluttuSumma());
//                GridPane taulukko = taulukko(korkeus, leveys, taulu, ruudunkoko);
////                pisteet.setText(String.valueOf(peli.getPisteet()));
////                vaikeustaso.setText(String.valueOf(peli.getHaluttuSumma()));
////                asettelu.setCenter(taulukko);
//                peli.setUusiArvo();
////                seuraavaPalikka.setText(Integer.toBinaryString((peli.getUusiArvo())));
////                seuraavaPalikka.setText(String.valueOf(peli.getUusiArvo()));
//            });
//            ylapalkki.getChildren().add(btnNumber);
//        }
//        return ylapalkki;
//    }
}
