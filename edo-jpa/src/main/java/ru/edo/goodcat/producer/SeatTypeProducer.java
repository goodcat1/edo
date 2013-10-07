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

import ru.edo.goodcat.model.SeatType;

@RequestScoped
public class SeatTypeProducer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1136889369354628232L;

	@Inject
	private DataManager seatRepository;

	private List<SeatType> seatTypes;

	@Produces
	@Named
	public List<SeatType> getSeatTypes() {
		return seatTypes;
	}

	public void onListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final SeatType member) {
		retrieveData();
	}

	@PostConstruct
	public void retrieveData() {
		seatTypes = seatRepository.findAllSeatTypes();
	}

}
