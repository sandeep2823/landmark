package backend;

import static io.restassured.RestAssured.given;
import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Test
public class Backend_test {
	public static void main(String[] args) {
		PriceCoversionTest1 conversion = new PriceCoversionTest1();
		conversion.BackEndTask1();
		conversion.BackEndTask2();
		conversion.BackEndTask3();
	}
}

class PriceCoversionTest1 {
	String apiKey = "122708b5-5600-41fa-9068-991fabbb163b";

	// function to retrieve BTC, USDT and ETH id and then covert there values to
	// Bolivian Boliviano (BOB)
	public void BackEndTask1() {
		System.out.println("TASK 1");
		// Getting ID for BTC
		Response responseBitcoin = given().baseUri("https://pro-api.coinmarketcap.com/")
				.basePath("v1/cryptocurrency/map").header("X-CMC_PRO_API_KEY", apiKey).queryParam("symbol", "BTC")
				.when().get();
		String jsonStringBitcoin = responseBitcoin.asString();
		JsonPath jpbitcoin = new JsonPath(jsonStringBitcoin);
		String bitcoinID = jpbitcoin.getString("data['id']");
		bitcoinID = bitcoinID.replaceAll("\\[", "").replaceAll("\\]", "");
		// System.out.println(bitcoinID);

		// Getting ID for USDT
		Response responseUSDTether = given().baseUri("https://pro-api.coinmarketcap.com/")
				.basePath("v1/cryptocurrency/map").header("X-CMC_PRO_API_KEY", apiKey).queryParam("symbol", "USDT")
				.when().get();
		String jsonStringUSDTether = responseUSDTether.asString();
		JsonPath usdtether = new JsonPath(jsonStringUSDTether);
		String USDTetherID = usdtether.getString("data['id']");
		USDTetherID = USDTetherID.replaceAll("\\[", "").replaceAll("\\]", "");
		// System.out.println(USDTetherID);

		// Getting ID for ETH
		Response responseETH = given().baseUri("https://pro-api.coinmarketcap.com/").basePath("v1/cryptocurrency/map")
				.header("X-CMC_PRO_API_KEY", apiKey).queryParam("symbol", "ETH").when().get();
		String jsonStringETH = responseETH.asString();
		JsonPath jpeth = new JsonPath(jsonStringETH);
		String ETHID = jpeth.getString("data['id']");
		ETHID = ETHID.replaceAll("\\[", "").replaceAll("\\]", "");
		// System.out.println(ETHID);

		// Converting BTC to BOB
		Response convertBTCtoBOB = given().baseUri("https://pro-api.coinmarketcap.com/")
				.basePath("v1/tools/price-conversion").header("X-CMC_PRO_API_KEY", apiKey).queryParam("amount", "1")
				.queryParam("id", bitcoinID).queryParam("convert", "BOB").when().get();
		String BTCconvertBOB = convertBTCtoBOB.asPrettyString();
		JsonPath jpBTCConvertBOB = new JsonPath(BTCconvertBOB);
//		System.out.println(BTCconvertBOB);
		String BTCtoBOB = jpBTCConvertBOB.getString("data['quote']['BOB']['price']");
		System.out.println("1 Bitcoin = " + BTCtoBOB + " Bolivian Boliviano");

		// Converting USDT to BOB
		Response convertUSDTtoBOB = given().baseUri("https://pro-api.coinmarketcap.com/")
				.basePath("v1/tools/price-conversion").header("X-CMC_PRO_API_KEY", apiKey).queryParam("amount", "1")
				.queryParam("id", USDTetherID).queryParam("convert", "BOB").when().get();
		String USDTconvertBOB = convertUSDTtoBOB.asPrettyString();
		JsonPath jpUSDTConvertBOB = new JsonPath(USDTconvertBOB);
//		System.out.println(USDTconvertBOB);
		String USDTtoBOB = jpUSDTConvertBOB.getString("data['quote']['BOB']['price']");
		System.out.println("1 USD tether = " + USDTtoBOB + " Bolivian Boliviano");

		// Converting ETH to BOB
		Response convertETHtoBOB = given().baseUri("https://pro-api.coinmarketcap.com/")
				.basePath("v1/tools/price-conversion").header("X-CMC_PRO_API_KEY", apiKey).queryParam("amount", "1")
				.queryParam("id", ETHID).queryParam("convert", "BOB").when().get();
		String ETHconvertBOB = convertETHtoBOB.asPrettyString();
		JsonPath jpETHConvertBOB = new JsonPath(ETHconvertBOB);
//		System.out.println(ETHconvertBOB);
		String ETHtoBOB = jpETHConvertBOB.getString("data['quote']['BOB']['price']");
		System.out.println("1 Ethereum = " + ETHtoBOB + " Bolivian Boliviano");

	}

