package com.adp.coins.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.adp.coins.config.CoinsPropConfig;
import com.adp.coins.constants.CoinsType;
import com.adp.coins.constants.CurrencyType;
import com.adp.coins.exception.CoinsException;
import com.adp.coins.response.CoinsResponse;
import com.adp.coins.service.impl.CoinsServiceImpl;

/**
 * The Class CoinsServiceImplTest.
 */
@ExtendWith(SpringExtension.class)
public class CoinsServiceImplTest {
	
	/** The service impl. */
	@InjectMocks
	private CoinsServiceImpl serviceImpl;
	
	/** The coins config. */
	@Mock
	CoinsPropConfig coinsConfig;
	
	/**
	 * Gets the all coins expect empty sucess response.
	 *
	 * @return the all coins expect empty sucess response
	 */
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
	
	/**
	 * Gets the all coins expect valid sucess response.
	 *
	 * @return the all coins expect valid sucess response
	 */
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
	
	/**
	 * Gets the coins one cent expect valid sucess response.
	 *
	 * @return the coins one cent expect valid sucess response
	 */
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
	
	/**
	 * Gets the coins five cent expect valid sucess response.
	 *
	 * @return the coins five cent expect valid sucess response
	 */
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
	
	/**
	 * Gets the coins ten cent expect valid sucess response.
	 *
	 * @return the coins ten cent expect valid sucess response
	 */
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
	
	/**
	 * Gets the coins quarter cent expect valid sucess response.
	 *
	 * @return the coins quarter cent expect valid sucess response
	 */
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
	
	/**
	 * Gets the coin change if value is high then throw exception.
	 *
	 * @return the coin change if value is high then throw exception
	 */
	@Test
	void getCoinChange_ifValue_isHigh_Then_Throw_Exception() {
		
		Mockito.when(coinsConfig.getTotal()).thenReturn(1.00);
		CoinsException coinsException = Assertions.assertThrows(CoinsException.class, () -> {			
			serviceImpl.getCoinChange(CurrencyType.HUNDRED);
		});
		assertNotNull(coinsException);
		assertEquals("Machine does not have enough coins", coinsException.getMessage());
	}
	
	/**
	 * Gets the coin change if value is valid then expect success response.
	 *
	 * @return the coin change if value is valid then expect success response
	 */
	@Test
	void getCoinChange_ifValue_isValid_Then_Expect_success_response() {
		
		Mockito.when(coinsConfig.getTotal()).thenReturn(40.00);
		Map<String, AtomicLong> coins = new HashMap<>();
		coins.put(CoinsType.ONECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.FIVECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.TENCENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.QUARTERCENT.getName(), new AtomicLong(100));
		Mockito.when(coinsConfig.getCoins()).thenReturn(coins); 
		
		CoinsResponse coinChange = serviceImpl.getCoinChange(CurrencyType.TWENTY);
		assertNotNull(coinChange);
		assertEquals(Long.valueOf(80), coinChange.getQuarterCent());
		assertEquals(Long.valueOf(0), coinChange.getTenCent());
		assertEquals(Long.valueOf(0), coinChange.getFiveCent());
		assertEquals(Long.valueOf(0), coinChange.getOneCent());
		
	}
	
	/**
	 * Gets the all coin change if value is high then throw exception.
	 *
	 * @return the all coin change if value is high then throw exception
	 */
	@Test
	void getAllCoinChange_ifValue_isHigh_Then_Throw_Exception() {
		
		Mockito.when(coinsConfig.getTotal()).thenReturn(1.00);
		CoinsException coinsException = Assertions.assertThrows(CoinsException.class, () -> {			
			serviceImpl.getAllCoinChange(CurrencyType.HUNDRED);
		});
		assertNotNull(coinsException);
		assertEquals("Machine does not have enough coins", coinsException.getMessage());
	}
	
	/**
	 * Gets the all coin change if value is valid then expect success response.
	 *
	 * @return the all coin change if value is valid then expect success response
	 */
	@Test
	void getAllCoinChange_ifValue_isValid_Then_Expect_success_response() {
		
		Mockito.when(coinsConfig.getTotal()).thenReturn(40.00);
		Map<String, AtomicLong> coins = new HashMap<>();
		coins.put(CoinsType.ONECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.FIVECENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.TENCENT.getName(), new AtomicLong(100));
		coins.put(CoinsType.QUARTERCENT.getName(), new AtomicLong(100));
		Mockito.when(coinsConfig.getCoins()).thenReturn(coins); 
		
		CoinsResponse coinChange = serviceImpl.getAllCoinChange(CurrencyType.TWENTY);
		assertNotNull(coinChange);
		assertEquals(Long.valueOf(47), coinChange.getQuarterCent());
		assertEquals(Long.valueOf(48), coinChange.getTenCent());
		assertEquals(Long.valueOf(49), coinChange.getFiveCent());
		assertEquals(Long.valueOf(100), coinChange.getOneCent());
		
	}
	

}
