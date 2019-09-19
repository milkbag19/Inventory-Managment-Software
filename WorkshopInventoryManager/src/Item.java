
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;

public class Item extends Inventory_main{
  
  String code, desc;
  JLabel name;
  Icon image;
  int item_stock;
//  Files file = new Files(new File("Data.txt"));
  ArrayList<String> categories = new ArrayList<String>();
  FIO categoryData = new FIO(new File(userHomePath+"//CategoryData.txt"));
  JButton itemDescriptionButton = new JButton();
  JButton removeItemButton = new JButton();
  int cat, itm;
  JButton imageButton;
  JFrame quickUpdateFrame;
  JLabel stockText = new JLabel("Stock : ");
  
  public Item(String s) throws java.io.IOException {
    
    code = s;
    boolean b = false;
    for(int i = 0;i<categoryCodeList.size();i++){
      for(int j = 0;j<categoryCodeList.get(i).size();j++){
        if(code.toUpperCase().equals( categoryCodeList.get(i).get(j).toString().toUpperCase())){
          b = true;
          
          desc = categoryDescList.get(i).get(j).toString();
          itemDescriptionButton.setToolTipText(desc);
          itemDescriptionButton.setPreferredSize(new Dimension(25,15));
          itemDescriptionButton.setBorderPainted(false);
          itemDescriptionButton.setFocusPainted(false);
          itemDescriptionButton.setContentAreaFilled(false);
          itemDescriptionButton.setBackground(Color.gray);
          cat = i;
          itm = j;
          
          removeItemButton.setBorderPainted(false);
          removeItemButton.setFocusPainted(false);
          removeItemButton.setContentAreaFilled(false);
          removeItemButton.setBackground(Color.gray);
          removeItemButton.setPreferredSize(new Dimension(25,25));
          removeItemButton.setToolTipText("Remove the selected item");
          removeItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
              try{
                categoryNameList.get(cat).remove(itm);
                categoryImageList.get(cat).remove(itm);
                categoryCodeList.get(cat).remove(itm);
                categoryDescList.get(cat).remove(itm);
                categoryStockList.get(cat).remove(itm);
                scrollBox.removeAll();
                filterMenu.setPreferredSize(new Dimension(30,30));
                mainbox.remove(scrollPane);
                frame.remove(mainbox);
                scrollBoxPanel.remove(scrollBox);
                setup(MAIN_PAGE);
                scrollBox.setMaximumSize(new Dimension(900,100));
                mainbox.add(JBox.vbox(scrollPane));
                scrollBoxPanel.add(scrollBox, BorderLayout.CENTER);
                scrollBoxPanel.setMaximumSize(new Dimension(8000,100));
                scrollPane = new JScrollPane(scrollBoxPanel);
                scrollPane.setMaximumSize(new Dimension(8000,8000));
                scrollPane.getVerticalScrollBar().setUnitIncrement(16);
                scrollPane.setBackground(new Color(54, 57, 62));
                mainbox = JBox.vbox(
                                    JBox.hbox(
                                              JBox.vbox(
                )
                                             ),
                                    JBox.hbox(
                                              
                                              scrollPane 
                                             )
                                   );
                mainbox.setBackground(new Color(54, 57, 62));
                frame.add(mainbox);
                frame.repaint();
                frame.setVisible(true);
              }catch(java.io.IOException e){}
            }
          });
          final ImageIcon hoverRemoveButton = new ImageIcon(ImageIO.read(new File(userHomePath+"\\Images\\hoverRemoveButton.png")).getScaledInstance(25, 25, Image.SCALE_DEFAULT));
          final ImageIcon defaultRemoveButton = new ImageIcon(ImageIO.read(new File(userHomePath+"\\Images\\RemoveButton.png")).getScaledInstance(25, 25, Image.SCALE_DEFAULT));
          removeItemButton.setIcon(defaultRemoveButton);
          itemDescriptionButton.setIcon(new ImageIcon(ImageIO.read(new File(userHomePath+"\\Images\\infoButton.png")).getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
          
          removeItemButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
              removeItemButton.setIcon(hoverRemoveButton);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
              removeItemButton.setIcon(defaultRemoveButton);
            }
          });
          
          String[] arr = categoryStockList.get(i).get(j).getText().split(" ");
          item_stock = Integer.parseInt(arr[2]);
      
