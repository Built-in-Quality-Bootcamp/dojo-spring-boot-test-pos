/// <reference types="Cypress" />

context('Actions', () => {
  beforeEach(() => {
    cy.visit(Cypress.env("site"))
  })

  it('add items', () => {
    // https://on.cypress.io/type
    cy.get('#code')
      .type('ITEM001').should('have.value', 'ITEM001')
      .get('#name')
      .type('可口可乐').should('have.value', '可口可乐')
      .get('#unit')
      .type('听').should('have.value', '听')
      .get('#price')
      .type('2').should('have.value', '2')

    cy.get('#submit')
      .click()
      .wait(500)

    cy.get('#code')
      .type('ITEM002').should('have.value', 'ITEM002')
      .get('#name')
      .type('苹果').should('have.value', '苹果')
      .get('#unit')
      .type('斤').should('have.value', '斤')
      .get('#price')
      .type('3.5').should('have.value', '3.5')

    cy.get('#submit')
      .click()
      .wait(500)

    cy.get('#tby>tr')
      .eq(0).contains('td', 'ITEM001')
      .parent('tr').contains('td', '可口可乐')
      .parent('tr').contains('td', '听')
      .parent('tr').contains('td', '2')

    cy.get('#tby>tr')
      .eq(1).contains('td', 'ITEM002')
      .parent('tr').contains('td', '苹果')
      .parent('tr').contains('td', '斤')
      .parent('tr').contains('td', '3.5')
  })
})
