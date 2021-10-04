package com.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.domain.BoardAttachVO;
import com.project.domain.BoardVO;
import com.project.domain.Criteria;
import com.project.domain.PageDTO;
import com.project.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.nio.file.*;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardContoller {
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list : " + cri);
		/*model.addAttribute("list", service.getList());*/
		model.addAttribute("list", service.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total = service.getTotal(cri);
		
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		
	}
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("==============================================");
		log.info("register : " + board);
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		log.info("==============================================");
		service.register(board);
		rttr.addFlashAttribute("result", board.getSeq_bno());
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("seq_bno") Long seq_bno,
			@ModelAttribute("cri") Criteria cri,
			Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(seq_bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify:"+board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		/*
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
		*/
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("seq_bno") Long seq_bno,
			@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove : " + seq_bno);
		List<BoardAttachVO> attachList = service.getAttachList(seq_bno);
		if(service.remove(seq_bno)) {
			deleteFiles(attachList); // delete Attach Files
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list" + cri.getListLink();
		/*
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
		*/
	}
	
	@GetMapping(value="/getAttachList",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long seq_bno) {
		log.info("getAttachList : "+seq_bno);
		return new ResponseEntity<List<BoardAttachVO>>
				(service.getAttachList(seq_bno),HttpStatus.OK);
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) return;
		log.info(attachList);
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\storage\\"+attach.getUploadPath()+
							"\\"+attach.getUuid()+"_"+attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\storage\\"+attach.getUploadPath()+
							"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch (Exception e) {
				log.error("delete file error : "+e.getMessage());
			}
		});
	}
}
