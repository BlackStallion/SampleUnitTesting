package layout.test.com.sampleunittesting;

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
    private LogInPresentationLayer presentationLayer;
    @Before
    public void setUp() throws Exception{

        presentationLayer =new LogInPresentationLayer(view);
    }
    @Test
    public void showErrorEmailId_IsEmpty() throws Exception{
        when(view.getUseName()).thenReturn("");
        presentationLayer.onLogInClicked();
        verify(view).showErrorMessageUserName(R.string.username_error);
    }
    @Test
    public void showErrorMessageForPasswordEmpty() throws Exception{
        when(view.getUseName()).thenReturn("Maidul");
        when(view.getPassWord()).thenReturn("");
        presentationLayer.onLogInClicked();
        verify(view).showErrorMessagePassword(R.string.password_error);
    }

//    @Test
//    public void startLogInwhenUserIdandPasswrdCorrect(){
//        when(view.getUseName()).thenReturn("Maidul Islam");
//        when(view.getPassWord()).thenReturn("1234");
//        presentationLayer.onLogInClicked();
//        verify(view).showErrorMessagePassword(R.string.password_error);
//    }
}
