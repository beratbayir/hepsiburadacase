package hepsiburadaScenario;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class baseTestClass {
    public static DesiredCapabilities cap = new DesiredCapabilities();
    public static AndroidDriver<MobileElement> ad;

    @BeforeTest
    public static void initSetUp() throws MalformedURLException {
        cap.setCapability("deviceName", "Nexus");
        cap.setCapability("udid", "emulator-5554");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "11");
        cap.setCapability("appPackage", "com.pozitron.hepsiburada");
        cap.setCapability("appActivity", "com.hepsiburada.ui.startup.SplashActivity");
        {
            try {
                ad = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), cap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void TestCase1() throws InterruptedException {
        ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        clickableButtonwithId("imageViewClose");
        clickableButtonwithId("textViewLocation");
        openPicker("citySelectorView");
        clickRandomItem();
        String city = getTextField("textViewSelectedItem");
        openPicker("townSelectorView");
        clickRandomItem();
        openPicker("districtSelectorView");
        clickRandomItem();
        clickableButtonwithId("buttonSave");
        Assert.assertEquals(getTextField("tvTitle"),"Konumunuz kaydedildi.");
        clickableButtonwithId("nav_graph_category");
        selectRandomCategory();
        Assert.assertEquals(city,getTextField("textViewLocation"));
    }
    @Test
    public void TestCase2() throws InterruptedException {
        ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(getTextField("dod_title"),"Süper Fiyat, Süper Teklif");
        clickableButtonwithId("dod_all");
        clickableButtonwithId("view_product");
        clickableButtonwithId("imageViewer");
        swipe();
        clickableButtonwithId("leftIcon");
        clickableButtonwithId("product_detail_favourites");
        ad.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        writeInput("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View/android.widget.EditText","beratgameacc1@gmail.com");
        eMailSignInButton();
        writeInput("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.widget.EditText","Testhb123");
        passwordSignInButton();
        isDisplayedOnPage("alertTitle");
        Assert.assertEquals(getTextField("android:id/message"),"Hoş geldin Test Test. Keyifli alışverişler dileriz.");
        clickableButtonwithId("android:id/button1");
        clickableButtonwithId("leftIcon");
        clickableButtonwithId("iv_toolbar_user_account_menu");
        isDisplayedOnPage("tvUserNameFull");
        Assert.assertEquals(getTextField("tvUserNameFull"),"Test Test");
        //clickableButtonWithAccessbilityId("account_menu_4");
        clickMyPageListItem("Beğendiklerim");
        MobileElement likePageItem = ad.findElementByXPath("//android.view.View[@content-desc='123']/android.view.View/android.view.View/android.view.View/android.view.View[1]");
        Assert.assertNotNull(likePageItem);




    }
    @AfterTest
    public void tearDown(){
        ad.closeApp();
    }


    public void clickRandomItem(){
        Random rand = new Random();
        int randomLocation = 3;
        int elementLocation = rand.nextInt(randomLocation);
        MobileElement picker = ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[@index='"+elementLocation+"']");
        picker.isDisplayed();
        picker.click();


    }
    public void openPicker(String id){
        MobileElement oPicker = ad.findElementById(id);
        oPicker.isDisplayed();
        oPicker.click();


    }

    public void clickableButtonwithId(String id){
        MobileElement element = ad.findElementById(id);
        element.isDisplayed();
        element.click();

    }

    public String getTextField(String id){
        MobileElement pickerTextInfo = ad.findElementById(id);
        String info = pickerTextInfo.getText();
        System.out.println(info);

        return info;
    }
    public void selectRandomCategory(){
        Random r = new Random();
        int locationNumber = 11;
        int categoryLocation = r.nextInt(locationNumber);
        MobileElement category = ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[@index='"+categoryLocation+"']");
        category.isDisplayed();
        category.click();
    }
    public void swipe(){
    new TouchAction<>(ad).press(point(868,1231)).moveTo(point(348,1225)).release().perform();


    }
    public void writeInput(String id,String text){
        MobileElement inputField = ad.findElementByXPath(id);
        inputField.isDisplayed();
        inputField.click();
        inputField.sendKeys(text);

    }
    public void isDisplayedOnPage(String id){
        MobileElement pageElement = ad.findElementById(id);
        pageElement.isDisplayed();
    }
    public void clickableButtonWithAccessbilityId(String acId){
        MobileElement elementAc = ad.findElementById(acId);
        elementAc.isDisplayed();
        elementAc.click();

    }
    public void eMailSignInButton(){
        MobileElement elementAc = ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[3]/android.widget.Button");
        elementAc.isDisplayed();
        elementAc.click();

    }
    public void passwordSignInButton(){
        MobileElement elementAc = ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[3]/android.widget.Button");
        elementAc.click();

    }
    public void clickMyPageListItem(String text){
        MobileElement likePage = ad.findElementByXPath("//android.widget.TextView[@text='"+text+"']");
        likePage.click();
    }






}
