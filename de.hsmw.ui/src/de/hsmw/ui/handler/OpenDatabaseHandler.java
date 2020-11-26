package de.hsmw.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.widgets.Shell;

import de.hsmw.ui.dialogs.OpenDataBaseDialog;

public class OpenDatabaseHandler {
	
	@Inject
	private IEventBroker eventBroker;
	
	@Execute
	public void execute(Shell parentShell) {
		
		System.out.println("test");
		
		
		
		new OpenDataBaseDialog(parentShell, eventBroker).open();
		
		
	}
	
}
