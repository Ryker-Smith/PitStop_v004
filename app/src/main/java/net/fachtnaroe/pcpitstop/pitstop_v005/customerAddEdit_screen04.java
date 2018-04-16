package net.fachtnaroe.pcpitstop.pitstop_v005;
// http://thunkableblocks.blogspot.ie/2017/07/java-code-snippets-for-app-inventor.html
//https://github.com/AppScale/sample-apps/blob/master/java/appinventor2/appinventor/components/tests/com/google/appinventor/components/runtime/WebTest.java

import android.os.Bundle;

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

//import static com.google.appinventor.components.runtime.util.YailList.makeList;

/**
 * Created by fachtna on 06/03/18.
 */

public class customerAddEdit_screen04 extends Form implements HandlesEventDispatching {

    private Button connectionButton, postButton, putButton, deleteButton, getButton;
    private Label firstLabel, familyLabel, emailLabel, phoneLabel, responseLabel, ppsLabel, urlLabel, debugSideLabel;
    public Web webComponent_POST, webComponent_PUT, webComponent_GET, webComponent_DELETE;
    public static VerticalScrollArrangement screenContainer;
    private HorizontalArrangement topLine, nextLine, ppsLine;
    public static TextBox firstBox, familyBox, emailBox, phoneBox,  outputBox, ppsBox, debugLabel;
    private Label address1Label, address2Label, address3Label;
    public static TextBox address1Box, address2Box, address3Box, pidBox;
    private static String remoteHost = "";
    public static String targetURL = "https://fachtnaroe.net/pcpitstop-2018?iam=fachtna&";
    private String sessionID;
    private int pID=-1;
//    public static String targetURL = remoteHost + remoteApp;

//    private int currentCustomer = -1;

    public customerAddEdit_screen04() {
    // Constructor
    }

    void oneFieldPair (Label labelName, String labelText, TextBox  boxName, String boxText) {

    }

    public static String RequestValue (String s1, String s2) {
        // Purpose: To combine two strings into a name=value pair
        // Expects: Two strings
        // Returns: One string of the form s1=s2
        return s1 + '=' + s2;
    }

    public static String RequestCombine (String[] Pairs) {
        // Purpose: To combine multiple strings (of the form name=value) into a web data string
        // Expects: Series of strings
        // Returns: One string of the form string&string&string
        int i=Pairs.length;
        int count = (int) i/2;
        String result = new String();
        for (int loop=0; loop<i; loop+=1){
            result += Pairs[loop];
            if (loop < (i-1)) {
                result += "&";
            }
        }
        return result;
    }

    protected void $define() {

        screenContainer = new VerticalScrollArrangement(this);
        screenContainer.WidthPercent(100);
        screenContainer.HeightPercent(100);

        topLine = new HorizontalArrangement(screenContainer);
        topLine.WidthPercent(100);
        Label titleLabel = new Label(topLine);
        titleLabel.Text("Add/edit Customer");
        titleLabel.FontSize(20);
        titleLabel.FontTypeface(Component.TYPEFACE_SERIF);
        titleLabel.WidthPercent(100);
        titleLabel.FontBold(true);
        titleLabel.TextAlignment(Component.ALIGNMENT_CENTER);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            sessionID = b.getString("sessionID");
            pID=b.getInt("pID");
        }

        HorizontalArrangement pidHoriz = new HorizontalArrangement(screenContainer);

        Label pidLabel = new Label(pidHoriz);
        pidLabel.Text("pID:");
        pidLabel.FontBold(true);
        pidBox = new TextBox(pidHoriz);
        pidBox.WidthPercent(15);
        if (pID != -1) {
            pidBox.Text(Integer.toString(pID));
        }
        pidBox.WidthPercent(25);
        getButton = new Button(pidHoriz);
        getButton.Text("Get existing customer");
        getButton.WidthPercent(60);
        getButton.Enabled(true);

