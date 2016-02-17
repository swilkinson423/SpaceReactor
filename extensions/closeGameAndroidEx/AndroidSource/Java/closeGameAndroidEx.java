/****************************************************************************************************************************
 *
 * closeGameAndroidEx
 *
 * closeGameAndroidEx allows you to create a quit Android native popup.
 *
 * Usage: just call
 * closeAndroidGame(String messageString, String yesString, String noString)
 * from within a release<Backspace> event in your Game Maker code to customize and open the popup.
 *
 * Author: Mattia Fortunati
 * Contact: mattia@mattiafortunati.com
 * Website: http://www.mattiafortunati.com
 *
 ****************************************************************************************************************************/
package ${YYAndroidPackageName};

//Basic imports
import android.util.Log;
import java.lang.String;
//
import android.app.Activity;
import android.content.Intent;

//Import Game Maker classes
import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;
import ${YYAndroidPackageName}.RunnerActivity;

//other imports
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.R.style;
import android.view.ContextThemeWrapper;

public class closeGameAndroidEx extends Activity {

    //var declarations
    private static String messageStr;
    private static String yesBtnStr;
    private static String noBtnStr;

    //just call this function from within a release<Backspace> event in your Game Maker code to customize and open the popup.	
    public void closeAndroidGame(String messageString, String yesString, String noString) {
        Intent intent = new Intent(RunnerActivity.CurrentActivity, closeGameAndroidEx.class);
        RunnerActivity.CurrentActivity.startActivity(intent);
        messageStr = messageString;
        yesBtnStr = yesString;
        noBtnStr = noString;
    }


    //Overrides
    @Override
    protected void onStart() {
        super.onStart();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Holo));

        //create dialog
        alertDialogBuilder
            .setMessage(messageStr)
            .setCancelable(true)
            .setPositiveButton(noBtnStr, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
	    finish();
                }
            })
            .setNegativeButton(yesBtnStr, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    RunnerActivity.CurrentActivity.finish();
                }
            })
            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                 public void onCancel(DialogInterface dialog) {
                    finish();
                 }
            });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
}