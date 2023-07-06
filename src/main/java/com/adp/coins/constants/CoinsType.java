package com.adp.coins.constants;

import java.util.Arrays;

import com.adp.coins.exception.CoinsException;

import lombok.Getter;

@Getter
public enum CoinsType {

	ONECENT("onecent", 0.01), FIVECENT("fivecent", 0.05), TENCENT("tencent", 0.10), QUARTERCENT("quartercent", 0.25);

	private String name;

	private Double cent;

	CoinsType(String name, Double cent) {
		this.name = name;
		this.cent = cent;
	}

	public static CoinsType getByName(String coinName) {
		return Arrays.stream(CoinsType.values()).filter(type -> type.getName().equals(coinName)).findAny()
				.orElseThrow(() -> new CoinsException("CoinName :"+ coinName +" is invalid"));
	}

}
