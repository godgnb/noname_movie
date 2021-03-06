package it.company.noname.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.company.noname.domain.MovieCommentVO;
import it.company.noname.domain.MovieRequestVO;
import it.company.noname.domain.MovieVO;
import it.company.noname.service.movieService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value="/movie/*")
@AllArgsConstructor
public class movieController {
	
	private movieService service;
	
	@GetMapping("/main")
	public String searchMovie(String query, MovieRequestVO vo, Model model) {
		List<MovieVO> movieList = service.searchMovie(vo);
		model.addAttribute("movieList", movieList);

		service.searchData(query);
		
		return "movie/list";
	} // searchMovie get
	
	
	@GetMapping("/content")
	public String content(MovieVO vo, Model model, HttpSession session) {
		
		String email =(String) session.getAttribute("email");
		
		// 전체 후기 가져오기
		List<MovieCommentVO> commentList = service.getComments(vo.getTitle());
		
		session.setAttribute("movie", vo);
		
		// 뷰에서 사용할 정보
		model.addAttribute("commentList", commentList);
		
		return "movie/content";
	} // content get
	
	
	@PostMapping("/commentWrite")
	public String commentWrite(MovieVO movieVO, MovieCommentVO movieCommentVO,
			Model model, HttpSession session) {
		
		log.info("movieCommentVO : " + movieCommentVO);
		
		String email =(String) session.getAttribute("email");
		
		movieCommentVO.setMovieName(movieVO.getTitle());
		movieCommentVO.setEmail(email);
		
		// 후기 한개 등록
		service.insertComment(movieCommentVO);
		
		// 전체 후기 가져오기
		List<MovieCommentVO> commentList = service.getComments(movieVO.getTitle());
		
		model.addAttribute("commentList", commentList);
		
		return "movie/content";
	} // commentWrite post
	
	
	@GetMapping("/commentDelete")
	public String commentDelete(int num, String title, Model model) {
		// 후기 한개 삭제
		service.deleteComment(num);
		
		// 전체 후기 가져오기
		List<MovieCommentVO> commentList = service.getComments(title);
		
		model.addAttribute("commentList", commentList);
		
		return "movie/content";
	} // commentDelete get
	
	
	@GetMapping("/commentUpdateForm")
	public String commentUpdateForm(int num, String title, Model model) {
		// 후기 한개 가져오기
		MovieCommentVO vo = service.getComment(num);
		
		// 전체 후기 가져오기
		List<MovieCommentVO> commentList = service.getComments(title);
		
		
		model.addAttribute("commentList", commentList);
		model.addAttribute("moviecomment", vo);
		
		return "movie/commentUpdate";
	} // commentUpdateForm get
	
	
	@PostMapping("/commentUpdate")
	public String commentUpdate(MovieCommentVO vo, Model model) {
		// 후기 한개 수정하기
		service.updateComment(vo);

		// 전체 후기 가져오기
		List<MovieCommentVO> commentList = service.getComments(vo.getMovieName());
		
		model.addAttribute("commentList", commentList);
		
		return "movie/content";
	} // commentUpdate post
	
	
} // movieController class