package com.example.LeeGamja.service;

import com.example.LeeGamja.dto.boardDto.BoardRequestDto;
import com.example.LeeGamja.dto.boardDto.BoardResponseDto;
import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.UserEntity;
import com.example.LeeGamja.error.exception.ForbiddenException;
import com.example.LeeGamja.error.exception.NotFoundException;
import com.example.LeeGamja.repository.BoardRepository;
import com.example.LeeGamja.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.LeeGamja.error.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<BoardResponseDto> readAll() { // 전체 불러오기
        List<BoardListEntity> entityList = boardRepository.findAllWithBoardListEntityUsingJoin();
        return entityList.stream().map(BoardResponseDto::new).sorted().collect(Collectors.toList());
    }

    @Transactional
    public List<BoardResponseDto> myReadAll(String userName) { // 전체 불러오기
        UserEntity userEntity = userRepository.findByUserName(userName);
        //접근권한 확인
        if (userEntity ==null) {throw new ForbiddenException(FORBIDDEN_EXCEPTION,"E0020");}
        List<BoardListEntity> entityList = boardRepository.findAllByUserEntity(userName);
        return entityList.stream().map(BoardResponseDto:: new).sorted().collect(Collectors.toList());
    }
    @Transactional
    public BoardResponseDto oneRead(Long id){
        BoardListEntity boardListEntity = boardRepository.findByBoardId(id);
        if (boardListEntity == null){throw new NotFoundException(NONEXISTENT_BOARD_EXCEPTION,"E0041");}
        BoardResponseDto boardResponseDto = new BoardResponseDto(boardListEntity);
        return boardResponseDto;
    }

    @Transactional
    public String createBoard (String user, BoardRequestDto boardRequestDto) { //추가

        UserEntity userEntity = userRepository.findByUserName(user);

        boardRequestDto.setUserName(userEntity.getUserName());

        boardRequestDto.setUserEntity(userEntity);

        BoardListEntity boardListEntity = new BoardListEntity();
        boardListEntity.transfer(boardRequestDto);
        boardRepository.save(boardListEntity);
        return "작성완료";
    }
    @Transactional
    public String boardUpdate(BoardRequestDto boardRequestDto){ // 수정
        // pk타입으로 꺼내서 넣을때는 예외처리안하면 오류발생!
        BoardListEntity boardListEntity = boardRepository.findById(boardRequestDto.getId()).orElseThrow(() ->
        {throw new NotFoundException(NONEXISTENT_BOARD_EXCEPTION,"E0041");});

        if(!(boardRequestDto.getUserName()).equals(boardListEntity.getUsername())){
            throw new ForbiddenException(FORBIDDEN_EXCEPTION,"E0020");
        }
        boardRequestDto.setUserEntity(boardListEntity.getUserEntity());
        boardListEntity.transfer(boardRequestDto);
       return "수정 완료";
    }

    @Transactional
    public String deleteBoard(String userName, Long id){// 삭제
        BoardListEntity boardListEntity= boardRepository.findById(id).
                orElseThrow(()-> {throw new NotFoundException(NONEXISTENT_BOARD_EXCEPTION,"E0041");});

        String user = LoginService.sessionBox.get(userName);
        if(!user.equals(boardListEntity.getUsername())){
            throw new ForbiddenException(FORBIDDEN_EXCEPTION,"E0020");
        }
        boardRepository.deleteById(id);
        return "삭제완료";
    }
}