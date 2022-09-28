package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.dao.TodoDAO;
import org.zerock.w1.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {

    private TodoDAO todoDAO;

    @BeforeEach
    public void ready() {
        todoDAO = new TodoDAO();
    }

    @Test
    void testTime() throws Exception {
        System.out.println(todoDAO.getTime());
    }

    @Test
    void testInsert() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .title("Sample Title")
                .dueDate(LocalDate.of(2021,12,31))
                .build();

        todoDAO.insert(todoVO);
    }

    @Test
    void testSelectAll() throws Exception {
        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    void testSelectOne() throws Exception {
        Long tno = 1L;

        TodoVO todoVO = todoDAO.selectOne(tno);

        System.out.println(todoVO);
    }

    @Test
    void testUpdateOne() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("Sample title")
                .dueDate(LocalDate.of(2021,12,31))
                .finished(true)
                .build();
        todoDAO.updateOne(todoVO);
    }
}
