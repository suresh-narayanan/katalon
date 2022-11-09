import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//Start clean - Kill all programs if they are running already

String cmd = "taskkill /f /im uim.exe"
Runtime.getRuntime().exec(cmd)

cmd = "taskkill /f /im uxchw.exe"
Runtime.getRuntime().exec(cmd)

cmd = "taskkill /f /im uxchwauto.exe"
Runtime.getRuntime().exec(cmd)

Thread.sleep(1000)

//Launch all the programs
cmd = "C:\\CXC5\\uxchw.exe"
Runtime.getRuntime().exec(cmd)

Windows.startApplication('C:\\UIM\\UIM.exe')

Thread.sleep(1000)

//Test multi Ticket redemption

for(int i=0; i< 2; i++)
{
	int TotalAmount = 0
	for(int t=0; t< (int)(Math.random() * 5 + 1); t++)
		{
			Amount = (int)(Math.random() * 9 + 1) * 1000;
			TotalAmount= TotalAmount + Amount
			cmd = "C:\\Payments\\Development\\UxchwAuto\\UxchwAuto\\bin\\Debug\\net6.0-windows\\uxchwauto.exe voucher 77000000000000" + Amount
			Runtime.getRuntime().exec(cmd)
			
			Thread.sleep(3000)
		}
		
		Windows.waitForElementPresent(findWindowsObject('Object Repository/UIM/TR Amount Text'), 60)
		
		assert WS.verifyMatch(Windows.getText(findWindowsObject('Object Repository/UIM/TR Amount Text')), '$'+(int)(TotalAmount/100)+'.00', false)
		
		Thread.sleep(4000)
}

//Clean-up - Close all programs 
cmd = "taskkill /f /im uxchw.exe"
Runtime.getRuntime().exec(cmd)

cmd = "taskkill /f /im uxchwauto.exe"
Runtime.getRuntime().exec(cmd)

Windows.closeApplication()