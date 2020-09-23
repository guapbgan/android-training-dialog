package com.uuu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class DialogExerciseActivity extends Activity {
    /** Called when the activity is first created. */
	final CharSequence[] items = {"選項1","選項2","選項3","選項4","選項5"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.button).setOnClickListener(view -> displayDialog1());

//        displayDialog1();
//
//        displayDialog2();


    }

    private void displayDialog2() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        ClickAction clickAction = new ClickAction(this);
        builder2.setTitle("Multiple Dialog");
        //     builder2.setItems(items, clickAction);
        builder2.setSingleChoiceItems(items, -1, new Foo());
        //     builder2.setSingleChoiceItems(arg0, arg1, arg2)

        AlertDialog dialog2 = builder2.create();
        dialog2.show();
    }

    private void displayDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to quit app?");
        builder.setPositiveButton("Yes",(d, w)->{
            finish();
        });
        builder.setNegativeButton("No",(d, w)->{
            displayDialog2();
        });
        builder.setNeutralButton("wait",(d, w)->{
            displayDialog3();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void displayDialog3() {
        ProgressDialog currentDialog = ProgressDialog.show(this, "wait", "until done", true, false);
        new Thread(()->{
           try{
               Thread.sleep(5000);
           }catch (InterruptedException ex){
               ex.printStackTrace();
           }
            currentDialog.dismiss();
        }).start();
    }

    private class Foo implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            Toast.makeText(DialogExerciseActivity.this,"#" + i  + " is clicked", Toast.LENGTH_LONG).show();

            ProgressDialog dialog = new ProgressDialog(DialogExerciseActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setTitle("Wait");
            dialog.setMessage("untile done");
            dialog.show();
            new Thread(()->{
                for(int x = 0; x <100; x++){

                    dialog.setProgress(x);
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
                dialog.dismiss();
            }).start();
        }
    }
}