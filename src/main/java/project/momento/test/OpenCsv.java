package project.momento.test;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OpenCsv {

    public static void writeDataToCsv(String filePath) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        String[] entries = "EW#City#State".split("#");  // 1
        writer.writeNext(entries);

        String[] entries1 = {"W", "Youngstown", "OH"};  // 2
        writer.writeNext(entries1);

        String[] entries2 = {"W", "Williamson", "WV"};
        writer.writeNext(entries2);

        writer.close();
    }

    public static void readDataFromCsv(String filePath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath)); // 1
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println(i + " " + nextLine[i]);
            }
            System.out.println();
        }
    }

}