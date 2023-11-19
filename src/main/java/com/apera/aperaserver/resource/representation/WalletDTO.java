package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.User;
import com.apera.aperaserver.model.Wallet;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class WalletDTO {
    private Long id;
    private String name;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static WalletDTO fromEntity(Wallet wallet) {
        WalletDTO dto = new WalletDTO();
        dto.setId(wallet.getId());
        dto.setName(wallet.getName());
        dto.setUser(wallet.getUser());
        return dto;
    }

    public Wallet toEntity() {
        Wallet wallet = new Wallet();
        wallet.setId(this.getId());
        wallet.setName(this.getName());
        wallet.setUser(this.getUser());
        return wallet;
    }

    public static List<WalletDTO> fromEntity(List<Wallet> wallets) {
        return wallets.stream().map(wallet -> fromEntity(wallet)).collect(Collectors.toList());
    }

    public static Page<WalletDTO> fromEntity(Page<Wallet> wallets) {
        List<WalletDTO> walletsFind = wallets.stream().map(wallet -> fromEntity(wallet)).collect(Collectors.toList());
        Page<WalletDTO> walletDTO = new PageImpl<>(walletsFind, wallets.getPageable(), wallets.getTotalElements());
        return walletDTO;
    }
}
