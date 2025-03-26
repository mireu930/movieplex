package com.movie.plex.boards.faq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaqService {
	@Autowired
	private FaqDAO faqDAO;
}
