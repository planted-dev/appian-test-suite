package appian.test.suite;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.appiancorp.ps.automatedtest.fixture.SitesFixture;

public class LibraryFixture extends SitesFixture {
    /* Folder constants */
    public String UPLOADS = "../resources/uploads/";
    public String DOWNLOADS = "../resources/downloads/";
    public String SCREENSHOTS = "../resources/screenshots/";

    /* Date/time constants and methods */
    public String formatDate(String format, LocalDate date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    public String now(String format) { return formatDate(format, LocalDate.now()); }
    public String today(String format) { return now(format); }
    public String yesterday(String format) { return formatDate(format, LocalDate.now().plusDays(-1)); }
    public String tomorrow(String format) { return formatDate(format, LocalDate.now().plusDays(1)); }
    public String twoDaysAgo(String format) { return formatDate(format, LocalDate.now().plusDays(-2)); }
    public String twoDays(String format) { return formatDate(format, LocalDate.now().plusDays(2)); }
    public String threeDaysAgo(String format) { return formatDate(format, LocalDate.now().plusDays(-3)); }
    public String threeDays(String format) { return formatDate(format, LocalDate.now().plusDays(3)); }
    public String oneWeekAgo(String format) { return formatDate(format, LocalDate.now().plusWeeks(-1)); }
    public String oneWeek(String format) { return formatDate(format, LocalDate.now().plusWeeks(1)); }
    public String twoWeeksAgo(String format) { return formatDate(format, LocalDate.now().plusWeeks(-2)); }
    public String twoWeeks(String format) { return formatDate(format, LocalDate.now().plusWeeks(2)); }
    public String oneMonthAgo(String format) { return formatDate(format, LocalDate.now().plusMonths(-1)); }
    public String oneMonth(String format) { return formatDate(format, LocalDate.now().plusMonths(1)); }
    public String twoMonthsAgo(String format) { return formatDate(format, LocalDate.now().plusMonths(-2)); }
    public String twoMonths(String format) { return formatDate(format, LocalDate.now().plusMonths(2)); }
    public String threeMonthsAgo(String format) { return formatDate(format, LocalDate.now().plusMonths(-3)); }
    public String threeMonths(String format) { return formatDate(format, LocalDate.now().plusMonths(3)); }
    public String sixMonthsAgo(String format) { return formatDate(format, LocalDate.now().plusMonths(-6)); }
    public String sixMonths(String format) { return formatDate(format, LocalDate.now().plusMonths(6)); }
    public String oneYearAgo(String format) { return formatDate(format, LocalDate.now().plusYears(-1)); }
    public String oneYear(String format) { return formatDate(format, LocalDate.now().plusYears(1)); }
    public String twentyOneYearsAgo(String format) { return formatDate(format, LocalDate.now().plusYears(-21)); }
    public String day() { return now("dd"); }
    public String month() { return now("MM"); }
    public String year() { return now("yyyy"); }
    public String hour() { return now("HH"); }
    public String minute() { return now("mm"); }
    public String second() { return now("ss"); }

    /* Fixtures */
    public void setScreenshotsFolder(String appPrefix, String testName) {
        setScreenshotPathTo(SCREENSHOTS + appPrefix + "/" + testName + "/");
    }
    public void accessSiteAsRole(String site, String role) {
        loginWithRole(role);
        navigateToSite(site);
    }
    public void accessSitePageAsRole(String site, String page, String role) {
        accessSiteAsRole(site, role);
        clickOnSitePage(page);
    }
    public void switchToRole(String role) {
        logout();
        loginWithRole(role);
    }
    public void switchToSiteAsRole(String site, String role) {
        switchToRole(role);
        navigateToSite(site);
    }
    public void switchToSitePageAsRole(String site, String page, String role) {
        switchToSiteAsRole(site, role);
        clickOnSitePage(page);
    }
    public void submitForm() {
        clickOnButton("Submit");
    }
    public void cancelForm() {
        clickOnButton("Cancel");
    }
    public boolean verifyRecordGridColumnOnlyContains(String column, String value) {
        sortRecordGridByColumn(column);
        boolean val1 = getGridColumnRowValue("1", column, "1").equals(value);
        sortRecordGridByColumn(column);
        boolean val2 = getGridColumnRowValue("1", column, "1").equals(value);
        return val1 == val2;
    }
    public boolean verifyGridHasRows(String grid) {
        int totalCount = getGridTotalCount(grid);
        return totalCount > 0;
    }
    public boolean verifyGridRowCountIs(String grid, int count) {
        int rowCount = getGridRowCount(grid);
        return rowCount == count;
    }
    public void toggleFormSection(String section) {
        clickOnCard(section);
    }
    public boolean verifyFieldIsRequired(String field) {
        return getFieldValidationMessage(field).equals("A value is required");
    }
    public boolean verifyAccessDenied() {
        return verifyTextIsPresent("403") && verifyTextIsPresent("Access Denied") && verifyTextIsPresent("You don't have permission to view this page");
    }
    public boolean verifyPlaceholderIsPresent(String placeholder) {
        String val = getFieldWithPlaceholderValue(placeholder);
        return val.equals("") || val.isEmpty() || val.equals(null);
    }
    public void setVariableWithTestData(String variable, String key) {
        String tedaTestVar = getWebApiWithRole("test-data?key=" + key, "teda_user");
        setTestVariableWith(variable, tedaTestVar);
    }
    public void goToRecord(String record) {
        clickOnLink("Records");
        waitForProgressBar();
        searchFor(record);
        waitForProgressBar();
        clickOnLink("1");
    }
}
