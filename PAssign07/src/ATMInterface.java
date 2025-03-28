import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ATMInterface extends Application {
    private Label display;
    private StringBuilder input;
    
    @Override
    public void start(Stage primaryStage) {
        input = new StringBuilder();
        display = new Label("Welcome! Enter amount:");
        display.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");
        
        KeyPadPane keypad = new KeyPadPane(true);
        
        Button btnWithdraw = new Button("Withdraw");
        Button btnDeposit = new Button("Deposit");
        Button btnClear = new Button("Clear");
        
        btnWithdraw.setOnAction(_ -> processTransaction("Withdraw"));
        btnDeposit.setOnAction(_ -> processTransaction("Deposit"));
        btnClear.setOnAction(_ -> clearInput());
        
        keypad.listButtons.forEach(btn -> btn.setOnAction(_ -> updateInput(btn.getText())));
        
        VBox actionBox = new VBox(10, btnWithdraw, btnDeposit, btnClear);
        actionBox.setStyle("-fx-padding: 10px;");
        
        BorderPane root = new BorderPane();
        root.setTop(display);
        root.setCenter(keypad);
        root.setRight(actionBox);
        
        Scene scene = new Scene(root, 350, 400);
        primaryStage.setTitle("ATM Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void updateInput(String value) {
        input.append(value);
        display.setText("Amount: " + input.toString());
    }
    
    private void processTransaction(String type) {
        if (input.length() > 0) {
            display.setText(type + " successful: $" + input.toString());
            input.setLength(0);
        } else {
            display.setText("Enter amount first!");
        }
    }
    
    private void clearInput() {
        input.setLength(0);
        display.setText("Welcome! Enter amount:");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
