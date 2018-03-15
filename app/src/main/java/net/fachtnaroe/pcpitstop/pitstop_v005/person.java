package net.fachtnaroe.pcpitstop.pitstop_v005;

import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Web;

import org.json.JSONException;
import org.json.JSONObject;

import static net.fachtnaroe.pcpitstop.pitstop_v005.customerAddEdit_screen04.address1Box;
import static net.fachtnaroe.pcpitstop.pitstop_v005.customerAddEdit_screen04.address2Box;
import static net.fachtnaroe.pcpitstop.pitstop_v005.customerAddEdit_screen04.address3Box;
import static net.fachtnaroe.pcpitstop.pitstop_v005.customerAddEdit_screen04.emailBox;
import static net.fachtnaroe.pcpitstop.pitstop_v005.customerAddEdit_screen04.familyBox;
import static net.fachtnaroe.pcpitstop.pitstop_v005.customerAddEdit_screen04.firstBox;
import static net.fachtnaroe.pcpitstop.pitstop_v005.customerAddEdit_screen04.phoneBox;
import static net.fachtnaroe.pcpitstop.pitstop_v005.customerAddEdit_screen04.ppsBox;

/**
 * Created by fachtna on 09/03/18.
 */

public class person extends Form implements HandlesEventDispatching{
    public int pID;
    public String First;
    public String Family;
    public String Email;
    public String Phone;
    public String Address1;
    public String Address2;
    public String Address3;
    public PPSN pps;
    public boolean SavedOK;

    protected void $define() {
    }

    public person (){
        // constructor
        pps  = new PPSN();
        pID=-1; // if subsequently found to be different, use PUT not POST
    }

    public person Load (Web webComponent, int pID) {
        String textToPost =
                customerAddEdit_screen04.RequestCombine(new String[]{
                        customerAddEdit_screen04.RequestValue("sessionID","a1b2c3d4"),
                        customerAddEdit_screen04.RequestValue("iam","fachtna"),
                        customerAddEdit_screen04.RequestValue("entity","person"),
                        customerAddEdit_screen04.RequestValue("pID",Integer.toString(pID))
                });
        webComponent.Url( customerAddEdit_screen04.targetURL +  textToPost );
        customerAddEdit_screen04.debugLabel.Text(webComponent.Url());
        webComponent.Get();
        return null;
    }

    public boolean Save(Web webComponent){
        String request = webComponent.Url() + "&" + MakeRequestString();
        if (pID == -1) { // -1 means no active pID, should POST; else PUT
            webComponent.PostText(request);
        }
        else {
            webComponent.PutText(request);
        }
        return true;
    }


    boolean Set (String[] details) {
        // SET means copy from screen to background record
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

    void Get (JSONObject dataRcvd) {
        // GET means for us, interpret the JSON data received then copy from background record into screen
//        String[] details = new String[] {};
        try {
//            JSONObject parser = new JSONObject(dataRcvd);
            pID= Integer.valueOf(dataRcvd.getString("pID"));
            First= dataRcvd.getString("First");
            Family= dataRcvd.getString("Family");
            Email= dataRcvd.getString("Email");
            Phone= dataRcvd.getString("Phone");
            Address1= dataRcvd.getString("Address1");
            Address2= dataRcvd.getString("Address2");
            Address3= dataRcvd.getString("Address3");
            pps.Set(dataRcvd.getString("pps"));

            firstBox.Text(First);
            familyBox.Text(Family);
            emailBox.Text(Email);
            phoneBox.Text(Phone);
            address1Box.Text(Address1);
            address2Box.Text(Address2);
            address3Box.Text(Address3);
            ppsBox.Text(pps.Number);


        } catch (JSONException e) {
            // if an exception occurs, code for it in here
        }

    }
    public String MakeRequestString (){
        // Purpose: build a URL string for use with Web compnent
        // Expects: Nothing. Hard coded for this object
        // Returns: String suitable for passing to AppInventor Web component.
        String textToPost =
                customerAddEdit_screen04.RequestCombine(new String[]{
                        customerAddEdit_screen04.RequestValue("sessionID","a1b2c3d4"),
                        customerAddEdit_screen04.RequestValue("myProcess","fachtna"),
                        customerAddEdit_screen04.RequestValue("entity","person"),
                        customerAddEdit_screen04.RequestValue("First",this.First),
                        customerAddEdit_screen04.RequestValue("Family",this.Family),
                        customerAddEdit_screen04.RequestValue("Email", this.Email),
                        customerAddEdit_screen04.RequestValue("Phone", this.Phone),
                        customerAddEdit_screen04.RequestValue("Address1", this.Address1),
                        customerAddEdit_screen04.RequestValue("Address2", this.Address2),
                        customerAddEdit_screen04.RequestValue("Address3", this.Address3),
                        customerAddEdit_screen04.RequestValue("PPS", this.pps.Number),
                });
        return textToPost;
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
