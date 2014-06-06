
import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
public class ButtonFrameMaster extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// final strings
    public final static String[] period = {"Choose Period", "1", "2", "3", "4", "6", "7", "8", "9"};
    public final static String[] area = {"Choose Area", "Computers", "Instructional Area", "Promethean"}; 
    
    private static JButton requestButton;
    private static JComboBox periodBox;
    private static JComboBox areaBox;
    private static JPanel request;
    private static datechooser.beans.DateChooserCombo dateCalendarRequest;
    
    private static SearcherMaster searchListener;
    private static Deleter deleteListener;
    
    private static JTextArea output;
    
    
    public ButtonFrameMaster() {
        
        /** Frame Properties **/
        this.setVisible(true);
        setContentPane(new JLabel(new ImageIcon("Valens Co 2.png")));
        setLayout(new FlowLayout()); // set the layout manager
        
        setRequester();
        
        
        
        add(request);
        setSize(1000,330);


    }

    public static void main(String[] args){
    	ButtonFrameMaster bfm = new ButtonFrameMaster();
    	bfm.setVisible(true);
    	bfm.setResizable(false);
    }
    
    public void actionPerformed(ActionEvent evt) {
    }

    public void setRequester(){
    	
        // initialize
        request = new JPanel(new GridBagLayout());
        dateCalendarRequest = new datechooser.beans.DateChooserCombo();
        requestButton = new JButton("Delete Time");
        output = new JTextArea(5,70);
        periodBox = new JComboBox();
        areaBox = new JComboBox();
        searchListener = new SearcherMaster();
        deleteListener = new Deleter();
        
        request.setBackground(new Color(0,0,0,125));
        
        for(int x = 0; x < period.length; x++){
            periodBox.addItem(period[x]);
        }
        for(int x = 0; x < area.length; x++){
        	areaBox.addItem(area[x]);
        }

        GridBagConstraints c = new GridBagConstraints();
        
        /** Set Components Properties **/
        request.setVisible(true);
        output.setEditable(false);
        
        dateCalendarRequest.addSelectionChangedListener(searchListener);
        requestButton.addActionListener(deleteListener);
        
        c.anchor = GridBagConstraints.NORTHWEST;
        
        try {
			searchListener.lookUp();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        dateCalendarRequest.setBackground(new Color(0,0,0,125));
        dateCalendarRequest.setForeground(new Color(207,181,59));
        
        
        areaBox.setBackground(new Color(0,0,0));
        areaBox.setForeground(new Color(207,181,59));
        
        periodBox.setBackground(new Color(0,0,0));
        periodBox.setForeground(new Color(207,181,59));
        
        output.setBackground(new Color(0,0,0));
        output.setForeground(new Color(207,181,59));
        
        requestButton.setForeground(new Color(0,0,0));
        requestButton.setBackground(new Color(207,181,59));
        // Add all the widgets to the applet
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(1,25,1,5);
        request.add(dateCalendarRequest, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(1,5,1,5);
        request.add(areaBox, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(1,5,1,25);
        request.add(periodBox, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(1,25,1,25);
        request.add(output, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 2;
        c.insets = new Insets(1,25,1,25);
        request.add(requestButton, c);
        
        
    }

	public static datechooser.beans.DateChooserCombo getDateCalendarRequest() {
		return dateCalendarRequest;
	}
    
	public static JTextArea getOutput() {
		return output;
	}
	
	public static JComboBox getPeriodBox() {
		return periodBox;
	}

	public static JComboBox getAreaBox() {
		return areaBox;
	}
	
	public static SearcherMaster getSearcher() {
		return searchListener;
	}
    
    
}

