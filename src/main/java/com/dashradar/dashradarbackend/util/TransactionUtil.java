package com.dashradar.dashradarbackend.util;

import com.dashradar.dashradarbackend.domain.Transaction;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionUtil {
    
    public static final long LAST_LEGACY_COLLATERAL_OUTPUT_BLOCK = 616520l;//roughly

    public static final long COLLATERAL_PAYMENT = 10000l;
    
    public static final long COLLATERAL_PAYMENT_LEGACY2 = 100000l;
    
    public static final long COLLATERAL_PAYMENT_LEGACY = 1000000l;
    
    public static final long COLLATERAL_OUTPUT = 40000l;
    
    public static final long COLLATERAL_OUTPUT_LEGACY2 = 400000l;

    public static final long COLLATERAL_OUTPUT_LEGACY = 4000000l;
    
    public static final long DENOM_0_001 = 100001l;

    public static final long DENOM_0_01 = 1000010l;

    public static final long DENOM_0_1 = 10000100l;

    public static final long DENOM_100_0 = 10000100000l;

    public static final long DENOM_10_0 = 1000010000l;

    public static final long DENOM_1_0 = 100001000l;

    public static List<Long> PRIVATESEND_DENOMINATIONS = Arrays.asList(DENOM_100_0, DENOM_10_0, DENOM_1_0, DENOM_0_1, DENOM_0_01, DENOM_0_001);

    public static int getPsType(Transaction transaction) {
        if (isMixingTransaction(transaction)) {
            if (transaction.getVout().get(0).getValueSat() == DENOM_0_001) {
                return Transaction.PRIVATE_SEND_MIXING_0_001;
            }
            if (transaction.getVout().get(0).getValueSat() == DENOM_0_01) {
                return Transaction.PRIVATE_SEND_MIXING_0_01;
            }
            if (transaction.getVout().get(0).getValueSat() == DENOM_0_1) {
                return Transaction.PRIVATE_SEND_MIXING_0_1;
            }
            if (transaction.getVout().get(0).getValueSat() == DENOM_1_0) {
                return Transaction.PRIVATE_SEND_MIXING_1_0;
            }
            if (transaction.getVout().get(0).getValueSat() == DENOM_10_0) {
                return Transaction.PRIVATE_SEND_MIXING_10_0;
            }
            if (transaction.getVout().get(0).getValueSat() == DENOM_100_0) {
                return Transaction.PRIVATE_SEND_MIXING_100_0;
            }
        } else if (isCreateDenominationsTransaction(transaction)) {
            return Transaction.PRIVATE_SEND_CREATE_DENOMINATIONS;
        } else if (isPrivateSendTransaction(transaction)) {
            return Transaction.PRIVATE_SEND;
        }
        return Transaction.PRIVATE_SEND_NONE;
    }
    
    public static boolean isCollateralPaymentOutput(long valueSat) {
        if (valueSat % COLLATERAL_PAYMENT == 0 && valueSat < COLLATERAL_OUTPUT && valueSat > 0) return true;
        if (valueSat % COLLATERAL_PAYMENT_LEGACY == 0 && valueSat < COLLATERAL_OUTPUT_LEGACY && valueSat > 0) return true;
        if (valueSat % COLLATERAL_PAYMENT_LEGACY2 == 0 && valueSat < COLLATERAL_OUTPUT_LEGACY2 && valueSat > 0) return true;
        return false;
    }

    public static boolean isMakeCollateralInputsOutput(long valueSat) {
        return valueSat == COLLATERAL_OUTPUT ||valueSat == COLLATERAL_OUTPUT_LEGACY || valueSat == COLLATERAL_OUTPUT_LEGACY2;
    }

    public static boolean isDenomination(long valueSat) {
        return PRIVATESEND_DENOMINATIONS.stream().anyMatch(denom -> denom.equals(valueSat));
    }

    public static boolean isMixingTransaction(Transaction transaction) {
        if (transaction.getVin().size() != transaction.getVin().size()) {
            return false;
        }
        if (transaction.getVin().size() < 3) {
            return false;
        }
        long denom = transaction.getVout().get(0).getValueSat();
        if (!isDenomination(denom)) {
            return false;
        }
        return allInputsSameDenom(transaction, denom) && allOutputsSameDenom(transaction, denom);
    }

    public static boolean isPrivateSendTransaction(Transaction transaction) {
        if (transaction.getVout().size() != 1) {
            return false;
        }
        return transaction.getVin().stream().allMatch(vin -> vin.getSource() != null && isDenomination(vin.getSource().getValueSat()));
    }

    private static boolean allInputsSameDenom(Transaction transaction, long denom) {
        return transaction.getVin()
                .stream()
                //.map(input -> input.getSource().getValueSat())
                .allMatch(input -> input.getSource() != null && input.getSource().getValueSat() == denom);
    }

    private static boolean allOutputsSameDenom(Transaction transaction, long denom) {
        return transaction.getVout()
                .stream()
                .map(output -> output.getValueSat())
                .allMatch(valueSat -> valueSat == denom);
    }

    private static boolean isCreateDenominationsTransaction(Transaction transaction) {
        //All outputs except change address should be denominations
        if (transaction.getVout().size() < 2) {
            return false;
        }

        List<Long> nonDenominations = transaction.getVout().stream().map(vout -> vout.getValueSat()).filter(valueSat -> !isDenomination(valueSat)).collect(Collectors.toList());
        if (nonDenominations.size() == transaction.getVout().size()) {
            return false;//Must contain at least one denomination
        }
        if (nonDenominations.size() == 1) {
            return true;
        }
        if (nonDenominations.size() == 2) {
            return nonDenominations.stream().anyMatch(denom -> isMakeCollateralInputsOutput(denom));
        }
        return false;
    }

}
