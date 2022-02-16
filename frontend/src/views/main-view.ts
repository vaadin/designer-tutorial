import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import './contact-form';

@customElement('main-view')
export class MainView extends LitElement {
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
<vaadin-vertical-layout theme="spacing" style="width: 100%; height: 100%; padding: var(--lumo-space-m);" id="vaadinVerticalLayout">
 <vaadin-horizontal-layout theme="spacing">
  <vaadin-text-field placeholder="Filter by name..." id="filterText" clear-button-visible></vaadin-text-field>
  <vaadin-button id="addContactButton">
    Add contact 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="width: 100%; height: 100%;">
  <vaadin-grid id="grid" style="width: 100%; height: 100%;flex: 2;"></vaadin-grid>
  <contact-form style="flex: 1;" id="contactForm"></contact-form>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
