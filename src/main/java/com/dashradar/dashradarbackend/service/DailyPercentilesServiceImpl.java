package com.dashradar.dashradarbackend.service;

import com.dashradar.dashradarbackend.repository.DailyPercentilesRepository;
import com.dashradar.dashradarbackend.repository.MetaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DailyPercentilesServiceImpl implements DailyPercentilesService {
    
    @Autowired
    private MetaRepository metaRepository;
    
    @Autowired
    private DailyPercentilesRepository dailyPercentilesRepository;

    @Override
    @Transactional
    public void createDailyPercentiles(long day, double percentile) {
        System.out.println("Creating daily percentiles for day "+day+" and percentile "+percentile);
        System.out.println("createDailyFeePerByteSat");
        dailyPercentilesRepository.createDailyFeePerByteSat(day, percentile);
        System.out.println("createDailyFeeSat");
        dailyPercentilesRepository.createDailyFeeSat(day, percentile);
        System.out.println("createPrivateSendFeePerByteSat");
        dailyPercentilesRepository.createPrivateSendFeePerByteSat(day, percentile);
        System.out.println("createPrivateSendFeeSat");
        dailyPercentilesRepository.createPrivateSendFeeSat(day, percentile);
        System.out.println("createCreateDenominationsFeePerByteSat");
        dailyPercentilesRepository.createCreateDenominationsFeePerByteSat(day, percentile);
        System.out.println("createCreateDenominationsFeeSat");
        dailyPercentilesRepository.createCreateDenominationsFeeSat(day, percentile);
        System.out.println("createRegularTransactionFeePerByteSat");
        dailyPercentilesRepository.createRegularTransactionFeePerByteSat(day, percentile);
        System.out.println("createRegularTransactionFeeSat");
        dailyPercentilesRepository.createRegularTransactionFeeSat(day, percentile);
        
        dailyPercentilesRepository.create_tx_size(day, percentile);
        System.out.println("create_privatesend_tx_size_size");
        dailyPercentilesRepository.create_privatesend_tx_size_size(day, percentile);
        System.out.println("create_regular_tx_size_size");
        dailyPercentilesRepository.create_regular_tx_size_size(day, percentile);
        System.out.println("create_mixing_10_0_tx_size_size");
        dailyPercentilesRepository.create_mixing_10_0_tx_size_size(day, percentile);
        System.out.println("create_mixing_1_0_tx_size_size");
        dailyPercentilesRepository.create_mixing_1_0_tx_size_size(day, percentile);
        System.out.println("create_mixing_0_1_tx_size_size");
        dailyPercentilesRepository.create_mixing_0_1_tx_size_size(day, percentile);
        System.out.println("create_mixing_0_01_tx_size_size");
        dailyPercentilesRepository.create_mixing_0_01_tx_size_size(day, percentile);
        System.out.println("create_create_denominations_tx_size");
        dailyPercentilesRepository.create_create_denominations_tx_size(day, percentile);
    }
    
    @Override
    public void createMissingDailyPercentiles(double percentile) {
        List<Long> daysMissingDailyPercentiles = dailyPercentilesRepository.daysMissingDailyPercentiles(percentile);
        daysMissingDailyPercentiles.stream().forEach(day -> createDailyPercentiles(day, percentile));
//        for (long day : daysMissingDailyPercentiles) {
//            createDailyPercentiles(day, percentile);
//        }
    }
    
    
}
