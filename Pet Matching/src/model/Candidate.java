package model;

import java.util.List;

public class Candidate{
	
	private String list_id;
	private List<Member> user_list;
	
	public Candidate() {	}
	
	public Candidate(String list_id, List<Member> user_list) {
		super();
		this.list_id = list_id;
		this.user_list = user_list;
	}
	
	public String getList_id() {
		return list_id;
	}
	public void setList_id(String list_id) {
		this.list_id = list_id;
	}
	public void setUser_list(List<Member> user_list){
		this.user_list = user_list;
	}
	public List<Member> getUser_list(){
		return user_list;
	}
	
}
