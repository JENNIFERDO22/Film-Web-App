package model;

import java.util.List;

public class GETResponse {
	private Criteria criteria;
	private List<ReportRecord> result;
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	public List<ReportRecord> getResult() {
		return result;
	}
	public void setResult(List<ReportRecord> result) {
		this.result = result;
	}
	
	
	
}
