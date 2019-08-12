package parameterization;

import org.testng.annotations.DataProvider;

public class DataproviderClass {
    @DataProvider (name = "SearchProvider")
    public static Object[][] getDataFromDataprovider() {
      return new Object[][] {
              { "misho", "veliko tarnovo"},
              { "bally", "sofia"},
              { "niko", "germany"}
        };
    }
}
