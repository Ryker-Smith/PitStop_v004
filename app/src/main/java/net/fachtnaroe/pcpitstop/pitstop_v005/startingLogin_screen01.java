package net.fachtnaroe.pcpitstop.pitstop_v005;
// http://thunkableblocks.blogspot.ie/2017/07/java-code-snippets-for-app-inventor.html

import android.content.Intent;
import android.os.Bundle;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.TinyDB;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;

import org.json.JSONException;
import org.json.JSONObject;

public class startingLogin_screen01 extends Form implements HandlesEventDispatching {

    private Button customerLoginButton, operatorLoginButton, administratorLoginButton, technicianLoginButton;
    private Label sendLabel, passLabel;
    private Web customer_webComponent, operator_webComponent, technician_webComponent, administrator_webComponent;
    private VerticalScrollArrangement screenContainer;
    private HorizontalArrangement userLine, passLine;
    private TextBox debugBox, userText, passText;
    private String targetURL = "https://fachtnaroe.net/pcpitstop-2018?iam=fachtna&";
    private Image myLogo;
    private Notifier myNotify;
    private TinyDB myFile;
    private String sessionID;

    protected void $define() {

        screenContainer = new VerticalScrollArrangement(this);
        screenContainer.WidthPercent(100);
        screenContainer.HeightPercent(100);
        myFile = new TinyDB(screenContainer);
        myNotify = new Notifier(screenContainer);

        userLine = new HorizontalArrangement(screenContainer);
        userLine.WidthPercent(100);
        sendLabel = new Label(userLine);
        sendLabel.Text("User: ");
        sendLabel.WidthPercent(30);
        userText = new TextBox(userLine);
        userText.Text("fachtna.roe");

        passLine = new HorizontalArrangement(screenContainer);
        passLine.WidthPercent(100);
        passLabel = new Label(passLine);
        passLabel.Text("Password: ");
        passLabel.WidthPercent(30);
        passText = new TextBox(passLine);
        passText.Text("tcfetcfe");

        debugBox = new TextBox(screenContainer);
        debugBox.WidthPercent(100);
        debugBox.Text("");

        VerticalArrangement buttonsArrangement= new VerticalArrangement(screenContainer);

        HorizontalArrangement firstLine = new HorizontalArrangement(buttonsArrangement);
        customerLoginButton = new Button(firstLine);
        customerLoginButton.Text("Customer");
        customerLoginButton.BackgroundColor(COLOR_LTGRAY);
        customerLoginButton.WidthPercent(50);
        customerLoginButton.TextAlignment(Component.ALIGNMENT_CENTER);
        customer_webComponent = new Web(screenContainer);
        operatorLoginButton = new Button(firstLine);
        operatorLoginButton.Text("Operator");
        operatorLoginButton.BackgroundColor(COLOR_LTGRAY);
        operatorLoginButton.WidthPercent(50);
        operatorLoginButton.TextAlignment(Component.ALIGNMENT_CENTER);
        operator_webComponent = new Web(screenContainer);

        HorizontalArrangement secondLine = new HorizontalArrangement(buttonsArrangement);
        administratorLoginButton = new Button(secondLine);
        administratorLoginButton.Text("Administrator");
        administratorLoginButton.BackgroundColor(COLOR_LTGRAY);
        administratorLoginButton.WidthPercent(50);
        administratorLoginButton.TextAlignment(Component.ALIGNMENT_CENTER);
        administrator_webComponent = new Web(screenContainer);

        technicianLoginButton = new Button(secondLine);
        technicianLoginButton.Text("Technician");
        technicianLoginButton.BackgroundColor(COLOR_LTGRAY);
        technicianLoginButton.WidthPercent(50);
        technicianLoginButton.TextAlignment(Component.ALIGNMENT_CENTER);
        technician_webComponent = new Web(screenContainer);

        EventDispatcher.registerEventForDelegation(this, "customerLoginButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "operatorLoginButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "technicianLoginButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "administratorLoginButton", "Click");
        EventDispatcher.registerEventForDelegation(this, "customer_webComponent", "GotText");
        EventDispatcher.registerEventForDelegation(this, "operator_webComponent", "GotText");
        EventDispatcher.registerEventForDelegation(this, "technician_webComponent", "GotText");
        EventDispatcher.registerEventForDelegation(this, "administrator_webComponent", "GotText");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (component.equals(customerLoginButton) && eventName.equals("Click")) {
            customerLogin();
            return true;
        }
        else if (component.equals(customer_webComponent) && eventName.equals("GotText")) {
            String result = (String) params[3];
            customerGotText(result);
            return true;
        }
        else if (component.equals(operatorLoginButton) && eventName.equals("Click")) {
            operatorLogin();
            return true;
        }
        else if (component.equals(operator_webComponent) && eventName.equals("GotText")) {
            String result = (String) params[3];
            operatorGotText(result);
            return true;
        }
        else if (component.equals(administratorLoginButton) && eventName.equals("Click")) {
            administratorLogin();
            return true;
        }
        else if (component.equals(administrator_webComponent) && eventName.equals("GotText")) {
            String result = (String) params[3];
            administratorGotText(result);
            return true;
        }
        else if (component.equals(technicianLoginButton) && eventName.equals("Click")) {
            technicianLogin();
            return true;
        }
        else if (component.equals(technician_webComponent) && eventName.equals("GotText")) {
            String result = (String) params[3];
            technicianGotText(result);
            return true;
        }
        else {
            passLabel.Text("Not matched");
        }
        // here is where you'd check for other events of your app...
        return false;
    }

    public void customerLogin() {

    }

    public void customerGotText(String result) {
        startActivity(new Intent(startingLogin_screen01.this, customerHome_screen07.class));
        try {
            JSONObject parser = new JSONObject(result);
            if (parser.getString("result").equals("OK"))
            {}
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
        }
    }

    public void operatorLogin() {
        String request=customerAddEdit_screen04.RequestCombine(new String[]{
            customerAddEdit_screen04.RequestValue("user", userText.Text()),
            customerAddEdit_screen04.RequestValue("pass", passText.Text())
        });
        operator_webComponent.Url(targetURL + "cmd=login" + "&" + request);
        operator_webComponent.Get();
    }

    public void operatorGotText(String result) {
        try {
            JSONObject parser = new JSONObject(result);
            debugBox.Text(
                    parser.getString("Status") + " (" +
                    parser.getString("sessionID") + ")"
            );
            if (parser.getString("Status").equals("OK")) {
                sessionID= parser.getString("sessionID");
//                myFile.StoreValue("sessionID", sessionID);
                myFile.StoreValue((String) "sessionID", (Object) sessionID);
                Intent intent = new Intent(this, operatorHome_screen02.class);
                Bundle b = new Bundle();
                b.putString("sessionID", sessionID); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
//                finish();
//                startActivity(new Intent(startingLogin_screen01.this, operatorHome_screen02.class));
//                startingLogin_screen01.switchForm("customerAddEdit_screen04");
            }
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
            myNotify.ShowMessageDialog("Error logging in", "Error", "OK");
        }
    }

    public void administratorLogin() {

    }

    public void administratorGotText(String result) {
        startActivity(new Intent(startingLogin_screen01.this, operatorHome_screen02.class));
        try {
            JSONObject parser = new JSONObject(result);
//            outputBox.Text(
//                    parser.getString("result") + " (" +
//                           parser.getString("sessionID") + ")"
//            );

            if (parser.getString("Status").equals("OK")) {}
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
        }
    }

    public void technicianLogin() {

    }

    public void technicianGotText(String result) {
        startActivity(new Intent(startingLogin_screen01.this, operatorHome_screen02.class));
        try {
            JSONObject parser = new JSONObject(result);
//            outputBox.Text(
//                    parser.getString("result") + " (" +
//                           parser.getString("sessionID") + ")"
//            );

            if (parser.getString("result").equals("OK")) {}
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
        }
    }

}
