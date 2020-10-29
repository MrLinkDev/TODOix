package ru.link.todoix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.link.todoix.Objects.*;
import ru.link.todoix.PostModels.ReviewPostModel;
import ru.link.todoix.Repositories.*;

import java.util.*;

@Service
public class ListServiceImpl implements ListService {
    @Autowired
    private ListRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void create(ListDTO listDTO) {
        ListEntity listEntity = Converter.DTOToEntity(listDTO);
        listRepository.save(listEntity);
    }

    @Override
    public ListDTO findById(UUID id) {
        ListEntity listEntity = listRepository.findById(id);

        return Converter.entityToDTO(listEntity);
    }

    @Override
    public void update(ListDTO listDTO) {
        listRepository.updateById(listDTO.getId(), listDTO.getName(), listDTO.getModifyDate());
    }

    @Override
    public void deleteById(UUID id) {
        listRepository.deleteById(id);
    }

    @Override
    public List<ListDTO> getAll() {
        List<ListEntity> out = listRepository.findAll();
        return Converter.convertListOfListEntitiesToDTO(out);
    }

    @Override
    public ReviewPostModel getPage(int p, int size, String sort) {
        Pageable pageable = PageRequest.of(p, size, Sort.by(sort));
        Page<ListEntity> page = listRepository.findAll(pageable);

        int finishedListCount = 0;
        int openedListCount = 0;

        for (ListEntity list : page.getContent()){
            boolean finished = true;
            List<TaskEntity> tasks = taskRepository.findByList(list);
            for (TaskEntity taskItem : tasks){
                if (!taskItem.isFinished()) {
                    finished = false;
                    break;
                }
            }

            if (finished) ++finishedListCount;
            else ++openedListCount;
        }

        ReviewPostModel review = new ReviewPostModel();
        review.setLists(Converter.convertListOfListEntitiesToDTO(page.getContent()));
        review.setOpenedListCount(openedListCount);
        review.setFinishedListCount(finishedListCount);

        return review;
    }
}
