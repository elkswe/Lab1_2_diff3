package catalog.service.managers;

import catalog.Main;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.List;

public final class LoaderManager {
    public static final int ADMIN_PAGE = 0;
    public static final int GUEST_PAGE = 1;
    public static final int LOGIN_PAGE = 2;
    public static final int SIGNUP_PAGE = 3;
    public static final int USER_PAGE = 4;

    private static final String ADMIN_URL = "view/administrator/AdminView.fxml";
    private static final String GUEST_URL = "view/guest/GuestView.fxml";
    private static final String LOGIN_URL = "view/login/LogInView.fxml";
    private static final String SIGNUP_URL = "view/login/SignUpView.fxml";
    private static final String USER_URL = "view/user/UserView.fxml";

    private static final int PAGE_COUNT = 5;

    private static List<String> urlList = new ArrayList<>(PAGE_COUNT);

    static {
        urlList.add(ADMIN_URL);
        urlList.add(GUEST_URL);
        urlList.add(LOGIN_URL);
        urlList.add(SIGNUP_URL);
        urlList.add(USER_URL);
    }

    public LoaderManager() {
    }

    public static FXMLLoader getLoader(int pageNum) {
        if (pageNum < PAGE_COUNT) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(urlList.get(pageNum)));
            return loader;
        }
        return null;
    }

}
