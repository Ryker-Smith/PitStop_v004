package com.wolber.samples;

import java.util.ArrayList;

import com.google.devtools.simple.runtime.components.android.ComponentContainer;
import com.google.devtools.simple.runtime.components.android.Form;
import com.google.devtools.simple.runtime.components.android.ListPicker;
import com.google.devtools.simple.runtime.components.android.ListPickerActivity;
import com.google.devtools.simple.runtime.components.util.YailList;

import android.app.Activity;
import android.os.Bundle;

public class ListViewBridgeSampleActivity extends Form {
    /** Called when the activity is first created. */
   
	FruitListPicker listPicker;
	void $define() 
	{
	   listPicker = new FruitListPicker(this);
	   listPicker.Text("fruits");
	   
	   
	}
	
	public class FruitListPicker extends ListPicker
	{

		public FruitListPicker(ComponentContainer container) {
			super(container);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void AfterPicking() {
			// TODO Auto-generated method stub
			super.AfterPicking();
		}

		@Override
		public void BeforePicking() {
			// TODO Auto-generated method stub
			super.BeforePicking();
			ArrayList<String> mylist = new ArrayList<String>();
			mylist.add("apple");
			mylist.add("cherry");
			YailList ylist = YailList.makeList(mylist);
			this.Elements(ylist);
			
		}
		
	}
}