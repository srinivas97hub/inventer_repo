package com.Quartz.Demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("...........Job Started.................");
		Map<String,String> MailIDs=new LinkedHashMap<String,String>();
		List<String> Names=new ArrayList<String>();
		String filePath ="/home/infyz/Documents/Data.xlsx";
		String empname ="Name";
		String dateOfBirthColumn = "DOB";
		String emailColumn = "Mail_Id";
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM");
		String Time = formatter.format(date);
		//System.out.println(Time);
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis) ;
			Sheet sheet = workbook.getSheetAt(0); // Assuming data is present in the first sheet

			int nameColumnIndex = getColumnIndex(sheet, empname);
			int dobColumnIndex = getColumnIndex(sheet, dateOfBirthColumn);
			int emailColumnIndex = getColumnIndex(sheet, emailColumn);

			if (nameColumnIndex!=-1 && dobColumnIndex != -1 && emailColumnIndex != -1) {
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next(); // Skip header row 

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					Cell nameCell = row.getCell(nameColumnIndex);
					Cell dobCell = row.getCell(dobColumnIndex);
					Cell emailCell = row.getCell(emailColumnIndex);
					Date d=dobCell.getDateCellValue();
					String dob=formatter.format(d);
					if(Time.equals(dob)) {
						MailIDs.put(nameCell.toString(), emailCell.toString());
					}
				}
			} else {
				System.out.println("Columns not found in the Excel sheet.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!MailIDs.isEmpty()) {
			for (Map.Entry<String,String> entry : MailIDs.entrySet()) //using map.entrySet() for iteration  
			{ 
				String body = ("Dear "+entry.getKey().toUpperCase()+", \r\n "
						+ "\r\n"
						+ "Your special days are just as special to us, and we are \n "
						+ "overjoyed to celebrate your birthday! On your birthday, we send \n "
						+ "you our most heartfelt wishes. An amazing person deserves all \n"
						+ "the happiness and health – and we expect nothing less for you. We are \n"
						+ "glad to have you as a valuable member of our team and look \n"
						+ "forward to celebrating your other special days as well. \r\n"
						+ "\r\n"
						+ "Hope your day is as wonderful as you are! ");
				String subject = ("Happy birthday, "+entry.getKey().toUpperCase()+"! ");

				String Recipient = entry.getValue();

				String sender = ("srinivas.t@infyzterminals.com");
				String password = ("infyz@2022");
				String senderUsername = ("srinivas.t");

				MailSenderBean mailsender = new MailSenderBean();
				mailsender.sendEmail(sender, senderUsername, password, Recipient, subject, body);
				System.out.println(".............Job Finished...............");
			}
		}else {
			System.out.println("no Birthday today");
		}
	}
	private static int getColumnIndex(Sheet sheet, String columnName) {
		Row headerRow = sheet.getRow(0);
		Iterator<Cell> cellIterator = headerRow.cellIterator();

		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
				return cell.getColumnIndex();
			}
		}

		return -1; // Column not found
	}

}