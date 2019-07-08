package org.dimigo.gui.helloworld;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Controller {
    @FXML
    private Label lbl_hello;
    @FXML
    private Label lbl_top10;
    @FXML private Button btn_click;
    @FXML private Button btn_top10;
    @FXML
    private ComboBox<String> jenre;

    @FXML
    public void handleAction(ActionEvent event) {
        new RotateTransition(); RotateTransition rt = new RotateTransition ( new Duration(1000), lbl_hello);
        Random generator = new Random();

        int num = jenre.getSelectionModel().getSelectedIndex();
        String url = "";
        switch (num){
            case 0 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN0502";
                break;
            case 1 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN0902";
                break;
            case 2 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN0903";
                break;
            case 3 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1002";
                break;
            case 4 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1006";
                break;
            case 5 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1005";
                break;
            case 6 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1004";
                break;
            case 7 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1104";
                break;
            case 8 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1103";
                break;
            case 9 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1202";
                break;
            case 10 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1204";
                break;
            case 11 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1205";
                break;
            case 12 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1304";
                break;
            case 13 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1608";
                break;
            case 14 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1703";
                break;
            case 15 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1702";
                break;
            case 16 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1705";
                break;
            case 17 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN1907";
                break;
            case 18 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN2002";
                break;
            case 19 :
                url = "https://www.melon.com/chart/style/index.htm?styleCd=GN2003";
                break;
        }
        try {
            Document doc = Jsoup.connect(url)
                    .get();
            System.out.println(doc.html());
            Elements songresult = doc.select("table tr.lst50 td div div div.rank01 span a");
            List<String> songlist = songresult.eachText();
            if(lbl_hello.getText().equals("")) {
                int j = generator.nextInt(50);
                System.out.println(songlist.get(j));
                lbl_hello.setText(songlist.get(j));
                rt.setFromAngle ( 0);
                rt.setToAngle ( 1080);
                rt.play();
            } else {
                lbl_hello.setText("");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showtop10(ActionEvent event) {

        try {
            Document doc = Jsoup.connect("https://www.melon.com/chart/index.htm")
                    .get();
            System.out.println(doc.html());
            Elements songresult = doc.select("table tr.lst50 td div div div.rank01 span a");
            List<String> songlist = songresult.eachText();
            Elements rankresult = doc.select("table tr.lst50 td div span.rank");
            List<String> ranklist = rankresult.eachText();
            int i=0;
            StringBuilder top10 = new StringBuilder( "" );
            for(String songtitle : songlist) {
                top10.append( ranklist.get(i) + ". " + songtitle + "\n" );
                i++;
                if(i==10) break;
            }
            System.out.println(top10);
            if(lbl_top10.getText().equals("")) {
                lbl_top10.setText(String.valueOf(top10));
            } else {
                lbl_top10.setText("");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void save(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(lbl_hello.getScene().getWindow());
        if (lbl_hello.equals("")) {
            lbl_hello.setText("Where is the song?");
        } else {
            saveFile(file);
        }


    }

    @FXML
    private void saveFile(File file){
        try{
            FileWriter writer = null;
            writer = new FileWriter(file);
            writer.write(lbl_hello.getText().replaceAll("\n", "\r\n"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

