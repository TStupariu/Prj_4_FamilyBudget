package repository;

import java.io.BufferedReader;

import model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.InvalidBudgetException;
import exceptions.InvalidNameException;
import exceptions.InvalidTypeException;

public class MemberRepository {
	private List<Member> members = new ArrayList<Member>();
	private List<Entry> entries = new ArrayList<Entry>();

	private final static String filenameMember = "membersF.txt";
	private final static String filenameBudget = "budgetF.txt";

	@SuppressWarnings("resource")
	public MemberRepository() {
		
		try{
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;
			String currentLine = null;

			fileReader = new FileReader(filenameMember);
			bufferedReader = new BufferedReader(fileReader);
			
			while ((currentLine = bufferedReader.readLine()) != null) {
				String line[] = currentLine.split(";");
				Member m = new Member(line[0], line[1]);
				this.members.add(m);			
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		try{
			FileReader fileReaderEntry = null;
			BufferedReader bufferedReaderEntry = null;
			String currentLineEntry = null;

			fileReaderEntry = new FileReader(filenameMember);
			bufferedReaderEntry = new BufferedReader(fileReaderEntry);
			
			while ((currentLineEntry = bufferedReaderEntry.readLine()) != null) {
				String line[] = currentLineEntry.split(";");
				int valueEntry = Integer.parseInt(line[1]);
				String idEntryMember = line[2];
				Entry e = new Entry(line[0],valueEntry,idEntryMember);
				this.entries.add(e);			
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}

	public boolean addMember(Member m){
		if (members.contains(m)) {
			return false;
		}
		return members.add(m);
	}
	public boolean addEntry(Entry e){
		if (this.entries.contains(e)) {
			return false;
		}
		boolean ok = false;
		for (int i = 0; i < members.size(); i++) {
			String foo = members.get(i).getId();
			String bar = e.getIdMember();
			if (members.get(i).getId().equals(e.getIdMember())) {
				ok = true;
			}
		}
		if (ok == false) return false;
		entries.add(e);
		return true;
	}
	public List<Entry> getAllEntries(){
		return entries;
	}
}
