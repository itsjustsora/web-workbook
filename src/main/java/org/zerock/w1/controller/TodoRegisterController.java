package org.zerock.w1.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w1.dto.TodoDTO;
import org.zerock.w1.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", urlPatterns = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        // JSESSIONID가 없다면
        if (session.isNew()) {
            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
            resp.sendRedirect("/login");
            return;
        }

        // JSESSIONID는 있지만 세션 컨텍스트에 loginInfo 객체가 없는 경우
        if (session.getAttribute("loginInfo") == null) {
            log.info("로그인한 정보가 없는 사용자");
            resp.sendRedirect("/login");
            return;
        }

        // 일반적인 경우라면
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
                .build();

        log.info("todoDTO: {}", todoDTO);

        try {
            todoService.register(todoDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        resp.sendRedirect("/todo/list");

    }
}
