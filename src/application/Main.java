package application;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Formelrad Application
 * 
 * @version 13.09.2018
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();

			// Creating an image
			Image image = new Image(new FileInputStream("bin\\application\\formelradelektronik.gif"));
			ImageView imageView = new ImageView(image);
			imageView.setX(10);
			imageView.setY(10);
			imageView.setFitHeight(300);
			imageView.setFitWidth(300);
			imageView.setPreserveRatio(true);
			root.getChildren().add(imageView);

			Label lbleistung = new Label("Leistung:");
			lbleistung.relocate(10, 285);
			lbleistung.setFont(Font.font(15));
			root.getChildren().add(lbleistung);

			TextField txLeistung = new TextField();
			txLeistung.relocate(100, 285);
			txLeistung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txLeistung);

			Label lblSpannung = new Label("Spannung:");
			lblSpannung.relocate(10, 325);
			lblSpannung.setFont(Font.font(15));
			root.getChildren().add(lblSpannung);

			TextField txSpannung = new TextField();
			txSpannung.relocate(100, 325);
			txSpannung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txSpannung);

			Label lblStrom = new Label("Strom:");
			lblStrom.relocate(10, 365);
			lblStrom.setFont(Font.font(15));
			root.getChildren().add(lblStrom);

			TextField txStrom = new TextField();
			txStrom.relocate(100, 365);
			txStrom.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txStrom);

			Label lblWiderstand = new Label("Widerstand:");
			lblWiderstand.relocate(10, 405);
			lblWiderstand.setFont(Font.font(15));
			root.getChildren().add(lblWiderstand);

			TextField txWiderstand = new TextField();
			txWiderstand.relocate(100, 405);
			txWiderstand.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txWiderstand);

			Button btnBerechnen = new Button();
			btnBerechnen.relocate(100, 445);
			btnBerechnen.setText("Berechnen");
			root.getChildren().add(btnBerechnen);

			Label lblWarning = new Label("");
			lblWarning.relocate(10, 485);
			lblWarning.setFont(Font.font(15));
			root.getChildren().add(lblWarning);

			btnBerechnen.setOnAction(e -> {
				Calculator myCalculator = new Calculator(Double.parseDouble("0" + txLeistung.getText()),
						Double.parseDouble("0" + txSpannung.getText()), Double.parseDouble("0" + txStrom.getText()),
						Double.parseDouble("0" + txWiderstand.getText()));
				System.out.print("Vorher:  ");
				System.out.println(myCalculator.toString());
				myCalculator.calculate();
				System.out.print("Nachher: ");
				System.out.println(myCalculator.toString());

				if (myCalculator.getWarning().equals("")) {
					double leistung = myCalculator.getLeistung();
					double spannung = myCalculator.getSpannung();
					double strom = myCalculator.getStrom();
					double widerstand = myCalculator.getWiderstand();
					if (leistung < 0) {
						txLeistung.setStyle("-fx-text-inner-color: red;");
						leistung *= -1;
					}else {
						txLeistung.setStyle("-fx-text-inner-color: black;");
					}
					txLeistung.setText((Double.toString(Math.round(leistung * 1000.0) / 1000.0)));
					if (spannung < 0) {
						txSpannung.setStyle("-fx-text-inner-color: red;");
						spannung *= -1;
					}else {
						txSpannung.setStyle("-fx-text-inner-color: black;");
					}
					txSpannung.setText(Double.toString(Math.round(spannung * 1000.0) / 1000.0));
					if (strom < 0) {
						txStrom.setStyle("-fx-text-inner-color: red;");
						strom *= -1;
					}else {
						txStrom.setStyle("-fx-text-inner-color: black;");
					}
					txStrom.setText(Double.toString(Math.round(strom * 1000.0) / 1000.0));
					if (widerstand < 0) {
						txWiderstand.setStyle("-fx-text-inner-color: red;");
						widerstand *= -1;
					}else {
						txWiderstand.setStyle("-fx-text-inner-color: black;");
					}
					txWiderstand.setText(Double.toString(Math.round(widerstand * 1000.0) / 1000.0));

					lblWarning.setStyle("-fx-text-color: red;");
					lblWarning.setText(myCalculator.getWarning());
				}else {
					lblWarning.setStyle("-fx-text-color: red;");
					lblWarning.setText(myCalculator.getWarning());

					if (!txLeistung.getText().equals("")) {
						txLeistung.setStyle("-fx-text-inner-color: red;");
					}
					if (!txSpannung.getText().equals("")) {
						txSpannung.setStyle("-fx-text-inner-color: red;");
					}
					if (!txStrom.getText().equals("")) {
						txStrom.setStyle("-fx-text-inner-color: red;");
					}
					if (!txWiderstand.getText().equals("")) {
						txWiderstand.setStyle("-fx-text-inner-color: red;");
					}
				}
			});

			Scene scene = new Scene(root, 330, 530);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Formelrad");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
