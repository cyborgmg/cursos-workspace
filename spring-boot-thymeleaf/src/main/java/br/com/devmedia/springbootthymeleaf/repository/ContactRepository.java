package br.com.devmedia.springbootthymeleaf.repository;

import br.com.devmedia.springbootthymeleaf.model.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

}
