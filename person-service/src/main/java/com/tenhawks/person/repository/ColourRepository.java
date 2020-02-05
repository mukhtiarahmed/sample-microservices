package com.tenhawks.person.repository;

import com.tenhawks.person.domain.Colour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Colour repository.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Repository
public interface ColourRepository extends JpaRepository<Colour, String> {


    List<Colour> findByIsActiveTrue();
}
