package de.hsmw.ui.parts;

import java.security.Provider.Service;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import de.hsmw.service.fb.android.api.IFacebookServiceAndroid;
import de.hsmw.service.fb.apple.api.IFacebookServiceApple;
import de.hsmw.ui.events.IEventConstants;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;

public class Dashboard {

	@Inject
	private IFacebookServiceAndroid androidService;
	@Inject 
	private IFacebookServiceApple appleService;
		
	private Text db_path_1;
	private Text db_path_2;
	private String db1_path;
	private String db2_path;
	
	
	
	@Inject
	@Optional
		private void subscribeTopicTodoUpdated(@UIEventTopic(IEventConstants.DBSELECTION)
	        String[] paths) {
		
		db1_path = paths[0];
		db2_path = paths[1];
		
	}
	
	
	
	
	
@PostConstruct
public void createComposite(Composite parent) {
		
	parent.setLayout(new GridLayout(2, false));
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	
	Button btnGetMessages = new Button(parent, SWT.NONE);
	
	btnGetMessages.setText("get Messages");
	new Label(parent, SWT.NONE);
	
	Button btnGetContacts = new Button(parent, SWT.NONE);
	btnGetContacts.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
		
		
		androidService.getData(db1_path, db2_path);
		}
		
		
	});
	btnGetContacts.setText("get Contacts");
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	new Label(parent, SWT.NONE);
	
	List list = new List(parent, SWT.BORDER);
	GridData gd_list = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
	gd_list.heightHint = 70;
	list.setLayoutData(gd_list);
	
	
	btnGetMessages.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			System.out.println(db1_path);
			System.out.println(db2_path);
			
			
			try {
				androidService.retrieveMessages(androidService.connectDatabase(db1_path), db1_path, db2_path);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			list.add(db1_path);
			list.add(db2_path);
		
			
						
		}
	});
	
	
}



	
}
