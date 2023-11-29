package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.*;
import com.apera.aperaserver.repository.ReleaseRepository;
import com.apera.aperaserver.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReleaseService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public Release createRelease(Release entity) {
        List<Release> releases = findAllReleasesByWallet(entity.getWallet().getId());

        double totalQuantityBuy = 0;
        double totalQuantitySell = 0;

        for (Release release : releases) {
            if (release.getReleaseType().equals(ReleaseType.COMPRA)) {
                totalQuantityBuy += release.getAmount();
            } else if (release.getReleaseType().equals(ReleaseType.VENDA)) {
                totalQuantitySell += release.getAmount();
            }
        }

        double amount = totalQuantityBuy - totalQuantitySell;

        if(entity.getReleaseType().equals(ReleaseType.VENDA) && entity.getAmount() > amount){
            throw new NotFoundException("Você não possui quantidades suficentes deste ativo para a venda!");
        }

        return releaseRepository.save(entity);
    }

    @Transactional
    public List<Release> findAllReleasesByWallet(Long walletId) {
      return (List<Release>) releaseRepository.findAll(QRelease.release.wallet.id.eq(walletId), Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Transactional
    public Object groupReleasesByAsset(Long walletId) {
        List<Release> releases = findAllReleasesByWallet(walletId);
        Optional<Wallet> wallet = walletRepository.findById(walletId);

        Map<String, List<Release>> groupedReleasesByName = releases.stream()
                .collect(Collectors.groupingBy(release -> release.getAsset().getName()));


        Map<String, Object> responseData = new HashMap<>();
        List<Map<String, Object>> result = new ArrayList<>();
        double valueApplied = 0;

        for (Map.Entry<String, List<Release>> entry : groupedReleasesByName.entrySet()) {
            List<Release> releasesList = entry.getValue();
            double totalQuantity = 0;
            double totalValue = 0;

            for (Release release : releasesList) {
                if (release.getReleaseType().equals(ReleaseType.COMPRA)) {
                    totalQuantity += release.getAmount();
                    totalValue += release.getAmount() * release.getPrice();
                } else if (release.getReleaseType().equals(ReleaseType.VENDA)) {
                    totalQuantity -= release.getAmount();
                    totalValue -= release.getAmount() * release.getPrice();
                }
            }

            valueApplied += totalValue;

            double weightedAverage = totalQuantity != 0 ? totalValue / totalQuantity : 0;

            if(totalQuantity > 0){
                Map<String, Object> releaseInfo = new HashMap<>();
                releaseInfo.put("stock", entry.getKey());
                releaseInfo.put("amount", totalQuantity);
                releaseInfo.put("averagePrice", weightedAverage);
                releaseInfo.put("totalValue", totalValue);

                result.add(releaseInfo);
            }

        }

        responseData.put("stocks", result);
        responseData.put("valueApplied", valueApplied);
        responseData.put("name", wallet.get().getName());
        return responseData;
    }


    public List<Release> buscarTodos(String filter) {
        return releaseRepository.findAll(filter, Release.class);
    }
    public Page<Release> buscarTodos(String filter, Pageable pageable) {
        return releaseRepository.findAll(filter.replace(" ", "+"), Release.class, pageable);
    }

    public Optional<Release> findReleaseById(Long id) {
        return releaseRepository.findById(id);
    }


    public Release updateRelease(Long id, Release entity) {
        Optional<Release> existingReleaseOptional = releaseRepository.findById(id);
        if (existingReleaseOptional.isEmpty()) {
            throw new NotFoundException("Lançamento não encontrado!");
        }

        Release existingRelease = existingReleaseOptional.get();

        modelMapper.map(entity, existingRelease);

        return releaseRepository.save(existingRelease);
    }

    public void deleteRelease(Long id) {
        releaseRepository.deleteById(id);
    }
}
