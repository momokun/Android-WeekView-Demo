package id.momokun.agendaview;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by ElmoTan on 10/7/2017.
 */

public class AgendaDay extends AppCompatActivity{

    // *TextView
    private TextView textViewServiceApp;
    private TextView textViewWeekCalender;
    private TextView textViewPrevDate;
    private TextView textViewDate;
    private TextView textViewNextDate;
    private TextView textViewSun;
    private TextView textViewMon;
    private TextView textViewTue;
    private TextView textViewWed;
    private TextView textViewThu;
    private TextView textViewFri;
    private TextView textViewSat;
    private TextView textView12am;
    private TextView textView1am;
    private TextView textView2am;
    private TextView textView3am;
    private TextView textView4am;
    private TextView textView5am;
    private TextView textView6am;
    private TextView textView7am;
    private TextView textView8am;
    private TextView textView9am;
    private TextView textView10am;
    private TextView textView11am;
    private TextView textView12pm;
    private TextView textView1pm;
    private TextView textView2pm;
    private TextView textView3pm;
    private TextView textView4pm;
    private TextView textView5pm;
    private TextView textView6pm;
    private TextView textView7pm;
    private TextView textView8pm;
    private TextView textView9pm;
    private TextView textView10pm;
    private TextView textView11pm;

    public static ArrayList<Entity1> arrayListEntity = new ArrayList<Entity1>();
    public static ArrayList<Entity1> arrayListEButtonId = new ArrayList<Entity1>();

