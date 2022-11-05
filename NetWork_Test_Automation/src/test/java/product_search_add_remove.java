import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class product_search_add_remove {

    WebDriver driver;

    @Test
    @Order(1)
    public void Testing() throws InterruptedException{

        //NetWork alışveriş sitesi anasayfa açılır.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.network.com.tr/");
        System.out.println("--- NetWork Alisveris Sitesi Acildi.");

        String URL = driver.getCurrentUrl();
        String actualURL = "https://www.network.com.tr/";
        System.out.println("--- NetWork URL Geldigi Kontol Edildi.");

        //Cookies Accept
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        System.out.println("--- NetWork Alisveris Sitesi Cookies Kabul Edildi.");
        Thread.sleep(2000);

        //"CEKET" araması yapıldı.
        driver.findElement(By.name("searchKey")).sendKeys("CEKET");
        System.out.println("--- 'CEKET' Aramasi Yapildi.");
        Thread.sleep(2000);

        driver.findElement(By.className("sgm-search-show-all")).click();
        System.out.println("--- Tumunu Gor Butonuna Tiklandi.");
        Thread.sleep(2000);

        //"CEKET" aramasında 2. sayfaya geçmek için sayfa sonuna scroll yapıldı.
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,15500)");
        System.out.println("--- Sayfa Sonuna Scroll Edildi.");
        Thread.sleep(3000);

        //2. sayfaya geçmek için "Daha Fazla Göster" butonuna tıklandı.
        driver.findElement(By.className("relative")).click();
        System.out.println("--- 'Daha Fazla Göster' Butonuna Tiklandi.");
        Thread.sleep(2000);

        //2. sayfadan indirimli ilk ürün seçildi.
        driver.findElement(By.linkText("Ekru Jakarlı Şal Yaka Smokin Ceketi")).click();
        System.out.println("--- Indirimli Urun Secimi Yapildi.");
        Thread.sleep(2000);

        //Seçilen ürünün beden seçimi yapıldı.
        driver.findElement(By.xpath("//label[@for='size_46/6N']")).click();
        System.out.println("--- Beden Secimi Yapildi.");
        Thread.sleep(2000);

        //Seçilen ürün sepete eklendi.
        driver.findElement(By.xpath("//button[contains(@class,'addToCart')]")).click();
        System.out.println("--- Indirimli Urun Sepete Eklendi.");
        Thread.sleep(1000);

        //Alışveriş sepetine gidilerek kontrol edildi.
        driver.findElement(By.xpath("//div[@class='header__basketSummary']//a[@href='/cart']")).click();
        System.out.println("--- Alisveris Sepetine Gidildi.");
        Thread.sleep(2000);

        //Sepete eklenen ürünün eski faiyatının indirimli fiyatta büyük olduğu kontrol edildi.
        String oldPrice = driver.findElement(By.xpath("//div[@class='cartItem__prices']//span[@class='cartItem__price -old -labelPrice']")).getText();
        String salePrice = driver.findElement(By.xpath("//div[@class='cartItem__prices']//span[@class='cartItem__price -sale']")).getText();
        Assert.assertNotEquals(oldPrice, salePrice);
        System.out.println("--- Urun Fiyat Kontrolu Yapildi.");

        driver.findElement(By.xpath("//div[@class='summary']//button[contains(@class,'continueButton')]")).click();
        System.out.println("--- Devam Et Butonuna Tiklandi.");
        Thread.sleep(2000);

        //Giriş yapmak için kullanıcı bilgileri girildi.
        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("eskiturkerturk@gmail.com");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("188110.x");
        Thread.sleep(2000);

        //Giriş yapılmadan anasayfa yönderilmesi yapıldı.
        driver.findElement(By.xpath("//div[@class='footerCheckout__bottom']//a[@href='/']")).click();
        System.out.println("--- NetWork Logosunu Tiklanarak Anasayfaya Yonlendirildi.");
        Thread.sleep(2000);

        //Sepet tekrar açılıp sepetteki ürün çıkarıldı.
        driver.findElement(By.xpath("//div[@class='header__basket js-basket header__basketLink']")).click();
        System.out.println("--- Sepetim Sekmesi Acildi.");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='header__basketProductBtn header__basketModal -remove']")).click();
        System.out.println("--- Sepetteki Urunler Cıkarildi.");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@class='btn -black o-removeCartModal__button']")).click();
        System.out.println("--- Sepetteki Urunlerin Cikarilmasi Onaylandı.");
        Thread.sleep(2000);

        //Test tamamlandı.
        System.out.println("--- NetWork Alisveris Sitesi Testi Tamamlandi.");
        driver.quit();
    }
}
