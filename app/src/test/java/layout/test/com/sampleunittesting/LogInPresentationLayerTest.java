package layout.test.com.sampleunittesting;

import com.network.LogInService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by maidulislam on 24/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class LogInPresentationLayerTest {

    @Mock
    private LogInView view;
    private LogInService logInService;
    private LogInPresentationLayer presentationLayer;
    @Before
    public void setUp() throws Exception{


        presentationLayer =new LogInPresentationLayer(view,logInService);
    }
    @Test
    public void showErrorEmailId_IsEmpty() throws Exception{
        when(view.getUseName()).thenReturn("");
        presentationLayer.onLogInClicked();
        verify(view).showErrorMessageUserName(R.string.username_error);
    }
    @Test
    public void showErrorMessageForPasswordEmpty() throws Exception{
        when(view.getUseName()).thenReturn("9910510346");
        when(view.getPassWord()).thenReturn("");
        presentationLayer.onLogInClicked();
        verify(view).showErrorMessagePassword(R.string.password_error);
    }

    @Test
    public void startLogInwhenUserandPasswrdCorrect(){
        when(view.getUseName()).thenReturn("9910510346");
        when(view.getPassWord()).thenReturn("1234");
        when(logInService.login("9910510346","1234")).thenReturn(true);
        presentationLayer.onLogInClicked();
        verify(view).showErrorMessageTokenResponse(R.string.fullname);
    }


}
