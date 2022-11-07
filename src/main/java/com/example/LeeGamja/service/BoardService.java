package com.example.LeeGamja.service;

import com.example.LeeGamja.dto.boardDto.BoardRequestDto;
import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.UserEntity;
import com.example.LeeGamja.repository.BoardRepository;
import com.example.LeeGamja.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<BoardListEntity> readAll() { // 전체 불러오기
        return boardRepository.findAll();
    }

    @Transactional
    public List<BoardListEntity> myReadAll(String user) { // 전체 불러오기
        UserEntity userEntity = userRepository.findByUserName(user);
        log.info("마이리드올 "+userEntity.getUserId());
        return boardRepository.findByUserEntity(userEntity);
    }
    @Transactional
    public BoardListEntity oneread(Long id){
        BoardListEntity boardListEntity = boardRepository.findById(id).orElseThrow(
                ()->{throw new RuntimeException("해당 정보가 없습니다");});

        return boardListEntity;
    }

    @Transactional
    public String Createboard (String user, BoardRequestDto boardRequestDto) { //추가
        log.info("만들기전에 이름 "+user);
        UserEntity userEntity = userRepository.findByUserName(user);
        log.info("만들기 "+userEntity.getUserId());
        boardRequestDto.setUserName(userEntity.getUserName());

        boardRequestDto.setUserEntity(userEntity);

        BoardListEntity boardListEntity = new BoardListEntity();
        boardListEntity.transfer(boardRequestDto);
        boardRepository.save(boardListEntity);
        return "작성완료";
    }
    @Transactional
    public String Boardupdate(String userName, Long id, BoardRequestDto boardRequestDto){ // 수정
        // pk타입으로 꺼내서 넣을때는 예외처리안하면 오류발생!
        BoardListEntity boardListEntity = boardRepository.findById(id).orElseThrow(() ->
        {throw new RuntimeException("해당 정보가 없습니다");});

        String user =LoginService.sessionBox.get(userName);

        if(!user.equals(boardListEntity.getUsername())){
            return "글 작성자만 수정할 수 있습니다.";
        }
        boardRequestDto.setUserEntity(boardListEntity.getUserEntity());
        boardRequestDto.setCommentEntity(boardListEntity.getCommentEntity());
        boardListEntity.transfer(boardRequestDto);
        boardRepository.save(boardListEntity);
       return "수정 완료";
    }

    @Transactional
    public String Deleteboard(String userName, Long id){// 삭제
        BoardListEntity boardListEntity= boardRepository.findById(id).orElseThrow(()-> {throw new RuntimeException("삭제 할 정보가 없습니다.");});

        String user = LoginService.sessionBox.get(userName);
        if(!user.equals(boardListEntity.getUsername())){
            return "글 작성자만 수정할 수 있습니다.";
        }
        boardRepository.deleteById(id);
        return "삭제완료";
    }
}