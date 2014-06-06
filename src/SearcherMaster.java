import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;

public class SearcherMaster implements ActionListener, SelectionChangedListener {

	private static String[][] output = new String[3][9];
	
	
	public void actionPerformed(ActionEvent arg0) {
		try {
			lookUp();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void lookUp() throws FileNotFoundException {
		String date = String.format("%1$tm-%1$td-%1$tY", ButtonFrameMaster.getDateCalendarRequest().getSelectedDate());
		String str;
		String[] line = new String[10];
		String out = date + "\t1\t2\t3\t4\t6\t7\t8\t9\nComputers\t";
		output = new String[3][9];
		BufferedReader reader = new BufferedReader(new FileReader("Requests"));

		// loops through file until it hits end
		while (true) {
			// looks if a period and section are filled and puts it into the full
			try {
				str = reader.readLine();
				if (str != null) {
					line = str.split(" ");
					if (line[0].equals(date)) {
						int period = (!line[1].equals("Lunch") ? Integer.parseInt(line[1]) : 5) - 1;
						int area = line[2].equals("Computers") ? 0 : (line[2].equals("Instructional_Area") ? 1 : 2);
						output[area][period] = line[3];
						line = str.split(" ");
					}
				} else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Format output for calendar
		
		for (int x = 0; x < output.length; x++) {
			for (int y = 0; y < output[0].length; y++) {
				if(y == 4) y++;
				if(output[x][y] != null){
					out += (output[x][y].length() > 8 ? output[x][y].substring(0,8):output[x][y]) + "\t";
				}else{
					out += "OPEN\t";
				}
			}
			if(x == 0){
				out += "\nInstructional\t";
			}else if(x == 1){
				out += "\nPromethean\t";
			}
		}
		
		ButtonFrameMaster.getOutput().setText(out);
		
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static String[][] getOutput(){ return output; }

	public void onSelectionChange(SelectionChangedEvent arg0) {
		try {
			lookUp();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
