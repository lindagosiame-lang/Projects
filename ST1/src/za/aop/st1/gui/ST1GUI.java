package za.aop.st1.gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.aop.api.CallDetail;
import za.ac.aop.api.CallDetailManager; //JAR required

/**
 *
 * @author LANGARM
 */
public class ST1GUI extends JFrame{
    private JPanel tab1, tab2;
    private final JTabbedPane tabbedPane;
    
    // TAB 1 Thingz
    private JTextField tfSentence;
    private JButton btnReverse, btnMapWordLength;
    private JLabel lblReversedSentence, lblMapWordLength;
    
    // TAB 2 Thingz
    private JTextArea taContent;
    private JTextArea taSentence;
    
    private JComboBox<String> cmbExtensions;
    private JButton btnCountLongerCalls, btnAddRecord, btnCountDayCalls;
    private JTextField tfPhone, tfProvider;

    private CallDetailManager cdm; //JAR required

    public ST1GUI() {
        setTitle("Semester Test 1 - AOP/AOR216D");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open File");
        openItem.addActionListener(new MenuItemOpenClickListener());
        JMenuItem saveItem = new JMenuItem("Save Report");
        saveItem.addActionListener(new MenuItemSaveClickListener());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        tabbedPane = new JTabbedPane();
         
        Question1Tab(); //initializing tab1 in here
        
        Question2Tab(); //initializing tab2 in here

        // Add tabs
        tabbedPane.addTab("Q1", tab1);
        tabbedPane.addTab("Q2", tab2);
       
        add(tabbedPane);
        
        setVisible(true);
    }
    
    private void Question1Tab(){
        tab1 = new JPanel(new BorderLayout());
        JPanel upper = new JPanel(new GridLayout(5,1,0,5));
        upper.setBorder(javax.swing.BorderFactory.createTitledBorder("Question 1 - sentence manipulation"));
        
        tfSentence = new JTextField("A good programmer, is a lazy programmer!");
        tfSentence.setHorizontalAlignment(JTextField.CENTER);
        tfSentence.setCaretPosition(0);
        tfSentence.setEditable(false);
        tfSentence.setFont(new Font("Comic Sans", Font.BOLD, 15));
        
        
      
        JPanel mPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnReverse = new JButton("Reverse sentence");
        btnReverse.setBackground(Color.decode("#ccc5f8"));
        btnReverse.addActionListener(new BtnReverseClickListener());
        btnMapWordLength = new JButton("Word-Length Mapper");
        btnMapWordLength.setBackground(Color.decode("#ccc44f"));
        btnMapWordLength.addActionListener(new BtnMapWordLengthClickListener());
        
        mPnl.add(btnMapWordLength); mPnl.add(btnReverse); 
       
        lblReversedSentence = new JLabel("Display reversed sentence here");
        lblReversedSentence.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblReversedSentence.setHorizontalAlignment(JLabel.CENTER);
        
        lblMapWordLength = new JLabel("Display word-length here");
        lblMapWordLength.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblMapWordLength.setHorizontalAlignment(JLabel.CENTER);
       
        
       
        
        
        
       
        upper.add(tfSentence);
        upper.add(mPnl);
        upper.add(lblMapWordLength);
        upper.add(lblReversedSentence);
        
        
        JButton btnNextQuestion = new JButton("Go to next question!");
        btnNextQuestion.addActionListener((e) ->{ tabbedPane.setSelectedIndex(1); });
        
        tab1.add(upper, BorderLayout.NORTH);
        tab1.add(btnNextQuestion, BorderLayout.SOUTH);
    }

  

    //TOTAL [10]
    private class BtnMapWordLengthClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
              String sentence = tfSentence.getText();
              int count=0;
              String trimmed = " ";
             String wordStore = " ";
            String s = " ";
            for(int i =0;i<sentence.length();i++){
                char c = sentence.charAt(i);
                
                if(c!=' '&&c!=','&&c!='!'){
                    s+= c;
                    
                    
                }
                else{
                   s+=" ";
                
                }
                  
                }
            trimmed= s.replaceAll("\\s+"," ");
                   String[] words = trimmed.split(" ");
                     
