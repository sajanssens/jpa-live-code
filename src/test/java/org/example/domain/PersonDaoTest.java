package org.example.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PersonDaoTest {

    PersonDao dao;

    EntityManager emMock = mock(EntityManager.class);
    EntityTransaction transactionMock = mock(EntityTransaction.class);

    @BeforeEach
    void setUp() {
        dao = new PersonDao(emMock);
    }

    @Test
    void whenCreateWithCorrectPersonThenPersonIsCommitted() {
        when(emMock.getTransaction()).thenReturn(transactionMock);

        dao.create(Person.builder().build());

        verify(transactionMock).begin();
        verify(transactionMock).commit();
    }

    @Test
    void whenCreateWithIncorrectPersonThenTransactionIsRolledBack() {
        Person p = Person.builder().id(null).build();
        when(emMock.getTransaction()).thenReturn(transactionMock);
        doThrow(RuntimeException.class).when(emMock).persist(eq(p));

        dao.create(p);

        verify(transactionMock).begin();
        verify(transactionMock, never()).commit();
        verify(transactionMock).rollback();
    }
}
