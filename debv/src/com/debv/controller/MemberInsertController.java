package com.debv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.debv.service.MemberService;
import com.debv.vo.MemberVO;

public class MemberInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//신규회원 등록기능 ->memberInsertOutput.jsp
		//입력파라메터 읽어오기
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		String mail = request.getParameter("mail");
		//
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setMail(mail);
		member.setName(name);
		member.setPasswd(passwd);
		
		//서비스로직 호출
		MemberService service= MemberService.getInstance();
		service.memberInsert(member);
		
		request.setAttribute("member", member);
		
		RequestDispatcher rd = request.getRequestDispatcher("/result/memberInsertOutput.jsp");
		rd.forward(request, response);
	}

}
