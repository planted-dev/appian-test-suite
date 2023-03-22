package appian.test.suite;

public class ADMF753 {
    LibraryFixture lib = new LibraryFixture();
    /*
     * Test Case: 1
     * Description: 
     */
    public void testCase1() {
        lib.accessSiteAsRole(null, null);
    }

    public void main( String[] args )
    {
        testCase1();
    }
}
