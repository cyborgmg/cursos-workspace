package br.com.siecola.aws_project02.service;

import br.com.siecola.aws_project02.model.Envelope;
import br.com.siecola.aws_project02.model.ProductEvent;
import br.com.siecola.aws_project02.model.SnsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;

@Service
public class ProductEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(ProductEventConsumer.class);

    private ObjectMapper objectMapper;

    @Autowired

    public ProductEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void receiveProductEvent(TextMessage textMessage) throws JMSException, IOException {

        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(),SnsMessage.class);

        Envelope envelope = objectMapper.readValue(snsMessage.getMessage(),Envelope.class);

        ProductEvent productEvent = objectMapper.readValue(envelope.getData(),ProductEvent.class);

        log.info("Product event received - Event: {} - ProductId: {}"
        ,envelope.getEventType()
        ,productEvent.getProductId());
    }
}
