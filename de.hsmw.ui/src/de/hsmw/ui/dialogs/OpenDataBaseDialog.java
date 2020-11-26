package de.hsmw.ui.dialogs;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import de.hsmw.ui.events.IEventConstants;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;



public class OpenDataBaseDialog extends TitleAreaDialog{
	
	private IEventBroker broker;
	private Text dbPath_1_text;
	private Text dbPath_2_text;
	private String db1_path;
	private String db2_path;
	Button AndroidRadioBtn;
	Button AppleRadioBtn;
	
	
	public OpenDataBaseDialog(Shell parentShell,IEventBroker broker) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		
		this.broker = broker;
		
		
		
	}

	@Override
	protected Control createDialogArea(Composite composite) {
		
		Composite container = new Composite(composite, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		setTitle("Open Database");
		setMessage("Select a DB");
		
		Button AndroidRadioBtn = new Button(container, SWT.RADIO);
		AndroidRadioBtn.setSelection(false);
		AndroidRadioBtn.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
		AndroidRadioBtn.setText("Android");
		
		new Label(container, SWT.NONE);
		
		Button AppleRadioBtn = new Button(container, SWT.RADIO);
		AppleRadioBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		AppleRadioBtn.setText("Apple");
		
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblSelectOsFirst_1 = new Label(container, SWT.NONE);
		lblSelectOsFirst_1.setText("Select OS first");
		
		dbPath_1_text = new Text(container, SWT.BORDER);
		dbPath_1_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnBrowse_1 = new Button(container, SWT.NONE);
		btnBrowse_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog fd = new FileDialog(btnBrowse_1.getShell());
				fd.setFilterPath("G:\\Softwareprojekt\\Facebook\\001Testdaten_Grundlegendes\\ExtraktionGeorgSamsonG4\\Facebook Export");
				
			
				db1_path = fd.open();
				if (db1_path != null) {
					dbPath_1_text.setText(db1_path);
				}
				
				
			}
		});
		btnBrowse_1.setEnabled(true);
		btnBrowse_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnBrowse_1.setText("Browse");
		new Label(container, SWT.NONE);
		
		Label lblSelectOsFirst_2 = new Label(container, SWT.NONE);
		lblSelectOsFirst_2.setText("Select OS first");
		
		dbPath_2_text = new Text(container, SWT.BORDER);
		dbPath_2_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnBrowse_2 = new Button(container, SWT.NONE);
		btnBrowse_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				FileDialog fd = new FileDialog(btnBrowse_2.getShell());
				fd.setFilterPath("G:\\Softwareprojekt\\Facebook\\001Testdaten_Grundlegendes\\ExtraktionGeorgSamsonG4\\Facebook Export\\com.facebook.katana\\databases");
				
				db2_path = fd.open();
				if (db2_path != null) {
					dbPath_2_text.setText(db2_path);
				}
				
				
			}
		});
		btnBrowse_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnBrowse_2.setText("Browse");
		btnBrowse_2.setEnabled(false);
		
		
		AndroidRadioBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
					System.out.println("Android gew�hlt");
					lblSelectOsFirst_2.setEnabled(true);
					
					lblSelectOsFirst_1.setText("Select threads_db2");
					lblSelectOsFirst_2.setText("Select prefs_db");
					dbPath_1_text.setEnabled(true);
					dbPath_2_text.setEnabled(true);
					btnBrowse_1.setEnabled(true);
					btnBrowse_2.setEnabled(true);
					AppleRadioBtn.setSelection(false);
					AndroidRadioBtn.setSelection(true);
			
			}
			});
			
			AppleRadioBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					
						
						System.out.println("Apple gew�hlt");
						lblSelectOsFirst_1.setText("Select lightspeed.db");
						lblSelectOsFirst_2.setEnabled(false);
						lblSelectOsFirst_2.setText("");
						dbPath_1_text.setEnabled(true);
						dbPath_2_text.setEnabled(false);
						btnBrowse_1.setEnabled(true);
						btnBrowse_2.setEnabled(false);
						AppleRadioBtn.setSelection(true);
						AndroidRadioBtn.setSelection(false);
					
				}
			});
		
		
		
		
		return composite;
				
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite composite) {
		
		Button button = createButton(composite, IDialogConstants.OK_ID, "load", false);
		
		createButton(composite, IDialogConstants.CANCEL_ID, "cancel", false);
		
	}
	
	@Override
	protected void okPressed() {
	
			
		
		String[] paths = new String[2];
		paths[0]=dbPath_1_text.getText();
		paths[1]=dbPath_2_text.getText();

				
		broker.post (IEventConstants.DBSELECTION, paths);
	
				
		super.okPressed();
		
		
		
		
		
	}
	
}