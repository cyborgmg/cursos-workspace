package br.com.devmedia.springbootthymeleaf.repository;

import br.com.devmedia.springbootthymeleaf.model.Phone;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {

}
