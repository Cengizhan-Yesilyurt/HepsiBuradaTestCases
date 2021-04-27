package testCases;

import listener.Listener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testCaseFunction.SearchTestCaseFunction;
import utils.BaseTest;

@Listeners({Listener.class})
public class SearchTestCase extends BaseTest {

    @Test
    public void searchTest() throws InterruptedException {
        SearchTestCaseFunction searchTestCaseFunction = new SearchTestCaseFunction(driver);
        searchTestCaseFunction.login();
        searchTestCaseFunction.search();
        searchTestCaseFunction.searchNavBar();
        searchTestCaseFunction.nextPage();
        searchTestCaseFunction.getNthProduct();
        searchTestCaseFunction.like();
        searchTestCaseFunction.goToLiked();
        searchTestCaseFunction.addBasket();
        searchTestCaseFunction.gotoBasket();
        searchTestCaseFunction.removeProduct();
        searchTestCaseFunction.isEmptyBasket();
    }

}
