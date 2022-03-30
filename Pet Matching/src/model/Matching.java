package model;

public class Matching {
	
	private String matching_id;
	private String pet_id;
	private String matching_date;
	private String user_id;
	private int matching_state;
	private String list_id;
	
	public Matching() { }
		public Matching(String pet_id, String matching_date, String user_id, int matching_state, String list_id) {
		this.pet_id = pet_id;
		this.matching_date = matching_date;
		this.user_id = user_id;
		this.matching_state = matching_state;
		this.list_id = list_id;
	}	
	
	public String getMatching_id() {
		return matching_id;
	}
	public void setMatching_id(String matching_id) {
		this.matching_id = matching_id;
	}
	public String getPet_id() {
		return pet_id;
	}
	public void setPet_id(String pet_id) {
		this.pet_id = pet_id;
	}
	public String getMatching_date() {
		return matching_date;
	}
	public void setMatching_date(String matching_date) {
		this.matching_date = matching_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getMatching_state() {
		return matching_state;
	}
	public void setMatching_state(int matching_state) {
		this.matching_state = matching_state;
	}
	public String getList_id() {
		return list_id;
	}
	public void setList_id(String list_id) {
		this.list_id = list_id;
	}
	
}
