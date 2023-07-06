package com.adp.coins.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.adp.coins.constants.CoinsType;
import com.adp.coins.response.CoinsResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * The Utility Class CoinsUtils to handle coins functionalities
 */
@Slf4j
public final class CoinsUtils {

	/**
	 * Instantiates a new coins utils.
	 *
	 * @throws InstantiationException the instantiation exception
	 */
	private CoinsUtils() throws InstantiationException {
		throw new InstantiationException("Utility class cannot be instantiated");

	}

	/**
	 * Map to response.
	 *
	 * @param coinsMap the coins map
	 * @return the coins response
	 */
	public static CoinsResponse mapToResponse(Map<String, AtomicLong> coinsMap) {
		CoinsResponse resp = new CoinsResponse();
		if (coinsMap != null && !coinsMap.isEmpty()) {
			resp.setOneCent(coinsMap.get(CoinsType.ONECENT.getName()).get());
			resp.setFiveCent(coinsMap.get(CoinsType.FIVECENT.getName()).get());
			resp.setTenCent(coinsMap.get(CoinsType.TENCENT.getName()).get());
			resp.setQuarterCent(coinsMap.get(CoinsType.QUARTERCENT.getName()).get());
		}
		log.info("Coins Response has been retrieved successfully");
		return resp;
	}

	/**
	 * Map to response.
	 *
	 * @param coinsMap the coins map
	 * @param coinType the coin type
	 * @return the coins response
	 */
	public static CoinsResponse mapToResponse(Map<String, AtomicLong> coinsMap, CoinsType coinType) {
		CoinsResponse resp = new CoinsResponse();
		if (coinsMap != null && !coinsMap.isEmpty()) {
			switch (coinType) {
			case ONECENT:
				resp.setOneCent(coinsMap.get(CoinsType.ONECENT.getName()).get());
				break;
			case FIVECENT:
				resp.setFiveCent(coinsMap.get(CoinsType.FIVECENT.getName()).get());
				break;
			case TENCENT:
				resp.setTenCent(coinsMap.get(CoinsType.TENCENT.getName()).get());
				break;
			case QUARTERCENT:
				resp.setQuarterCent(coinsMap.get(CoinsType.QUARTERCENT.getName()).get());
				break;
			}
		}
		log.info("Coins Response has been retrieved successfully for coinType : {}", coinType.getName());
		return resp;
	}

	/**
	 * Gets the cent count.
	 *
	 * @param coinsMap        the coins map
	 * @param currencyDecimal the currency decimal
	 * @param coinType        the coin type
	 * @return the cent count
	 */
	public static ImmutablePair<Long, BigDecimal> getCentCount(Map<String, AtomicLong> coinsMap,
			BigDecimal currencyDecimal, CoinsType coinType) {
		Long centCount = 0L;
		AtomicLong centAtomicLong = coinsMap.get(coinType.getName());
		log.info("CoinType: {} and its cent: {}", coinType.getName(), centAtomicLong);
		while (centAtomicLong.get() > 0 && currencyDecimal.doubleValue() > 0) {
			centAtomicLong.decrementAndGet();
			currencyDecimal = currencyDecimal.subtract(new BigDecimal(coinType.getCent()));
			centCount++;
		}
		ImmutablePair<Long, BigDecimal> pair = new ImmutablePair<>(centCount, currencyDecimal);
		log.info("Cent Count has been calculated successfully");
		return pair;
	}

	/**
	 * Gets the all cent count.
	 *
	 * @param coinsMap the coins map
	 * @param currencyDecimal the currency decimal
	 * @param coinType the coin type
	 * @return the all cent count
	 */
	public static ImmutablePair<Long, Double> getAllCentCount(Map<String, AtomicLong> coinsMap, Double currencyDecimal,
			CoinsType coinType) {
		Long centCount = 0L;
		AtomicLong centAtomicLong = coinsMap.get(coinType.getName());
		log.info("CoinType: {} and its cent: {}", coinType.getName(), centAtomicLong);
		if (centAtomicLong.get() > 0 && currencyDecimal.doubleValue() > 0
				&& (currencyDecimal.doubleValue() - coinType.getCent()) >= 0) {
			DecimalFormat df = new DecimalFormat("0.00");
			String formate = df.format(currencyDecimal.doubleValue() - coinType.getCent());
			try {
				if (formate.endsWith(".00")) {
					currencyDecimal = Long.valueOf(formate.replace(".00", "")).doubleValue();
				} else {
					currencyDecimal = (Double) df.parse(formate);
				}
				centAtomicLong.decrementAndGet();
				centCount++;
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		ImmutablePair<Long, Double> pair = new ImmutablePair<>(centCount, currencyDecimal);
		log.info("Cent Count has been calculated successfully");
		return pair;
	}

}
