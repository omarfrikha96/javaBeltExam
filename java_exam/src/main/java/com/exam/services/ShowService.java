package com.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.exam.models.Show;
import com.exam.repositories.ShowRepository;

@Service
public class ShowService {
	@Autowired
	private ShowRepository showRepo;

	public List<Show> allShows() {
		return showRepo.findAll();
	}

	public Show updateShow(Show show) {
		return showRepo.save(show);
	}

	public Show addShow(Show show, BindingResult result) {
		
		Optional<Show> potentialTitle = showRepo.findByTitle(show.getTitle());
    	
	    // Reject if title is taken (present in database)
	    if (potentialTitle.isPresent()) {
	        result.reject("title", "The title already exists!");
	        return null; // Or handle accordingly
	    }
		return showRepo.save(show);
	}

	public void deleteShow(Show show) {
		showRepo.delete(show);
	}

	public Show findById(Long id) {
		Optional<Show> optionalShow = showRepo.findById(id);
		if (optionalShow.isPresent()) {
			return optionalShow.get();
		} else {
			return null;
		}
	}
}
