package com.example.LeeGamja.controller;


import com.example.LeeGamja.dto.boardDto.BoardRequestDto;
import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.service.BoardService;
import com.example.LeeGamja.service.LoginService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CookieValue;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/read") // 모두 읽어오기
    public List<BoardListEntity> mainBoard(){
        return boardService.readAll();
    }
    @GetMapping("/myread") // 나의 글 모두 읽어오기
    public List<BoardListEntity> myBoard(@CookieValue(value = "userName",required = false)String userName){
        String user = LoginService.sessionBox.get(userName);
        return boardService.myReadAll(user);
    }
    @GetMapping("/oneread/{id}")// 해당id 게시글 읽어오기
    public BoardListEntity onereadboard(@PathVariable Long id){

        return boardService.oneread(id);
    }
    @PostMapping("/create")
    public String createboard(@CookieValue(value = "userName",required = true) String userName, @RequestBody BoardRequestDto boardRequestDto){
        if (userName == null){return "로그인하세요";}
        String user = LoginService.sessionBox.get(userName);
        return boardService.Createboard(user, boardRequestDto);
    }

    @PutMapping("/updata/{id}")
    public String updateboard(@CookieValue(value = "userName",required = true)String userName,
                                          @PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        if (userName == null){return "접근 권한이 없습니다.";}

        boardRequestDto.setUserName(userName);
        return boardService.Boardupdate(userName,id,boardRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteboard(@CookieValue(value = "userName",required = true)String userName,
                                          @PathVariable Long id){
        if (userName == null){return "접근 권한이 없습니다.";}
        return boardService.Deleteboard(userName,id);
    }

}
