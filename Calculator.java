import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {

    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    String[] buttonValues = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("Calculator");
    JLabel displayJLabel = new JLabel();
    JPanel displayJPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    String A = "0";
    String operator = null;
    String B = null;

    Calculator() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Display
        displayJLabel.setBackground(customBlack);
        displayJLabel.setForeground(Color.white);
        displayJLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayJLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayJLabel.setText("0");
        displayJLabel.setOpaque(true);

        displayJPanel.setLayout(new BorderLayout());
        displayJPanel.add(displayJLabel);
        frame.add(displayJPanel, BorderLayout.NORTH);

        // Buttons
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];

            button.setText(buttonValue);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));

            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            } else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }

            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    String buttonValue= button.getText();
                if(Arrays.asList(rightSymbols).contains(buttonValue)){
                    // displayJLabel.setText(displayJLabel.getText()+buttonValue);
                    if(buttonValue == "="){
                        if(A !=null){
                            B = displayJLabel.getText();
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(B);

                            if (operator =="+"){
                                displayJLabel.setText(removeZeroDecimal(numA+numB));
                            }
                            if (operator =="-"){
                                displayJLabel.setText(removeZeroDecimal(numA-numB));
                            }
                            if (operator =="×"){
                                displayJLabel.setText(removeZeroDecimal(numA*numB));
                            }
                            if (operator =="%"){
                                displayJLabel.setText(removeZeroDecimal(numA%numB));
                            }
                            clearAll();
                        }
                    }
                    else if("+-×÷".contains(buttonValue)){
                        if (operator ==null){
                            A= displayJLabel.getText();
                            displayJLabel.setText("0");
                            B = "0";
                        }
                        operator = buttonValue;
                    }
                }   
                else if (Arrays.asList(topSymbols).contains(buttonValue)){
                    if (buttonValue=="AC") {
                        clearAll();
                        displayJLabel.setText("0");
                        
                    }else if (buttonValue=="+/-") {
                        double numDisplay = Double.parseDouble(displayJLabel.getText());
                        numDisplay *= -1;
                        displayJLabel.setText(removeZeroDecimal(numDisplay));
                    }
                    else if (buttonValue=="%"){
                        double numDisplay = Double.parseDouble(displayJLabel.getText());
                        numDisplay /= 100;
                        displayJLabel.setText(removeZeroDecimal(numDisplay));
                    }
                }   
                else{
                    if(buttonValue=="."){
                        if(!displayJLabel.getText().contains(buttonValue) ){
                            displayJLabel.setText (displayJLabel.getText()+buttonValue);
                        }


                    }else if("0123456789".contains(buttonValue)){
                        if(displayJLabel.getText()=="0" ){
                            displayJLabel.setText(buttonValue);
                        }
                        else{
                            displayJLabel.setText(displayJLabel.getText()+ buttonValue);
                        }
                    }
                }          }
            });
        }

        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    void clearAll(){
        A = "0";
        operator =null;
        B = null;
    }
    String removeZeroDecimal(double numDisplay){
        if (numDisplay % 1== 0 ) {
            return Integer.toString((int)numDisplay);
        }
        return Double.toString(numDisplay); 
    }
}
