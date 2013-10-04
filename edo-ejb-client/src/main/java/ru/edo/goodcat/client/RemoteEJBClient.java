package ru.edo.goodcat.client;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ru.goodcat.edo.ejb.AddDataException;
import ru.goodcat.edo.ejb.ProcessorInfo;
import ru.goodcat.edo.ejb.ProcessorWorker;

public class RemoteEJBClient {
	private final static Logger log = Logger.getLogger(RemoteEJBClient.class
			.getName());

	@SuppressWarnings("rawtypes")
	private final static Hashtable jndiProperties = new Hashtable();

	public static void main(String[] args) throws Exception {
		Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
		Logger.getLogger("org.xnio").setLevel(Level.SEVERE);

		testRemoteEJB();
	}

	@SuppressWarnings("unchecked")
	private static void testRemoteEJB() throws NamingException {
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");

		final ProcessorInfo processorInfo = lookupProcessorInfo();

		log.info("ProcessorInfo Data 1: " + processorInfo.printData());

		final ProcessorWorker processorWorker = lookupProcessorWorker();

		try {
			processorWorker.addData("DDDDD");
		} catch (AddDataException e) {
			log.fine(e.getMessage());

			return;
		}

		log.info("ProcessorInfo Data 2: " + processorInfo.printData());
	}

	private static ProcessorInfo lookupProcessorInfo() throws NamingException {
		final Context context = new InitialContext(jndiProperties);
		return (ProcessorInfo) context
				.lookup("ejb:/edo-ejb//ProcessorInfoBean!ru.goodcat.edo.ejb.ProcessorInfo");
	}

	private static ProcessorWorker lookupProcessorWorker()
			throws NamingException {
		final Context context = new InitialContext(jndiProperties);
		return (ProcessorWorker) context
				.lookup("ejb:/edo-ejb/ProcessorWorkerBean!ru.goodcat.edo.ejb.ProcessorWorker?stateful");
	}
}
