Cypress.Commands.add("titlesCheck", (title) => {
    cy.get('mat-card-title').contains(title).next()
        .get("mat-card > mat-card > mat-card-title").contains("Best Song")
    cy.get('mat-card-title').contains(title).next()
        .get("mat-card > mat-card:nth-child(2) > mat-card-title").contains("Best Singer")
    cy.get('mat-card-title').contains(title).next()
        .get("mat-card > mat-card:nth-child(3) > mat-card-title").contains("Best New Artist")
    cy.get('mat-card-title').contains(title).next()
        .get("mat-card > mat-card:nth-child(4) > mat-card-title").contains("Best Instrumental")
});
Cypress.Commands.add("selectFirst", (title) => {
    const click = $el => $el.click()
    cy.wait(1000);
    cy.get('mat-card-title').contains(title).next()
        .get("mat-card > mat-card > mat-radio-group > mat-radio-button").first().pipe(click);
    cy.wait(200);
    cy.get('mat-card-title').contains(title).next()
        .get("mat-card > mat-card:nth-child(2) > mat-radio-group > mat-radio-button").first().pipe(click);
    cy.wait(200);
    cy.get('mat-card-title').contains(title).next()
        .get("mat-card > mat-card:nth-child(3) > mat-radio-group > mat-radio-button").first().pipe(click);
    cy.wait(200);
    cy.get('mat-card-title').contains(title).next()
        .get("mat-card > mat-card:nth-child(4) > mat-radio-group > mat-radio-button").first().pipe(click);
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