                     String result = "";
                     String empty = " ";
                 for(int j = 0; j<words.length;j++){
                    if(!words[j].isEmpty()){
                        result += words[j]+words[j].length()+" ";
                    }
    }
              lblMapWordLength.setForeground(Color.magenta);
                 lblMapWordLength.setText(result);
           
    }
    }
    //TOTAL [5]
    private class BtnReverseClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String sentence = tfSentence.getText();
             
              
            String s = " ";
            for(int i =0;i<sentence.length();i++){
                char c = sentence.charAt(i);
                s=c+s;
                }
                  lblReversedSentence.setText(s);
                }
    }
    
    private void Question2Tab() {
        tab2 = new JPanel(new BorderLayout());
        taContent = new JTextArea(10, 30);
        taContent.setFont(new Font("Arial", Font.BOLD, 12));
        taContent.setEditable(false);
        taContent.setTabSize(3);
        JScrollPane scrollPane = new JScrollPane(taContent);
        
        JPanel topPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPnl.setBorder(BorderFactory.createTitledBorder("New Service Detail"));
        
        cmbExtensions = new JComboBox(
        new String[]{"200", "202", "205", "206", "207", "208", "210", "211",
            "212", "213", "214", "216", "218", "219", "220"});
        
        tfPhone = new JTextField(8);
        tfProvider = new JTextField(8);
        
        btnAddRecord = new JButton("Add Record");
        btnAddRecord.setEnabled(false);
        btnAddRecord.addActionListener(new BtnAddRecordClickListener());
        
        topPnl.add(new JLabel("Ext")); topPnl.add(cmbExtensions);
        topPnl.add(new JLabel("Number")); topPnl.add(tfPhone);
        topPnl.add(new JLabel("Provider")); topPnl.add(tfProvider);
        topPnl.add(btnAddRecord);
        
        JPanel bottomPnl = new JPanel();
        bottomPnl.setBorder(BorderFactory.createTitledBorder("Functionality Processing"));
        
        btnCountLongerCalls = new JButton("Count Calls > 2 Mins");
        btnCountLongerCalls.addActionListener(new BtnCountLongCallsClickListener());
        btnCountLongerCalls.setEnabled(false);
        
        btnCountDayCalls = new JButton("Count Day with Most Calls");
        btnCountDayCalls.addActionListener(new BtnCountMostDayCallsClickListener());
        btnCountDayCalls.setEnabled(false);
        
        bottomPnl.add(btnCountLongerCalls);
        bottomPnl.add(btnCountDayCalls);
        
        tab2.add(topPnl, BorderLayout.NORTH);
        tab2.add(scrollPane, BorderLayout.CENTER);
        tab2.add(bottomPnl, BorderLayout.SOUTH);
        
        cdm = new CallDetailManager();
    }
    
    // TOTAL [11]
    private class MenuItemOpenClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        
        JFileChooser jf = new JFileChooser();
        int dialog = jf.showOpenDialog(null);
        File file = jf.getSelectedFile();
         
        if(dialog==JFileChooser.APPROVE_OPTION){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line =null;
                //DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                
                while((line= br.readLine())!= null){
                  String[] fields = line.split(",");
                  LocalDate callDate = LocalDate.parse(fields[0].trim(),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                  LocalTime startTime = LocalTime.parse(fields[1].trim(),DateTimeFormatter.ofPattern("HH:mm:ss"));
                  LocalTime endTime = LocalTime.parse(fields[2].trim(),DateTimeFormatter.ofPattern("HH:mm:ss"));
                  Long extension = Long.parseLong(fields[3].trim());
                  String phoneNumber = fields[4].trim();
                  String provider = fields[5].trim();
                  
                CallDetail cd = new CallDetail(callDate, startTime, endTime, extension, phoneNumber, provider);
                cdm.addCallDetail(cd);
        } 
                taContent.setText("");
                for(CallDetail calls : cdm.getCalls()){
                     taContent.append(calls.getCallDate()+"\t"+calls.getStartTime()+
                             "\t"+calls.getEndTime()+"\t"+calls.getExtension()+"\t"+calls.getPhoneNumber()
                             +"\t"+calls.getNetworkProvider()+"\n");
                     }
                 
                
             
               btnAddRecord.setEnabled(true);
               btnCountLongerCalls.setEnabled(true);
               btnCountDayCalls.setEnabled(true);
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ST1GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ST1GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
        }
    }
    
    // TOTAL [14]
    private class BtnAddRecordClickListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedReader br = null;
            
                long extension = Long.parseLong(cmbExtensions.getSelectedItem().toString());
                String phoneNum = tfPhone.getText();
                String provider = tfProvider.getText();
                int digits = phoneNum.length();
                if(digits!=10){
                    JOptionPane.showMessageDialog(taContent,"Phone number length invalid ");
                }
                if(!(provider.equalsIgnoreCase("Vodacom")||provider.equalsIgnoreCase("MTN")||
                        provider.equalsIgnoreCase("Cell C")||provider.equalsIgnoreCase("Telkom"))){
                    JOptionPane.showMessageDialog(taContent,"Provider invalid ");
                } 
                
                LocalDate callDate = LocalDate.now();
              LocalTime startTime = LocalTime.now();
              LocalTime endTime = LocalTime.from(startTime).plusMinutes(3).plusSeconds(24);
            
              CallDetail newRecord = new CallDetail(callDate,startTime,endTime,extension,phoneNum,provider);
               cdm.addCallDetail(newRecord);
               
               taContent.append(newRecord.getCallDate().toString()+"\t"+
                       newRecord.getStartTime()+"\t"+newRecord.getEndTime()+"\t"+newRecord.getExtension()
                        +"\t"+newRecord.getPhoneNumber()+"\t"+newRecord.getNetworkProvider()+"\n");
               
                    
            
                
            tfPhone.setText("");
            tfProvider.setText("");
            cmbExtensions.setSelectedIndex(0);
            
            JOptionPane.showMessageDialog(null,"Record added");
            
               }
            }
    
    private class BtnCountLongCallsClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            BufferedReader rd = null;
            try {
                File file = new File("November.dat");
                rd = new BufferedReader(new FileReader(file));
                int count = 0;
                String line = null;
                
                while((line=rd.readLine())!=null){
                   String[] fields = line.split(",");
                   LocalTime startTime = LocalTime.parse(fields[1]);
                   LocalTime endTime = LocalTime.parse(fields[2]);
                   
                   long durationMinutes = java.time.Duration.between(startTime, endTime).toMinutes();
                   
                  if(durationMinutes>(int)2){
                     count++; 
                  }
                   
                }
                JOptionPane.showMessageDialog(null,count+" "
                        + "calls were longer than 2 minutes");
                      
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ST1GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ST1GUI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    rd.close();
                } catch (IOException ex) {
                    Logger.getLogger(ST1GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
    }
    
    // TOTAL [11]
    private class BtnCountMostDayCallsClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          
            
            LocalDate currentDate = LocalDate.MIN;
            int count = 1, highCount = 0;
            LocalDate highDate = LocalDate.MIN;
            
            for(CallDetail call : cdm.getCalls()){
               if(!(call.getCallDate().equals(currentDate))){
                  currentDate = call.getCallDate();
                  
                  if(count > highCount){
                      highCount = count ;
                      highDate = currentDate;
                  }
                  count = 1;
               }
               else{
                   count++;
               }
}
                JOptionPane.showMessageDialog(null,highCount+" were the most recorded calls on "
+highDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

        }}
    // TOTAL [8]
    private class MenuItemSaveClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                BufferedWriter br = null;
                
                String directory = System.getProperty("user.dir");
                
                JFileChooser jf = new JFileChooser(directory);
                
                File file = new File("Call Report.txt");
                file.createNewFile();
              
                int dialog = jf.showSaveDialog(null);
                
                br = new BufferedWriter(new FileWriter(file));
                
                
                LocalDate callDate;
        
                for(CallDetail call : cdm.getCalls()){
                    
                  br.write(call.getCallDate().toString());
                    br.write(call.getStartTime().toString());
                 
                    br.write(call.getEndTime().toString());
                    br.write(Long.toString(call.getExtension()));
                    br.write(call.getPhoneNumber());
                    br.write(call.getNetworkProvider());
               
                }  br.close();
            } catch (IOException ex) {
                Logger.getLogger(ST1GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new ST1GUI());
    }
}




    
