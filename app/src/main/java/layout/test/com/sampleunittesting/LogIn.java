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
import com.network.LogInService;

import org.json.JSONException;
import org.json.JSONObject;

import iNterface.ResponseData;
import utils.Constants;
import utils.Log;
import utils.TokenSavedData;

public class LogIn extends AppCompatActivity implements View.OnClickListener, LogInView, ResponseData{

    ResponseData responseData;
    private EditText edt_UserName;
    private EditText edt_Password;
    private Button btn_LogIn;
    private LogInPresentationLayer logInPresentationLayer;
    ProgressDialog pDialog;
    private Context context=null;
    String TAG = "LogIn";
    int statusCode=0;
    boolean bool_response=false;
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
//                if(Constants.TAUTH_SERVICE_INITIALIZE_TOKEN.equalsIgnoreCase(PresentUrl)){
//                    Log.d(TAG,"MAIDUL TESTING>>>>>>>>>>>>>>>>>>>>>>>>");
//                    Log.d(TAG,"responseData "+responseData);
//                }
//            }
//        };

        getAccessToken(context);

        pDialog = new ProgressDialog(context);
        btn_LogIn.setOnClickListener(this);

        logInPresentationLayer=new LogInPresentationLayer(this,new LogInService());


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
        JSONObject params = new JSONObject();
        params = TokenSavedData.SetAccessTokenToJsonObject(context, params);
        int flags = Constants.FLAG_SHOW_LOGS | Constants.FLAG_SHOW_LOADER;
        new CommonNetworkClass(context).NetworkHandlerResponseData(context,flags,pDialog,Constants.TAUTH_SERVICE_INITIALIZE_TOKEN,Constants.ID_TOKEN,params);
//        boolean boolToken=new CommonNetworkClass(context).NetworkHandlerResponseData(context,flags,pDialog,Constants.TAUTH_SERVICE_INITIALIZE_TOKEN,Constants.ID_TOKEN);
//        Log.d(TAG,"boolToken >> "+boolToken);

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
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public void showErrorMessageTokenResponse(int resId) {
        Toast.makeText(context,getString(resId),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(int resId) {
        Toast.makeText(context,getString(resId),Toast.LENGTH_SHORT).show();
    }


    @Override
    public void responseData(int statusCode, String responseData, String PresentUrl, int URL_ID, boolean bool_response) {


            switch (statusCode){
                case 200:
                    switch (URL_ID){
                        case Constants.ID_TOKEN:
                            Log.d(TAG, "MAIDUL TESTING>>>>>>>>>>>>>>>>>>>>>>>>" + statusCode);
                            Log.d(TAG, "MAIDUL TESTING>>>>>>>>>>>>>>>>>>>>>>>> responseData \n" + responseData);
                            this.statusCode=statusCode;
                            this.bool_response=bool_response;

                            try {
                                JSONObject a = new JSONObject(responseData);
                                JSONObject b = a.getJSONObject("token");
                                TokenSavedData.savePreferencesToken("token", b.toString(), LogIn.this);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            break;
                        case Constants.ID_LOGIN:
                            this.bool_response=bool_response;
                            this.statusCode=statusCode;
                            logInPresentationLayer.setResponse(bool_response);
                            break;

                    }
                case 400:
                    Toast.makeText(context,"Bad Token ",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    this.statusCode=statusCode;
            }
        }

    public void userLogin(String userName,String password){
        JSONObject params = new JSONObject();
        params = TokenSavedData.SetAccessTokenToJsonObject(context, params);
        JSONObject a = new JSONObject();

        try {
            a.put("mobile", userName);
            a.put("pin", password);  //need to change
            params.put("data", a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("login params", params.toString());
        int flags = Constants.FLAG_SHOW_LOGS | Constants.FLAG_SHOW_LOADER;
        new CommonNetworkClass(context).NetworkHandlerResponseData(context,flags,pDialog,Constants.URL_LOGIN,Constants.ID_TOKEN,params);


    }


}

