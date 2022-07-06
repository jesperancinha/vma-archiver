chai.use(require('chai-uuid'));

describe('Results #1', () => {
    it('passes', () => {
        cy.visit('http://localhost:8080/result')
        cy.get('mat-card-title').contains('VMA - Voting results!')
            .should((matCardTitle) => {
                const text = matCardTitle.text()
                expect(text).to.eq('VMA - Voting results!')
            });
        cy.get('mat-card-title').contains('VMA - Voting results!')
            .next().children().first().should((matCardTitle) => {
            const text = matCardTitle.text()
            expect(text).to.be.a.uuid()
        });
        cy.get('mat-card-title').contains('VMA - Voting results!')
            .next().children().first().next().children().should('have.length.gte', 4)
    })
})