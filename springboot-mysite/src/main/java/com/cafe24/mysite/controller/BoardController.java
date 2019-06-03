package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/reply/{no}", method=RequestMethod.GET)
	public String reply(@PathVariable Long no, Model model) {
		BoardVo boardVo = boardService.show(no);
		model.addAttribute("vo", boardVo);
		return "board/reply";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(@ModelAttribute BoardVo vo) {
		boardService.reply(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(HttpSession session, @PathVariable Long no) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "user/login";
		}
		else {
			BoardVo boardVo = boardService.show(no);
			if(boardVo.getUserNo() == authUser.getNo()) {
				boardService.delete(no);
			}
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(HttpSession session, @PathVariable Long no, Model model) {
		Object authUser = session.getAttribute("authUser");
		if(authUser == null) {
			return "user/login";
		}
		model.addAttribute("vo", boardService.show(no));
		return "board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo) {
		boardService.modify(vo);
		return "redirect:/board/view/" + vo.getNo();
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable Long no, Model model) {
		model.addAttribute("vo", boardService.show(no));
		return "board/view";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@RequestParam(value="page", defaultValue="0") int currentPage, @RequestParam(value="kwd", defaultValue="") String keyword, Model model) {
		System.out.println("get list " + keyword);
		model.addAttribute("list", boardService.showList(currentPage, keyword));
		model.addAttribute("pager", boardService.getPager());
		model.addAttribute("keyword", keyword);
		return "board/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String list(@RequestParam(value="kwd", defaultValue="") String keyword, Model model) {
		System.out.println("post list " + keyword);
		model.addAttribute("list", boardService.showList(0, keyword));
		model.addAttribute("pager", boardService.getPager());
		model.addAttribute("keyword", keyword);
		return "board/list";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo) {
		boardService.write(vo);
		
		return "redirect:/board/list";
	}
}
