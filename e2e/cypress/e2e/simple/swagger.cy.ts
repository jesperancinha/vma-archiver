describe('VMA E2E Tests', () => {
    const TIMEOUT_CONFIG = Cypress.env('TIMEOUT_CONFIG');
    it('shows swagger', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        cy.visit(`http://${host}:8080/api/vma/swagger-ui/index.html`, TIMEOUT_CONFIG);
        cy.contains('OpenAPI definition').should('not.be.null');
        cy.wait(1000);

        cy.get('div[class="servers"] > label > select > option', TIMEOUT_CONFIG).should('have.value', 'http://localhost:8080/api/vma')
    });
})