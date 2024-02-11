package com.example.chittopot;

import android.util.Log;

import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class HistoryEntry {

    private String emotion;
    private Object timestamp;

    public HistoryEntry() {
        // Default constructor required for calls to DataSnapshot.getValue(HistoryEntry.class)
    }

    public HistoryEntry(String emotion) {
        this.emotion = emotion;
        this.timestamp = ServerValue.TIMESTAMP; // Initialize timestamp with the Firebase ServerValue
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    // Additional methods to get formatted date and time

    public String getFormattedDate() {
        return formatDate(timestamp);
    }

    public String getFormattedTime() {
        return formatTime(timestamp);
    }

    private String formatDate(Object timestamp) {
        try {
            long timestampLong = getTimestampAsLong(timestamp);
            Date date = new Date(timestampLong);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return dateFormat.format(date);
        } catch (Exception e) {
            // Handle the exception, e.g., log an error
            Log.e("HistoryEntry", "Error formatting date", e);
            return "N/A"; // Return a default value or handle it as needed
        }
    }

    private String formatTime(Object timestamp) {
        try {
            long timestampLong = getTimestampAsLong(timestamp);
            Date date = new Date(timestampLong);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            return timeFormat.format(date);
        } catch (Exception e) {
            // Handle the exception, e.g., log an error
            Log.e("HistoryEntry", "Error formatting time", e);
            return "N/A"; // Return a default value or handle it as needed
        }
    }

    private long getTimestampAsLong(Object timestamp) {
        if (timestamp instanceof Long) {
            return (Long) timestamp;
        } else if (timestamp instanceof Map) {
            Object timestampValue = ((Map<String, Object>) timestamp).get("timestamp");

            if (timestampValue instanceof Long) {
                return (Long) timestampValue;
            } else {
                // Handle ServerValue.TIMESTAMP
                return handleServerTimestamp();
            }
        } else {
            throw new IllegalArgumentException("Unsupported timestamp format: " + timestamp.getClass());
        }
    }

    private long handleServerTimestamp() {
        // Implement your logic for handling ServerValue.TIMESTAMP
        // For example, you can use System.currentTimeMillis() as a fallback
        return System.currentTimeMillis();
    }
}
