package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.aftercare.ContentAftercareController;
import controller.aftercare.DeleteAftercareController;
import controller.aftercare.InsertAftercareController;
import controller.aftercare.InsertAftercareFormController;
import controller.aftercare.ListAftercareController;
import controller.aftercare.UpdateAftercareController;
import controller.matching.*;
import controller.mine.ContentPetController;
import controller.mine.DeletePetController;
import controller.mine.InsertPetController;
import controller.mine.ListPetController;
import controller.mine.MyPageController;
import controller.mine.UpdatePetController;
import controller.noticeboard.ContentNoticeBoardController;
import controller.noticeboard.DeleteNoticeBoardController;
import controller.noticeboard.InsertNoticeBoardController;
import controller.noticeboard.InsertNoticeboardFormController;
import controller.noticeboard.ListNoticeBoardController;
import controller.noticeboard.RecommendedListController;
import controller.noticeboard.UpdateNoticeBoardController;
import controller.user.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	 mappings.put("/", new ForwardController("index.jsp"));
    	 
         mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
         mappings.put("/user/login", new LoginController());
         mappings.put("/user/logout", new LogoutController());
         
         mappings.put("/user/view", new ViewUserController());
         mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
         mappings.put("/user/register", new RegisterUserController());
         mappings.put("/user/update", new UpdateUserController());

         mappings.put("/mine/mypage", new MyPageController("/mine/MyPage.jsp"));
         mappings.put("/mine/petlist", new ListPetController());
         mappings.put("/mine/pet/update/form", new UpdatePetController());
         mappings.put("/mine/pet/update", new UpdatePetController());
         mappings.put("/mine/pet/insert/form", new ForwardController("/mine/PetInsertForm.jsp"));
         mappings.put("/mine/pet/insert", new InsertPetController());
         mappings.put("/mine/pet/content", new ContentPetController());
         mappings.put("/mine/pet/delete", new DeletePetController());
         
         mappings.put("/aftercare/list", new ListAftercareController());
         mappings.put("/aftercare/insert/form", new InsertAftercareFormController("/aftercare/AftercareInsertForm.jsp"));
         mappings.put("/aftercare/insert", new InsertAftercareController());
         mappings.put("/aftercare/content", new ContentAftercareController());
         mappings.put("/aftercare/update/form", new UpdateAftercareController());
         mappings.put("/aftercare/update", new UpdateAftercareController());
         mappings.put("/aftercare/delete", new DeleteAftercareController());
         
         mappings.put("/noticeboard/list", new ListNoticeBoardController());
         mappings.put("/noticeboard/delete", new DeleteNoticeBoardController());
         mappings.put("/noticeboard/insert/form", new InsertNoticeboardFormController("/noticeboard/NoticeboardInsertForm.jsp"));
         mappings.put("/noticeboard/insert", new InsertNoticeBoardController());
         mappings.put("/noticeboard/content", new ContentNoticeBoardController());
         mappings.put("/noticeboard/update/form", new UpdateNoticeBoardController());
         mappings.put("/noticeboard/update", new UpdateNoticeBoardController());
         mappings.put("/noticeboard/recommended", new RecommendedListController());
         
         mappings.put("/matching/noticeboard", new ContentNoticeBoardController());
         mappings.put("/matching/myapplicationlist", new ListApplicationController());
         mappings.put("/matching/candidatelist", new ListCandidateController());
         mappings.put("/matching/insert", new MatchingController());
         mappings.put("/matching/update", new MatchingController());
         mappings.put("/matching/delete", new DeleteApplyController());
         mappings.put("/matching/mymatching", new ListCompleteMatchingController());
         
         logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	//주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
