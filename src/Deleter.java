import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Deleter implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		delete();
	}

	private void delete() {
		BufferedReader reader = null;
		String str;
		String[] line;
		String date = String.format("%1$tm-%1$td-%1$tY", ButtonFrameMaster.getDateCalendarRequest().getSelectedDate());
		String toRemove = null;
		String area = ((String) ButtonFrameMaster.getAreaBox().getSelectedItem()).replace(" ", "_");
		try {
			
			File input = new File("Requests");
			
			reader = new BufferedReader(new FileReader(input));
			
			while (true) {

				try {
					str = reader.readLine();
					if (str != null) {
						line = str.split(" ");
						System.out.println("String != null");
						if(date.equals(line[0])){
							System.out.println("date");
							if(ButtonFrameMaster.getPeriodBox().getSelectedItem().equals(line[1])){
								System.out.println("period");
								if(area.equals(line[2])){
									System.out.println("area");
									toRemove = str;
									break;
								}
							}
						}
						
					} else {
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		System.out.println("TEST");
		
		if(toRemove != null)
			removeLineFromFile("Requests", toRemove);
		
		try {
			ButtonFrameMaster.getSearcher().lookUp();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeLineFromFile(String file, String lineToRemove) {

		try {

		  File inFile = new File(file);

		  if (!inFile.isFile()) {
		    System.out.println("Parameter is not an existing file");
		    return;
		  }

		  //Construct the new file that will later be renamed to the original filename.
		  File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		  BufferedReader br = new BufferedReader(new FileReader(file));
		  PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		  String line = null;

		  //Read from the original file and write to the new
		  //unless content matches data to be removed.
		  while ((line = br.readLine()) != null) {

		    if (!line.trim().equals(lineToRemove)) {

		      pw.println(line);
		      pw.flush();
		    }
		  }
		  pw.close();
		  br.close();

		  //Delete the original file
		  if (!inFile.delete()) {
		    System.out.println("Could not delete file");
		    return;
		  }

		  //Rename the new file to the filename the original file had.
		  if (!tempFile.renameTo(inFile))
		    System.out.println("Could not rename file");

		}
		catch (FileNotFoundException ex) {
		  ex.printStackTrace();
		}
		catch (IOException ex) {
		  ex.printStackTrace();
		}
	}

}
