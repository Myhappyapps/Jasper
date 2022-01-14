package com.poornabhaskar.jasper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poornabhaskar.jasper.model.User;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GenPdf {
	
	static final String fileName = "/Users/poornabhaskarduvvari/JaspersoftWorkspace/MyReports/Users.jrxml";
	static final String outFile = "/Users/poornabhaskarduvvari/JaspersoftWorkspace/MyReports/Report-2.pdf";


	public static void main(String[] args) throws JRException, FileNotFoundException {
		List<User> users = new ArrayList<User>();

		Map<String, Object> parameter = new HashMap<String, Object>();

		users.add(new User(1, "Om"));
		users.add(new User(2, "Om Shakthi"));
		users.add(new User(3, "Omdram"));
		JRBeanCollectionDataSource studentCollectionDataSource = new JRBeanCollectionDataSource(users);
		
		parameter.put("userDataSource", studentCollectionDataSource);
		parameter.put("title", new String("Hi, I am Title"));

		JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameter, new JREmptyDataSource());

		File file = new File(outFile);
		OutputStream outputSteam = new FileOutputStream(file);
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);

		System.out.println("Report Generated!");
	}

}
