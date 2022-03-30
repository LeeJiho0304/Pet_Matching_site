package model;

public class Member {
	
	private String userId;
	private String name;
	private String password;
	private String res_id;
	private int age;
	private String gender;
	private String family_mem;
	private int pet_cnt;
	private String address;
	private String phone;
	private String job;
	private String profile;

	public Member() { }
	
	public Member(String userId, String name, String password, String res_id, int age, String gender, 
			String family_mem, int pet_cnt,	String address, String phone, String job, String profile) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.res_id = res_id;
		this.age = age;
		this.gender = gender;
		this.family_mem = family_mem;
		this.pet_cnt = pet_cnt;
		this.address = address;
		this.phone = phone;
		this.job = job;
		this.profile = profile;
	}

	public String getRes_id() {
		return res_id;
	}
	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFamily_mem() {
		return family_mem;
	}
	public void setFamily_mem(String family_mem) {
		this.family_mem = family_mem;
	}
	public int getPet_cnt() {
		return pet_cnt;
	}
	public void setPet_cnt(int pet_cnt) {
		this.pet_cnt = pet_cnt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", phone="
				+ phone + "]";
	}	
}
