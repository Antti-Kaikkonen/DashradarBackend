package com.dashradar.dashradarbackend.service;

public interface DailyPercentilesService {
    public void createDailyPercentiles(long day, double percentile);
    
    public void createMissingDailyPercentiles(double percentile);
}
