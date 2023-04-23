import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.lang.*;
import javax.swing.event.*;
import javax.swing.table.*;


public class MainWindow 
{
    public Icon InventoryIcon;
    public Icon TransacIcon;
    public Icon BuyIcon;
    public Icon UserIcon;
    public Icon LogOutIcon; 
    
 
    public void updates(Object[][] vals, JTable table, JTextField... tf) {
        // Save column sizes
        int[] columnSizes = new int[table.getColumnCount()];
        for (int i = 0; i < columnSizes.length; i++) {
            columnSizes[i] = table.getColumnModel().getColumn(i).getWidth();
        }
    
        String productcode = tf[0].getText();
        String itemdescription = tf[1].getText();
        int updateV = Integer.valueOf(tf[2].getText());
        int cc =  Integer.valueOf("" + vals[0][3].toString());
        
        if (!productcode.equals("") && !itemdescription.equals("")) {
            try { 
            String query = "update sql12600942.inventory set Quantity = '" + (cc + updateV) + "', Amount = '" + ((float) (cc + updateV) * Float.parseFloat(vals[0][4].toString())) + "' where ProductCode = '" + productcode + "';";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
            Statement pst = con.createStatement();
            pst.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Stock Updated!!!");
            table.setModel(new DefaultTableModel(getTable(), new String[]{"ProductCode", "Product Category", "Item Description", "Quantity", "Unit Price", "Amount"}));
            con.close();
            for (JTextField tff : tf ) {
                tff.setText("");
            }
            
            // Restore column sizes
            for (int i = 0; i < columnSizes.length; i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(columnSizes[i]);
            }
            
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }
    
    public void minusstock(Object[][] vals, JTable table, JTextField... tf) {
        // Save column sizes
        int[] columnSizes = new int[table.getColumnCount()];
        for (int i = 0; i < columnSizes.length; i++) {
            columnSizes[i] = table.getColumnModel().getColumn(i).getWidth();
        }
    
        String productcode = tf[0].getText();
        String itemdescription = tf[1].getText();
        int updateV = Integer.valueOf(tf[2].getText());
        int cc =  Integer.valueOf("" + vals[0][3].toString());
        
        if (!productcode.equals("") && !itemdescription.equals("")) {
            try { 
            String query = "update sql12600942.inventory set Quantity = '" + (cc - updateV) + "', Amount = '" + ((float) (cc + updateV) * Float.parseFloat(vals[0][4].toString())) + "' where ProductCode = '" + productcode + "';";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
            Statement pst = con.createStatement();
            pst.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Stock Updated!!!");
            table.setModel(new DefaultTableModel(getTable(), new String[]{"ProductCode", "Product Category", "Item Description", "Quantity", "Unit Price", "Amount"}));
            con.close();
            for (JTextField tff : tf ) {
                
            }
            
            // Restore column sizes
            for (int i = 0; i < columnSizes.length; i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(columnSizes[i]);
            }
            
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }
    
    public void deletes(Object[][] vals, JTable table, JTextField... tf) {
        String ProdCode = tf[0].getText();

        // Save column sizes before updating the table model
        int[] columnSizes = new int[table.getColumnModel().getColumnCount()];
        for (int i = 0; i < columnSizes.length; i++) {
            columnSizes[i] = table.getColumnModel().getColumn(i).getWidth();
        }
    
        if (!ProdCode.equals("")) {
            try { 
                String query = "DELETE FROM inventory WHERE `inventory`.`ProductCode` = ProdCode";
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println(query);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                Statement pst = con.createStatement();
                pst.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Stock Deleted!!!");

                // Restore column sizes after updating the table model
                DefaultTableModel model = new DefaultTableModel(getTable(), new String[]{"ProductCode", "Product Category", "Item Description", "Quantity", "Unit Price", "Amount"});
                table.setModel(model);
                for (int i = 0; i < columnSizes.length; i++) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(columnSizes[i]);
                }

                con.close();
                for (JTextField tff : tf ) {
                    tff.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object[][] getTable() {
        String query = "select * from sql12600942.inventory;";
        
        ArrayList<ArrayList<Object>> arryB = new ArrayList<ArrayList<Object>>();

        int rowCount = 1;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                    Statement pst = con.createStatement();
                    ResultSet rs = pst.executeQuery(query);
                                
                    while (rs.next()) {
                        ArrayList<Object> arry = new ArrayList<Object>();

                        arry.add(rs.getString("ProductCode"));
                        arry.add(rs.getString("Product Category"));
                        arry.add(rs.getString("Item Description"));
                        arry.add(rs.getInt("Quantity"));
                        arry.add(rs.getBigDecimal("Unit Price"));
                        arry.add(rs.getBigDecimal("Amount"));
                        
                        arryB.add(arry);
                    }
                
                   con.close();
                }
            catch(Exception ex) {
                ex.printStackTrace();
            }
            
            Object[][] mainobj = new Object[arryB.size()][6];
        
            for (int i = 0; i < arryB.size(); i++) {
                for (int j = 0; j < arryB.get(i).size(); j++) {
                    mainobj[i][j] = arryB.get(i).get(j);
                }
            }
            
            return mainobj;
        }

    public Object[][] getTable(String cell) {
        String query = "select * from sql12600942.inventory where Product Category = '" + cell + "';";        
        ArrayList<ArrayList<Object>> arryB = new ArrayList<ArrayList<Object>>();
        System.out.println();
        int rowCount = 10;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                Statement pst = con.createStatement();
                ResultSet rs = pst.executeQuery(query);
                                
                while (rs.next()) {
                    ArrayList<Object> arry = new ArrayList<Object>();
                    
                    arry.add(rs.getString("ProductCode"));
                    arry.add(rs.getString("Product Category"));
                    arry.add(rs.getString("Item Description"));
                    arry.add(rs.getInt("Quantity"));
                    arry.add(rs.getBigDecimal("Unit Price"));
                    arry.add(rs.getBigDecimal("Amount"));
                    
                    arryB.add(arry);
                }
                
               con.close();
            }
        catch(Exception ex) {
            ex.printStackTrace();
        }
            
        Object[][] mainobj = new Object[arryB.size()][6];
        
        for (int i = 0; i < arryB.size(); i++) {
            for (int j = 0; j < arryB.get(i).size(); j++) {
                mainobj[i][j] = arryB.get(i).get(j);
            }
        }
            
        return mainobj;
    }
    
    
    public Object[][] getTable(String cell, boolean is) {
        String query = "select * from sql12600942.inventory where ProductCode = '" + cell + "';";
        ArrayList<ArrayList<Object>> arryB = new ArrayList<ArrayList<Object>>();

        int rowCount = 1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                Statement pst = con.createStatement();
                ResultSet rs = pst.executeQuery(query);
                                
                while (rs.next()) {
                    ArrayList<Object> arry = new ArrayList<Object>();
                    
                    arry.add(rs.getString("ProductCode"));
                    arry.add(rs.getString("Product Category"));
                    arry.add(rs.getString("Item Description"));
                    arry.add(rs.getInt("Quantity"));
                    arry.add(rs.getBigDecimal("Unit Price"));
                    arry.add(rs.getBigDecimal("Amount"));
                    
                    arryB.add(arry);
                }
                
               con.close();
            }
        catch(Exception ex) {
            ex.printStackTrace();
        }
            
        Object[][] mainobj = new Object[arryB.size()][6];
        
        for (int i = 0; i < arryB.size(); i++) {
            for (int j = 0; j < arryB.get(i).size(); j++) {
                mainobj[i][j] = arryB.get(i).get(j);
            }
        }
            
        return mainobj;
    }
    
    public int getRowByValue(JTable table, Object value, int column){
        for(int i=0; i<table.getRowCount(); i++){
            //if(table.getValueAt(i, column).equals(value)){
            if(table.getValueAt(i, column).toString().equalsIgnoreCase(value.toString())){
                return i;
            }
        }
        return -1; // If the value is not found in any row
    }
    public Object[][] getTabletrans() {
        String query = "select * from transactionlog AS transL, inventory AS inv, buytable AS buyT where transL.BuyID = buyT.BuyID && transL.ProductCode = inv.ProductCode;";
        
        ArrayList<ArrayList<Object>> arryB = new ArrayList<ArrayList<Object>>();
        
        int rowCount = 1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                Statement pst = con.createStatement();
                ResultSet rs = pst.executeQuery(query);
                                
                while (rs.next()) {
                    ArrayList<Object> arry = new ArrayList<Object>();
                    arry.add(rs.getString("buyT.DoP"));
                    arry.add(rs.getInt("buyT.BuyID"));
                    arry.add(rs.getString("buyT.Customersname"));
                    arry.add(rs.getString("ProductCode"));
                    arry.add(rs.getString("inv.Item Description"));
                    arry.add(rs.getInt("buyT.Quantity"));
                    arry.add(rs.getBigDecimal("inv.Unit Price"));
                    arry.add(rs.getBigDecimal("buyT.Amount"));                    
                    arryB.add(arry);
                }
                
               con.close();
            }
        catch(Exception ex) {
            ex.printStackTrace();
        }
            
        Object[][] mainobj = new Object[arryB.size()][8];
        
        for (int i = 0; i < arryB.size(); i++) {
            for (int j = 0; j < arryB.get(i).size(); j++) {
                mainobj[i][j] = arryB.get(i).get(j);
            }
        }
            
        return mainobj;
        }
        
        public Object[][] getTableBuy() {
        String query = "select * from buytable AS bt, inventory AS inv WHERE bt.ProductCode = inv.ProductCode;";
        
        ArrayList<ArrayList<Object>> arryB = new ArrayList<ArrayList<Object>>();
        
        int rowCount = 1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                Statement pst = con.createStatement();
                ResultSet rs = pst.executeQuery(query);
                                
                while (rs.next()) {
                    ArrayList<Object> arry = new ArrayList<Object>();
                    arry.add(rs.getInt("BuyID"));
                    arry.add(rs.getString("Customersname"));
                    arry.add(rs.getString("Product Code"));
                    arry.add(rs.getString("inv.Item Description"));
                    arry.add(rs.getInt("Quantity"));
                    arry.add(rs.getBigDecimal("inv.Unit Price"));
                    arry.add(rs.getBigDecimal("Amount"));
                    arry.add(rs.getString("DoP"));
                    
                    arryB.add(arry);
                }
                
               con.close();
            }
        catch(Exception ex) {
            ex.printStackTrace();
        }
            
        Object[][] mainobj = new Object[arryB.size()][8];
        
        for (int i = 0; i < arryB.size(); i++) {
            for (int j = 0; j < arryB.get(i).size(); j++) {
                mainobj[i][j] = arryB.get(i).get(j);
            }
        }
            
        return mainobj;
        
    }
        
    public Object[][] getTableUser() {
        String query = "select * from sql12600942.account;";
        
        ArrayList<ArrayList<Object>> arryB = new ArrayList<ArrayList<Object>>();

        int rowCount = 1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                Statement pst = con.createStatement();
                ResultSet rs = pst.executeQuery(query);
                                
                while (rs.next()) {
                    ArrayList<Object> arry = new ArrayList<Object>();
                    
                    arry.add(rs.getString("Username"));
                    arry.add(rs.getString("Passwd"));
                    arry.add(rs.getString("AccType"));
                    
                    arryB.add(arry);
                }
                
               con.close();
            }
        catch(Exception ex) {
            ex.printStackTrace();
        }
            
        Object[][] mainobj = new Object[arryB.size()][3];
        
        for (int i = 0; i < arryB.size(); i++) {
            for (int j = 0; j < arryB.get(i).size(); j++) {
                mainobj[i][j] = arryB.get(i).get(j);
            }
        }
            
        return mainobj;
    }
    public MainWindow(Icon ii, Icon ti, Icon bi, Icon ui, Icon loi, JLabel acc) 
    {
        JFrame root = new JFrame();
        JPanel panel = new JPanel();
        ImageIcon Logo = new ImageIcon(System.getProperty("user.dir") + File.separator + "Logo.png");
        root.setIconImage(Logo.getImage());
        
        panel.setLayout(null);
        
        // Inventory Section
        
        JLabel green = new JLabel("");
        JLabel Inven = new JLabel("Inventory");
        JLabel Trans = new JLabel("Transaction");
        JLabel Buys = new JLabel("Buy");
        JLabel Users = new JLabel("Users");
        JLabel OutLog = new JLabel("Log Out");
        JLabel Account = new JLabel(acc.getText());
        JLabel TypeOfMaterial = new JLabel("Type of Material");
        JLabel Search = new JLabel("Search:");
        JLabel ProductCode = new JLabel("Product Code:");
        JLabel ItemDescription = new JLabel("Item Description:");
        JLabel StockToAdd = new JLabel("Stock to Add:");
        
        JButton Inventory = new JButton(ii);
        JButton Transac = new JButton(ti);
        JButton Buy = new JButton(bi);
        JButton User = new JButton(ui);
        JButton LogOut = new JButton(loi);
        JButton AddNewItem = new JButton("Add new Item");
        JButton Update = new JButton("Update");
        JButton RemoveItem = new JButton("Remove Item");
        JTextField SearchBar = new JTextField();
        JTextField ProductCodeBar = new JTextField();
        JTextField ItemDescriptionBar = new JTextField();
        JTextField StockToAddBar = new JTextField();

        green.setBounds(-10,-10,900,100);
        green.setForeground(Color.WHITE);
        green.setBackground(new Color(41, 73, 20));
        green.setOpaque(true);
        
        Inven.setFont(new Font("Arial", Font.BOLD, 10));
        Inven.setForeground(Color.WHITE);
        Inven.setBounds(32,21,100,100);
        Inven.setOpaque(false);
        
        Trans.setFont(new Font("Arial", Font.BOLD, 10));
        Trans.setForeground(Color.WHITE);
        Trans.setBounds(106,21,100,100);
        Trans.setOpaque(false);
        
        Buys.setFont(new Font("Arial", Font.BOLD, 10));
        Buys.setForeground(Color.WHITE);
        Buys.setBounds(206,21,100,100);
        Buys.setOpaque(false);
        
        Users.setFont(new Font("Arial", Font.BOLD, 10));
        Users.setForeground(Color.WHITE);
        Users.setBounds(281,21,100,100);
        Users.setOpaque(false);
        
        OutLog.setFont(new Font("Arial", Font.BOLD, 10));
        OutLog.setForeground(Color.WHITE);
        OutLog.setBounds(823,15,100,100);
        OutLog.setOpaque(false);
        
        JPanel panel_ = new JPanel();
        panel_.setLayout(new GridLayout(5,2));
        
        JTextField AddProductCodeBar = new JTextField();
        JTextField AddCategoryBar = new JTextField();
        JTextField AddItemDescriptionBar = new JTextField();
        JTextField AddQuantityBar = new JTextField();
        JTextField AddPriceBar = new JTextField();
        
        panel_.add(new JLabel("Product Code"));
        panel_.add(AddProductCodeBar);
        panel_.add(new JLabel("Product Category"));
        panel_.add(AddCategoryBar);
        panel_.add(new JLabel("Item Description"));
        panel_.add(AddItemDescriptionBar);
        panel_.add(new JLabel("Quantity"));
        panel_.add(AddQuantityBar);
        panel_.add(new JLabel("Unit Price"));
        panel_.add(AddPriceBar);
        
        AddNewItem.setBounds(100, 120, 120, 25);
        AddNewItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        AddNewItem.setContentAreaFilled(false);
        AddNewItem.setOpaque(false);
        
        TypeOfMaterial.setFont(new Font("Arial", Font.BOLD, 12));
        TypeOfMaterial.setForeground(Color.BLACK);
        TypeOfMaterial.setBounds(420,120,120,25);
        TypeOfMaterial.setOpaque(false);
        
        Search.setFont(new Font("Arial", Font.BOLD, 12));
        Search.setForeground(Color.BLACK);
        Search.setBounds(690,120,120,25);
        Search.setOpaque(false);
        SearchBar.setBounds(740,120,120,25);
        SearchBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        SearchBar.setOpaque(false);
        
        
        
        LogOut.setBounds(825,20,35,35);
        LogOut.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                root.dispose();
                new LoginWindow();
            }
        });
        
        JTable Lamisa = new JTable(); 
        Lamisa.setModel(new DefaultTableModel(getTable(), new String[]{"ProductCode", "Product Category", "Item Description", "Stock", "Unit Price", "Amount"}));
        Lamisa.getColumnModel().getColumn(2).setPreferredWidth(220);
        Lamisa.getColumnModel().getColumn(4).setPreferredWidth(50);
        Lamisa.getColumnModel().getColumn(5).setPreferredWidth(50);
        Lamisa.setRowSelectionAllowed(true);
        Lamisa.getTableHeader().setReorderingAllowed(false);
        Lamisa.getTableHeader().setResizingAllowed(false);
        JScrollPane MainLamisa = new JScrollPane(Lamisa); 
        MainLamisa.setBounds(30,160,830,220);
        AddNewItem.addActionListener(e ->
        {
            AddProductCodeBar.setText("");
            AddCategoryBar.setText("");
            AddItemDescriptionBar.setText("");
            AddQuantityBar.setText("");
            AddPriceBar.setText("");
    
            
            int AddNewItemWindow = JOptionPane.showOptionDialog(
                null,panel_,"Add New Item",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,new String[]{
                "Add"},"Add");
                
            if (!AddProductCodeBar.getText().equals("") && !AddCategoryBar.getText().equals("") && !AddItemDescriptionBar.getText().equals("") && !AddQuantityBar.getText().equals("") && !AddPriceBar.getText().equals("") && AddNewItemWindow==0){
                DefaultTableModel model = (DefaultTableModel) Lamisa.getModel();
                Object[] row = { AddProductCodeBar.getText(), AddCategoryBar.getText(), AddItemDescriptionBar.getText(), AddQuantityBar.getText(), AddPriceBar.getText(), 
                                ((float) Integer.valueOf(AddQuantityBar.getText())) * Float.parseFloat(AddPriceBar.getText()) };
                model.addRow(row);
                
                ConnectDB cdb=new ConnectDB();
                cdb.insertData(AddProductCodeBar.getText(),AddCategoryBar.getText(),AddItemDescriptionBar.getText(),Integer.valueOf(AddQuantityBar.getText()),Float.parseFloat(AddPriceBar.getText()),((float) Integer.valueOf(AddQuantityBar.getText())) * Float.parseFloat(AddPriceBar.getText()));
            }
            });
        
          SearchBar.addKeyListener(new KeyListener() {
            
            DefaultTableModel model;
            
            public void search (String str){
                model = (DefaultTableModel) Lamisa.getModel();
                TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
                Lamisa.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter("(?i)"+str));
            }         
            @Override
            public void keyTyped(KeyEvent event) {
                
            }
         
            @Override
            public void keyReleased(KeyEvent event) {
               String searchString = SearchBar.getText();
               search(searchString);
            }
         
            @Override
            public void keyPressed(KeyEvent event) {
               
            }
        });  
            
        ProductCode.setFont(new Font("Arial", Font.BOLD, 12));
        ProductCode.setForeground(Color.BLACK);
        ProductCode.setBounds(180,430,150,25);
        ProductCode.setOpaque(false);
        ProductCodeBar.setBounds(265,430,150,25);
        ProductCodeBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ProductCodeBar.setOpaque(false);
        
        JMenuItem all_ = new JMenuItem("All");
        JMenuItem Adapter = new JMenuItem("Adapter");
        JMenuItem Bar = new JMenuItem("Bar");
        JMenuItem Cement = new JMenuItem("Cement");
        JMenuItem Coupling = new JMenuItem("Coupling");
        JMenuItem Hardiflex = new JMenuItem("Hardiflex");
        JMenuItem Pipe = new JMenuItem("Pipe");
        JMenuItem Plywood = new JMenuItem("Plywood");
        JMenuItem Sheet = new JMenuItem("Sheet");
        JComboBox Category = new JComboBox(new String[]{"All", "Adapter", "Bar", "Cement", "Coupling", "Hardiflex", "Pipe", "Plywood", "Sheet"});
        Category.setFont(new Font("Arial", Font.BOLD, 12));
        Category.setBounds(520, 120, 120, 25);
        Category.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                String cats = (String) Category.getItemAt(Category.getSelectedIndex());
                if (cats.equals("All")) {
                Lamisa.setModel(new DefaultTableModel(getTable(), new String[]{"ProductCode", "Product Category", "Item Description", "Quantity", "Unit Price", "Amount"}));   
                } else {
                Lamisa.setModel(new DefaultTableModel(getTable(cats), new String[]{"ProductCode", "Product Category", "Item Description", "Quantity", "Unit Price", "Amount"}));
                }
            }
        });

        ItemDescription.setFont(new Font("Arial", Font.BOLD, 12));
        ItemDescription.setForeground(Color.BLACK);
        ItemDescription.setBounds(163,470,150,25);
        ItemDescription.setOpaque(false);
        ItemDescriptionBar.setBounds(265,470,150,25);
        ItemDescriptionBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ItemDescriptionBar.setOpaque(false);
    
        ((AbstractDocument)ProductCodeBar.getDocument()).addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                Object[][] xd = getTable(ProductCodeBar.getText(), true);
                if (xd.length != 0) {
                    String name = (String) xd[0][2];
                    ItemDescriptionBar.setText(name);
                }
            }
            public void removeUpdate(DocumentEvent e) {
                Object[][] xd = getTable(ProductCodeBar.getText(), true);
                if (xd.length != 0) {
                    String name = (String) xd[0][2];
                    ItemDescriptionBar.setText(name);
                }
            }
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        
        StockToAdd.setFont(new Font("Arial", Font.BOLD, 12));
        StockToAdd.setForeground(Color.BLACK);
        StockToAdd.setBounds(168,510,150,25);
        StockToAdd.setOpaque(false);
        StockToAddBar.setBounds(265,510,150,25);
        StockToAddBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        StockToAddBar.setOpaque(false);
        
        Update.setBounds(500, 460, 120, 50);
        Update.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        Update.setContentAreaFilled(false);
        Update.setOpaque(false);
        Update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    updates(getTable(ProductCodeBar.getText(), true), Lamisa, ProductCodeBar, ItemDescriptionBar, StockToAddBar);
            }
        });
        
        RemoveItem.setBounds(630, 460, 120, 50);
        RemoveItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        RemoveItem.setContentAreaFilled(false);
        RemoveItem.setOpaque(false);
        RemoveItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int stat=JOptionPane.showConfirmDialog(null,"Are you sure you want to remove the item?", "Remove Item",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (stat==0){
                int rowIndex = Lamisa.getSelectedRow();
                
                String value = Lamisa.getModel().getValueAt(rowIndex, 0).toString();
            
                         String query = "DELETE FROM inventory WHERE `inventory`.`ProductCode` = '"+value+"'";
                        try
                        {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                            Statement pst = con.createStatement();
                            pst.executeUpdate(query);
                            
                            DefaultTableModel model = (DefaultTableModel)Lamisa.getModel();
                            model.removeRow(rowIndex);
                        }
                        catch(ClassNotFoundException | SQLException ex){
                            JOptionPane.showMessageDialog(null, "Error: "+ex);
        
                        }
                }
            }
        });
        
        
        // Transaction Log Section
        JTable TransactionLogTable = new JTable();
        TransactionLogTable.setModel(new DefaultTableModel(getTabletrans(), new String[]{"Date Purchase","Receipt No.", "Customer's Name","Product Code", "Item Description", "Quantity", "Unit Price", "Amount"}));
        TransactionLogTable.getColumnModel().getColumn(2).setPreferredWidth(220);
        TransactionLogTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        TransactionLogTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        TransactionLogTable.setRowSelectionAllowed(true);
        JScrollPane TransacLogTable = new JScrollPane(TransactionLogTable); 
        TransacLogTable.setBounds(30,160,830,320);
        TransacLogTable.setVisible(false);
        
        JLabel TransacTotalSales = new JLabel("Total Sales: ₱");
        JTextField TransacTotalSalesBar = new JTextField(); 
        
         
    
        SearchBar.addKeyListener(new KeyListener() {
                    
            DefaultTableModel model;
            
            public void search (String str){
                model = (DefaultTableModel) TransactionLogTable.getModel();
                TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
                TransactionLogTable.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter("(?i)"+str));
            }         
            @Override
            public void keyTyped(KeyEvent event) {
                
            }
         
            @Override
            public void keyReleased(KeyEvent event) {
               String searchString = SearchBar.getText();
               search(searchString);
            }
         
            @Override
            public void keyPressed(KeyEvent event) {
               
            }
        });  
        
        TransacTotalSales.setFont(new Font("Arial", Font.BOLD, 12));
        TransacTotalSales.setForeground(Color.BLACK);
        TransacTotalSales.setBounds(30,480,150,25);
        TransacTotalSales.setOpaque(false);
        TransacTotalSales.setVisible(false);
        TransacTotalSalesBar.setBounds(120,480,50,25);
        TransacTotalSalesBar.setBorder(BorderFactory.createEmptyBorder());
        TransacTotalSalesBar.setEditable(false);
        TransacTotalSalesBar.setOpaque(false);
        TransacTotalSalesBar.setVisible(false);
        
        // Buy Section
        JTable BuyTable = new JTable();
        BuyTable.setModel(new DefaultTableModel(getTableBuy(), new String[]{"Receipt No.","Customer's Name","Product Code", "Item Description", "Quantity", "Unit Price", "Amount", "Date of Purchase"}));
        BuyTable.getColumnModel().getColumn(0).setPreferredWidth(45);
        BuyTable.getColumnModel().getColumn(1).setPreferredWidth(55);
        BuyTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        BuyTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        BuyTable.getColumnModel().getColumn(4).setPreferredWidth(35);
        BuyTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        BuyTable.getColumnModel().getColumn(6).setPreferredWidth(70);
        BuyTable.setRowSelectionAllowed(true);
        JScrollPane BTable = new JScrollPane(BuyTable); 
        BTable.setBounds(30,160,830,320);
        BTable.setVisible(false);

        JLabel BuyProductCode = new JLabel("Product Code");
        JLabel BuyItemDescription = new JLabel("Item Description");
        JLabel RecNo = new JLabel("Receipt No.");
        JLabel BuyPrice = new JLabel("Unit Price");
        JLabel BuyQuantity = new JLabel("Quantity");
        JLabel BuyCustomersName = new JLabel("Sold to");
        JLabel BuyDate = new JLabel("Date of Purchase:");
        
        JTextField BuyFillUpBar = new JTextField();
        JTextField BuyProductCodeBar = new JTextField();
        JTextField BuyItemDescriptionBar = new JTextField();
        JTextField RecNoBar = new JTextField();
        JTextField BuyPriceBar = new JTextField();
        JTextField BuyQuantityBar = new JTextField();
        JTextField BuyCustomersNameBar = new JTextField();
        JTextField BuyDateBar = new JTextField();
        
        JButton BuyAdd = new JButton("Add");
        
        BuyAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            if (!BuyCustomersNameBar.getText().equals("") && !BuyFillUpBar.getText().equals("") && !BuyProductCodeBar.getText().equals("") && !BuyItemDescriptionBar.getText().equals("") && !RecNoBar.getText().equals("") && !BuyPriceBar.getText().equals("") && !BuyQuantityBar.getText().equals("") && !BuyDateBar.getText().equals("")){
                DefaultTableModel model = (DefaultTableModel) BuyTable.getModel();
                /*Object[] row = { BuyCustomersNameBar.getText(),BuyFillUpBar.getText(), BuyProductCodeBar.getText(), BuyItemDescriptionBar.getText(), RecNoBar.getText(), BuyPriceBar.getText(), 
                                ((float) Float.parseFloat(BuyQuantityBar.getText())) * Float.parseFloat(BuyPriceBar.getText()), BuyDateBar.getText() };*/
                                
                 Object[] row = {RecNoBar.getText(),BuyCustomersNameBar.getText(),BuyProductCodeBar.getText(),BuyItemDescriptionBar.getText(),Float.parseFloat(BuyQuantityBar.getText()), BuyPriceBar.getText(), ((float) Float.parseFloat(BuyQuantityBar.getText())) * Float.parseFloat(BuyPriceBar.getText()),
                   BuyDateBar.getText() };
                model.addRow(row);
                
                ConnectDB cdb=new ConnectDB();
                
                //cdb.Buy(BuyCustomersNameBar.getText(),BuyProductCodeBar.getText(), Integer.valueOf(BuyQuantityBar.getText()), Float.parseFloat(BuyPriceBar.getText()), ((float) Integer.valueOf(BuyQuantityBar.getText())) * Float.parseFloat(BuyPriceBar.getText()));
                cdb.Buy(Integer.valueOf(RecNoBar.getText()),BuyCustomersNameBar.getText(), BuyProductCodeBar.getText(), Float.parseFloat(BuyQuantityBar.getText()), Float.parseFloat(BuyQuantityBar.getText()) * Float.parseFloat(BuyPriceBar.getText()), BuyDateBar.getText());
                
                cdb.Transaclog(BuyProductCodeBar.getText(), Integer.valueOf(RecNoBar.getText()));
                
                minusstock(getTable(BuyProductCodeBar.getText(), true), Lamisa, BuyProductCodeBar, BuyItemDescriptionBar, BuyQuantityBar);
                
            }       
            }
        });
        
        JButton BuyCalculate = new JButton("Calculate");
        
        BuyCalculate.addActionListener(e -> {
            JLabel TotalAmount = new JLabel("Total amount:");
            JTextField TotalAmountBar = new JTextField();
            
            JLabel AmountPaid = new JLabel("Amount paid:");
            JTextField AmountPaidBar = new JTextField();
            
            JLabel ChangeLabel = new JLabel("Change:");
            JTextField ChangeBar = new JTextField();
            ChangeBar.setEditable(false);
            
            Object[] fields = {TotalAmount, TotalAmountBar, AmountPaid, AmountPaidBar, ChangeLabel, ChangeBar};
            JOptionPane pane = new JOptionPane(fields, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, null, new Object[]{"Compute"});
            
            AmountPaidBar.getDocument().addDocumentListener(new DocumentListener() {
                int rowIndex = BuyTable.getSelectedRow();
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateChange();
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateChange();
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateChange();
                                       
                }
                private void updateChange() {
                    try {
                            double totalAmount = Double.parseDouble(TotalAmountBar.getText());
                            double amountPaid = Double.parseDouble(AmountPaidBar.getText());
                            double change = amountPaid - totalAmount;
                            ChangeBar.setText(String.format("₱ %.2f", change));
                           
                        } catch (NumberFormatException ex) {
                            // ignore invalid input
                        }
                }
                               
            });
            /* if (!BuyProductCodeBar.getText().equals("") && !RecNoBar.getText().equals("") && !AmountPaidBar.getText().equals("") && !ChangeBar.getText().equals("")){
                                DefaultTableModel model = (DefaultTableModel) TransactionLogTable.getModel();
                                Object[] row = {BuyProductCodeBar.getText(), RecNoBar.getText(), BuyPriceBar.getText(),Double.parseDouble(AmountPaidBar.getText()), Double.parseDouble(ChangeBar.getText())};
                                model.addRow(row);
                            
                                ConnectDB cdb=new ConnectDB();
                            
                                cdb.Transaclog(BuyProductCodeBar.getText(), Integer.valueOf(RecNoBar.getText()),Double.parseDouble(AmountPaidBar.getText()), Double.parseDouble(ChangeBar.getText()));
                            } */
            
            JDialog dialog = pane.createDialog(null, "Change Calculator");
            dialog.setVisible(true);
            dialog.dispose();
        });
        
        
        JButton BuyClear= new JButton("Clear");
        
        BuyClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!BuyCustomersNameBar.getText().equals("") && !BuyFillUpBar.getText().equals("") && !BuyProductCodeBar.getText().equals("") && !BuyItemDescriptionBar.getText().equals("") && !RecNoBar.getText().equals("") && !BuyPriceBar.getText().equals("") && !BuyQuantityBar.getText().equals("") && !BuyDateBar.getText().equals("")){
                DefaultTableModel model = (DefaultTableModel) TransactionLogTable.getModel();
                   // Object[] row = { BuyCustomersNameBar.getText(),BuyFillUpBar.getText(), BuyProductCodeBar.getText(), BuyItemDescriptionBar.getText(), RecNoBar.getText(), BuyPriceBar.getText(), 
                          //      ((float) Integer.valueOf(BuyQuantityBar.getText())) * Float.parseFloat(BuyPriceBar.getText()), BuyDateBar.getText() };
                                
                Object[] row = { BuyDateBar.getText(),RecNoBar.getText(),BuyCustomersNameBar.getText(), BuyProductCodeBar.getText(), BuyItemDescriptionBar.getText(),Integer.valueOf(BuyQuantityBar.getText()),
                    BuyPriceBar.getText(),((float) Integer.valueOf(BuyQuantityBar.getText())) * Float.parseFloat(BuyPriceBar.getText()) };
                model.addRow(row);
                
                ConnectDB cdb=new ConnectDB();
                
              
                cdb.Transaclog(BuyProductCodeBar.getText(), Integer.valueOf(RecNoBar.getText()));
            }       
                
               double sum = 0;
        for (int i = 0; i <TransactionLogTable.getRowCount(); i++)
        {
            sum = sum + Double.parseDouble(TransactionLogTable.getValueAt(i,7).toString());
        }
        TransacTotalSalesBar.setText(Double.toString(sum));    
            
                DefaultTableModel model = (DefaultTableModel)BuyTable.getModel();
                model.setRowCount(0);
                
                 BuyFillUpBar.setText("");
                 BuyProductCodeBar.setText("");
                 BuyItemDescriptionBar.setText("");
                 RecNoBar.setText("");
                 BuyPriceBar.setText("");
                 BuyQuantityBar.setText("");
                 BuyCustomersNameBar.setText("");
                 BuyDateBar.setText("");
                
            }
        });
        
        JButton BuyRemove = new JButton("Remove");
        
        BuyFillUpBar.setBounds(70,150,285,25);
        BuyFillUpBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyFillUpBar.setHorizontalAlignment(SwingConstants.CENTER);
        BuyFillUpBar.setOpaque(false);
        BuyFillUpBar.setVisible(false);
        
        BuyProductCode.setFont(new Font("Arial", Font.BOLD, 12));
        BuyProductCode.setForeground(Color.BLACK);
        BuyProductCode.setBounds(107,195,150,25);
        BuyProductCode.setOpaque(false);
        BuyProductCode.setVisible(false);
        BuyProductCodeBar.setBounds(195,195,160,25);
        BuyProductCodeBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyProductCodeBar.setOpaque(false);
        BuyProductCodeBar.setVisible(false);
        
        BuyItemDescription.setFont(new Font("Arial", Font.BOLD, 12));
        BuyItemDescription.setForeground(Color.BLACK);
        BuyItemDescription.setBounds(90,230,150,25);
        BuyItemDescription.setOpaque(false);
        BuyItemDescription.setVisible(false);
        BuyItemDescriptionBar.setBounds(195,230,160,25);
        BuyItemDescriptionBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyItemDescriptionBar.setOpaque(false);
        BuyItemDescriptionBar.setVisible(false);
        
        RecNo.setFont(new Font("Arial", Font.BOLD, 12));
        RecNo.setForeground(Color.BLACK);
        RecNo.setBounds(115,265,150,25);
        RecNo.setOpaque(false);
        RecNo.setVisible(false);
        RecNoBar.setBounds(195,265,160,25);
        RecNoBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        RecNoBar.setOpaque(false);
        RecNoBar.setVisible(false);
        
        BuyPrice.setFont(new Font("Arial", Font.BOLD, 12));
        BuyPrice.setForeground(Color.BLACK);
        BuyPrice.setBounds(132,300,150,25);
        BuyPrice.setOpaque(false);
        BuyPrice.setVisible(false);
        BuyPriceBar.setBounds(195,300,160,25);
        BuyPriceBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyPriceBar.setOpaque(false);
        BuyPriceBar.setVisible(false);
        
        ((AbstractDocument)BuyFillUpBar.getDocument()).addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                int i_ = getRowByValue(Lamisa, BuyFillUpBar.getText(), 0);
                
                if (i_ >= 0) {
                    BuyProductCodeBar.setText(String.valueOf(Lamisa.getValueAt(i_, 0)));
                    BuyItemDescriptionBar.setText(String.valueOf(Lamisa.getValueAt(i_, 2)));
                    
                    BuyPriceBar.setText(String.valueOf(Lamisa.getValueAt(i_, 4)));
                } else {
                    BuyProductCodeBar.setText("");
                    BuyItemDescriptionBar.setText("");

                    BuyPriceBar.setText("");
                }
            }
            
            public void removeUpdate(DocumentEvent e) {
               insertUpdate(e);
            }
            public void changedUpdate(DocumentEvent e) {
                System.out.println(e);
            }
        });
        
        BuyQuantity.setFont(new Font("Arial", Font.BOLD, 12));
        BuyQuantity.setForeground(Color.BLACK);
        BuyQuantity.setBounds(140,335,150,25);
        BuyQuantity.setOpaque(false);
        BuyQuantity.setVisible(false);
        BuyQuantityBar.setBounds(195,335,160,25);
        BuyQuantityBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyQuantityBar.setOpaque(false);
        BuyQuantityBar.setVisible(false);
        
        BuyCustomersName.setFont(new Font("Arial", Font.BOLD, 12));
        BuyCustomersName.setForeground(Color.BLACK);
        BuyCustomersName.setBounds(145,370,150,25);
        BuyCustomersName.setOpaque(false);
        BuyCustomersName.setVisible(false);
        BuyCustomersNameBar.setBounds(195,370,160,25);
        BuyCustomersNameBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyCustomersNameBar.setOpaque(false);
        BuyCustomersNameBar.setVisible(false);
        
        BuyDate.setFont(new Font("Arial", Font.BOLD, 12));
        BuyDate.setForeground(Color.BLACK);
        BuyDate.setBounds(90,405,150,25);
        BuyDate.setOpaque(false);
        BuyDate.setVisible(false);
        BuyDateBar.setBounds(195,405,160,25);
        BuyDateBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyDateBar.setOpaque(false);
        BuyDateBar.setVisible(false);
        
        BuyAdd.setBounds(70, 450, 80, 40);
        BuyAdd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyAdd.setContentAreaFilled(false);
        BuyAdd.setOpaque(false);
        BuyAdd.setVisible(false);
        
        BuyCalculate.setBounds(173, 450, 80, 40);
        BuyCalculate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyCalculate.setContentAreaFilled(false);
        BuyCalculate.setOpaque(false);
        BuyCalculate.setVisible(false);
        
        BuyClear.setBounds(70, 500, 280, 40);
        BuyClear.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyClear.setContentAreaFilled(false);
        BuyClear.setOpaque(false);
        BuyClear.setVisible(false);
        
        BuyRemove.setBounds(275, 450, 80, 40);
        BuyRemove.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyRemove.setContentAreaFilled(false);
        BuyRemove.setOpaque(false);
        BuyRemove.setVisible(false);
        
         // Users Account Section
        JTable UserAccountsTable = new JTable();
        UserAccountsTable.setModel(new DefaultTableModel(getTableUser(), new String[]{"Username","Passwd","AccType"}));
        JScrollPane UserAndAccountsTable = new JScrollPane(UserAccountsTable);
        UserAndAccountsTable.setBounds(400,130,440,250);
        UserAndAccountsTable.setVisible(false);
        
        JLabel UserUsername = new JLabel("Username");
        JLabel UserPassword = new JLabel("Password");
        JLabel UserAccountType = new JLabel("Account Type");
        
        JTextField UserUsernameBar = new JTextField();
        JTextField UserPasswordBar = new JTextField();
        JTextField UserAccountTypeBar = new JTextField();
        
        JButton UserNew = new JButton("New");
        JButton UserDelete = new JButton("Delete");
        
        UserUsername.setFont(new Font("Arial", Font.BOLD, 12));
        UserUsername.setForeground(Color.BLACK);
        UserUsername.setBounds(107,150,150,25);
        UserUsername.setOpaque(false);
        UserUsername.setVisible(false);
        UserUsernameBar.setBounds(205,150,160,25);
        UserUsernameBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        UserUsernameBar.setOpaque(false);
        UserUsernameBar.setVisible(false);
        
        UserPassword.setFont(new Font("Arial", Font.BOLD, 12));
        UserPassword.setForeground(Color.BLACK);
        UserPassword.setBounds(107,195,150,25);
        UserPassword.setOpaque(false);
        UserPassword.setVisible(false);
        UserPasswordBar.setBounds(205,195,160,25);
        UserPasswordBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        UserPasswordBar.setOpaque(false);
        UserPasswordBar.setVisible(false);
        
        UserAccountType.setFont(new Font("Arial", Font.BOLD, 12));
        UserAccountType.setForeground(Color.BLACK);
        UserAccountType.setBounds(107,240,150,25);
        UserAccountType.setOpaque(false);
        UserAccountType.setVisible(false);
        UserAccountTypeBar.setBounds(205,240,160,25);
        UserAccountTypeBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        UserAccountTypeBar.setOpaque(false);
        UserAccountTypeBar.setVisible(false);
    
        UserNew.setBounds(135, 300, 80, 40);
        UserNew.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        UserNew.setContentAreaFilled(false);
        UserNew.setOpaque(false);
        UserNew.setVisible(false);
        UserNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!UserUsernameBar.getText().equals("") && !UserPasswordBar.getText().equals("") && !UserAccountTypeBar.getText().equals("")){
                DefaultTableModel model = (DefaultTableModel) UserAccountsTable.getModel();
                Object[] row = { UserUsernameBar.getText(), UserPassword.getText(), UserAccountTypeBar.getText()};
                model.addRow(row);
                
                ConnectDB cdb=new ConnectDB();
                cdb.UserAccountsTable(UserUsernameBar.getText(),UserPasswordBar.getText(),UserAccountTypeBar.getText());
            }
            }
        });
        
        UserDelete.setBounds(257, 300, 80, 40);
        UserDelete.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        UserDelete.setContentAreaFilled(false);
        UserDelete.setOpaque(false);
        UserDelete.setVisible(false);
        UserDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int stat=JOptionPane.showConfirmDialog(null,"Are you sure you want to remove the item?", "Remove Item",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (stat==0){
                    int rowIndex = UserAccountsTable.getSelectedRow();
                    
                    String value = UserAccountsTable.getModel().getValueAt(rowIndex, 0).toString();
            
                    String query = "DELETE FROM account WHERE `account`.`Username` = '"+value+"'";
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                        Statement pst = con.createStatement();
                        pst.executeUpdate(query);
                            
                        DefaultTableModel model = (DefaultTableModel)UserAccountsTable.getModel();
                        model.removeRow(rowIndex);
                    }
                    catch(ClassNotFoundException | SQLException ex){
                         JOptionPane.showMessageDialog(null, "Error: "+ex);
                    }
                }
            }
        });
        
        // Conditioning of accounts
        if (acc.getText().equals("Staff"))
        {
            Account.setFont(new Font("Arial", Font.BOLD, 15));
            Account.setForeground(Color.WHITE);
            Account.setBounds(700,10,100,50);
            Account.setOpaque(false);
            User.setVisible(false);
            Users.setVisible(false);
            BuyRemove.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    JTextField usernameField = new JTextField();
                    JPasswordField passwordField = new JPasswordField();
                    panel.add(new JLabel("Enter Supervisor Credentials:"));
                    panel.add(usernameField);
                    panel.add(new JLabel("Supervisor Password:"));
                    panel.add(passwordField);

                    JLabel errorLabel = new JLabel("Incorrect Supervisor Credentials. Please try again.");
                    errorLabel.setForeground(Color.RED);
                    boolean showError = false;

                    while (true) {
                    if (showError) {
                        panel.add(errorLabel);
                    }
                    JOptionPane pane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    JDialog dialog = pane.createDialog(null, "Login");
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                    Object result = pane.getValue();
                    if (result == JOptionPane.UNINITIALIZED_VALUE || result.equals(JOptionPane.CANCEL_OPTION)) {
                    dialog.dispose();
                    break;
                    }
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    if (username.equals("Supervisor") && password.equals("123")) {
                        int stat=JOptionPane.showConfirmDialog(null,"Are you sure you want to remove the item?", "Remove Item",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (stat==0){
                            int rowIndex = BuyTable.getSelectedRow();

                            String value = BuyTable.getModel().getValueAt(rowIndex, 1).toString();

                            String query = "DELETE FROM buytable WHERE `buytable`.`BuyID` = '"+value+"'";
                            try{
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                                Statement pst = con.createStatement();
                                pst.executeUpdate(query);

                                DefaultTableModel model = (DefaultTableModel)BuyTable.getModel();
                                model.removeRow(rowIndex);
                            }
                            catch(ClassNotFoundException | SQLException ex){
                                JOptionPane.showMessageDialog(null, "Error: "+ex);
                            }
                        }
                        dialog.dispose();
                        break;
                        } else {
                        if (!showError) {
                            showError = true;
                        }
                        usernameField.setText("");
                        passwordField.setText("");
                        }
                    }
                }
            });
        }
        else
        {
        Account.setFont(new Font("Arial", Font.BOLD, 15));
        Account.setForeground(Color.WHITE);
        Account.setBounds(700,10,100,50);
        Account.setOpaque(false);
        }
        
        Inventory.setBounds(30,15,50,50);
        Inventory.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddNewItem.setVisible(true);
                TypeOfMaterial.setVisible(true);
                Category.setVisible(true);
                Search.setVisible(true);
                SearchBar.setVisible(true);
                ItemDescription.setVisible(true);
                ProductCode.setVisible(true);
                ProductCodeBar.setVisible(true);
                ItemDescriptionBar.setVisible(true);
                StockToAdd.setVisible(true);
                StockToAddBar.setVisible(true);
                Update.setVisible(true);
                RemoveItem.setVisible(true);
                Lamisa.setVisible(true);
                MainLamisa.setVisible(true);
                TransacLogTable.setVisible(false);
                TransacTotalSales.setVisible(false);
                TransacTotalSalesBar.setVisible(false);
                BuyFillUpBar.setVisible(false);
                BuyProductCode.setVisible(false);
                BuyProductCodeBar.setVisible(false);
                BuyItemDescription.setVisible(false);
                BuyItemDescriptionBar.setVisible(false);
                BTable.setVisible(false);
                RecNo.setVisible(false);
                RecNoBar.setVisible(false);
                BuyPrice.setVisible(false);
                BuyPriceBar.setVisible(false);
                BuyQuantity.setVisible(false);
                BuyQuantityBar.setVisible(false);
                BuyCustomersName.setVisible(false);
                BuyCustomersNameBar.setVisible(false);
                BuyDate.setVisible(false);
                BuyDateBar.setVisible(false);
                BuyAdd.setVisible(false);
                BuyClear.setVisible(false);
                BuyCalculate.setVisible(false);
                BuyRemove.setVisible(false);
                UserAndAccountsTable.setVisible(false);
                UserUsername.setVisible(false);
                UserPassword.setVisible(false);
                UserAccountType.setVisible(false);
                UserUsernameBar.setVisible(false);
                UserPasswordBar.setVisible(false);
                UserAccountTypeBar.setVisible(false);
                UserNew.setVisible(false);
                UserDelete.setVisible(false);
            }
        });
        
        Transac.setBounds(110,15,50,50);
        Transac.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == Transac){
                    TransacLogTable.setBounds(30,160,830,320);
                }
                AddNewItem.setVisible(false);
                TypeOfMaterial.setVisible(false);
                Category.setVisible(false);
                Search.setVisible(true);
                SearchBar.setVisible(true);
                ItemDescription.setVisible(false);
                ProductCode.setVisible(false);
                ProductCodeBar.setVisible(false);
                ItemDescriptionBar.setVisible(false);
                StockToAdd.setVisible(false);
                StockToAddBar.setVisible(false);
                Update.setVisible(false);
                RemoveItem.setVisible(false);
                Lamisa.setVisible(false);
                MainLamisa.setVisible(false);
                TransacLogTable.setVisible(true);
                TransacTotalSales.setVisible(true);
                TransacTotalSalesBar.setVisible(true);
                BTable.setVisible(false);
                BuyFillUpBar.setVisible(false);
                BuyProductCode.setVisible(false);
                BuyProductCodeBar.setVisible(false);
                BuyItemDescription.setVisible(false);
                BuyItemDescriptionBar.setVisible(false);
                RecNo.setVisible(false);
                RecNoBar.setVisible(false);
                BuyPrice.setVisible(false);
                BuyPriceBar.setVisible(false);
                BuyQuantity.setVisible(false);
                BuyQuantityBar.setVisible(false);
                BuyCustomersName.setVisible(false);
                BuyCustomersNameBar.setVisible(false);
                BuyDate.setVisible(false);
                BuyDateBar.setVisible(false);
                BuyAdd.setVisible(false);
                BuyClear.setVisible(false);
                BuyCalculate.setVisible(false);
                BuyRemove.setVisible(false);
                UserAndAccountsTable.setVisible(false);
                UserUsername.setVisible(false);
                UserPassword.setVisible(false);
                UserAccountType.setVisible(false);
                UserUsernameBar.setVisible(false);
                UserPasswordBar.setVisible(false);
                UserAccountTypeBar.setVisible(false);
                UserNew.setVisible(false);
                UserDelete.setVisible(false);
            }
        });
        
        Buy.setBounds(190,15,50,50);
        Buy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == Buy){
                    BTable.setBounds(380,130,470,350);
                }
                AddNewItem.setVisible(false);
                TypeOfMaterial.setVisible(false);
                Category.setVisible(false);
                Search.setVisible(false);
                SearchBar.setVisible(false);
                ItemDescription.setVisible(false);
                ProductCode.setVisible(false);
                ProductCodeBar.setVisible(false);
                ItemDescriptionBar.setVisible(false);
                StockToAdd.setVisible(false);
                StockToAddBar.setVisible(false);
                Update.setVisible(false);
                RemoveItem.setVisible(false);
                Lamisa.setVisible(false);
                MainLamisa.setVisible(false);
                TransacLogTable.setVisible(false);
                TransacTotalSales.setVisible(false);
                TransacTotalSalesBar.setVisible(false);
                BuyFillUpBar.setVisible(true);
                BuyProductCode.setVisible(true);
                BuyProductCodeBar.setVisible(true);
                BuyItemDescription.setVisible(true);
                BuyItemDescriptionBar.setVisible(true);
                RecNo.setVisible(true);
                RecNoBar.setVisible(true);
                BuyPrice.setVisible(true);
                BuyPriceBar.setVisible(true);
                BTable.setVisible(true);
                BuyQuantity.setVisible(true);
                BuyQuantityBar.setVisible(true);
                BuyCustomersName.setVisible(true);
                BuyCustomersNameBar.setVisible(true);
                BuyDate.setVisible(true);
                BuyDateBar.setVisible(true);
                BuyAdd.setVisible(true);
                BuyClear.setVisible(true);
                BuyCalculate.setVisible(true);
                BuyRemove.setVisible(true);
                UserAndAccountsTable.setVisible(false);
                UserUsername.setVisible(false);
                UserPassword.setVisible(false);
                UserAccountType.setVisible(false);
                UserUsernameBar.setVisible(false);
                UserPasswordBar.setVisible(false);
                UserAccountTypeBar.setVisible(false);
                UserNew.setVisible(false);
                UserDelete.setVisible(false);
            }
        });
        
        
        User.setBounds(270,15,50,50);
        User.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddNewItem.setVisible(false);
                TypeOfMaterial.setVisible(false);
                Category.setVisible(false);
                Search.setVisible(false);
                SearchBar.setVisible(false);
                ItemDescription.setVisible(false);
                ProductCode.setVisible(false);
                ProductCodeBar.setVisible(false);
                ItemDescriptionBar.setVisible(false);
                StockToAdd.setVisible(false);
                StockToAddBar.setVisible(false);
                Update.setVisible(false);
                RemoveItem.setVisible(false);
                Lamisa.setVisible(false);
                MainLamisa.setVisible(false);
                TransacLogTable.setVisible(false);
                TransacTotalSales.setVisible(false);
                TransacTotalSalesBar.setVisible(false);
                BTable.setVisible(false);
                BuyFillUpBar.setVisible(false);
                BuyProductCode.setVisible(false);
                BuyProductCodeBar.setVisible(false);
                BuyItemDescription.setVisible(false);
                BuyItemDescriptionBar.setVisible(false);
                RecNo.setVisible(false);
                RecNoBar.setVisible(false);
                BuyPrice.setVisible(false);
                BuyPriceBar.setVisible(false);
                BuyQuantity.setVisible(false);
                BuyQuantityBar.setVisible(false);
                BuyCustomersName.setVisible(false);
                BuyCustomersNameBar.setVisible(false);
                BuyDate.setVisible(false);
                BuyDateBar.setVisible(false);
                BuyAdd.setVisible(false);
                BuyClear.setVisible(false);
                BuyCalculate.setVisible(false);
                BuyRemove.setVisible(false);
                UserAndAccountsTable.setVisible(true);
                UserUsername.setVisible(true);
                UserPassword.setVisible(true);
                UserAccountType.setVisible(true);
                UserUsernameBar.setVisible(true);
                UserPasswordBar.setVisible(true);
                UserAccountTypeBar.setVisible(true);
                UserNew.setVisible(true);
                UserDelete.setVisible(true);
            }
        });
        
        
        
        panel.add(Inven); 
        panel.add(Trans);
        panel.add(Buys);
        panel.add(Users);
        panel.add(OutLog);
        panel.add(Account);
        panel.add(Inventory);
        panel.add(Transac);
        panel.add(Buy);
        panel.add(User);
        panel.add(LogOut);
        panel.add(AddNewItem);
        panel.add(TypeOfMaterial);
        panel.add(Category);
        panel.add(Search);
        panel.add(SearchBar);
        panel.add(ProductCode);
        panel.add(ProductCodeBar);
        panel.add(ItemDescription);
        panel.add(ItemDescriptionBar);
        panel.add(StockToAdd);
        panel.add(StockToAddBar);
        panel.add(Update);
        panel.add(RemoveItem);
        panel.add(green);
        panel.add(MainLamisa);
        
        panel.add(TransacLogTable);
        panel.add(TransacTotalSales);
        panel.add(TransacTotalSalesBar);
        
        panel.add(BTable);
        panel.add(BuyFillUpBar);
        panel.add(BuyProductCode);
        panel.add(BuyProductCodeBar);
        panel.add(BuyItemDescription);
        panel.add(BuyItemDescriptionBar);
        panel.add(BuyPrice);
        panel.add(BuyPriceBar);
        panel.add(RecNo);
        panel.add(RecNoBar);
        panel.add(BuyQuantity);
        panel.add(BuyQuantityBar);
        panel.add(BuyCustomersName);
        panel.add(BuyCustomersNameBar);
        panel.add(BuyDate);
        panel.add(BuyDateBar);
        panel.add(BuyAdd);
        panel.add(BuyClear);
        panel.add(BuyCalculate);
        panel.add(BuyRemove);
        
        panel.add(UserAndAccountsTable);
        panel.add(UserUsername);
        panel.add(UserPassword);
        panel.add(UserAccountType);
        panel.add(UserUsernameBar);
        panel.add(UserPasswordBar);
        panel.add(UserAccountTypeBar);
        panel.add(UserNew);
        panel.add(UserDelete);        
        
        root.revalidate();
        root.repaint();
        
        root.add(panel);
        root.setTitle("3E'S IMS");
        
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(900, 600);
        root.setLocationRelativeTo(null);
        root.setResizable(false);
        root.setVisible(true);
    }
}
