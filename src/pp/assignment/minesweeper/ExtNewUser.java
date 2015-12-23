package pp.assignment.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.app.*;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExtNewUser extends Activity{
	
	EditText username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_newuser);
		username=(EditText)findViewById(R.id.txt_username);
	}
	
	public void submitUser(View v){
		Intent intent=new Intent();
		if(username.getText().toString().trim().length()!=0){
			intent.putExtra(Utils.PREF_USERNAME,username.getText().toString());
			intent.putExtra(Utils.PREF_SUCCESS,true);
			setResult(RESULT_OK,intent);
			finish();
		}
		else{
			Toast.makeText(getApplicationContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
		}
		
	}
}
