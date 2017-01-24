package web;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class BBSList {
	private ArrayList<Integer> seqNoList = new ArrayList<Integer>();
	private ArrayList<String> titleList = new ArrayList<String>();
	private ArrayList<String> writerList = new ArrayList<String>();
	private ArrayList<Date> dateList = new ArrayList<Date>();
	private ArrayList<Time> timeList = new ArrayList<Time>();
	private boolean lastPage = false;

	public BBSList() {
	}

	public boolean isLastPage(){
		return this.lastPage;
	}
	
	public int getListSize() {
		return seqNoList.size();
	}

	public Integer[] getSeqNo() {
		return seqNoList.toArray(new Integer[seqNoList.size()]);
	}

	public String[] getTitle() {
		return titleList.toArray(new String[titleList.size()]);
	}

	public String[] getWriter() {
		return writerList.toArray(new String[writerList.size()]);
	}

	public Date[] getDate() {
		return dateList.toArray(new Date[dateList.size()]);
	}

	public Time[] getTime() {
		return timeList.toArray(new Time[timeList.size()]);
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public void setTime(int index, Time time) {
		this.timeList.add(index, time);
	}

	public void setDate(int index, Date date) {
		this.dateList.add(index, date);
	}

	public void setWriter(int index, String writer) {
		this.writerList.add(index, writer);
	}

	public void setTitle(int index, String title) {
		this.titleList.add(index, title);
	}

	public void setSeqNo(int index, Integer seqNo) {
		this.seqNoList.add(index, seqNo);
	}

}
