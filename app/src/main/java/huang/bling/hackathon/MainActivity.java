package huang.bling.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Contract.View{
    Contract.Presenter presenter;
    TextView value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value = findViewById(R.id.textView);
        value.setText("");
        // todo
    }

    @Override
    public void showAnswer(String s) {
        value.setText(s);
    }

    @Override
    public String getOldString() {
        return value.getText().toString();
    }

    public void clickButton(View view) {
        String val ;
        switch(view.getId()){
            case R.id.bt_0:
                val = "0";
                break;
            case R.id.bt_1:
                val = "1";
                break;
            case R.id.bt_2:
                val = "2";
                break;
            case R.id.bt_3:
                val = "3";
                break;
            case R.id.bt_4:
                val = "4";
                break;
            case R.id.bt_5:
                val = "5";
                break;
            case R.id.bt_6:
                val = "6";
                break;
            case R.id.bt_7:
                val = "7";
                break;
            case R.id.bt_8:
                val = "8";
                break;
            case R.id.bt_9:
                val = "9";
                break;
            case R.id.bt_add:
                val = "+";
                break;
            case R.id.bt_minus:
                val = "-";
                break;
            case R.id.bt_multiple:
                val = "*";
                break;
            case R.id.bt_divide:
                val = "/";
                break;
            case R.id.bt_clear:
                val = "C";
                break;
            case R.id.bt_back:
                val = "B";
                break;
            case R.id.bt_eq:
                val = "=";
                break;
            case R.id.percent:
                val = "%";
                break;
            default:
                return;
        }
        presenter.clickButton(val);
    }
}
