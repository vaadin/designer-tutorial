package com.example.application;

import com.example.application.data.Contact;
import com.example.application.data.ContactRepository;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

/**
 * A Designer generated component for the main-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("main-view")
@JsModule("./src/views/main-view.ts")
@Route("")
public class MainView extends LitTemplate {

    @Id("filterText")
    private TextField filterText;
    @Id("addContactButton")
    private Button addContactButton;
    @Id("grid")
    private Grid<Contact> grid;
    @Id("contactForm")
    private ContactForm contactForm;

    private ContactRepository contactRepository;

    private Contact currentContact;

    private BeanValidationBinder<Contact> binder;

    public MainView(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;

        grid.addColumn(Contact::getFirstName).setHeader("First name");
        grid.addColumn(Contact::getLastName).setHeader("Last name");
        grid.addColumn(Contact::getEmail).setHeader("Email");
        grid.addColumn(Contact::getCompany).setHeader("Company");
        grid.addColumn(Contact::getStatus).setHeader("Status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        updateList();

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        configureBinding();
    }

    public void updateList() {
        String filterValue = filterText.getValue();
        if (filterValue == null || filterValue.isBlank()) {
            grid.setItems(contactRepository.findAll());
        } else {
            grid.setItems(contactRepository.findByFirstNameOrLastNameContainsIgnoreCase(filterValue, filterValue));
        }
    }

    private void configureBinding() {
        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            Contact contact = event.getValue();
            if (contact != null) {
                populateForm(contact);
            } else {
                clearForm();
            }
        });

        binder = new BeanValidationBinder<>(Contact.class);
        //Bind member fields found in the ContactForm object.
        binder.bindInstanceFields(contactForm);
        contactForm.getDelete().addClickListener(e -> {
            if (this.currentContact != null) {
                contactRepository.delete(this.currentContact);
                updateList();
                clearForm();
                Notification.show("Contact deleted.");
            }
        });

        contactForm.getClose().addClickListener(e -> {
            clearForm();
        });

        contactForm.getSave().addClickListener(e -> {
            try {
                if (this.currentContact == null) {
                    this.currentContact = new Contact();
                }
                binder.writeBean(this.currentContact);
                contactRepository.save(this.currentContact);
                updateList();
                clearForm();
                Notification.show("Contact details stored.");
            } catch (ValidationException validationException) {
                Notification.show("Please enter a valid contact details.");
            }
        });
    }

    void clearForm() {
        populateForm(null);
    }

    void populateForm(Contact contact) {
        this.currentContact = contact;
        binder.readBean(this.currentContact);
    }
}
