package com.adp.coins.service.impl;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.adp.coins.config.CoinsPropConfig;
import com.adp.coins.constants.CoinsConstants;
import com.adp.coins.constants.CoinsType;
import com.adp.coins.constants.CurrencyType;
import com.adp.coins.exception.CoinsException;
import com.adp.coins.response.CoinsResponse;
import com.adp.coins.service.CoinsService;
import com.adp.coins.utils.CoinsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoinsServiceImpl implements CoinsService {

	private final CoinsPropConfig coinsConfig;

	@Override
	public CoinsResponse getAllCoins() {
		Map<String, AtomicLong> coinsMap = coinsConfig.getCoins();
		CoinsResponse response = CoinsUtils.mapToResponse(coinsMap);
		return response;
	}

	@Override
	public CoinsResponse getCoin(CoinsType coinType) {
		Map<String, AtomicLong> coinsMap = coinsConfig.getCoins();
		CoinsResponse response = CoinsUtils.mapToResponse(coinsMap, coinType);
		return response;
	}

	@Override
	public CoinsResponse getCoinChange(CurrencyType currencyType) {
		Integer currency = currencyType.getCurrency();
		if(currency > coinsConfig.getTotal()) {
			throw new CoinsException(CoinsConstants.ERR_MSG_1);
		}
		return null;
	}

}
