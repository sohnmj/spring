package board.main.Repository;

import board.main.Entity.BoardEntity;
import board.main.Entity.CommentEntity;
import board.main.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    public List<CommentEntity> findByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
    public List<CommentEntity> findByMemberEntityOrderByIdDesc(MemberEntity memberEntity);
}
