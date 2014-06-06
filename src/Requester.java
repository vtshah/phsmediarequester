import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class Requester implements ActionListener {
	public void actionPerformed(ActionEvent evt) {		
		// resets the output
		ButtonFrame.getOutputLog().setText("");
		ButtonFrame.getOutputLog().setText("");
		if (ButtonFrame.getPeriodBox().getSelectedItem().equals("Choose Period") && ButtonFrame.getAreaBox().getSelectedItem().equals("Choose Area")) {
			ButtonFrame.getOutputLog().append("Enter a period and an area\n");
		} else if (ButtonFrame.getPeriodBox().getSelectedItem().equals("Choose Period"))
			ButtonFrame.getOutputLog().append("Enter a period\n");
		else if (ButtonFrame.getAreaBox().getSelectedItem().equals("Choose Area"))
			ButtonFrame.getOutputLog().append("Enter an area\n");
		else if (!ButtonFrame.getTeacherInput().getText().equals("")){
			System.out.println(ButtonFrame.getTeacherInput().getText());
			if(!ButtonFrame.getTeacherInput().getText().trim().equals(""))
				request();
			else
				ButtonFrame.getOutputLog().append("Enter your last name");
		}
		else{
			ButtonFrame.getOutputLog().append("Enter your last name");
			
		}
		ButtonFrame.getInputLog().requestFocus();
		
		try {
			ButtonFrame.getSearcher().lookUp();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void request() {
		BufferedWriter writer = null;
		BufferedReader reader = null;
		String str;
		String[] line;
		String date = String.format("%1$tm-%1$td-%1$tY", ButtonFrame.getDateCalendarRequest().getSelectedDate());
		String period = (String) ButtonFrame.getPeriodBox().getSelectedItem();
		String teacher = ButtonFrame.getTeacherInput().getText();
		String reservedTeacher = "";
		boolean reserved = false;
		try {
			// make a time stamp for logging purposes
			String timeLog = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			String area1 = ((String) ButtonFrame.getAreaBox().getSelectedItem()).contains("Instructional Area")?((String) ButtonFrame.getAreaBox().getSelectedItem()).replace("Instructional Area", "Instructional_Area"):((String) ButtonFrame.getAreaBox().getSelectedItem());
			String area2 = "";
			String area3 = "";
			String[] totalArea = new String[3];
			
			System.out.println(area1);
			
			if(area1.contains("and")){
				totalArea = area1.split(" ");
				System.out.println(area1 + " " + area2);
				area1 = totalArea[0];
				area2 = totalArea[2];
			}else if (area1.contains("Whole Library")){
				area1 = "Computers";
				area2 = "Promethean";
				area3 = "Instructional_Area";
			}

			writer = new BufferedWriter(new FileWriter("Log", true));
			if(area1 != ""){
				System.out.println("1");
				writer.write(timeLog + " --> " + "Request for space by "
						+ ButtonFrame.getTeacherInput().getText() + " for "
						+ area1
						+ " at  period "
						+ ButtonFrame.getPeriodBox().getSelectedItem() + "." + "\n");
			}
			if(area2 != ""){
				System.out.println("2");
				writer.write(timeLog + " --> " + "Request for space by "
						+ ButtonFrame.getTeacherInput().getText() + " for "
						+ area2
						+ " at  period "
						+ ButtonFrame.getPeriodBox().getSelectedItem() + "." + "\n");
			}
			if(area3 != ""){
				System.out.println("3");
				writer.write(timeLog + " --> " + "Request for space by "
						+ ButtonFrame.getTeacherInput().getText() + " for "
						+ area3
						+ " at  period "
						+ ButtonFrame.getPeriodBox().getSelectedItem() + "." + "\n");
			}
			writer = new BufferedWriter(new FileWriter("Requests", true));
			reader = new BufferedReader(new FileReader("Requests"));

			// loop through file to see if requested time and place is open
			while (true) {
				str = reader.readLine();
				if ((str != null && str.length() != 0)) {
					line = str.split(" ");
					if (line[0].equals(date)) {
						if (ButtonFrame.getPeriodBox().getSelectedItem().equals(line[1])) {
							if (area1.equals(line[2]) || area2.equals(line[2]) || area3.equals(line[2])) {
								reserved = true;
								reservedTeacher = line[3];
								break;
							}
						}
					}
				} else {
					break;
				}
			}
			ButtonFrame.getOutputLog().setText("");
			if (!reserved) {
				if(area1 != "")
					writer.write(date + " " + period + " " + area1 + " " + teacher + "\n");
				if(area2 != ""){
					writer.write(date + " " + period + " " + area2 + " " + teacher + "\n");
					System.out.println("HSDG");
				}
				if(area3 != "")
					writer.write(date + " " + period + " " + area3 + " " + teacher + "\n");
				
				ButtonFrame.getOutputLog().append("Request submitted.\n");
			} else {
				ButtonFrame.getOutputLog().append(
						"Already requested by " + reservedTeacher
								+ ", please choose another time.\n");
			}
			
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
				reader.close();
			} catch (Exception e) {
			}
		}
	}
}