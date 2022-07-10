chai.use(require('chai-uuid'));

describe('Vote Casting #1', () => {
    it('passes', () => {
        let host = Cypress.env('host') ?  Cypress.env('host') : 'localhost';
        cy.visit(`http://${host}:8080`);
        cy.wait(1000);
        cy.get('mat-card-title').contains('VMA - Cast your vote page!')
            .should((matCardTitle) => {
                const text = matCardTitle.text();
                expect(text).to.eq('VMA - Cast your vote page!');
            });
        cy.get('mat-card-title').contains('VMA - Cast your vote page!')
            .next().children().first().should((matCardTitle) => {
            const text = matCardTitle.text();
            expect(text).to.be.a.uuid();
        });
        cy.get('mat-card-title').contains('VMA - Cast your vote page!')
            .next().children().first().next().children().should('have.length.gte', 4);

        cy.titlesCheck('VMA - Cast your vote page!');
        cy.wait(1000);
        cy.selectFirst('VMA - Cast your vote page!');
        const click = $el => $el.click()
        cy.get('mat-card-title').next().get('button').pipe(click);
        cy.titlesCheck('VMA - Cast your vote page!');
    })
})