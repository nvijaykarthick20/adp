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
import com.adp.coins.response.CoinsResponse;
import com.adp.coins.service.CoinsService;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = { "server.coins.onecent=100", "server.coins.fivecent=100", "server.coins.tencent=100",
		"server.coins.quartercent=100" })
public class CoinsControllerTest {

	private static MockMvc mockMvc;

	private static CoinsService service = Mockito.mock(CoinsService.class);

	private static Map<String, AtomicLong> coins = new HashMap<>();

	@Value("${server.coins.onecent}")
	private Long oneCent;

	@Value("${server.coins.fivecent}")
	private Long fiveCent;

	@Value("${server.coins.tencent}")
	private Long tenCent;

	@Value("${server.coins.quartercent}")
	private Long quarterCent;

	@BeforeAll
	static void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(new CoinsController(service)).build();
	}

	@BeforeEach
	void coins() {
		coins.put(CoinsType.ONECENT.name(), new AtomicLong(oneCent));
		coins.put(CoinsType.FIVECENT.name(), new AtomicLong(fiveCent));
		coins.put(CoinsType.TENCENT.name(), new AtomicLong(tenCent));
		coins.put(CoinsType.QUARTERCENT.name(), new AtomicLong(quarterCent));
	}

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
	
	@Test
	void getCoin_onecent_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.ONECENT;
		response.setOneCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", coinsType.getName())).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").value(coins.get(coinsType.name()).get()));
	}
	
	@Test
	void getCoin_fivecent_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.FIVECENT;
		response.setFiveCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", coinsType.getName())).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").value(coins.get(coinsType.name()).get()));
	}
	
	@Test
	void getCoin_tencent_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.TENCENT;
		response.setTenCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", coinsType.getName())).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").value(coins.get(coinsType.name()).get()));
	}
	
	@Test
	void getCoin_quartercent_expect_success_response() throws Exception {
		CoinsResponse response = new CoinsResponse();
		CoinsType coinsType = CoinsType.QUARTERCENT;
		response.setQuarterCent(coins.get(coinsType.name()).get());
		Mockito.when(service.getCoin(coinsType)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.get("/{coin}", coinsType.getName())).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.oneCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fiveCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tenCent").doesNotExist())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.quarterCent").value(coins.get(coinsType.name()).get()));
	}
	
	
}
