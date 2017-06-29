package tetris.binarytetris.ui;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tetris.binarytetris.logic.PalikkaTaulukko;
import tetris.binarytetris.logic.PalikkaPeli;

/**
 * Luokassa luodaan graafinen käyttöliittymä ja pelin toiminnallisuuksia.
 *
 * @author Venla Viljamaa
 */
public class Kayttoliittyma extends Application {

    /**
     * Main-metodi.
     *
     * @param args args
     *
     */
    public static void main(String[] args) {
        launch(Kayttoliittyma.class);
    }

    @Override
    public void start(Stage ikkuna) throws Exception {
        int ruudunkoko = 50;
        int leveys = 4;
        int korkeus = 5;

        // Luodaan aloitusnakyma
        BorderPane alkuasettelu = new BorderPane();
        alkuasettelu.setPrefSize(leveys * ruudunkoko + ruudunkoko * 2, korkeus * ruudunkoko + ruudunkoko);

        VBox aloitusValikko = new VBox();

        Label alaraja = new Label("Aloitusarvo");
        ComboBox alaArvo = new ComboBox();

        alaArvo.getItems().addAll(IntStream.range(5, 11).boxed().collect(Collectors.toList()));
        alaArvo.getSelectionModel().selectFirst();

        Label ylaraja = new Label("Lopetusarvo");
        ComboBox ylaArvo = new ComboBox();

        ylaArvo.getItems().addAll(IntStream.range(10, 31).boxed().collect(Collectors.toList()));
        ylaArvo.getSelectionModel().selectFirst();

        CheckBox pikaPeli = new CheckBox("Pikapeli");

        Button start = new Button("Aloita");

        aloitusValikko.getChildren().addAll(alaraja, alaArvo, ylaraja, ylaArvo, pikaPeli, start);
        aloitusValikko.setAlignment(Pos.CENTER);
        aloitusValikko.setSpacing(ruudunkoko / 4);

        alkuasettelu.setCenter(aloitusValikko);

        Scene aloitusnakyma = new Scene(alkuasettelu);

        // Luodaan loppunäkymä
        BorderPane loppuasettelu = new BorderPane();
        loppuasettelu.setPrefSize(leveys * ruudunkoko + ruudunkoko * 2, korkeus * ruudunkoko + ruudunkoko);

        VBox tulokset = new VBox();
        Label lopputeksti = new Label();
        Button uusiPeli = new Button("Uusi peli");
        uusiPeli.setOnAction((event) -> {
            ikkuna.setScene(aloitusnakyma);
        });

        tulokset.getChildren().addAll(lopputeksti, uusiPeli);
        tulokset.setAlignment(Pos.CENTER);
        tulokset.setSpacing(ruudunkoko / 4);

        loppuasettelu.setCenter(tulokset);

        Scene lopetusnakyma = new Scene(loppuasettelu);

        // Luodaan pelinäkyma
        start.setOnAction((event) -> {

            BorderPane peliasettelu = new BorderPane();
            peliasettelu.setPrefSize(leveys * ruudunkoko + ruudunkoko * 2, korkeus * ruudunkoko + ruudunkoko);

            int summa = (int) alaArvo.getValue();
            int voittoSumma = (int) ylaArvo.getValue();

            PalikkaPeli peli = new PalikkaPeli(korkeus, leveys, summa, voittoSumma);

            if (pikaPeli.isSelected()) {
                peli.setVaikeustaso(1);
            } else {
                peli.setVaikeustaso(5);
            }

            peli.setUusiArvo();
            Scene pelinakyma = new Scene(peliasettelu);
            ikkuna.setScene(pelinakyma);

            // pelinäkymän sivupalkki
            VBox sivupalkki = new VBox();

            Label seuraavaPalikka = new Label(Integer.toBinaryString((peli.getUusiArvo())));
//            Label seuraavaPalikka = new Label(String.valueOf(peli.getUusiArvo()));
            seuraavaPalikka.setPrefSize(ruudunkoko, ruudunkoko);
            seuraavaPalikka.setStyle("-fx-border-color: black;");
            seuraavaPalikka.setAlignment(Pos.CENTER);

            Canvas palkki = new Canvas(2 * ruudunkoko, ruudunkoko / 2);
            GraphicsContext piirturi = palkki.getGraphicsContext2D();

            Label tasoOhje = new Label("Tavoitesumma:");
            Label tavoiteSumma = new Label(String.valueOf(summa));

            sivupalkki.getChildren().addAll(seuraavaPalikka, palkki, tasoOhje, tavoiteSumma);
//            sivupalkki.setAlignment(Pos.CENTER);
            sivupalkki.setAlignment(Pos.TOP_CENTER);
            sivupalkki.setSpacing(ruudunkoko / 4);

            // Luodaan sarakkeiden valintanapit
            HBox ylapalkki = new HBox();
            for (int i = 0; i < leveys; i++) {
                int nro = i + 1;
                Button btnNumber = new Button();
                btnNumber.setPrefSize(ruudunkoko, ruudunkoko);
                btnNumber.setText(String.valueOf(nro));

                piirturi.setFill(Color.GREY);
                piirturi.fillRect(0, 0, ruudunkoko * 2, ruudunkoko / 2);

                btnNumber.setOnAction((actionEvent) -> {

                    peli.paivita(nro);
//                    peli.tulosta();
//                    System.out.println(peli.getHaluttuSumma());
                    PalikkaTaulukko taulu = peli.getTaulukko();
                    GridPane taulukko = taulukko(korkeus, leveys, taulu, ruudunkoko);

                    piirturi.setFill(Color.GREY);
                    piirturi.fillRect(0, 0, ruudunkoko * 2, ruudunkoko / 2);

                    piirturi.setFill(Color.RED);

                    piirturi.fillRect(0, 0, 0.4 * ruudunkoko * peli.getPisteet(), ruudunkoko / 2);
                    tavoiteSumma.setText(String.valueOf(peli.getHaluttuSumma()));

                    peliasettelu.setCenter(taulukko);
                    peli.setUusiArvo();
                    seuraavaPalikka.setText(Integer.toBinaryString((peli.getUusiArvo())));
//                seuraavaPalikka.setText(String.valueOf(peli.getUusiArvo()));

                    if (peli.peliVoitettu()) {
                        lopputeksti.setText("Onneksi olkoon, pääsit pelin läpi!");
                        ikkuna.setScene(lopetusnakyma);
                    }
                    if (peli.peliHavitty()) {
                        lopputeksti.setText("Peli päättyi, jäit lukuun " + peli.getHaluttuSumma());
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

    /**
     * Metodi luo pelinäkymän taulukon.
     *
     * @param korkeus taulukon korkeus
     * @param leveys taulukon leveys
     * @param taulu PalikkaTaulukko-olio
     * @param ruudunkoko palikan sivun koko
     *
     * @return GridPane-olio pelinäkymää varten
     *
     */
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

}
