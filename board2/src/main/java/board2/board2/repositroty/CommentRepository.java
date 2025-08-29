package board2.board2.repositroty;

import board2.board2.entitiy.BaseEntity;
import board2.board2.entitiy.BoardEntity;
import board2.board2.entitiy.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findByBoardEntityOrderByBoardCreateTimeDesc(BoardEntity boardEntity);
}
