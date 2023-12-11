package com.miracle.userservice.controller.sort;

import org.springframework.data.domain.Sort;

public enum ApplicationLetterListSort implements Sortable {

    SUBMIT_DATE_ASC {
        @Override
        public Sort toSort() {
            return Sort.by(Sort.Order.asc("submitDate"));
        }
    },
    SUBMIT_DATE_DESC {
        @Override
        public Sort toSort() {
            return Sort.by(Sort.Order.desc("submitDate"));
        }
    }
}
