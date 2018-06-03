package com.dashradar.dashradarbackend.service;

import com.dashradar.dashradarbackend.domain.dto.TransactionClusterData;
import com.dashradar.dashradarbackend.repository.MultiInputHeuristicClusterRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MultiInputHeuristicClusterServiceImpl implements MultiInputHeuristicClusterService {
    
    @Autowired
    private MultiInputHeuristicClusterRepository multiInputHeuristicClusterRepository;
    

    @Transactional
    @Override
    public void clusteerizeBlock(long height) {
        
        List<String> blockNonMixingTransactions = multiInputHeuristicClusterRepository.blockNonMixingTransactions(height);
        for (String tx : blockNonMixingTransactions) {
            clusterizeTransaction(tx);
        }
        
    }
    
    @Transactional
    @Override
    public void clusterizeTransaction(String txid) {
        
        List<TransactionClusterData> addressClustersOfTransaction = multiInputHeuristicClusterRepository.addressClustersOfTransaction(txid);
        Optional<TransactionClusterData> biggestCluster = addressClustersOfTransaction.stream().filter(e -> e.clusterId != null && e.clusterSize != null).max((a, b) -> Long.compare(a.clusterSize, b.clusterSize));
        
        if (biggestCluster.isPresent()) {
            long biggestClusterId = biggestCluster.get().clusterId;
            Set<String> nonClusterAddresses = addressClustersOfTransaction.stream()
                    .filter(e -> e.clusterId == null) //|| e.clusterId != biggestClusterId)
                    .flatMap(e -> e.addresses.stream()).collect(Collectors.toSet());
            long biggestClusterSize = (long)biggestCluster.get().clusterSize;
            long newClusterSize = biggestClusterSize+nonClusterAddresses.size();
            Set<Long> clustersToMerge = addressClustersOfTransaction.stream().map(e -> e.clusterId).filter(e -> e != null && e != biggestClusterId).collect(Collectors.toSet());
            //System.out.println("removing "+clustersToRemove.size()+" clusters");
            for (Long clusterToRemove : clustersToMerge) {
                multiInputHeuristicClusterRepository.mergeClusterToCluster(clusterToRemove, biggestClusterId);
                //multiInputHeuristicClusterRepository.detachDeleteCluster(clusterToRemove);
            }
            //System.out.println("adding "+addressesToAdd.size()+" addresses");
            for (String addressToAdd : nonClusterAddresses) {
                multiInputHeuristicClusterRepository.addAddressToCluster(biggestClusterId, addressToAdd);
            }
        } else {
            //System.out.println("biggest not present..");
            Set<String> addresses = addressClustersOfTransaction.stream().flatMap(e -> e.addresses.stream()).collect(Collectors.toSet());
            if (addresses.size() > 1) {//Don't create empty clusters or clusters with one address
                multiInputHeuristicClusterRepository.createClusterWithAddresses(addresses);
            }
        }
        
    }
    
}
