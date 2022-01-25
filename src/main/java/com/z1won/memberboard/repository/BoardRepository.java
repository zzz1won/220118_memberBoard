package com.z1won.memberboard.repository;

import com.z1won.memberboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    BoardEntity findByBoardTitle(String boardTitle);

    // native query
    // jsql -- query dsl

    // jpql은 jpa를 보조해쥬는 장치
    // 반드시 테이블에 대한 약칭을 써야 함, BoardEntity as b <-
    // 쿼리를 작성한다고해서 DB에 명명한 기준이 아닌, Entity에 선언한 것을 기준으로 가져온다.
    //이걸 하는 이유: 수정시간이 바뀌지 않게 하기 위해!
    @Modifying
    @Query(value="update BoardEntity b set b.boardHits = b.boardHits+1 where b.id= :boardId")
    int boardHits(@Param("boardId") Long boardId);


    List<BoardEntity> findByBoardTitleContaining(String keyword);

    List<BoardEntity> findByBoardContentsContaining(String keyword);

    List<BoardEntity> findByMemberEmailContaining(String keyword);
}
