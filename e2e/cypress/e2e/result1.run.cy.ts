chai.use(require('chai-uuid'));

describe('Results #1', () => {
    it('passes', () => {
        cy.visit('http://localhost:8080/result')
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