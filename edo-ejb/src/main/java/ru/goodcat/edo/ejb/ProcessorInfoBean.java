package ru.goodcat.edo.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ProcessorInfo.class)
public class ProcessorInfoBean implements ProcessorInfo {
	@EJB
	Processor processor;

	@Override
	public String printData() {
		ArrayList<String> data = processor.getData();
		StringBuilder sb = new StringBuilder();

		for (String s : data) {
			sb.append(s).append(";");
		}

		return sb.toString();
	}

}
