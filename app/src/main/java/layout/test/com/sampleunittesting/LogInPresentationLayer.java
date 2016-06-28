package layout.test.com.sampleunittesting;

/**
 * Created by maidulislam on 24/06/16.
 */
public class LogInPresentationLayer {;
    private LogInView view;
    String TAG = "LogInPresentationLayer";
    public LogInPresentationLayer(LogInView view){
    this.view=view;
    }

    public void onLogInClicked() {
    String userName=view.getUseName();

        if(userName.isEmpty()){
        view.showErrorMessageUserName(R.string.username_error);
            return;
        }

    String passWord=view.getPassWord();

        if(passWord.isEmpty()){
            view.showErrorMessagePassword(R.string.password_error);
            return;
        }



    }
}
