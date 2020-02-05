package com.tenhawks.person.service;

import com.tenhawks.person.annotations.LogMethod;
import com.tenhawks.person.domain.Colour;
import com.tenhawks.exception.AssignmentException;
import com.tenhawks.person.repository.ColourRepository;
import com.tenhawks.person.util.PersonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tenhawks.bean.CommonHelper.checkNull;

/**
 * The Colour service
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */

@Slf4j
@Service
@Transactional
public class ColourServiceImpl extends BaseListableService<Colour, String> implements ColourService {


    @Override
    @LogMethod
    public Colour update(String id, Colour entity) throws AssignmentException {
        checkNull(id, "id");
        checkNull(entity, "entity");
        Colour oldEntity = get(id);
        oldEntity.setName(entity.getName());
        return repository.save(entity);
    }


    @Override
    @LogMethod
    public List<Colour> activeColourList() {


        return ((ColourRepository) repository).findByIsActiveTrue();
    }


}
