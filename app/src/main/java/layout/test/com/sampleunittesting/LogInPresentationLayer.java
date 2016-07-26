package layout.test.com.sampleunittesting;

import com.network.LogInService;

/**
 * Created by maidulislam on 24/06/16.
 */
public class LogInPresentationLayer {
    private LogInService logInService;
    private LogInView view;

    public boolean isResponse() {
        return response;
    }

    boolean response=false;
    String TAG = "LogInPresentationLayer";
    LogIn logIn=new LogIn();

    public LogInPresentationLayer(LogInView view, LogInService logInService) {
        this.view = view;
        this.logInService=logInService;
    }



    public void onLogInClicked() {
        String userName = view.getUseName();

        if (userName.isEmpty()) {
            view.showErrorMessageUserName(R.string.username_error);
            return;
        }

        String passWord = view.getPassWord();

        if (passWord.isEmpty()) {
            view.showErrorMessagePassword(R.string.password_error);
            return;
        }

        int iTokenStatus = view.getStatusCode();

        if (iTokenStatus != 200) {
            view.showErrorMessageTokenResponse(R.string.token_error);
        }


        boolean loginSucceeded = logInService.login(userName, passWord);
        if (loginSucceeded) {

            return;
        }
        view.showLoginError(R.string.login_error);
    }




    public void setResponse(boolean response) {

        this.response = response;
    }


}
