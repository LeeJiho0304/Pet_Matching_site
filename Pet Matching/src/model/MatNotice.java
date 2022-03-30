package model;

public class MatNotice {
	
   private String list_id;
   private String list_title;
   private int matching_state;
   private String race;
   
   public MatNotice() {	}      // 기본 생성자
   
   public MatNotice(String list_id, String list_title, int matching_state, String race) {
      super();
      this.list_id = list_id;
      this.list_title = list_title;
      this.matching_state = matching_state;
      this.race = race;
   }
   
   public String getRace() {
      return race;
   }
   public void setRace(String race) {
      this.race = race;
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
   public int getMatching_state() {
      return matching_state;
   }
   public void setMatching_state(int matching_state) {
      this.matching_state = matching_state;
   }
   
}