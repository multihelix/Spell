package spell.multihelix.com.spell;
//This is my attempt at producing a spelling aid application to help my kids learn their school spellins
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    //private Array question_array[] ={"squirell","A tree rat";  };
    TextView spelling_guess;
    Button repeat;
    String current_word = "separate";
  //  public Speaker speaker = new Speaker(this);
    private CompoundButton.OnCheckedChangeListener toggleListener;
    private final int CHECK_CODE = 0x1;
    private final int LONG_DURATION = 5000;
    private final int SHORT_DURATION = 1200;
   // private TextToSpeech engine = new TextToSpeech(this, this);
    //engine = new TextToSpeech(this, this);
    private TextToSpeech engine;
    @Override
    public void onInit(int status) {
       Log.d("Speech", "OnInit - Status ["+status+"]");

       if (status == TextToSpeech.SUCCESS) {
           Log.d("Speech", "Success!");
         //  engine.setLanguage(Locale.UK);
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        engine = new TextToSpeech(this, this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spelling_guess = (TextView) findViewById(R.id.tv_spelling_attempt);
        repeat = (Button) findViewById(R.id.button_repeat);
        //checkTTS();
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // speaker.speak(current_word);

               engine.speak(current_word, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkTTS(){
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, CHECK_CODE);
    }

}
