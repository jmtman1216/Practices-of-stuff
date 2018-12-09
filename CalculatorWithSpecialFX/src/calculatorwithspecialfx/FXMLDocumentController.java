/*
 * Justin Tran November 7,2018
 * Resizing window thanksto the constraints i made make it do weird things
 */
package calculatorwithspecialfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
/**
 *
 * @author Jmtran1216
 */
public class FXMLDocumentController implements Initializable {
   @FXML
   private Button one;
   @FXML
   private Button two;
   @FXML
   private Button three;
   @FXML
   private Button four;
   @FXML
   private Button five;
   @FXML
   private Button six;
   @FXML
   private Button seven;
   @FXML
   private Button eight;
   @FXML
   private Button nine;
   @FXML
   private Button zero;
   @FXML
   private Button minus;
   @FXML
   private Button plus;
   @FXML
   private Button times;
   @FXML
   private Button divides;
   @FXML
   private Button equals;
   @FXML
   private Button lparen;
   @FXML
   private Button rparen;
   @FXML
   private Button delete;
   @FXML
   private Button decimal;
   @FXML
   private Button clear;
   @FXML
   private TextField textField;
   
   @FXML
    private void handleNum1ButtonAction(ActionEvent nbe) {
        textField.appendText("1");
    }
    @FXML
    private void handleNum2ButtonAction(ActionEvent nbe) {
        textField.appendText("2");
    }
    @FXML
    private void handleNum3ButtonAction(ActionEvent nbe) {
        textField.appendText("3");
    }
    @FXML
    private void handleNum4ButtonAction(ActionEvent nbe) {
        textField.appendText("4");
    }
    @FXML
    private void handleNum5ButtonAction(ActionEvent nbe) {
        textField.appendText("5");
    }
    @FXML
    private void handleNum6ButtonAction(ActionEvent nbe) {
        textField.appendText("6");
    }
    @FXML
    private void handleNum7ButtonAction(ActionEvent nbe) {
        textField.appendText("7");
    }
    @FXML
    private void handleNum8ButtonAction(ActionEvent nbe) {
        textField.appendText("8");
    }
    @FXML
    private void handleNum9ButtonAction(ActionEvent nbe) {
        textField.appendText("9");
    }
    @FXML
    private void handleNum0ButtonAction(ActionEvent nbe) {
        textField.appendText("0");
    }
    @FXML
    private void handleNumDecButtonAction(ActionEvent nbe) {
        textField.appendText(".");
    }
    @FXML
    private void handleNumPlusButtonAction(ActionEvent nbe) {
        textField.appendText("+");
    }
    @FXML
    private void handleNumMinusButtonAction(ActionEvent nbe) {
        textField.appendText("-");
    }
    @FXML
    private void handleNumTimesButtonAction(ActionEvent nbe) {
        textField.appendText("*");
    }
    @FXML
    private void handleNumDividesButtonAction(ActionEvent nbe) {
        textField.appendText("/");
    }
    @FXML
    private void handleNumLParenButtonAction(ActionEvent nbe) {
        textField.appendText("(");
    }@FXML
    private void handleNumRParenButtonAction(ActionEvent nbe) {
        textField.appendText(")");
    }
    @FXML
    private void handleClearButtonAction(ActionEvent nbe) {
        textField.setText("");
    }
    @FXML
    private void handleDeleteButtonAction(ActionEvent nbe) {
        if(textField != null){textField.setText(
                textField.getText().substring(0,textField.getText().length()-1));
        }
    }
    @FXML
    private void handleEqualsButtonAction(ActionEvent nbe) {
        String expression;
        if(textField != null){
        expression = textField.getText();}else expression = "";
        textField.setText("");
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        if(expression !=null){
            textField.setText(null);
            try{
                Double result = Double.valueOf("" + engine.eval(expression));
                textField.setText("" + result);
            }
            catch(ScriptException se){textField.setText("");}
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
