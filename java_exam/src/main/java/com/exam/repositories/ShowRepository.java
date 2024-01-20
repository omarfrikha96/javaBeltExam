package com.exam.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.models.Show;
import com.exam.models.User;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long> {
	List<Show> findAll();
	Show findByIdIs(Long id);
	List<Show> findAllByUsers(User user);
	Optional<Show> findByTitle(String title);
}
