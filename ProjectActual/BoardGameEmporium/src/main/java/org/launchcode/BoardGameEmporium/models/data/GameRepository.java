package org.launchcode.BoardGameEmporium.models.data;



import org.springframework.data.repository.CrudRepository;
import org.launchcode.BoardGameEmporium.models.Game;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GameRepository extends CrudRepository<Game, Integer> {

}
