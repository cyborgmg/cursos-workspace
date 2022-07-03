package com.baeldung.springsoap.client;

import com.baeldung.springsoap.client.gen.Country;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.springframework.oxm.Marshaller;

@Configuration
public class CountryClientConfig {

    @Bean
    public Marshaller marshaller() {
        /*
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setContextPath("com.baeldung.springsoap.client.gen");
            return marshaller;*/
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
            Marshaller marshaller;
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            return marshaller;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public CountryClient countryClient(Marshaller marshaller) {
            CountryClient client = new CountryClient();
            client.setDefaultUri("http://localhost:8080/ws");
            client.setMarshaller(marshaller);
            client.setUnmarshaller(marshaller);
            return client;
    }
}