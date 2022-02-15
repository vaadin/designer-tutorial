import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@vaadin/vaadin-combo-box/src/vaadin-combo-box.js';
import '@vaadin/vaadin-text-field/src/vaadin-email-field.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';

@customElement('contact-form')
export class ContactForm extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<vaadin-form-layout style="width: 100%; height: 100%;">
 <vaadin-text-field label="First name" id="firstName"></vaadin-text-field>
 <vaadin-text-field label="Last name" id="lastName"></vaadin-text-field>
 <vaadin-email-field label="Email" id="email"></vaadin-email-field>
 <vaadin-combo-box label="Company" id="company"></vaadin-combo-box>
 <vaadin-combo-box label="Status" id="status"></vaadin-combo-box>
 <vaadin-horizontal-layout theme="spacing">
  <vaadin-button theme="primary" id="save">
    Save 
  </vaadin-button>
  <vaadin-button theme="error" id="delete">
    Delete 
  </vaadin-button>
  <vaadin-button theme="tertiary" id="close">
    Close 
  </vaadin-button>
 </vaadin-horizontal-layout>
</vaadin-form-layout>
`;
  }

  /*
  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }*/
}
