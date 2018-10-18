package huang.bling.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements Contract.View{

    EditText editText;
    Button button;
    Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        presenter = new Presenter(this,new Module());
    }

    @Override
    public void showText(String s) {
        editText.setText(s);
    }

    @Override
    public String getValue() {
        return editText.getText().toString();
    }

    public void clickButton(View view) {
        presenter.clickButton();
    }
}
