package com.mudndcapstone.server.utils

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class PaginationHandlerTests {

    @Test
    void givenEmptyElements_whenPageRequested_thenEmptyListReturned() {
        // Given
        List<Integer> list = []

        // When
        List<Integer> page = PaginationHandler.getPage(list, null, null)

        // Then
        assert page.isEmpty()
    }

    @Test
    void givenSmallList_whenPageRequested_thenEntireListReturned() {
        // Given
        List<Integer> list = (0..5).collect()

        // When
        List<Integer> page = PaginationHandler.getPage(list, null, null)

        // Then
        assert page == list
    }

    @Test
    void givenList_whenPageRequested_thenFirstPageReturned() {
        // Given
        List<Integer> list = (0..100).collect()
        int pageSize = PaginationHandler.DEFAULT_PAGE_SIZE

        // When
        List<Integer> page = PaginationHandler.getPage(list, null, null)

        // Then
        assert page == (0..<pageSize).collect()
    }

    @Test
    void givenList_whenSpecificPageRequested_thenThatPageReturned() {
        // Given
        List<Integer> list = (0..200).collect()
        int start = 150 // 3rd page
        int end = start + PaginationHandler.DEFAULT_PAGE_SIZE - 1

        // When
        List<Integer> page = PaginationHandler.getPage(list, Optional.of(3), null)

        // Then
        assert page == (start..end).collect()
    }

    @Test
    void givenList_whenSpecificCountRequested_thenThatCountReturned() {
        // Given
        List<Integer> list = (0..10).collect()

        // When
        List<Integer> page = PaginationHandler.getPage(list, null, Optional.of(5))

        // Then
        assert page == (0..<5).collect()
    }

    @Test
    void givenList_whenSpecificPageAndCountRequested_thenThatPageAndCountReturned() {
        // Given
        List<Integer> list = (0..50).collect()

        // When
        List<Integer> page = PaginationHandler.getPage(list, Optional.of(5), Optional.of(2))

        // Then
        assert page == [10, 11]
    }

}
