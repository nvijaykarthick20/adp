package com.adp.coins.service;

import com.adp.coins.constants.CoinsType;
import com.adp.coins.constants.CurrencyType;
import com.adp.coins.response.CoinsResponse;

/**
 * The Interface CoinsService.
 */
public interface CoinsService {

	/**
	 * Gets the all coins.
	 *
	 * @return the all coins
	 */
	CoinsResponse getAllCoins();

	/**
	 * Gets the coin.
	 *
	 * @param coinType the coin type
	 * @return the coin
	 */
	CoinsResponse getCoin(CoinsType coinType);

	/**
	 * Gets the coin change.
	 *
	 * @param currencyType the currency type
	 * @return the coin change
	 */
	CoinsResponse getCoinChange(CurrencyType currencyType);

}
