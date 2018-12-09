/*
    Program that is a simple calculator
    Justin Tran,created 27 September 2018
 */
import javax.swing.*;
import java.util.regex.*;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.event.WindowAdapter;
public class Calculator extends JFrame{
    /* All the varibles! */
    private JTextArea calcField;
    private JPanel calcPanel;
    private JButton[] numButtons;
    private JButton[] operators;
    private final String[] ops = new String[]{"+","-","*","/","."};  
    private JButton clear;
    private JButton equals;
    private JButton delete;
    private GridBagConstraints c;
    private String expression;
    private final Insets t = new Insets(2,2,2,2);
    /*
     * initializes window and draws the calculator screen
     */
    public Calculator(){
        super("Calculator");
        setSize(500,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcField = new JTextArea();
        calcField.setEditable(false);
        calcPanel = new JPanel( new GridBagLayout());
            operators = new JButton[5];
            for(int i =0;i<operators.length;i++){
                operators[i] = new JButton(ops[i]);
                operators[i].addActionListener(new OperatorButtonActionListener(i));
            }
            numButtons = new JButton[10];
            for(int i = 0;i<numButtons.length;i++){
                numButtons[i] = new JButton(""+i);
                numButtons[i].addActionListener(new NumberButtonActionListener(i));
            }
            delete = new JButton("DELETE");
            delete.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String temp = calcField.getText();
                    if(temp!=null && temp.length()>=1){
                        temp=temp.substring(0, temp.length()-1);
                        calcField.setText(temp);
                    }
                }    
            });
            clear = new JButton("CLEAR");
            clear.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        calcField.setText(null);
                }
            });
            equals = new JButton("=");
            equals.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
 
                    try{
                    calcField.setText(""+calculate());
                    }catch(Exception e2){e2.printStackTrace(System.err);}
                }
            });
            c = new GridBagConstraints(1, 0, 3, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(calcField, c);
            c = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(clear, c);
            c = new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(operators[3], c);
            c = new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(operators[2], c);
            c = new GridBagConstraints(3, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(operators[1], c);
            c = new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[7], c);
            c = new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[8], c);
            c = new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[9], c);
            c = new GridBagConstraints(3, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(operators[0], c);
            c = new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[4], c);
            c = new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[5], c);
            c = new GridBagConstraints(2, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[6], c);
            c = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(delete,c);
            c = new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[1], c);
            c = new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[2], c);
            c = new GridBagConstraints(2, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[3], c);
            c = new GridBagConstraints(3, 4, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(equals, c);
            c = new GridBagConstraints(0, 6, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(numButtons[0], c);
            c = new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, t, 0,0);
            calcPanel.add(operators[4], c);
            getContentPane().add(calcPanel, BorderLayout.CENTER);
            calcField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {
                    if(ke.getKeyChar() == KeyEvent.VK_1){
                        calcField.setText(calcField.getText() + 1);
                    }else if(ke.getKeyChar() == KeyEvent.VK_2){
                        calcField.setText(calcField.getText() + 2);
                    }else if(ke.getKeyChar() == KeyEvent.VK_3){
                        calcField.setText(calcField.getText() + 3);
                    }else if(ke.getKeyChar() == KeyEvent.VK_4){
                        calcField.setText(calcField.getText() + 4);
                    }else if(ke.getKeyChar() == KeyEvent.VK_5){
                        calcField.setText(calcField.getText() + 5);
                    }else if(ke.getKeyChar() == KeyEvent.VK_6){
                        calcField.setText(calcField.getText() + 6);
                    }else if(ke.getKeyChar() == KeyEvent.VK_7){
                        calcField.setText(calcField.getText() + 7);
                    }else if(ke.getKeyChar() == KeyEvent.VK_8){
                        calcField.setText(calcField.getText() + 8);
                    }else if(ke.getKeyChar() == KeyEvent.VK_9){
                        calcField.setText(calcField.getText() + 9);
                    }else if(ke.getKeyChar() == KeyEvent.VK_0){
                        calcField.setText(calcField.getText() + 0);
                    }else if(ke.getKeyChar() == KeyEvent.VK_EQUALS ||ke.getKeyChar() == KeyEvent.VK_ENTER){
                        try{
                            calcField.setText(""+calculate());
                            }
                            catch(Exception e2){e2.printStackTrace(System.err);}
                    }else if(ke.getKeyChar() == '+'){
                        calcField.setText(calcField.getText() + operators[0].getText());
                    }else if(ke.getKeyChar() == KeyEvent.VK_MINUS){
                        calcField.setText(calcField.getText() + operators[1].getText());
                    }else if(ke.getKeyChar() == '*'){
                        calcField.setText(calcField.getText() + operators[2].getText());
                    }else if(ke.getKeyChar() == KeyEvent.VK_SLASH){
                        calcField.setText(calcField.getText() + operators[3].getText());
                    }else if(ke.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                        calcField.setText(null);
                    }else if(ke.getKeyChar() == KeyEvent.VK_DELETE){
                        String temp = calcField.getText();
                        if(temp!=null && temp.length()>=1){
                            temp=temp.substring(0, temp.length()-1);
                            calcField.setText(temp);
                        }   
                    }
                    //System.out.println(ke.getk());
                }
            });
            this.addWindowFocusListener(new WindowAdapter() {
                public void windowGainedFocus(WindowEvent e) {
                    calcField.requestFocusInWindow();
                }
            });
    }
    public double calculate()throws ScriptException{
        // This was the only way I could get this to work...
        // I tried multiple attempts the commented out section was the last attempt
        // before resorting to this method.
        if(calcField != null){
        expression = calcField.getText();}
        calcField.setText(null);
        // Throws a script exception when the string inputted
        // is not of the form a * b where a and b are real numbers and * is any operator.
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        if(expression!=null){
            calcField.setText(null);
            return Double.valueOf("" + engine.eval(expression));
        }else {return 0;}
    }
    public static final void main(String[] args){
        new Calculator().setVisible(true);

    }
    class NumberButtonActionListener implements ActionListener {
        private int buttonNumber;
        NumberButtonActionListener(int buttonNumber) {
            this.buttonNumber = buttonNumber;
        }
        public void actionPerformed(ActionEvent e) {
        calcField.setText(calcField.getText() + buttonNumber);
    }
    }
    class OperatorButtonActionListener implements ActionListener {
        private String[] buttonOp;
        OperatorButtonActionListener(int i){
            buttonOp = new String[]{"+","-","*","/","."};
        }
        public void actionPerformed(ActionEvent e) {

                calcField.setText(calcField.getText() + ((JButton)e.getSource()).getText()); 
            }         
        }
    }

