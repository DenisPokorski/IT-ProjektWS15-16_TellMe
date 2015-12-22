package de.hdm.tellme.client;

import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.CommonSettings;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.ReportServiceAsync;

public class ClientsideSettings extends CommonSettings{

	private static EditorServiceAsync editor = null;
	
	private static ReportServiceAsync report = null;
	
	private static final String LOGGER_NAME = "TellMe Web Client";
	
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	public static Logger getLogger() {
		return log;
	}
	
	public static EditorServiceAsync getEditor() {
		if (editor == null){
			editor = GWT.create(EditorServiceAsync.class);
		}
		return editor;
	}
	
	public static ReportServiceAsync getReport(){
		if (report == null){
			report = GWT.create(ReportServiceAsync.class);
			
			final AsyncCallback<Void> initReportCallback = new AsyncCallback<Void>(){
				@Override
				public void onFailure(Throwable caught){
					ClientsideSettings.getLogger().info(
							"Der RepertGenerator wurde initialisiert.");
				}

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					
				}
			};
		//	report.init(initReportCallback);
			
		}
		return report;
	}
}
