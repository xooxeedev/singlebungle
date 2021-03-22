package com.gaji.SingleBungle.admin.vo;

public class reportReply {
	
	private int replyNo;
	private int boardNo;
	private int boardCode;
	private String status;
	private int reportNo;
	private String reportTitle;
	private String reportContent;
	private String reportCategoryNm;
	
	public reportReply() {}

	public reportReply(int replyNo, int boardNo, int boardCode, String status, int reportNo, String reportTitle,
			String reportContent, String reportCategoryNm) {
		super();
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.boardCode = boardCode;
		this.status = status;
		this.reportNo = reportNo;
		this.reportTitle = reportTitle;
		this.reportContent = reportContent;
		this.reportCategoryNm = reportCategoryNm;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getReportCategoryNm() {
		return reportCategoryNm;
	}

	public void setReportCategoryNm(String reportCategoryNm) {
		this.reportCategoryNm = reportCategoryNm;
	}

	@Override
	public String toString() {
		return "reportReply [replyNo=" + replyNo + ", boardNo=" + boardNo + ", boardCode=" + boardCode + ", status="
				+ status + ", reportNo=" + reportNo + ", reportTitle=" + reportTitle + ", reportContent="
				+ reportContent + ", reportCategoryNm=" + reportCategoryNm + "]";
	}
	
	
}
