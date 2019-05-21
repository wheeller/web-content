package hello.repos;

import hello.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
    List<Message> findByAuthorId(long user_id);
}