          name = new JLabel(categoryNameList.get(i).get(j).toString());
          name.setBackground(new Color(187, 190, 196));
          name.setForeground(new Color(187, 190, 196));
          try{
             image = new ImageIcon(ImageIO.read(new File(userHomePath+categoryImageList.get(i).get(j))).getScaledInstance(75, 65, Image.SCALE_DEFAULT));
             imageButton = new JButton(image);
          }catch(Exception f){
             image = new ImageIcon(ImageIO.read(new File(categoryImageList.get(i).get(j))).getScaledInstance(75, 65, Image.SCALE_DEFAULT));
             imageButton = new JButton(image);
             
          }
          imageButton.setBorderPainted(false);
          imageButton.setFocusPainted(false);
          imageButton.setContentAreaFilled(false);
          imageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
              quickUpdateFrame = new JFrame();
              quickUpdateFrame.setSize(275,95);
              quickUpdateFrame.setLocationRelativeTo(null);
              quickUpdateFrame.setUndecorated(true);
              quickUpdateField = new JTextField("Stock change here");
              quickUpdateField.setBackground(new Color(54, 57, 62));
              quickUpdateField.setBounds(75,0,200,65);
              JLabel imageExample = new JLabel(image);
              quickUpdateField.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e){
                  if(quickUpdateField.getText().equals("Stock change here")||quickUpdateField.getText().equals("* * Number must be used * *")){
                    quickUpdateField.setText("");
                    quickUpdateField.setForeground(Color.black);
                  }
                  else{
                    Func.print("ya don goofed"); 
                  }
                }
                public void focusLost(FocusEvent e){
                  if(quickUpdateField.getText().equals(""))
                    quickUpdateField.setText("Stock change here");
                  else
                    Func.print("ya don goofed");
                }
              });
              imageExample.setBounds(0,0,75,65);
              quickUpdateFrame.add(imageExample);
              JButton quickSubmitButton = new JButton("Submit");
              quickSubmitButton.setBounds(137,65,75,30);
              quickUpdateFrame.add(quickSubmitButton);
              quickSubmitButton.requestFocusInWindow();
              
              quickSubmitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                  try{
                    quickUpdateFrame.setVisible(false);
                    String[] arr = categoryStockList.get(cat).get(itm).getText().split(" ");
                    int temp = Integer.parseInt(arr[2]);
                     temp += Integer.parseInt(quickUpdateField.getText());
                     categoryStockList.get(cat).get(itm).setText("Stock : "+String.valueOf(temp));
                     frame.repaint();
                     arr = categoryStockList.get(cat).get(itm).getText().split(" ");
                     if(Integer.parseInt(arr[2])<=2) {
                   	  categoryStockList.get(cat).get(itm).setForeground(Color.red);
                   	  stockText.setForeground(Color.red);
                     }
                     else {
                    	 categoryStockList.get(cat).get(itm).setForeground(new Color(187, 190, 196));
                      	  stockText.setForeground(new Color(187, 190, 196));
                     }
                     categoryStockList.get(cat).get(itm).repaint();
                     stockText.repaint();
                  }catch(NumberFormatException f){
                    quickUpdateField.setText("* * Number must be used * *");
                    quickUpdateField.setForeground(Color.red);
                  }
                  }});
              quickUpdateField.setForeground(new Color(187, 190, 196));
              quickUpdateField.setBorder(javax.swing.BorderFactory.
                                   createTitledBorder(null, getNameS(),+ javax.swing.border.
                                                         TitledBorder.CENTER, javax.swing.border.
                                                         TitledBorder.CENTER, new Font("Franklin Gothic Medium", Font.BOLD, 15), new Color(187, 190, 196)));
              quickUpdateFrame.add(quickUpdateField);
              quickUpdateFrame.setBackground(new Color(54, 57, 62));
              quickUpdateFrame.setLayout(null);
              quickUpdateFrame.setVisible(true);
            }
          });
        }
      }
    }
    
    if(!b){
      throw new RuntimeException();
    }
    
  }
  public Item(){
    for(int i = 1;;i++){
      if(categoryData.load(i).equals("-"))
        break;
      else
        categories.add(categoryData.load(i));
    }
  }
  public JButton getDescButton(){
    return itemDescriptionButton;
  }
  public ArrayList<String> getCategories(){
    return categories;
  }
  public String getDesc(){
    return desc; 
  }
  public JButton getRemoveButton(){
    return removeItemButton;
  }
  public JLabel getStock(){
    if(item_stock <2){
      JLabel label = new JLabel("Stock : "+item_stock);
      label.setBackground(Color.RED);
      label.setForeground(Color.RED);
      return label;
    }
    else{
      JLabel label = new JLabel("Stock : "+item_stock);
      label.setForeground(new Color(187, 190, 196));
      return label;
    }
  }
  public int getStockInt(){
    return item_stock;
    
  }
  public JLabel getName(){
    return name;
  }
  public String getCode(){
    return code; 
  }
  public JButton getImage(){
    return imageButton; 
  }
  public String getNameS(){
    return name.getText();
  }
  public JLabel getStockText() {
	  return stockText;
  
  }
}
