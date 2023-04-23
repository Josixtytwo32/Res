import java.sql.*;
import javax.swing.*;

public class ConnectDB{
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    //Constructor
    public ConnectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
            st=con.createStatement();
            

        }catch (ClassNotFoundException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error:" +ex);
        }
    }
    
    public void insertData(String prdcode, String categ, String itemdes, int quant, float price, float total)
    {
        String query;
        try{
            query = "INSERT INTO `inventory`(`ProductCode`, `Product Category`, `Item Description`, `Quantity`, `Unit Price`, `Amount`) VALUES ('"+prdcode+"','"+categ+"','"+itemdes+"',"+quant+","+price+","+total+")";
            st.executeUpdate(query);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    }
    public void Transaclog (String ProductCode, int BuyID){
        String query;
        try{
            query = "INSERT INTO `transactionlog`(`ProductCode`, `BuyID`)VALUES('"+ProductCode+"', "+BuyID+")";
            st.executeUpdate(query);
        }catch(SQLException ex){    
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    }
    public void UserAccountsTable (String Username, String Passwd, String AccType){
        String query;
        try{
            query = "INSERT INTO `account`(`Username`, `Passwd`, `AccType`) VALUES ('"+Username+"','"+Passwd+"', '"+AccType+"' )";          
            st.executeUpdate(query);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    }   
    public void Buy (int buyid, String Customersname, String ProductCode,  float Quantity, float Amount, String dop){
        String query;
        try{
            query = "INSERT INTO `buytable`(`BuyID`, `Customersname`, `ProductCode`, `Amount`, `Quantity`, `DoP`) VALUES ("+buyid+", '"+Customersname+"', '"+ProductCode+"' , "+Amount+", "+Quantity+", '"+dop+"')";
            
            st.executeUpdate(query);    
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    }
    public static void main(String args[])
    {
        ConnectDB cdb=new ConnectDB();  
        
    }
        
}       
