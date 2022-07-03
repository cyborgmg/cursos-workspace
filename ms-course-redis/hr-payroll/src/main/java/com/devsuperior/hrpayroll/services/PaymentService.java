package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feingnclientes.WorkerFeingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeingClient workerFeingClient;
/*

    @Autowired
    private RestTemplate restTemplate;

    @Value("${hr-worker.host}")
    private String hrWorkerHost;

    public Payment getPayment(long workerId, int days){

        Map<String, String> params = new HashMap<>();
        params.put("id", ""+workerId);

        Worker worker = restTemplate.getForObject(hrWorkerHost+"/workers/{id}", Worker.class,  params);

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
*/

    public Payment getPayment(long workerId, int days){

        Worker worker = workerFeingClient.findById(workerId).getBody();

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

}
