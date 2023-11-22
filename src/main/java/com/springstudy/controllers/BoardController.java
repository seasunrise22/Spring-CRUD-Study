package com.springstudy.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springstudy.models.Comment;
import com.springstudy.models.Post;
import com.springstudy.services.BoardService;

@Controller
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService postService) {
		this.boardService = postService;
	}

	@GetMapping("/")
	public String list(Model model, @RequestParam(required = false) Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		int pageSize = 5;
		int numOfPages = boardService.getNumOfPages(pageSize);
		List<Post> posts = boardService.getPostsByPage(page, pageSize);
		model.addAttribute("posts", posts);
		model.addAttribute("numOfPages", numOfPages);
		return "board/list";
	}

	@GetMapping("/write")
	public String writePost() {
		return "board/write";
	}

	@PostMapping("/create")
	public String createPost(@ModelAttribute Post post) {
		if (post.getId() == null) {
			boardService.create(post);
		} else {
			boardService.update(post);
		}
		return "redirect:/";
	}

	@GetMapping("/read/{id}")
	public String readPost(@PathVariable Integer id, Model model) {
		boardService.incrementViews(id); // 조회수 증가

		model.addAttribute("post", boardService.getPostById(id));
		model.addAttribute("comments", boardService.getCommentsById(id));
		return "board/read";
	}

	// read.jsp 페이지에서 수정 or 삭제 버튼 눌렀을 때, 비밀번호 확인 페이지로 이동
	@GetMapping("/checkPassword/{id}")
	public String checkPassword(@PathVariable Integer id, @RequestParam String action, Model model) {
		model.addAttribute("postId", id);
		model.addAttribute("action", action);
		return "board/checkPassword";
	}

	// checkPassword.jsp 비밀번호 확인 페이지에서 수정 버튼 눌렀을 때
	@PostMapping("/update/{id}")
	public String updatePost(@PathVariable Integer id, @RequestParam String password, Model model,
			RedirectAttributes redirectAttrs) {
		if (boardService.checkPassword(id, password)) {
			model.addAttribute("post", boardService.getPostById(id));
			return "board/write";
		} else {
			redirectAttrs.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "redirect:/checkPassword/" + id + "?action=update";
		}
	}

	// checkPassword.jsp 비밀번호 확인 페이지에서 삭제 버튼 눌렀을 때
	@PostMapping("/delete/{id}")
	public String deletePost(@PathVariable Integer id, @RequestParam String password,
			RedirectAttributes redirectAttrs) {
		if (boardService.checkPassword(id, password)) {
			boardService.delete(id);
		} else {
			redirectAttrs.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "redirect:/checkPassword/" + id + "?action=delete";
		}

		return "redirect:/";
	}
	
	/**
	 * 댓글
	 */
	@PostMapping("/comment/create")
	public String createComment(@ModelAttribute Comment comment) {
		boardService.createComment(comment);
		return "redirect:/read/" + comment.getPostId();
	}
}