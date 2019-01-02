package com.mpe.common.util;

import org.hibernate.stat.Statistics;

public class HibernateStatisticsStopWatch {
	
	Statistics stats;
	
	// some interesting metrics
    long queryExecutions;
    long transactions;
    long entityLoads;
    long connects;
    long time;

    public HibernateStatisticsStopWatch() {
        this.stats = HibernateUtil.currentSession("hibernate").getSessionFactory().getStatistics();
    }

    public void start() {
        queryExecutions = -stats.getQueryExecutionCount();
        transactions = -stats.getTransactionCount();
        entityLoads = -stats.getEntityLoadCount();
        connects = -stats.getConnectCount();
        time = -System.currentTimeMillis();
    }

    public void stop() {
        queryExecutions += stats.getQueryExecutionCount();
        transactions += stats.getTransactionCount();
        entityLoads += stats.getEntityLoadCount();
        connects += stats.getConnectCount();
        time += System.currentTimeMillis();
    }

    // getter methods for various delta metrics

    public String toString() {
        return "Stats" 
            + "[ queries=" + queryExecutions 
            + ", xactions=" + transactions 
            + ", loads=" + entityLoads 
            + ", connects=" + connects 
            + ", time=" + time + " ]";
    }

}
