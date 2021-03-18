import java.time.Year;
import java.util.ArrayList;

/***
 * This class hold the property tax information
 */
public class PropertyTax {


    private static final String taxFileName = "data/taxCalculations.csv";
    private static ArrayList<String[]> stringData;
    private static double[] taxRate;
    private static double[] locationTaxCal;
    private static double[] flatChargeCal;
    private static double[] fixedCostCal;
    private static double[] overduePenalty;

    /***
     * this method read from the csv file information
     */
    public static void loadPropertyTax() {
        ArrayList<String[]> csvData = CSVHandler.loadCSV(taxFileName);
        if (csvData.size() == 0) {
            saveToCSV(); // if csv is missing, make new one
            csvData = CSVHandler.loadCSV(taxFileName);
        }
        ArrayList<double[]> csvDataTemp = new ArrayList<>();

        // Need to go through the arraylists contents and parse everything to a double
        for (String[] temp1 : csvData) {

            double[] temp2 = new double[temp1.length];

            for (int j = 0; j < temp1.length; j++) {

                temp2[j] = Double.parseDouble(temp1[j]);
            }
            csvDataTemp.add(temp2);
        }
        // 4 elements in the arraylist
        taxRate = csvDataTemp.get(0);
        locationTaxCal = csvDataTemp.get(1);
        flatChargeCal = csvDataTemp.get(2);
        fixedCostCal = csvDataTemp.get(3);
        overduePenalty = csvDataTemp.get(4);
    }


    // Returns the overdue tax from previous years going into a specific year

    /***
     *  get OverdueTaxForYear
     * @param property passed as parameter
     * @param payments passed as parameter
     * @param year passed as parameter
     * @return Returns the overdue tax from previous years going into a specific year
     */
    public static double getOverdueTaxForYear(Property property, PaymentList payments, int year) {
        double yearlyTax = getYearlyTaxDue(property);
        double runningOverdue = 0;
        for (int yearX = property.getYearAdded(); yearX < year; yearX++) {
            ArrayList<Payment> paymentsMade = payments.getPropertyPaymentsOnYear(property, yearX);
            double totalPaidOnYear = 0;
            for (Payment p : paymentsMade) {
                totalPaidOnYear += p.getAmount();
            }
            runningOverdue += yearlyTax - totalPaidOnYear;
            if (runningOverdue > 0) {
                runningOverdue *= 1 + overduePenalty[0];
            }
        }
        return runningOverdue;
    }

    /***
     * get the yearly tax due
     * @param property passed as parameter
     * @return yearly tax due
     */
    public static double getYearlyTaxDue(Property property) {
        double totalTax = 0;
        totalTax += fixedCostCal[0];

        double value = property.getEstimatedMarketValue();
        double marketValueTaxRate;
        if (value > taxRate[0]) {
            marketValueTaxRate = taxRate[1];
        } else if ((value <= taxRate[2]) && (value >= taxRate[3])) {
            marketValueTaxRate = taxRate[4];
        } else if ((value <= taxRate[5]) && (value >= taxRate[6])) {
            marketValueTaxRate = taxRate[7];
        } else {
            marketValueTaxRate = taxRate[8];
        }
        totalTax += marketValueTaxRate;

        String address = property.getAddress().getLocationType();
        double locationTax = 0;
        if (address.equalsIgnoreCase("City")) {
            locationTax = locationTaxCal[0];
        } else if (address.equalsIgnoreCase("Large town")) {
            locationTax = locationTaxCal[1];
        } else if (address.equalsIgnoreCase("Small town")) {
            locationTax = locationTaxCal[2];
        } else if (address.equalsIgnoreCase("Village")) {
            locationTax = locationTaxCal[3];
        } else if (address.equalsIgnoreCase("Countryside")) {
            locationTax = locationTaxCal[4];
        }
        totalTax += locationTax;

        double flatCharge = 0;
        if (!property.getPrincipalResidence()) {
            flatCharge = flatChargeCal[0];
        }
        totalTax += flatCharge;

        return totalTax;
    }

    /***
     * This method write what the property tax calculation is based on to csv file
     */
    private static void saveToCSV() {
        stringData = new ArrayList<>();
        taxRateSaveToCSV("650000", "0.04", "650000", "400001", "0.02", "400000", "150000", "0.01", "0");
        locationTaxSaveToCSV("100", "80", "60", "50", "25");
        flatChargeSaveToCSV("100");
        fixedCostSaveToCSV("100");
        overduePenaltySaveToCSV("0.07");
        CSVHandler.writeCSV(taxFileName, stringData);
    }

    /***
     * This method add the tax rates of a property to arrayList
     * @param zero passed as parameter
     * @param one passed as parameter
     * @param two passed as parameter
     * @param three passed as parameter
     * @param four passed as parameter
     * @param five passed as parameter
     * @param six passed as parameter
     * @param seven passed as parameter
     * @param eight passed as parameter
     */
    private static void taxRateSaveToCSV(String zero, String one, String two, String three, String four, String five, String six,
                                         String seven, String eight) {
        String[] s = new String[9];
        s[0] = zero;
        s[1] = one;
        s[2] = two;
        s[3] = three;
        s[4] = four;
        s[5] = five;
        s[6] = six;
        s[7] = seven;
        s[8] = eight;
        stringData.add(s);
    }

    /***
     * This method add the lacation category  of a property to arrayList
     * @param zero passed as parameter
     * @param one passed as parameter
     * @param two passed as parameter
     * @param three passed as parameter
     * @param four passed as parameter
     */
    private static void locationTaxSaveToCSV(String zero, String one, String two, String three, String four) {
        String[] s = new String[5];
        s[0] = zero;
        s[1] = one;
        s[2] = two;
        s[3] = three;
        s[4] = four;
        stringData.add(s);
    }

    /***
     * This method add the flat charge to array list
     * @param zero passed as parameter
     */
    private static void flatChargeSaveToCSV(String zero) {
        String[] s = new String[1];
        s[0] = zero;
        stringData.add(s);
    }

    /***
     * This method add the fixed cost to array list
     * @param zero passed as parameter
     */
    private static void fixedCostSaveToCSV(String zero) {
        String[] s = new String[1];
        s[0] = zero;
        stringData.add(s);
    }

    /***
     * This method add the overduePenalty to array list
     * @param zero passed as parameter
     */
    private static void overduePenaltySaveToCSV(String zero) {
        String[] s = new String[1];
        s[0] = zero;
        stringData.add(s);
    }
}
