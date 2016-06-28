package layout.test.com.sampleunittesting;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.network.CommonNetworkClass;

import iNterface.ResponseData;
import utils.Constants;
import utils.Log;

public class LogIn extends AppCompatActivity implements View.OnClickListener, LogInView, ResponseData{

    ResponseData responseData;
    private EditText edt_UserName;
    private EditText edt_Password;
    private Button btn_LogIn;
    private LogInPresentationLayer logInPresentationLayer;
    ProgressDialog pDialog;
    private Context context=null;
    CommonNetworkClass commonNetworkClass;
    String TAG = "LogIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        edt_UserName= (EditText) findViewById(R.id.username);
        edt_Password= (EditText) findViewById(R.id.password);
        btn_LogIn= (Button) findViewById(R.id.login);
        context=LogIn.this;

//        responseData=new ResponseData() {
//            @Override
//            public void responseData(int statusCode, String responseData, String PresentUrl) {
//                if(Constants.url_initialize_access_token.equalsIgnoreCase(PresentUrl)){
//                    Log.d(TAG,"MAIDUL TESTING>>>>>>>>>>>>>>>>>>>>>>>>");
//                    Log.d(TAG,"responseData "+responseData);
//                }
//            }
//        };

        getAccessToken(context);

        pDialog = new ProgressDialog(context);
        btn_LogIn.setOnClickListener(this);

        logInPresentationLayer=new LogInPresentationLayer(this);


    }



    @Override
    public void onClick(View view) {

        logInPresentationLayer.onLogInClicked();

        Toast.makeText(getApplicationContext(),"Testing",Toast.LENGTH_SHORT).show();
//        String strUserName=edt_UserName.getText().toString();
//        String strPassword=edt_Password.getText().toString();
//        boolean isValid=true;
//
//        int iUsername=Validation.checkUsername(strUserName);
//        switch (iUsername){
//            case 0:
//                edt_UserName.setError("Something is wrong Please check");
//                isValid=false;
//                break;
//            case 1:
//                edt_UserName.setError("User Name should not be Empty");
//                isValid=false;
//                break;
//            case 2:
//                edt_UserName.setError("Please insert First and Last name");
//                isValid=false;
//            default:
//                break;
//        }
//        int iPass=Validation.checkPassWord(strPassword);
//        switch (iPass){
//            case 0:
//                edt_Password.setError("Something is wrong Please check");
//                isValid=false;
//                break;
//            case 1:
//                edt_Password.setError("Please enter your 4 digit PIN");
//                isValid=false;
//                break;
//            default:
//                break;
//        }


    }


    public void getAccessToken(Context context) {
        int flags = Constants.FLAG_SHOW_LOGS | Constants.FLAG_SHOW_LOADER;
        commonNetworkClass=new CommonNetworkClass(context);
        commonNetworkClass.NetworkHandlerResponseData(context,flags,pDialog,Constants.url_initialize_access_token);

    }

    @Override
    public String getUseName() {
        return edt_UserName.getText().toString();
    }

    @Override
    public void showErrorMessageUserName(int resId) {
        edt_UserName.setError(getString(resId));
    }

    @Override
    public String getPassWord() {
        return edt_Password.getText().toString();
    }

    @Override
    public void showErrorMessagePassword(int resId) {
        edt_Password.setError(getString(resId));
    }


    @Override
    public void responseData(int statusCode, String responseData, String PresentUrl) {
        if(statusCode==200) {
            if (Constants.url_initialize_access_token.equalsIgnoreCase(PresentUrl)) {
                Log.d(TAG, "MAIDUL TESTING>>>>>>>>>>>>>>>>>>>>>>>>" + statusCode);
                Log.d(TAG, "responseData " + responseData);
            }
        }
    }
}
