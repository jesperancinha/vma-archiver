Cypress.Commands.add("titlesCheck", (title) => {
    const TIMEOUT_CONFIG = Cypress.env('TIMEOUT_CONFIG');
    getChainableCardTitle(title)
        .get("mat-card > mat-card > mat-card-title", TIMEOUT_CONFIG).contains("Best Song", TIMEOUT_CONFIG)
    getChainableCardTitle(title)
        .get("mat-card > mat-card:nth-child(2) > mat-card-title", TIMEOUT_CONFIG).contains("Best Singer", TIMEOUT_CONFIG)
    getChainableCardTitle(title)
        .get("mat-card > mat-card:nth-child(3) > mat-card-title", TIMEOUT_CONFIG).contains("Best New Artist", TIMEOUT_CONFIG)
    getChainableCardTitle(title)
        .get("mat-card > mat-card:nth-child(4) > mat-card-title", TIMEOUT_CONFIG).contains("Best Instrumental", TIMEOUT_CONFIG)
});

Cypress.Commands.add("selectFirst", (title) => {
    const TIMEOUT_CONFIG = Cypress.env('TIMEOUT_CONFIG');
    const click = $el => $el.click()
    cy.wait(1000);
    getChainableCardTitle(title)
        .get("mat-card > mat-card > mat-radio-group > mat-radio-button", TIMEOUT_CONFIG).first(TIMEOUT_CONFIG).pipe(click);
    cy.wait(200);
    getChainableCardTitle(title)
        .get("mat-card > mat-card:nth-child(2) > mat-radio-group > mat-radio-button", TIMEOUT_CONFIG).first(TIMEOUT_CONFIG).pipe(click);
    cy.wait(200);
    getChainableCardTitle(title)
        .get("mat-card > mat-card:nth-child(3) > mat-radio-group > mat-radio-button", TIMEOUT_CONFIG).first(TIMEOUT_CONFIG).pipe(click);
    cy.wait(200);
    getChainableCardTitle(title)
        .get("mat-card > mat-card:nth-child(4) > mat-radio-group > mat-radio-button", TIMEOUT_CONFIG).first(TIMEOUT_CONFIG).pipe(click);
    cy.wait(200);
});
/// <reference types="cypress" />
// ***********************************************
// This example commands.ts shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })
//
// declare global {
//   namespace Cypress {
//     interface Chainable {
//       login(email: string, password: string): Chainable<void>
//       drag(subject: string, options?: Partial<TypeOptions>): Chainable<Element>
//       dismiss(subject: string, options?: Partial<TypeOptions>): Chainable<Element>
//       visit(originalFn: CommandOriginalFn, url: string, options: Partial<VisitOptions>): Chainable<Element>
//     }
//   }
// }

function getChainableCardTitle(title: any) {
    const TIMEOUT_CONFIG = Cypress.env('TIMEOUT_CONFIG');
    return cy.get('mat-card-title', TIMEOUT_CONFIG).contains(title, TIMEOUT_CONFIG).next();
}
