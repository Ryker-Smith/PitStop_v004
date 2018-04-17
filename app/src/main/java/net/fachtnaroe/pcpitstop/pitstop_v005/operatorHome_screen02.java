package net.fachtnaroe.pcpitstop.pitstop_v005;

/**
 * Created by fachtna on 14/03/18.
 */

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
//import android.widget.ListView;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListPicker;
import com.google.appinventor.components.runtime.ListView;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.TinyDB;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.util.YailList;


import org.json.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class operatorHome_screen02 extends Form implements HandlesEventDispatching {

    // LOOK AT TinyDB ...
    //https://groups.google.com/forum/#!topic/app-inventor-open-source-dev/O1gR1MVOGNY

    private Button jobAddButton, jobEditButton, customerAddButton, customerEditButton;
    private Label aLabel;
    public Web customerList_webComponent, jobList_webComponent;
    public VerticalScrollArrangement screenContainer;
    private HorizontalArrangement topLine;
    public TextBox debugBox;
    public static String targetURL = "https://fachtnaroe.net/pcpitstop-2018?iam=fachtna&";
    private TinyDB myFile;
    private String sessionID;
    private ListView customerList, jobList;
    private ArrayList<String> listData;
    private person myPerson;
    private Notifier myNotify;
    private JSONArray customerData, jobData;

    public void operatorHome_screen02() {
        // Constructor
    }

    public static int getScreenWidth()
    {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    //get height of screen
    public static int getScreenHeight()
    {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    protected void $define() {

        Bundle b = getIntent().getExtras();
        if (b != null) {
            sessionID = b.getString("sessionID");
        }
        myPerson = new person();
        screenContainer = new VerticalScrollArrangement(this);
        screenContainer.Height(getScreenHeight());
        screenContainer.Width(getScreenWidth());
        myNotify = new Notifier(screenContainer);
        topLine = new HorizontalArrangement(screenContainer);
        topLine.Width(LENGTH_FILL_PARENT);
        Label titleLabel = new Label(topLine);
        titleLabel.Width(LENGTH_FILL_PARENT);

        titleLabel.FontSize(20);
        titleLabel.FontTypeface(TYPEFACE_SERIF);
        titleLabel.FontBold(true);
        titleLabel.HTMLFormat(true);
        titleLabel.Text("Operator Home");
        VerticalArrangement customersVertical = new VerticalArrangement(screenContainer);
        customersVertical.Width(LENGTH_FILL_PARENT);
        Label customersLabel = new Label(customersVertical);
        customersLabel.Width(LENGTH_FILL_PARENT);
        customersLabel.FontBold(true);
//        customersLabel.TextAlignment(ALIGNMENT_CENTER);
        customersLabel.Text("Customers");
        customersLabel.FontSize(15);
        customersLabel.FontTypeface(TYPEFACE_SERIF);
        customerList = new ListView(customersVertical);
        customerList.Width(LENGTH_FILL_PARENT);
        customerList.HeightPercent(40);
        customerList.SelectionColor(COLOR_LTGRAY);
        customerList.ShowFilterBar(true);
        customerList.TextSize(40);
        customerList.TextColor(COLOR_BLACK);
        customerList.BackgroundColor(COLOR_WHITE);

        HorizontalArrangement customersControl = new HorizontalArrangement(customersVertical);
        customersControl.Width(LENGTH_FILL_PARENT);
        customerAddButton = new Button(customersControl);
        customerAddButton.WidthPercent(50);
        customerAddButton.Text("Add");
        customerEditButton = new Button(customersControl);
        customerEditButton.WidthPercent(50);
        customerEditButton.Text("Edit");

        VerticalArrangement jobsVertical = new VerticalArrangement(screenContainer);
        jobsVertical.Width(LENGTH_FILL_PARENT);
        Label jobsLabel = new Label(jobsVertical);
        jobsLabel.Text("Jobs");
        jobsLabel.FontSize(15);
        jobsLabel.FontTypeface(TYPEFACE_SERIF);
        jobsLabel.Width(LENGTH_FILL_PARENT);
        jobsLabel.FontBold(true);
//        jobsLabel.TextAlignment(ALIGNMENT_CENTER);
        jobList = new ListView(jobsVertical);
        jobList.Width(LENGTH_FILL_PARENT);
        jobList.HeightPercent(40);
        jobList.SelectionColor(COLOR_LTGRAY);
        jobList.ShowFilterBar(true);
        jobList.TextSize(40);
        jobList.TextColor(COLOR_BLACK);
        jobList.BackgroundColor(COLOR_WHITE);

        HorizontalArrangement jobsControl = new HorizontalArrangement(jobsVertical);
        jobsControl.Width(LENGTH_FILL_PARENT);
        jobAddButton = new Button(jobsControl);
        jobAddButton.WidthPercent(50);
        jobAddButton.Text("Add");
        jobEditButton = new Button(jobsControl);
        jobEditButton.WidthPercent(50);
        jobEditButton.Text("Edit");

        EventDispatcher.registerEventForDelegation(this, "jobsAddButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "jobsEditButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "customerList_webComponent", "GotText");
        EventDispatcher.registerEventForDelegation(this, "jobsList_webComponent", "GotText");
        EventDispatcher.registerEventForDelegation(this, "listPicker", "AfterPicking");
        EventDispatcher.registerEventForDelegation(this, "customerList", "AfterPicking");
        EventDispatcher.registerEventForDelegation(this, "myNotify", "AfterChoosing");

        customerList_webComponent = new Web(screenContainer);
        customerList_webComponent.Url(targetURL + "&" +
                customerAddEdit_screen04.RequestCombine(new String[]{
                        customerAddEdit_screen04.RequestValue("sessionID", sessionID),
                        customerAddEdit_screen04.RequestValue("entity", "person")
                }));
        customerList_webComponent.Get();
        jobList_webComponent = new Web(screenContainer);
        jobList_webComponent.Url(targetURL + "&" +
                customerAddEdit_screen04.RequestCombine(new String[]{
                        customerAddEdit_screen04.RequestValue("sessionID", sessionID),
                        customerAddEdit_screen04.RequestValue("entity", "job")
                }));
        jobList_webComponent.Get();
//        jobAddButton.Shape(BUTTON_SHAPE_ROUNDED);
        this.HideKeyboard();
        HideKeyboard();
    }

    @Override
    public void onBackPressed() {
        this.finish();
        return;
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (component.equals(jobAddButton) && eventName.equals("Click")) {
            jobAddButtonClick();
            return true;
        }
        else if (component.equals(jobEditButton) && eventName.equals("Click")) {
            jobEditButtonClick();
            return true;
        }
        else if (component.equals(jobList) && eventName.equals("AfterPicking")) {
            return true;
        }
        else if (component.equals(jobList_webComponent) && eventName.equals("GotText")) {
            String result = (String) params[3];
            gotJobs(result);
            return true;
        }
        else if (component.equals(myNotify) && eventName.equals("AfterChoosing")) {
            return true;
        }
        if (component.equals(customerAddButton) && eventName.equals("Click")) {
            customerAddButtonClick();
            return true;
        }
        else if (component.equals(customerEditButton) && eventName.equals("Click")) {
            customerEditButtonClick();
            return true;
        }
        else if (component.equals(customerList) && eventName.equals("AfterPicking")) {
            return true;
        }
        else if (component.equals(customerList_webComponent) && eventName.equals("GotText")) {
            String result = (String) params[3];
            gotCustomers(result);
            return true;
        }
        return false;
    }

    void customerAddButtonClick() {
        Intent intent = new Intent(this, customerAddEdit_screen04.class);
        Bundle b = new Bundle();
        b.putString("sessionID", sessionID); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    void customerEditButtonClick() {
          int pID = getCustomerID();
          if (pID != -1){
            Intent intent = new Intent(this, customerAddEdit_screen04.class);
            Bundle b = new Bundle();
            b.putString("sessionID", sessionID); //Your id
            b.putInt("pID", pID);
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
        }
    }

    void jobAddButtonClick() {
        Intent intent = new Intent(this, jobAddEdit_screen03.class);
        Bundle b = new Bundle();
        b.putString("sessionID", sessionID); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    void jobEditButtonClick() {
    }

    void gotCustomers(String result) {
        person myPerson = new person();
        try {
            JSONObject myJSONparser = new JSONObject(result);
            customerData = myJSONparser.getJSONArray("person");
            ArrayList<String> temp_NotYlist = new ArrayList<String>(myPerson.toList(customerData));
            YailList temp_Ylist = YailList.makeList(temp_NotYlist);
            customerList.Elements(temp_Ylist);
        } catch (JSONException e) {
            myNotify.ShowMessageDialog("Error getting customer list", "Error", "Grand");
        }
    }

    void gotJobs(String result) {
        try {
//            ArrayList<String> people = new ArrayList<String>();
            JSONObject myJSONparser = new JSONObject(result);
            jobData = myJSONparser.getJSONArray("job");
            ArrayList<String> temp_NotYlist = new ArrayList<String>(toJobList(jobData));
            YailList temp_Ylist = YailList.makeList(temp_NotYlist);
            jobList.Elements(temp_Ylist);
        } catch (JSONException e) {
            myNotify.ShowMessageDialog("Error getting job list", "Error", "Grand");
        }
    }

    public ArrayList<String> toJobList (JSONArray data) {
        ArrayList<String> jobs = new ArrayList<String>();
        try {
            for (int n=0; n<=data.length()-1;n++) {
                String line = "[pID " + data.getJSONObject(n).getString("pID")
                        + "] " + data.getJSONObject(n).getString("Details")
                        + " of " + data.getJSONObject(n).getString("Location");
                jobs.add(line);
                Log.i("Fachtna ",line);
            }
            return jobs;

        } catch (JSONException e) {
            // if an exception occurs, code for it in here
        }
        return null;
    }

    public int getCustomerID () {
        if (customerList.Selection() != null) {
//            Log.i("Fachtna", "NOT NULL [" + customerList.Selection() + "]");
            String editCust = customerList.Selection();
            if (editCust != "") {
                int start = editCust.indexOf('[');
                int end = editCust.indexOf(']');
                String custNum = editCust.substring(start + 1, end);
                custNum = custNum.replace("pID ", "");
                int pID = Integer.valueOf(custNum);
                return pID;
            }
            else {
                return -1;
            }

        }
        return -1;
    }
}

