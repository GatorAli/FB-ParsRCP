package de.hsmw.ui.parts;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import de.hsmw.service.fb.android.api.IFacebookServiceAndroid;
import de.hsmw.service.fb.apple.api.IFacebookServiceApple;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

	public class Login {
	
		@Inject EModelService modelService;
		@Inject MApplication app;
	
	@Inject
	private IFacebookServiceAndroid androidService;
	@Inject 
	private IFacebookServiceApple appleService;
	
	
	private Text db_path_1;
	private Text db_path_2;
	private String db1_path;
	private String db2_path;
	

	
	public String getDb1_path() {
		return db1_path;
	}




	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(5, false));
		
		MUIElement dashboardWindow = modelService.find( "de.hsmw.ui.trimmedwindow.Dashboard", app);
		MUIElement loginWindow = modelService.find("de.hsmw.ui.trimmedwindow.Login", app);
		
		loginWindow.setToBeRendered(false);
		
				
		new Label(parent, SWT.NONE);
		
		Label lblSelectOs = new Label(parent, SWT.NONE);
		lblSelectOs.setText("Select OS");
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Button AndroidRadioBtn = new Button(parent, SWT.RADIO);
		AndroidRadioBtn.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
		AndroidRadioBtn.setText("Android");
		
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		new Label(parent, SWT.NONE);
		
		Button AppleRadioBtn = new Button(parent, SWT.RADIO);
		AppleRadioBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		AppleRadioBtn.setText("Apple");
		
		
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label label = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 5, 1));
		new Label(parent, SWT.NONE);
		
		Label lblSelectOsFirst_1 = new Label(parent, SWT.NONE);
		GridData gd_lblSelectOsFirst_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1);
		gd_lblSelectOsFirst_1.widthHint = 337;
		lblSelectOsFirst_1.setLayoutData(gd_lblSelectOsFirst_1);
		lblSelectOsFirst_1.setText("Select OS first");
		
		new Label(parent, SWT.NONE);
		
		db_path_1 = new Text(parent, SWT.BORDER);
		db_path_1.setEnabled(false);
		db_path_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Button BrowseBtn_1 = new Button(parent, SWT.NONE);
		BrowseBtn_1.setEnabled(false);
		BrowseBtn_1.addSelectionListener(new SelectionAdapter() {
			
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog fd = new FileDialog(BrowseBtn_1.getShell());
				fd.setFilterPath("G:\\Softwareprojekt\\Facebook\\001Testdaten_Grundlegendes\\ExtraktionGeorgSamsonG4\\Facebook Export");
				
			
				db1_path = fd.open();
				if (db1_path != null) {
					db_path_1.setText(db1_path);
				}
				
				
			}
		});
		
		
		
		BrowseBtn_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		BrowseBtn_1.setText("Browse");
		new Label(parent, SWT.NONE);
		
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblSelectOsFirst_2 = new Label(parent, SWT.NONE);
		GridData gd_lblSelectOsFirst_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1);
		gd_lblSelectOsFirst_2.widthHint = 351;
		lblSelectOsFirst_2.setLayoutData(gd_lblSelectOsFirst_2);
		lblSelectOsFirst_2.setText("Select OS first");
		
	
		
		
		
		new Label(parent, SWT.NONE);
		
		db_path_2 = new Text(parent, SWT.BORDER);
		db_path_2.setEnabled(false);
		db_path_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Button BrowseBtn_2 = new Button(parent, SWT.NONE);
		BrowseBtn_2.setEnabled(false);
		BrowseBtn_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		
		
		BrowseBtn_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog fd = new FileDialog(BrowseBtn_2.getShell());
				fd.setFilterPath("G:\\Softwareprojekt\\Facebook\\001Testdaten_Grundlegendes\\ExtraktionGeorgSamsonG4\\Facebook Export\\com.facebook.katana\\databases");
				
				db2_path = fd.open();
				if (db2_path != null) {
					db_path_2.setText(db2_path);
				}
				
				
				
				
			}
		});
		BrowseBtn_2.setText("Browse");
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		
		
		
		Button ConnectDbBtn = new Button(parent, SWT.NONE);
		ConnectDbBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(!AndroidRadioBtn.getSelection() && !AppleRadioBtn.getSelection()){
				
					Shell shell = new Shell(parent.getDisplay());
					MessageBox box = new MessageBox(shell,SWT.OK);
					box.setMessage("Please Choose an OS first");
					box.open();
				
				
				}else if(AndroidRadioBtn.getSelection() && (db2_path == null || db1_path == null) || 
						AppleRadioBtn.getSelection() && db1_path == null){
				
					Shell shell = new Shell(parent.getDisplay());
					MessageBox box = new MessageBox(shell,SWT.OK);
					box.setMessage("Please Select all Databases");
					box.open();
				
				}else if ( AndroidRadioBtn.getSelection()){
				
				dashboardWindow.setToBeRendered(true);
				//loginWindow.setToBeRendered(true);
				
				androidService.getData(db1_path, db2_path);
				//	androidService.getOwnerData(db2_path);
					
					
			}else if(AppleRadioBtn.getSelection()) {
				
				
				dashboardWindow.setToBeRendered(true);
				//loginWindow.setToBeRendered(true);
				
					appleService.getData(db1_path);
					
						
					
			}
				
		}
		});
		ConnectDbBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 5, 1));
		ConnectDbBtn.setText("Connect Database");
		
		AndroidRadioBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
					System.out.println("Android gew�hlt");
					lblSelectOsFirst_2.setEnabled(true);
					
					lblSelectOsFirst_1.setText("Select threads_db2");
					lblSelectOsFirst_2.setText("Select prefs_db");
					db_path_1.setEnabled(true);
					db_path_2.setEnabled(true);
					BrowseBtn_1.setEnabled(true);
					BrowseBtn_2.setEnabled(true);
			
			}
			});
			
			AppleRadioBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					
						
						System.out.println("Apple gew�hlt");
						lblSelectOsFirst_1.setText("Select lightspeed.db");
						lblSelectOsFirst_2.setEnabled(false);
						lblSelectOsFirst_2.setText("");
						db_path_1.setEnabled(true);
						db_path_2.setEnabled(false);
						BrowseBtn_1.setEnabled(true);
						BrowseBtn_2.setEnabled(false);
						
					
					
				}
			});
		
			
			
			
	}
	
	
	

}
