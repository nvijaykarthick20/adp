package com.adp.coins.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.adp.coins.config.CoinsPropConfig;
import com.adp.coins.constants.CoinsType;
import com.adp.coins.response.CoinsResponse;
import com.adp.coins.service.impl.CoinsServiceImpl;

@ExtendWith(SpringExtension.class)
public class CoinsServiceImplTest {
	
	@InjectMocks
	private CoinsServiceImpl serviceImpl;
	
	@Mock
	CoinsPropConfig coinsConfig;
	
	@Test
	void getAllCoins_expect_empty_sucess_response() {
		Mockito.when(coinsConfig.getCoins()).thenReturn(null); 
		CoinsResponse allCoins = serviceImpl.getAllCoins();
		assertNotNull(allCoins);
		assertNull(allCoins.getOneCent());
		assertNull(allCoins.getFiveCent());
		assertNull(allCoins.getTenCent());
		assertNull(allCoins.getQuarterCent());
	}
	
	@Test
	void getAllCoins_expect_valid_sucess_response() {
		Map<String, AtomicLong> coins = new HashMap<>();
		coins.put(CoinsType.ONECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.FIVECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.TENCENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.QUARTERCENT.getName(), new AtomicLong(100));
		Mockito.when(coinsConfig.getCoins()).thenReturn(coins); 
		CoinsResponse allCoins = serviceImpl.getAllCoins();
		assertNotNull(allCoins);
		assertEquals(Long.valueOf(100), allCoins.getOneCent());
		assertEquals(Long.valueOf(100), allCoins.getFiveCent());
		assertEquals(Long.valueOf(100), allCoins.getTenCent());
		assertEquals(Long.valueOf(100), allCoins.getQuarterCent());
	}
	
	@Test
	void getCoins_oneCent_expect_valid_sucess_response() {
		Map<String, AtomicLong> coins = new HashMap<>();
		coins.put(CoinsType.ONECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.FIVECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.TENCENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.QUARTERCENT.getName(), new AtomicLong(100));
		Mockito.when(coinsConfig.getCoins()).thenReturn(coins); 
		CoinsResponse allCoins = serviceImpl.getCoin(CoinsType.ONECENT);
		assertNotNull(allCoins);
		assertEquals(Long.valueOf(100), allCoins.getOneCent());
	}
	
	@Test
	void getCoins_fiveCent_expect_valid_sucess_response() {
		Map<String, AtomicLong> coins = new HashMap<>();
		coins.put(CoinsType.ONECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.FIVECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.TENCENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.QUARTERCENT.getName(), new AtomicLong(100));
		Mockito.when(coinsConfig.getCoins()).thenReturn(coins); 
		CoinsResponse allCoins = serviceImpl.getCoin(CoinsType.FIVECENT);
		assertNotNull(allCoins);
		assertEquals(Long.valueOf(100), allCoins.getFiveCent());
	}
	
	@Test
	void getCoins_tenCent_expect_valid_sucess_response() {
		Map<String, AtomicLong> coins = new HashMap<>();
		coins.put(CoinsType.ONECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.FIVECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.TENCENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.QUARTERCENT.getName(), new AtomicLong(100));
		Mockito.when(coinsConfig.getCoins()).thenReturn(coins); 
		CoinsResponse allCoins = serviceImpl.getCoin(CoinsType.TENCENT);
		assertNotNull(allCoins);
		assertEquals(Long.valueOf(100), allCoins.getTenCent());
	}
	
	@Test
	void getCoins_quarterCent_expect_valid_sucess_response() {
		Map<String, AtomicLong> coins = new HashMap<>();
		coins.put(CoinsType.ONECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.FIVECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.TENCENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.QUARTERCENT.getName(), new AtomicLong(100));
		Mockito.when(coinsConfig.getCoins()).thenReturn(coins); 
		CoinsResponse allCoins = serviceImpl.getCoin(CoinsType.QUARTERCENT);
		assertNotNull(allCoins);
		assertEquals(Long.valueOf(100), allCoins.getQuarterCent());
	}

}
