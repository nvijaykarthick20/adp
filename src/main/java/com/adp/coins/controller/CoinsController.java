package com.adp.coins.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.coins.constants.CoinsType;
import com.adp.coins.constants.CurrencyType;
import com.adp.coins.response.CoinsResponse;
import com.adp.coins.service.CoinsService;

import lombok.RequiredArgsConstructor;

/**
 * API Restcontroller to handle coins functionality
 */
@RestController

/**
 * Instantiates a new coins controller.
 *
 * @param coinsService the coins service
 */
@RequiredArgsConstructor
public class CoinsController {

	/** The coins service. */
	private final CoinsService coinsService;

	/**
	 * API end point to get all coins details
	 * Gets the coins.
	 *
	 * @return the coins
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoinsResponse> getCoins() {
		CoinsResponse response = coinsService.getAllCoins();
		return ResponseEntity.ok(response);
	}
	
	/**
	 * API end point to get coins details by coin type
	 * Gets the coin.
	 *
	 * @param coin the coin
	 * @return the coin
	 */
	@GetMapping(value = "/{coin}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoinsResponse> getCoin(@PathVariable String coin) {
		CoinsType coinType = CoinsType.getByName(coin) ;
		CoinsResponse response = coinsService.getCoin(coinType);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * API end point to get the coin change for the given currency
	 * Gets the coin change.
	 *
	 * @param currency the currency
	 * @return the coin change
	 */
	@PostMapping(value = "/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoinsResponse> getCoinChange(@PathVariable Integer currency){
		CurrencyType currencyType = CurrencyType.getByCurrency(currency);
		CoinsResponse response = coinsService.getCoinChange(currencyType);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Gets the all coin change.
	 *
	 * @param currency the currency
	 * @return the all coin change
	 */
	@PostMapping(value = "/{currency}/mix", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoinsResponse> getAllCoinChange(@PathVariable Integer currency){
		CurrencyType currencyType = CurrencyType.getByCurrency(currency);
		CoinsResponse response = coinsService.getAllCoinChange(currencyType);
		return ResponseEntity.ok(response);
	}
	

}
