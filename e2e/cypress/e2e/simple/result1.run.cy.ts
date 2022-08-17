chai.use(require('chai-uuid'));

describe('Results #1', () => {
    const TIMEOUT_CONFIG = Cypress.env('TIMEOUT_CONFIG');
    it('passes', () => {
        let host = Cypress.env('host') ?  Cypress.env('host') : 'localhost';
        cy.visit(`http://${host}:8080/result`, TIMEOUT_CONFIG);
        cy.get('mat-card-title').contains('VMA - Voting results!')
            .should((matCardTitle) => {
                const text = matCardTitle.text();
                expect(text).to.eq('VMA - Voting results!');
            });
        cy.get('mat-card-title').contains('VMA - Voting results!')
            .next().children().first().should((matCardTitle) => {
            const text = matCardTitle.text();
            expect(text).to.be.a.uuid();
        });
        let allVotingResults = cy.get('mat-card-title').contains('VMA - Voting results!')
            .next().children().first().next().children();
        allVotingResults.should('have.length.gte', 4);

        cy.titlesCheck('VMA - Voting results!');
    })
})