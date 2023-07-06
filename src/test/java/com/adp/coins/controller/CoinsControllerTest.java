package com.adp.coins.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.adp.coins.constants.CoinsType;
import com.adp.coins.constants.CurrencyType;
import com.adp.coins.exception.CoinsExceptionHandler;
import com.adp.coins.response.CoinsResponse;
import com.adp.coins.service.CoinsService;

/**
 * The Class CoinsControllerTest.
 */
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = { "server.coins.onecent=100", "server.coins.fivecent=100", "server.coins.tencent=100",
		"server.coins.quartercent=100" })
public class CoinsControllerTest {

	/** The mock mvc. */
	private static MockMvc mockMvc;

	/** The service. */
	private static CoinsService service = Mockito.mock(CoinsService.class);

	/** The coins. */
	private static Map<String, AtomicLong> coins = new HashMap<>();

	/** The one cent. */
	@Value("${server.coins.onecent}")
	private Long oneCent;

	/** The five cent. */
	@Value("${server.coins.fivecent}")
	private Long fiveCent;

	/** The ten cent. */
	@Value("${server.coins.tencent}")
	private Long tenCent;

	/** The quarter cent. */
	@Value("${server.coins.quartercent}")
	private Long quarterCent;

	/**
	 * Inits the.
	 */
	@BeforeAll
	static void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(new CoinsController(service))
				.setControllerAdvice(new CoinsExceptionHandler()).build();
	}

	/**
	 * Coins.
	 */
	@BeforeEach
	void coins() {
		coins.put(CoinsType.ONECENT.name(), new AtomicLong(oneCent));
		coins.put(CoinsType.FIVECENT.name(), new AtomicLong(fiveCent));
		coins.put(CoinsType.TENCENT.name(), new AtomicLong(tenCent));
		coins.put(CoinsType.QUARTERCENT.name(), new AtomicLong(quarterCent));
	}

	/**
	 * Gets the coins expect success response.
	 *
	 * @return the coins expect success response
	 * @throws Exception the exception
	 */
	@Test
	void getCoins_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		response.setOneCent(coins.get(CoinsType.ONECENT.name()).get());
		response.setFiveCent(coins.get(CoinsType.FIVECENT.name()).get());
		response.setTenCent(coins.get(CoinsType.TENCENT.name()).get());
		response.setQuarterCent(coins.get(CoinsType.QUARTERCENT.name()).get());
		Mockito.when(service.getAllCoins()).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").value(coins.get(CoinsType.ONECENT.name()).get()))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.fiveCent").value(coins.get(CoinsType.FIVECENT.name()).get()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").value(coins.get(CoinsType.TENCENT.name()).get()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent")
						.value(coins.get(CoinsType.QUARTERCENT.name()).get()));
	}

	/**
	 * Gets the coin onecent expect success response.
	 *
	 * @return the coin onecent expect success response
	 * @throws Exception the exception
	 */
	@Test
	void getCoin_onecent_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.ONECENT;
		response.setOneCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", coinsType.getName())).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").value(coins.get(coinsType.name()).get()));
	}

	/**
	 * Gets the coin fivecent expect success response.
	 *
	 * @return the coin fivecent expect success response
	 * @throws Exception the exception
	 */
	@Test
	void getCoin_fivecent_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.FIVECENT;
		response.setFiveCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", coinsType.getName())).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").value(coins.get(coinsType.name()).get()));
	}

	/**
	 * Gets the coin tencent expect success response.
	 *
	 * @return the coin tencent expect success response
	 * @throws Exception the exception
	 */
	@Test
	void getCoin_tencent_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.TENCENT;
		response.setTenCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", coinsType.getName())).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").value(coins.get(coinsType.name()).get()));
	}

	/**
	 * Gets the coin quartercent expect success response.
	 *
	 * @return the coin quartercent expect success response
	 * @throws Exception the exception
	 */
	@Test
	void getCoin_quartercent_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.QUARTERCENT;
		response.setQuarterCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", coinsType.getName())).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").value(coins.get(coinsType.name()).get()));
	}

	/**
	 * Gets the coin invalid then throw exception.
	 *
	 * @return the coin invalid then throw exception
	 * @throws Exception the exception
	 */
	@Test
	void getCoin_invalid_then_throw_exception() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.QUARTERCENT;
		response.setQuarterCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", "test")).andDo(print())
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errMsg").value("CoinName :test is invalid"));
	}

	/**
	 * Gets the coin change if invalid type then throw exception.
	 *
	 * @return the coin change if invalid type then throw exception
	 * @throws Exception the exception
	 */
	@Test
	void getCoinChange_if_InvalidType_Then_Throw_Exception() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "test")).andDo(print())
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errMsg").value("Input should be number"));
	}

	/**
	 * Gets the coin change if valid type then expect success response.
	 *
	 * @return the coin change if valid type then expect success response
	 * @throws Exception the exception
	 */
	@Test
	void getCoinChange_if_validType_Then_expect_success_response() throws Exception {
		CurrencyType currency = CurrencyType.FIVE;
		CurrencyType currencyType = CurrencyType.getByCurrency(currency.getCurrency());
		CoinsResponse response = new CoinsResponse();
		response.setQuarterCent(20L);
		response.setTenCent(0L);
		response.setFiveCent(0L);
		response.setOneCent(0L);
		Mockito.when(service.getCoinChange(currencyType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", currency.getCurrency())).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").value(20))
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").value(0));
	}

	/**
	 * Gets the all coin change if invalid type then throw exception.
	 *
	 * @return the all coin change if invalid type then throw exception
	 * @throws Exception the exception
	 */
	@Test
	void getAllCoinChange_if_InvalidType_Then_Throw_Exception() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/{currency}/mix", "test")).andDo(print())
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errMsg").value("Input should be number"));
	}

	/**
	 * Gets the all coin change if valid type then expect success response.
	 *
	 * @return the all coin change if valid type then expect success response
	 * @throws Exception the exception
	 */
	@Test
	void getAllCoinChange_if_validType_Then_expect_success_response() throws Exception {
		CurrencyType currency = CurrencyType.FIVE;
		CurrencyType currencyType = CurrencyType.getByCurrency(currency.getCurrency());
		CoinsResponse response = new CoinsResponse();
		response.setQuarterCent(20L);
		response.setTenCent(0L);
		response.setFiveCent(0L);
		response.setOneCent(0L);
		Mockito.when(service.getAllCoinChange(currencyType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.post("/{currency}/mix", currency.getCurrency())).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").value(20))
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").value(0));
	}

}
