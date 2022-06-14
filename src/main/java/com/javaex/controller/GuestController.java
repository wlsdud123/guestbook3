package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestController {

	//필드
	
	//생성자
		
	//메소드 gs
		
	//메소드 일반
		//등록 폼(리스트)
		@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST })
		public String addList(Model model) {
			System.out.println("GuestController>addList()");
			
		
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestList = guestbookDao.getList();
			
			
			model.addAttribute("guestList", guestList);
			
			
			return "/WEB-INF/views/addList.jsp";
		}
		
		//등록
		@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST })
		public String add(@ModelAttribute GuestbookVo guestbookVo) {
			System.out.println("GuestController>add()");
			
			//파라미터 꺼내기 + Vo로 묶기를 DS해서 메소드의 파라미터로 보내준다
			
			//dao로 저장하기
			GuestbookDao guestbookDao = new GuestbookDao();
			int count = guestbookDao.insert(guestbookVo);
			System.out.println(count);
			
			//리다이렉트
			return "redirect:/addList";
		}
		
		//삭제 폼
		@RequestMapping(value="/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
		public String deleteForm() {
			System.out.println("guestController>deleteForm()");
			
			return "/WEB-INF/views/deleteForm.jsp";
		}
		
		//삭제
		@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
		public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
			System.out.println("guestController>delete()");
			
			//파라미터 꺼내기
			System.out.println(no);
			System.out.println(password);
			
			//vo에 no,password담기
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);
			
			//Dao로 처리하기(삭제)
			GuestbookDao dao = new GuestbookDao();
			dao.delete(vo);
			
			
			return "redirect:/addList";
		}

	
	

	
	
		//테스트
		@RequestMapping(value="/test", method= {RequestMethod.GET, RequestMethod.POST })
		public String test() {
			System.out.println("GuestController>test()");
			
			//다오
			return "/WEB-INF/views/test.jsp";
		}
}