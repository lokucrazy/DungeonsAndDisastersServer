package com.mudndcapstone.server.utils

import org.springframework.beans.support.PagedListHolder

class PaginationHandler {

    static final int DEFAULT_PAGE = 0
    static final int DEFAULT_PAGE_SIZE = 50

    static <T> List<T> getPage(List<T> elements, Optional<Integer> _page, Optional<Integer> _count) {
        PagedListHolder pagedListHolder = new PagedListHolder(elements)
        int page = _page ? _page.orElse(DEFAULT_PAGE) : DEFAULT_PAGE
        int count = _count ? _count.orElse(DEFAULT_PAGE_SIZE) : DEFAULT_PAGE_SIZE

        pagedListHolder.setPageSize(count)

        if (page < 0 || page >= pagedListHolder.getPageCount()) return []
        pagedListHolder.setPage(page)

        pagedListHolder.getPageList()
    }

}
