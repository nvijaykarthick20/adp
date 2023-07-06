package com.adp.coins.constants;

import java.util.Arrays;

import com.adp.coins.exception.CoinsException;

import lombok.Getter;

@Getter
public enum CurrencyType {

	TWO(2), FIVE(5), TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);

	private final Integer currency;

	private CurrencyType(Integer currency) {
		this.currency = currency;
	}

	public static CurrencyType getByCurrency(Integer currency) {
		return Arrays.stream(CurrencyType.values()).filter(cur -> cur.getCurrency().equals(currency)).findAny()
				.orElseThrow(() -> new CoinsException("Currency " + currency + " is invalid"));
	}
}
