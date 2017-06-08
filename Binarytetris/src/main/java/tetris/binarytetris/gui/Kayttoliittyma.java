package tetris.binarytetris.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

    @Override
    public void start(Stage ikkuna) throws Exception {
        int ruudunkoko = 30;
        int leveys = 5;
        int korkeus = 10;
        int summa = 15;
        TetrisPeli peli = new TetrisPeli(korkeus, leveys, summa);
        PalikkaTaulukko taulu = peli.getTaulukko();

        BorderPane asettelu = new BorderPane();

        GridPane taulukko = new GridPane();
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                Label palikka = new Label(taulu.getPalikka(y, x).toString());
                palikka.setPrefSize(ruudunkoko, ruudunkoko);
                palikka.setAlignment(Pos.CENTER);
                taulukko.add(palikka, x, y);
            }
        }

        HBox ylapalkki = new HBox();
        for (int i = 0; i < leveys; i++) {
            int nro = i + 1;
            Button btnNumber = new Button();
            btnNumber.setPrefSize(ruudunkoko, ruudunkoko);
            btnNumber.setText(String.valueOf(nro));
            btnNumber.setOnAction((actionEvent) -> {
                peli.paivita(nro);
                System.out.println(nro);
            });
            ylapalkki.getChildren().add(btnNumber);
        }

        asettelu.setTop(ylapalkki);

        VBox sivupalkki = new VBox();
        Button seuraavaPalikka = new Button();
        sivupalkki.getChildren().add(seuraavaPalikka);

        asettelu.setRight(sivupalkki);

        new AnimationTimer() {
            private long edellinen;

            @Override
            public void handle(long nykyhetki) {
                if (nykyhetki - edellinen < 1_000_000_000 / 10) {
                    return;
                }
                edellinen = nykyhetki;

//                while (peli.onkoTaulukossaTilaa()) {
//                    peli.setUusiArvo();
//                    seuraavaPalikka.setText("" + peli.getUusiArvo());
////                    paivitaPeli();
//                }
////                System.out.println("Peli loppui");
            }
        }.start();

        asettelu.setCenter(taulukko);

        Scene nakyma = new Scene(asettelu);

        ikkuna.setScene(nakyma);
        ikkuna.show();
    }

    public static void main(String[] args) {

        launch(Kayttoliittyma.class);

    }
}
