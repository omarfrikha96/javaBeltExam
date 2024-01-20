package com.exam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.models.Review;
import com.exam.models.Show;
import com.exam.models.User;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
	List<Review> findAll();
	List<Review> findByShowIs(Show show);
    List<Review> findByUser(User user);
}
