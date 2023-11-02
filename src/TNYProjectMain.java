import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class TNYProjectMain extends JFrame implements ActionListener
{
   Connection con;
   Statement stmt;
   DatabaseMetaData dbm;
   ResultSet rs;
   String sql;
   JPanel pAll;
   JPanel pTop;
   JPanel pTitle;
   JPanel pText;
   JPanel pLName;
   JPanel pRName;
   JPanel pName;
   JPanel pLMatric;
   JPanel pRMatric;
   JPanel pMatric;
   JPanel pLGen;
   JPanel pRGen;
   JPanel pGender;
   JPanel pEmail;
   JPanel pLEmail;
   JPanel pREmail;
   JPanel pAddress;
   JPanel pLAddress;
   JPanel pRAddress;
   JPanel pCity;
   JPanel pLCity;
   JPanel pRCity;
   JPanel pLSubject;
   JPanel pTSubject;
   JPanel pRSubject;
   JPanel pOSubject;
   JPanel pSubject;
   JPanel pButton;
   JLabel lblTitle;
   JLabel lblText;
   JLabel lblName;
   JLabel lblMatric;
   JLabel lblGender;
   JLabel lblEmail;
   JLabel lblAddress;
   JLabel lblCity;
   JLabel lblSubject;
   JLabel lblTSubject;
   JLabel lblWarn;
   JTextField txtName;
   JTextField txtMatric;
   JTextField txtEmail;
   ButtonGroup bg;
   JRadioButton rbMale;
   JRadioButton rbFemale;
   JTextArea txaAdd;
   JComboBox cbCity;
   JCheckBox cbkSMC;
   JCheckBox cbkMAD;
   JCheckBox cbkIPT;
   JCheckBox cbkVB;
   JButton btnSubmit;
   JButton btnUpdate;
   JButton btnExit;
   JMenuBar mb;
   JMenu menuFile;
   JMenu menuAdmin;
   JMenuItem mNew;
   JMenuItem mLogin;
   Integer count = 0;

   public TNYProjectMain() 
   {
      super("Student Registration Form");

      try {
         connectDB();
         
      } catch (Exception e) 
      {
         System.out.println("Error!!!Cannot connect to database.");
      }

      prjMenu();
      PrjGUI();
      setJMenuBar(mb);
      setLayout(new FlowLayout());
      setSize(600, 700);
      setVisible(true);
      setLocationRelativeTo(null);
   }

   public void connectDB() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String path = "jdbc:mysql://localhost:3306/info?zeroDateTimeBehavior=CONVERT_TO_NULL";
		con = DriverManager.getConnection(path,"root","umisone23/24");
		
		dbm = con.getMetaData();
		rs = dbm.getTables(null,null,"tblStudent",null);
		if(rs.next())
		{
			System.out.println("Table exists");
		}
		else
		{
			stmt = con.createStatement();
			sql = "CREATE TABLE tblStudent (MatricNo VARCHAR(255) NOT NULL, Name VARCHAR(255), Gender VARCHAR(255), Email VARCHAR(255), Address VARCHAR(255), City VARCHAR(255), Subject VARCHAR(255), PRIMARY KEY(MatricNo))";
			
			stmt.executeUpdate(sql);
			System.out.println("Table created");
		}
	}

   public void prjMenu() 
   {
      mb = new JMenuBar();
      menuFile = new JMenu("File");
      menuAdmin = new JMenu("Admin");
      mNew = new JMenuItem("New");
      mLogin = new JMenuItem("Login");
      mLogin.addActionListener(this);
      mNew.addActionListener(this);
      menuFile.add(mNew);
      menuAdmin.add(mLogin);
      mb.add(menuFile);
      mb.add(menuAdmin);
   }

   public void PrjGUI() {
      pAll = new JPanel();
      pTop = new JPanel();
      pTitle = new JPanel();
      lblTitle = new JLabel("WELCOME TO PUO");
      lblTitle.setFont(new Font("Sans_Serif", Font.BOLD, 18));
      pTitle.add(lblTitle, BorderLayout.NORTH);
      
      pText = new JPanel();
      lblText = new JLabel("Please fill all the field");
      lblText.setFont(new Font("Serif", Font.PLAIN, 13));
      pText.add(lblText, BorderLayout.NORTH);
      pTop.add(pTitle);
      pTop.add(pText);
      pTop.setLayout(new BoxLayout(pTop, BoxLayout.Y_AXIS));
      
      
      pName = new JPanel();
      pLName = new JPanel();
      lblName = new JLabel("Name :        ");
      pLName.add(lblName);
      pRName = new JPanel();
      txtName = new JTextField(20);
      pRName.add(txtName);
      pName.add(pLName);
      pName.add(pRName);
      pName.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      
      pMatric = new JPanel();
      pLMatric = new JPanel();
      lblMatric = new JLabel("Matric No : ");
      pLMatric.add(lblMatric);
      pRMatric = new JPanel();
      txtMatric = new JTextField(20);
      pRMatric.add(txtMatric);
      pMatric.add(pLMatric);
      pMatric.add(pRMatric);
      pMatric.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      
      pGender = new JPanel();
      pLGen = new JPanel();
      lblGender = new JLabel("Gender :     ");
      pLGen.add(lblGender);
      pRGen = new JPanel();
      rbMale = new JRadioButton("Male");
      rbFemale = new JRadioButton("Female");
      bg = new ButtonGroup();
      bg.add(rbMale);
      bg.add(rbFemale);
      pRGen.add(rbMale);
      pRGen.add(rbFemale);
      pGender.add(pLGen);
      pGender.add(pRGen);
      pGender.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      
      pEmail = new JPanel();
      pLEmail = new JPanel();
      lblEmail = new JLabel("Email  :        ");
      pLEmail.add(lblEmail);
      pREmail = new JPanel();
      txtEmail = new JTextField(20);
      pREmail.add(txtEmail);
      pEmail.add(pLEmail);
      pEmail.add(pREmail);
      pEmail.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      
      pAddress = new JPanel();
      pLAddress = new JPanel();
      lblAddress = new JLabel("Address :    ");
      pLAddress.add(lblAddress);
      pRAddress = new JPanel();
      txaAdd = new JTextArea(8, 26);
      pRAddress.add(txaAdd);
      pAddress.add(pLAddress);
      pAddress.add(pRAddress);
      pAddress.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      
      pCity = new JPanel();
      pLCity = new JPanel();
      lblCity = new JLabel("City :             ");
      pLCity.add(lblCity);
      pRCity = new JPanel();
      String[] city = new String[]{"Please select your city", "Kuala Lumpur", "Ipoh", "Kuching", "Johor Bahru", "Kota Kinabalu", "George Town", "Shah Alam", "Malacca City", "Alor Setar", "Miri", "Petaling Jaya", "Kuala Terengganu", "Iskandar Puteri", "Seberang Perai", "Seremban", "Subang Jaya", "Pasir Gudang", "Kuantan"};
      cbCity = new JComboBox(city);
      pRCity.add(cbCity);
      pCity.add(pLCity);
      pCity.add(pRCity);
      pCity.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      
      pSubject = new JPanel();
      pLSubject = new JPanel();
      pTSubject = new JPanel();
      lblSubject = new JLabel("Subject :      ");
      lblTSubject = new JLabel("(Please select only 2 subjects)");
      lblTSubject.setFont(new Font("Serif", Font.PLAIN, 9));
      pTSubject.add(lblSubject);
      pTSubject.add(lblTSubject);
      pTSubject.setLayout(new BoxLayout(pTSubject, BoxLayout.Y_AXIS));
      pLSubject.add(pTSubject);
      pRSubject = new JPanel();
      pOSubject = new JPanel();
      cbkSMC = new JCheckBox("Secure Mobile Computing");
      cbkMAD = new JCheckBox("Mobile Application Development");
      cbkIPT = new JCheckBox("Intergrative Programming & Technologies");
      cbkVB = new JCheckBox("Visual Basic Programming");
      lblWarn = new JLabel("");
      lblWarn.setFont(new Font("Serif", Font.PLAIN, 13));
      lblWarn.setForeground(Color.RED);
      pOSubject.add(cbkSMC);
      pOSubject.add(cbkMAD);
      pOSubject.add(cbkIPT);
      pOSubject.add(cbkVB);
      pOSubject.add(lblWarn);
      pOSubject.setLayout(new BoxLayout(pOSubject, BoxLayout.Y_AXIS));
      pRSubject.add(pOSubject);
      pSubject.add(pLSubject);
      pSubject.add(pRSubject);
      pSubject.setLayout(new FlowLayout(FlowLayout.LEFT));
      cbkSMC.addItemListener(new ItemListener()
      {
      	public void itemStateChanged(ItemEvent ie)
      	{
      		if(ie.getStateChange() == ItemEvent.SELECTED)
      		{
      			count = count + 1;
      			if(count >=3)
      			{
      				lblWarn.setText("You have selected more than 2 subjects");
      			}
      		}
      		else
      		{
      			count = count - 1;
      			if(count <= 2)
      			{
      				lblWarn.setText("");
      			}
      		}
      	}
      });
      
      cbkMAD.addItemListener(new ItemListener()
      {
      	public void itemStateChanged(ItemEvent ie)
      	{
      		if(ie.getStateChange() == ItemEvent.SELECTED)
      		{
      			count = count + 1;
      			if(count >=3)
      			{
      				lblWarn.setText("You have selected more than 2 subjects");
      			}
      		}
      		else
      		{
      			count = count - 1;
      			if(count <= 2)
      			{
      				lblWarn.setText("");
      			}
      		}
      	}
      });
      
      
      cbkIPT.addItemListener(new ItemListener()
      {
      	public void itemStateChanged(ItemEvent ie)
      	{
      		if(ie.getStateChange() == ItemEvent.SELECTED)
      		{
      			count = count + 1;
      			if(count >=3)
      			{
      				lblWarn.setText("You have selected more than 2 subjects");
      			}
      		}
      		else
      		{
      			count = count - 1;
      			if(count <= 2)
      			{
      				lblWarn.setText("");
      			}
      		}
      	}
      });
      
      
      cbkVB.addItemListener(new ItemListener()
      {
      	public void itemStateChanged(ItemEvent ie)
      	{
      		if(ie.getStateChange() == ItemEvent.SELECTED)
      		{
      			count = count + 1;
      			if(count >=3)
      			{
      				lblWarn.setText("You have selected more than 2 subjects");
      			}
      		}
      		else
      		{
      			count = count - 1;
      			if(count <= 2)
      			{
      				lblWarn.setText("");
      			}
      		}
      	}
      });
      
     
      pButton = new JPanel();
      btnSubmit = new JButton("SUBMIT");
      btnSubmit.setBackground(Color.GREEN);
      btnSubmit.setForeground(Color.WHITE);
      btnUpdate = new JButton("UPDATE");
      btnUpdate.setBackground(Color.BLUE);
      btnUpdate.setForeground(Color.WHITE);
      btnExit = new JButton("EXIT");
      btnExit.setBackground(Color.RED);
      btnExit.setForeground(Color.WHITE);
      pButton.add(btnSubmit);
      pButton.add(btnUpdate);
      pButton.add(btnExit);
      pButton.setLayout(new FlowLayout(FlowLayout.LEFT));
      btnSubmit.addActionListener(this);
      btnUpdate.addActionListener(this);
      btnExit.addActionListener(this);
      
      
      pAll.add(pTop);
      pAll.add(pName);
      pAll.add(pMatric);
      pAll.add(pGender);
      pAll.add(pEmail);
      pAll.add(pAddress);
      pAll.add(pCity);
      pAll.add(pSubject);
      pAll.add(pButton);
      pAll.setLayout(new BoxLayout(pAll, BoxLayout.Y_AXIS));
      add(pAll);
   }


   public void validateDataI() throws Exception 
   	{
      String nama = txtName.getText();
      String matric = txtMatric.getText();
      String address = txaAdd.getText();
      String email = txtEmail.getText();
      
      if (nama.hashCode() == 0)
      {
         JOptionPane.showMessageDialog(null, "Name field is empty!\nPlease fill it up. ", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else if (matric.hashCode() == 0) 
      {
         JOptionPane.showMessageDialog(null, "Matric No field is empty!\nPlease fill it up. ", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else if (!rbMale.isSelected() && !rbFemale.isSelected())
      {
         JOptionPane.showMessageDialog(null, "Please select a gender.", "Alert", JOptionPane.WARNING_MESSAGE);
      }
      else if (email.hashCode() == 0) 
      {
         JOptionPane.showMessageDialog(null, "Email field is empty!\nPlease fill it up. ", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else if (address.hashCode() == 0) 
      {
         JOptionPane.showMessageDialog(null, "Address field is empty!\nPlease fill it up. ", "Alert", JOptionPane.WARNING_MESSAGE);
      }
      else if (cbCity.getSelectedItem().toString() == "Please select your city") 
      {
         JOptionPane.showMessageDialog(null, "Please choose your city. ", "Alert", JOptionPane.WARNING_MESSAGE);
      }
      else if (!cbkSMC.isSelected() && !cbkMAD.isSelected() && !cbkIPT.isSelected() && !cbkVB.isSelected())
      {
         JOptionPane.showMessageDialog(null, "Please check 2 subjects.", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else if (count >= 3) 
      {
         JOptionPane.showMessageDialog(null, "You have selected more than 2 subject. ", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else 
      {
         try 
         {
            insertData();
            JOptionPane.showMessageDialog(null, "Data has been saved.");
            txtName.setText("");
            txtMatric.setText("");
            bg.clearSelection();
            txtEmail.setText("");
            txaAdd.setText("");
            cbCity.setSelectedIndex(0);
            cbkSMC.setSelected(false);
            cbkMAD.setSelected(false);
            cbkIPT.setSelected(false);
            cbkVB.setSelected(false);
         } catch (Exception e) 
         {
            JOptionPane.showMessageDialog(null, "Error!!!\nData can't save.\nThere is an exsisting Matric No.", "Alert", JOptionPane.WARNING_MESSAGE);
         }
      }
   }

   public void validateDataU() throws Exception 
   {
      String nama = txtName.getText();
      String matric = txtMatric.getText();
      String address = txaAdd.getText();
      String email = txtEmail.getText();
      
      if (nama.hashCode() == 0)
      {
         JOptionPane.showMessageDialog(null, "Name field is empty!\nPlease fill it up. ", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else if (matric.hashCode() == 0) 
      {
         JOptionPane.showMessageDialog(null, "Matric No field is empty!\nPlease fill it up. ", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else if (!rbMale.isSelected() && !rbFemale.isSelected())
      {
         JOptionPane.showMessageDialog(null, "Please select a gender.", "Alert", JOptionPane.WARNING_MESSAGE);
      }
      else if (email.hashCode() == 0) 
      {
         JOptionPane.showMessageDialog(null, "Email field is empty!\nPlease fill it up. ", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else if (address.hashCode() == 0) 
      {
         JOptionPane.showMessageDialog(null, "Address field is empty!\nPlease fill it up. ", "Alert", JOptionPane.WARNING_MESSAGE);
      }
      else if (cbCity.getSelectedItem().toString() == "Please select your city") 
      {
         JOptionPane.showMessageDialog(null, "Please choose your city. ", "Alert", JOptionPane.WARNING_MESSAGE);
      }
      else if (!cbkSMC.isSelected() && !cbkMAD.isSelected() && !cbkIPT.isSelected() && !cbkVB.isSelected())
      {
         JOptionPane.showMessageDialog(null, "Please check JOptionPane.WARNING_MESSAGE subjects.", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else if (count >= 3) 
      {
         JOptionPane.showMessageDialog(null, "You have selected more than 2 subject. ", "Alert", JOptionPane.WARNING_MESSAGE);
      } 
      else 
      {
         try 
         {
            checkingData();
         } catch (Exception e) {}
      }
   }

   public void insertData() throws Exception 
   {
      String SMC = "";
      String MAD = "";
      String IPT = "";
      String VB = "";
      String Subject = "";
      String gender;
      
      if (rbMale.isSelected())
      {
         gender = "Male";
      } 
      else 
      {
         gender = "Female";
      }

      if (cbkSMC.isSelected()) 
      {
         SMC = " (Secure Mobile Computing) ";
      }

      if (cbkMAD.isSelected()) 
      {
         MAD = " (Mobile Application Development) ";
      }

      if (cbkIPT.isSelected()) 
      {
         IPT = " (Intergrative Programming & Technologies) ";
      }

      if (cbkVB.isSelected()) 
      {
         VB = " (Visual Basic Programming) ";
      }

      Subject = SMC + MAD + IPT + VB;
      
      stmt = con.createStatement();
      sql = "INSERT INTO tblStudent VALUES ('" + txtMatric.getText() + "', '" + 
      	     txtName.getText() + "', '" + gender + "' ,'" + txtEmail.getText() + 
      	     "', '" + txaAdd.getText() + "', '" + cbCity.getSelectedItem().toString() + 
      	     "', '" + Subject + "')";
      stmt.executeUpdate(sql);
   }

   public void checkingData() throws Exception 
   {
      stmt = con.createStatement();
      sql = "SELECT * FROM tblStudent WHERE MatricNo = '" + txtMatric.getText() + "'";
      rs = stmt.executeQuery(sql);
      if (rs.next()) 
      {
         try
         {
            updateData();
         } catch (Exception e) {}
      } 
      else 
      {
         JOptionPane.showMessageDialog(null, "There is no such Matric No","Alert",JOptionPane.WARNING_MESSAGE);
      }
   }

   public void updateData() throws Exception
   {
      String SMC = "";
      String MAD = "";
      String IPT = "";
      String VB = "";
      String Subject = "";
      String gender;
      
      if (rbMale.isSelected())
      {
         gender = "Male";
      } 
      else
      {
         gender = "Female";
      }

      if (cbkSMC.isSelected()) 
      {
         SMC = " (Secure Mobile Computing) ";
      }

      if (cbkMAD.isSelected()) 
      {
         MAD = " (Mobile Application Development) ";
      }

      if (cbkIPT.isSelected()) 
      {
         IPT = " (Intergrative Programming & Technologies) ";
      }

      if (cbkVB.isSelected()) 
      {
         VB = " (Visual Basic Programming) ";
      }

      Subject = SMC + MAD + IPT + VB;
      
      stmt = con.createStatement();
      sql = "UPDATE tblStudent SET Name = '" + txtName.getText() + 
      	    "', Gender = '" + gender + "', Email = '" + txtEmail.getText() + 
      	    "', Address = '" + txaAdd.getText() + "', City = '" + cbCity.getSelectedItem().toString() +
      	    "', Subject = '" + Subject + "' WHERE MatricNo = '" + txtMatric.getText() + "'";
      stmt.executeUpdate(sql);
      
      JOptionPane.showMessageDialog(null, "Data has been updated.");
      txtName.setText("");
      txtMatric.setText("");
      bg.clearSelection();
      txtEmail.setText("");
      txaAdd.setText("");
      cbCity.setSelectedIndex(0);
      cbkSMC.setSelected(false);
      cbkMAD.setSelected(false);
      cbkIPT.setSelected(false);
      cbkVB.setSelected(false);
   }


   public void actionPerformed(ActionEvent ae) 
   {
      if (ae.getSource() == mNew) 
      {
         int clear = JOptionPane.showConfirmDialog(null, "Are you sure?");
         if (clear == JOptionPane.YES_OPTION)
         {
            txtName.setText("");
            txtMatric.setText("");
            bg.clearSelection();
            txtEmail.setText("");
            txaAdd.setText("");
            cbCity.setSelectedIndex(0);
            cbkSMC.setSelected(false);
            cbkMAD.setSelected(false);
            cbkIPT.setSelected(false);
            cbkVB.setSelected(false);
            JOptionPane.showMessageDialog(null, "Input has been cleared");
         } 
      } 
      else if (ae.getSource() == btnSubmit) 
      {
         try 
         {
            validateDataI();
         } catch (Exception e)
         {
            JOptionPane.showMessageDialog(null, "ERROR!!!.", "Alert", JOptionPane.WARNING_MESSAGE);
         }
      } 
      else if (ae.getSource() == btnUpdate) 
      {
         try 
         {
            validateDataU();
         } catch (Exception e) 
         {
            JOptionPane.showMessageDialog(null, "ERROR!!!.", "Alert", JOptionPane.WARNING_MESSAGE);
         }
      }
      else if (ae.getSource() == btnExit) 
      {
         JOptionPane.showMessageDialog(null, "Thank you for using.");
         dispose();
      } 
      else if (ae.getSource() == mLogin) 
      {
         new TNYProjectPassword();
         dispose();
      }
   }

   public static void main(String[] args) {
      new TNYProjectMain().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
