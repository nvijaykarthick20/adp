package com.adp.coins.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CoinsResponse {
	
	private Long oneCent;
	
	private Long fiveCent;
	
	private Long tenCent;
	
	private Long quarterCent;

}
