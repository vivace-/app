/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package org.apache.cordova.example;

import java.util.regex.Pattern;

import org.apache.cordova.DroidGap;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Patterns;

public class cordovaExample extends DroidGap
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); 
        super.init();
        super.loadUrl("file:///android_asset/www/index_contacts_create.html");
        // Intent intent = getIntent();
        // String addr = intent.getStringExtra("addr");
        // super.loadUrl(addr);
        
        // ���� ���� �� �信 �ڹٽ�ũ��Ʈ ��밡���ϵ��� ����
        this.appView.getSettings().setJavaScriptEnabled(true);
        
        // �긴�� �߰�(�������̽� �߰�)
        this.appView.addJavascriptInterface(new JSBridge(this), "UserData");
        // �ΰ� ���� addJavascriptInterface(Object obj, String namespace)
        // obj�� �������̽� �ν��Ͻ��� �Ѱ��ְ�(���� �ڹٽ�ũ��Ʈ���� ȣ���� �޼��带 ��� �ִ� 
        // �ν��Ͻ��� �������ָ� ��)
        // namespace�� �ڹٽ�ũ��Ʈ���� ȣ�� �� ����� ���ӽ����̽�
        // �ڹٽ�ũ��Ʈ���� ȣ�� �� "���ӽ����̽�.(Ŭ������)�޼����(����)"�� �������� ���

        // ����� �ڹٽ�ũ��Ʈ<->����Ƽ�� �� ��ȣ ȣ���� ������.
        
        
    }
    
    public class JSBridge{
    	private Context context;
    	public JSBridge(Context context){
    		this.context = context;
    	}
    	
    	/* ��ȭ��ȣ ������ ���� �޼��� */
    	public final String getPhoneNumber(){
    		TelephonyManager tMgr = (TelephonyManager)context
    				.getSystemService(Context.TELEPHONY_SERVICE);
    		String mPhoneNumber = tMgr.getLine1Number();
    		return mPhoneNumber;
    	}
    	
    	/* ����� e-mail �������� */
    	// GET_ACCOUNT �۹̼� �߰� -- �̹� �������� �⺻������ �߰��ѵ�.. ����.
    	// http://stackoverflow.com/questions/2112965/how-to-get-the-android-devices-primary-e-mail-address
    	public final String getEmailAddress(){
    		//Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
    		Account[] accounts = AccountManager.get(context).getAccounts();
    		StringBuilder builder = new StringBuilder();
    		
    		for (Account account : accounts) {
    			builder.append("<li>");
		    	builder.append(account.name);
		    	builder.append("</li>\n");
    		}
    		
    		return builder.toString();
    	}
    	
    }
}

