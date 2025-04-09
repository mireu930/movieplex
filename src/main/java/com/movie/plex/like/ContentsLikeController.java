package com.movie.plex.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ContentsLikeController {
		
		@Autowired
		private ContentsLikeService contentsLikeService;
		
		//좋아요 토글 (추가/삭제)
		@PostMapping("/reviewNest/toggleLike")
		public ResponseEntity<Map<String, Object>> toggleLike(@RequestBody ContentsLikeDTO contentsLikeDTO) {
				Map<String, Object> response = new HashMap<String, Object>();
				
				try {
					//좋아요 토글 (추가/삭제)
					boolean liked = contentsLikeService.toggleLike(contentsLikeDTO);
					response.put("liked", liked);
					response.put("message", liked ? "좋아요 추가" : "좋아요 취소");
					return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
					
				} catch (Exception e) {
					//예외처리
					response.put("movieliked", false);
					response.put("message", "오류: "+ e.getMessage());
					return new ResponseEntity<Map<String,Object>> (response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
		
}
