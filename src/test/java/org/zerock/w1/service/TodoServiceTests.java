package org.zerock.w1.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.dto.TodoDTO;

import java.time.LocalDate;

@Log4j2
class TodoServiceTests {

    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }

    @Test
    void testRegister() throws Exception {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();

        log.info("--------------------------------------");
        log.info("todoDTO: {}", todoDTO);

        todoService.register(todoDTO);
    }

}