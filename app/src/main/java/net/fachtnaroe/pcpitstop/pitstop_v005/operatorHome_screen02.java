package net.fachtnaroe.pcpitstop.pitstop_v005;

/**
 * Created by fachtna on 14/03/18.
 */

import android.content.Intent;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListPicker;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.TinyDB;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;


import org.json.*;

public class operatorHome_screen02 extends Form implements HandlesEventDispatching {

    private Button jobsAddButton, jobsEditButton;
    private Label aLabel;
    public Web webComponent;
    public VerticalScrollArrangement screenContainer;
    private HorizontalArrangement aLine;
    public TextBox debugBox;
    public static String targetURL = "https://fachtnaroe.net/pcpitstop-2018?iam=fachtna&";
    private TinyDB myFile;
    private String sessionID=new String();

    public void operatorHome_screen2_() {
        // Constructor
//        sessionID=(String) myFile.GetValue((String)"sessionID", -1);
    }

    protected void $define() {

        screenContainer = new VerticalScrollArrangement(this);
        screenContainer.WidthPercent(100);
        screenContainer.HeightPercent(100);
        myFile = new TinyDB(screenContainer);

        VerticalArrangement jobsVertical = new VerticalArrangement(screenContainer);
        ListPicker jobsList = new ListPicker(jobsVertical);
        HorizontalArrangement jobsControl = new HorizontalArrangement(jobsVertical);
        jobsAddButton = new Button(jobsControl);
        jobsEditButton = new Button (jobsControl);
        debugBox = new TextBox(screenContainer);
        debugBox.WidthPercent(100);
        debugBox.Text("");

        EventDispatcher.registerEventForDelegation(this, "jobsAddButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "jobsEditButton", "Click");

    }


    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (component.equals(jobsAddButton) && eventName.equals("Click")) {
            jobsAddButtonClick();
            return true;
        }
        else if (component.equals(jobsEditButton) && eventName.equals("Click")) {
            jobsEditButtonClick();
            return true;
        }
        return false;
    }

    void jobsAddButtonClick(){

    }
    void jobsEditButtonClick(){

    }

}