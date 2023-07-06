package com.adp.coins.constants;

import java.util.Arrays;

import com.adp.coins.exception.CoinsException;

import lombok.Getter;

/**
 * Gets the currency.
 *
 * @return the currency
 */
@Getter
public enum CurrencyType {

	/** The one. */
	ONE(1),
	/** The two. */
	TWO(2),
	/** The five. */
	FIVE(5),
	/** The ten. */
	TEN(10),
	/** The twenty. */
	TWENTY(20),
	/** The fifty. */
	FIFTY(50),
	/** The hundred. */
	HUNDRED(100);

	/** The currency. */
	private final Integer currency;

	/**
	 * Instantiates a new currency type.
	 *
	 * @param currency the currency
	 */
	private CurrencyType(Integer currency) {
		this.currency = currency;
	}

	/**
	 * Gets the by currency.
	 *
	 * @param currency the currency
	 * @return the by currency
	 */
	public static CurrencyType getByCurrency(Integer currency) {
		return Arrays.stream(CurrencyType.values()).filter(cur -> cur.getCurrency().equals(currency)).findAny()
				.orElseThrow(() -> new CoinsException("Currency " + currency + " is invalid"));
	}
}
