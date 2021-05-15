package com.wallet.manager.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wallet.manager.dto.WalletDTO;
import com.wallet.manager.model.Wallet;

@Mapper
public interface WalletToWalletDTO {
	WalletToWalletDTO INSTANCE = Mappers.getMapper(WalletToWalletDTO.class);
	
	WalletDTO toWalletDTO(Wallet wallet);
	List<WalletDTO> toWalletDTOList(List<Wallet> walletList);

}
