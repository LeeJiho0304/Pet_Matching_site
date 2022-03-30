package model;

public class Aftercare {
	
   private String ac_list_id;
   private String pet_id;
   private String ac_list_content;
   private String ac_list_date;
   private String ac_list_title;
   private String writer;
   
   public Aftercare() {   }
   
   public Aftercare(String ac_list_title, String ac_list_content, String writer) {
      this.ac_list_title = ac_list_title;
      this.ac_list_content = ac_list_content;
      this.writer = writer;
   }
   
   public Aftercare(String ac_list_id, String pet_id, String ac_list_content, String ac_list_date, 
		   String ac_list_title, String writer) {
      super();
      this.ac_list_id = ac_list_id;
      this.pet_id = pet_id;
      this.ac_list_content = ac_list_content;
      this.ac_list_date = ac_list_date;
      this.ac_list_title = ac_list_title;
      this.writer = writer;
   }

   public String getAc_list_id() {
      return ac_list_id;
   }
   public void setAc_list_id(String ac_list_id) {
      this.ac_list_id = ac_list_id;
   }
   public String getPet_id() {
      return pet_id;
   }
   public void setPet_id(String pet_id) {
      this.pet_id = pet_id;
   }
   public String getAc_list_content() {
      return ac_list_content;
   }
   public void setAc_list_content(String ac_list_content) {
      this.ac_list_content = ac_list_content;
   }
   public String getAc_list_date() {
      return ac_list_date;
   }
   public void setAc_list_date(String ac_list_date) {
      this.ac_list_date = ac_list_date;
   }
   public String getAc_list_title() {
      return ac_list_title;
   }
   public void setAc_list_title(String ac_list_title) {
      this.ac_list_title = ac_list_title;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }   
   
}