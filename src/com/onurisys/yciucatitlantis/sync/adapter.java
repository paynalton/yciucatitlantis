package com.onurisys.yciucatitlantis.sync;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import com.google.android.gms.common.GooglePlayServicesUtil;
import com.onurisys.yciucatitlantis.alertas.DBhelper;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;
import java.text.*;
import java.util.*;

public class adapter  extends AbstractThreadedSyncAdapter {
	ContentResolver mContentResolver;
	Context contexto;

    public adapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }
    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public adapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }
	@Override
	public void onPerformSync(Account arg0, Bundle arg1, String arg2,
			ContentProviderClient arg3, SyncResult arg4) {
		String xml = null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		//int gservicesAvaliable=GooglePlayServicesUtil.isGooglePlayServicesAvailable(contexto);
		//Log.v("Servicios",String.format("%i", gservicesAvaliable));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		String currentDateandTime = sdf.format(new Date());
		Log.v("fecha",currentDateandTime);
		HttpGet httpPost = new HttpGet(String.format("http://ycicatetantlis.onurisys.com:500/?lat=19.466349&lon=-99.163413&d=600&t=%s",currentDateandTime));
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			xml = EntityUtils.toString(httpEntity);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document Alertas=parseXML(xml);
		//DBhelper dbh = new DBhelper(contexto,"Alertas",null,1);
		
		//SQLiteDatabase db = dbh.getWritableDatabase();
		
		NodeList entries=Alertas.getElementsByTagName("entry");
		
		for(int i =0; i < entries.getLength();i++){
			NodeList datos=entries.item(i).getChildNodes();

			String id = "";
			String title = "";
			String updated = "";
			String identifier = "";
			String sender = "";
			String sent = "";
			String status = "";
			String msgType = "";
			String scope = "";
			String code = "";
			String language = "";
			String category = "";
			String event = "";
			String responseType = "";
			String urgency = "";
			String severity = "";
			String certainty = "";
			String effective = "";
			String expires = "";
			String senderName = "";
			String headline = "";
			String description = "";
			String web = "";
			String contact = "";
			try {
			for(int j=0;j<datos.getLength();j++){
				
				if(datos.item(j).getNodeName().equals("id")){
					id = new String(datos.item(j).getTextContent().getBytes(),"UTF-8");
				}
				else if(datos.item(j).getNodeName().equals("title")){
					title = new String(datos.item(j).getTextContent().getBytes(),"UTF-8");
				}
				else if(datos.item(j).getNodeName().equals("updated")){
					updated = new String(datos.item(j).getTextContent().getBytes(),"UTF-8");
				}
				else if(datos.item(j).getNodeName().equals("content")){
					NodeList contenidos=datos.item(j).getChildNodes();
					for(int k=0; k<contenidos.getLength();k++){
						if(contenidos.item(k).getNodeName().equals("alert")){
							NodeList alertas=contenidos.item(k).getChildNodes();
							for(int l=0;l<alertas.getLength();l++){
								if(alertas.item(l).getNodeName().equals("identifier")){
									identifier = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
								}
								else if(alertas.item(l).getNodeName().equals("sender")){
									sender = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
								}
								else if(alertas.item(l).getNodeName().equals("sent")){
									sent = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
								}
								else if(alertas.item(l).getNodeName().equals("status")){
									status = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
								}
								else if(alertas.item(l).getNodeName().equals("msgType")){
									msgType = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
								}
								else if(alertas.item(l).getNodeName().equals("scope")){
									scope = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
								}
								else if(alertas.item(l).getNodeName().equals("code")){
									code = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
								}
								else if(alertas.item(l).getNodeName().equals("info")){
									NodeList infos=alertas.item(l).getChildNodes();
									for(int m=0;m<infos.getLength();m++){
										if(infos.item(m).getNodeName().equals("language")){
											language = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
										}
										if(infos.item(m).getNodeName().equals("language")){
											language = new String(alertas.item(l).getTextContent().getBytes(),"UTF-8");
										}
									}
								}
							}
						}
					}
				}
				else {
					//Log.v("Nodo Ignorado",datos.item(j).getNodeName());
				}
			}
			}catch(UnsupportedEncodingException e){
				
			}
			
			String query="insert into Alertas values(" +
					"'"+id+"'" +","+
					"'"+title+"'" +","+
					"'"+updated+"'" +","+
							")";
			
			//Log.v("Insertando",query);
		}
		
	}
	private Document parseXML(String xml){
		Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
 
            DocumentBuilder db = dbf.newDocumentBuilder();
 
            InputSource is = new InputSource(new StringReader(xml));
            is.setEncoding("ISO-8859-15");
                doc = db.parse(is); 
 
            } catch (ParserConfigurationException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (SAXException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            }
                // return DOM
            return doc;
	}
}
