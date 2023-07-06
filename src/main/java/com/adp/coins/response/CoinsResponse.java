package com.adp.coins.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Instantiates a new coins response.
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CoinsResponse {
	
	/** The one cent. */
	private Long oneCent;
	
	/** The five cent. */
	private Long fiveCent;
	
	/** The ten cent. */
	private Long tenCent;
	
	/** The quarter cent. */
	private Long quarterCent;

}
