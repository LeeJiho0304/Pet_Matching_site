package model;

public class NoticeBoard {
	
	private String list_id;
	private String pet_id; 
	private String list_title;  
	private String list_content;
	private String list_date;
	private int view_cnt; 
	private String entrust_term;
	private int notice_state;
	private String category;
	
	public NoticeBoard() {	}		// 기본 생성자
	
	public NoticeBoard(String list_id, String pet_id, String list_title, String list_content, String list_date,
			int view_cnt, String entrust_term, int notice_state, String category) {
		super();
		this.list_id = list_id;
		this.pet_id = pet_id;
		this.list_title = list_title;
		this.list_content = list_content;
		this.list_date = list_date;
		this.view_cnt = view_cnt;
		this.entrust_term = entrust_term;
		this.notice_state = notice_state;
		this.category = category;
	}
	
	public NoticeBoard(String list_id, String list_title) {
		super();
		this.list_id = list_id;
		this.list_title = list_title;
	}
	
	public String getList_id() {
		return list_id;
	}
	public void setList_id(String list_id) {
		this.list_id = list_id;
	}
	public String getPet_id() {
		return pet_id;
	}
	public void setPet_id(String pet_id) {
		this.pet_id = pet_id;
	}
	public String getList_title() {
		return list_title;
	}
	public void setList_title(String list_title) {
		this.list_title = list_title;
	}
	public String getList_content() {
		return list_content;
	}
	public void setList_content(String list_content) {
		this.list_content = list_content;
	}
	public String getList_date() {
		return list_date;
	}
	public void setList_date(String list_date) {
		this.list_date = list_date;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getEntrust_term() {
		return entrust_term;
	}
	public void setEntrust_term(String entrust_term) {
		this.entrust_term = entrust_term;
	}
	public int getNotice_state() {
		return notice_state;
	}
	public void setNotice_state(int notice_state) {
		this.notice_state = notice_state;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
