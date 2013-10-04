package ru.edo.goodcat.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import ru.edo.goodcat.ejb.Processor;
import ru.edo.goodcat.model.Data;

@Model
public class ProcessorInfoBean {
	@Inject
	Processor processor;

	private List<Data> values;

	@Produces
	@Named
	public List<Data> getValues() {
		return values;
	}

	public void onValuesListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Data value) {
		retrieveValues();
	}

	@PostConstruct
	public void retrieveValues() {
		values = processor.getValues();
	}
}
