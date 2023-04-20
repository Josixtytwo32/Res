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
            query = "INSERT INTO `inventory`(`ProductCode`, `Category`, `Item Description`, `Quantity`, `Price`, `Total`) VALUES ('"+prdcode+"','"+categ+"','"+itemdes+"',"+quant+","+price+","+total+")";
            st.executeUpdate(query);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    }
    public void Transaclog (int TransactionCode, String ProductCode, String Category, String ItemDescription, int Quantity, float Price, float Total){
        String query;
        try{
            query = "INSERT INTO 'transactionlog' ('Trancsaction Code', 'Product Code', 'Category', 'Item Description', ' Quantity', 'Price', 'Total')VALUES('"+TransactionCode+"', '"+ProductCode+"' , '"+Category+"', '"+ItemDescription+"', "+Quantity+", "+Price+", "+Total+")";
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
    public void Buy ( String ProductCode, String Category, String ItemDescription, int Quantity, float Price, float Total){
        String query;
        try{
            query = "INSERT INTO `transactionlog` (`Transaction Code`, `Product Code`, `Category`, `Item Description`, `Quantity`, `Price`, `Total`) VALUES ( NULL, '"+ProductCode+"' , '"+Category+"', '"+ItemDescription+"', "+Quantity+", "+Price+", "+Total+")";
            
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
