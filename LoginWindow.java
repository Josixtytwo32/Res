import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class LoginWindow
{
private static JLabel userLabel,passwordLabel;
private static JTextField userText;
private static JPasswordField passwordText;
private static JButton Login;


private String dbName = "sql12600942";
private String driver = "com.mysql.cj.jdbc.Driver";
private String userName = "root"; 
private String password = "";

public boolean isInternet() {
    return true;
}

public void execute() {
    
}

public ArrayList<ArrayList<String>> retrieve() {
    ArrayList<ArrayList<String>> accountsSet = new ArrayList<ArrayList<String>>();
    try 
    {
      Class.forName(driver).newInstance();
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
      
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select Username, Passwd, AccType from Account;");
      
      while (rs.next()) {
          ArrayList<String> accPair = new ArrayList<String>();
          
          accPair.add(rs.getString("Username"));
          accPair.add(rs.getString("Passwd"));
          accPair.add(rs.getString("AccType"));
          
          accountsSet.add(accPair);
      }
      conn.close();
    } catch (Exception e) 
    {
      e.printStackTrace();
    }
    return accountsSet;
}
public LoginWindow() 
{
        ArrayList<ArrayList<String>> accs = this.retrieve();
        Icon InventoryIcon = new ImageIcon(System.getProperty("user.dir") + File.separator + "Inventory.png");
        Icon TransacIcon = new ImageIcon(System.getProperty("user.dir") + File.separator + "Transac.png");
        Icon BuyIcon = new ImageIcon(System.getProperty("user.dir") + File.separator + "Buy.png");
        Icon UserIcon = new ImageIcon(System.getProperty("user.dir") + File.separator + "User.png");
        Icon LogOutIcon = new ImageIcon(System.getProperty("user.dir") + File.separator + "LogOut.png");
        JPanel Panel = new JPanel();
        JLabel Green = new JLabel("3E's IMS", JLabel.CENTER);
        JLabel G = new JLabel("Inventory Management System", JLabel.CENTER);
        JLabel Icon = new JLabel();
        
        JLabel Error = new JLabel();
        JLabel Success = new JLabel();
        
        JLabel Welcome = new JLabel("Welcome,");
        JLabel Acc = new JLabel();
        
        JFrame Frame = new JFrame();
        Frame.setTitle("3E'S IMS");
        ImageIcon Logo = new ImageIcon(System.getProperty("user.dir") + File.separator + "Logo.png");
        Frame.setIconImage(Logo.getImage());
        
        Green.setFont(new Font("Arial", Font.BOLD, 35));
        Green.setBounds(-10,-10,280,85);
        Green.setForeground(Color.WHITE);
        Green.setBackground(new Color(41, 73, 20));
        Green.setOpaque(true);
        
        G.setFont(new Font("Arial", Font.BOLD, 12));
        G.setBounds(-10,50,280,14);
        G.setForeground(Color.WHITE);
        G.setOpaque(false);
        
        Welcome.setFont(new Font("Arial", Font.BOLD, 20));
        Welcome.setBounds(88, 185, 100, 35);
        Welcome.setBackground(Color.BLACK);
        Welcome.setVisible(false);
        
        Acc.setBackground(Color.BLACK);
        Acc.setVisible(false);
        
        Icon.setBounds(110,100,50,50);
        Icon.setIcon(new ImageIcon("./Icon.png"));
        Panel.add(Icon);
        
        userLabel = new JLabel("Username");
        userLabel.setBounds(35, 170, 80, 25);
        Panel.add(userLabel);
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(35, 205, 80, 25);
        Panel.add(passwordLabel);
        
        userText = new JTextField(20);
        userText.setBounds(125, 170, 110, 25);
        Panel.add(userText);
        userText.setBackground(Color.GRAY);
        userText.setForeground(Color.WHITE);
        userText.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.WHITE));
        
        passwordText = new JPasswordField();
        passwordText.setBounds(125, 205, 110, 25);
        Panel.add(passwordText);
        passwordText.setBackground(Color.GRAY);
        passwordText.setForeground(Color.WHITE);
        passwordText.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.WHITE));
        
        userText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.transferFocus();
            }
        });
        
        JButton Ok = new JButton("Okay");
        Ok.setBounds(95, 260, 80, 25);
        Ok.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Frame.dispose();
                new MainWindow(InventoryIcon, TransacIcon, BuyIcon, UserIcon, LogOutIcon, Acc);
            }
        });
        
        passwordText.addActionListener(new ActionListener() 
        {
        @Override
        public void actionPerformed(ActionEvent e) 
            {
            String user = userText.getText();
            String password = passwordText.getText();
            
            Error.setText("");
            Success.setText("");
            if(user.equals(accs.get(0).get(0)) && password.equals(accs.get(0).get(1)))
            {
                Login.setVisible(false);
                Login.setEnabled(false);
                
                Welcome.setVisible(true);
                
                Acc.setText("Staff");
                Acc.setFont(new Font("Arial", Font.BOLD, 17));
                Acc.setBounds(80, 215, 145, 35);
                Acc.setVisible(true);
                
                Ok.setVisible(true);
                Ok.requestFocusInWindow();
                
                userLabel.setVisible(false);
                passwordLabel.setVisible(false);
                userText.setVisible(false);
                passwordText.setVisible(false);
                
                Success.setText("Log-in Successful");
                userText.setText("");
                passwordText.setText("");
            }
            else if(user.equals(accs.get(1).get(0)) && password.equals(accs.get(1).get(1)))
            {
                Login.setVisible(false);
                Login.setEnabled(false);
                
                Welcome.setVisible(true);
                
                Acc.setText("Supervisor");
                Acc.setFont(new Font("Arial", Font.BOLD, 20));
                Acc.setBounds(80, 215, 145, 35);
                Acc.setVisible(true);
                
                Ok.setVisible(true);
                Ok.requestFocusInWindow();
                
                userLabel.setVisible(false);
                passwordLabel.setVisible(false);
                userText.setVisible(false);
                passwordText.setVisible(false);
                
                Success.setText("Log-in Successful");
                userText.setText("");
                passwordText.setText("");
            }
            else
            {
                Error.setText("Incorrect Username/Password, Try again!");
                userText.setText("");
                passwordText.setText("");
            }
            }  
        });
        
        Ok.setVisible(false);
        Ok.setForeground(Color.BLACK);
        Ok.setBorder(new LineBorder(Color.GRAY));
        Ok.setContentAreaFilled(false);
        
        Login = new JButton("Log In");
        Login.setBounds(95, 260, 80, 25);
        Login.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            String user = userText.getText();
            String password = passwordText.getText();
            
            Error.setText("");
            Success.setText("");
             
            if(user.equals(accs.get(0).get(0)) && password.equals(accs.get(0).get(1)))
            {
                Login.setVisible(false);
                Login.setEnabled(false);
                
                Welcome.setVisible(true);
                
                Acc.setText("Staff");
                Acc.setFont(new Font("Arial", Font.BOLD, 17));
                Acc.setBounds(80, 215, 145, 35);
                Acc.setVisible(true);
                
                Ok.setVisible(true);
                Ok.requestFocusInWindow();
                
                userLabel.setVisible(false);
                passwordLabel.setVisible(false);
                userText.setVisible(false);
                passwordText.setVisible(false);
                
                Success.setText("Log-in Successful");
                userText.setText("");
                passwordText.setText("");
            }
            else if(user.equals(accs.get(1).get(0)) && password.equals(accs.get(1).get(1)))
            {
                Login.setVisible(false);
                Login.setEnabled(false);
                
                Welcome.setVisible(true);
                
                Acc.setText("Supervisor");
                Acc.setFont(new Font("Arial", Font.BOLD, 20));
                Acc.setBounds(80, 215, 145, 35);
                Acc.setVisible(true);
                
                Ok.setVisible(true);
                Ok.requestFocusInWindow();
                
                userLabel.setVisible(false);
                passwordLabel.setVisible(false);
                userText.setVisible(false);
                passwordText.setVisible(false);
                
                Success.setText("Log-in Successful");
                userText.setText("");
                passwordText.setText("");
            }
            else
            {
                Error.setText("Incorrect Username/Password, Try again!");
                userText.setText("");
                passwordText.setText("");
            }
            }  
        });
        Panel.add(Login);
        Panel.add(Welcome);
        Panel.add(Acc);
        Panel.add(Ok);
        
        Login.setForeground(Color.BLACK);
        Login.setBorder(new LineBorder(Color.GRAY));
        Login.setContentAreaFilled(false);
        
        Error.setFont(new Font("Arial", Font.BOLD, 9));
        Error.setBounds(40,240, 200, 14);
        Error.setForeground(Color.RED);
        Panel.add(Error);
        
        Success.setFont(new Font("Arial", Font.BOLD,  11));
        Success.setBounds(87, 160, 200, 14);
        Success.setForeground(Color.GREEN);
        Panel.add(Success);
        
        Panel.setLayout(null);
        Green.setLayout(null);
        Panel.add(G);
        Panel.add(Green);
        Panel.add(Icon);
        Frame.add(Panel);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(280, 350);
        Frame.setResizable(false);
        Frame.setVisible(true);
        Frame.setLocationRelativeTo(null);
} 
}
