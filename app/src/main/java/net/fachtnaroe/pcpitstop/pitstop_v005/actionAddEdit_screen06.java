package net.fachtnaroe.pcpitstop.pitstop_v005;

/**
 * Created by fachtna on 14/03/18.
 */

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;

import org.json.*;

public class actionAddEdit_screen06 extends Form implements HandlesEventDispatching {

    private Button aButton;
    private Label aLabel;
    public Web webComponent;
    public static VerticalScrollArrangement screenContainer;
    private HorizontalArrangement aLine;
    public static TextBox debugLabel;
    public static String targetURL = "https://fachtnaroe.net/pcpitstop-2018?";
//    public static String targetURL = remoteHost + remoteApp;

    public void operatorHome_screen2_() {
        // Constructor
    }

    protected void $define() {

        screenContainer = new VerticalScrollArrangement(this);
        screenContainer.WidthPercent(100);
        screenContainer.HeightPercent(100);

    }

}
