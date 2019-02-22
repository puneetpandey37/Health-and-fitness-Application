package com.supafit.bo.program;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.security.LoggedInUserUtil;
import com.supafit.dao.program.ProgramManager;
import com.supafit.dao.program.model.Program;
import com.supafit.dao.program.model.ProgramCategory;
import com.supafit.dao.program.model.ProgramSubscription;
import com.supafit.dao.program.model.ProgramType;

@Component
public class ProgramService {

	private ProgramManager programManager;
	private LoggedInUserUtil loggedInUserUtil;
	@Autowired
	public void setLoggedInUserUtil(LoggedInUserUtil loggedInUserUtil) {
		this.loggedInUserUtil = loggedInUserUtil;
	}

	@Autowired
	public void setProgramManager(ProgramManager programManager) {
		this.programManager = programManager;
	}

	public List<Program> getPrograms(Long typeId, Long categoryId) {
		return programManager.getPrograms(getProgramQuery(typeId, categoryId));
	}

	private String getProgramQuery(Long typeId, Long categoryId) {
		String query = "select p from Program p";
		String subQuery = "";
		if (typeId != null && categoryId != null) {
			subQuery = "where p.typeId= " + typeId + " " + "and p.categoryId="
					+ categoryId;
		} else if (typeId != null) {
			subQuery = "where p.typeId= " + typeId;
		} else if (categoryId != null) {
			subQuery = "where p.categoryId=" + categoryId;
		}
		query = query + " " + subQuery;
		return query;
	}

	public List<ProgramCategory> getProgramCategories() {
		return programManager.getProgramCategories();
	}

	public List<ProgramType> getProgramTypes() {
		return programManager.getProgramTypes();
	}

	public ProgramSubscription subscribe(ProgramSubscription subscription) {
//		Long userId = loggedInUserUtil.getLoggedInUserId();
//		subscription.setUserId(userId);
		return programManager.subscribe(subscription);
	}

	public ProgramSubscription updateSubscribtion(
			ProgramSubscription subscription) throws InvalidRequestException {
		ProgramSubscription result = null;
//		User loggedInUser = loggedInUserUtil.getLoggedInUser();
//		Long userTypeId = loggedInUser.getUserTypeId();
//		if(userTypeId != null && userTypeId == 5) {
			result = subscribe(subscription);
		/*}else {
			throw new InvalidRequestException(
					"Not an Admin!");
		}*/
		return result;
	}

}
