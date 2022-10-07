package Repository;

import Entity.BoardListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsRepository extends JpaRepository<BoardListEntity, Long> {
     //page에 대해 공부
}
