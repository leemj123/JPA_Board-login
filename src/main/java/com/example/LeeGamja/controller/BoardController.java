package com.example.LeeGamja.controller;


import com.example.LeeGamja.dto.boardDto.BoardRequestDto;
import com.example.LeeGamja.dto.boardDto.BoardResponseDto;
import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.UserEntity;
import com.example.LeeGamja.error.exception.UnAuthorizedException;
import com.example.LeeGamja.service.BoardService;
import com.example.LeeGamja.service.LoginService;
import com.example.LeeGamja.time.TimeGet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.ArrayList;
import java.util.List;

import static com.example.LeeGamja.error.ErrorCode.ACCESS_DENIED_EXCEPTION;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/time")
    public String timeResponse(){
        TimeGet timeGet = new TimeGet();
        return timeGet.getTime();
    }
    @GetMapping("/read") // 모두 읽어오기
    public List<BoardResponseDto> mainBoard(){;
        return boardService.readAll();
    }
    @GetMapping("/myread") // 나의 글 모두 읽어오기
    public List<BoardResponseDto> myBoard(@CookieValue(value = "userName",required = false)String userName){
        if(userName == null){
            throw new UnAuthorizedException(ACCESS_DENIED_EXCEPTION,"E0010");
        }
        String user = LoginService.sessionBox.get(userName);
        return boardService.myReadAll(user);
    }
    @GetMapping("/oneread/{id}")// 해당id 게시글 읽어오기
    public BoardResponseDto oneReadBoard(@PathVariable Long id){

        return boardService.oneRead(id);
    }
    @PostMapping("/create")
    public void createBoard(@CookieValue(value = "userName",required = true) String userName, @RequestBody BoardRequestDto boardRequestDto){
        if (userName == null){throw new UnAuthorizedException(ACCESS_DENIED_EXCEPTION,"E0010");}
        String user = LoginService.sessionBox.get(userName);
        boardService.createBoard(user, boardRequestDto);
    }

    @PutMapping("/update/{id}")
    public String updateBoard(@CookieValue(value = "userName",required = true)String userName,
                                          @PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        if (userName == null){throw new UnAuthorizedException(ACCESS_DENIED_EXCEPTION,"E0010");}
        String user = LoginService.sessionBox.get(userName);
        boardRequestDto.setUserName(user);
        boardRequestDto.setId(id);
        return boardService.boardUpdate(boardRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBoard(@CookieValue(value = "userName",required = true)String userName,
                                          @PathVariable Long id){
        if (userName == null){throw new UnAuthorizedException(ACCESS_DENIED_EXCEPTION,"E0010");}
        boardService.deleteBoard(userName,id);
    }


}
