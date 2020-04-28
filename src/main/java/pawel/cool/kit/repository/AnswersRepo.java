package pawel.cool.kit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pawel.cool.kit.model.Answer;

@Repository
public interface AnswersRepo extends CrudRepository<Answer, Integer> {
}
