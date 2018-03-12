package net.fachtnaroe.pcpitstop.pitstop_v004;
// http://thunkableblocks.blogspot.ie/2017/07/java-code-snippets-for-app-inventor.html
//https://github.com/AppScale/sample-apps/blob/master/java/appinventor2/appinventor/components/tests/com/google/appinventor/components/runtime/WebTest.java

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;


import org.json.JSONException;
import org.json.JSONObject;

//import static com.google.appinventor.components.runtime.util.YailList.makeList;

/**
 * Created by fachtna on 06/03/18.
 */

public class webRequests extends Form implements HandlesEventDispatching {

    private Button connectionButton, postButton, putButton, deleteButton, getButton;
    private Label firstLabel, familyLabel, emailLabel, phoneLabel, responseLabel, ppsLabel, urlLabel, debugSideLabel;
    public Web webComponent_POST, webComponent_PUT, webComponent_GET, webComponent_DELETE;
    public static VerticalScrollArrangement screenContainer;
    private HorizontalArrangement topLine, nextLine, ppsLine;
    public static TextBox firstBox, familyBox, emailBox, phoneBox, urlBox, outputBox, ppsBox, debugLabel;
    private Label address1Label, address2Label, address3Label;
    private TextBox address1Box, address2Box, address3Box;
    private static String remoteHost = "https://fachtnaroe.net";
    private static String remoteApp = "/pcpitstop-2018?";
    public static String targetURL = remoteHost + remoteApp;


    public webRequests() {
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
        titleLabel.Text("Add a person");
        titleLabel.FontSize(20);
        titleLabel.FontTypeface(Component.TYPEFACE_SERIF);
        titleLabel.WidthPercent(100);
        titleLabel.FontBold(true);
        titleLabel.TextAlignment(Component.ALIGNMENT_CENTER);

        HorizontalArrangement urlHorz = new HorizontalArrangement(screenContainer);
        urlLabel = new Label(urlHorz);
        urlLabel.Text("Backend:");
        urlBox = new TextBox(urlHorz);
        urlBox.WidthPercent(100);
        urlBox.Text(remoteHost + remoteApp);

        HorizontalArrangement firstHoriz = new HorizontalArrangement(screenContainer);
        firstHoriz.WidthPercent(100);
        firstLabel = new Label(firstHoriz);
        firstLabel.Text("First: ");
        firstLabel.WidthPercent(20);
        firstBox = new TextBox(firstHoriz);
        firstBox.WidthPercent(100);
        firstBox.Text("Frank");

        HorizontalArrangement familyHoriz = new HorizontalArrangement(screenContainer);
        familyHoriz.WidthPercent(100);
        familyLabel = new Label(familyHoriz);
        familyLabel.Text("Family: ");
        familyLabel.WidthPercent(20);
        familyBox = new TextBox(familyHoriz);
        familyBox.WidthPercent(100);
        familyBox.Text("Frankinson");

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
        phoneBox.Text("1234567");

        HorizontalArrangement address1Horiz = new HorizontalArrangement(screenContainer);
        address1Horiz.WidthPercent(100);
        address1Label = new Label(address1Horiz);
        address1Label.Text("Address1: ");
        address1Label.WidthPercent(20);
        address1Box = new TextBox(address1Horiz);
        address1Box.WidthPercent(100);
        address1Box.Text("1 Main Street");

        HorizontalArrangement address2Horiz = new HorizontalArrangement(screenContainer);
        address2Horiz.WidthPercent(100);
        address2Label = new Label(address2Horiz);
        address2Label.Text("Address2: ");
        address2Label.WidthPercent(20);
        address2Box = new TextBox(address2Horiz);
        address2Box.WidthPercent(100);
        address2Box.Text("Clogheen");

        HorizontalArrangement address3Horiz = new HorizontalArrangement(screenContainer);
        address3Horiz.WidthPercent(100);
        address3Label = new Label(address3Horiz);
        address3Label.Text("Address3: ");
        address3Label.WidthPercent(20);
        address3Box = new TextBox(address3Horiz);
        address3Box.WidthPercent(100);
        address3Box.Text("Co. Tipperary");

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
        postButton = new Button(uploadHoriz);
        postButton.Text("POST");
        postButton.WidthPercent(50);

        putButton = new Button(uploadHoriz);
        putButton.Text("PUT");
        putButton.WidthPercent(50);
        putButton.Enabled(false);

        HorizontalArrangement getHoriz = new HorizontalArrangement(screenContainer);
        getHoriz.WidthPercent(100);
        deleteButton = new Button(getHoriz);
        deleteButton.Text("DELETE");
        deleteButton.WidthPercent(50);
        deleteButton.Enabled(false);

        getButton = new Button(getHoriz);
        getButton.Text("GET");
        getButton.WidthPercent(50);
        getButton.Enabled(false);

//        connectionButton = new Button(screenContainer);
//        connectionButton.Text("Connect");
//
//        connectionButton.BackgroundColor(COLOR_LTGRAY);
//        connectionButton.WidthPercent(50);
//        connectionButton.TextAlignment(Component.ALIGNMENT_CENTER);

        webComponent_POST = new Web(screenContainer);
        webComponent_PUT = new Web(screenContainer);
        webComponent_GET = new Web(screenContainer);
        webComponent_DELETE = new Web(screenContainer);

//        EventDispatcher.registerEventForDelegation(this, "connectButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "postButton", "Click");
//        EventDispatcher.registerEventForDelegation(this, "putButton", "Click");
//        EventDispatcher.registerEventForDelegation(this, "deleteButton", "Click");
//        EventDispatcher.registerEventForDelegation(this, "getButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "webComponent_POST", "GotText");
    }



    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (component.equals(connectionButton) && eventName.equals("Click")) {
            return true;
        }
        else if (component.equals(webComponent_POST) && eventName.equals("GotText")) {
            String result = (String) params[3];
            youveGotText(result);
            return true;
        }
        else if (component.equals(postButton) && eventName.equals("Click")) {
            targetURL = urlBox.Text();
            debugLabel.Text(targetURL);
            postButton.Text("sending");
            webComponent_POST.Url(targetURL);
            person myPerson = new person();

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
            boolean savedOK=myPerson.Save ();
//            String textToPost = myPerson.MakeRequestString();
//            webComponent_POST.PostText(textToPost);
//            debugLabel.Text(textToPost);
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
        else {
        }

        // here is where you'd check for other events of your app...
        return false;
    }

    public void getButtonClick() {

//        targetURL = urlBox.Text();
//        webComponent_POST.Url(targetURL);
//        webComponent_POST.Get();
    }

    public void youveGotText(String result) {
        postButton.Text("Got data");
        debugLabel.Text(result);
        try {
            JSONObject parser = new JSONObject(result);
            debugLabel.Text(result);
//                    parser.getString("result") + " (" +
//                            parser.getString("sessionID") + ")"
//            );

        } catch (JSONException e) {
            // if an exception occurs, code for it in here
        }
    }

}
