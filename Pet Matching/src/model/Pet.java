package model;

public class Pet {
	
   private String pet_id;
   private String user_id;
   private String pet_name;
   private String kinds;
   private String race;
   private int age;
   private String pet_gender;
   private String vaccine;
   private String diagnosis_date;
   private int price;
   
   public Pet() {   }
   
   public Pet(String pet_id, String pet_name, String kinds, String race, int age, String pet_gender, 
		   String vaccine, String diagnosis_date, int price) {
      super();
      this.pet_id = pet_id;
      this.pet_name = pet_name;
      this.kinds = kinds;
      this.race = race;
      this.age = age;
      this.pet_gender = pet_gender;
      this.vaccine = vaccine;
      this.diagnosis_date = diagnosis_date;
      this.price = price;
   }

   public Pet(String pet_id, String user_id, String pet_name, String kinds, String race, int age, 
		   String pet_gender, String vaccine, String diagnosis_date, int price) {
      super();
      this.pet_id = pet_id;
      this.user_id = user_id;
      this.pet_name = pet_name;
      this.kinds = kinds;
      this.race = race;
      this.age = age;
      this.pet_gender = pet_gender;
      this.vaccine = vaccine;
      this.diagnosis_date = diagnosis_date;
      this.price = price;
   }

   public String getUser_id() {
      return user_id;
   }
   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }
   public String getPet_id() {
      return pet_id;
   }
   public void setPet_id(String pet_id) {
      this.pet_id = pet_id;
   }
   public String getPet_name() {
      return pet_name;
   }
   public void setPet_name(String pet_name) {
      this.pet_name = pet_name;
   }
   public String getKinds() {
      return kinds;
   }
   public void setKinds(String kinds) {
      this.kinds = kinds;
   }
   public String getRace() {
      return race;
   }
   public void setRace(String race) {
      this.race = race;
   }
   public int getAge() {
      return age;
   }
   public void setAge(int age) {
      this.age = age;
   }
   public String getPet_gender() {
      return pet_gender;
   }
   public void setPet_gender(String pet_gender) {
      this.pet_gender = pet_gender;
   }
   public String getVaccine() {
      return vaccine;
   }
   public void setVaccine(String vaccine) {
      this.vaccine = vaccine;
   }
   public String getDiagnosis_date() {
      return diagnosis_date;
   }
   public void setDiagnosis_date(String diagnosis_date) {
      this.diagnosis_date = diagnosis_date;
   }
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   
}
