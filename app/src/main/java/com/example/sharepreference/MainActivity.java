package com.example.sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText xname,xpassword;
    CheckBox checkBox;
    Button xbtn;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xname=(EditText)findViewById(R.id.username);
        xpassword=(EditText)findViewById(R.id.password);
        checkBox=(CheckBox)findViewById(R.id.chx);
        xbtn=(Button)findViewById(R.id.btn);
        xbtn.setOnClickListener(new BtnClick());

        sp=getSharedPreferences("data",MODE_PRIVATE);
        editor=sp.edit();

        if(sp.getBoolean("checked",false)){
            xname.setText(sp.getString("username",""));
            checkBox.setChecked(true);
        }


    }

    private class BtnClick implements View.OnClickListener {
        String name,password;
        @Override
        public void onClick(View view) {
            name=xname.getText().toString().trim();
            password=xpassword.getText().toString().trim();

            if(!name.isEmpty() && !password.isEmpty()){
                if(checkBox.isChecked()){
                    editor.putString("username",name);//保存用户输入的账号
                    editor.putBoolean("checked",true);
                    editor.commit();
                }else {
                    editor.putString("username","");
                    editor.putBoolean("checked",false);
                    editor.commit();
                }
            }else {
                Toast.makeText(MainActivity.this,"用户名或密码不能为空",Toast.LENGTH_LONG).show();
            }

        }
    }
}