        HorizontalArrangement firstHoriz = new HorizontalArrangement(screenContainer);
        firstHoriz.WidthPercent(100);
        firstLabel = new Label(firstHoriz);
        firstLabel.Text("First: ");
        firstLabel.WidthPercent(20);
        firstBox = new TextBox(firstHoriz);
        firstBox.WidthPercent(100);
        firstBox.Text("");

        HorizontalArrangement familyHoriz = new HorizontalArrangement(screenContainer);
        familyHoriz.WidthPercent(100);
        familyLabel = new Label(familyHoriz);
        familyLabel.Text("Family: ");
        familyLabel.WidthPercent(20);
        familyBox = new TextBox(familyHoriz);
        familyBox.WidthPercent(100);
        familyBox.Text("");

        HorizontalArrangement emailHoriz = new HorizontalArrangement(screenContainer);
        emailHoriz.WidthPercent(100);

        oneFieldPair (emailLabel, "Email: ", emailBox, "");
        emailLabel = new Label(emailHoriz);
        emailLabel.Text("Email: ");
        emailLabel.WidthPercent(20);
        emailBox = new TextBox(emailHoriz);
        emailBox.WidthPercent(100);
        emailBox.Text("me@here.ie");

        HorizontalArrangement phoneHoriz = new HorizontalArrangement(screenContainer);
        phoneHoriz.WidthPercent(100);
        phoneLabel = new Label(phoneHoriz);
        phoneLabel.Text("Phone: ");
        phoneLabel.WidthPercent(20);
        phoneBox = new TextBox(phoneHoriz);
        phoneBox.WidthPercent(100);
        phoneBox.Text("");

        HorizontalArrangement address1Horiz = new HorizontalArrangement(screenContainer);
        address1Horiz.WidthPercent(100);
        address1Label = new Label(address1Horiz);
        address1Label.Text("Address1: ");
        address1Label.WidthPercent(20);
        address1Box = new TextBox(address1Horiz);
        address1Box.WidthPercent(100);
        address1Box.Text("");

        HorizontalArrangement address2Horiz = new HorizontalArrangement(screenContainer);
        address2Horiz.WidthPercent(100);
        address2Label = new Label(address2Horiz);
        address2Label.Text("Address2: ");
        address2Label.WidthPercent(20);
        address2Box = new TextBox(address2Horiz);
        address2Box.WidthPercent(100);
        address2Box.Text("");

        HorizontalArrangement address3Horiz = new HorizontalArrangement(screenContainer);
        address3Horiz.WidthPercent(100);
        address3Label = new Label(address3Horiz);
        address3Label.Text("Address3: ");
        address3Label.WidthPercent(20);
        address3Box = new TextBox(address3Horiz);
        address3Box.WidthPercent(100);
        address3Box.Text("");

        HorizontalArrangement ppsHoriz = new HorizontalArrangement(screenContainer);
        ppsHoriz.WidthPercent(100);
        ppsLabel = new Label(ppsHoriz);
        ppsLabel.Text("PPS: ");
        ppsLabel.WidthPercent(20);
        ppsBox = new TextBox(ppsHoriz);
        ppsBox.WidthPercent(100);
        ppsBox.Text("");

        HorizontalArrangement debugHoriz = new HorizontalArrangement(screenContainer);
        debugSideLabel = new Label(debugHoriz);
        debugSideLabel.WidthPercent(20);
        debugSideLabel.Text("Debug: ");
        debugLabel = new TextBox(debugHoriz);
        debugLabel.WidthPercent(100);
        debugLabel.Text("");

        HorizontalArrangement uploadHoriz = new HorizontalArrangement(screenContainer);
        uploadHoriz.WidthPercent(100);
        uploadHoriz.AlignHorizontal(1);
        postButton = new Button(uploadHoriz);
        postButton.Text("Save");
        postButton.WidthPercent(50);

