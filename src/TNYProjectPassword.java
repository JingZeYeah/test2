import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class TNYProjectPassword extends JFrame implements ActionListener
{
	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnLogin, btnCancel;
	Integer count = 3;
	final String adminUser = "admin";
	final String adminPass = "admin123$";
	
	public TNYProjectPassword()
	{
		super("Admin Login");
		
		JLabel lblUsername = new JLabel("Username : ");
		txtUsername = new JTextField(10);
		JPanel pUsername = new JPanel();
		JPanel pLUsername = new JPanel();
		JPanel pRUsername = new JPanel();
		pLUsername.add(lblUsername);
		pRUsername.add(txtUsername);
		pUsername.add(pLUsername);
		pUsername.add(pRUsername);
		pUsername.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel lblPassword = new JLabel("Password : ");
		txtPassword = new JPasswordField(10);
		JPanel pLPassword = new JPanel();
		JPanel pRPassword = new JPanel();
		JPanel pPassword = new JPanel();
		pLPassword.add(lblPassword);
		pRPassword.add(txtPassword);
		pPassword.add(pLPassword);
		pPassword.add(pRPassword);
		pPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel pButtonL = new JPanel();
		btnLogin = new JButton("LOGIN");
		pButtonL.add(btnLogin);
		btnCancel = new JButton("CANCEL");
		pButtonL.add(btnCancel);
		pButtonL.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel pLogin = new JPanel();
		pLogin.add(pUsername);
		pLogin.add(pPassword);	
		pLogin.add(pButtonL);
		pLogin.setLayout(new BoxLayout(pLogin,BoxLayout.Y_AXIS));
		add(pLogin);
		
		btnCancel.addActionListener(this);
		btnLogin.addActionListener(this);
		
	    setLayout(new FlowLayout());
		setSize(300,165);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent ae)	
	{
		if(ae.getSource() == btnLogin)
		{
			String username = txtUsername.getText();
			String password = txtPassword.getText();
			
			if(username.hashCode() == 0)
			{
				JOptionPane.showMessageDialog(null,"Please enter Username.","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else if(!username.trim().equals(adminUser))
			{
				JOptionPane.showMessageDialog(null,"Incorrect Username.\nPlease enter again.","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else if(password.hashCode() == 0)
			{
				JOptionPane.showMessageDialog(null,"Please enter Password.","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				while(count>0)
				{
					if(password.trim().equals(adminPass))
					{
						JOptionPane.showMessageDialog(null,"Login Successful");
						new TNYProjectAdmin();
						dispose();
						break;
					}else
					{
						count = count - 1;
						JOptionPane.showMessageDialog(null,"Incorrect Password.\nRemaining attempts : " + count,"Alert",JOptionPane.WARNING_MESSAGE);
						break;
					}	
				}
				if(count == 0)
				{
					JOptionPane.showMessageDialog(null,"Attempt exceed!\nPlease try again later","Alert",JOptionPane.WARNING_MESSAGE);
					dispose();
					new TNYProjectMain();
				}
			}
		}
		else if(ae.getSource() == btnCancel)
		{
			dispose();
			new TNYProjectMain();
		}
	}

}