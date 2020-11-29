package com;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class CustomersScoresServiceImpl implements CustomersScoresService{
    ConcurrentHashMap<String, ArrayList<Customer>> customers = new ConcurrentHashMap<>();
    ConcurrentHashMap<String,Integer> cunstomersScores = new ConcurrentHashMap<>();
    Timer t = new Timer();

    public CustomersScoresServiceImpl() {
        t.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        deleteExpiredResults();
                    }
                }, 1, 1);
    }


    private void deleteExpiredResults() {
        Enumeration<String> keys = customers.keys();
        Boolean isRemoved = false;
        while(keys.hasMoreElements())
        {
            String key = keys.nextElement();
            isRemoved=false;
                ArrayList<Customer> customerScores = customers.get(key);
            for (Customer c : customerScores) {
                if (c != null) {
                    if (c.getExpiryDate().isBefore(LocalDateTime.now())) {
                        updateAverageScore(c.getId().toString(), c.getScore() * -1);
                        customers.remove(c.getId());
                        isRemoved = true;
                    }
                    if (isRemoved == false)
                        break;
                }
            }

        }
    }

    public void processMessage(String id,String score)
    {
        Customer customer = new Customer(Integer.parseInt(id),Integer.parseInt(score));
        if(customers.get(id) == null) {
          ArrayList<Customer> scores = customers.get(id);
          scores = new ArrayList<Customer>();
          customers.put(id,scores);
        }
         customers.get(id).add(customer);
         updateAverageScore(id, Integer.parseInt(score));
    }

    public Double getAverageScore(String id) {
        Double averageScore = 0.0;
        if (customers.get(id) != null && cunstomersScores.get(id)!=null) {
            return Double.valueOf((double)cunstomersScores.get(id)/customers.get(id).size());
        }
        return Double.valueOf(0);
    }

    public void updateAverageScore(String id,Integer score) {
        cunstomersScores.put(id,(cunstomersScores.getOrDefault(id,  0)+score));
    }


}
