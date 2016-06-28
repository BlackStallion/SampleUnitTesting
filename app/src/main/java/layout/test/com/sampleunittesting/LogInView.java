package layout.test.com.sampleunittesting;

/**
 * Created by maidulislam on 24/06/16.
 */
public interface LogInView {
    String getUseName();

    void showErrorMessageUserName(int resId);

    String getPassWord();

    void showErrorMessagePassword(int resId);


}
