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
        
        // 폰갭 내장 웹 뷰에 자바스크립트 사용가능하도록 설정
        this.appView.getSettings().setJavaScriptEnabled(true);
        
        // 브릿지 추가(인터페이스 추가)
        this.appView.addJavascriptInterface(new JSBridge(this), "UserData");
        // 부가 설명 addJavascriptInterface(Object obj, String namespace)
        // obj에 인터페이스 인스턴스를 넘겨주고(실제 자바스크립트에서 호출할 메서드를 담고 있는 
        // 인스턴스를 전달해주면 됨)
        // namespace는 자바스크립트에서 호출 시 사용할 네임스페이스
        // 자바스크립트에서 호출 시 "네임스페이스.(클래스의)메서드명(인자)"의 형식으로 사용

        // 참고로 자바스크립트<->네이티브 앱 상호 호출이 가능함.
        
        
    }
    
    public class JSBridge{
    	private Context context;
    	public JSBridge(Context context){
    		this.context = context;
    	}
    	
    	/* 전화번호 가지고 오는 메서드 */
    	public final String getPhoneNumber(){
    		TelephonyManager tMgr = (TelephonyManager)context
    				.getSystemService(Context.TELEPHONY_SERVICE);
    		String mPhoneNumber = tMgr.getLine1Number();
    		return mPhoneNumber;
    	}
    	
    	/* 사용자 e-mail 가져오기 */
    	// GET_ACCOUNT 퍼미션 추가 -- 이미 폰갭에서 기본적으로 추가한듯.. 있음.
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

