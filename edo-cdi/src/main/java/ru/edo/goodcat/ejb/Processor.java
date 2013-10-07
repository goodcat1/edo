package ru.edo.goodcat.ejb;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import ru.edo.goodcat.model.Data;

@Singleton
@Startup
public class Processor implements Serializable {
	private static final long serialVersionUID = -3032244992909968484L;

	private final Logger log = Logger.getLogger(Processor.class);
	private final ArrayList<Data> values;

	@SuppressWarnings("serial")
	public Processor() {
		values = new ArrayList<Data>() {
			{
				add(new Data() {
					{
						setId(1L);
						setValue("aa");
					}
				});
				add(new Data() {
					{
						setId(2L);
						setValue("bB");
					}
				});
			}
		};
	}

	@Inject
	Event<Data> valueEvent;

	@Lock(LockType.READ)
	public ArrayList<Data> getValues() {
		log.debug("Getting values");

		return values;
	}

	@Lock(LockType.WRITE)
	public void addValue(Data value) {
		log.debugv("Adding value {}", value);

		values.add(value);

		valueEvent.fire(value);
	}

}
