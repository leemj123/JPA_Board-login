package com.example.LeeGamja.controller;

import com.example.LeeGamja.dto.commentDto.CommentRequestDto;
import com.example.LeeGamja.service.CommentService;
import com.example.LeeGamja.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/addcomment/{id}")
    public String addComment(@CookieValue(value = "userName",required = false)String userName, @PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto){
        String user = LoginService.sessionBox.get(userName);
        return commentService.add(id, user,commentRequestDto);
    }
}
