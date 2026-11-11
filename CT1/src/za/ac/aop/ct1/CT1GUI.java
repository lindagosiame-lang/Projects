package za.ac.aop.ct1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import za.ac.aop.manager.CDRManager;
import za.ac.aop.model.CallDetailRecord;

/**
 *
 * @author LANGARM
 */
public class CT1GUI extends JFrame{
    private JPanel tab1, tab2;
    
    // TAB 1 Thingz
    private JTextField tfSentence;
    private JButton btnCountWords, btnSwapWordCase, btnCountLetter;
    private JTextArea taSentenceOutput;
    
    // TAB 2 Thingz
    private JLabel lblFileStatus;
    private JButton btnDisplay;
    private JTextArea taContent;
    
    private CDRManager mgr;

    public CT1GUI() {
        setTitle("Class Test 1 - AOP/AOR216D");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open File");
        openItem.addActionListener(new MenuItemOpenClickListener());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new MenuItemExitClickListener());

        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        Question1Tab(); //initializing tab1 in here
        
        Question2Tab(); //initializing tab2 in here

        // Add tabs
        tabbedPane.addTab("Q1", tab1);
        tabbedPane.addTab("Q2", tab2);

        add(tabbedPane);

        setVisible(true);
    }

    private void Question1Tab() {
        tab1 = new JPanel(new BorderLayout());
        
        JPanel upper = new JPanel(new GridLayout(2,1,5,5));
        upper.setBorder(javax.swing.BorderFactory.createTitledBorder("Question 1 - sentence manipulation"));
        
        JPanel mPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tfSentence = new JTextField("TalK is chEAp, SHOW me tHe Code");
        tfSentence.setEditable(false);
        tfSentence.setFont(new Font("Comic Sans", Font.BOLD, 15));
        mPnl.add(tfSentence);
        
        upper.add(mPnl);
        
        btnCountWords = new JButton("Word Count");
        btnCountWords.setBackground(Color.decode("#bd6b5b"));
        btnCountWords.addActionListener(new BtnCountWordsClickListener());
        btnSwapWordCase = new JButton("Swap Word Case");
        btnSwapWordCase.addActionListener(new BtnSwapCaseClickListener());
        btnSwapWordCase.setBackground(Color.decode("#85bd5b"));
        btnCountLetter = new JButton("Count Letter E");
        btnCountLetter.setBackground(Color.decode("#5baebd"));
        btnCountLetter.addActionListener(new BtnCountLetterClickListener());
        
        mPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mPnl.add(btnCountWords); mPnl.add(btnSwapWordCase); mPnl.add(btnCountLetter);
        
        upper.add(mPnl);
        
        taSentenceOutput = new JTextArea(15, 15);
        taSentenceOutput.setFont(new Font("Comic Sans", Font.PLAIN, 14));
        taSentenceOutput.setEditable(false);
        
        tab1.add(upper, BorderLayout.NORTH);
        tab1.add(new JScrollPane(taSentenceOutput));
    }
    
    //TOTAL MARKS = 4
    private class BtnCountWordsClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //WRITE CODE HERE
            
        }
        
    }
    
    //TOTAL MARKS = 6
    private class BtnSwapCaseClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //WRITE CODE HERE
            
        }
    }
    
    //TOTAL MARKS = 5
    private class BtnCountLetterClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //WRITE CODE HERE
            
            
        }
        
    }
    
    //TAB 2 => Complete the following Event Listeners
    private void Question2Tab() {
        tab2 = new JPanel(new BorderLayout());
        taContent = new JTextArea(10, 30);
        taContent.setFont(new Font("Arial", Font.BOLD, 12));
        taContent.setEditable(false);
        taContent.setTabSize(4);
        taContent.setCaretColor(Color.white);
        JScrollPane scrollPane = new JScrollPane(taContent);
        
        lblFileStatus = new JLabel("File not read yet");
        lblFileStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblFileStatus.setForeground(Color.red);
        
        btnDisplay = new JButton("View Records");
        btnDisplay.setBackground(Color.decode("#9b84d1"));
        btnDisplay.setEnabled(false);
        btnDisplay.addActionListener(new BtnDisplayClickListener());
        
        JPanel topPnl = new JPanel(new GridLayout(1, 2));
        topPnl.setBorder(BorderFactory.createTitledBorder("File Operation Status"));
        topPnl.add(lblFileStatus); topPnl.add(btnDisplay);
        
        tab2.add(topPnl, BorderLayout.NORTH);
        tab2.add(scrollPane, BorderLayout.CENTER);
        
        mgr = new CDRManager();
    }
    
    // TOTAL [14]
    private class MenuItemOpenClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           JFileChooser jfc = new JFileChooser();
          int result = jfc.showOpenDialog(null);
           int count = 0 ;
           if(result==JFileChooser.APPROVE_OPTION){
               BufferedReader br = null;
               try {
                   
                   String line = null;
                   File file = jfc.getSelectedFile();
                   br = new BufferedReader(new FileReader(file));
                   try {
                       while((line = br.readLine())!=null){
                          count++;
                           
                          String[] fields = line.split(",");
                          LocalDate callDate = LocalDate.parse(fields[0],DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                          LocalTime starttime = LocalTime.parse(fields[1]);
                          LocalTime endTime = LocalTime.parse(fields[2]);
                          Long extension = Long.valueOf(fields[3]);
                          String phoneNumber = fields[4];
                          String provider = fields[5];
                          
                        CallDetailRecord cdr = new CallDetailRecord(callDate, starttime, endTime, extension, phoneNumber, provider);
                        
                        mgr.addCDR(cdr);
                        lblFileStatus.setForeground(Color.blue);
                        lblFileStatus.setText("File successfully loaded ("+count+")");
                        btnDisplay.setEnabled(true);
                        taContent.setText("");
                       }
                       
                   } catch (IOException ex) {
                       Logger.getLogger(CT1GUI.class.getName()).log(Level.SEVERE, null, ex);
                   }
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(CT1GUI.class.getName()).log(Level.SEVERE, null, ex);
               } finally {
                   try {
                       br.close();
                   } catch (IOException ex) {
                       Logger.getLogger(CT1GUI.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
             
               
           }
           
            
        }
    }
    
    // TOTAL [5]
    private class BtnDisplayClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
           taContent.setText("");
           
            for(CallDetailRecord record : mgr.getRecords()) {
               taContent.append(record.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"\t"+record.getStartTime()+"\t"+record.getEndTime()+
                "\t"+record.getExtension()+"\t"+record.getPhoneNumber()+"\t"+record.getProvider()+"\n");
            }
           
           
            
            
        }
        
    }
    
    // TOTAL [1]
    private class MenuItemExitClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            
            
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CT1GUI::new);
    }
}


