
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Hangman extends JFrame{
    public Hangman(){
        setTitle("p4Younouss_T");
        setSize(500, 500);
        Container contentPane = getContentPane();        
        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        gameKey = new JTextField();
        gameKey.setEditable(false);
        gameKey.setForeground(Color.RED);
        gameKey.setFont(new Font("Courier New", Font.BOLD, 18));        
        playerGuess = new JTextField();   
        playerGuess.setEditable(false); 
        playerGuess.setFont(new Font("Courier New", Font.BOLD, 18));   
        northPanel.add(gameKey);
        northPanel.add(playerGuess);
        contentPane.add(northPanel,"North");
        canvas = new DrawPanel();
        contentPane.add(canvas,"Center");
        JPanel southPanel = new JPanel(new GridLayout(4, 7));
        alphabet = new JButton[26];
        newButton = new JButton("New");
        String[] labels = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for(int i =0; i < 26 ;i++){
            alphabet[i] = new JButton(labels[i]);
        }
        for(int i = 0; i < 26 ;i++ ){
            southPanel.add(alphabet[i]);
        }
        southPanel.add(newButton);
        contentPane.add(southPanel,"South");
        guessList.add("java");
        guessList.add("computer");
        guessList.add("object");
        guessList.add("program");
        guessList.add("grade");
        guessList.add("array");
        guessList.add("scanner");
        guessList.add("string");
        guessList.add("integer");
        guessList.add("character");
        guessList.add("class");
        guessList.add("guess");
        guessList.add("word");
        guessList.add("game");
        guessList.add("double");
        guessList.add("public");
        guessList.add("private");
        guessList.add("main");
        guessList.add("subclass");
        guessList.add("variable");
        guessList.add("constant");
        guessList.add("library");
        guessList.add("package");
        guessList.add("loop");
        guessList.add("stringbuffer");
        guessList.add("arraylist");
        guessList.add("random");
        guessList.add("print");
        guessList.add("system");
        guessList.add("palm");
        ButtonObserver observer = new ButtonObserver();
        newButton.addActionListener(observer);  
        for(int i = 0; i < 26 ;i++){
            alphabet[i].addActionListener(observer); 
            alphabet[i].setEnabled(false);
        }
    }  
    class ButtonObserver implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {  
            Object source = e.getSource();  
            if(source == newButton){
                healthIndex = 5;
                endGame = false;                
                drawHealthLevel = true;
                int x = 50;
                for(int i = 0; i < healthIndex; i++){
                    health[i] = new Rectangle2D.Double(x,100,50,50);
                    x = x + 60;
                    canvas.repaint();      
                }                          
                for(int i = 0; i < 26 ;i++){
                    alphabet[i].setEnabled(true); 
                }                
                playerGuess.setText("");
                int randomNumber = (int)(Math.random()*30);
                stringGameKey = guessList.get(randomNumber);
                stringPlayerGuess = new StringBuffer();
                for(int i = 0; i < stringGameKey.length() ;i++){
                    stringPlayerGuess.insert(i, ".");
                }
                gameKey.setText(stringGameKey);
                playerGuess.setText(stringPlayerGuess.toString());
            }
            for(int i = 0; i < 26 ;i++){
                if(source == alphabet[i]){
                    alphabet[i].setEnabled(false);
                    charPlayerGuess = alphabet[i].getText().charAt(0);
                    for(int j = 0; j < stringGameKey.length() ;j++){
                        if(stringGameKey.charAt(j) == charPlayerGuess){
                            characterFound = true;
                            stringPlayerGuess.setCharAt(j, charPlayerGuess);
                            playerGuess.setText(stringPlayerGuess.toString());                            
                        }                   
                    }
                    if(characterFound == false){
                        health[healthIndex - 1] = null;
                        canvas.repaint();
                        healthIndex--;
                    }
                    canvas.repaint();                    
                    characterFound = false;  
                }         
            }        
        }
    }
    class DrawPanel  extends JPanel {
        @Override
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            if(drawHealthLevel == false){
                g2.setColor(java.awt.Color.RED);                    
                g2.setFont(new Font("Courier New", Font.BOLD, 30));
                g2.drawString("Press <New> to start", 50, 50);  
            }
            if(drawHealthLevel == true){
                if(stringGameKey.equals(playerGuess.getText())){
                    endGame = true;
                    g2.setColor(java.awt.Color.RED);                    
                    g2.setFont(new Font("Courier New", Font.BOLD, 30));
                    g2.drawString("Press <New> to start", 50, 50);  
                    g2.drawString("YOU WON !!!", 80, 100);   
                    for(int j = 0; j < 26 ;j++){
                        alphabet[j].setEnabled(false); 
                    }                     
                }
            if(healthIndex - 1 == -1){ 
                endGame = true;
                g2.setColor(java.awt.Color.RED);                    
                g2.setFont(new Font("Courier New", Font.BOLD, 30));
                g2.drawString("Press <New> to start", 50, 50);  
                g2.drawString("YOU LOST !!!", 80, 100);      
                for(int j = 0; j < 26 ;j++){
                    alphabet[j].setEnabled(false); 
                }                              
            } 
            if(endGame == false){
                g2.setFont(new Font("Courier New", Font.BOLD, 50));                
                g2.setColor(java.awt.Color.BLUE);                    
                g2.drawString("Health Level", 50, 50);                         
                for(int i = 0; i < healthIndex; i++){
                    g2.fill(health[i]);
                }          
            }
            }
        }
    } 
    DrawPanel canvas;   
    JButton[] alphabet;    
    JButton newButton;    
    JTextField gameKey;   
    JTextField playerGuess;    
    String stringGameKey;
    StringBuffer stringPlayerGuess;    
    Character charPlayerGuess;
    ArrayList<String> guessList = new ArrayList();    
    Boolean drawHealthLevel = false;
    Boolean characterFound = false;
    Rectangle2D.Double[] health = new Rectangle2D.Double[5];
    int healthIndex;
    Boolean endGame;
}   
