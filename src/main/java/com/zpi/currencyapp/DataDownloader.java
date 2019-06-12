package com.zpi.currencyapp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.TableA;
import com.zpi.datamodel.TableRateA;

import edu.emory.mathcs.backport.java.util.Collections;

public class DataDownloader {
	//TO DO dodać obsługę błędnych zakresów dat oraz gdy pobrane dane są puste
	public static CurrencyNoteA getDataForSingleCurrency(String currencyCode, LocalDate startDate, LocalDate EndDate) {
		String uri=createRequestUriForSingleCurrency(currencyCode,startDate,EndDate);
		String jsonStringData = executeRequest(uri);
		Gson gson = new Gson();
		CurrencyNoteA data = gson.fromJson(jsonStringData, CurrencyNoteA.class);
		return data;
	}

	private static String createRequestUriForSingleCurrency(String currencyCode, LocalDate startDate,
			LocalDate endDate) {
		String slash = "/";
		String uri = "http://api.nbp.pl/api/exchangerates/rates/a/";
		uri+=currencyCode+slash;
		uri+=startDate.toString()+slash;
		uri+=endDate.toString()+slash;
		return uri;
	}

	// "http://api.nbp.pl/api/exchangerates/rates/a/gbp/last/10/?format=json"
	private static String executeRequest(String uri) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String data = "";
		try {
			HttpGet httpGetRequest = new HttpGet(uri);

			// Execute HTTP request
			HttpResponse httpResponse = httpClient.execute(httpGetRequest);
			// Get hold of the response entity
			HttpEntity entity = httpResponse.getEntity();

			// If the response does not enclose an entity, there is no need
			// to bother about connection release
			byte[] buffer = new byte[1024];
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				try {
					int bytesRead = 0;
					
					BufferedInputStream bis = new BufferedInputStream(inputStream);
					while ((bytesRead = bis.read(buffer)) != -1) {
						data += new String(buffer, 0, bytesRead);
					}
					

				} catch (IOException ioException) {
					// In case of an IOException the connection will be released
					// back to the connection manager automatically
					ioException.printStackTrace();
				} catch (RuntimeException runtimeException) {
					// In case of an unexpected exception you may want to abort
					// the HTTP request in order to shut down the underlying
					// connection immediately.
					httpGetRequest.abort();
					runtimeException.printStackTrace();
				} finally {
					// Closing the input stream will trigger connection release
					try {
						inputStream.close();
					} catch (Exception ignore) {
					}
				}
			}
		} catch (ClientProtocolException e) {
			// thrown by httpClient.execute(httpGetRequest)
			e.printStackTrace();
		} catch (IOException e) {
			// thrown by entity.getContent();
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
	/**
	 * Downloads whole table A to get list of currencies that are available to choose
	 * @return TableA object having list of all available currencies sorted by code
	 */
	public static TableA getListOfCurrencies() {
		String uri = "http://api.nbp.pl/api/exchangerates/tables/a/"+LocalDate.now().minusDays(1).toString()+"?format=json";
		String jsonStringData = executeRequest(uri);
		Gson gson = new Gson();
		System.out.println(jsonStringData);
		TableA[] temp = gson.fromJson(jsonStringData, TableA[].class);
		TableA data=temp[0];
		data.getRates().sort((TableRateA d1,TableRateA d2)->d1.getCode().compareTo(d2.getCode()));
		return data;
	}
}
