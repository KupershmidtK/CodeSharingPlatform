package platform.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import platform.businesslayer.CodeSnippet;

public interface CodeRepository extends CrudRepository<CodeSnippet, String> {
    public Iterable<CodeSnippet> findAll(Sort sort);
}
