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
        String productcode = tf[0].getText();
        String itemdescription = tf[1].getText();
        int updateV = Integer.valueOf(tf[2].getText());
        int cc =  Integer.valueOf("" + vals[0][3].toString());
        
        if (!productcode.equals("") && !itemdescription.equals("")) {
            try { 
                String query = "update sql12600942.inventory set Quantity = '" + (cc + updateV) + "', Total = '" + ((float) updateV * Float.parseFloat(vals[0][4].toString())) + "' where ProductCode = '" + productcode + "';";
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println(query);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                Statement pst = con.createStatement();
                pst.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Stock Updated!!!");
            table.setModel(
            new DefaultTableModel(getTable(), new String[]{"Product Code", "Category", "Item Description", "Quantity", "Price", "Total"})
        );
                con.close();
                for (JTextField tff : tf ) {
                    tff.setText("");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        }
    
    public void deletes(Object[][] vals, JTable table, JTextField... tf) {
        String ProdCode = tf[0].getText();
        
        
        if (!ProdCode.equals("")) {
            try { 
                String query = "DELETE FROM inventory WHERE `inventory`.`ProductCode` = ProdCode";
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println(query);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql12600942","root","");
                Statement pst = con.createStatement();
                pst.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Stock Deleted!!!");
                table.setModel(
            new DefaultTableModel(getTable(), new String[]{"Product Code", "Category", "Item Description", "Quantity", "Price", "Total"})
        );
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
                    arry.add(rs.getString("Category"));
                    arry.add(rs.getString("Item Description"));
                    arry.add(rs.getInt("Quantity"));
                    arry.add(rs.getBigDecimal("Price"));
                    arry.add(rs.getBigDecimal("Total"));
                    
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
        String query = "select * from sql12600942.inventory where Category = '" + cell + "';";
        
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
                    arry.add(rs.getString("Category"));
                    arry.add(rs.getString("Item Description"));
                    arry.add(rs.getInt("Quantity"));
                    arry.add(rs.getBigDecimal("Price"));
                    arry.add(rs.getBigDecimal("Total"));
                    
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
                    arry.add(rs.getString("Category"));
                    arry.add(rs.getString("Item Description"));
                    arry.add(rs.getInt("Quantity"));
                    arry.add(rs.getBigDecimal("Price"));
                    arry.add(rs.getBigDecimal("Total"));
                    
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
            if(table.getValueAt(i, column).equals(value)){
                return i;
            }
        }
        return -1; // If the value is not found in any row
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
        
        if (acc.getText().equals("Merchandiser"))
        {
        Account.setFont(new Font("Arial", Font.BOLD, 12));
        Account.setForeground(Color.WHITE);
        Account.setBounds(700,10,100,50);
        Account.setOpaque(false);
        User.setVisible(false);
        Users.setVisible(false);
        }
        else
        {
        Account.setFont(new Font("Arial", Font.BOLD, 15));
        Account.setForeground(Color.WHITE);
        Account.setBounds(700,10,100,50);
        Account.setOpaque(false);
        }
        
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
        panel_.add(new JLabel(" Category"));
        panel_.add(AddCategoryBar);
        panel_.add(new JLabel("Item Description"));
        panel_.add(AddItemDescriptionBar);
        panel_.add(new JLabel("Quantity"));
        panel_.add(AddQuantityBar);
        panel_.add(new JLabel("Price"));
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
        Lamisa.setModel(new DefaultTableModel(getTable(), new String[]{"Product Code", "Category", "Item Description", "Quantity", "Price", "Total"}));
        Lamisa.getColumnModel().getColumn(2).setPreferredWidth(220);
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
        
            
            
        ProductCode.setFont(new Font("Arial", Font.BOLD, 12));
        ProductCode.setForeground(Color.BLACK);
        ProductCode.setBounds(180,430,150,25);
        ProductCode.setOpaque(false);
        ProductCodeBar.setBounds(265,430,150,25);
        ProductCodeBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ProductCodeBar.setOpaque(false);
        
        JMenuItem all_ = new JMenuItem("All");
        JMenuItem Adapter = new JMenuItem("Adapter");
        JMenuItem Cement = new JMenuItem("Cement");
        JMenuItem Bar = new JMenuItem("Bar");
        JComboBox Category = new JComboBox(new String[]{"All", "Adapter", "Bar", "Cement", "Coupling", "Hardiflex", "Pipe", "Plywood", "Sheet"});
        Category.setFont(new Font("Arial", Font.BOLD, 12));
        Category.setBounds(520, 120, 120, 25);
        Category.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                String cats = (String) Category.getItemAt(Category.getSelectedIndex());
                if (cats.equals("All")) {
                Lamisa.setModel(new DefaultTableModel(getTable(), new String[]{"Product Code", "Category", "Item Description", "Quantity", "Price", "Total"}));   
                } else {
                Lamisa.setModel(new DefaultTableModel(getTable(cats), new String[]{"Product Code", "Category", "Item Description", "Quantity", "Price", "Total"}));
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
            
            private void SearchBarKeyReleased(java.awt.event.KeyEvent evt){
                DefaultTableModel model = (DefaultTableModel)Lamisa.getModel();
                String search = SearchBar.getText();
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
                Lamisa.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(search));
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
        StockToAdd.setBounds(185,510,150,25);
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
        TransactionLogTable.setModel(new DefaultTableModel(getTable(), new String[]{"Transaction Code","Product Code", "Category", "Item Description", "Quantity", "Price", "Total"}));
        TransactionLogTable.getColumnModel().getColumn(2).setPreferredWidth(220);
        TransactionLogTable.setVisible(false);
        JScrollPane TransacLogTable = new JScrollPane(TransactionLogTable); 
        TransacLogTable.setVisible(false);
        
        // Buy Section
        JLabel BuyProductCode = new JLabel("Product Code");
        JLabel BuyItemDescription = new JLabel("Item Description");
        JLabel BuyStock = new JLabel("Stock");
        JLabel BuyPrice = new JLabel("Price");
        JLabel BuyQuantity = new JLabel("Quantity");
        
        JTextField BuyFillUpBar = new JTextField();
        JTextField BuyProductCodeBar = new JTextField();
        JTextField BuyItemDescriptionBar = new JTextField();
        JTextField BuyStockBar = new JTextField();
        JTextField BuyPriceBar = new JTextField();
        JTextField BuyQuantityBar = new JTextField();
        
        JButton BuyAdd = new JButton("Add");
        JButton BuyCalculate = new JButton("Calculate");
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
        
        BuyStock.setFont(new Font("Arial", Font.BOLD, 12));
        BuyStock.setForeground(Color.BLACK);
        BuyStock.setBounds(153,265,150,25);
        BuyStock.setOpaque(false);
        BuyStock.setVisible(false);
        BuyStockBar.setBounds(195,265,160,25);
        BuyStockBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyStockBar.setOpaque(false);
        BuyStockBar.setVisible(false);
        
        BuyPrice.setFont(new Font("Arial", Font.BOLD, 12));
        BuyPrice.setForeground(Color.BLACK);
        BuyPrice.setBounds(156,300,150,25);
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
                    BuyStockBar.setText(String.valueOf(Lamisa.getValueAt(i_, 3)));
                    BuyPriceBar.setText(String.valueOf(Lamisa.getValueAt(i_, 4)));
                } else {
                    BuyProductCodeBar.setText("");
                    BuyItemDescriptionBar.setText("");
                    BuyStockBar.setText("");
                    BuyPriceBar.setText("");
                }
            }
            
            public void removeUpdate(DocumentEvent e) {
               insertUpdate(e);
            }
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        BuyQuantity.setFont(new Font("Arial", Font.BOLD, 12));
        BuyQuantity.setForeground(Color.BLACK);
        BuyQuantity.setBounds(138,335,150,25);
        BuyQuantity.setOpaque(false);
        BuyQuantity.setVisible(false);
        BuyQuantityBar.setBounds(195,335,160,25);
        BuyQuantityBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyQuantityBar.setOpaque(false);
        BuyQuantityBar.setVisible(false);
        
        BuyAdd.setBounds(70, 380, 80, 40);
        BuyAdd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyAdd.setContentAreaFilled(false);
        BuyAdd.setOpaque(false);
        BuyAdd.setVisible(false);
        
        BuyCalculate.setBounds(173, 380, 80, 40);
        BuyCalculate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyCalculate.setContentAreaFilled(false);
        BuyCalculate.setOpaque(false);
        BuyCalculate.setVisible(false);
        
        BuyRemove.setBounds(275, 380, 80, 40);
        BuyRemove.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        BuyRemove.setContentAreaFilled(false);
        BuyRemove.setOpaque(false);
        BuyRemove.setVisible(false);
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
                int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                        if (username.equals("Supervisor") && password.equals("123")) {
                        JOptionPane.showOptionDialog(null, "Login successful!", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                        } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Supervisor Credentials. Please try again.");
                    }
            }
    }
});
        
         // Users Account Section
        JTable UserAccountsTable = new JTable();
        UserAccountsTable.setModel(new DefaultTableModel(getTable(), new String[]{"Username","Passwd","AccType"}));
        UserAccountsTable.getColumnModel().getColumn(2).setPreferredWidth(220);
        UserAccountsTable.setVisible(false);
        JScrollPane UserAndAccountsTable = new JScrollPane(UserAccountsTable);
        UserAndAccountsTable.setBounds(400,130,440,250);
        UserAndAccountsTable.setVisible(false);
         
        
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
                TransactionLogTable.setVisible(false);
                TransacLogTable.setVisible(false);
                BuyFillUpBar.setVisible(false);
                BuyProductCode.setVisible(false);
                BuyProductCodeBar.setVisible(false);
                BuyItemDescription.setVisible(false);
                BuyItemDescriptionBar.setVisible(false);
                BuyStock.setVisible(false);
                BuyStockBar.setVisible(false);
                BuyPrice.setVisible(false);
                BuyPriceBar.setVisible(false);
                BuyQuantity.setVisible(false);
                BuyQuantityBar.setVisible(false);
                BuyAdd.setVisible(false);
                BuyCalculate.setVisible(false);
                BuyRemove.setVisible(false);
                UserAccountsTable.setVisible(false);
                UserAndAccountsTable.setVisible(false);
            }
        });
        
        Transac.setBounds(110,15,50,50);
        Transac.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == Transac){
                    TransacLogTable.setBounds(30,130,830,350);
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
                TransactionLogTable.setVisible(true);
                TransacLogTable.setVisible(true);
                BuyFillUpBar.setVisible(false);
                BuyProductCode.setVisible(false);
                BuyProductCodeBar.setVisible(false);
                BuyItemDescription.setVisible(false);
                BuyItemDescriptionBar.setVisible(false);
                BuyStock.setVisible(false);
                BuyStockBar.setVisible(false);
                BuyPrice.setVisible(false);
                BuyPriceBar.setVisible(false);
                BuyQuantity.setVisible(false);
                BuyQuantityBar.setVisible(false);
                BuyAdd.setVisible(false);
                BuyCalculate.setVisible(false);
                BuyRemove.setVisible(false);
                UserAccountsTable.setVisible(false);
                UserAndAccountsTable.setVisible(false);
            }
        });
        
        Buy.setBounds(190,15,50,50);
        Buy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == Buy){
                    TransacLogTable.setBounds(400,130,440,370);
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
                TransactionLogTable.setVisible(true);
                TransacLogTable.setVisible(true);
                BuyFillUpBar.setVisible(true);
                BuyProductCode.setVisible(true);
                BuyProductCodeBar.setVisible(true);
                BuyItemDescription.setVisible(true);
                BuyItemDescriptionBar.setVisible(true);
                BuyStock.setVisible(true);
                BuyStockBar.setVisible(true);
                BuyPrice.setVisible(true);
                BuyPriceBar.setVisible(true);
                BuyQuantity.setVisible(true);
                BuyQuantityBar.setVisible(true);
                BuyAdd.setVisible(true);
                BuyCalculate.setVisible(true);
                BuyRemove.setVisible(true);
                UserAccountsTable.setVisible(false);
                UserAndAccountsTable.setVisible(false);
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
                TransactionLogTable.setVisible(false);
                TransacLogTable.setVisible(false);
                BuyFillUpBar.setVisible(false);
                BuyProductCode.setVisible(false);
                BuyProductCodeBar.setVisible(false);
                BuyItemDescription.setVisible(false);
                BuyItemDescriptionBar.setVisible(false);
                BuyStock.setVisible(false);
                BuyStockBar.setVisible(false);
                BuyPrice.setVisible(false);
                BuyPriceBar.setVisible(false);
                BuyQuantity.setVisible(false);
                BuyQuantityBar.setVisible(false);
                BuyAdd.setVisible(false);
                BuyCalculate.setVisible(false);
                BuyRemove.setVisible(false);
                UserAccountsTable.setVisible(true);
                UserAndAccountsTable.setVisible(true);
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
        
        panel.add(TransactionLogTable);
        panel.add(TransacLogTable);
        
        panel.add(BuyFillUpBar);
        panel.add(BuyProductCode);
        panel.add(BuyProductCodeBar);
        panel.add(BuyItemDescription);
        panel.add(BuyItemDescriptionBar);
        panel.add(BuyPrice);
        panel.add(BuyPriceBar);
        panel.add(BuyStock);
        panel.add(BuyStockBar);
        panel.add(BuyQuantity);
        panel.add(BuyQuantityBar);
        panel.add(BuyAdd);
        panel.add(BuyCalculate);
        panel.add(BuyRemove);
        
        panel.add(UserAccountsTable);
        panel.add(UserAndAccountsTable);
        
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
