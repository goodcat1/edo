package ru.edo.goodcat.producer;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import ru.edo.goodcat.model.Seat;

@RequestScoped
public class SeatProducer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1680596916764711698L;
	@Inject
	private DataManager seatRepository;
	private List<Seat> seats;

	@Produces
	@Named
	public List<Seat> getSeats() {
		return seats;
	}

	public void onMemberListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Seat member) {
		retrieveAllSeats();
	}

	@PostConstruct
	public void retrieveAllSeats() {
		seats = seatRepository.findAllSeats();
	}
}
