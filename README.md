  Selenium and TestNG Testcases login and register for :-
                                            https://www.phptravels.net/register
                                            https://www.phptravels.net/login


Discrption

there are 2 testcase (validateLogin and validateRegister) with After and BeforeMethod annoutation for setUp and teatDown methods, 2 func (takeScreenShot, getStatus).

BeforeMethod setUp:

it runs before each testcase, it establish the driver.

AfterMethod tearDown:

it runs after each testcase, check if the testcase failed or not, if failed, it generate a report with screen shot to the failed testcase and terminate the driver. close the driver.

validateRegister:

it goes to https://www.phptravels.net/register and enter firstname, lastname, phone, email, password, confirmpassword, click_button then Assert the url with expected_url url = "https://www.phptravels.net/account/"

validateLogin:

it goes to https://www.phptravels.net/login and enter username, password, click_button then Assert the url with expected_url url = "https://www.phptravels.net/account/"

takeScreenShot:

takes a screenshot.

getStatus:

generate a HttpInterceptor and have request and get response.
