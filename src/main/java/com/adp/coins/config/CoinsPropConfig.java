package com.adp.coins.config;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.adp.coins.constants.CoinsType;

import lombok.Getter;

/**
 * The Class CoinsPropConfig.
 */
@Component
@ConfigurationProperties(prefix = "server")
public class CoinsPropConfig {

	/**
	 * Gets the coins.
	 *
	 * @return the coins
	 */
	@Getter
	private Map<String, AtomicLong> coins;

	/**
	 * Sets the coins.
	 *
	 * @param coins the coins
	 */
	public void setCoins(Map<String, Long> coins) {
		this.coins = coins.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> new AtomicLong(e.getValue())));
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public Double getTotal() {
		Double total = 0.00;
		for (Map.Entry<String, AtomicLong> keyVal : coins.entrySet()) {
			BigDecimal count = new BigDecimal(keyVal.getValue().get());
			BigDecimal coin = new BigDecimal(CoinsType.getByName(keyVal.getKey()).getCent()); 
			total = total + count.multiply(coin).doubleValue();
	    }
		
		return total;
	}

}
