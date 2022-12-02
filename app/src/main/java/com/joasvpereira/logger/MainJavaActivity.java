package com.joasvpereira.logger;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joasvpereira.loggger.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class MainJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();


        Logger logger = Logger.INSTANCE;

        logger.setDefaultTag("LoggerTest");
        logger.setDefaultLevel(Logger.Level.INFO);

        ArrayList<Logger.Level> exclude = new ArrayList<Logger.Level>();
        exclude.add(Logger.Level.VERBOSE);
        exclude.add(Logger.Level.WARN);
        logger.levelsState(
                false,
                exclude
        );

        logger.log("--------------------------------------------------------------------");
        logger.log("------------------------------ Log Function ------------------------");
        logger.log("--------------------------------------------------------------------");
        logger.log("Log 1");
        logger.log("LoggerTest2","Log 2");
        logger.log(Logger.Level.ERROR,"Log 3");
        logger.log(Logger.Level.ERROR, "LoggerTest2","Log 4");
        logger.log(Logger.Level.VERBOSE, "LoggerTest2","This should not be shown");
        logger.log(Logger.Level.WARN, "LoggerTest2","This should not be shown");

        logger.levelsState(true, null);

        logger.log(Logger.Level.VERBOSE, "LoggerTest2","Know this should be shown");
        logger.log(Logger.Level.WARN, "LoggerTest2","Know this should be shown");
    }
}
