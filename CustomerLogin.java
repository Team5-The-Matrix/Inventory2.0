import javax.swing.JOptionPane;

public class CustomerLogin {

//added strings in order to enter mysql server and register new customer users
private static String User = "customer";
private static String Password = "password";
//private static CustomerClient cust = new CustomerClient();

//
public void CLogin(String user, String password) {
   
    User = user;
    Password = password;
    if((CustomerClient.connect(User, Password)))
    {
        JOptionPane.showMessageDialog(null, "Login Successful");

    }
    else {
        JOptionPane.showMessageDialog(null, "Wrong username and/or password/nPlease try again.");
    }

}

public static void newRegister(String Nuser, String Npassword)
{
    if((CustomerClient.connect(Nuser, Npassword)))
    {
        JOptionPane.showMessageDialog(null, "Username already exists");
    }

    if((CustomerClient.connect(User, Password)) )
    {
        String createCU = "CREATE USER [IF NOT EXISTS] " + Nuser + " IDENTIFIED BY " + Npassword;
                                //^ whatever the customer user table is named
    }
}

    
}
