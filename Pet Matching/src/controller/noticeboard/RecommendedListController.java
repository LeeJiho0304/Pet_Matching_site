package controller.noticeboard;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Content;
import model.MatNotice;
import model.dao.MatchingDAO;
import model.dao.NoticeBoardDAO;

public class RecommendedListController implements Controller{
	
   private MatchingDAO matchingDAO = new MatchingDAO();
   private NoticeBoardDAO noticeboardDAO = new NoticeBoardDAO();
   
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   
      List<MatNotice> applicationList = matchingDAO.getMyApplicationList(UserSessionUtils.getUserFromSession(request.getSession()));
      
      // applicationList에 나의 신청리스트 항목 담고, 신청한 항목 중 가장 많이 신청된 펫의 race를  mostfavorite 항목 저장 
      int cnt = 0;
      int max = 0;
      String[] race = new String[applicationList.size()];
      
      for(int i = 0; i < applicationList.size(); i++){
         race[i] = applicationList.get(i).getRace();
      }
      Arrays.sort(race);
      
      String tmp = race[0];
      String mostFavorite = race[0];
      cnt++;//1
      for(int i = 1; i < race.length; i++){
         if(tmp.equals(race[i])){
            cnt++;
         }else{
            tmp = race[i];
            cnt = 1;
         }
   
         if(max < cnt){
               max = cnt;
               mostFavorite = race[i];   
         }
      }
      
      //matchingDAO.getMyRecommendedList 호출해서 mostfavorite( race )를 매개변수로보내줌, 매개변수에 해당하는 list 불러옴 
      List<Content> rec_content_list = noticeboardDAO.getMyRecommendedList(mostFavorite);
      request.setAttribute("rec_content_list", rec_content_list);
      request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));

      // 나의 추천 리스트 화면으로 이동(forwarding)
      return "/noticeboard/MyRecommended_list.jsp";
   }
}
