package br.com.devmedia.springbootthymeleaf.repository;

import br.com.devmedia.springbootthymeleaf.model.Suggestion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long> {

}
