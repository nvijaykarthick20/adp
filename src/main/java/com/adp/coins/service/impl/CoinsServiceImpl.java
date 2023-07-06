package com.adp.coins.service.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.MDC;
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
import lombok.extern.slf4j.Slf4j;


/**
 * The Service Class CoinsServiceImpl to handle all coins functionality
 */
@Service

/**
 * Instantiates a new coins service impl.
 *
 * @param coinsConfig the coins config
 */
@RequiredArgsConstructor
@Slf4j
public class CoinsServiceImpl implements CoinsService {

	/** The coins config. */
	private final CoinsPropConfig coinsConfig;

	/**
	 * Gets the all coins.
	 *
	 * @return the all coins
	 */
	@Override
	public CoinsResponse getAllCoins() {
		Map<String, AtomicLong> coinsMap = coinsConfig.getCoins();
		CoinsResponse response = CoinsUtils.mapToResponse(coinsMap);
		log.info("All coins details has been retrieved successfully");
		return response;
	}

	/**
	 * Gets the coin.
	 *
	 * @param coinType the coin type
	 * @return the coin
	 */
	@Override
	public CoinsResponse getCoin(CoinsType coinType) {
		Map<String, AtomicLong> coinsMap = coinsConfig.getCoins();
		CoinsResponse response = CoinsUtils.mapToResponse(coinsMap, coinType);
		log.info("Get coin details has been retrieved successfully");
		return response;
	}

	/**
	 * Gets the coin change.
	 *
	 * @param currencyType the currency type
	 * @return the coin change
	 */
	@Override
	public CoinsResponse getCoinChange(CurrencyType currencyType) {
		CoinsResponse resp = new CoinsResponse();
		BigDecimal currencyDecimal = new BigDecimal(currencyType.getCurrency());
		Double total = coinsConfig.getTotal();
		log.info("Available coins: {}", total);
		if (currencyDecimal.doubleValue() > total) {
			throw new CoinsException(CoinsConstants.ERR_MSG_1);
		}

		ImmutablePair<Long, BigDecimal> quarterCentCount = CoinsUtils.getCentCount(coinsConfig.getCoins(), currencyDecimal,
				CoinsType.QUARTERCENT);
		resp.setQuarterCent(quarterCentCount.getLeft());
		currencyDecimal = quarterCentCount.getRight();
		log.info("Currency changed to quarter cent coins successfully");

		ImmutablePair<Long, BigDecimal> tenCentCount = CoinsUtils.getCentCount(coinsConfig.getCoins(), currencyDecimal, CoinsType.TENCENT);
		resp.setTenCent(tenCentCount.getLeft());
		currencyDecimal = tenCentCount.getRight();
		log.info("Currency changed to ten cent coins successfully");

		ImmutablePair<Long, BigDecimal> fiveCentCount = CoinsUtils.getCentCount(coinsConfig.getCoins(), currencyDecimal, CoinsType.FIVECENT);
		resp.setFiveCent(fiveCentCount.getLeft());
		currencyDecimal = fiveCentCount.getRight();
		log.info("Currency changed to five cent coins successfully");

		ImmutablePair<Long, BigDecimal> oneCentCount = CoinsUtils.getCentCount(coinsConfig.getCoins(), currencyDecimal, CoinsType.ONECENT);
		resp.setOneCent(oneCentCount.getLeft());
		currencyDecimal = oneCentCount.getRight();
		log.info("Currency changed to one cent coins successfully");

		return resp;
	}

}
