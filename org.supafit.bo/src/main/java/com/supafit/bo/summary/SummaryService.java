package com.supafit.bo.summary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.summary.SummaryManager;
import com.supafit.dao.summary.model.ActivitySummary;
@Component
public class SummaryService {

	private SummaryManager summaryManager;
	@Autowired
	public void setSummaryManager(SummaryManager summaryManager) {
		this.summaryManager = summaryManager;
	}
	
	public ActivitySummary createSummary(ActivitySummary summary) {
		summary.setDate(getCurrentDate());
		return summaryManager.createSummary(summary);
	}
	
	public List<ActivitySummary> getUsersSummary(Long userId) {
		return summaryManager.getUsersSummary(userId);
	}
	
	public ActivitySummary getTodaysSummary(Long userId, String date) {
		if(date == null)
			date = getCurrentDate();
		return summaryManager.getTodaysSummary(userId, date);
	}
	
	public ActivitySummary getSummaryOfGivenDate(Long userId, String date) {
		return summaryManager.getTodaysSummary(userId, date);
	}
	public ActivitySummary updateSummary(ActivitySummary summary) {
		return summaryManager.createSummary(summary);
	}

	private String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return df.format(date);
	}
}
