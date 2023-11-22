package Models;

/**
 * Report interface to ensure that report subclasses will implement the generateReport
 */
public abstract class Report {
    /**
     * function to generate the report based on int input. int 1 would generate txt, int 2 would generate csv file
     * @param type the integer input to determine the file type of the report
     * @return true if generated, else false
     */
    abstract boolean generateReport(int type);
}