    // * Views
    private RelativeLayout relativeLayoutDaily;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_day_layout);

        textViewPrevDate = (TextView) findViewById(R.id.textViewPrevDate);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewNextDate = (TextView) findViewById(R.id.textViewNextDate);
        textView12am = (TextView) findViewById(R.id.textView12am);
        textView1am = (TextView) findViewById(R.id.textView1am);
        textView2am = (TextView) findViewById(R.id.textView2am);
        textView3am = (TextView) findViewById(R.id.textView3am);
        textView4am = (TextView) findViewById(R.id.textView4am);
        textView5am = (TextView) findViewById(R.id.textView5am);
        textView6am = (TextView) findViewById(R.id.textView6am);
        textView7am = (TextView) findViewById(R.id.textView7am);
        textView8am = (TextView) findViewById(R.id.textView8am);
        textView9am = (TextView) findViewById(R.id.textView9am);
        textView10am = (TextView) findViewById(R.id.textView10am);
        textView11am = (TextView) findViewById(R.id.textView11am);
        textView12pm = (TextView) findViewById(R.id.textView12pm);
        textView1pm = (TextView) findViewById(R.id.textView1pm);
        textView2pm = (TextView) findViewById(R.id.textView2pm);
        textView3pm = (TextView) findViewById(R.id.textView3pm);
        textView4pm = (TextView) findViewById(R.id.textView4pm);
        textView5pm = (TextView) findViewById(R.id.textView5pm);
        textView6pm = (TextView) findViewById(R.id.textView6pm);
        textView7pm = (TextView) findViewById(R.id.textView7pm);
        textView8pm = (TextView) findViewById(R.id.textView8pm);
        textView9pm = (TextView) findViewById(R.id.textView9pm);
        textView10pm = (TextView) findViewById(R.id.textView10pm);
        textView11pm = (TextView) findViewById(R.id.textView11pm);

        relativeLayoutDaily = (RelativeLayout) findViewById(R.id.relativeLayoutDaily);



        String value = getIntent().getExtras().getString("dayName");
        textViewDate.setText(value);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = f.parse(value);
            milliseconds = d.getTime();
            tomorrowz = milliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try
        {
            new LoadViewsInToWeekView().execute("2","5");
        } catch (Exception e)
        {
            Log.getStackTraceString(e);
        }


        textViewNextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int x = rand.nextInt((7 - 1) + 1) + 1;
                int y = rand.nextInt((15 - 8) + 1) + 8;

                Log.d("HASIL RAND", "X: "+x+" Y: "+y);

                Log.d("HARI INI", String.valueOf(milliseconds));

                tomorrowz = tomorrowz + 86400000;

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(new Date(tomorrowz));

                Log.d("Besok", dateString);
                textViewDate.setText(dateString);
                new LoadViewsInToWeekView().execute(String.valueOf(x),String.valueOf(y));


                weekDatas.clear();
            }
        });

        textViewPrevDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int x = rand.nextInt((7 - 1) + 1) + 1;
                int y = rand.nextInt((15 - 8) + 1) + 8;

                Log.d("HASIL RAND", "X: "+x+" Y: "+y);

                Log.d("HARI INI", String.valueOf(milliseconds));

                tomorrowz = tomorrowz - 86400000;

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(new Date(tomorrowz));

                Log.d("Besok", dateString);
                textViewDate.setText(dateString);
                new LoadViewsInToWeekView().execute(String.valueOf(x),String.valueOf(y));


                weekDatas.clear();
            }
        });
    }

    long tomorrowz;
    long milliseconds;
    public ArrayList<WeekSets> weekDatas;
    String tapMargin ;
    String buttonHight;
    String tomorrowDate;

    public class LoadViewsInToWeekView extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            try {

            } catch (Exception e) {
                Log.getStackTraceString(e);
            }

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                weekDatas = new ArrayList<WeekSets>();


                int x = Integer.parseInt(params[0]);
                int y = Integer.parseInt(params[1]);



                //** for sun day
                tapMargin = getWithAndHigthToButton(x);
                buttonHight = getHightOfButton(x, y);
                weekDatas.add(getWeekValues(String.valueOf(0), "12", "1",
                        tapMargin, buttonHight));


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String str) {
            int length = 0;
            int day = 0;
            try {

                WeekSets weekToDay;
                length = weekDatas.size();
                Log.i("length===>", String.valueOf(length));
                relativeLayoutDaily.removeAllViews();

                if (length != 0) {
                    for (int k = 0; k < weekDatas.size(); k++) {
                        weekToDay = weekDatas.get(k);

                        day = Integer.parseInt(weekToDay.day);
                        switch (day) {
                            case 0:

                                int sunday = 100;
                                relativeLayoutDaily
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, sunday));
                                arrayListEButtonId.add(getButton(0, sunday));
                                sunday++;
                                break;

                            default:
                                break;
                        }

                    }

                }

            } catch (Exception e) {
                Log.getStackTraceString(e);
            }

        }



    }

    public static Entity1 getEntity(String jobID, String jobRefID) {
        Entity1 E = new Entity1();
        E.JobIDForButton = jobID;
        E.JobRefID = jobRefID;
        return E;

    }

    public static Entity1 getButton(int layoutView, int buttonTag) {
        Entity1 E = new Entity1();
        E.layoutView = layoutView;
        E.buttonTag = buttonTag;
        return E;

    }

    public static WeekSets getWeekValues(String weekDay, String jobId,
                                         String jobRefId, String tapMargina, String buttonHighta) {
        WeekSets W = new WeekSets();
        W.day = weekDay;
        W.jobID = jobId;
        W.jobRefID = jobRefId;
        W.tapMargin = tapMargina;
        W.buttonHight = buttonHighta;

        return W;
    }

    public String getWithAndHigthToButton(int startTime) {

        int time;
        String size = "0";
        try {
            time = startTime;

            switch (time) {
                case 0:
                    size = "0";
                    break;
                case 1:
                    size = "60";

                    break;
                case 2:
                    size = "120";

                    break;
                case 3:
                    size = "180";

                    break;
                case 4:
                    size = "240";

                    break;
                case 5:
                    size = "300";

                    break;
                case 6:
                    size = "360";

                    break;
                case 7:
                    size = "420";

                    break;
                case 8:
                    size = "480";

                    break;
                case 9:
                    size = "540";

                    break;
                case 10:
                    size = "600";

                    break;
                case 11:
                    size = "660";

                    break;
                case 12:
                    size = "720";

                    break;
                case 13:
                    size = "780";

                    break;
                case 14:
                    size = "840";

                    break;
                case 15:
                    size = "900";

                    break;
                case 16:
                    size = "960";

                    break;
                case 17:
                    size = "1020";

                    break;
                case 18:
                    size = "1080";

                    break;
                case 19:
                    size = "1140";

                    break;
                case 20:
                    size = "1200";

                    break;
                case 21:
                    size = "1260";

                    break;
                case 22:
                    size = "1320";

                    break;
                case 23:
                    size = "1380";
                    break;

                default:
                    break;
            }

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }

        return size;

    }

    public String getHightOfButton(int startTime, int endTime) {
        String hight = "0";
        try {
            int subHigth = endTime - startTime;

            hight = String.valueOf(subHigth * 60);

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }

        return hight;

    }

    public Button getButtonToLayout(int higth, int marginTop,
                      String buttonText, String jobID, int buttonID) {

        @SuppressWarnings("deprecation")
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, higth);
        Button button = new Button(getApplicationContext());
        button.setLayoutParams(params);
        button.setBackgroundColor(Color.parseColor("#9ACC61"));
        button.setText(buttonText);
        button.setOnClickListener(buttonOnclckForAllWeekButton);
        button.setTextSize(9);
        button.setId(buttonID);
        params.setMargins(0, marginTop, 0, 0);
        arrayListEntity.add(getEntity(jobID, buttonText));

        return button;

    }

    public View.OnClickListener buttonOnclckForAllWeekButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Button b = (Button) v;

            String buttonText = b.getText().toString();
            int position = 0;

            for (int j = 0; j < arrayListEntity.size(); j++) {
                Entity1 itemOne = arrayListEntity.get(j);

                String arryJobRefID = itemOne.JobRefID;
                if (arryJobRefID.equals(buttonText)) {
                    position = j;
                    break;
                }
            }

            Entity1 itemOne1 = arrayListEntity.get(position);
            Toast.makeText(getApplicationContext() , "  " + itemOne1.JobRefID , Toast.LENGTH_SHORT).show();

        }
    };
}
