/**
 * this class get the path of the CSV file
 */
package Model.raw;

final public class CSVPath {
    private static String path = CSVDataHandling.class.getClassLoader().getResource("Books.csv").getPath();

    public static String getPath(){
        return path;
    }
}
