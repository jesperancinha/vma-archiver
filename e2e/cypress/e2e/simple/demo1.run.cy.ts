chai.use(require('chai-uuid'));

describe('Vote Casting #1', () => {
    const TIMEOUT_CONFIG = Cypress.env('TIMEOUT_CONFIG');
    it('passes', () => {
        cy.log(TIMEOUT_CONFIG);
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        cy.visit(`http://${host}:8080`, TIMEOUT_CONFIG);
        cy.wait(1000);
        cy.get("mat-card-title", TIMEOUT_CONFIG).contains('VMA - Cast your vote page!', TIMEOUT_CONFIG)
            .should((matCardTitle) => {
                const text = matCardTitle.text();
                expect(text).to.eq('VMA - Cast your vote page!');
            });
        cy.get("mat-card-title", TIMEOUT_CONFIG).contains('VMA - Cast your vote page!', TIMEOUT_CONFIG)
            .next().children().first().should((matCardTitle) => {
            const text = matCardTitle.text();
            expect(text).to.be.a.uuid();
        });
        cy.get("mat-card-title", TIMEOUT_CONFIG).contains("VMA - Cast your vote page!", TIMEOUT_CONFIG)
            .next().children().first().next().children().should('have.length.gte', 4);

        cy.titlesCheck('VMA - Cast your vote page!');
        cy.wait(1000);
        cy.selectFirst('VMA - Cast your vote page!');
        const click = $el => $el.click()
        cy.get("mat-card-title").next().get("button", TIMEOUT_CONFIG).pipe(click);
        cy.titlesCheck('VMA - Cast your vote page!');
    })
})
