package se331.lab.rest.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import se331.lab.rest.entity.Event;
import se331.lab.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {
	@Autowired
	private EventRepository eventRepository;

	@Override
	public Integer getEventSize() {
		return Math.toIntExact(eventRepository.count());
	}

	@Override
	public List<Event> getEvents(Integer pageSize, Integer page) {
		Pageable pageable = PageRequest.of(page == null ? 0 : page - 1, pageSize == null ? (int) eventRepository.count() : pageSize);
		return eventRepository.findAll(pageable).getContent();
	}

	@Override
	public Event getEvent(Long id) {
		return eventRepository.findById(id).orElse(null);
	}
}
