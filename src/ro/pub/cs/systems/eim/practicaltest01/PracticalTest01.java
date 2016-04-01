package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01 extends Activity {
	
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	
	private EditText leftEditText = null;
	private EditText rightEditText = null;
	private Button leftButton = null;
	private Button rightButton = null;
	private Button navigateToSecondaryActivityButton = null;
	
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	  private class ButtonClickListener implements View.OnClickListener {
	    @Override
	    public void onClick(View view) {
	      switch(view.getId()) {
	        case R.id.button1:
	          int leftNumberOfClicks = Integer.parseInt(leftEditText.getText().toString());
	          leftNumberOfClicks++;
	          leftEditText.setText(String.valueOf(leftNumberOfClicks));
	          break;
	        case R.id.button2:
	          int rightNumberOfClicks = Integer.parseInt(rightEditText.getText().toString());
	          rightNumberOfClicks++;
	          rightEditText.setText(String.valueOf(rightNumberOfClicks));
	          break;
	        case R.id.navigate_to_secondary_activity_button:
	          Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
	          int numberOfClicks = Integer.parseInt(leftEditText.getText().toString()) + 
	                                 Integer.parseInt(rightEditText.getText().toString());
	          intent.putExtra("numberOfClicks", numberOfClicks);
	          startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
	          break;
	      }
	    }
	  }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_main);
		
		leftEditText = (EditText)findViewById(R.id.left_edit_text);
	    rightEditText = (EditText)findViewById(R.id.right_edit_text);
	    leftEditText.setText(String.valueOf(0));
	    rightEditText.setText(String.valueOf(0));
	 
	    leftButton = (Button)findViewById(R.id.button1);
	    rightButton = (Button)findViewById(R.id.button2);
	    
	    leftButton.setOnClickListener(buttonClickListener);
	    rightButton.setOnClickListener(buttonClickListener); 
	    
	    navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
	    navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);  
	    
	    if (savedInstanceState != null) {
	        if (savedInstanceState.containsKey("leftCount")) {
	          leftEditText.setText(savedInstanceState.getString("leftCount"));
	        } else {
	          leftEditText.setText(String.valueOf(0));
	        }
	        if (savedInstanceState.containsKey("rightCount")) {
	          rightEditText.setText(savedInstanceState.getString("rightCount"));
	        } else {
	          rightEditText.setText(String.valueOf(0));
	        }
	      } else {
	        leftEditText.setText(String.valueOf(0));
	        rightEditText.setText(String.valueOf(0));
	      }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
			Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
	    }
	 }
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
	    savedInstanceState.putString("leftCount", leftEditText.getText().toString());
	    savedInstanceState.putString("rightCount", rightEditText.getText().toString());
	}
	
	@Override
	  protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    if (savedInstanceState.containsKey("leftCount")) {
	      leftEditText.setText(savedInstanceState.getString("leftCount"));
	    } else {
	      leftEditText.setText(String.valueOf(0));
	    }
	    if (savedInstanceState.containsKey("rightCount")) {
	      rightEditText.setText(savedInstanceState.getString("rightCount"));
	    } else {
	      rightEditText.setText(String.valueOf(0));
	    }
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
