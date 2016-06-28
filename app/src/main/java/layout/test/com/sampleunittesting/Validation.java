package layout.test.com.sampleunittesting;

/**
 * Created by maidulislam on 24/06/16.
 */
public class Validation {

    public static int checkUsername(String strUserName) {
        int i=0;
        if(strUserName==null){
            i=1;

        }
        String usernameArr[] = strUserName.split(" ");
        if(usernameArr.length>1){
            i=2;
        }

        return i;
    }

    public static int checkPassWord(String strPassword) {
        int i=0;
        if(strPassword.toString().trim().length() != 4){
            return i=1;
        }
        return i;
    }
}
