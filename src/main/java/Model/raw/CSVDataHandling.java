/**
 * this class handling the raw data from CSV to a list of String[]
 */
package Model.raw;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataHandling{

//    turn the raw data into list of String[]

    /**
     * convert the data from CSV file into a list of String array
     * @param filePath
     * @return List<String[]>
     * @throws CsvValidationException
     * @throws IOException
     */
    public List<String[]> readDataCSV(String filePath) throws CsvValidationException, IOException {
        FileReader fileReader = new FileReader(filePath);
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        CSVReader csvReader = new CSVReaderBuilder(fileReader)
                .withCSVParser(parser)
                .build();
        List<String[]> output = new ArrayList<>();
        String[] test = csvReader.readNext();
        while (test!=null){
            output.add(test);
            test = csvReader.readNext();
        }
        return output;
    }
}
