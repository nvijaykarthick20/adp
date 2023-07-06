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

@RestController
@RequiredArgsConstructor
public class CoinsController {

	private final CoinsService coinsService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoinsResponse> getCoins() {
		CoinsResponse response = coinsService.getAllCoins();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{coin}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoinsResponse> getCoin(@PathVariable String coin) {
		CoinsType coinType = CoinsType.getByName(coin) ;
		CoinsResponse response = coinsService.getCoin(coinType);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoinsResponse> getCoinChange(@PathVariable Integer currency){
		CurrencyType currencyType = CurrencyType.getByCurrency(currency);
		CoinsResponse response = coinsService.getCoinChange(currencyType);
		return ResponseEntity.ok(response);
	}
	

}
