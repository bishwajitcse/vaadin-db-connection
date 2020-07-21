package com.lausnbd;

import java.util.List;

import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;

import com.lausnbd.backend.beans.User;
import com.lausnbd.backend.services.UserService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

	private TextField textField = new TextField("Your name");
	private Button btnSave = new Button("Save");
	private Button btnfindAll = new Button("Find All");
	
	private Div usersList = new Div();
    
    @Autowired private UserService userService;
   
    public MainView() {

      
        textField.addThemeName("bordered");

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        btnSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        btnSave.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(textField, btnSave, btnfindAll, usersList);
        
        eventListenerRegister();
    }
    
    private void eventListenerRegister() {
    	
    	btnSave.addClickListener(e->{
    		
    		User user = new User();
    		user.setFirstName(textField.getValue());
    		user = userService.saveUser(user);
    		
    		Notification.show("User added successfully! You can click now Find All button for see all Users ");
    		
    		Div userName = new Div();
			userName.setText(user.getFirstName());
			usersList.add(userName);
    		
    	});
    	
    	btnfindAll.addClickListener(e->{
    		List<User> users = userService.getAllUsers();
    		usersList.removeAll();
    		for(User user:users) {
    			Div userName = new Div();
    			userName.setText(user.getFirstName());
    			usersList.add(userName);
    		}
    	});
    }

}
