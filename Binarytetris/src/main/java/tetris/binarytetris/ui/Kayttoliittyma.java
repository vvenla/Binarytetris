package tetris.binarytetris.ui;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        int ruudunkoko = 50;
        int leveys = 4;
        int korkeus = 5;

        // Luodaan aloitusnakyma
        BorderPane alkuasettelu = new BorderPane();
        alkuasettelu.setPrefSize(leveys * ruudunkoko + ruudunkoko * 2, korkeus * ruudunkoko + ruudunkoko);

        VBox aloitusValikko = new VBox();

        Label alaraja = new Label("Min");
        ComboBox alaArvo = new ComboBox();

        alaArvo.getItems().addAll(IntStream.range(5, 11).boxed().collect(Collectors.toList()));
        alaArvo.getSelectionModel().selectFirst();

        Label ylaraja = new Label("Max");
        ComboBox ylaArvo = new ComboBox();

        ylaArvo.getItems().addAll(IntStream.range(10, 31).boxed().collect(Collectors.toList()));
        ylaArvo.getSelectionModel().selectFirst();

        Button start = new Button("Start");

        aloitusValikko.getChildren().addAll(alaraja, alaArvo, ylaraja, ylaArvo, start);
        aloitusValikko.setAlignment(Pos.CENTER);
        aloitusValikko.setPadding(new Insets(ruudunkoko / 2));

        alkuasettelu.setCenter(aloitusValikko);

        Scene aloitusnakyma = new Scene(alkuasettelu);

        // Luodaan loppunäkymä
        BorderPane loppuasettelu = new BorderPane();
        loppuasettelu.setPrefSize(leveys * ruudunkoko + ruudunkoko * 2, korkeus * ruudunkoko + ruudunkoko);
        VBox tulokset = new VBox();
        Label lopputeksti = new Label();
        Button uusiPeli = new Button("New game");
        uusiPeli.setOnAction((event) -> {
            ikkuna.setScene(aloitusnakyma);
        });
        
        tulokset.getChildren().addAll(lopputeksti);
        loppuasettelu.setCenter(lopputeksti);

        Scene lopetusnakyma = new Scene(loppuasettelu);

        // Luodaan pelinäkyma
        BorderPane peliasettelu = new BorderPane();
        peliasettelu.setPrefSize(leveys * ruudunkoko + ruudunkoko * 2, korkeus * ruudunkoko + ruudunkoko);

        Scene pelinakyma = new Scene(peliasettelu);

        start.setOnAction((event) -> {

            int summa = (int) alaArvo.getValue();
            int voittoSumma = (int) ylaArvo.getValue();

//        String sum = start.getOnAction().toString();
//        System.out.println(sum);
//        int summa = Integer.parseInt(sum);
            TetrisPeli peli = new TetrisPeli(korkeus, leveys, summa, voittoSumma);

            // pelinäkymän sivupalkki
            VBox sivupalkki = new VBox();

            peli.setUusiArvo();
            Label seuraavaPalikka = new Label(Integer.toBinaryString((peli.getUusiArvo())));
//        Label seuraavaPalikka = new Label(String.valueOf(peli.getUusiArvo()));
            seuraavaPalikka.setPrefSize(ruudunkoko, ruudunkoko);
            seuraavaPalikka.setStyle("-fx-border-color: black;");
            seuraavaPalikka.setAlignment(Pos.CENTER);

            Canvas palkki = new Canvas(2 * ruudunkoko, ruudunkoko / 2);
            GraphicsContext piirturi = palkki.getGraphicsContext2D();

            Label tasoOhje = new Label("Level:");
            Label vaikeustaso = new Label(String.valueOf(summa));

            sivupalkki.getChildren().addAll(seuraavaPalikka, palkki, tasoOhje, vaikeustaso);
            sivupalkki.setAlignment(Pos.CENTER);
            sivupalkki.setSpacing(ruudunkoko / 2);

            ikkuna.setScene(pelinakyma);

            HBox ylapalkki = new HBox();
            for (int i = 0; i < leveys; i++) {
                int nro = i + 1;
                Button btnNumber = new Button();
                btnNumber.setPrefSize(ruudunkoko, ruudunkoko);
                btnNumber.setText(String.valueOf(nro));

                
                
                
                btnNumber.setOnAction((actionEvent) -> {

                    peli.paivita(nro);
                    PalikkaTaulukko taulu = peli.getTaulukko();
                    GridPane taulukko = taulukko(korkeus, leveys, taulu, ruudunkoko);
                    piirturi.setFill(Color.GREY);
                    piirturi.fillRect(0, 0, ruudunkoko * 2, ruudunkoko / 2);
                    piirturi.setFill(Color.RED);

                    piirturi.fillRect(0, 0, 0.4 * ruudunkoko * peli.getPisteet(), ruudunkoko / 2);
                    vaikeustaso.setText(String.valueOf(peli.getHaluttuSumma()));
                    peliasettelu.setCenter(taulukko);
                    peli.setUusiArvo();
                    seuraavaPalikka.setText(Integer.toBinaryString((peli.getUusiArvo())));
//                seuraavaPalikka.setText(String.valueOf(peli.getUusiArvo()));
                    if (peli.peliVoitettu()) {
                        lopputeksti.setText("You won!");
                        ikkuna.setScene(lopetusnakyma);
                    }
                    if (peli.peliHavitty()) {
                        lopputeksti.setText("Game over! Level: " + peli.getHaluttuSumma());
                        ikkuna.setScene(lopetusnakyma);
                    }

                });

                ylapalkki.getChildren().add(btnNumber);
            }

            peliasettelu.setTop(ylapalkki);
            peliasettelu.setRight(sivupalkki);

        });

        ikkuna.setScene(aloitusnakyma);
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

//    
//    }
}
