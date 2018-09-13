package retani.com.org.xmlreader;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private String fileContent = "";
    private StringBuilder processedTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void loadXMLApplication(View view) {
        // Let's get the context for loading the application files

        try {
            // The Byte array to hold the Data for the String
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            InputStream fileStream = getAssets().open("data.xml");
            byte[] characterBuffer = new byte[fileStream.available()]; // Hold 1024 bytes of data
            while (fileStream.read(characterBuffer) != -1) {
                //Let's take the result and add to the stringBuilder
                byteArrayOutputStream.write(characterBuffer);
            }
            // We need to close the Stream once we are done with it
            fileStream.close();
            fileContent = byteArrayOutputStream.toString("UTF-8");
            Log.v(TAG, fileContent);
            Log.i(TAG, "Reading file from the Application");
            processXML(fileContent);


        } catch (FileNotFoundException fileException) {
            fileException.printStackTrace();

        } catch (IOException fileException) {
            fileException.printStackTrace();
        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        }

    }

    private void processXML(String xmlContent) throws XmlPullParserException, IOException {
        //Instantiate the XMLPullParser
        // Clear the text content
        TextView xmlTextView = findViewById(R.id.xmlText);
        processedTextMessage = new StringBuilder();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        StringReader reader = new StringReader(xmlContent);

        parser.setInput(reader);


        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlResourceParser.START_TAG:

                    //Get the Tag and the text
                    String tagName = parser.getName();
                    Log.v(TAG, parser.getName());


                    break;

                case XmlResourceParser.TEXT:
                    String text = parser.getText().trim();
                    if(!text.equals("\n") && text.length() > 0){
                        processedTextMessage.append(String.format("Text Found: [%s] \n", parser.getText()));
                    }


                    break;
            }
            eventType = parser.next();


        }
        System.out.println("Ended reading document");

        xmlTextView.setText(processedTextMessage.toString());
    }
}
