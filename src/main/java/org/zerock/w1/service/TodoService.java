package org.zerock.w1.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w1.dao.TodoDAO;
import org.zerock.w1.domain.TodoVO;
import org.zerock.w1.dto.TodoDTO;
import org.zerock.w1.util.MapperUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        log.info("todoVO: {}", todoVO);

        dao.insert(todoVO);
    }

    public List<TodoDTO> listAll() throws Exception {
        List<TodoVO> voList = dao.selectAll();

        log.info("voList: {}", voList);

        return voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    public TodoDTO get(Long tno) throws Exception {
        log.info("tno: {}", tno);
        TodoVO todoVO = dao.selectOne(tno);
        return modelMapper.map(todoVO, TodoDTO.class);
    }

    public void remove(Long tno) throws Exception {
        log.info("tno: {}", tno);
        dao.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception {
        log.info("todoDTO: {}", todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(todoVO);
    }
}
