package com.category;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.category.log.LogPanel;
import com.vdurmont.emoji.EmojiParser;

public class AutoRun {

	private static WebDriver driver;
	private static Map<String, String> item;
	private static String excelPath;
	private static JTextPane log_text;

	public AutoRun(final WebDriver driver, final Map<String, String> item, final String excelPath, final JTextPane log_text) {
		AutoRun.driver = driver;
		AutoRun.item = item;
		AutoRun.excelPath = excelPath;
		AutoRun.log_text = log_text;
	}

	private WebElement findElementByClassName(final String className) {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return ((RemoteWebDriver) d).findElementByClassName(className).isEnabled();
			}
		});
		return driver.findElement(By.className(className));
	}

	private List<WebElement> findElements(By id) {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return d.findElements(id).get(0).isEnabled();
			}
		});
		return driver.findElements(id);
	}

	private WebElement findElement(By id) {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return d.findElement(id).isEnabled();
			}
		});
		return driver.findElement(id);
	}

	public void login() throws Exception {
		LogPanel.append("로그인 처리 시작");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://partner.sinsangmarket.kr/orderList");

		findElement(By.id("userid")).sendKeys("ade1208");
		findElement(By.id("passwd")).sendKeys("riri1122");
		findElement(By.id("btn_login")).click();

		LogPanel.append("로그인 처리 완료");
		Thread.sleep(5000);
	}

	public void goRegistrationPage() throws Exception {
		LogPanel.append("15분간 대기 후 상품 등록 페이지 이동");
		Thread.sleep(1000 * 60 * 15); // 등록 후 10초간 대기
		driver.get("https://partner.sinsangmarket.kr/goodsRegistration");

		removeElement("html_element");
	}

	public void removeElement(final String id) {
		JavascriptExecutor js = null;
		if (driver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) driver;
		}
		js.executeScript("return document.getElementById('" + id + "').display='none';");
	}

	public void runApp() throws Exception {

		String root = new File(AutoRun.excelPath).getParent();

		login();
		goRegistrationPage();

		Thread.sleep(500);

		String name = item.get("COL0");
		String price = item.get("COL1");
		String color = item.get("COL2");
		String imageDir = item.get("COL3");
		String category = item.get("COL4");
		String size = item.get("COL5");
		String comment = item.get("COL6");
		String style = item.get("COL8");
		String madein = item.get("COL9");

		LogPanel.append(name + " 상품 등록 시작");
		inputImages(root, imageDir);
		Thread.sleep(5000);

		selectImageSort();
		Thread.sleep(5000);

		findElementByClassName("goodsName").sendKeys(name);
		Thread.sleep(5000);

		findElementByClassName("goodsPrice").sendKeys(price);
		Thread.sleep(5000);

		selectCategory(category);
		Thread.sleep(5000);

		findElementByClassName("inputColorTT").sendKeys(color + " ");
		Thread.sleep(5000);

		findElementByClassName("inputSizeTT").sendKeys(size + " ");
		Thread.sleep(5000);

		driver.findElement(By.id("myTextarea")).sendKeys(EmojiParser.removeAllEmojis(comment));
		Thread.sleep(5000);

		findElements(By.cssSelector("label[for='allOpen']")).get(0).click();
		Thread.sleep(5000);

		if (madein.equals("대한민국")) findElement(By.cssSelector("label[for='choiceKorea']")).click();
		else if (madein.equals("중국")) findElement(By.cssSelector("label[for='choiceChina']")).click();
		else findElement(By.cssSelector("label[for='choiceEtc']")).click();
		Thread.sleep(5000);

		if (style.equals("로맨틱")) findElement(By.cssSelector("label[for='romantic']")).click();
		else if (style.equals("시크")) findElement(By.cssSelector("label[for='chic']")).click();
		else if (style.equals("럭셔리")) findElement(By.cssSelector("label[for='luxury']")).click();
		else if (style.equals("미시")) findElement(By.cssSelector("label[for='missy']")).click();
		else if (style.equals("오피스")) findElement(By.cssSelector("label[for='office']")).click();
		else if (style.equals("캐쥬얼")) findElement(By.cssSelector("label[for='casual']")).click();
		else if (style.equals("섹시")) findElement(By.cssSelector("label[for='sexy']")).click();
		else if (style.equals("어반/모던")) findElement(By.cssSelector("label[for='modern']")).click();
		else if (style.equals("유니크")) findElement(By.cssSelector("label[for='unique']")).click();
		else if (style.equals("명품스타일")) findElement(By.cssSelector("label[for='masterpiece']")).click();
		else if (style.equals("연예인")) findElement(By.cssSelector("label[for='entertainer']")).click();
		else if (style.equals("심플/베이직")) findElement(By.cssSelector("label[for='simple']")).click();
		Thread.sleep(5000);

		findElement(By.id("regist")).click();
		LogPanel.append(name + " 상품 등록 완료");

		Thread.sleep(5000);
		driver.get("https://partner.sinsangmarket.kr/logout");
		LogPanel.append("Logout..");

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 카테고리 선택
	 *
	 * @param category
	 */
	private static void selectCategory(String category) {
		// 카테고리 선택
		String categoryScript = "";
		Document doc = Jsoup.parse(driver.getPageSource());
		Elements aTags = doc.select(".has-children a");
		for (int x = 0; x < aTags.size(); x++) {
			Element aTag = aTags.get(x);
			if (aTag.text().trim().equals(category)) {
				categoryScript = aTag.attr("onclick");
				break;
			}
		}
		((JavascriptExecutor) driver).executeScript(categoryScript);
	}

	/**
	 * 사진 순서 선택
	 *
	 * @throws InterruptedException
	 */
	private static void selectImageSort() throws InterruptedException {
		short idx = 0;

		driver.findElements(By.className("fileImgRound")).get(idx++).click();
		Thread.sleep(500);

		driver.findElements(By.className("fileImgRound")).get(idx++).click();
		Thread.sleep(500);

		driver.findElements(By.className("fileImgRound")).get(idx++).click();
		Thread.sleep(500);

		driver.findElements(By.className("fileImgRound")).get(idx++).click();
		Thread.sleep(500);

		driver.findElements(By.className("fileImgRound")).get(idx++).click();
		Thread.sleep(500);
	}

	/**
	 * 사진 선택
	 *
	 * @param root
	 * @param i
	 * @param item
	 * @throws InterruptedException
	 */
	private void inputImages(String root, String dir) throws InterruptedException {
		String imgDir = root + File.separator + dir;
		File images = new File(imgDir);
		File[] imageAll = images.listFiles();
		int cnt = 1;
		for (int j = 0; j < imageAll.length; j++) {
			if (imageAll[j].isFile()) {
				findElement(By.xpath("//input[@id='file-5']")).sendKeys(imageAll[j].getAbsolutePath());
				Thread.sleep(500);
				if (cnt >= 5) break;
				cnt++;
			}
		}
	}

	public String findXLSX() {
		String root = getJarFolder();
		File dir = new File(root);
		for (File file : dir.listFiles()) {
			if (getExtension(file.getAbsolutePath()).equals("xlsx")) {
				return file.getAbsolutePath();
			}
		}
		return null;
	}

	public String getJarFolder() {
		File currentJavaJarFile = new File(AutoRun.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
		String currentRootDirectoryPath = currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "");
		return currentRootDirectoryPath;
	}

	public String getExtension(final String fileStr) {
		return fileStr.substring(fileStr.lastIndexOf(".") + 1, fileStr.length());
	}
}
