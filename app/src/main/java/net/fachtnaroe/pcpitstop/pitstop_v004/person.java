package net.fachtnaroe.pcpitstop.pitstop_v004;

import android.support.annotation.MainThread;

import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Web;

/**
 * Created by fachtna on 09/03/18.
 */

public class person {
    public int pID;
    public String First;
    public String Family;
    public String Email;
    public String Phone;
    public String Address1;
    public String Address2;
    public String Address3;
    public PPSN pps;

    private Web webComponent;

    public boolean SavedOK;

    public person (){
        // constructor
        pps  = new PPSN();
        pID=-1; // if subsequently found to be different, use PUT not POST
        webComponent = new Web(webRequests.screenContainer);
    }

    public person Get (int pID) {
        webComponent.Url (webRequests.targetURL + "entity=person&pID=" + Integer.toString(pID));
        EventDispatcher.registerEventForDelegation(this, "getButton", "Click");
        webComponent.Get();
        return null;
    }

    boolean Set (String[] details) {
        First = details[0];
        Family = details[1];
        Email = details[2];
        Phone = details[3];
        Address1 = details[4];
        Address2 = details[5];
        Address3 = details[6];
        // not forcing a valid PPS at this point
        boolean result=pps.Set (details[7]);
        return true;
    }

    public String MakeRequestString (){
        // Purpose: build a URL string for use with Web compnent
        // Expects: Nothing. Hard coded for this object
        // Returns: String suitable for passing to AppInventor Web component.
        String textToPost =
                webRequests.RequestCombine(new String[]{
                        webRequests.RequestValue("entity","person"),
                        webRequests.RequestValue("First",this.First),
                        webRequests.RequestValue("Family",this.Family),
                        webRequests.RequestValue("Email", this.Email),
                        webRequests.RequestValue("Phone", this.Phone),
                        webRequests.RequestValue("Address1", this.Address1),
                        webRequests.RequestValue("Address2", this.Address2),
                        webRequests.RequestValue("Address3", this.Address3),
                        webRequests.RequestValue("PPS", this.pps.Number),
                });
        return textToPost;
    }

    public boolean Save(){
//        Web webComponent = new Web(.screenContainer);
        webComponent.PostText(MakeRequestString());
        return true;
    }

    public String AddressOf () {
        String result= this.Address1;
        if (this.Address2 != null) {
            result += "," + Address2;
        }
        if (this.Address3 != null) {
            result += "," + Address3;
        }
        return result;
    }

    public boolean HasContactDetails () {
        if ((this.Phone != null) && (this.Email != null)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String ContactDetails () {
        String result = new String();
        if (this.Phone != null) {
            result= "Phone: (" + this.Phone + ")";
        }
        if (this.Email != null) {
            result += "Email: (" + this.Email + ")";
        }
        return result;
    }

}
