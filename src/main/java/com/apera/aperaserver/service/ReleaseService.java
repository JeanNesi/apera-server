package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.QRelease;
import com.apera.aperaserver.model.Release;
import com.apera.aperaserver.model.ReleaseType;
import com.apera.aperaserver.repository.ReleaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


//Regra de negócio 5
/* Ao adicionar um lançamento de compra,
deverá ser feita uma verificação caso o ativo seja novo deverá criar um novo
registro na carteira, caso o ativo adicionado já exista, o sistema deverá adicionar
a quantidade ao registro já existente e recalcular o preço médio e o preço total do ativo.*/

@Service
public class ReleaseService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReleaseRepository releaseRepository;

    @Transactional
    public Release createRelease(Release entity) {
//        VerificaAtivoExistente(entity); // Adicionar verificação para salvar na table de compra ou venda também
        return releaseRepository.save(entity);
    }

//    public String VerificaAtivoExistente(Release release) {
//        if (ativoRepository.existsById(release.getAtivo().getId())) {
//            alterarQuantidadeCarteira(release.getId());
//        }
//        else {
//            salvarAtivoCarteira(release.getId());
//        }
//        return null;
//    }

    @Transactional
    public void salvarAtivoCarteira(Long ativo) {
        // Salvar ativo na carteira do usuário;
    }

    @Transactional
    public void alterarQuantidadeCarteira(Long ativo) {
        // Alterar quantidade ativo na carteira do usuário
    }

    @Transactional
    public List<Release> findAllReleasesByWallet(Long walletId) {
      return (List<Release>) releaseRepository.findAll(QRelease.release.wallet.id.eq(walletId), Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Transactional
    public Object groupReleasesByAsset(Long walletId) {
        List<Release> releases = findAllReleasesByWallet(walletId);

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

            Map<String, Object> releaseInfo = new HashMap<>();
            releaseInfo.put("stock", entry.getKey());
            releaseInfo.put("amount", totalQuantity);
            releaseInfo.put("averagePrice", weightedAverage);
            releaseInfo.put("totalValue", totalValue);

            result.add(releaseInfo);
        }

        responseData.put("stocks", result);
        responseData.put("valueApplied", valueApplied);

        return responseData;
    }


    public List<Release> buscarTodos(String filter) {
        return releaseRepository.findAll(filter, Release.class);
    }
    public Page<Release> buscarTodos(String filter, Pageable pageable) {
        return releaseRepository.findAll(filter, Release.class, pageable);
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
