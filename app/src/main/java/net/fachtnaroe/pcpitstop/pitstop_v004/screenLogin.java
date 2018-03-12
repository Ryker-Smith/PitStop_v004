package net.fachtnaroe.pcpitstop.pitstop_v004;
// http://thunkableblocks.blogspot.ie/2017/07/java-code-snippets-for-app-inventor.html

import android.content.Intent;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;

import org.json.JSONException;
import org.json.JSONObject;

public class screenLogin extends Form implements HandlesEventDispatching {

    private Button connectionButton, webRequestsButton;
    private Label sendLabel, responseLabel, ppsLabel;
    private Web webComponent;
    private VerticalScrollArrangement screenContainer;
    private HorizontalArrangement topLine, nextLine, ppsLine;
    private TextBox inputBox, outputBox, ppsBox, debugLabel;
    private String remoteHost = "fachtnaroe.net";
    private Image myLogo;
    private PPSN CandidatePPS;

    protected void $define() {

        screenContainer = new VerticalScrollArrangement(this);
        screenContainer.WidthPercent(100);
        screenContainer.HeightPercent(100);

        topLine = new HorizontalArrangement(screenContainer);
        topLine.WidthPercent(100);
        topLine.Height(100);

        sendLabel = new Label(topLine);
        sendLabel.Text("User: ");

        inputBox = new TextBox(topLine);
        inputBox.WidthPercent(100);
        inputBox.Text("https://" + remoteHost + "/tuuber?cmd=login&email=me@here.ie&password=tcfetcfe");
        nextLine = new HorizontalArrangement(screenContainer);
        nextLine.WidthPercent(100);
        nextLine.Height(100);

        responseLabel = new Label(nextLine);
        responseLabel.Text("Receive: ");

        outputBox = new TextBox(nextLine);
        outputBox.WidthPercent(100);
        outputBox.Text("Waiting");

        ppsLine = new HorizontalArrangement(screenContainer);
        ppsLabel = new Label(ppsLine);
        ppsLabel.Text("PPS: ");

        ppsBox = new TextBox(ppsLine);
        ppsBox.WidthPercent(100);

        CandidatePPS = new PPSN();

        ppsBox.Text(CandidatePPS.Number);

        connectionButton = new Button(screenContainer);
        connectionButton.Text("Test login function");
        connectionButton.BackgroundColor(COLOR_LTGRAY);
        connectionButton.WidthPercent(50);
        connectionButton.TextAlignment(Component.ALIGNMENT_CENTER);
        webComponent = new Web(screenContainer);

        debugLabel = new TextBox(screenContainer);
        debugLabel.WidthPercent(100);
        debugLabel.Text("");

        webRequestsButton = new Button(screenContainer);
        webRequestsButton.Text("webRequests");
        webRequestsButton.BackgroundColor(COLOR_LTGRAY);
        webRequestsButton.WidthPercent(50);
        webRequestsButton.TextAlignment(Component.ALIGNMENT_CENTER);

        EventDispatcher.registerEventForDelegation(this, "connectButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "webRequestsButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "webComponent", "GotText");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (component.equals(connectionButton) && eventName.equals("Click")) {
            connectionButtonClick();
            return true;
        } else if (component.equals(webComponent) && eventName.equals("GotText")) {
            String result = (String) params[3];
            youveGotText(result);
            return true;
        } else if (component.equals(webRequestsButton) && eventName.equals("Click")) {
            webRequestsButtonClick();
            return true;
        }
        else {
            responseLabel.Text("Not matched");
        }

        // here is where you'd check for other events of your app...
        return false;
    }

    public void connectionButtonClick() {
        String targetURL;

        if (CandidatePPS.Set(   ppsBox.Text())  ) {
            debugLabel.Text(debugLabel.Text() + " true");
        } else {
            debugLabel.Text(debugLabel.Text() + "false");
            connectionButton.Text("A valid PPS must be entered first.");
            return;
        }

        connectionButton.Text("Pressed, getting login response: ");
        targetURL = inputBox.Text();
        webComponent.Url(targetURL);
        webComponent.Get();
    }

    public void webRequestsButtonClick() {
        startActivity(new Intent(screenLogin.this, webRequests.class));
    }

    public void youveGotText(String result) {
        connectionButton.Text("Got data: ");
        try {
            JSONObject parser = new JSONObject(result);
            outputBox.Text(
                    parser.getString("result") + " (" +
                           parser.getString("sessionID") + ")"
            );

            if (parser.getString("result").equals("OK")) {}
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
        }
    }

}
