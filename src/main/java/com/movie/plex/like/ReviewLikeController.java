package com.movie.plex.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.plex.users.UserDTO;

@RestController
public class ReviewLikeController {
		
		@Autowired
		private ReviewLikeService reviewLikeService;
		
		//좋아요 토글 (추가/삭제)
		@PostMapping("/reviewNest/toggleReviewLike")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> toggleReviewLike(@RequestBody ReviewLikeDTO reviewLikeDTO,HttpSession session) {
				Map<String, Object> response = new HashMap<String, Object>();
				
				System.out.println("userNum: " + reviewLikeDTO.getUserNum());
				System.out.println("reviewId: " + reviewLikeDTO.getReviewId());
				System.out.println("kind: " + reviewLikeDTO.getKind());
				
				try {
					//좋아요 토글 (추가/삭제)
					boolean liked = reviewLikeService.toggleReviewLike(reviewLikeDTO);
					response.put("liked", liked);
					response.put("message", liked ? "좋아요 추가" : "좋아요 취소");
					return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
					
				} catch (Exception e) {
					//예외처리
					response.put("liked", false);
					response.put("message", "오류: "+ e.getMessage());
					return new ResponseEntity<Map<String,Object>> (response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
		
}
