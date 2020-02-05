package com.tenhawks.person.service;

import com.tenhawks.person.annotations.LogMethod;
import com.tenhawks.person.domain.Hobby;
import com.tenhawks.exception.AssignmentException;
import com.tenhawks.person.repository.HobbyRepository;
import com.tenhawks.person.util.PersonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tenhawks.bean.CommonHelper.checkNull;

/**
 * The Hobby service
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Slf4j
@Service
@Transactional
public class HobbyServiceImpl extends BaseListableService<Hobby, String> implements HobbyService {

    @Override
    @LogMethod
    public Hobby update(String id, Hobby entity) throws AssignmentException {
        checkNull(id, "id");
        checkNull(entity, "entity");
        Hobby oldEntity = get(id);
        oldEntity.setName(entity.getName());
        return repository.save(entity);
    }

    @Override
    public List<Hobby> activeHobbyList() {
        return ((HobbyRepository) repository).findByIsActiveTrue();
    }
}
