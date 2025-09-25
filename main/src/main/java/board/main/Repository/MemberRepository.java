package board.main.Repository;

import board.main.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    public Optional<MemberEntity> findByUserId(String userId);
}
