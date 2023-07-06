package com.adp.coins.service;

import com.adp.coins.constants.CoinsType;
import com.adp.coins.constants.CurrencyType;
import com.adp.coins.response.CoinsResponse;

public interface CoinsService {

	CoinsResponse getAllCoins();

	CoinsResponse getCoin(CoinsType coinType);

	CoinsResponse getCoinChange(CurrencyType currencyType);

}
