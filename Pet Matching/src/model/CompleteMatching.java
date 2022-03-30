package model;

public class CompleteMatching {
	
	private String owner_id;
	private String app_id;
	private String pet_id;
	private String pet_name;	
	
	public CompleteMatching() {	}
	
	public CompleteMatching(String owner_id, String app_id, String pet_id, String pet_name) {
		super();
		this.owner_id = owner_id;
		this.app_id = app_id;
		this.pet_id = pet_id;
		this.pet_name = pet_name;
	}
	
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getPet_id() {
		return pet_id;
	}
	public void setPet_id(String pet_id) {
		this.pet_id = pet_id;
	}
	
}
