import java.sql.*;
import javax.swing.*;

public class ConnectDB{
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    //Constructor
    public ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
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
    
    public static void main(String args[])
    {
        ConnectDB cdb=new ConnectDB();
    
    }
        
}