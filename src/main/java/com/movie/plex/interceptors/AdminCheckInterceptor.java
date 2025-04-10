package com.movie.plex.interceptors;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.movie.plex.users.UserDAO;
import com.movie.plex.users.UserDTO;

@Component
public class AdminCheckInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
		if (userDTO == null) {
			request.setAttribute("result", "로그인 하세요.");
			request.setAttribute("path", "/users/login");

			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
			view.forward(request, response);

			return false;
		}
		List<UserDTO> admins = userDAO.getAdmin();

		for (UserDTO user : admins) {
			if (user.getUserId().equals(userDTO.getUserId())) {
				return true;
			}
		}
		request.setAttribute("result", "접근 권한이 없습니다");
		request.setAttribute("path", "/");

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
		view.forward(request, response);

		return false;
	}

}