	// Function to verify Logo url, technical doc url, symbol, date added,
	// platform, tag for ethereum
	public void BackEndTask2() {
		System.out.println("TASK 2");
		String expected_ETH_logo_url = "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png";
		String expected_ETH_technical_doc_url = "https://github.com/ethereum/wiki/wiki/White-Paper";
		String expected_ETH_Symbol = "ETH";
		String expected_ETH_date_Added = "2015-08-07T00:00:00.000Z";
		String expected_platform = null;
		String expected_tag = "mineable";

		Response responseETHlogo = given().baseUri("https://pro-api.coinmarketcap.com/v1")
				.basePath("cryptocurrency/info").header("X-CMC_PRO_API_KEY", apiKey).queryParam("id", 1027).when()
				.get();
		String jsonStringETHlogo = responseETHlogo.asPrettyString();
//		System.out.println(jsonStringETHlogo);
		JsonPath jpethlogo = new JsonPath(jsonStringETHlogo);

		// Asserting logo url
		String ETHlogo = jpethlogo.getString("data['1027']['logo']");
		Assert.assertEquals("Logo URL matches", expected_ETH_logo_url, ETHlogo);
		System.out.println("Ethereum Logo url is: "+ETHlogo);

		// Asserting technical doc url
		String ETHTechnicalDoc = jpethlogo.getString("data['1027']['urls']['technical_doc']");
		ETHTechnicalDoc = ETHTechnicalDoc.replaceAll("\\[", "").replaceAll("\\]", "");
		Assert.assertEquals("Technical Doc URL matches", expected_ETH_technical_doc_url, ETHTechnicalDoc);
		System.out.println("Ethereum technical doc url is: "+ETHTechnicalDoc);

		// Asserting Ethereum symbol
		String ETHsymbol = jpethlogo.getString("data['1027']['symbol']");
		Assert.assertEquals("ETH symbol matches", expected_ETH_Symbol, ETHsymbol);
		System.out.println("Ethereum symbol is: "+ETHsymbol);

		// Asserting Ethereum date added
		String ETH_date_added = jpethlogo.getString("data['1027']['date_added']");
		Assert.assertEquals("ETH symbol matches", expected_ETH_date_Added, ETH_date_added);
		System.out.println("Ethereum date added is: "+ETH_date_added);

		// Asserting Ethereum platform
		String ETH_platform = jpethlogo.getString("data['1027']['platform']");
		Assert.assertEquals("platform matches", expected_platform, ETH_platform);
		System.out.println("Ethereum platform is: "+ETH_platform);

		// Asserting Ethereum tag
		String ETH_tag = jpethlogo.getString("data['1027']['tags']");
		ETH_tag = ETH_tag.replaceAll("\\[", "").replaceAll("\\]", "");
		Assert.assertTrue(ETH_tag.contains(expected_tag));
		System.out.println("Ethereum tag is: "+ETH_tag);
	}

	// Function to verify mineable tags for first 10 currencies and getting
	// correct names
	public void BackEndTask3() {
		System.out.println("TASK 3");
		int i = 1;
		String expected_tag = "mineable";
		Response responsecurrencies = given().baseUri("https://pro-api.coinmarketcap.com/v1")
				.basePath("cryptocurrency/info").header("X-CMC_PRO_API_KEY", apiKey)
				.queryParam("id", "1,2,3,4,5,6,7,8,9,10").when().get();
		String jsonStringfirstten = responsecurrencies.asPrettyString();
		JsonPath jpethlogo = new JsonPath(jsonStringfirstten);

		// Asserting mienable tags
		while (i <= 10) {
			String ETH_tag = jpethlogo.getString("data['" + i + "']['tags']");
			ETH_tag = ETH_tag.replaceAll("\\[", "").replaceAll("\\]", "");
			Assert.assertTrue(ETH_tag.contains(expected_tag));
			// System.out.println(ETH_tag);
			i++;
		}

		// Printing name of currencies from id 1 to 10
		JsonPath jpethlogo1 = new JsonPath(jsonStringfirstten);
		int j = 1;
		System.out.println("Currency list from 1 to 10 are : ");
		while (j <= 10) {
			String currency_name = jpethlogo1.getString("data['" + j + "']['name']");
			System.out.println(currency_name);
			j++;
		}
	}
}