        webComponent_POST = new Web(screenContainer);
        webComponent_PUT = new Web(screenContainer);
        webComponent_GET = new Web(screenContainer);
        webComponent_DELETE = new Web(screenContainer);

//        EventDispatcher.registerEventForDelegation(this, "connectButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "postButton", "Click");
//        EventDispatcher.registerEventForDelegation(this, "putButton", "Click");
//        EventDispatcher.registerEventForDelegation(this, "deleteButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "getButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "webComponent_POST", "GotText");
        if (pID != -1) {

//            debugLabel.Text("Clicked");
            this.getButtonClick();
//            closeForm(null);
//            finishAndRemoveTask();
        }
    }

//    @Override
    public void onBackPressed() {
        closeForm(null);
        finishAndRemoveTask();
        return;
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (component.equals(postButton) && eventName.equals("Click")) {
//            targetURL = urlBox.Text();
            debugLabel.Text(targetURL);
            postButton.Text("Saving");
            webComponent_POST.Url(targetURL);
            person myPerson = new person();
            EventDispatcher.registerEventForDelegation(this, "webComponent_POST", "GotText");
            myPerson.Set(new String[]{
                    firstBox.Text(),
                    familyBox.Text(),
                    emailBox.Text(),
                    phoneBox.Text(),
                    address1Box.Text(),
                    address2Box.Text(),
                    address3Box.Text(),
                    ppsBox.Text() }
            );
            boolean savedOK=myPerson.Save (webComponent_POST);
            return true;
        }
        else if (component.equals(webComponent_POST) && eventName.equals("GotText")) {
            String result = (String) params[3];
            String message;
            Notifier myNotify = new Notifier(this);
            try {
                JSONObject parser = new JSONObject(result);
                String status= parser.getString("Status");
                if (status.equals("OK")) {
                    postButton.Text("Saved");
                    pID = Integer.valueOf(parser.getString("pID"));
                    message = "Added customer with pID " + parser.getString("pID");
                    myNotify.ShowMessageDialog(message, "Add/edit Customer", "OK");
                }
                else {
                    message = "Something went wrong";
                    postButton.Text("Not saved");
                    myNotify.ShowMessageDialog(message, "Add/edit Customer", "OK");
                }

            } catch (JSONException e) {
            // if an exception occurs, code for it in here
            }
            return true;
        }
        else if (component.equals(putButton) && eventName.equals("Click")) {
            return true;
        }
        else if (component.equals(deleteButton) && eventName.equals("Click")) {
            return true;
        }
        else if (component.equals(getButton) && eventName.equals("Click")) {
            getButtonClick();
            return true;
        }
        else if (component.equals(webComponent_GET) && eventName.equals("GotText")) {
            String result = (String) params[3];
            String message;
            debugLabel.Text("Got Text");
            person myPerson = new person();
            Notifier myNotify = new Notifier(this);
            try {
                // use
                JSONObject myJSONparser = new JSONObject(result);
                JSONArray data= myJSONparser.getJSONArray("person");
                // the same pID?
                if (0 == data.getJSONObject(0).getString("pID").compareTo(pidBox.Text())) {
                    myPerson.Get(data.getJSONObject(0));
                    debugLabel.Text("pIDs match");
                }
                else {
                    message = "Person records don't match. Try again.";
                    myNotify.ShowMessageDialog(message, "Add/edit Customer", "OK");
                    debugLabel.Text("Error");
                }

            } catch (JSONException e) {
                // if an exception occurs, code for it in here
                message = "Unable to retrieve that record. Is the pID number right?";
                myNotify.ShowMessageDialog(message, "Add/edit Customer", "OK");
            }
            return true;
        }
        else if (component.equals(connectionButton) && eventName.equals("Click")) {
            return true;
        }
        else {
        }

        // here is where you'd check for other events of your app...
        return false;
    }

    public void getButtonClick() {
        debugLabel.Text("Sending");
        person myPerson = new person();
        EventDispatcher.registerEventForDelegation(this, "webComponent_GET", "GotText");
        myPerson.pID=Integer.valueOf(pidBox.Text() );
        myPerson.Load(webComponent_GET, myPerson.pID);
    }


}
