package model;

public class Content {
	
	private String pet_id;
	private String pet_kinds;
	private String pet_race;
	private int pet_age;
	private String pet_gender;
	private String pet_vaccine;
	private String pet_price;
	private String pet_name;
	
	private String user_id;
	
	private String list_id;
	private String list_title;
	private String list_content;
	private String list_date; 
	private String entrust_term;
	private int notice_state;
	private String category;
	
	public Content() {	}

	public Content(String list_title,  String list_content) {
		this.list_title = list_title;
		this.list_content = list_content;
	}
	
	public Content(String list_id, String list_title, String list_date, String pet_id, String list_content) {
		this.list_id = list_id;
		this.list_title = list_title;
		this.list_date = list_date;
		this.pet_id = pet_id;
		this.list_content = list_content;
	}	
	
	public String getPet_kinds() {
		return pet_kinds;
	}
	public String getPet_id() {
		return pet_id;
	}
	public void setPet_id(String pet_id) {
		this.pet_id = pet_id;
	}
	public void setPet_kinds(String pet_kinds) {
		this.pet_kinds = pet_kinds;
	}
	public String getPet_race() {
		return pet_race;
	}
	public void setPet_race(String pet_race) {
		this.pet_race = pet_race;
	}
	public int getPet_age() {
		return pet_age;
	}
	public void setPet_age(int pet_age) {
		this.pet_age = pet_age;
	}
	public String getPet_gender() {
		return pet_gender;
	}
	public void setPet_gender(String pet_gender) {
		this.pet_gender = pet_gender;
	}
	public String getPet_vaccine() {
		return pet_vaccine;
	}
	public void setPet_vaccine(String pet_vaccine) {
		this.pet_vaccine = pet_vaccine;
	}
	public String getPet_price() {
		return pet_price;
	}
	public void setPet_price(String pet_price) {
		this.pet_price = pet_price;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getList_id() {
		return list_id;
	}
	public void setList_id(String list_id) {
		this.list_id = list_id;
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
