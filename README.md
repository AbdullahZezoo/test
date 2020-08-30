  ***Selenium and TestNG Testcases login and register for :-
                                            https://www.phptravels.net/register
                                            https://www.phptravels.net/login

Requirements:-
_______________
  First, you should change the filePath in function takeScreenShot for path of screenShot and you can see reports on build/test/results/tester/TestNG tests.html

                                       ________________________________________________________________________________

Discrption
__________
there are 2 testcase (validateLogin and validateRegister) with After and BeforeMethod annoutation for setUp and teatDown methods, 2 func (takeScreenShot, getStatus).


BeforeMethod (setUp):
__________________
it runs before each testcase, it establish the driver.

AfterMethod (tearDown):
_______________________
it runs after each testcase, check if the testcase failed or not, if failed, it generate a report with screen shot to the failed testcase and terminate the driver. close the driver.

validateRegister:
_________________
it goes to https://www.phptravels.net/register and enter firstname, lastname, phone, email, password, confirmpassword, click_button then getCurrentUrl and assertUrl.

validateLogin:
_______________
it goes to https://www.phptravels.net/login and enter username, password, click_button then getCurrentUrl and assertUrl.

getCurrentUrl :
_______________
returns the current url.

assertUrl :
____________
assertequal the actual url to the expected url .... the expected_url = "https://www.phptravels.net/account/"

takeScreenShot:
________________
takes a screenshot.

getStatus:
___________
generate a HttpInterceptor and have request and get response.
