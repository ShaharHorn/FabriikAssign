package com;

public interface CustomersScoresService {
    void processMessage(String id, String score);

    Double getAverageScore(String id);

    public void updateAverageScore(String id, Integer score);

}
