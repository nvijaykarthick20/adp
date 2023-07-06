package com.adp.coins.constants;

import java.util.Arrays;

import com.adp.coins.exception.CoinsException;

import lombok.Getter;

/**
 * Gets the cent.
 *
 * @return the cent
 */
@Getter
public enum CoinsType {

	/** The onecent. */
	ONECENT("onecent", 0.01),
	/** The fivecent. */
	FIVECENT("fivecent", 0.05),
	/** The tencent. */
	TENCENT("tencent", 0.10),
	/** The quartercent. */
	QUARTERCENT("quartercent", 0.25);

	/** The name. */
	private String name;

	/** The cent. */
	private Double cent;

	/**
	 * Instantiates a new coins type.
	 *
	 * @param name the name
	 * @param cent the cent
	 */
	CoinsType(String name, Double cent) {
		this.name = name;
		this.cent = cent;
	}

	/**
	 * Gets the by name.
	 *
	 * @param coinName the coin name
	 * @return the by name
	 */
	public static CoinsType getByName(String coinName) {
		return Arrays.stream(CoinsType.values()).filter(type -> type.getName().equals(coinName)).findAny()
				.orElseThrow(() -> new CoinsException("CoinName :" + coinName + " is invalid"));
	}

}
