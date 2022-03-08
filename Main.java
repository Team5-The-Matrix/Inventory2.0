import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

// CLASS TO RUN GUI AND CLIENT PROGRAMS
public class Main {
    
public static void main(String[] args){
 
    
//LOGIN GUI    
JFrame login = new JFrame("Login To Inventory Manager");
JTextField user;
JPasswordField pass;
JButton enter,clear;
JLabel username, password;
enter = new JButton("Enter");
clear = new JButton("Clear");
user = new JTextField();
pass = new JPasswordField();
username = new JLabel();
password = new JLabel();
username.setText("Username:");
password.setText("Password:");
username.setBounds(100, 50, 100, 30);
password.setBounds(100,100,100, 30);
user.setBounds(175, 50, 150, 30);
pass.setBounds(175, 100, 150,30);
enter.setBounds(200,150,95,30);
clear.setBounds(200,200,95,30);
login.add(enter);
login.add(clear);
login.add(user);
login.add(pass);
login.add(username);
login.add(password);
login.setLocation(500, 0);
login.setSize(500,500);
login.setLayout(null);
login.setVisible(true);
login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

JFrame warningFrame = new JFrame("Message:");
JLabel noCredentials = new JLabel("You must enter both username and password!", SwingConstants.CENTER);
JLabel loginFailed = new JLabel("Login Failed. Try Again", SwingConstants.CENTER);
JLabel success = new JLabel("Authenticated. Login Successful!",SwingConstants.CENTER);


// ENTER BUTTON - STARTS PROGRAM IF LOGIN IS CORRECT
enter.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent enter){
        String username = user.getText();
        char[] pw = pass.getPassword();
        String password = new String(pw);

        if ((username.equals("")|| username == null) || (password.equals("")|| password == null))
            {
                warningFrame.setSize(500, 200);
                warningFrame.setLocation(500, 50);
                warningFrame.add(noCredentials);
                warningFrame.setVisible(true);
                warningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }


        //call Login Method here
        System.out.println("Establishing Connection... ");
        if ((Client.connect(username, password)))
            {
                warningFrame.setSize(500, 200);
                warningFrame.setLocation(500, 50);
                warningFrame.add(success);
                warningFrame.setVisible(true);
                
                // SLEEP FOR 2 SECONDS TO ALLOW USER TO READ MESSAGE
                try{
                TimeUnit.SECONDS.sleep(3);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                warningFrame.dispose();
                login.dispose();

                // CALL INVENTORY MANAGER
                InventoryManagerGUI.main(args);

            }
        else
            {
                warningFrame.setSize(500, 200);
                warningFrame.setLocation(500, 50);
                warningFrame.add(loginFailed);
                warningFrame.setVisible(true);
                warningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

    }
});

//CLEAR BUTTON - CLEARS TEXT FIELDS
clear.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent clear){
        user.setText("");
        pass.setText("");
    }
});
}
}
