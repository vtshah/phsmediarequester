
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class ButtonFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    // final strings
    public final static String[] period = {"Choose Period", "1", "2", "3", "4", "6", "7", "8", "9"};
    public final static String[] area = {"Choose Area", "Computers", "Instructional Area", "Promethean", "Computers and Instructional Area", "Computers and Promethean", "Instructional Area and Promethean", "Whole Library"}; 
    
    private static JTextField inputLog;
    private static JTextArea outputLog;
    private static JScrollPane scrollPaneLog;
    private static JButton requestButton;
    private static JComboBox periodBox;
    private static JComboBox areaBox;
    private static JTextArea test;
    private static JTextArea instructionsRequest;
    private static JTextArea teacherTitle;
    private static JTextField teacherInput;
    private static JPanel request;
    private static datechooser.beans.DateChooserCombo dateCalendarRequest;
    
    private static Searcher searchListener;
    
    private static JTextArea output;
    
    
    public ButtonFrame(ImageIcon i) {
        
        /** Frame Properties **/
        this.setVisible(true);
        //setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Valens Co.png")));
        setLayout(new FlowLayout()); // set the layout manager
        
        setRequester();
        
        
        
        add(request);
        setSize(1000,500);


    }

    public void actionPerformed(ActionEvent evt) {
    }

    public static void main(String args[]){
    	ButtonFrame bf = new ButtonFrame(new ImageIcon("Valens Co.png"));
    	bf.setVisible(true);
    	bf.setResizable(false);
    }
    
    public void setRequester(){
    	
        // initialize
        request = new JPanel(new GridBagLayout());
        inputLog = new JTextField(15);
        outputLog = new JTextArea(2, 80);
        scrollPaneLog = new JScrollPane(outputLog);
        dateCalendarRequest = new datechooser.beans.DateChooserCombo();
        periodBox = new JComboBox();
        areaBox = new JComboBox();
        test = new JTextArea();
        instructionsRequest = new JTextArea(1, 15);
        requestButton = new JButton("Request Time");
        teacherTitle = new JTextArea();
        teacherInput = new JTextField(15);
        output = new JTextArea(5, 70);
        searchListener = new Searcher();
        
        Requester requestListener = new Requester();
        
        request.setBackground(new Color(0,0,0,125));
        
        for(int x = 0; x < period.length; x++){
            periodBox.addItem(period[x]);
        }
        for(int x = 0; x < area.length; x++){
        	areaBox.addItem(area[x]);
        }
        
        GridBagConstraints c = new GridBagConstraints();
        
        /** Set Components Properties **/
        requestButton.addActionListener(requestListener);
        request.setVisible(true);
        output.setEditable(false);
        
        inputLog.addActionListener(requestListener);
        
        instructionsRequest.setText("Request a time here:");
        instructionsRequest.setEditable(false);
        
        teacherTitle.setText("Last name:");
        teacherTitle.setEditable(false);
        
        teacherInput.addActionListener(searchListener);
        
        try {
			searchListener.lookUp();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        dateCalendarRequest.addSelectionChangedListener(searchListener);
        
        outputLog.setEditable(false);

        c.anchor = GridBagConstraints.NORTHWEST;
        
        instructionsRequest.setBackground(new Color(0,0,0));
        instructionsRequest.setForeground(new Color(207,181,59));
        
        dateCalendarRequest.setBackground(new Color(0,0,0,125));
        dateCalendarRequest.setForeground(new Color(207,181,59));
        
        
        areaBox.setBackground(new Color(0,0,0));
        areaBox.setForeground(new Color(207,181,59));
        
        periodBox.setBackground(new Color(0,0,0));
        periodBox.setForeground(new Color(207,181,59));
        
        teacherTitle.setBackground(new Color(0,0,0));
        teacherTitle.setForeground(new Color(207,181,59));
        
        teacherInput.setBackground(new Color(0,0,0));
        teacherInput.setForeground(new Color(207,181,59));
        
        output.setBackground(new Color(0,0,0));
        output.setForeground(new Color(207,181,59));
        
        outputLog.setBackground(new Color(0,0,0));
        outputLog.setForeground(new Color(207,181,59));
        
        requestButton.setForeground(new Color(0,0,0));
        requestButton.setBackground(new Color(207,181,59));
        
        // Add all the widgets to the applet
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(1,25,1,5);
        request.add(instructionsRequest, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(1,25,1,5);
        request.add(dateCalendarRequest, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(1,5,1,5);
        request.add(areaBox, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(1,5,1,25);
        request.add(periodBox, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(1,25,1,5);
        request.add(teacherTitle, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 2;
        c.insets = new Insets(1,5,1,25);
        request.add(teacherInput, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 3;
        c.insets = new Insets(1,25,1,25);
        request.add(output, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        c.insets = new Insets(1,25,1,25);
        request.add(outputLog, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 5;
        c.ipady = 2;
        c.insets = new Insets(1,25,1,25);
        request.add(requestButton, c);
        
        
    }

    
	public static String[] getPeriod() {
		return period;
	}

	public static String[] getArea() {
		return area;
	}

	public static JTextField getInputLog() {
		return inputLog;
	}

	public static JTextArea getOutputLog() {
		return outputLog;
	}

	public static JScrollPane getScrollPaneLog() {
		return scrollPaneLog;
	}

	public static JButton getRequestButton() {
		return requestButton;
	}

	public static JComboBox getPeriodBox() {
		return periodBox;
	}

	public static JComboBox getAreaBox() {
		return areaBox;
	}

	public static JTextArea getTest() {
		return test;
	}

	public static JTextArea getInstructionsRequest() {
		return instructionsRequest;
	}

	public static JTextArea getTeacherTitle() {
		return teacherTitle;
	}

	public static JTextField getTeacherInput() {
		return teacherInput;
	}

	public static JPanel getRequest() {
		return request;
	}

	public static datechooser.beans.DateChooserCombo getDateCalendarRequest() {
		return dateCalendarRequest;
	}

	public static JTextArea getOutput() {
		return output;
	}
	
	public static Searcher getSearcher(){
		return searchListener;
	}
    
    
}

