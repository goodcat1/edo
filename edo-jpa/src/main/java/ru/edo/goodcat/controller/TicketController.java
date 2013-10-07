package ru.edo.goodcat.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ru.edo.goodcat.model.SeatType;
import ru.edo.goodcat.service.TicketService;

@Model
public class TicketController {
	@Inject
	private FacesContext facesContext;

	@Inject
	private TicketService ticketService;

	@Inject
	List<SeatType> seatTypes;

	@Inject
	Conversation conversation;

	@Produces
	@Named
	private SeatType newSeatType;

	@PostConstruct
	public void initNewSeatType() {
		newSeatType = new SeatType();
	}

	public String createTheatre() {
		ticketService.createTheatre(seatTypes);
		conversation.begin();
		return "book";
	}

	public String restart() {
		ticketService.doCleanUp();
		conversation.end();
		return "/index";
	}

	public void create() throws Exception {
		try {
			ticketService.createSeatType(newSeatType);
			FacesMessage m = new FacesMessage();
		} catch (Exception e) {

		}
	}
}
