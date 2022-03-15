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

    }

}

public static void newRegister(String Nuser, String Npassword)
{
    if((CustomerClient.connect(User, Password)))
    {
        String create = "CREATE USER [IF NOT EXISTS] " + Nuser + " IDENTIFIED BY " + Npassword;
        
    }
}

    
}
