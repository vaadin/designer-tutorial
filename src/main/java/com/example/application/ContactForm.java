package com.example.application;

import com.example.application.data.Contact;
import com.example.application.data.ContactRepository;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

/**
 * A Designer generated component for the contact-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("contact-form")
@JsModule("./src/views/contact-form.ts")
public class ContactForm extends LitTemplate {

    @Id("firstName")
    private TextField firstName;
    @Id("lastName")
    private TextField lastName;
    @Id("email")
    private EmailField email;
    @Id("company")
    private ComboBox<String> company;
    @Id("status")
    private ComboBox<String> status;
    @Id("save")
    private Button save;
    @Id("delete")
    private Button delete;
    @Id("close")
    private Button close;

    public ContactForm(ContactRepository contactRepository) {
        company.setItems(contactRepository.findDistinctCompanies());
        status.setItems(contactRepository.findDistinctStatuses());
    }

    public Button getSave() {
        return save;
    }

    public Button getDelete() {
        return delete;
    }

    public Button getClose() {
        return close;
    }
}
