import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/***
 * This class handle the reading from csv file anb writing to it
 */
public class CSVHandler {

    /***
     * get the content of csv file
     * @param fileName file name passed as paramter
     * @return the content that was read from a csv file
     */
    public static ArrayList<String[]> loadCSV(String fileName) {
        File file = new File(fileName);
        Scanner scan;
        ArrayList<String[]> returnData = new ArrayList<String[]>();
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            // if file doesn't exist, return empty list
            return returnData;
        }
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] lineSplit = line.split(",");
            if (line.endsWith(",")) {
                // if a line ends with a comma, the last element is an empty string. split ignores this.
                String[] temp = new String[lineSplit.length + 1];
                System.arraycopy(lineSplit, 0, temp, 0, lineSplit.length);
                temp[lineSplit.length] = "";
                lineSplit = temp;
            }
            returnData.add(lineSplit);
        }

        scan.close();
        return returnData;
    }

    /***
     * This method just write data arraylist to csv file
     * @param fileName passed as parameter
     * @param data to be written passed to method
     */
    public static void writeCSV(String fileName, ArrayList<String[]> data) {
        File file = new File(fileName);
        // create the file if it doesn't exist. Will throw IOException if there is an error creating the file
        try {
            file.getParentFile().mkdirs(); // create folders if they don't exist
            file.createNewFile();
        } catch (IOException e) {
            return; // just cancel the write if file creation fails
        }
        PrintWriter writer;
        try {
            // Since the file was just created, it's impossible for the file not to be found, but we must catch it anyway.
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            return;
        }
        for (String[] stringArr : data) {
            boolean first = true;
            for (String s : stringArr) {
                if (!first) {
                    // don't add a comma on the first element, since we only want commas between elements
                    writer.print(",");
                }
                first = false;
                // Remove commas embedded in text to prevent parsing errors
                writer.print(s.replace(",", ""));
            }
            // add a newline to the end of each line
            writer.println("");
        }
        writer.close();
    }
}
