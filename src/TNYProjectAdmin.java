import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class TNYProjectAdmin extends JFrame implements ActionListener
{
	Connection con;
	Statement stmt;
	DatabaseMetaData dbm;
	ResultSet rs;
	String sql, output;
	JLabel lblMatric;
	JTextField txtMatric;
	JButton btnSearch, btnDelete;
	JTextArea txaView;
	JScrollPane scroll;
	JPanel pAll,pMatric,pLMatric,pRMatric, pButton, pTextView;
	JMenuBar mb;
	JMenu menuFile;
	JMenuItem mBack, mView;
		
	
	public TNYProjectAdmin()	
	{
		super("Admin Page");
		
		try
		{
			connectDB();
			JOptionPane.showMessageDialog(null,"Database connected.");
		}catch(Exception e){
			System.out.println("Error!!!Cannot connect to database.");
		}
		
		adminMenu();
		adminGUI();
		
		try
		{
			ViewAll();
			txaView.setText(output);
		}catch(Exception e){}
		
		setJMenuBar(mb);
		setSize(1100,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
	}
	
	public void ViewAll() throws Exception
	{
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM tblStudent");
		output = "Name \t Matric No \t\t Gender \t Email \t\t Address \t City \t Subject \n";
		while(rs.next())
		{
			output = output + rs.getString("Name") + "\t"+
				     rs.getString("MatricNo") + "\t" +
				     rs.getString("Gender") + "\t" +
				     rs.getString("Email") + "\t" +
				     rs.getString("Address") + "\t" +
				     rs.getString("City") + "\t" +
				     rs.getString("Subject") + "\n";
		}
	}
	
	
	public void connectDB() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String path = "jdbc:mysql://localhost:3306/info?useTimezone=true&serverTimezone=UTC";
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
	
	public void adminMenu()
	{
		mb = new JMenuBar();
		menuFile = new JMenu("File");
		mBack = new JMenuItem("Back");
		mView = new JMenuItem("View");
		menuFile.add(mBack);
		menuFile.add(mView);
		mb.add(menuFile);
		add(mb);
		mBack.addActionListener(this);
		mView.addActionListener(this);
	}
	
	public void adminGUI()
	{
		pAll =  new JPanel();
		
		pMatric = new JPanel();
		pLMatric = new JPanel();
		lblMatric = new JLabel("Matric No : ");
		pLMatric.add(lblMatric);
		pRMatric = new JPanel();
		txtMatric = new JTextField(10);
		pRMatric.add(txtMatric);
		pMatric.add(pLMatric);
		pMatric.add(pRMatric);
		pMatric.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pButton = new JPanel();
		btnSearch = new JButton("SEARCH");
		btnDelete = new JButton("DELETE");
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		pButton.add(btnSearch);
		pButton.add(btnDelete);
		pButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pTextView = new JPanel();
		txaView = new JTextArea(25,90);
		scroll = new JScrollPane (txaView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pTextView.add(scroll);
		
		
		pAll.add(pMatric);
		pAll.add(pButton);
		pAll.add(pTextView);
		pAll.setLayout(new BoxLayout(pAll, BoxLayout.Y_AXIS));
		add(pAll);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == mBack)
		{
			new TNYProjectMain();
			dispose();
		}
		else if(ae.getSource() == btnSearch)
		{
			try
			{
				checkingDataS();
			}catch(Exception e){}
		}
		else if(ae.getSource() == btnDelete)
		{
			try
			{
				checkingDataD();
			}catch(Exception e){}
		}
		else if(ae.getSource() == mView)
		{
			try
			{
				ViewAll();
				txaView.setText(output);
			}catch(Exception e){}
		}
	}
	
	public void checkingDataS()throws Exception
	{
		stmt = con.createStatement();
		sql = "SELECT * FROM tblStudent WHERE MatricNo = '" + txtMatric.getText() + "'";
		rs = stmt.executeQuery(sql);
		
		if(rs.next())
		{
			try
			{
				searchData();
				txaView.setText(output);
				txtMatric.setText("");
					
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"ERROR!!!");
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null,"There is no such Matric No","Alert",JOptionPane.WARNING_MESSAGE);
		}
	}
	
		public void checkingDataD()throws Exception
		{
		stmt = con.createStatement();
		sql = "SELECT * FROM tblStudent WHERE MatricNo = '" + txtMatric.getText() + "'";
		rs = stmt.executeQuery(sql);
		
		if(rs.next())
		{
			try
			{
				deleteData();
				txaView.setText(output);
					
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"ERROR!!!");
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null,"There is no such Matric No","Alert",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void searchData() throws Exception
	{
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM tblStudent WHERE MatricNo = '" + txtMatric.getText() + "'");
		output = "Name \t Matric No \t\t Gender \t Email \t\t Address \t City \t Subject \n";
		while(rs.next())
		{
			output = output + rs.getString("Name") + "\t"+
				     rs.getString("MatricNo") + "\t" +
				     rs.getString("Gender") + "\t" +
				     rs.getString("Email") + "\t" +
				     rs.getString("Address") + "\t" +
				     rs.getString("City") + "\t" +
				     rs.getString("Subject") + "\n";
		}
	}
	
	public void deleteData() throws Exception
	{
		int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to delete ?");
		if (confirm == JOptionPane.YES_OPTION)
		{
			stmt = con.createStatement();
			sql = "DELETE FROM tblStudent WHERE MatricNo = '" + txtMatric.getText() + "'";
			stmt.executeUpdate(sql);
			
			JOptionPane.showMessageDialog(null,"Matric No has been deleted.");
			txtMatric.setText("");
			
			try
			{
				ViewAll();
				txaView.setText(output);
				
			}catch(Exception e){}
		}
	}
}