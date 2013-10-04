package ru.goodcat.edo.ejb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

@Singleton
@Startup
public class Processor {
	private ArrayList<String> data;
	private static final Logger log = Logger.getLogger(Processor.class);

	@PostConstruct
	@SuppressWarnings("serial")
	public void setUpProcessor() {
		data = new ArrayList<String>() {
			{
				add("AA");
				add("BB");
				add("cC");
			}
		};
		
		log.info("Data constructed");
	}

	@Lock(LockType.READ)
	public ArrayList<String> getData() {
		log.debug("Get data");
		
		return data;
	}

	@Lock(LockType.READ)
	public String getVal(int id) {
		return getData().get(id);
	}

	@Lock(LockType.WRITE)
	public void setVal(String val) {
		data.add(val);
	}

}
