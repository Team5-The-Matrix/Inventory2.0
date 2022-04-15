package Other;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Label;
import java.awt.Window;

import javax.swing.JTextField;

import org.w3c.dom.Text;

import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JButton;
public class login {

	protected Frame frame1;
	private Text text;
	private Text text_1;
	private Text txtUserId;
	private Text txtPassword;
	private GraphicsConfiguration login;
	
	JFrame frame = new JFrame(login);
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();
	
	static HashMap<String,String> logininfo = new HashMap<String,String>();
	
	login(HashMap<String,String> loginInfoOriginal) {
	logininfo = loginInfoOriginal;
	}
	
	/**
	 * Launch the application
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		try {
			 login window = new login(logininfo);
			 window.open();
		} catch (Exception e) {
			   e.printStackTrace();
		   }
	}
	
	/**
	* Open the window
	*/
		public void open() {
		Display display = Display.getDefault();
			createContents();
			frame1.open();
			frame1.layout();
			while (!frame1.isDisposing()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}
		/**
		 * Create contents of the window
		 */
		protected void createContents() {
			frame1 = new Frame();
			frame1.setSize(928, 635);
			frame1.setText("SWT Application");
			
			Object SWT;
			Label lblUserlogin = new Label(frame1, SWT.NONE);
			lblUserlogin.setFont(SWTResourcesManager.getFont("Segoe UI", 40, SWT.BOLD));
			lblUserlogin.setBounds(33, 24, 353, 105);
			lblUserlogin.setText("User Login");
			
			text = new Text(frame1, SWT.BORDER);
			((Window) text).setBounds(342, 193, 253, 40);
			
			text_1 = new Text(frame1, SWT.BORDER);
			((Window) text_1).setBounds(342, 262, 253, 40);
			
			
			txtUserId = new Text(frame1, SWT.BORDER);
			txtUserId.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND);
			txtPassword.setText("Password: ");
			txtPassword.SetFont(SWTRResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
			txtPassword.setBounds(141, 262, 151, 40);
			
			JButton btnNewButton = new Button(frame1, SWT.NONE);	
			btnNewButton.setBounds(187, 355, 271, 52);
			btnNewButton.setText("Login Button");
		}
	}
}
