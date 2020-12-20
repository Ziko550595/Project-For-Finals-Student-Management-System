import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame
{
	Container c;
	JButton btnAdd, btnView, btnUpdate , btnDelete;
	Color col = new Color(175, 93, 246);

	MainFrame()
	{
		c = getContentPane();
		c.setLayout(new FlowLayout());
		c.setBackground(new Color(175, 93, 246));
	btnAdd = new JButton("Add");
	btnAdd.setBackground(Color.magenta);
	btnView = new JButton("View"); 
	btnView.setBackground(Color.magenta);
	btnUpdate = new JButton("Update");
	btnUpdate.setBackground(Color.magenta);
	JLabel icon = new JLabel(new ImageIcon("logo.png"));
	JLabel text = new JLabel("Student Management System of Ala-Too University App");
	this.addWindowListener(new WL());

	c.add(btnAdd);
	c.add(btnView);
	c.add(btnUpdate);
	c.add(icon);
	c.add(text);

btnAdd.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae)
	{
		AddFrame a =new AddFrame();
		dispose();
	}
});

btnView.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae)
	{
		ViewFrame a =new ViewFrame();
		dispose();
	}
});

btnUpdate.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae){
		UpdateFrame a = new UpdateFrame();
		dispose();
	}
});

	setTitle("Student management system");
	setSize(500,400);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	}


	public static void main(String[] args) 
	{
		MainFrame m = new MainFrame();	

	}


class WL implements WindowListener {

	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	public void windowOpened(WindowEvent e)
	{
		JOptionPane.showMessageDialog(c , "Welcome to Student Management System ");
	}

	public void windowClosed(WindowEvent e) {}

	public void windowClosing(WindowEvent e)
	{
		int output = JOptionPane.showConfirmDialog(c , "Are you Sure ? ", null , JOptionPane.YES_OPTION);
		if(output == JOptionPane.YES_OPTION)
		{	
			JOptionPane.showMessageDialog(null , "Please visit again");
			System.exit(1);
		}
		 

	}

	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}

}

}


class DbHandler {
	
	public void addStudent(int rno , String name) {
		try {
			Connection con = DriverManager.getConnection
			("jdbc:mysql://localhost:3306/db2","root","tipoparol");
			String sql = "insert into student values(?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,rno);
			pst.setString(2,name);
			int r =pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog() , r + " records inserted");
			con.close();

		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(),"ii" +e);
		}
	}

	public String viewStudent() 
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			Connection con = DriverManager.getConnection 
			("jdbc:mysql://localhost:3306/db2","root","tipoparol");
			String sql = "select * from Student";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				int rno = rs.getInt(1);
				String name = rs.getString(2);
				sb.append("Rno :" + rno + "                      Name :" + name + "\n");
			}
			rs.close();
			con.close();

		}
		catch(SQLException e) {}
		return sb.toString();
	}

	public void UpdateStudent(int rno ,String name)
	{	
		try
		{
			Connection con = DriverManager.getConnection 
			("jdbc:mysql://localhost:3306/db2","root","tipoparol");
			String sql1 = "update student set name=? where rno=? ";
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(2, rno);
			pstmt1.setString(1,name);
			int z = pstmt1.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),z + " Name Updated" );
			con.close();


		}
		catch(SQLException e)
		{
			System.out.println("Update issue" + e);
		}
	}
	public void DeleteStudent(int rno)
	{
		try
		{
			Connection con = DriverManager.getConnection 
			("jdbc:mysql://localhost:3306/db2","root","tipoparol");
			String s2 = "delete from student where rno=?";
			PreparedStatement p1 = con.prepareStatement(s2);
			p1.setInt(1,rno);
			int r = p1.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " Student Data Deleted ");
			con.close();


		}
		catch(SQLException e)
		{
			System.out.println("Delete issue" + e);
		}
	


	}

}