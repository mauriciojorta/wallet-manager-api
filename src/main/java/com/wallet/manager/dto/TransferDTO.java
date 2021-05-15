package com.wallet.manager.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class TransferDTO {
    @ApiModelProperty(value = "Hash of the sender wallet", required = true, example = "b701dc7d38a8e3f5f05330c72074d60c8d0596711acd12bc72456dee4ea7922b")
    @NotBlank(message = "Surname can not be null")	
	private String senderHash;
    @ApiModelProperty(value = "R of the customer", required = true, example = "506c8b894ea86a36b20ca47e155f687734dee3c1b701a136478a09d5b5497dd4")
    @NotBlank(message = "Surname can not be null")
	private String recipientHash;
    @ApiModelProperty(value = "Funds to transfer", required = true, example = "100.00")
	@Min(value = 0, message = "Min transfer funds amount is 0.00")
	private BigDecimal funds;
	
	public String getSenderHash() {
		return senderHash;
	}
	public void setSenderHash(String senderHash) {
		this.senderHash = senderHash;
	}
	public String getRecipientHash() {
		return recipientHash;
	}
	public void setRecipientHash(String recipientHash) {
		this.recipientHash = recipientHash;
	}
	public BigDecimal getFunds() {
		return funds;
	}
	public void setFunds(BigDecimal funds) {
		this.funds = funds;
	}	
	
}
