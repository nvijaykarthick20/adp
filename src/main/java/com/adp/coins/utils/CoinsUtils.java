package com.adp.coins.utils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.adp.coins.constants.CoinsType;
import com.adp.coins.response.CoinsResponse;

public final class CoinsUtils {

	private CoinsUtils() throws InstantiationException {
		throw new InstantiationException("Utility class cannot be instantiated");

	}

	public static CoinsResponse mapToResponse(Map<String, AtomicLong> coinsMap) {
		CoinsResponse resp = new CoinsResponse();
		if (coinsMap != null && !coinsMap.isEmpty()) {
			resp.setOneCent(coinsMap.get(CoinsType.ONECENT.getName()).get());
			resp.setFiveCent(coinsMap.get(CoinsType.FIVECENT.getName()).get());
			resp.setTenCent(coinsMap.get(CoinsType.TENCENT.getName()).get());
			resp.setQuarterCent(coinsMap.get(CoinsType.QUARTERCENT.getName()).get());
		}
		return resp;
	}

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
		return resp;
	}

}
